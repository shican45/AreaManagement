package com.example.demo.util;

public enum ErrorMsg {
    EDIT_FAIL_ERROR("编辑失败"),
    ADD_FAIL_ERROR("添加失败"),
    DELETE_FAIL_ERROR("删除失败"),
    CITY_EXIT("城市已存在");

    private String msg;

    ErrorMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
