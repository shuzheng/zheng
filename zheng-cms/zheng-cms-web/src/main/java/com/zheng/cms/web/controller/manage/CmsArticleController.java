package com.zheng.cms.web.controller.manage;

import com.zheng.cms.web.controller.BaseController;
import com.zheng.cms.dao.model.CmsArticle;
import com.zheng.cms.dao.model.CmsArticleExample;
import com.zheng.cms.rpc.api.CmsArticleService;
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
 * 文章控制器
 * Created by shuzheng on 2016/11/14.
 */
@Controller
@RequestMapping("/manage/article")
public class CmsArticleController extends BaseController {

	private final static Logger _log = LoggerFactory.getLogger(CmsArticleController.class);
	
	@Autowired
	private CmsArticleService cmsArticleService;

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
		CmsArticleExample cmsArticleExample = new CmsArticleExample();
		cmsArticleExample.setOffset((page - 1) * rows);
		cmsArticleExample.setLimit(rows);
		cmsArticleExample.setOrderByClause(desc ? "orders desc" : "orders asc");
		List<CmsArticle> articles = cmsArticleService.selectByExample(cmsArticleExample);

		// 分页对象
		long total = cmsArticleService.countByExample(cmsArticleExample);
		Paginator paginator = new Paginator(total, page, rows, request);

		model.addAttribute("articles", articles);
		model.addAttribute("paginator", paginator);
		return "/manage/article/list";
	}
	
	/**
	 * 新增get
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add() {
		return "/manage/article/add";
	}
	
	/**
	 * 新增post
	 * @param cmsArticle
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(CmsArticle cmsArticle, Model model) {
		long time = System.currentTimeMillis();
		cmsArticle.setCtime(time);
		cmsArticle.setOrders(time);
		int count = cmsArticleService.insertSelective(cmsArticle);
		model.addAttribute("count", count);
		_log.info("新增记录id为：{}", cmsArticle.getArticleId());
		return "redirect:/manage/article/list";
	}

	/**
	 * 删除
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	public String delete(@PathVariable("ids") String ids, Model model) {
		int count = cmsArticleService.deleteByPrimaryKeys(ids);
		model.addAttribute("count", count);
		return "redirect:/manage/article/list";
	}
	
	/**
	 * 修改get
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, Model model) {
		CmsArticle article = cmsArticleService.selectByPrimaryKey(id);
		model.addAttribute("article", article);
		return "/manage/article/update";
	}
	
	/**
	 * 修改post
	 * @param id
	 * @param cmsArticle
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String update(@PathVariable("id") int id, CmsArticle cmsArticle, Model model) {
		int count = cmsArticleService.updateByPrimaryKeySelective(cmsArticle);
		model.addAttribute("count", count);
		model.addAttribute("id", id);
		return "redirect:/manage/article/list";
	}


}