import static com.kms.katalon.core.testdata.TestDataFactory.findTestData

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as IGV

import generic.ReportUtils
import pageObjects.WebLoginPage
import pageObjects.WebAdminPage
import pageObjects.WebDashboardPage

import com.kms.katalon.core.testdata.TestData

/*==== Inisialisasi Page Object ========*/
WebLoginPage login = new WebLoginPage()
WebAdminPage admin = new WebAdminPage()
ReportUtils report = new ReportUtils()
WebDashboardPage dashboard = new WebDashboardPage()
/*=====Inisialisasi steps pre condition====*/
login.loginAdminSuccessfully(IGV.Admin_Username,IGV.Admin_Password)
dashboard.clickMenuPetugas()
admin.clickTambahAdmin()

/*==== Inisialisasi Active Test Data ====*/
TestData data = IGV.currentTestData
int index = IGV.currentIndex


/*===== Step to Execute =====*/

admin.inputUsername(data.getValue('Username', index))

admin.inputPassword(data.getValue('Password', index))

admin.inputEmail(data.getValue('Email', index))

admin.uploadFoto(data.getValue('Foto', index))
ReportUtils.captureScreenshot()

admin.clickSimpan()


/*====== Verify Result ====== */
admin.verifyListDataAdmin(data.getValue('Username', index), data.getValue('Email', index))
ReportUtils.captureScreenshot()



