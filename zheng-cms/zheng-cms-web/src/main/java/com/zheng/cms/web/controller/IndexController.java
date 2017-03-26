package com.zheng.cms.web.controller;

import com.zheng.cms.dao.model.*;
import com.zheng.cms.rpc.api.*;
import com.zheng.common.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 首页控制器
 * Created by shuzheng on 2017/3/19.
 */
@Controller
public class IndexController extends BaseController {

    private static Logger _log = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private CmsMenuService cmsMenuService;

    @Autowired
    private CmsCategoryService cmsCategoryService;

    @Autowired
    private CmsTagService cmsTagService;

    @Autowired
    private CmsTopicService cmsTopicService;

    @Autowired
    private CmsArticleService cmsArticleService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        // 菜单
        CmsMenuExample cmsMenuExample = new CmsMenuExample();
        cmsMenuExample.setOrderByClause("orders asc");
        List<CmsMenu> menus = cmsMenuService.selectByExample(cmsMenuExample);
        model.addAttribute("menus", menus);
        // 所有类目
        CmsCategoryExample cmsCategoryExample = new CmsCategoryExample();
        cmsCategoryExample.setOrderByClause("orders asc");
        List<CmsCategory> categories = cmsCategoryService.selectByExample(cmsCategoryExample);
        model.addAttribute("categories", categories);
        // 所有标签
        CmsTagExample cmsTagExample = new CmsTagExample();
        cmsTagExample.setOrderByClause("orders asc");
        List<CmsTag> tags = cmsTagService.selectByExample(cmsTagExample);
        model.addAttribute("tags", tags);
        // 最新5个专题
        CmsTopicExample cmsTopicExample = new CmsTopicExample();
        cmsTopicExample.setOrderByClause("ctime desc");
        List<CmsTopic> topics = cmsTopicService.selectByExample(cmsTopicExample);
        model.addAttribute("topics", topics);
        // 最新5条文章
        CmsArticleExample cmsArticleExample = new CmsArticleExample();
        cmsArticleExample.createCriteria()
                .andStatusEqualTo((byte) 1);
        cmsArticleExample.setOrderByClause("orders desc");
        List<CmsArticle> newArticles = cmsArticleService.selectByExample(cmsArticleExample);
        model.addAttribute("newArticles", newArticles);
        // 最火5条文章
        cmsArticleExample.setOrderByClause("readnumber desc");
        List<CmsArticle> hotArticles = cmsArticleService.selectByExample(cmsArticleExample);
        model.addAttribute("hotArticles", hotArticles);
        return thymeleaf("/index");
    }

}