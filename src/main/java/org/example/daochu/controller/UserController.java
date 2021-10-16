package org.example.daochu.controller;


import org.example.daochu.model.ServiceRecordEntity;
import org.example.daochu.service.HandicapCertsService;
import org.example.daochu.service.OrgInformationService;
import org.example.daochu.service.myService.OrgService;
import org.example.daochu.service.myService.UserService;
import org.example.daochu.utils.UUIDUtil;
import org.example.daochu.vo.HandicapAllInfoVo;
import org.example.daochu.vo.OrgAllInfoVo;
import org.example.daochu.vo.ServiceAllInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.List;

/**
 * 对于用户 有以下几个功能：<br/>
 * 1、登录，通过输入用户名和密码进行登录  <br/>
 * 2、查看服务： <br/>
 * 通过服务类型查看提供服务的组织简要信息，（展现在初始页面<br/>
 * 点击组织名称可以查看到组织的详细信息<br/>
 * 在查看组织详细信息的时候，可以看到历史的评论信息和评分<br/>
 * <p>
 * 3、申请服务：<br/>
 * 用户可以通过点击 组织 信息后 的 申请按钮， 填写相关的信息（服务类型，组织名称， 服务时间， 服务地址，具体描述），申请服务
 * <p>
 * 4、模糊查询：<br/>
 * 用户可以通过页面的查询框，输入组织的名称，<br/>
 * 查询到组织信息，展示在页面中<br/>
 * 5、查看服务历史：<br/>
 * 通过 服务的状态，查询到服务的历史情况<br/>
 * 一个是已接受<br/>
 * 一个是已完成<br/>
 * 一个是未接受<br/>
 */

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private OrgService orgService;

    private String handicapId;

    // 登录
    @RequestMapping("/login.do")
    public ModelAndView login(String userName, String password, HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView();
/*        System.out.println("userName = " + userName);
        System.out.println("password = " + password);*/
        session.setAttribute("id", userName);
//        session.setAttribute("userName", userName);
        handicapId = userName;
        HandicapAllInfoVo handicapAllInfoVo = userService.selectLoginIfo(userName, password);
        if (handicapAllInfoVo == null) {
            mv.addObject("err", "msg");
            return mv;
        } else {
            session.setAttribute("id", handicapAllInfoVo.getId());
            session.setAttribute("name", handicapAllInfoVo.getName());
            session.setAttribute("hCategory", handicapAllInfoVo.gethCategory());
            session.setAttribute("hLevel", handicapAllInfoVo.gethLevel());
        }
        mv.setViewName("redirect:/userPart/Inipage.html");
        return mv;
    }

    // 查看服务的组织（获取组织的详细信息
    @RequestMapping("/selectOrg.do")
    @ResponseBody
    public Object selectOrgByService(String ser) throws Exception {
        return userService.selectOrgInfoByService(ser);
    }

    // 根据组织id查组织信息
    @RequestMapping("/selectOrgById")
    @ResponseBody
    public Object selectOrgById(String id) throws Exception {
        OrgAllInfoVo orgAllInfoVo = orgService.selectOrgById(id);
        System.out.println(orgAllInfoVo.toString());
        return orgAllInfoVo;
    }

    // 获取历史评论信息
    @RequestMapping("/getHisComm")
    @ResponseBody
    public Object getHistoryComment(String orgId) throws Exception {

        return userService.getServiceComm(orgId);
    }

    // 申请服务
    @RequestMapping("/apply.do")
    @ResponseBody
    public boolean apply(ServiceRecordEntity serviceRecordEntity, String orgId) throws Exception {
        serviceRecordEntity.setId(UUIDUtil.getUUID());
//        System.out.println("\n\n\n--> id = " + serviceRecordEntity.getId() + "\n\n\n");
        serviceRecordEntity.sethId(handicapId);
//        System.out.println("\n\n" + serviceRecordEntity.toString() + "\n\n" + "orgId = " + orgId + "\n\n");
        return userService.applyService(serviceRecordEntity, orgId);
    }

    // 查看服务历史，通过状态查服务历史
    @ResponseBody
    @RequestMapping("/getHistory")
    public Object get(String state) throws Exception {
//        System.out.println("\n\n-->state = " + state + " << -- \n\n 对应code为 --> " + StateCode.getStateCode(state) + "\n\n");
        List<ServiceAllInfoVo> list = userService.getServiceByState(state);
        for (ServiceAllInfoVo serviceAllInfoVo : list) {
            String orgId = serviceAllInfoVo.getOrgId();
            OrgAllInfoVo orgAllInfoVo = orgService.selectOrgById(orgId);
            serviceAllInfoVo.setOrgId(orgAllInfoVo.getName());
        }
        System.out.println(list.toString());
        return list;
    }

    @RequestMapping("/comm.do")
    public void comm(String id, Integer sco, String comment) throws Exception {
        BigInteger score = BigInteger.valueOf(sco);
        userService.confirmService(id, score, comment);
        System.out.println("评论成功");
    }

}
