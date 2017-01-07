package com.zheng.cms.web.controller.manage;

import com.zheng.cms.web.controller.BaseController;
import com.zheng.cms.dao.model.CmsComment;
import com.zheng.cms.dao.model.CmsCommentExample;
import com.zheng.cms.rpc.api.CmsCommentService;
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
 * 评论控制器
 * Created by shuzheng on 2016/11/14.
 */
@Controller
@RequestMapping("/manage/comment")
public class CmsCommentController extends BaseController {

	private final static Logger _log = LoggerFactory.getLogger(CmsCommentController.class);
	
	@Autowired
	private CmsCommentService cmsCommentService;

	/**
	 * 列表
	 * @param page 当前页码
	 * @param rows 每页条数
	 * @param desc 降序排序
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(
			@RequestParam(required = false, defaultValue = "1", value = "page") int page,
			@RequestParam(required = false, defaultValue = "20", value = "rows") int rows,
			@RequestParam(required = false, defaultValue = "true", value = "desc") boolean desc,
			HttpServletRequest request, Model model) {

		// 数据列表
		CmsCommentExample cmsCommentExample = new CmsCommentExample();
		cmsCommentExample.setOffset((page - 1) * rows);
		cmsCommentExample.setLimit(rows);
		cmsCommentExample.setOrderByClause(desc ? "comment_id desc" : "comment_id asc");
		List<CmsComment> comments = cmsCommentService.selectByExample(cmsCommentExample);

		// 分页对象
		long total = cmsCommentService.countByExample(cmsCommentExample);
		Paginator paginator = new Paginator(total, page, rows, request);

		model.addAttribute("comments", comments);
		model.addAttribute("paginator", paginator);
		return "/manage/comment/list";
	}
	
	/**
	 * 新增get
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add() {
		return "/manage/comment/add";
	}
	
	/**
	 * 新增post
	 * @param cmsComment
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(CmsComment cmsComment, Model model) {
		cmsComment.setCtime(System.currentTimeMillis());
		int count = cmsCommentService.insertSelective(cmsComment);
		model.addAttribute("count", count);
		_log.info("新增记录id为：{}", cmsComment.getArticleId());
		return "redirect:/manage/comment/list";
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
		return "redirect:/manage/comment/list";
	}
	
	/**
	 * 修改get
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, Model model) {
		CmsComment comment = cmsCommentService.selectByPrimaryKey(id);
		model.addAttribute("comment", comment);
		return "/manage/comment/update";
	}
	
	/**
	 * 修改post
	 * @param id
	 * @param cmsComment
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String update(@PathVariable("id") int id, CmsComment cmsComment, Model model) {
		int count = cmsCommentService.updateByPrimaryKeySelective(cmsComment);
		model.addAttribute("count", count);
		model.addAttribute("id", id);
		return "redirect:/manage/comment/list";
	}

}