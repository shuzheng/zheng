package com.zheng.cms.web.controller;

import com.zheng.cms.dao.model.CmsArticle;
import com.zheng.cms.rpc.api.CmsArticleService;
import com.zheng.common.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 文章控制器
 * Created by shuzheng on 2017/3/26.
 */
@Controller
@RequestMapping(value = "/article")
public class ArticleController extends BaseController {

    private static Logger _log = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private CmsArticleService cmsArticleService;

    @RequestMapping(value = "/{articleId}", method = RequestMethod.GET)
    public String index(@PathVariable("articleId") int articleId, Model model) {
        CmsArticle article = cmsArticleService.selectByPrimaryKey(articleId);
        model.addAttribute("article", article);
        return thymeleaf("/article/index");
    }

}