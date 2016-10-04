package com.zheng.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.zheng.mapper.UserMapper;
import com.zheng.model.User;
import com.zheng.model.UserVO;

/**
 * 测试service
 * @author shuzheng
 * @date 2016年7月6日 下午6:07:43
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"classpath:applicationContext.xml",
	"classpath:applicationContext-jdbc.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void index() {
		// 自定义接口调用
		UserVO userVO = userService.selectUserWithBook(1);
		System.out.println(userVO.getBooks().size());
		// 自动生成接口调用
		User user2 = userService.getMapper().selectByPrimaryKey(1);
		System.out.println(user2.getNickname());
	}
	
}
