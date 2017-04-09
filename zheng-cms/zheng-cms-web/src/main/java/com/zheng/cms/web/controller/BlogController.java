package com.zheng.cms.web.controller;

import com.zheng.cms.dao.model.*;
import com.zheng.cms.rpc.api.*;
import com.zheng.common.base.BaseController;
import com.zheng.common.util.Paginator;
import org.apache.commons.lang.StringUtils;
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
 * 博客首页控制器
 * Created by shuzheng on 2017/3/26.
 */
@Controller
@RequestMapping(value = "/blog")
public class BlogController extends BaseController {

    private static Logger _log = LoggerFactory.getLogger(BlogController.class);
    private static String CODE = "blog";

    @Autowired
    private CmsArticleService cmsArticleService;

    @Autowired
    private CmsCategoryService cmsCategoryService;

    @Autowired
    private CmsTagService cmsTagService;

    @Autowired
    private CmsSystemService cmsSystemService;

    @Autowired
    private CmsCommentService cmsCommentService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(@RequestParam(required = false, defaultValue = "1", value = "page") int page,
                        @RequestParam(required = false, defaultValue = "orders", value = "sort") String sort,
                        @RequestParam(required = false, defaultValue = "desc", value = "order") String order,
                        HttpServletRequest request,
                        Model model) {
        // 系统id
        CmsSystemExample cmsSystemExample = new CmsSystemExample();
        cmsSystemExample.createCriteria()
                .andCodeEqualTo(CODE);
        CmsSystem system = cmsSystemService.selectFirstByExample(cmsSystemExample);
        model.addAttribute("system", system);
        // 该系统类目
        CmsCategoryExample cmsCategoryExample = new CmsCategoryExample();
        cmsCategoryExample.createCriteria()
                .andSystemIdEqualTo(system.getSystemId());
        cmsCategoryExample.setOrderByClause("orders asc");
        List<CmsCategory> categories = cmsCategoryService.selectByExample(cmsCategoryExample);
        model.addAttribute("categories", categories);
        // 该系统标签
        CmsTagExample cmsTagExample = new CmsTagExample();
        cmsTagExample.createCriteria()
                .andSystemIdEqualTo(system.getSystemId());
        cmsTagExample.setOrderByClause("orders asc");
        List<CmsTag> tags = cmsTagService.selectByExample(cmsTagExample);
        model.addAttribute("tags", tags);
        // 该系统文章列表
        int rows = 10;
        CmsArticleExample cmsArticleExample = new CmsArticleExample();
        cmsArticleExample.createCriteria()
                .andStatusEqualTo((byte) 1)
                .andSystemIdEqualTo(system.getSystemId());
        cmsArticleExample.setOffset((page - 1) * rows);
        cmsArticleExample.setLimit(rows);
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            cmsArticleExample.setOrderByClause(sort + " " + order);
        }
        List<CmsArticle> articles = cmsArticleService.selectByExample(cmsArticleExample);
        model.addAttribute("articles", articles);
        // 文章总数
        long total = cmsArticleService.countByExample(cmsArticleExample);
        // 分页
        Paginator paginator = new Paginator(total, page, rows, request);
        model.addAttribute("paginator", paginator);
        return thymeleaf("/blog/index");
    }

    @RequestMapping(value = "/category/{alias}", method = RequestMethod.GET)
    public String category(@PathVariable("alias") String alias,
                           @RequestParam(required = false, defaultValue = "1", value = "page") int page,
                           HttpServletRequest request,
                           Model model) {
        // 系统id
        CmsSystemExample cmsSystemExample = new CmsSystemExample();
        cmsSystemExample.createCriteria()
                .andCodeEqualTo(CODE);
        CmsSystem system = cmsSystemService.selectFirstByExample(cmsSystemExample);
        model.addAttribute("system", system);
        // 当前类目
        CmsCategoryExample cmsCategoryExample = new CmsCategoryExample();
        cmsCategoryExample.createCriteria()
                .andSystemIdEqualTo(system.getSystemId())
                .andAliasEqualTo(alias);
        CmsCategory category = cmsCategoryService.selectFirstByExample(cmsCategoryExample);
        model.addAttribute("category", category);
        // 该类目文章列表
        int rows = 10;
        List<CmsArticle> articles = cmsArticleService.selectCmsArticlesByCategoryId(category.getCategoryId(), (page - 1) * rows, rows);
        model.addAttribute("articles", articles);
        // 文章总数
        long total = cmsArticleService.countByCategoryId(category.getCategoryId());
        // 分页
        Paginator paginator = new Paginator(total, page, rows, request);
        model.addAttribute("paginator", paginator);
        return thymeleaf("/blog/category/index");
    }

    @RequestMapping(value = "/tag/{alias}", method = RequestMethod.GET)
    public String tag(@PathVariable("alias") String alias,
                      @RequestParam(required = false, defaultValue = "1", value = "page") int page,
                      HttpServletRequest request,
                      Model model) {
        // 系统id
        CmsSystemExample cmsSystemExample = new CmsSystemExample();
        cmsSystemExample.createCriteria()
                .andCodeEqualTo(CODE);
        CmsSystem system = cmsSystemService.selectFirstByExample(cmsSystemExample);
        model.addAttribute("system", system);
        // 当前标签
        CmsTagExample cmsTagExample = new CmsTagExample();
        cmsTagExample.createCriteria()
                .andSystemIdEqualTo(system.getSystemId())
                .andAliasEqualTo(alias);
        CmsTag tag = cmsTagService.selectFirstByExample(cmsTagExample);
        model.addAttribute("tag", tag);
        // 该标签文章列表
        int rows = 10;
        List<CmsArticle> articles = cmsArticleService.selectCmsArticlesByTagId(tag.getTagId(), (page - 1) * rows, rows);
        model.addAttribute("articles", articles);
        // 文章总数
        long total = cmsArticleService.countByTagId(tag.getTagId());
        // 分页
        Paginator paginator = new Paginator(total, page, rows, request);
        model.addAttribute("paginator", paginator);
        return thymeleaf("/blog/tag/index");
    }

    @RequestMapping(value = "/details/{articleId}", method = RequestMethod.GET)
    public String details(@PathVariable("articleId") int articleId, Model model) {
        CmsArticle article = cmsArticleService.selectByPrimaryKey(articleId);
        model.addAttribute("article", article);
        // 系统id
        CmsSystemExample cmsSystemExample = new CmsSystemExample();
        cmsSystemExample.createCriteria()
                .andCodeEqualTo(CODE);
        CmsSystem system = cmsSystemService.selectFirstByExample(cmsSystemExample);
        model.addAttribute("system", system);
        // 评论列表
        CmsCommentExample cmsCommentExample = new CmsCommentExample();
        cmsCommentExample.createCriteria()
                .andSystemIdEqualTo(system.getSystemId())
                .andArticleIdEqualTo(articleId)
                .andStatusEqualTo((byte) 1);
        cmsCommentExample.setOrderByClause("ctime desc");
        List<CmsComment> comments = cmsCommentService.selectByExampleWithBLOBs(cmsCommentExample);
        model.addAttribute("comments", comments);
        return thymeleaf("/blog/details/index");
    }

}