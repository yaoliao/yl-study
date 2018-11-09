package com.yl.study.okhttp;

import java.io.Serializable;

/**
 * @author Administrator
 * @date 2018/9/12 0012
 * @time 下午 18:34
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public class Che300PreRespon implements Serializable{

    private static final long serialVersionUID = 6218470264796015508L;

    private Integer status;

    private EvalPricesResult eval_prices;

    private String error_msg;

    public Che300PreRespon() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public EvalPricesResult getEval_prices() {
        return eval_prices;
    }

    public void setEval_prices(EvalPricesResult eval_prices) {
        this.eval_prices = eval_prices;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

}
