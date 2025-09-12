package pageObjects

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import helpers.BaseHelper
import locators.WebLocators
import generic.ReportUtils
import internal.GlobalVariable


class WebLoginPage extends BaseHelper {

	public TestObject txtUsername
	public TestObject txtPassword
	public TestObject btnLogin
	public TestObject lblErrorMessage


	public WebLoginPage() {
		txtUsername 	= getTestObject("txtUsername", WebLocators.INPUT_USERNAME, "xpath")
		txtPassword 	= getTestObject("txtPassword", WebLocators.INPUT_PASSWORD, "xpath")
		btnLogin    	= getTestObject("btnLogin", WebLocators.BTN_LOGIN, "xpath")
		lblErrorMessage = getTestObject("lblErrorMessage", WebLocators.LBL_ERROR_MESSAGE, "xpath")
	}


	public void inputUsername(String username) {
		setText(txtUsername, username)
	}

	public void inputPassword(String password) {
		setEncryptedText(txtPassword, password)
	}
	public void clearInputPassword(String password) {
		clearInput(txtPassword, password)
	}

	public void clickLogin() {
		click(btnLogin)
	}

	public void clickOkButtonAlertPopup() {
		okButtonAlertPopup()
	}


	// ==============================
	// Verification (CONTINUE on failure)
	// ==============================
	public void verifyErrorMessage(String expectedMessage) {
		verifyElementAndTextVisible(lblErrorMessage, expectedMessage)
	}

	public void verifyPopupAlertMessage(String expectedAlertMessage) {
		verifyTextAlertPopup(expectedAlertMessage)
	}

	public void loginAdminSuccessfully(String username,String password) {
		setText(txtUsername, username)
		setEncryptedText(txtPassword, password)
		click(btnLogin)
	}
}
