package com.yl.study.service;

import com.yl.study.mapper.EventDefineMapper;
import com.yl.study.model.EventDefine;
import com.yl.study.model.EventDefineExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by DELL on 2017/11/2.
 */
@Service
public class EventDefineService {

    @Resource
    private EventDefineMapper eventDefineMapper;


    public List<EventDefine> findByNodeDefineCode(String nodeDefineCode) {
        EventDefineExample eventDefineExample = new EventDefineExample();
        EventDefineExample.Criteria criteria = eventDefineExample.createCriteria();
        criteria.andNodeDefineCodeEqualTo(nodeDefineCode);
        criteria.andIsEnabledEqualTo(1);
        return eventDefineMapper.selectByExample(eventDefineExample);
    }


    public EventDefine getByCode(String code) {

        EventDefineExample eventDefineExample = new EventDefineExample();
        EventDefineExample.Criteria criteria = eventDefineExample.createCriteria();
        criteria.andCodeEqualTo(code);
        criteria.andIsEnabledEqualTo(1);
        List<EventDefine> eventDefines = eventDefineMapper.selectByExample(eventDefineExample);
        return CollectionUtils.isNotEmpty(eventDefines) ? eventDefines.get(0) : null;
    }


}
