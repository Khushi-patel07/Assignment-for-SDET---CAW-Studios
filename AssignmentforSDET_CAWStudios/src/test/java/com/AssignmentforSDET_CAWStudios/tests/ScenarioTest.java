package com.AssignmentforSDET_CAWStudios.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.AssignmentforSDET_CAWStudios.pages.Page_Scenario;


public class ScenarioTest  extends Base_Test {
	
	private static Logger logger = LogManager.getLogger();

	@Test(groups = {"sanity"})
	public void Help() throws InterruptedException {


		Page_Scenario user = new Page_Scenario(driver);
		user.a();
		user.b();
		user.c();
		Assert.assertEquals(driver.getTitle(), "Dynamic HTML TABLE Tag");
		logger.info("It is Dynamic HTML TABLE Tag Website Page");
	}

	}


