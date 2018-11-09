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
public class EvalPricesResult implements Serializable{

    private static final long serialVersionUID = -2573074487624925602L;

    private Double c2b_price;
    private Double b2b_price;

    public EvalPricesResult() {
    }

    public Double getC2b_price() {
        return c2b_price;
    }

    public void setC2b_price(Double c2b_price) {
        this.c2b_price = c2b_price;
    }

    public Double getB2b_price() {
        return b2b_price;
    }

    public void setB2b_price(Double b2b_price) {
        this.b2b_price = b2b_price;
    }
}
