package com.yl.study.domain;

import com.yl.common.utils.BeanUtil;
import com.yl.study.bo.EventDefineBo;
import com.yl.study.model.EventDefine;
import com.yl.study.service.EventDefineService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by DELL on 2017/11/2.
 */
@Service(value = "eventDefineDomain")
public class EventDefineNativeDomain implements EventDefineDomain {

    @Resource
    private EventDefineService eventDefineService;


    /**
     * 根据节点code 获取 事件列表
     *
     * @param nodeDefineCode
     * @return
     */
    @Override
    public List<EventDefineBo> findByNodeDefineCode(String nodeDefineCode) {
        List<EventDefine> eventDefines = eventDefineService.findByNodeDefineCode(nodeDefineCode);
        return BeanUtil.convertList(eventDefines, EventDefineBo.class);
    }

    /**
     * 根据事件BizDefineCode 获取 事件对象 (t_Evt_EventDefine中FCode字段 对应 @Consumer 的 value)
     *
     * @param code
     * @return
     */
    @Override
    public EventDefineBo getByCode(String code) {
        EventDefine eventDefine = eventDefineService.getByCode(code);
        return BeanUtil.convert(eventDefine, EventDefineBo.class);
    }
}
