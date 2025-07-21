package com.task.retry.mapper.mysql;

import com.task.retry.entity.model.Task;
import com.task.retry.entity.request.QueryTaskRequest;
import com.task.retry.entity.request.TaskPageRequest;
import com.task.retry.mapper.TaskMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Author: miracle
 * Date: 2023/9/7 14:55
 */
@Mapper
public interface MySqlTaskMapper extends TaskMapper {

    /**
     * 分页查询
     *
     * @param offset   起始偏移量
     * @param pageSize 每页大小
     * @param request  入参
     * @return list
     */
    List<Task> pageList(@Param("offset") Integer offset,
                        @Param("pageSize") Integer pageSize,
                        @Param("request") TaskPageRequest request);

    List<Task> queryList(@Param("request") QueryTaskRequest request);

    int save(Task task);

    Task getById(@Param("id") Long id);

    int updateById(@Param("task") Task task, @Param("stateList") List<String> stateList);

    int selectNotCompleteFailedCount(@Param("businessType") String businessType, @Param("groupId") String groupId);

}
