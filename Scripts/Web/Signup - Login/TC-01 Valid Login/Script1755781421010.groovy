import pageObjects.LoginPage
import pageObjects.DashboardPage
import pageObjects.LogoutPage
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

LoginPage login = new LoginPage()
DashboardPage dashboard = new DashboardPage()
LogoutPage logout = new LogoutPage()

// Step 1: Open Browser
WebUI.openBrowser('')
WebUI.navigateToUrl("https://demo.katalon.com")

// Step 2: Login
login.enterUsername("admin")
login.enterPassword("admin123")
login.clickLogin()

// Step 3: Verify Dashboard
dashboard.verifyLoginSuccess()

// Step 4: Logout
logout.clickLogout()
logout.verifyLogoutSuccess()

// Step 5: Close Browser
WebUI.closeBrowser()