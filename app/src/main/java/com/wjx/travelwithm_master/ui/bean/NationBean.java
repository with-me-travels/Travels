package com.wjx.travelwithm_master.ui.bean;

public class NationBean {
    String name;
    String time;
    String title;

    public NationBean() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public NationBean(String name, String time, String title) {
        this.name = name;
        this.time = time;
        this.title = title;
    }
}
