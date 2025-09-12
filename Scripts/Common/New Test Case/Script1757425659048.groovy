import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import helpers.BaseHelper
import locators.WebLocators
import generic.ReportUtils
import internal.GlobalVariable


public static final String TBL_DATA_ADMIN   = "//table[@id='sample-table-2']";
public static final String OBJ_EMAIL_COLUMN = "//th[normalize-space(text())='Email']";
public static final String TBL_UPDATE_ADMIN = "//a[text()='Update']";
public static final String TBL_DELETE_ADMIN = "//a[text()='Delete']";
def uniqueValue = "admin02@example.com"
chooseToClickByUniqueValue(TBL_DATA_ADMIN,uniqueValue,btnUpdateAdmin)
