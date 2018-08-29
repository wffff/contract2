package com.sncj.contract.entity;

/**
 * Created by Danny on 2018-08-29.
 */
public enum AttachmentTypeEnum {
    MONEY(0, "回款情况"),
    EXECUTE(1, "执行情况"),
    ;

    public Integer code;
    public String des;

    AttachmentTypeEnum(Integer code, String des) {
        this.code = code;
        this.des = des;
    }

    public static AttachmentTypeEnum get(Integer code) {
        for (AttachmentTypeEnum c : AttachmentTypeEnum.values()) {
            if (c.code.toString().equals(code.toString())) {
                return c;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "code:" + code + ", des:" + des;
    }
}
