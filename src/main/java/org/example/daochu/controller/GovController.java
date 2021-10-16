package org.example.daochu.controller;


import org.example.daochu.model.HandicapCertsEntity;
import org.example.daochu.model.HandicapRegisterEntity;
import org.example.daochu.model.OrgInformationEntity;
import org.example.daochu.model.bo.HandicapCertsInsertCertInputBO;
import org.example.daochu.service.HandicapCertsService;
import org.example.daochu.service.myService.GovService;
import org.example.daochu.utils.HandicapCategoryCode;
import org.example.daochu.utils.HandicapLevelCode;
import org.example.daochu.utils.UUIDUtil;
import org.example.daochu.vo.HandicapAllInfoVo;
import org.example.daochu.vo.OrgAllInfoVo;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

/**
 * 管理者方后台，管理者可以调用里面的数据进行
 * 注册、查看
 * 对数据进行合理的利用 例如
 * <p>
 * 数据可视化
 */

@Controller
@RequestMapping("/gov")
public class GovController {
    @Autowired
    private GovService govService;
    @Autowired
    private HandicapCertsService handicapCertsService;

    @RequestMapping("/registry.do")
    public void registry(HandicapCertsEntity handicapCertEntity) throws Exception {
        String id = handicapCertEntity.getId();
        String name = handicapCertEntity.getName();
        String certId = handicapCertEntity.getCertId();
        BigInteger hCategory = HandicapCategoryCode.getHandicapCategoryCode(handicapCertEntity.gethCategory());
        BigInteger hLevel = HandicapLevelCode.getHandicapLevelCode(handicapCertEntity.gethLevel());
        TransactionResponse response = handicapCertsService.insertCert(new HandicapCertsInsertCertInputBO(id, name, certId, hCategory, hLevel));
        Set<String> set = response.getEventResultMap().keySet();
     /*   for (String s : set) {
            System.out.println("\n\n --> s = " + s + "  <---\n\n");
            System.out.println("\n\n --> res = " + response.getEventResultMap().get(s).toString() + " <--- \n\n");
        }*/
    }

    @RequestMapping("/registryUser.do")
    public void registryUser(HandicapRegisterEntity handicapRegisterEntity) throws Exception {
//        System.out.println("\n\n handicapRegisterEntity -->>" + handicapRegisterEntity.toString() + "<<<<--\n\n");
        govService.registryHandicapRegister(handicapRegisterEntity);
    }

    @RequestMapping("/registryOrg.do")
    public void registryOrg(OrgInformationEntity orgInformationEntity) throws Exception {
        orgInformationEntity.setId(UUIDUtil.getUUID());
//        System.out.println("\n\n orgInformationEntity = -->" + orgInformationEntity.toString() + " << -- \n\n");
        govService.registryOrg(orgInformationEntity);
//        System.out.println("-------组织信息注册成功------------");
    }

    // 通过异步请求得到数据
    @RequestMapping("/getAllHandicapInfo.do")
    public List<HandicapAllInfoVo> getAllHandicapInfo() throws Exception {
        return govService.getHandicapAllInfo();
    }

    // 通过异步请求得到数据
    @RequestMapping("/getAllOrgInfo.do")
    public List<OrgAllInfoVo> getAllOrgInfo() throws Exception {
        return govService.getOrgAllInfo();
    }
}
