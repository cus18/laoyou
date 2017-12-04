package com.yhl.laoyou.common.constant;

/**
 * Created by sunxiao on 2017/8/15.
 */
public enum StatusType {

    PREPARE("prepare"),//准备，还没开始
    ONGOING("ongoing"),//正在进行
    FINISH("finish");//结束，完成

    private final String value;

    StatusType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
