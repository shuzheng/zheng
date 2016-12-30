package com.zheng.upms.rpc.service.impl;

import com.zheng.upms.dao.mapper.UpmsSystemMapper;
import com.zheng.upms.rpc.api.UpmsSystemService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统service实现
 * Created by shuzheng on 2016/12/18.
 */
@Service
@Transactional
public class UpmsSystemServiceImpl implements UpmsSystemService {

    private static Logger _log = LoggerFactory.getLogger(UpmsSystemServiceImpl.class);

    @Autowired
    private UpmsSystemMapper upmsSystemMapper;

    @Override
    public UpmsSystemMapper getMapper() {
        return upmsSystemMapper;
    }

    // 批量删除
    @Override
    public int deleteByPrimaryKeys(String ids) {
        if (StringUtils.isBlank(ids)) {
            return 0;
        }
        String[] idArray = ids.split("-");
        int count = 0;
        for (String id : idArray) {
            if (StringUtils.isBlank(id)) {
                continue;
            }
            try {
                count += upmsSystemMapper.deleteByPrimaryKey(Integer.parseInt(id));
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
        return count;
    }

    @Override
    public String test() {
        return "hello world!";
    }

}