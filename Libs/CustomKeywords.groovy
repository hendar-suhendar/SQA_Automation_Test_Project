
/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */

import java.lang.String

import com.kms.katalon.core.testobject.TestObject



def static "generic.WebActions.openBrowser"(
    	String browserName	
     , 	String url	) {
    (new generic.WebActions()).openBrowser(
        	browserName
         , 	url)
}

 /**
	 * Send request and verify status code
	 * @param request request object, must be an instance of RequestObject
	 * @param expectedStatusCode
	 * @return a boolean to indicate whether the response status code equals the expected one
	 */ 
def static "generic.ApiActions.verifyStatusCode"(
    	TestObject request	
     , 	int expectedStatusCode	) {
    (new generic.ApiActions()).verifyStatusCode(
        	request
         , 	expectedStatusCode)
}

 /**
	 * Add Header basic authorization field,
	 * this field value is Base64 encoded token from user name and password
	 * @param request object, must be an instance of RequestObject
	 * @param username username
	 * @param password password
	 * @return the original request object with basic authorization header field added
	 */ 
def static "generic.ApiActions.addBasicAuthorizationProperty"(
    	TestObject request	
     , 	String username	
     , 	String password	) {
    (new generic.ApiActions()).addBasicAuthorizationProperty(
        	request
         , 	username
         , 	password)
}

 /**
	 * Check if element present in timeout
	 * @param to Katalon test object
	 * @param timeout time to wait for element to show up
	 * @return true if element present, otherwise false
	 */ 
def static "generic.MobileActions.isElementPresent_Mobile"(
    	TestObject to	
     , 	int timeout	) {
    (new generic.MobileActions()).isElementPresent_Mobile(
        	to
         , 	timeout)
}

 /**
	 * Get mobile driver for current session
	 * @return mobile driver for current session
	 */ 
def static "generic.MobileActions.getCurrentSessionMobileDriver"() {
    (new generic.MobileActions()).getCurrentSessionMobileDriver()
}
