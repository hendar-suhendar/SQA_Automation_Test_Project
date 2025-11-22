package internal

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.main.TestCaseMain


/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */
public class GlobalVariable {
     
    /**
     * <p>Profile default : Base URL Web App</p>
     */
    public static Object Web_Url
     
    /**
     * <p></p>
     */
    public static Object Admin_Username
     
    /**
     * <p></p>
     */
    public static Object Admin_Password
     
    /**
     * <p>Profile default : Mobile App Path</p>
     */
    public static Object Mobile_AppPath
     
    /**
     * <p>Profile default : config Test data value</p>
     */
    public static Object currentTestData
     
    /**
     * <p>Profile default : config Test data index</p>
     */
    public static Object currentIndex
     
    /**
     * <p>Profile default : Database Name</p>
     */
    public static Object DB_Name
     
    /**
     * <p>Profile default : Database Username</p>
     */
    public static Object DB_User
     
    /**
     * <p>Profile default : Database Password</p>
     */
    public static Object DB_Password
     
    /**
     * <p>Profile default : TRUE or FALSE | Run multiple test cases in a Test Suite using a single browser session</p>
     */
    public static Object Single_Open_Browser
     
    /**
     * <p>Profile default : TRUE or FALSE | Run multiple test cases in a Test Suite using a single Login session</p>
     */
    public static Object Single_Login
     
    /**
     * <p>Profile default : TRUE or FALSE | Close Session Application after running single in Testcase</p>
     */
    public static Object Close_Session_TC
     
    /**
     * <p>Profile default : TRUE or FALSE | Close Session Application after running single in Testsuite</p>
     */
    public static Object Close_Session_TS
     

    static {
        try {
            def selectedVariables = TestCaseMain.getGlobalVariables("default")
			selectedVariables += TestCaseMain.getGlobalVariables(RunConfiguration.getExecutionProfile())
    
            Web_Url = selectedVariables['Web_Url']
            Admin_Username = selectedVariables['Admin_Username']
            Admin_Password = selectedVariables['Admin_Password']
            Mobile_AppPath = selectedVariables['Mobile_AppPath']
            currentTestData = selectedVariables['currentTestData']
            currentIndex = selectedVariables['currentIndex']
            DB_Name = selectedVariables['DB_Name']
            DB_User = selectedVariables['DB_User']
            DB_Password = selectedVariables['DB_Password']
            Single_Open_Browser = selectedVariables['Single_Open_Browser']
            Single_Login = selectedVariables['Single_Login']
            Close_Session_TC = selectedVariables['Close_Session_TC']
            Close_Session_TS = selectedVariables['Close_Session_TS']
            
        } catch (Exception e) {
            TestCaseMain.logGlobalVariableError(e)
        }
    }
}
