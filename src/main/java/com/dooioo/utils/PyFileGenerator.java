package com.dooioo.utils;

import com.dooioo.models.Step;
import com.dooioo.models.TestCase;

/**
 * Created by RadAsm on 17/4/20.
 */
public class PyFileGenerator {

    private static String EQUAL = " = ";
    private static String DOT = ".";
    public static String EXCHANGE_LINE = "\n";
    private static String TRUE = "true";
    private static String FALSE = "false";
    private static String LEFT_ = "(";
    private static String RIGHT_ = ")";
    private static String PY_CLASS_METHOD_PREFIX = "    ";
    private static String PY_CLASS_METHOD_CONTENT_PREFIX = PY_CLASS_METHOD_PREFIX + PY_CLASS_METHOD_PREFIX;

    public static String generateAction(Step step) {

        String elementId = step.getElementId();
        StringBuilder actionBuilder = new StringBuilder();
        String action = step.getAction();
//
//        actionBuilder.append(PY_CLASS_METHOD_CONTENT_PREFIX);
//        actionBuilder
//                // 确保每一个elementId都不一样?
//                .append(elementId)
//                .append(EQUAL)
//                .append("self.driver.find_element_by_id('" + elementId + "')\n");
        actionBuilder.append(PY_CLASS_METHOD_CONTENT_PREFIX);

        if (Utils.isNotNull(action)) {
            if (action.equals(APPIUM.ACTION.CLICK.getValue())) {
                actionBuilder
                        .append(elementId)
                        .append(DOT)
                        .append(APPIUM.ACTION.CLICK.getOperation());
            } else if (action.equals(APPIUM.ACTION.LONG_CLICK)) {
                actionBuilder
                        .append(elementId)
                        .append(DOT)
                        .append(APPIUM.ACTION.LONG_CLICK.getOperation());
            }

            actionBuilder.append(EXCHANGE_LINE);
        }

        return actionBuilder.toString();

    }


    public static String generateSetDataStep(Step step) {

        StringBuilder setDataStepBuilder = new StringBuilder();

        setDataStepBuilder.append(PY_CLASS_METHOD_CONTENT_PREFIX);
        String elementId = step.getElementId();
        String data = step.getData();
        if (Utils.isNotNull(data)) {
            setDataStepBuilder
                    .append(elementId)
                    .append(DOT)
                    .append(APPIUM.SEND_KEYS)
                    .append(LEFT_)
                    .append("'")
                    .append(data)
                    .append("'")
                    .append(RIGHT_);
        }

        setDataStepBuilder.append(EXCHANGE_LINE);

        return setDataStepBuilder.toString();

    }

    public static String generateSleepStep(Step step) {
        StringBuilder sleepActionBuilder = new StringBuilder();
        sleepActionBuilder.append(PY_CLASS_METHOD_CONTENT_PREFIX);
        String shouldSleep = step.getShouldSleep();
        if (Utils.isNotNull(shouldSleep) && TRUE.equals(shouldSleep.toLowerCase())) {
            String sleepMills = step.getSleepMills();

            // python占位符号
            sleepActionBuilder
                    .append(APPIUM.SLEEP)
                    .append(LEFT_)
                    .append(sleepMills)
                    .append(RIGHT_);
        }

        sleepActionBuilder.append(EXCHANGE_LINE);
        return sleepActionBuilder.toString();

    }

    public static String generateStatement(Step step) {
        String elementId = step.getElementId();
        StringBuilder actionBuilder = new StringBuilder();

        actionBuilder.append(PY_CLASS_METHOD_CONTENT_PREFIX);
        actionBuilder
                // 确保每一个elementId都不一样?
                .append(elementId)
                .append(EQUAL)
                .append("self.driver.find_element_by_id('" + elementId + "')\n");
        return actionBuilder.toString();
    }

    public static String generateTestMethodName(TestCase testCase) {
        return "test_" + testCase.getTestCaseName();
    }
}
