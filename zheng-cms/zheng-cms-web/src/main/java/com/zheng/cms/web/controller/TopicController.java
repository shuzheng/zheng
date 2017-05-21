package com.zheng.cms.web.controller;

import com.zheng.cms.dao.model.CmsTopic;
import com.zheng.cms.dao.model.CmsTopicExample;
import com.zheng.cms.rpc.api.CmsTopicService;
import com.zheng.common.base.BaseController;
import com.zheng.common.util.Paginator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 专题首页控制器
 * Created by shuzheng on 2017/3/26.
 */
@Controller
@RequestMapping(value = "/topic")
public class TopicController extends BaseController {

    private static Logger _log = LoggerFactory.getLogger(TopicController.class);

    @Autowired
    private CmsTopicService cmsTopicService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(
            @RequestParam(required = false, defaultValue = "1", value = "page") int page,
            HttpServletRequest request,
            Model model) {
        // 专题列表
        int rows = 10;
        CmsTopicExample cmsTopicExample = new CmsTopicExample();
        List<CmsTopic> topics = cmsTopicService.selectByExampleForOffsetPage(cmsTopicExample, (page - 1) * rows, rows);
        model.addAttribute("topics", topics);
        // 文章总数
        long total = cmsTopicService.countByExample(cmsTopicExample);
        // 分页
        Paginator paginator = new Paginator(total, page, rows, request);
        model.addAttribute("paginator", paginator);
        return thymeleaf("/topic/list");
    }

    @RequestMapping(value = "{topicId}", method = RequestMethod.GET)
    public String index(@PathVariable("topicId") int topicId, Model model) {
        CmsTopic topic = cmsTopicService.selectByPrimaryKey(topicId);
        model.addAttribute("topic", topic);
        return thymeleaf("/topic/index");
    }

}