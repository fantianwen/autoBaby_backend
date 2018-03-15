package com.dooioo.controllers;

import com.dooioo.models.Step;
import com.dooioo.models.TestCase;
import com.dooioo.utils.PyFileGenerator;
import com.dooioo.utils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by RadAsm on 17/4/20.
 */
@RestController
public class TestCaseFileController {

    private static final String PY_PREFIX = "    ";

    @RequestMapping(method = RequestMethod.POST, path = "/dooioo/testcase/generate")
    public void generateTestCase(@RequestBody TestCase testCase) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(Utils.parseTestCaseJSONPath(testCase)), testCase);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(path = "/dooioo/testcase/execute", method = RequestMethod.GET)
    public void executeTestCase(
            @RequestParam("testCaseCategory") String testCaseCategory,
            @RequestParam("testCaseName") String testCaseName) {

        File file = new File(Utils.getTestCaseJSONFilePath(testCaseCategory, testCaseName));
        File testCasePyFile = new File(Utils.parseTestCasePyFilePath(testCaseCategory, testCaseName));

        TestCase testCase = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            testCase = mapper.readValue(file, TestCase.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (testCase != null) {

            StringBuilder pyFileBuilder = new StringBuilder("import os\n" +
                    "import unittest\n" +
                    "\n" +
                    "from appium import webdriver\n" +
                    "from time import sleep\n" +
                    "\n" +
                    "PATH = lambda p: os.path.abspath(\n" +
                    "    os.path.join(os.path.dirname(__file__), p)\n" +
                    ")\n" +
                    "\n" +
                    "\n" +
                    "class SimpleDooiooAndroidTest(unittest.TestCase):\n" +
                    "    def setUp(self):\n" +
                    "        desired_caps = {}\n" +
                    "        desired_caps['platformName'] = 'Android'\n" +
                    "        desired_caps['deviceName'] = 'Android'\n" +
                    "        desired_caps['app'] = PATH(\n" +
                    // 可配置
                    "            '../../apks/dyMobile_v9.1.0_3.apk'\n" +
                    "        )\n" +
                    // 可配置
                    "        desired_caps['appPackage'] = 'com.dooioo.addressbook'\n" +
                    // 可配置
                    "        desired_caps['appActivity'] = 'com.dooioo.core.activity.MainActivity'\n" +
                    "\n" +
                    "        self.driver = webdriver.Remote('http://localhost:4723/wd/hub', desired_caps)\n" +
                    "\n" +


                    PY_PREFIX + "def " + PyFileGenerator.generateTestMethodName(testCase) + "(self):\n");

            if (testCase.getCaseSteps() != null && testCase.getCaseSteps().size() > 0) {

                List<Step> caseSteps = testCase.getCaseSteps();
                for (Step step : caseSteps) {
                    String statementStepString = PyFileGenerator.generateStatement(step);
                    String setDataStepString = PyFileGenerator.generateSetDataStep(step);
                    String actionStepString = PyFileGenerator.generateAction(step);
                    String sleepStepString = PyFileGenerator.generateSleepStep(step);

                    pyFileBuilder
                            .append(statementStepString)
                            .append(setDataStepString)
                            .append(actionStepString)
                            .append(sleepStepString);

                }

            }

            pyFileBuilder
                    .append(PyFileGenerator.EXCHANGE_LINE)
                    .append("if __name__ == '__main__':\n" +
                            "    suite = unittest.TestLoader().loadTestsFromTestCase(SimpleDooiooAndroidTest)\n" +
                            "    unittest.TextTestRunner(verbosity=2).run(suite)");

            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(testCasePyFile);
                fileWriter.write(pyFileBuilder.toString());
                fileWriter.flush();
            } catch (IOException e) {
            } finally {
                if (fileWriter != null) {
                    try {
                        fileWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            Runtime rt = Runtime.getRuntime();
            try {
                System.out.print("开始执行测试:" + "python3 " + testCasePyFile.getAbsolutePath());
                Process pr = rt.exec("python3 " + testCasePyFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
