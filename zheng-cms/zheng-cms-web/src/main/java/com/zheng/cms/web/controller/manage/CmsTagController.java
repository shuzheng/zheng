package com.zheng.cms.web.controller.manage;

import com.zheng.cms.web.controller.BaseController;
import com.zheng.cms.dao.model.CmsTag;
import com.zheng.cms.dao.model.CmsTagExample;
import com.zheng.cms.rpc.api.CmsTagService;
import com.zheng.common.util.Paginator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 标签控制器
 * Created by shuzheng on 2016/11/14.
 */
@Controller
@RequestMapping("/manage/tag")
public class CmsTagController extends BaseController {

	private final static Logger _log = LoggerFactory.getLogger(CmsTagController.class);
	
	@Autowired
	private CmsTagService cmsTagService;

	/**
	 * 列表
	 * @param page 当前页码
	 * @param rows 每页条数
	 * @param desc 降序排序
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/list")
	public String list(
			@RequestParam(required = false, defaultValue = "1", value = "page") int page,
			@RequestParam(required = false, defaultValue = "20", value = "rows") int rows,
			@RequestParam(required = false, defaultValue = "false", value = "desc") boolean desc,
			HttpServletRequest request, ModelMap modelMap) {

		// 数据列表
		CmsTagExample cmsTagExample = new CmsTagExample();
		cmsTagExample.setOffset((page - 1) * rows);
		cmsTagExample.setLimit(rows);
		cmsTagExample.setOrderByClause(desc ? "orders desc" : "orders asc");
		List<CmsTag> tags = cmsTagService.selectByExample(cmsTagExample);

		// 分页对象
		long total = cmsTagService.countByExample(cmsTagExample);
		Paginator paginator = new Paginator(total, page, rows, request);

		modelMap.put("tags", tags);
		modelMap.put("paginator", paginator);
		return "/manage/tag/list";
	}
	
	/**
	 * 新增get
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add() {
		return "/manage/tag/add";
	}
	
	/**
	 * 新增post
	 * @param cmsTag
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(CmsTag cmsTag, ModelMap modelMap) {
		long time = System.currentTimeMillis();
		cmsTag.setCtime(time);
		cmsTag.setOrders(time);
		int count = cmsTagService.insertSelective(cmsTag);
		modelMap.put("count", count);
		_log.info("新增记录id为：{}", cmsTag.getTagId());
		return "redirect:/manage/tag/list";
	}

	/**
	 * 删除
	 * @param ids
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	public String delete(@PathVariable("ids") String ids, ModelMap modelMap) {
		int count = cmsTagService.deleteByPrimaryKeys(ids);
		modelMap.put("count", count);
		return "redirect:/manage/tag/list";
	}
	
	/**
	 * 修改get
	 * @param id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, ModelMap modelMap) {
		CmsTag tag = cmsTagService.selectByPrimaryKey(id);
		modelMap.put("tag", tag);
		return "/manage/tag/update";
	}
	
	/**
	 * 修改post
	 * @param id
	 * @param cmsTag
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String update(@PathVariable("id") int id, CmsTag cmsTag, ModelMap modelMap) {
		int count = cmsTagService.updateByPrimaryKeySelective(cmsTag);
		modelMap.put("count", count);
		modelMap.put("id", id);
		return "redirect:/manage/tag/list";
	}

}