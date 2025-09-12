import pageObjects.LoginScreen
import com.kms.katalon.core.testdata.ExcelData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory

// Load Excel data
ExcelData loginData = TestDataFactory.findTestData("Data Files/LoginData")

LoginScreen login = new LoginScreen()

for (def i = 1; i <= loginData.getRowNumbers(); i++) {
    def username = loginData.getValue("username", i)
    def password = loginData.getValue("password", i)
    def expectedError = loginData.getValue("expectedError", i)

    login.inputUsername(username)
    login.inputPassword(password)
    login.clickLogin()

    // Verify error if present
    if (login.isPresent(login.lblError)) {
        login.verifyErrorMessage(expectedError)
    } else {
        login.verifyLandingScreen()
    }

    // Clear form for next iteration
    login.clearText(login.txtUsername)
    login.clearText(login.txtPassword)
}
