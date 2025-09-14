package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;

public class base {

	protected static WebDriver driver;
	protected static WebDriverWait wait;

	@BeforeSuite
	public void initializeDriver() {
		WebDriverManager.chromedriver().setup();
		try {
			ChromeOptions options = new ChromeOptions();
			options.setBinary("E:/Aa(SCALEUP AUTOMATION)/chrome-win64/chrome-win64/chrome.exe");

			HashMap<String, Object> prefs = new HashMap<>();
			prefs.put("profile.password_manager_leak_detection", false); // Disables "Change Password" prompt/leak
																			// alerts
			options.setExperimentalOption("prefs", prefs);

			String projectDir = System.getProperty("user.dir");

			// Create a dedicated "downloads" folder inside project
			String downloadDirPath = projectDir + File.separator + "Downloads";
			File downloadDir = new File(downloadDirPath);
			if (!downloadDir.exists()) {
				downloadDir.mkdir(); // create folder if it doesn't exist
			}

			prefs.put("download.default_directory", downloadDirPath);// <--- saves to project root
			prefs.put("download.prompt_for_download", false); // disable prompt
			prefs.put("download.directory_upgrade", true); // overwrite if exists
			options.setExperimentalOption("prefs", prefs);

			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterSuite
	public void tearDown() {
		System.out.println("Closing Browser...");
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
