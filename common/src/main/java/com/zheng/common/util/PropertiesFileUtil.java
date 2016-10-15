package com.zheng.common.util;

import java.util.ResourceBundle;

/**
 * 配置文件读取工具
 *
 * @author shuzheng
 * @date 2016年10月15日
 */
public class PropertiesFileUtil {

    private ResourceBundle resourceBundle = null;

    public PropertiesFileUtil(String bundleFile) {
        resourceBundle = ResourceBundle.getBundle(bundleFile);
    }

    public String getValue(String key) {
        if (null == resourceBundle) {
            return null;
        }
        return resourceBundle.getString(key);
    }

}
