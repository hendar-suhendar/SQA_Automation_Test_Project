package pageObjects

import com.kms.katalon.core.testobject.ObjectRepository as OR
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

class DashboardPage {

    private String welcomeMessage = "Page_Dashboard/lbl_welcome"

    def verifyLoginSuccess() {
        WebUI.verifyElementPresent(OR.findTestObject(welcomeMessage), 10)
    }
}
