package com.zheng.cms.controller;

import com.zheng.cms.dao.model.CmsComment;
import com.zheng.cms.dao.model.CmsCommentExample;
import com.zheng.cms.dao.model.CmsTag;
import com.zheng.cms.dao.model.CmsTagExample;
import com.zheng.cms.service.CmsCommentService;
import com.zheng.common.util.Paginator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 评论控制器
 * Created by shuzheng on 2016/11/14.
 */
@Controller
@RequestMapping("/comment")
public class CmsCommentController extends BaseController {

	private final static Logger _log = LoggerFactory.getLogger(CmsCommentController.class);
	
	@Autowired
	private CmsCommentService cmsCommentService;

	/**
	 * 列表
	 * @param page
	 * @param rows
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(
			@RequestParam(required = false, defaultValue = "1", value = "page") int page,
			@RequestParam(required = false, defaultValue = "20", value = "rows") int rows,
			HttpServletRequest request, Model model) {

		// 数据列表
		CmsCommentExample cmsCommentExample = new CmsCommentExample();
		cmsCommentExample.setOffset((page - 1) * rows);
		cmsCommentExample.setLimit(rows);
		cmsCommentExample.setOrderByClause("orders desc");
		List<CmsComment> comments = cmsCommentService.getMapper().selectByExample(cmsCommentExample);

		// 分页对象
		long total = cmsCommentService.getMapper().countByExample(cmsCommentExample);
		Paginator paginator = new Paginator(total, page, rows, request);

		model.addAttribute("comments", comments);
		model.addAttribute("paginator", paginator);
		return "/comment/list";
	}
	
	/**
	 * 新增get
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add() {
		return "/comment/add";
	}
	
	/**
	 * 新增post
	 * @param cmsComment
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@Valid CmsComment cmsComment, Model model) {
		cmsComment.setCtime(System.currentTimeMillis());
		int count = cmsCommentService.getMapper().insertSelective(cmsComment);
		model.addAttribute("count", count);
		_log.info("新增记录id为：{}", cmsComment.getArticleId());
		return "redirect:/comment/list";
	}

	/**
	 * 删除
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	public String delete(@PathVariable("ids") String ids, Model model) {
		int count = cmsCommentService.deleteByPrimaryKeys(ids);
		model.addAttribute("count", count);
		return "redirect:/comment/list";
	}
	
	/**
	 * 修改get
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, Model model) {
		CmsComment comment = cmsCommentService.getMapper().selectByPrimaryKey(id);
		model.addAttribute("comment", comment);
		return "/comment/update";
	}
	
	/**
	 * 修改post
	 * @param id
	 * @param cmsComment
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String update(@PathVariable("id") int id, @Valid CmsComment cmsComment, Model model) {
		int count = cmsCommentService.getMapper().updateByPrimaryKeySelective(cmsComment);
		model.addAttribute("count", count);
		model.addAttribute("id", id);
		return "redirect:/comment/list";
	}

}