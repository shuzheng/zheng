package com.zheng.upms.rpc.service;

import com.zheng.upms.dao.mapper.UpmsUserMapper;
import com.zheng.upms.dao.model.UpmsSystemExample;
import com.zheng.upms.dao.model.UpmsUser;
import com.zheng.upms.rpc.api.UpmsSystemService;
import com.zheng.upms.rpc.api.UpmsUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * 单元测试
 * Created by shuzheng on 2017/2/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:applicationContext.xml",
        "classpath:META-INF/spring/applicationContext-jdbc.xml",
        "classpath:META-INF/spring/applicationContext-listener.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class UpmsServiceTest {

    @Autowired
    private UpmsSystemService upmsSystemService;

    @Autowired
    private UpmsUserMapper upmsUserMapper;

    @Autowired
    private UpmsUserService upmsUserService;

    @Test
    public void index() {
        UpmsUser upmsUser = new UpmsUser();
        upmsUser.setAvatar("");
        upmsUser.setCtime(System.currentTimeMillis());
        upmsUser.setEmail("");
        upmsUser.setLocked((byte) 0);
        upmsUser.setPassword("xxx");
        upmsUser.setPhone("");
        upmsUser.setRealname("zsz");
        upmsUser.setSex((byte) 1);
        upmsUser.setSalt("");
        upmsUser.setUsername("zsz");
        upmsUserService.insertSelective(upmsUser);
        System.out.println(upmsUser.getUserId());
    }

}
