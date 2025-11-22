package pageObjects

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import helpers.BaseHelper
import locators.WebLocators
import generic.ReportUtils
import internal.GlobalVariable


class WebAdminPage extends BaseHelper {

	public TestObject txtAdminUsername;
	public TestObject txtAdminPassword;
	public TestObject txtAdminEmail;
	public TestObject txtAdminFoto;
	public TestObject btnAdminSimpan;
	public TestObject btnAdminKeluar;
	public TestObject tblDataAdmin;
	public TestObject btnTambahAdmin;
	public TestObject objEmailColumn;
	public TestObject btnUpdateAdmin;
	public TestObject btnDeleteAdmin;


	public WebAdminPage() {

		txtAdminUsername  = getTestObject("txtAdminUsername", WebLocators.INPUT_ADMIN_USERNAME, "xpath");
		txtAdminPassword  = getTestObject("txtAdminPassword", WebLocators.INPUT_ADMIN_PASSWORD, "xpath");
		txtAdminEmail     = getTestObject("txtAdminEmail", WebLocators.INPUT_ADMIN_EMAIL, "xpath");
		txtAdminFoto      = getTestObject("txtAdminFoto", WebLocators.INPUT_ADMIN_FOTO, "xpath");

		btnAdminSimpan    = getTestObject("btnAdminSimpan", WebLocators.BTN_ADMIN_SIMPAN, "xpath");
		btnAdminKeluar    = getTestObject("btnAdminKeluar", WebLocators.BTN_ADMIN_KELUAR, "xpath");
		btnTambahAdmin    = getTestObject("btnTambahAdmin", WebLocators.BTN_TAMBAH_ADMIN, "xpath");
		tblDataAdmin    = getTestObject("tblDataAdmin", WebLocators.TBL_DATA_ADMIN, "xpath");
		objEmailColumn	=	getTestObject("objEmailColumn",WebLocators.OBJ_EMAIL_COLUMN, "xpath");
		btnUpdateAdmin    = getTestObject("btnUpdateAdmin", WebLocators.TBL_UPDATE_ADMIN, "xpath");
		btnDeleteAdmin    = getTestObject("btnDeleteAdmin", WebLocators.TBL_DELETE_ADMIN, "xpath");
	}

	public void clickTambahAdmin() {
		click(btnTambahAdmin);
	}

	public void inputUsername(String username) {
		setText(txtAdminUsername, username);
	}

	public void updateUsername(String username) {
		updateText(txtAdminUsername, username);
	}

	public void inputPassword(String password) {
		setText(txtAdminPassword, password);
	}

	public void updatePassword(String password) {
		setText(txtAdminPassword, password);
	}

	public void inputEmail(String email) {
		setText(txtAdminEmail, email);
	}
	public void updateEmail(String email) {
		updateText(txtAdminEmail, email);
	}

	public void uploadFoto(String imageFile) {
		uploadFileFromProject(txtAdminFoto, imageFile);
	}

	public void clickUpdateDataAdmin(String uniqueValue) {
		chooseToClickByUniqueValue(tblDataAdmin,uniqueValue,btnUpdateAdmin)
	}
	public void clickDeleteDataAdmin(String uniqueValue) {
		chooseToClickByUniqueValue(tblDataAdmin,uniqueValue,btnDeleteAdmin)
	}

	public void clickSimpan() {
		click(btnAdminSimpan);
	}

	public void clickKeluar() {
		click(btnAdminKeluar);
	}

	public void clickOKDeletePopup() {
		handleConfirmationAlert("Are You Sure ?", "OK")
	}

	// ==============================
	// Verification (CONTINUE on failure)
	// ==============================
	public void verifyListDataAdmin(String username,String password) {

		verifyDataTableExist(tblDataAdmin, [2, 3], [username, password])
	}
	public void verifyDataAdminDeleted(String username,String password) {

		verifyDataTableNotExist(tblDataAdmin, [2, 3], [username, password])
	}
}
