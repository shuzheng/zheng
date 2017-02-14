package com.zheng.upms.server.controller.manage;

import com.zheng.common.base.BaseController;
import com.zheng.upms.dao.model.UpmsUser;
import com.zheng.upms.dao.model.UpmsUserExample;
import com.zheng.upms.rpc.api.UpmsUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户controller
 * Created by shuzheng on 2017/2/6.
 */
@Controller
@Api(value = "用户管理", description = "用户管理")
@RequestMapping("/manage/user")
public class UpmsUserController extends BaseController {

    private static Logger _log = LoggerFactory.getLogger(UpmsUserController.class);

    @Autowired
    private UpmsUserService upmsUserService;

    @ApiOperation(value = "用户首页")
    @RequiresPermissions("upms:user:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/user/index";
    }

    @ApiOperation(value = "用户列表")
    @RequiresPermissions("upms:user:read")
    @RequestMapping("/list")
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        // 数据列表
        UpmsUserExample upmsUserExample = new UpmsUserExample();
        upmsUserExample.setOffset(offset);
        upmsUserExample.setLimit(limit);
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            upmsUserExample.setOrderByClause(sort + " " + order);
        }
        List<UpmsUser> rows = upmsUserService.selectByExample(upmsUserExample);
        long total = upmsUserService.countByExample(upmsUserExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @ApiOperation(value = "新增用户")
    @RequiresPermissions("upms:user:create")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "/manage/user/add";
    }

    @ApiOperation(value = "新增用户")
    @RequiresPermissions("cms:user:create")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(UpmsUser upmsUser, ModelMap modelMap) {
        long time = System.currentTimeMillis();
        upmsUser.setCtime(time);
        int count = upmsUserService.insertSelective(upmsUser);
        modelMap.put("count", count);
        _log.info("新增记录id为：{}", upmsUser.getUserId());
        return "redirect:/manage/user/list";
    }

    @ApiOperation(value = "删除用户")
    @RequiresPermissions("cms:user:delete")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    public String delete(@PathVariable("ids") String ids, ModelMap modelMap) {
        int count = upmsUserService.deleteByPrimaryKeys(ids);
        modelMap.put("count", count);
        return "redirect:/manage/user/list";
    }

    @ApiOperation(value = "修改用户")
    @RequiresPermissions("cms:user:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, ModelMap modelMap) {
        UpmsUser user = upmsUserService.selectByPrimaryKey(id);
        modelMap.put("user", user);
        return "/manage/user/update";
    }

    @ApiOperation(value = "修改用户")
    @RequiresPermissions("cms:user:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") int id, UpmsUser upmsUser, ModelMap modelMap) {
        int count = upmsUserService.updateByPrimaryKeySelective(upmsUser);
        modelMap.put("count", count);
        modelMap.put("id", id);
        return "redirect:/manage/user/list";
    }

}
