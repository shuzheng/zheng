package com.zheng.cms.web.schedule;

import com.taobao.pamirs.schedule.IScheduleTaskDealSingle;
import com.taobao.pamirs.schedule.TaskItemDefine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 测试任务
 * Created by zhangshuzheng on 2016/11/14.
 */
public class MapSchedule implements IScheduleTaskDealSingle<Map> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapSchedule.class);

    /**
     * 执行单个任务
     * @param item Object
     * @param ownSign 当前环境名称
     * @throws Exception
     */
    @Override
    public boolean execute(Map item, String ownSign) throws Exception {
        LOGGER.info("执行任务：{}", item);
        return true;
    }

    /**
     * 根据条件，查询当前调度服务器可处理的任务
     * @param taskParameter 任务的自定义参数
     * @param ownSign 当前环境名称
     * @param taskItemNum 当前任务类型的任务队列数量
     * @param taskItemList 当前调度服务器，分配到的可处理队列
     * @param eachFetchDataNum 每次获取数据的数量
     * @return
     * @throws Exception
     */
    @Override
    public List<Map> selectTasks(String taskParameter, String ownSign, int taskItemNum, List<TaskItemDefine> taskItemList, int eachFetchDataNum) throws Exception {
        List<Map> allDrawList = new ArrayList<>();
        Map map = new HashMap(1);
        map.put("ID", System.currentTimeMillis());
        allDrawList.add(map);
        return allDrawList;
    }

    /**
     * 获取任务的比较器,只有在NotSleep模式下需要用到
     * @return
     */
    @Override
    public Comparator<Map> getComparator() {
        return new Comparator<Map>() {
            @Override
            public int compare(Map o1, Map o2) {
                Long l1 = (Long) o1.get("ID");
                Long l2 = (Long) o2.get("ID");
                return l1.compareTo(l2);
            }

            @Override
            public boolean equals(Object obj) {
                return this == obj;
            }
        };
    }

}
