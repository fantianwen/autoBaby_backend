package com.dooioo.utils;

/**
 * Created by RadAsm on 17/4/20.
 */
public class APPIUM {

    enum ACTION {

        CLICK("click", "click()"),
        LONG_CLICK("long_click", "longClick()");

        String value;

        String operation;

        ACTION(String value, String operation) {
            this.value = value;
            this.operation = operation;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getOperation() {
            return operation;
        }

        public void setOperation(String operation) {
            this.operation = operation;
        }
    }

    public static String SEND_KEYS = "send_keys";

    public static String SLEEP = "sleep";
}
