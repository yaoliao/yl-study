package com.yl.study.domain;

import com.yl.study.bo.EventExecuteLogBo;

/**
 * Created by DELL on 2017/11/2.
 */
public interface EventExecuteLogDomain {

    /**
     * 根据msgId获取日志执行对象
     *
     * @param msgId
     * @return
     */
    EventExecuteLogBo getByMsgId(String msgId);


    /**
     * 创建一个日志对象
     * 创建一个事件执行日志,这里把它存进数据库,并记录下创建时间和发送时间
     * insert into t_Evt_EventExecuteLog(FBizId, FBizCode, FBizName, FNodeId, FNodeName, FNodeCode, FEventDefineId,
     * FEventDefineName, FEventDefineCode, FStep, FBizPrimaryId, FTopic, FSubscribe, FProducerGroupName, FConsumerGroupName,
     * FMsgId, FDelayLevel, FCreateTime, FSendTime, FConsumeStatus, FConsumeException)
     * values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
     *
     * @param eventExecuteLog
     */
    void create(EventExecuteLogBo eventExecuteLog);


    /**
     * 开始消费
     * 根据msgId查询到事件执行日志对象,确认其存在后,修改消费状态为:消费进行中,并记录下开始消费时间
     * update t_Evt_EventExecuteLog set FConsumeStatus = 1, FConsumeStartTime = ? where FMsgId = ?
     *
     * @param msgId
     */
    void consumeStart(String msgId);


    /**
     * 消费失败
     * 根据msgId查询到事件执行日志对象,确认其存在后,修改消费状态为:消费失败,并记录下失败异常,重试次数,以及消费结束时间
     * update t_Evt_EventExecuteLog set FConsumeStatus = 3, FConsumeEndTime = ?, FConsumeException = ?, FRetry = ? where FMsgId = ?
     *
     * @param msgId
     * @param exception
     */
    void consumeFail(String msgId, String exception);

    /**
     * 消费成功
     * 根据msgId查询到事件执行日志对象,确认其存在后,修改消费状态为:消费成功,并记录下消费结束时间
     * update t_Evt_EventExecuteLog set FConsumeStatus = 2, FConsumeEndTime = ? where FMsgId = ?
     *
     * @param msgId
     */
    void consumeSuccess(String msgId);

}
