package demotest;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import Base.base;
import utils.FileUtils;

public class AdminAccounts extends base {

	@Test(priority = 1)
	public void navigateToAdminAccounts() {
		if (!driver.getCurrentUrl().contains("/admin/")) {
			WebElement adminMenu = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space(text())='Admin']")));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", adminMenu);

			wait.until(ExpectedConditions.elementToBeClickable(adminMenu)).click();
		}
		System.out.println("Clicked on Admin");

		WebElement AdminAccounts = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//ul[@id='menu']//a[@href='/admin/accounts/accountmain' and contains(normalize-space(.),'Accounts')]")));
		System.out.println("Accounts located");
		AdminAccounts.click();
		System.out.println("Accounts clicked");
	}

	@Test(priority = 2)
	public void ExportAndRename() throws Exception {

		String projectDir = System.getProperty("user.dir");

		// Create a dedicated "downloads" folder inside project
		String downloadDirPath = projectDir + File.separator + "Downloads";
		FileUtils.clearFilesInDir(downloadDirPath);

		ClickExport();
		// use helper method from FileUtils

		File pdfFile = FileUtils.waitAndRenameDownloadedFile("transactions_report.pdf", "ExportBill_old.pdf");
		System.out.println("Downloaded File Path: " + pdfFile.getAbsolutePath());
	}

	@Test(priority = 3)
	public void AddIncome() {
		WebElement addIncome = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//button[contains(@class,'btn') and contains(@class,'btn-success') and normalize-space(text())='+Add Income']")));
		addIncome.click();
		System.out.println("Clicked on addincome");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Start Date and Time
		String todayDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));

		WebElement DateAndTime = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//input[@id='formDate' and @name='date' and contains(@class,'form-control')]")));
		DateAndTime.click();
		DateAndTime.clear();
		DateAndTime.sendKeys(todayDateTime);
		System.out.println("date and time entered");

		// Category dropdown random selection
		WebElement categoryDropdown = driver.findElement(By.id("formCategory"));
		Select select = new Select(categoryDropdown);
		List<WebElement> options = select.getOptions();
		Random random = new Random();
		int randomIndex = random.nextInt(options.size() - 1) + 1;
		select.selectByIndex(randomIndex);
		System.out.println("Selected: " + options.get(randomIndex).getText());

		// Amount
		WebElement amountField = driver.findElement(By.id("formAmount"));
		amountField.clear();
		int amountCounter = 5000;
		amountField.sendKeys(String.valueOf(amountCounter));
		System.out.println("Amount entered: " + amountCounter);

		// Receipt number
		int randomNum = random.nextInt(900000) + 100000;
		String receipt = "REC" + randomNum;
		WebElement ReceiptNumber = driver.findElement(By.id("formReceiptNumber"));
		ReceiptNumber.clear();
		ReceiptNumber.sendKeys(receipt);
		System.out.println("Receipt Number entered: " + receipt);

		// Description
		String[] messages = { "Payment received", "Order completed", "Test automation entry", "Customer invoice" };
		String randomMessage = messages[random.nextInt(messages.length)];
		WebElement Description = driver.findElement(By.id("formDescription"));
		Description.clear();
		Description.sendKeys(randomMessage);
		System.out.println("Description entered: " + randomMessage);

		// Discount
		int discountPercent = random.nextInt(16) + 5; // 5% to 20%
		int discountValue = (amountCounter * discountPercent) / 100;
		WebElement discountField = driver.findElement(By.id("formDiscount"));
		discountField.clear();
		discountField.sendKeys(String.valueOf(discountValue));
		System.out.println("Discount entered: " + discountValue + " (" + discountPercent + "%)");

		WebElement saveIncomeButton = driver
				.findElement(By.xpath("//button[contains(@class,'btn btn-success') and text()='Save Income']"));
		saveIncomeButton.click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 4)
	public void AddExpense() {
		WebElement addExpense = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[contains(@class,'btn btn-danger') and text()='+Add Expense']")));
		addExpense.click();
		System.out.println("Clicked on expense");

		String todayDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));

		WebElement DateAndTime = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//input[@id='formDate' and @name='date' and contains(@class,'form-control')]")));
		DateAndTime.click();
		DateAndTime.clear();
		DateAndTime.sendKeys(todayDateTime);
		System.out.println("date and time entered");

		// Category dropdown random selection
		WebElement categoryDropdown = driver.findElement(By.id("formCategory"));
		Select select = new Select(categoryDropdown);
		List<WebElement> options = select.getOptions();
		Random random = new Random();
		int randomIndex = random.nextInt(options.size() - 1) + 1;
		select.selectByIndex(randomIndex);
		System.out.println("Selected: " + options.get(randomIndex).getText());

		// Amount
		WebElement amountField = driver.findElement(By.id("formAmount"));
		amountField.clear();
		int amountCounter = 5000;
		amountField.sendKeys(String.valueOf(amountCounter));
		System.out.println("Amount entered: " + amountCounter);

		// Receipt number
		int randomNum = random.nextInt(900000) + 100000;
		String receipt = "REC" + randomNum;
		WebElement ReceiptNumber = driver.findElement(By.id("formReceiptNumber"));
		ReceiptNumber.clear();
		ReceiptNumber.sendKeys(receipt);
		System.out.println("Receipt Number entered: " + receipt);

		// Description
		String[] messages = { "Payment received", "Order completed", "Test automation entry", "Customer invoice" };
		String randomMessage = messages[random.nextInt(messages.length)];
		WebElement Description = driver.findElement(By.id("formDescription"));
		Description.clear();
		Description.sendKeys(randomMessage);
		System.out.println("Description entered: " + randomMessage);

		WebElement saveExpenseButton = driver
				.findElement(By.xpath("//button[contains(@class,'btn btn-danger') and text()='Save Expense']"));
		saveExpenseButton.click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Test(priority = 5)
	public void DownloadAndCompareUpdatedFile() throws IOException {
		ClickExport();

		File pdfFile;
		try {
			pdfFile = FileUtils.waitAndRenameDownloadedFile("transactions_report.pdf",
					"transactions_report_updated.pdf");
			System.out.println("Downloaded File Path: " + pdfFile.getAbsolutePath());

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		// ✅ Call your PDF comparison here
		boolean result = PDFUtils.comparePDFs("ExportBill_old.pdf", "transactions_report_updated.pdf");
		System.out.println("Are PDFs equal? " + result);

		String projectDir = System.getProperty("user.dir");

		// Create a dedicated "downloads" folder inside project
		String downloadDirPath = projectDir + File.separator + "Downloads";
		FileUtils.clearFilesInDir(downloadDirPath);
		Assert.assertTrue(result,"Both the PDF are same Update Failed");

	}

	public static void ClickExport() {

		WebElement exportBill = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//button[contains(@class,'btn') and contains(@class,'btn-primary') and normalize-space(text())='Export Bill']")));
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("(//table[@id='transactions-table']//td[1])[1]")));
		exportBill.click();
		System.out.println("Clicked on Export Bill");

	}
}
