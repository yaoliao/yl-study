package com.yl.study.mapper;

import com.yl.study.model.EventDefine;
import com.yl.study.model.EventDefineExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EventDefineMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_Evt_EventDefine
     *
     * @mbg.generated
     */
    long countByExample(EventDefineExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_Evt_EventDefine
     *
     * @mbg.generated
     */
    int deleteByExample(EventDefineExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_Evt_EventDefine
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer ID);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_Evt_EventDefine
     *
     * @mbg.generated
     */
    int insert(EventDefine record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_Evt_EventDefine
     *
     * @mbg.generated
     */
    int insertSelective(EventDefine record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_Evt_EventDefine
     *
     * @mbg.generated
     */
    List<EventDefine> selectByExample(EventDefineExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_Evt_EventDefine
     *
     * @mbg.generated
     */
    EventDefine selectByPrimaryKey(Integer ID);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_Evt_EventDefine
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") EventDefine record, @Param("example") EventDefineExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_Evt_EventDefine
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") EventDefine record, @Param("example") EventDefineExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_Evt_EventDefine
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(EventDefine record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_Evt_EventDefine
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(EventDefine record);
}