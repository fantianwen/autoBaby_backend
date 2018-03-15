package com.dooioo.models;

/**
 * Created by RadAsm on 17/4/20.
 */
public class Step {

    private String elementId;

    private String data;

    private String action;

    private String shouldSleep;

    private String sleepMills;

    private String wish;

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getShouldSleep() {
        return shouldSleep;
    }

    public void setShouldSleep(String shouldSleep) {
        this.shouldSleep = shouldSleep;
    }

    public String getSleepMills() {
        return sleepMills;
    }

    public void setSleepMills(String sleepMills) {
        this.sleepMills = sleepMills;
    }

    public String getWish() {
        return wish;
    }

    public void setWish(String wish) {
        this.wish = wish;
    }
}
