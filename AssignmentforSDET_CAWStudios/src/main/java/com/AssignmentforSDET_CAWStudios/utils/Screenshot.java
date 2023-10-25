package com.AssignmentforSDET_CAWStudios.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {

	//defines function to take screenshots
	public static String captureScreenshot(WebDriver driver, String testName) throws IOException {
    //Screenshots gets placed under failedscreenshot folder
		String imagepath = System.getProperty("user.dir") + "./FailedScreenshot/" + testName + ".jpg";
		File srcfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcfile, new File(imagepath));
		return imagepath;

	}

}
