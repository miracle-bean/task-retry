package com.task.retry.mapper;

import com.task.retry.entity.model.TaskDO;
import com.task.retry.entity.request.TaskPageRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Author: miracle
 * Date: 2023/9/7 14:55
 */
public interface TaskMapper {

    /**
     * 分页查询
     *
     * @param offset   起始偏移量
     * @param pageSize 每页大小
     * @param request  入参
     * @return list
     */
    List<TaskDO> pageList(@Param("offset") Integer offset,
                          @Param("pageSize") Integer pageSize,
                          @Param("request") TaskPageRequest request);

    int save(TaskDO task);

    TaskDO getById(@Param("id") Long id);

    int updateById(@Param("task") TaskDO task, @Param("stateList") List<String> stateList);


}
