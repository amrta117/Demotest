package demotest;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Base.base;

public class adminRoles extends base {

	@Test(priority = 1)
	public void navigateToAdminRoles() {

		// Click on Admin dropdown
		if (!driver.getCurrentUrl().contains("/admin/")) {
			WebElement adminMenu = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space(text())='Admin']")));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", adminMenu);

			wait.until(ExpectedConditions.elementToBeClickable(adminMenu)).click();
		}

		System.out.println("Clicked on Admin menu");

		WebElement roles = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//a[@href='/admin/roles' and normalize-space(text())='Roles']")));
		roles.click();
		System.out.println("Navigated to Roles");
	}

	@Test(priority = 2)
	public void navigateToNewRole() {
		WebElement newRoleButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[contains(@class,'btn-primary') and normalize-space(text())='+ New Role']")));
		newRoleButton.click();
		System.out.println("New Role button clicked");

		WebElement addRoleName = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter role name']")));
		addRoleName.sendKeys("Tester" + System.currentTimeMillis() % 100000);
		System.out.println("Role name entered");

		// Dropdown selection
		WebElement parentRoleDropdown = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name='parentId']")));
		System.out.println("parent role located");

		parentRoleDropdown.click();
		System.out.println("parent role clicked");

		// ✅ Fix: Locate options, not select
		List<WebElement> dropdownOptions = wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//select[@name='parentId']/option")));

		Assert.assertTrue(dropdownOptions.size() > 1, "Dropdown does not have enough options to skip the first one!");

		Random rand = new Random();
		int randomIndex = 1 + rand.nextInt(dropdownOptions.size() - 1); // skip index 0
		WebElement randomOption = dropdownOptions.get(randomIndex);

		System.out.println("Randomly Selected: " + randomOption.getText());
		randomOption.click();

		// ✅ Ensure we did not select "None"
		Assert.assertNotEquals(randomOption.getText().trim(), "None", "First option (None) was incorrectly selected!");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Checking checkboxes
		wait.until(ExpectedConditions.elementToBeClickable(By.name("leadCreate"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("leadEdit"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("leadDelete"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("oppertunityEdit"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("oppertunityDelete"))).click();
		System.out.println("Checked lead and opportunity permissions");

		WebElement actionsDailyReport = wait.until(ExpectedConditions.elementToBeClickable(By.name("dailyReport")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", actionsDailyReport);
		actionsDailyReport.click();

		WebElement actionsViewAccounts = wait.until(ExpectedConditions.elementToBeClickable(By.name("accountsView")));
		actionsViewAccounts.click();

		WebElement saveChanges = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[contains(@class,'btn-primary') and normalize-space(text())='Save changes']")));

		saveChanges.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Role saved successfully");
	}

	@Test(priority = 3)
	public void selectRoleToUpdate() {
		List<WebElement> roles = driver.findElements(By.xpath("//div[@id='main-wrapper']//div[@class='col-6']/div"));
		System.out.println("12 found");

		Assert.assertTrue(roles.size() > 0, "No roles found on the page!");
		WebElement editRoleTester = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[@id='headingOne' and @role='button'][.//span[normalize-space(text())='Tester']]")));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", editRoleTester);
		System.out.println("tester to edit found");
		editRoleTester.click();
		System.out.println("tester to edit clicked");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// edit Checking checkboxes
		wait.until(ExpectedConditions.elementToBeClickable(By.name("oppertunityEdit"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("oppertunityDelete"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("ProjectCreate"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("feedCreate"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("resourceUtilizationView"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("HrView"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("applcationPortalView"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("businessAdministrationView"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("telecaller"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.name("admin")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				wait.until(ExpectedConditions.elementToBeClickable(By.name("admin"))));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("admin"))).click();
		WebElement updateChangesBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//button[contains(@class,'btn') and contains(@class,'btn-primary') and normalize-space(text())='Update changes']")));
		updateChangesBtn.click();
		System.out.println("changes updated");
		// ✅ Assert success message
		WebElement successMsg = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Role updates successfully.')]")));
		Assert.assertTrue(successMsg.isDisplayed(), "Success message not displayed after role update!");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test(priority = 4)
	public void selectRoleToDelete() {
		// Find all role elements
		List<WebElement> roles = driver.findElements(By.xpath("//div[@id='main-wrapper']//div[@class='col-6']/div"));
		System.out.println(roles.size() + " roles found");

		// Assert at least one role is present
		Assert.assertTrue(roles.size() > 0, "No roles found on the page!");

		// Get the LAST role
		WebElement lastRole = roles.get(roles.size() - 1);
		// Wait until it's clickable
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement clickableLastRole = wait.until(ExpectedConditions.elementToBeClickable(lastRole));

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Click last role
		System.out.println("Clicking last role: " + lastRole.getText());
		clickableLastRole.click();
		System.out.println("last role clicked");
		WebElement lastRoleDelete = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//div[@class='modal-footer']//button[@class='btn btn-danger' and normalize-space(text())='Delete']")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", lastRoleDelete);
		lastRoleDelete.click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement deleteYes = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[contains(@class,'swal-button--danger') and normalize-space(text())='Yes']")));
		deleteYes.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
