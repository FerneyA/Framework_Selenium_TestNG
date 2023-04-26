package utils;

import com.demo.tests.BaseTests;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class SuiteListener implements ITestListener, IAnnotationTransformer {

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String fileName = "target/reports/evidence/passed/" + result.getMethod().getMethodName();
        File f = ((TakesScreenshot) BaseTests.driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(f, new File(fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String fileName = "target/reports/evidence/failed/" + result.getMethod().getMethodName();
        File f = ((TakesScreenshot)BaseTests.driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(f, new File(fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
