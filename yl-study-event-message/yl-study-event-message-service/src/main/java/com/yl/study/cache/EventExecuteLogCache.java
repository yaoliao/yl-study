package com.yl.study.cache;

import com.alibaba.fastjson.TypeReference;
import com.yl.common.utils.JSONUtils;
import com.yl.redis.RedisUtil;
import com.yl.study.bo.EventExecuteLogBo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by DELL on 2017/11/2.
 */
@Component
public class EventExecuteLogCache {

    private static final String EVENT_EXECUTE_LOG_CACHE_KEY = "EventExecuteLogKey";

    @Resource
    private RedisUtil redisUtil;

    public EventExecuteLogBo getByMsgID(String msgId) {

        String str = redisUtil.hashGet(EVENT_EXECUTE_LOG_CACHE_KEY, msgId);
        EventExecuteLogBo eventExecuteLogBo = null;
        if (StringUtils.isNotBlank(str)) {
            eventExecuteLogBo = JSONUtils.toObject(str, new TypeReference<EventExecuteLogBo>() {
            });
        }
        return eventExecuteLogBo;
    }

    public void setEventLog(EventExecuteLogBo eventExecuteLogBo) {
        if (eventExecuteLogBo != null) {
            redisUtil.hashSet(EVENT_EXECUTE_LOG_CACHE_KEY, eventExecuteLogBo.getMsgId(), JSONUtils.toJSONString(eventExecuteLogBo));
        }
    }

    public void deleteEventLog(String... msgIds) {
        redisUtil.hdelFields(EVENT_EXECUTE_LOG_CACHE_KEY, msgIds);
    }


}
