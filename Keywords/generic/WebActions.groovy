package generic

import internal.GlobalVariable
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil

/*set open browser*/
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.edge.EdgeOptions
import com.kms.katalon.core.testobject.TestObject

class WebActions {

	@Keyword
	def openBrowser(String browserName, String url) {
		String projectDir = RunConfiguration.getProjectDir()
		WebDriver driver = null

		switch(browserName.toLowerCase()) {
			case "chrome":
				System.setProperty("webdriver.chrome.driver", projectDir + "/Drivers/chromedriver.exe")
				ChromeOptions chromeOptions = new ChromeOptions()
				chromeOptions.addArguments("--start-maximized")
				chromeOptions.addArguments("--incognito")

				Map<String, Object> prefs = new HashMap<>()
				prefs.put("credentials_enable_service", false)
				prefs.put("profile.password_manager_enabled", false)
				chromeOptions.setExperimentalOption("prefs", prefs)

				driver = new ChromeDriver(chromeOptions)
				break

			case "firefox":
				System.setProperty("webdriver.gecko.driver", projectDir + "/Drivers/geckodriver.exe")
				FirefoxOptions firefoxOptions = new FirefoxOptions()
				firefoxOptions.addArguments("--width=1280")
				firefoxOptions.addArguments("--height=800")
				driver = new FirefoxDriver(firefoxOptions)
				break

			case "edge":
				System.setProperty("webdriver.edge.driver", projectDir + "/Drivers/msedgedriver.exe")
				EdgeOptions edgeOptions = new EdgeOptions()
				driver = new EdgeDriver(edgeOptions)
				break

			default:
				throw new Exception("Browser '${browserName}' tidak dikenali. Gunakan: chrome / firefox / edge.")
		}

		DriverFactory.changeWebDriver(driver)
		driver.get(url)
	}
}