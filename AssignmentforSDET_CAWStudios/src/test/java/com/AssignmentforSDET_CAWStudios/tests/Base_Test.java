package com.AssignmentforSDET_CAWStudios.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.AssignmentforSDET_CAWStudios.utils.Screenshot;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Base_Test {

	// Logging is an important feature that helps developers to trace out the
	// errors.
	// It is use for tracking events that happen when some software runs.
	// Logs gets placed under Logs folder.
	private static Logger logger = LogManager.getLogger();

	static WebDriver driver;
	public static File file;
	public static FileInputStream fis;
	public static Properties properties;
	public static ExtentReports extent;
	public static ExtentTest extentTest;

	static {
		try {
			file = new File("./Resources/config.properties");
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		properties = new Properties();
		try {
			properties.load(fis);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@BeforeSuite(groups = { "sanity", "invalid" })
	public void setExtent() {
	// Extent report gets generated after the run under Reports/ExtentReport.html
	extent = new ExtentReports("./Reports/ExtentReport.html");
	}

	@AfterSuite(groups = { "sanity", "invalid" })
	public void endReport() {
		extent.flush();
		extent.close();
	}

	@BeforeMethod(groups = { "sanity", "invalid" })
	public static void intializeDriver() {
		System.setProperty(properties.getProperty("driverProperty"), properties.getProperty("driverPath"));
   		//ChromeOptions options = new ChromeOptions();
		//options.addArguments("headless");
		//driver = new ChromeDriver(options);
		driver = new ChromeDriver();
		// Headless Chrome is a way to run Chrome browser without the actual browser UI being spawned.
		// its running Chrome without showing chrome.In headless mode there is no need a visible UI shell
		logger.info("Chrome browser was opened successfully");
		String ImplicitWaitTime = properties.getProperty("ImplicitWaitTime");
		driver.manage().window().maximize();

	}

	@BeforeMethod(groups = { "sanity", "invalid" })
	public static void openUrl() {

		driver.get(properties.getProperty("url"));
		logger.info("website was opened");
	}

	@BeforeMethod(groups = { "sanity", "invalid" })
	public void startTest(Method name) {
		String testname = name.getName();
		extentTest = extent.startTest(testname);
	}

	@AfterMethod(groups = { "sanity", "invalid" })
	public void resultTest(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {

			String imagePath = Screenshot.captureScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(imagePath), "Test case failed");
			logger.info("Test case failed");
		} else {
			if (result.getStatus() == ITestResult.SUCCESS) {
				extentTest.log(LogStatus.PASS, "Test case passed successfully");
				logger.info("Test case passed ");

			}
		}
		extent.endTest(extentTest);
	}

	@AfterMethod(groups = { "sanity", "invalid" })
	public static void testclose() {
		driver.close();
		logger.info("website was closed");
	}

}
