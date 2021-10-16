package org.example.daochu.controller;

import org.example.daochu.model.ChargeInfoEntity;
import org.example.daochu.service.myService.OrgService;
import org.example.daochu.vo.ServiceAllInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对于 组织来说，有以下几个服务：<br/>
 * 1、登录，通过用户名和密码登录<br/>
 * 2、展示已经申请的服务：<br/>
 * 即 通过状态查服务，展示（申请时间，用户ID，服务类型，具体需求，状态<br/>
 * 3、处理服务：<br/>
 * 3-1 接受这一条申请，为用户提供服务，<br/>
 * 接受服务时，要填写负责人 姓名 负责人 联系方式<br/>
 * 同时，组织可以查看 这一条服务的负责人，联系方式<br/>
 * 3-2 如果组织不接受 这一服务<br/>
 * 需要填写不接受 的理由<br/>
 * <br/>
 * 4、查看服务历史：<br/>
 * 主要分为俩部分<br/>
 * 待完成部分，组织可以直接看到 （预约时间，用户ID，服务类型，状态，负责人姓名）<br/>
 * 已完成部分，组织可以直接看到（提供服务的时间，用户ID，服务类型，负责人姓名）<br/>
 * 同时，可以点击详情 按钮，查看更加详细的信息：<br/>
 * 服务类型，用户ID， 服务时长， 服务地址，服务评分，以及用户评价<br/>
 */

@Controller
@RequestMapping("/org")
public class OrgController {
    @Autowired
    private OrgService orgService;

    // 登录
    @RequestMapping("/login.do")
    public ModelAndView login(String orgName, String password) {
        ModelAndView mv = new ModelAndView();
//        System.out.println("orgName = " + orgName);
//        System.out.println("password = " + password);
        mv.setViewName("redirect:/orgPart/Inipage1.html");
        return mv;
    }

    // 展示 已经申请的服务 即 通过状态（未处理）查看服务信息
    // 查看服务历史 也是通过状态查看服务信息
    @RequestMapping("/showAppliedService.do")
    @ResponseBody
    public Object showAppliedService(String state) throws Exception {
//        System.out.println("\n\n--->>> state = " + state + " <<<<--- \n\n");
        List<ServiceAllInfoVo> list = orgService.getServiceByState(state);
//        System.out.println("\n\n\n list = " + list.toString() + " << -\n\n\n");
        return list;
    }

    // 处理服务-接受服务 将负责人姓名和负责人联系方式，和服务绑定
    @RequestMapping("/acceptApply.do")
    @ResponseBody
    public Object acceptApply(String id, ChargeInfoEntity chargeInfoEntity) throws Exception {
        Map<String, Boolean> map = new HashMap<>();
        boolean res = orgService.acceptApply(id, chargeInfoEntity);
        map.put("success", res);
        return map;
    }

    // 处理服务-不接受
    @RequestMapping("/rejectApply.do")
    public void rejectApply(String id, String reason) throws Exception {
        orgService.reject(id, reason);
    }

    @RequestMapping("/selectServiceDetail")
    public Object selectServiceDetail(String id) {
        return null;
    }

    @RequestMapping("/confirmFinish.do")
    public void confirmFinish(String id, String state) throws Exception {
        orgService.comfired(id, state);
//        System.out.println("确认完毕\n\n\n\n");
    }

}
