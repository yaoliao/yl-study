package com.yl.study.domain;

import com.yl.study.bo.EventDefineBo;

import java.util.List;

/**
 * Created by DELL on 2017/11/2.
 */
public interface EventDefineDomain {

    /**
     * 根据节点code 获取 事件列表
     * select e.* from t_Evt_EventDefine e join t_Biz_Node b on e.FNodeDefineCode = b.FUniqueKey where e.FNodeDefineCode = ? and e.FIsEnabled = 1
     *
     * @param nodeDefineCode
     * @return
     */
    List<EventDefineBo> findByNodeDefineCode(String nodeDefineCode);


    /**
     * 根据事件code 获取 事件对象
     * select e.* from t_Evt_EventDefine e where e.FCode = ? and e.FIsEnabled = 1
     * @param code
     * @return
     */
    EventDefineBo getByCode(String code);
}
