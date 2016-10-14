package com.zheng.cms.service;

import com.zheng.cms.model.User;
import com.zheng.cms.model.UserVO;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * 测试service
 * @author shuzheng
 * @date 2016年7月6日 下午6:07:43
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"classpath:applicationContext.xml",
	"classpath:applicationContext-jdbc.xml",
	"classpath:applicationContext-ehcache.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void index() {
		// 自定义接口调用
		UserVO userVO = userService.selectUserWithBook(1);
		System.out.println(null == userVO ? "null" : userVO.getBooks().size());
		// 自动生成接口调用
		User user = userService.getMapper().selectByPrimaryKey(1);
		System.out.println(null == user ? "null" :user.getNickname());

		// Create a cache manager
		final CacheManager cacheManager = CacheManager.getInstance();

		// create the cache called "hello-world"
		final Cache cache = cacheManager.getCache("ehCache");

		// create a key to map the data to
		final String key = "key";

		// Create a data element
		final Element element = new Element(key, "value");

		// Put the element into the data store
		cache.put(element);

		// Retrieve the data element
		final Element cacheElement = cache.get(key);

		// Print the value
		System.out.println(cacheElement.getObjectValue());
	}
	
}
