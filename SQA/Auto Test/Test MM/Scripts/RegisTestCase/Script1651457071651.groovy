import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

WebUI.openBrowser('')

WebUI.navigateToUrl('http://localhost:8080/Electronic/')

WebUI.click(findTestObject('Object Repository/Page_Electronic Store/a_Sign In'))

WebUI.click(findTestObject('Object Repository/Page_Login/button_Create                            Account'))

WebUI.setText(findTestObject('Object Repository/Page_Document/input_Create Account_name'), Fullname)

WebUI.setText(findTestObject('Object Repository/Page_Document/input_Create Account_email'), email)

WebUI.setText(findTestObject('Object Repository/Page_Document/input_Create Account_address'), address)

WebUI.setText(findTestObject('Object Repository/Page_Document/input_Create Account_tel'), phone)

WebUI.setText(findTestObject('Object Repository/Page_Document/input_Create Account_username'), username)

WebUI.setText(findTestObject('Object Repository/Page_Document/input_Create Account_password'), password)

WebUI.setText(findTestObject('Object Repository/Page_Document/input_Create Account_cpass'), cpassword)

WebUI.click(findTestObject('Object Repository/Page_Document/button_Create                              _1e4c57'))

if (error == '1') {
    if (WebUI.verifyElementPresent(findTestObject('Page_Document/div_Password not match'), 5) == true) {
        Errmsg_notif = WebUI.getText(findTestObject('Page_Document/div_Password not match'))
		if(WebUI.verifyMatch(Errmsg_notif, output, false)) {
			KeywordUtil.markPassed('Wording is fine')
			}else {
			KeywordUtil.markFailed('There’s a typo')
			}
    }
	else{
		KeywordUtil.markFailed('Dont have arlert')
	}
}

