package demotest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import Base.base;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class LeadSourceTest extends base {

	@Test(priority = 1)
	public void navigateToLeadSettings() {
		System.out.println("2");

		// Click on Admin dropdown
		if (!driver.getCurrentUrl().contains("/admin/")) {
			WebElement adminMenu = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space(text())='Admin']")));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", adminMenu);

			wait.until(ExpectedConditions.elementToBeClickable(adminMenu)).click();
		}

		// Click on Lead Settings
		WebElement leadSettings = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space(text())='Lead settings']")));
		leadSettings.click();
		System.out.println("2 end");
		System.out.println(driver.getCurrentUrl());
		Assert.assertTrue(driver.getCurrentUrl().contains("lead/config"), "Not navigated to Lead Settings");
	}

	@Test(priority = 2)
	public void createLeadSource() {
		System.out.println("3");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement createBtn = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//button[contains(.,'Create')] | //a[contains(.,'Create')]")));
		createBtn.click();

		String leadName = "Test Source " + System.currentTimeMillis() % 100000;
		WebElement leadNameInput = driver.findElement(By.name("name"));
		leadNameInput.sendKeys(leadName);

		// Save lead
		WebElement saveBtn = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save and Close')]")));

		saveBtn.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Click on
		// Validate creation

		WebElement createdLead = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id='lead_source']//li[contains(@class,'list-group-item') and normalize-space(text())='"
						+ leadName + "']")));

		Assert.assertTrue(createdLead.isDisplayed(), "Lead Source not created");
	}

	@Test(priority = 3)
	public void editLeadSource() {
		System.out.println("4");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Click edit icon for the first lead source
		WebElement editIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"(//*[@id='lead_source']//span[contains(@class,'badge') and contains(@class,'badge-pill') and contains(@class,'pointer')])[1]")));
		editIcon.click();

		System.out.println("4 clicked");
		// Edit name
		WebElement leadNameInput = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//input[@name='EditIdName' and @type='text']")));
		leadNameInput.clear();
		String leadName = "updated name " + System.currentTimeMillis() % 100000;

		leadNameInput.sendKeys(leadName);

		// Save
		WebElement saveBtn = driver.findElement(By.xpath("//button[contains(text(),'Save')]"));
		saveBtn.click();
		System.out.println("4 end");

		WebElement createdLead = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//li[normalize-space(text())='" + leadName + "']")));

		Assert.assertTrue(createdLead.isDisplayed(), "Lead Source not updated");
	}

	@Test(priority = 4)
	public void deleteLeadSource() {
		System.out.println("5");

		// Click delete icon for first lead source
		WebElement deleteIcon = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("(//*[@id='lead_source']//span[contains(@class,'pointer')][2])[32]")));
		deleteIcon.click();

		WebElement deleconfirm = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//button[contains(@class,'swal-button') and contains(@class,'swal-button--confirm') and contains(@class,'swal-button--danger')]")));
		deleconfirm.click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement deleconfirmsuccess = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@class='swal-title' and normalize-space(text())='Deleted!']")));
		boolean isConfirmShown = deleconfirmsuccess.isDisplayed();
		System.out.println(isConfirmShown);
		// Validate deletion
		WebElement okButton = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//button[contains(@class, 'swal-button--confirm')]")));
		okButton.click();
		Assert.assertTrue(isConfirmShown, "Delete Failed");

	}
}
