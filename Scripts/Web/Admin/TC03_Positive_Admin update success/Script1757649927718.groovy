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

/*==== Inisialisasi Active Test Data ====*/
TestData data = IGV.currentTestData
int index = IGV.currentIndex

/*=====Inisialisasi steps pre condition====*/
if(IGV.Single_Login==true) {
	dashboard.clickMenuPetugas()
}else {
	login.loginAdminSuccessfully(IGV.Admin_Username,IGV.Admin_Password)
	dashboard.clickMenuPetugas()
}
///*===== Step to Execute =====*/
admin.clickUpdateDataAdmin(data.getValue('Email', index))

admin.updateUsername(data.getValue('Username', index))

admin.updatePassword(data.getValue('Password', index))

admin.updateEmail(data.getValue('Email Update', index))

admin.uploadFoto(data.getValue('Foto', index))

ReportUtils.captureScreenshot()

admin.clickSimpan()


/*====== Verify Result ====== */
admin.verifyListDataAdmin(data.getValue('Username', index), data.getValue('Email Update', index))
ReportUtils.captureScreenshot()



