package org.example.daochu.service.myService;

import org.example.daochu.model.ServiceRecordEntity;
import org.example.daochu.model.bo.*;
import org.example.daochu.service.*;
import org.example.daochu.utils.*;
import org.example.daochu.vo.HandicapAllInfoVo;
import org.example.daochu.vo.OrgAllInfoVo;
import org.example.daochu.vo.ServiceAllInfoVo;
import org.example.daochu.vo.ServiceCommVo;
import org.fisco.bcos.sdk.transaction.model.dto.CallResponse;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private HandicapCertsService handicapCertsService;
    @Autowired
    private HandicapRegisterService handicapRegisterService;
    @Autowired
    private OrgInformationService orgInformationService;
    @Autowired
    private ServiceRecordService serviceRecordService;
    @Autowired
    private ServiceRemarkService serviceRemarkService;

    // 登录
    public HandicapAllInfoVo selectLoginIfo(String userName, String password) throws Exception {
        HandicapAllInfoVo handicapAllInfoVo = new HandicapAllInfoVo();

        TransactionResponse response = handicapRegisterService.selectLoginInfo(new HandicapRegisterSelectLoginInfoInputBO(userName, password));
        Map<String, List<List<Object>>> map = response.getEventResultMap();
        Set<String> set = response.getEventResultMap().keySet();
        for (String s : set) {
            List<List<Object>> list = map.get(s);
            handicapAllInfoVo.setId((String) list.get(0).get(0));
            handicapAllInfoVo.setName((String) list.get(1).get(0));
            handicapAllInfoVo.setCertId((String) list.get(2).get(0));
            handicapAllInfoVo.sethCategory(HandicapCategoryCode.code2Category((BigInteger) list.get(3).get(0)));
            handicapAllInfoVo.sethLevel(HandicapLevelCode.code2Level((BigInteger) list.get(4).get(0)));
        }
        CallResponse callResponse = handicapCertsService.selectByid(new HandicapCertsSelectByidInputBO(userName));
        List<Object> list = callResponse.getReturnObject();
        handicapAllInfoVo.sethPhone((String) ((List<Object>) list.get(1)).get(0));
        handicapAllInfoVo.setGuardianName((String) ((List<Object>) list.get(2)).get(0));
        handicapAllInfoVo.setGuardianPhone((String) ((List<Object>) list.get(3)).get(0));

        return handicapAllInfoVo;
    }

    // 根据服务 类型 查询 组织的的所有信息
    public List<OrgAllInfoVo> selectOrgInfoByService(String service) throws Exception {
        BigInteger state = ServiceCode.getServiceCode(service);

        CallResponse response = orgInformationService.selectByservice(new OrgInformationSelectByserviceInputBO(state));
        List<Object> list = response.getReturnObject();
        List<OrgAllInfoVo> res = new ArrayList<>();
        int size = ((List<Object>) list.get(0)).size();
        for (int i = 0; i < size; i++) {
            OrgAllInfoVo orgAllInfoVo = new OrgAllInfoVo();
            orgAllInfoVo.setId((String) ((List<Object>) list.get(0)).get(i));
            orgAllInfoVo.setName((String) ((List<Object>) list.get(1)).get(i));
            orgAllInfoVo.setService(ServiceCode.code2Service((BigInteger) ((List<Object>) list.get(2)).get(i)));
            orgAllInfoVo.setGrade((BigInteger) ((List<Object>) list.get(3)).get(i));
            res.add(orgAllInfoVo);
        }
        // 应该还有一个表的，存放着组织的公开信息，通过id查组织的公开信息，完善orgAllInfoVo
        return res;
    }

    // 根据 组织组织ID 查服务记录 可以得到 服务评价，服务评分，用户id
    public List<ServiceCommVo> getServiceComm(String orgId) throws Exception {
        List<ServiceCommVo> res = new ArrayList<>();

        CallResponse remarkResponse = serviceRemarkService.selectByOrgId(new ServiceRemarkSelectByOrgIdInputBO(orgId));
        int size = ((List<Object>) remarkResponse.getReturnObject().get(0)).size();
        List<Object> remarkList = remarkResponse.getReturnObject();
        for (int i = 0; i < size; i++) {
            String serviceId = (String) ((List<Object>) remarkList.get(0)).get(i);
            CallResponse recordResponse = serviceRecordService.selectById(new ServiceRecordSelectByIdInputBO(serviceId));
            List<Object> recordList = recordResponse.getReturnObject();
            ServiceCommVo vo = new ServiceCommVo();
            vo.setId(serviceId);
            vo.setSerAddress((String) ((List<Object>) recordList.get(3)).get(0));
            vo.setComment((String) ((List<Object>) remarkList.get(3)).get(i));
            vo.setGrade((BigInteger) ((List<Object>) remarkList.get(5)).get(i));
            vo.setService(ServiceCode.code2Service((BigInteger) ((List<Object>) recordList.get(5)).get(0)));
            res.add(vo);
        }
        return res;
    }

    // 申请服务，给服务添加信息
    public boolean applyService(ServiceRecordEntity serviceRecordEntity, String orgId) throws Exception {
        String id = serviceRecordEntity.getId();
        String hId = serviceRecordEntity.gethId();
        String startTime = serviceRecordEntity.getStartTime();
        String serAddress = serviceRecordEntity.getSerAddress();
        String detail = serviceRecordEntity.getDetail();
        BigInteger serviceCode = ServiceCode.getServiceCode(serviceRecordEntity.getService());
        TransactionResponse response1 = serviceRecordService.applyService(new ServiceRecordApplyServiceInputBO(id, hId, startTime, serAddress, detail, serviceCode));
        TransactionResponse response2 = serviceRemarkService.applyService(new ServiceRemarkApplyServiceInputBO(id, orgId));
        return (response1.getReturnObject().get(0)) == (response2.getReturnObject().get(0));
    }

    // 服务确认
    public boolean confirmService(String serviceId, BigInteger score, String comment) throws Exception {
        String endTime = DateTimeUtil.getSysTime();
        TransactionResponse response1 = serviceRemarkService.confirmService(new ServiceRemarkConfirmServiceInputBO(serviceId, StateCode.getStateCode("已完成")));
        TransactionResponse response2 = serviceRemarkService.commentService(new ServiceRemarkCommentServiceInputBO(serviceId, endTime, comment, score));

        return ((List<Object>) response1.getReturnObject().get(0)).get(0) == ((List<Object>) response2.getReturnObject().get(0)).get(0);
    }

    // 根据服务状态查服务历史
    public List<ServiceAllInfoVo> getServiceByState(String strState) throws Exception {
        List<ServiceAllInfoVo> vos = new ArrayList<>();
        CallResponse response = serviceRemarkService.selectByState(new ServiceRemarkSelectByStateInputBO(StateCode.getStateCode(strState)));
        List<Object> remarkList = response.getReturnObject();
        int size = ((List<Object>) remarkList.get(0)).size();

        for (int i = 0; i < size; i++) {
            ServiceAllInfoVo serviceAllInfoVo = new ServiceAllInfoVo();
            String serviceId = (String) ((List<Object>) remarkList.get(0)).get(i);

            CallResponse callResponse = serviceRecordService.selectById(new ServiceRecordSelectByIdInputBO(serviceId));
            List<Object> recordList = callResponse.getReturnObject();

            serviceAllInfoVo.sethId(serviceId);
            serviceAllInfoVo.setOrgId((String) ((List<Object>) remarkList.get(1)).get(i));
            serviceAllInfoVo.setStartTime((String) ((List<Object>) recordList.get(2)).get(0));
            serviceAllInfoVo.setDetail((String) ((List<Object>) recordList.get(4)).get(0));
            serviceAllInfoVo.setSerAddress((String) ((List<Object>) recordList.get(3)).get(0));
            serviceAllInfoVo.setService(ServiceCode.code2Service((BigInteger) ((List<Object>) recordList.get(5)).get(0)));
            serviceAllInfoVo.setEndTime((String) ((List<Object>) remarkList.get(2)).get(i));
            serviceAllInfoVo.setComment((String) ((List<Object>) remarkList.get(3)).get(i));
            serviceAllInfoVo.setState(StateCode.code2State((BigInteger) ((List<Object>) remarkList.get(4)).get(i)));
            serviceAllInfoVo.setGrade((BigInteger) ((List<Object>) remarkList.get(5)).get(i));

            vos.add(serviceAllInfoVo);
        }

        return vos;
    }


}
