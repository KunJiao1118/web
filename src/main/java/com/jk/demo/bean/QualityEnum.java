package com.jk.demo.bean;

/**
 * @author billxkk
 * @date 2020/2/20 15:48
 * 品相种类
 */
public enum QualityEnum {
    NEW("全新"),
    NINE("九品"),
    EIGHT("八品"),
    SEVEN("七品"),
    SIX("六品"),
    FIVE("五品"),
    FOUR("四品");

    private final String qualityStr;

    private QualityEnum(String qualityStr) {
        this.qualityStr = qualityStr;
    }

    public String getQualityStr() {
        return qualityStr;
    }
}
