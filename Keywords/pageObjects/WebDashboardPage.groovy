package pageObjects

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import helpers.BaseHelper
import locators.WebLocators
import generic.ReportUtils
import internal.GlobalVariable as GlobalVariable

class WebDashboardPage extends BaseHelper {


	public TestObject lblDashboardLoginSuccess
	public TestObject lblDashboardLoginInitial
	public TestObject ddlHeader
	public TestObject btnLogout
	public TestObject menuPetugas

	public WebDashboardPage() {
		lblDashboardLoginSuccess 	= getTestObject("lblDashboardLoginSuccess", WebLocators.MSG_LOGIN_SUCCESS, "xpath")
		lblDashboardLoginInitial	= getTestObject("lblDashboardLoginInitial", WebLocators.LBL_LOGIN_INITIAL, "xpath")
		ddlHeader					= getTestObject("ddlHeader", WebLocators.DDL_HEADER, "xpath")
		btnLogout					= getTestObject("btnLogout", WebLocators.BTN_LOGOUT, "xpath")
		menuPetugas					= getTestObject("btnLogout", WebLocators.MENU_PETUGAS, "xpath")
	}

	public void clickMenuPetugas() {
		click(menuPetugas)
	}

	public void actionLogoutWebsite() {
		click(ddlHeader)
		WebUI.delay(2)
		click(btnLogout)
	}

	// ==============================
	// Verification (CONTINUE on failure)
	// ==============================
	public void verifyWelcomeMessage(String expectedText) {
		verifyElementAndTextVisible(lblDashboardLoginSuccess,expectedText)
	}

	public void verifyLoginInitial(String expectedText) {
		verifyElementAndTextInitial(lblDashboardLoginInitial,expectedText)
	}
}
