package com.dooioo.utils;

import com.dooioo.models.TestCase;

import java.io.File;
import java.util.Locale;

/**
 * Created by RadAsm on 17/4/20.
 */
public class Utils {

    private static final String path_relative = "./";
    private static final String TEST_CASE_FOLDER_NAME = "TEST_CASES";

    public static String parseTestCaseJSONPath(TestCase testCase) {
        File file = new File(TEST_CASE_FOLDER_NAME + File.separator + testCase.getTestCaseCategory());
        if (!file.exists()) {
            file.mkdirs();
        }

        return new File(file, testCase.getTestCaseName() + ".json").getAbsolutePath();
    }

    public static String getTestCaseJSONFilePath(String testCaseCategory, String testCaseName) {
        return TEST_CASE_FOLDER_NAME + File.separator + testCaseCategory + File.separator + testCaseName + ".json";
    }

    public static String parseTestCasePyFilePath(String testCaseCategory, String testCaseName) {
        return TEST_CASE_FOLDER_NAME + File.separator + testCaseCategory + File.separator + testCaseName + ".py";
    }

    public static boolean isNotNull(String s) {
        return s != null && s.length() > 0 && !s.toLowerCase().equals("null");
    }


}
