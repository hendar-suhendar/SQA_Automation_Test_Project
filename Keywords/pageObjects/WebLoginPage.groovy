package pageObjects
import com.kms.katalon.core.testobject.ObjectRepository as OR
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class WebLoginPage {


		private String usernameField = "Page_Login/input_username"
		private String passwordField = "Page_Login/input_password"
		private String loginButton   = "Page_Login/btn_login"
	
		def enterUsername(String username) {
			WebUI.setText(OR.findTestObject(usernameField), username)
		}
	
		def enterPassword(String password) {
			WebUI.setText(OR.findTestObject(passwordField), password)
		}
	
		def clickLogin() {
			WebUI.click(OR.findTestObject(loginButton))
		}
}

