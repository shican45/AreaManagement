package com.example.demo.entity;

public class EditAreaVo {
    private Area newArea;
    private Area oldArea;

    public Area getNewArea() {
        return newArea;
    }

    public void setNewArea(Area newArea) {
        this.newArea = newArea;
    }

    public Area getOldArea() {
        return oldArea;
    }

    public void setOldArea(Area oldArea) {
        this.oldArea = oldArea;
    }

    @Override
    public String toString() {
        return "EditAreaVo{" +
                "newArea=" + newArea +
                ", oldArea=" + oldArea +
                '}';
    }
}
