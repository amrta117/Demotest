package demotest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import Base.base;

public class milestone extends base {

	@Test(priority = 1)
	public void navigateToProject() {

		System.out.println("p1");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement project = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/project/?view=list']")));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", project);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		wait.until(ExpectedConditions.elementToBeClickable(project)).click();

		System.out.println("Found: " + project.getText());
		try {
			project.click();
			System.out.println("Clicked on Project link");
		} catch (Exception e) {
			System.out.println("Click failed: " + e.getMessage());
		}

		// Click on ProjectName
		WebElement projectName = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//table//tr[1]//span[contains(@class,'w-space-no') and contains(@class,'pt-2')]")));
		System.out.println("Found: " + projectName.getText());

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		projectName.click();
		System.out.println("Clicked on ProjectName");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test(priority = 2)
	public void navigateToMilestone() {

		WebElement milestonePage = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='#milestone']")));
		System.out.println("Found: " + milestonePage.getText());
		milestonePage.click();
		System.out.println("Clicked on Milestone");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement createMilestone = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[@id='milestone']//button[normalize-space(text())='+ Add Milestone']")));
		System.out.println("CREATE Milestone LOCATED");
		createMilestone.click();
		System.out.println("CREATE Milestone CLICKED");

		driver.findElement(By.name("name")).sendKeys("amrta");
		System.out.println("name sent");

		String today = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		// Start Date
		WebElement startDate = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='startDate']")));
		startDate.clear();
		startDate.sendKeys(today);
		System.out.println("start date entered");

		// End Date
		WebElement endDate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='endDate']")));
		endDate.clear();
		endDate.sendKeys(today);
		System.out.println("end date entered");

		WebElement tagsInput = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//input[starts-with(@id,'react-select') and contains(@id,'-input')]")));
		tagsInput.click();
		// tagsInput.sendKeys("Name"); // typing
		// Wait for all options
		List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.xpath("//div[starts-with(@id,'react-select') and contains(@id,'-option-')]")));

		// Step 3: Assert options exist
		Assert.assertTrue(options.size() > 0, "No options found in dropdown!");

		// Step 4: Pick random option
		Random rand = new Random();
		WebElement randomOption = options.get(rand.nextInt(options.size()));
		String optionText = randomOption.getText();
		randomOption.click();

		System.out.println("Selected option: " + optionText);

		// Locate Owner input field
		WebElement ownerInput = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//label[normalize-space()='Owner']/following::input[1]")));
		ownerInput.click();

		// Wait for Owner options to load
		List<WebElement> ownerOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.xpath("//div[starts-with(@id,'react-select') and contains(@id,'-option-')]")));

		// Assert options exist
		Assert.assertTrue(ownerOptions.size() > 0, "No Owner options found!");

		// Pick a random Owner option
		Random rand1 = new Random();
		WebElement randomOwner = ownerOptions.get(rand1.nextInt(ownerOptions.size()));
		String ownerText = randomOwner.getText();
		randomOwner.click();

		System.out.println("Selected Owner: " + ownerText);

		// Save and close milestone
		WebElement saveAndClose = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[contains(@class,'btn-primary') and normalize-space()='Save and Close']")));
		saveAndClose.click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("save and close done");
	}

	@Test(priority = 3)
	public void navigateToeditMi() {

		// Locate the edit icon using relative XPath
		WebElement editMi = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[@id='milestone']//i[contains(@class,'fa-pencil-alt')]")));

		// Scroll into view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", editMi);

		// Wait for it to be clickable
		wait.until(ExpectedConditions.elementToBeClickable(editMi));
		System.out.println("editmi located");
		// Click the icon
		editMi.click();
		System.out.println("Edit icon clicked successfully.");

		WebElement MiNameInput = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form//input[@name='name']")));
		System.out.println("update input field located ");

		// Get existing text
		String existingText = MiNameInput.getAttribute("value");
		String updateMi = "intern";
		String updatedName;

		// Check if "intern" is already there
		if (existingText != null && existingText.contains(updateMi)) {
			// Clear if already has intern
			MiNameInput.clear();
			updatedName = ""; // or you can reset to a base value if needed
			System.out.println("'intern' already present → field cleared");
		} else {
			// Otherwise, add intern
			updatedName = existingText + " " + updateMi;
			MiNameInput.clear();
			MiNameInput.sendKeys(updatedName);
			System.out.println("Updated name sent: " + updatedName);
		}

		// Save and close milestone
		WebElement saveAndClose = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[contains(@class,'btn-primary') and normalize-space()='Save and Close']")));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		saveAndClose.click();
		System.out.println("Updated name save and close");
		System.out.println("Updated name sent: " + updatedName);
		WebElement deleteMi = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id=\"milestone\"]//a[contains(@class,'btn-danger')]")));
		System.out.println("DELETE ICON LOCATED");
		// Scroll into view (important if element is not visible)
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", deleteMi);

		// Click using JavaScript (avoids click interception issues)
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteMi);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("DELETE ICON CLICKED");
		WebElement deleteYes = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//button[contains(@class,'swal-button--confirm') and contains(@class,'swal-button--danger') and contains(text(),'Yes')]")));
		deleteYes.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("deleted");

	}
}
