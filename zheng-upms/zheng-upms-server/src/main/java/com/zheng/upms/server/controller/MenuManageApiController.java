package com.zheng.upms.server.controller;


import com.google.common.collect.Maps;
import com.zheng.common.base.BaseController;
import com.zheng.common.util.JsonUtil;
import com.zheng.upms.common.constant.UpmsResult;
import com.zheng.upms.common.constant.UpmsResultConstant;
import com.zheng.upms.dao.model.UpmsPermission;
import com.zheng.upms.dao.model.UpmsSystem;
import com.zheng.upms.dao.model.UpmsSystemExample;
import com.zheng.upms.dao.model.UpmsUser;
import com.zheng.upms.rpc.api.UpmsApiService;
import com.zheng.upms.rpc.api.UpmsSystemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * <p> Title: </p>
 * <p> Description: </p>
 *
 * @作者 Kayuu
 * @创建时间
 * @版本 1.00
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * ----------------------------------------
 * <p>
 * ----------------------------------------
 * </pre>
 */
@Controller
@RequestMapping("/manage/api")
@Api(value = "菜单管理", description = "根据用户和系统ID获取所拥有的权限")
public class MenuManageApiController extends BaseController {


    @Autowired
    private UpmsSystemService upmsSystemService;

    @Autowired
    private UpmsApiService upmsApiService;


    /**
     * 根据用户和系统ID获取所拥有的权限
     * @param callback 固定参数
     * @parm systemId 系统ID
     * @return
     */
    @ApiOperation(value = "对外提供获取菜单接口")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ResponseBody
    public Object index(String callback,String systemId) {
        HashMap<Object, Object> reslutMap = Maps.newHashMapWithExpectedSize(2);
        // 已注册系统
        UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
        upmsSystemExample.createCriteria().andStatusEqualTo((byte) 1);
        List<UpmsSystem> upmsSystems = upmsSystemService.selectByExample(upmsSystemExample);
        reslutMap.put("upmsSystems", upmsSystems);
        // 当前登录用户权限
        Subject subject = SecurityUtils.getSubject();
        UpmsUser upmsUser = (UpmsUser) subject.getPrincipal();
        List<UpmsPermission> upmsPermissions = upmsApiService.selectUpmsPermissionByUpmsUserIdAndSystemId(upmsUser.getUserId(),Integer.valueOf(systemId));
        reslutMap.put("upmsPermissions", upmsPermissions);
        reslutMap.put("upmsUser",upmsUser);
        UpmsResult upmsResult = new UpmsResult(UpmsResultConstant.SUCCESS, reslutMap);
        if(callback !=null && "callback".equals(callback)){
            return callback +"("+ JsonUtil.toJSONString(upmsResult) +")";
        }else{
            return upmsResult;
        }
    }
}
