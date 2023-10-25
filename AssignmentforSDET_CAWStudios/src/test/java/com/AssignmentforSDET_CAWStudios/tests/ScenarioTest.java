package com.AssignmentforSDET_CAWStudios.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.AssignmentforSDET_CAWStudios.pages.Page_Scenario;


public class ScenarioTest  extends Base_Test {
	
	private static Logger logger = LogManager.getLogger();

	@Test(groups = { "sanity", "invalid" })
	public void Help() throws InterruptedException {


		Page_Scenario user = new Page_Scenario(driver);
		user.a();
		user.b();
		user.c();
	}

	}


