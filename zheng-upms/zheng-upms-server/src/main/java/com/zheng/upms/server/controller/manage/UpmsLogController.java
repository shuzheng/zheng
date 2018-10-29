package com.zheng.upms.server.controller.manage;

import com.zheng.common.base.BaseController;
import com.zheng.common.util.StringUtil;
import com.zheng.upms.common.constant.UpmsResult;
import com.zheng.upms.common.constant.UpmsResultConstant;
import com.zheng.upms.dao.model.UpmsLog;
import com.zheng.upms.dao.model.UpmsLogExample;
import com.zheng.upms.rpc.api.UpmsLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 日志controller
 * Created by shuzheng on 2017/3/14.
 */
@Controller
@Api(value = "日志管理", description = "日志管理")
@RequestMapping("/manage/log")
public class UpmsLogController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsLogController.class);

    @Autowired
    private UpmsLogService upmsLogService;

    @ApiOperation(value = "日志首页")
    @RequiresPermissions("upms:log:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/log/index.jsp";
    }

    @ApiOperation(value = "日志列表")
    @RequiresPermissions("upms:log:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        UpmsLogExample upmsLogExample = new UpmsLogExample();
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            upmsLogExample.setOrderByClause(StringUtil.humpToLine(sort) + " " + order);
        }
        if (StringUtils.isNotBlank(search)) {
            upmsLogExample.or()
                    .andDescriptionLike("%" + search + "%");
        }
        List<UpmsLog> rows = upmsLogService.selectByExampleForOffsetPage(upmsLogExample, offset, limit);
        long total = upmsLogService.countByExample(upmsLogExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @ApiOperation(value = "删除日志")
    @RequiresPermissions("upms:log:delete")
    @RequestMapping(value = "/delete/{ids}", method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        int count = upmsLogService.deleteByPrimaryKeys(ids);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

}