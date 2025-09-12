
import pageObjects.WebLoginPage
import pageObjects.WebDashboardPage
import generic.ReportUtils
import internal.GlobalVariable
import com.kms.katalon.core.testdata.TestData


/*==== Inisialisasi Page Object ========*/
WebLoginPage login = new WebLoginPage()
WebDashboardPage dashboard = new WebDashboardPage()
ReportUtils report = new ReportUtils()

/*==== Inisialisasi Active Test Data ====*/
TestData data = GlobalVariable.currentTestData
int index = GlobalVariable.currentIndex

/*===== Step to Execute =====*/
login.inputUsername(data.getValue('Username', index))

login.inputPassword(data.getValue('Password', index))
ReportUtils.captureScreenshot()
login.clickLogin()

/*====== Verify Result ====== */
dashboard.verifyWelcomeMessage(data.getValue('Expected Outcome 1', index))
dashboard.verifyLoginInitial(data.getValue('Expected Outcome 2', index))
ReportUtils.captureScreenshot()

dashboard.actionLogoutWebsite()


