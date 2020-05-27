package com.bs.demo.model;


public class BaseModeBean {
    private boolean isCheck=false;
    private boolean editable=false;
    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
}
