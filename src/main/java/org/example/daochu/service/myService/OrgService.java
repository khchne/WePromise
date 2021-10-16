package org.example.daochu.service.myService;


import org.example.daochu.model.ChargeInfoEntity;
import org.example.daochu.model.OrgInformationEntity;
import org.example.daochu.model.bo.*;
import org.example.daochu.service.*;
import org.example.daochu.utils.DateTimeUtil;
import org.example.daochu.utils.ServiceCode;
import org.example.daochu.utils.StateCode;
import org.example.daochu.vo.OrgAllInfoVo;
import org.example.daochu.vo.ServiceAllInfoVo;
import org.fisco.bcos.sdk.transaction.model.dto.CallResponse;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrgService {
    @Autowired
    private ChargeInfoService chargeInfoService;
    @Autowired
    private OrgInformationService orgInformationService;
    @Autowired
    private OrgRegisterService orgRegisterService;
    @Autowired
    private ServiceRecordService serviceRecordService;
    @Autowired
    private ServiceRemarkService serviceRemarkService;

    // 登录
    public OrgInformationEntity login(String orgName, String password) throws Exception {

        TransactionResponse response = orgRegisterService.selectLoginInfo(new OrgRegisterSelectLoginInfoInputBO(orgName, password));
        List<Object> list = response.getReturnObject();
        String orgId = (String) ((List<Object>) list.get(0)).get(0);

        CallResponse response1 = orgInformationService.selectByid(new OrgInformationSelectByidInputBO(orgId));
        List<Object> orgList = response1.getReturnObject();
        OrgInformationEntity orgInformationEntity = new OrgInformationEntity();
        orgInformationEntity.setId(orgId);
        orgInformationEntity.setName((String) ((List<Object>) orgList.get(1)).get(0));
        orgInformationEntity.setService(ServiceCode.code2Service((BigInteger) ((List<Object>) orgList.get(2)).get(0)));
        orgInformationEntity.setGrade((BigInteger) ((List<Object>) orgList.get(3)).get(0));
        return orgInformationEntity;
    }

    // 查询服务信息、服务历史
    public List<ServiceAllInfoVo> getServiceByState(String strState) throws Exception {
        List<ServiceAllInfoVo> vos = new ArrayList<>();
        System.out.println("\n\n-->> StateCode.getStateCode(strState) = " + StateCode.getStateCode(strState) + " <<<---\n\n");
        CallResponse response = serviceRemarkService.selectByState(new ServiceRemarkSelectByStateInputBO(StateCode.getStateCode(strState)));
        List<Object> remarkList = response.getReturnObject();
        System.out.println("\n\n org service : >>>> " + remarkList.toString() + "<<< \n\n");
        int size = ((List<Object>) remarkList.get(0)).size();
        System.out.println("\n\n size = " + size + "\n\n");
        for (int i = 0; i < size; i++) {
            ServiceAllInfoVo serviceAllInfoVo = new ServiceAllInfoVo();
            String serviceId = (String) ((List<Object>) remarkList.get(0)).get(i);
            CallResponse response1 = chargeInfoService.selectById(new ChargeInfoSelectByIdInputBO(serviceId));
            List<Object> chargeInfoList = response1.getReturnObject();
            System.out.println("循环ing >>>>>> chargeInfo list >>>> " + chargeInfoList.toString() + " <<<<--- \n\n");
            CallResponse response2 = serviceRecordService.selectById(new ServiceRecordSelectByIdInputBO(serviceId));
            List<Object> recordList = response2.getReturnObject();

            serviceAllInfoVo.setId(serviceId);
            serviceAllInfoVo.sethId((String) ((List<Object>) recordList.get(1)).get(0));
            serviceAllInfoVo.setOrgId((String) ((List<Object>) remarkList.get(1)).get(i));
            serviceAllInfoVo.setStartTime((String) ((List<Object>) recordList.get(2)).get(0));
            serviceAllInfoVo.setDetail((String) ((List<Object>) recordList.get(4)).get(0));
            serviceAllInfoVo.setSerAddress((String) ((List<Object>) recordList.get(3)).get(0));
            serviceAllInfoVo.setService(ServiceCode.code2Service((BigInteger) ((List<Object>) recordList.get(5)).get(0)));
            serviceAllInfoVo.setEndTime((String) ((List<Object>) remarkList.get(2)).get(i));
            serviceAllInfoVo.setComment((String) ((List<Object>) remarkList.get(3)).get(i));
            serviceAllInfoVo.setState(StateCode.code2State((BigInteger) ((List<Object>) remarkList.get(4)).get(i)));
            serviceAllInfoVo.setGrade((BigInteger) ((List<Object>) remarkList.get(5)).get(i));
            if (((List<Object>) chargeInfoList.get(1)).size() > 0) {
                serviceAllInfoVo.setChargeName((String) ((List<Object>) chargeInfoList.get(1)).get(0));
                serviceAllInfoVo.setChargePhone((String) ((List<Object>) chargeInfoList.get(2)).get(0));
            }
            vos.add(serviceAllInfoVo);
        }
        System.out.println("\n\n循环结束\n\n");
        return vos;
    }

    // 根据服务id查单;
    public void selectServiceById(String id) throws Exception {
        serviceRecordService.selectById(new ServiceRecordSelectByIdInputBO(id));
    }

    // 多一张表 负责人安排表
    public boolean acceptApply(String id, ChargeInfoEntity chargeInfoEntity) throws Exception {

        TransactionResponse response1 = serviceRemarkService.confirmService(new ServiceRemarkConfirmServiceInputBO(id, StateCode.getStateCode("已接受")));
        TransactionResponse response2 = chargeInfoService.insert(new ChargeInfoInsertInputBO(id,
                chargeInfoEntity.getChargeName(), chargeInfoEntity.getChargePhone()));
        return (response1.getReturnObject().get(0)).equals(response2.getReturnObject().get(0));
    }

    // 不接受 服务
    public boolean reject(String id, String comment) throws Exception {
        String endTime = DateTimeUtil.getSysTime();
        TransactionResponse response = serviceRemarkService.rejectService(new ServiceRemarkRejectServiceInputBO(id, endTime, comment));
        return response.getReturnObject().get(0) == null;
    }

    public OrgAllInfoVo selectOrgById(String id) throws Exception {
        OrgAllInfoVo orgAllInfoVo = new OrgAllInfoVo();
        CallResponse response = orgInformationService.selectByid(new OrgInformationSelectByidInputBO(id));
        List<Object> list = response.getReturnObject();
        orgAllInfoVo.setId((String) ((List<Object>) list.get(0)).get(0));
        orgAllInfoVo.setName((String) ((List<Object>) list.get(1)).get(0));
        orgAllInfoVo.setService(ServiceCode.code2Service((BigInteger) ((List<Object>) list.get(2)).get(0)));
        orgAllInfoVo.setGrade((BigInteger) ((List<Object>) list.get(3)).get(0));
        return orgAllInfoVo;
    }

    public void comfired(String id, String state) throws Exception {
        serviceRemarkService.confirmService(new ServiceRemarkConfirmServiceInputBO(id, StateCode.getStateCode(state)));
    }
}
