package com.zheng.cms.controller;

import com.zheng.cms.dao.model.CmsTag;
import com.zheng.cms.dao.model.CmsTagExample;
import com.zheng.cms.service.CmsTagService;
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
 * 标签控制器
 * Created by shuzheng on 2016/11/14.
 */
@Controller
@RequestMapping("/tag")
public class CmsTagController extends BaseController {

	private final static Logger _log = LoggerFactory.getLogger(CmsTagController.class);
	
	@Autowired
	private CmsTagService cmsTagService;

	/**
	 * 首页
	 * @return
	 */
	@RequestMapping("")
	public String index() {
		return "redirect:/tag/list";
	}
	
	/**
	 * 列表
	 * @param page
	 * @param rows
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public String list(
			@RequestParam(required = false, defaultValue = "1", value = "page") int page,
			@RequestParam(required = false, defaultValue = "20", value = "rows") int rows,
			HttpServletRequest request, Model model) {

		// 数据列表
		CmsTagExample cmsTagExample = new CmsTagExample();
		cmsTagExample.setOffset((page - 1) * rows);
		cmsTagExample.setLimit(rows);
		cmsTagExample.setOrderByClause("tagId desc");
		List<CmsTag> tags = cmsTagService.getMapper().selectByExample(cmsTagExample);

		// 分页对象
		long total = cmsTagService.getMapper().countByExample(cmsTagExample);
		Paginator paginator = new Paginator(total, page, rows, request);

		model.addAttribute("tags", tags);
		model.addAttribute("paginator", paginator);
		return "/tag/list";
	}
	
	/**
	 * 新增get
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add() {
		return "/tag/add";
	}
	
	/**
	 * 新增post
	 * @param cmsTag
	 * @param binding
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@Valid CmsTag cmsTag, BindingResult binding) {
		if (binding.hasErrors()) {
			for (ObjectError error : binding.getAllErrors()) {
				_log.error(error.getDefaultMessage());
			}
			return "/tag/add";
		}
		cmsTag.setCtime(System.currentTimeMillis());
		cmsTagService.getMapper().insertSelective(cmsTag);
		_log.info("新增记录id为：{}", cmsTag.getTagId());
		return "redirect:/tag/list";
	}

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
	public String delete(@PathVariable("id") int id) {
		cmsTagService.getMapper().deleteByPrimaryKey(id);
		return "redirect:/tag/list";
	}
	
	/**
	 * 修改get
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, Model model) {
		model.addAttribute("tag", cmsTagService.getMapper().selectByPrimaryKey(id));
		return "/tag/update";
	}
	
	/**
	 * 修改post
	 * @param id
	 * @param cmsTag
	 * @param binding
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String update(@PathVariable("id") int id, @Valid CmsTag cmsTag, BindingResult binding, Model model) {
		if (binding.hasErrors()) {
			model.addAttribute("errors", binding.getAllErrors());
			return "/tag/update/" + id;
		}
		cmsTagService.getMapper().updateByPrimaryKeySelective(cmsTag);
		return "redirect:/tag/list";
	}

}