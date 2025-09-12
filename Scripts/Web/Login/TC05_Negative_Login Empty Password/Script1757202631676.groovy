import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import pageObjects.WebLoginPage
import pageObjects.WebDashboardPage
import generic.ReportUtils
import internal.GlobalVariable
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as IGV

/*==== Inisialisasi Page Object ========*/
WebLoginPage login = new WebLoginPage()
WebDashboardPage dashboard = new WebDashboardPage()

/*=====Inisialisasi steps pre condition====*/
if(IGV.Single_Open_Browser==true){WebUI.refresh()}

/*==== Inisialisasi Active Test Data ====*/
/*==== Inisialisasi Active Test Data ====*/
TestData data = GlobalVariable.currentTestData
int index = GlobalVariable.currentIndex

/*===== Step to Execute =====*/

login.inputUsername(data.getValue('Username', index))

login.clearInputPassword()

login.clickLogin()

/*====== Verify Result ====== */
login.verifyPopupAlertMessage(data.getValue('Expected Outcome 1', index))

login.clickOkButtonAlertPopup()
//alert tidak dapat di screenshot->ambil screenshot setelah alert 
ReportUtils.captureScreenshot()

