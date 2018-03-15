package com.dooioo.models;

import java.util.List;

/**
 * Created by RadAsm on 17/4/20.
 */
public class TestCase {

    private String testCaseCategory;

    private String testCaseName;

    private List<Step> caseSteps;

    public String getTestCaseCategory() {
        return testCaseCategory;
    }

    public void setTestCaseCategory(String testCaseCategory) {
        this.testCaseCategory = testCaseCategory;
    }

    public String getTestCaseName() {
        return testCaseName;
    }

    public void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }

    public List<Step> getCaseSteps() {
        return caseSteps;
    }

    public void setCaseSteps(List<Step> caseSteps) {
        this.caseSteps = caseSteps;
    }
}
