package org.example.daochu.service.myService;


import org.example.daochu.model.HandicapCertsEntity;
import org.example.daochu.model.HandicapRegisterEntity;
import org.example.daochu.model.OrgInformationEntity;
import org.example.daochu.model.bo.HandicapCertsInsertCertInputBO;
import org.example.daochu.model.bo.HandicapRegisterRegisterInputBO;
import org.example.daochu.model.bo.HandicapRegisterSelectByidInputBO;
import org.example.daochu.model.bo.OrgInformationInsertOrgInfoInputBO;
import org.example.daochu.service.HandicapCertsService;
import org.example.daochu.service.HandicapRegisterService;
import org.example.daochu.service.OrgInformationService;
import org.example.daochu.utils.HandicapCategoryCode;
import org.example.daochu.utils.HandicapLevelCode;
import org.example.daochu.utils.ServiceCode;
import org.example.daochu.vo.HandicapAllInfoVo;
import org.example.daochu.vo.OrgAllInfoVo;
import org.fisco.bcos.sdk.transaction.model.dto.CallResponse;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class GovService {
    @Autowired
    private HandicapCertsService handicapCertsService;
    @Autowired
    private HandicapRegisterService handicapRegisterService;
    @Autowired
    private OrgInformationService orgInformationService;


    // 注册残疾证
    public boolean registryHandicapCert(HandicapCertsEntity handicapCertEntity) throws Exception {
        String id = handicapCertEntity.getId();
        String name = handicapCertEntity.getName();
        String certId = handicapCertEntity.getCertId();
        BigInteger hCategory = HandicapCategoryCode.getHandicapCategoryCode(handicapCertEntity.gethCategory());
        BigInteger hLevel = HandicapLevelCode.getHandicapLevelCode(handicapCertEntity.gethLevel());
        TransactionResponse response = handicapCertsService.insertCert(new HandicapCertsInsertCertInputBO(id, name, certId, hCategory, hLevel));
        return ((List<Integer>) response.getReturnObject().get(0)).get(0) == 1;
    }

    // 注册残疾人登录信息
    public boolean registryHandicapRegister(HandicapRegisterEntity handicapRegisterEntity) throws Exception {
        TransactionResponse response = handicapRegisterService.register(new HandicapRegisterRegisterInputBO(handicapRegisterEntity.getId(),
                handicapRegisterEntity.gethPhone(), handicapRegisterEntity.getGuardianName(),
                handicapRegisterEntity.getGuardianPhone()));
        return (response.getReturnObject().get(0)).equals(BigInteger.valueOf(1));
    }

    // 注册组织信息
    public boolean registryOrg(OrgInformationEntity orgInformationEntity) throws Exception {
        String id = orgInformationEntity.getId();
        String name = orgInformationEntity.getName();
        BigInteger service = ServiceCode.getServiceCode(orgInformationEntity.getService());
        TransactionResponse response = orgInformationService.insertOrgInfo(new OrgInformationInsertOrgInfoInputBO(id, name, service, BigInteger.valueOf(0)));
        return (response.getReturnObject().get(0)).equals(1);
    }

    // 获取所有残疾人信息
    public List<HandicapAllInfoVo> getHandicapAllInfo() throws Exception {
        List<HandicapAllInfoVo> list = new ArrayList<>();
        CallResponse response = handicapCertsService.selectAll();
        List<Object> certList = response.getReturnObject();
        int size = ((List<Object>) certList.get(0)).size();
        for (int i = 0; i < size; i++) {
            HandicapAllInfoVo handicapAllInfoVo = new HandicapAllInfoVo();
            String handicapId = (String) ((List<Object>) certList.get(0)).get(i);
            CallResponse response1 = handicapRegisterService.selectByid(new HandicapRegisterSelectByidInputBO(handicapId));
            List<Object> registerList = response1.getReturnObject();

            handicapAllInfoVo.setId(handicapId);
            handicapAllInfoVo.setName((String) ((List<Object>) certList.get(1)).get(i));
            handicapAllInfoVo.setCertId((String) ((List<Object>) certList.get(2)).get(i));
            handicapAllInfoVo.sethCategory(HandicapCategoryCode.code2Category((BigInteger) ((List<Object>) certList.get(3)).get(i)));
            handicapAllInfoVo.sethLevel(HandicapLevelCode.code2Level((BigInteger) ((List<Object>) certList.get(4)).get(i)));
            handicapAllInfoVo.sethPhone((String) ((List<Object>) registerList.get(1)).get(0));
            handicapAllInfoVo.setGuardianName((String) ((List<Object>) registerList.get(2)).get(0));
            handicapAllInfoVo.setGuardianPhone((String) ((List<Object>) registerList.get(3)).get(0));
            list.add(handicapAllInfoVo);
        }
        return list;
    }

    // 获取所有组织信息
    public List<OrgAllInfoVo> getOrgAllInfo() throws Exception {
        List<OrgAllInfoVo> list = new ArrayList<>();

        CallResponse response = orgInformationService.selectAll();
        List<Object> orgInfoList = response.getReturnObject();

        int size = ((List<Object>) orgInfoList.get(0)).size();
        for (int i = 0; i < size; i++) {
            OrgAllInfoVo orgAllInfoVo = new OrgAllInfoVo();
            String id = (String) ((List<Object>) orgInfoList.get(0)).get(i);
            orgAllInfoVo.setId(id);
            orgAllInfoVo.setName((String) ((List<Object>) orgInfoList.get(1)).get(i));
            orgAllInfoVo.setService(ServiceCode.code2Service((BigInteger) ((List<Object>) orgInfoList.get(2)).get(i)));
            orgAllInfoVo.setGrade((BigInteger) ((List<Object>) orgInfoList.get(3)).get(i));
        }
        return list;
    }


}
