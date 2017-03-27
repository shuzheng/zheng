package com.zheng.cms.web.controller;

import com.zheng.cms.dao.model.CmsArticle;
import com.zheng.cms.dao.model.CmsComment;
import com.zheng.cms.dao.model.CmsCommentExample;
import com.zheng.cms.rpc.api.CmsArticleService;
import com.zheng.cms.rpc.api.CmsCommentService;
import com.zheng.common.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

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

    @Autowired
    private CmsCommentService cmsCommentService;

    @RequestMapping(value = "/{articleId}", method = RequestMethod.GET)
    public String index(@PathVariable("articleId") int articleId, Model model) {
        CmsArticle article = cmsArticleService.selectByPrimaryKey(articleId);
        model.addAttribute("article", article);
        // 评论列表
        CmsCommentExample cmsCommentExample = new CmsCommentExample();
        cmsCommentExample.createCriteria()
                .andArticleIdEqualTo(articleId)
                .andStatusEqualTo((byte) 1);
        cmsCommentExample.setOrderByClause("ctime desc");
        List<CmsComment> comments = cmsCommentService.selectByExampleWithBLOBs(cmsCommentExample);
        model.addAttribute("comments", comments);
        return thymeleaf("/article/index");
    }

}