package com.yl.study.example9;

import java.io.Serializable;

/**
 * Created by DELL on 2018/1/18.
 */
public class Aa implements Serializable{

    private static final long serialVersionUID = 4276129668053988608L;
    private Integer resultID;
    private Integer type;
    private Integer brandID;
    private String brandName;
    private Integer modelID;
    private String modelName;
    private String result;

    public Aa() {
    }

    public Aa(Integer resultID, Integer type, Integer brandID, String brandName, Integer modelID, String modelName, String result) {
        this.resultID = resultID;
        this.type = type;
        this.brandID = brandID;
        this.brandName = brandName;
        this.modelID = modelID;
        this.modelName = modelName;
        this.result = result;
    }

    public Integer getResultID() {
        return resultID;
    }

    public void setResultID(Integer resultID) {
        this.resultID = resultID;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getBrandID() {
        return brandID;
    }

    public void setBrandID(Integer brandID) {
        this.brandID = brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Integer getModelID() {
        return modelID;
    }

    public void setModelID(Integer modelID) {
        this.modelID = modelID;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
