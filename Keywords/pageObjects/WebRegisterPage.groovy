package pageObjects

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import helpers.BaseHelper
import locators.WebLocators
import generic.ReportUtils
import internal.GlobalVariable as GlobalVariable

class RegisterPage extends BaseHelper {

	public TestObject txtName;
	public TestObject txtEmail;
	public TestObject btnSignup;
	public TestObject lblTitleAccountInfo;
	public TestObject lblTitleAddressInfo;
	public TestObject txtNameInfo;
	public TestObject txtEmailInfo;
	public TestObject txtPasswordInfo;
	public TestObject drpDay;
	public TestObject drpMonth;
	public TestObject drpYear;
	public TestObject chkNewsletter;
	public TestObject chkOptin;
	public TestObject txtFirstName;
	public TestObject txtLastName;
	public TestObject txtCompany;
	public TestObject txtAddress1;
	public TestObject txtAddress2;
	public TestObject selCountry;
	public TestObject txtState;
	public TestObject txtCity;
	public TestObject txtZipcode;
	public TestObject txtMobileNumber;
	public TestObject btnCreateAccount;
	public TestObject btnSignupLogin

	public RegisterAccount() {
		btnSignupLogin			= getTestObject("btnSignupLogin", WebLocators.BTN_SIGNUP_LOGIN, "xpath");
		txtName 				= getTestObject("txtName", WebLocators.INPUT_NAME, "xpath");
		txtEmail 				= getTestObject("txtEmail", WebLocators.INPUT_PASSWORD, "data-qa");
		btnSignup 				= getTestObject("btnSignup", WebLocators.BTN_SIGNUP, "data-qa");
		lblTitleAccountInfo 	= getTestObject("lblTitleAccountInfo", WebLocators.TITLE_ACCOUNT_INFO, "xpath");
		lblTitleAddressInfo 	= getTestObject("lblTitleAddressInfo", WebLocators.TITLE_ADDRESS_INFO, "xpath");
		txtNameInfo 			= getTestObject("txtNameInfo", WebLocators.INPUT_NAME_INFO, "xpath");
		txtEmailInfo 			= getTestObject("txtEmailInfo", WebLocators.INPUT_EMAIL_INFO, "xpath");
		txtPasswordInfo 		= getTestObject("txtPasswordInfo", WebLocators.INPUT_PASSWORD_INFO, "xpath");
		drpDay 					= getTestObject("drpDay", WebLocators.DROPDOWN_DAY, "xpath");
		drpMonth 				= getTestObject("drpMonth", WebLocators.DROPDOWN_MONTH, "xpath");
		drpYear 				= getTestObject("drpYear", WebLocators.DROPDOWN_YEAR, "xpath");
		chkNewsletter 			= getTestObject("chkNewsletter", WebLocators.CHECKBOX_NEWSLETTER, "xpath");
		chkOptin 				= getTestObject("chkOptin", WebLocators.CHECKBOX_OPTIN, "xpath");
		txtFirstName 			= getTestObject("txtFirstName", WebLocators.INPUT_FIRST_NAME, "xpath");
		txtLastName 			= getTestObject("txtLastName", WebLocators.INPUT_LAST_NAME, "xpath");
		txtCompany 				= getTestObject("txtCompany", WebLocators.INPUT_COMPANY, "xpath");
		txtAddress1 			= getTestObject("txtAddress1", WebLocators.INPUT_ADDRESS1, "xpath");
		txtAddress2 			= getTestObject("txtAddress2", WebLocators.INPUT_ADDRESS2, "xpath");
		selCountry 				= getTestObject("selCountry", WebLocators.SELECT_COUNTRY, "xpath");
		txtState 				= getTestObject("txtState", WebLocators.INPUT_STATE, "xpath");
		txtCity 				= getTestObject("txtCity", WebLocators.INPUT_CITY, "xpath");
		txtZipcode 				= getTestObject("txtZipcode", WebLocators.INPUT_ZIPCODE, "xpath");
		txtMobileNumber 		= getTestObject("txtMobileNumber", WebLocators.INPUT_MOBILE_NUMBER, "xpath");
		btnCreateAccount 		= getTestObject("btnCreateAccount", WebLocators.BUTTON_CREATE_ACCOUNT, "xpath");
	}


	public void openBrowser() {
		new generic.WebActions().openBrowser("Chrome", GlobalVariable.Web_Url);
		ReportUtils.captureScreenshot();
	}

	public void clickLoginSignupMenu() {
		click(btnSignupLogin)
	}



	public void inputName(String name) {
		setText(txtName, name);
	}

	public void inputEmail(String email) {
		setText(txtEmail, email);
	}

	public void clickSignup() {
		click(btnSignup);
	}

	public void inputNameInfo(String name) {
		setText(txtNameInfo, name);
	}

	public void inputEmailInfo(String email) {
		setText(txtEmailInfo, email);
	}

	public void inputPassword(String password) {
		setText(txtPasswordInfo, password);
	}

	public void selectDay(String day) {
		selectDropdownByValue(drpDay, day);
	}

	public void selectMonth(String month) {
		selectDropdownByValue(drpMonth, month);
	}

	public void selectYear(String year) {
		selectDropdownByValue(drpYear, year);
	}

	public void checkNewsletter(boolean value) {
		setCheckbox(chkNewsletter, value);
	}

	public void checkOptin(boolean value) {
		setCheckbox(chkOptin, value);
	}

	public void inputFirstName(String firstName) {
		setText(txtFirstName, firstName);
	}

	public void inputLastName(String lastName) {
		setText(txtLastName, lastName);
	}

	public void inputCompany(String company) {
		setText(txtCompany, company);
	}

	public void inputAddress1(String address1) {
		setText(txtAddress1, address1);
	}

	public void inputAddress2(String address2) {
		setText(txtAddress2, address2);
	}

	public void selectCountry(String country) {
		selectDropdownByValue(selCountry, country);
	}

	public void inputState(String state) {
		setText(txtState, state);
	}

	public void inputCity(String city) {
		setText(txtCity, city);
	}

	public void inputZipcode(String zipcode) {
		setText(txtZipcode, zipcode);
	}

	public void inputMobileNumber(String mobileNumber) {
		setText(txtMobileNumber, mobileNumber);
	}

	public void clickCreateAccount() {
		click(btnCreateAccount);
	}

	public void verifyTitleAccountInfo() {
		verifyVisible(lblTitleAccountInfo);
	}

	public void verifyTitleAddressInfo() {
		verifyVisible(lblTitleAddressInfo);
	}
}
