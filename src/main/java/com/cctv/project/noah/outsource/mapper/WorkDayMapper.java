package com.cctv.project.noah.outsource.mapper;

import com.cctv.project.noah.outsource.entity.WorkDay;

import java.util.Date;
import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author system
 * @date 2020-05-06
 */
public interface WorkDayMapper {
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public WorkDay selectWorkDayById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param workDay 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<WorkDay> selectWorkDayList(WorkDay workDay);

    public List<WorkDay> selectHoliday(String start,String end);

    public List<WorkDay> selectWorkDayByDate(String date);

    /**
     * 新增【请填写功能名称】
     * 
     * @param workDay 【请填写功能名称】
     * @return 结果
     */
    public int insertWorkDay(WorkDay workDay);

    /**
     * 修改【请填写功能名称】
     * 
     * @param workDay 【请填写功能名称】
     * @return 结果
     */
    public int updateWorkDay(WorkDay workDay);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteWorkDayById(Long id);

    public int deleteWorkDayByDate(String date);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWorkDayByIds(String[] ids);
}
