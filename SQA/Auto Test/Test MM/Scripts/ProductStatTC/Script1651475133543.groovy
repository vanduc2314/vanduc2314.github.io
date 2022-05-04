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

WebUI.setText(findTestObject('Object Repository/Page_Login/input_Sign In_username'), 'duc')

WebUI.setEncryptedText(findTestObject('Object Repository/Page_Login/input_Sign In_password'), 'tzH6RvlfSTg=')

WebUI.click(findTestObject('Object Repository/Page_Login/button_Sign                            In'))

WebUI.click(findTestObject('Object Repository/Page_Electronic Store/a_Report'))

WebUI.click(findTestObject('Object Repository/Page_Electronic Store/a_Product Statistic'))
if(start_date==""||end_date=="") {
	WebUI.click(findTestObject('Object Repository/Page_Electronic Store/button_Statistic'))
	elementPresent=WebUI.waitForAlert(5)
	if (elementPresent==true) {
	 alertText = WebUI.getAlertText()
	 if (alertText==alert){
	 KeywordUtil.markPassed('SUCCESS')
	 WebUI.acceptAlert()
	 } else {
	 WebUI.acceptAlert()
	 KeywordUtil.markFailed('ERROR')
	 }
	}
}
	else {
		def sdate=new Date().parse("yyyy/mm/dd",start_date)
		String newSdate=sdate.format("yyyy-mm-dd")
		def edate=new Date().parse("yyyy/mm/dd",end_date)
		String newEdate=sdate.format("yyyy-mm-dd")
		WebUI.executeJavaScript('document.getElementById(\'sdate\').value="' + newSdate + '"', null)
		
		WebUI.executeJavaScript('document.getElementById(\'edate\').value="' + newEdate+ '"', null)
		
		WebUI.click(findTestObject('Object Repository/Page_Electronic Store/button_Statistic'))
		
	
	if(alert!="") {
		elementPresent=WebUI.waitForAlert(5)
	if (elementPresent==true) {
	 alertText = WebUI.getAlertText()
	 if (alertText==alert){
	 KeywordUtil.markPassed('SUCCESS')
	 WebUI.acceptAlert()
	 } else {
	 WebUI.acceptAlert()
	 KeywordUtil.markFailed('ERROR')
	 }
	}
	else {
		KeywordUtil.markFailed('Missing alert')
	}
	}}
	

