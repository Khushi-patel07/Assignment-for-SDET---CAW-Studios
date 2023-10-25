package com.AssignmentforSDET_CAWStudios.pages;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Page_Scenario {

	WebDriver driver;

	public Page_Scenario(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(how = How.XPATH, using = "/html/body/div/div[3]/details")
	public WebElement TableData;

	@FindBy(how = How.XPATH, using = "//*[@id=\"jsondata\"]")
	public WebElement Inputdata;

	@FindBy(how = How.XPATH, using = "//*[@id=\"refreshtable\"]")
	public WebElement Refreshbutton;

	public void a() throws InterruptedException {
		TableData.click();
		Thread.sleep(1000);

	}

	public void b() {
		Inputdata.clear();
		 String jsonData = readJsonFile("Json\\Data.json");
		Inputdata.sendKeys(jsonData);
	}

	public void c() throws InterruptedException {
		Refreshbutton.click();
		Thread.sleep(1000);
		
		 // Read JSON data from the file
        String jsonData = readJsonFile("Json\\Data.json");
		JSONArray jsonArray = new JSONArray(jsonData);

		// Find the UI table
		WebElement table = driver.findElement(By.id("dynamictable"));

		// Retrieve the table data
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		for (int i = 1; i < rows.size(); i++) {
			WebElement row = rows.get(i);
			List<WebElement> cells = row.findElements(By.tagName("td"));

			// Get the JSON data for the corresponding row
			JSONObject jsonRow = jsonArray.getJSONObject(i - 1);

			// Compare JSON data with UI table data
			String nameFromJSON = jsonRow.getString("name");
			String ageFromJSON = String.valueOf(jsonRow.getInt("age"));
			String genderFromJSON = jsonRow.getString("gender");

			String nameFromTable = cells.get(0).getText();
			String ageFromTable = cells.get(1).getText();
			String genderFromTable = cells.get(2).getText();

			if (nameFromJSON.equals(nameFromTable) && ageFromJSON.equals(ageFromTable)
					&& genderFromJSON.equals(genderFromTable)) {
				System.out.println("Data matches for row " + i);
			} else {
				System.out.println("Data does not match for row " + i);
			}

		}
	}
	
	public static String readJsonFile(String filePath) {
        try {
            FileReader reader = new FileReader(filePath);
            int character;
            StringBuilder content = new StringBuilder();

            while ((character = reader.read()) != -1) {
                content.append((char) character);
            }

            reader.close();
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
}
}
