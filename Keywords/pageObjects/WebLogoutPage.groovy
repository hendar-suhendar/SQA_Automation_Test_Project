package pageObjects

import com.kms.katalon.core.testobject.ObjectRepository as OR
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

class LogoutPage {

	private String logoutButton = "Page_Logout/btn_logout"

	def clickLogout() {
		WebUI.click(OR.findTestObject(logoutButton))
	}

	def verifyLogoutSuccess() {
		WebUI.verifyElementPresent(OR.findTestObject("Page_Login/input_username"), 10)
	}
}
