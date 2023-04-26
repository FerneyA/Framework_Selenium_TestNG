package utils;

import com.google.common.io.Files;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Common {
    // Action to take a screenshot and save it temporarily
    public static String getScreenshot(WebDriver driver) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File file = ts.getScreenshotAs(OutputType.FILE);
        String path = "target/reports/evidence/failed/CP001.png";
        File destination = new File(path);
        try {
            FileUtils.copyFile(file, destination);
        }catch(IOException e) {
           e.getMessage();
        }
        return path;
    }
}
