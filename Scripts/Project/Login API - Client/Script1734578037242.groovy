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
import groovy.json.JsonSlurper
import com.kms.katalon.core.testobject.ResponseObject

WebUI.openBrowser('https://staging.perqara.com/')
WebUI.maximizeWindow()
// WebUI.click(findTestObject('Object Repository/perqara landing page/landing page-close banner'))
WebUI.click(findTestObject('Object Repository/perqara landing page/landing page-masuk'))
// WebUI.click(findTestObject('Object Repository/perqara landing page/landing page-advokat perqara'))
WebUI.click(findTestObject('Object Repository/perqara landing page/halaman login/masuk sebagai advokat'))

String expectedurl = 'https://staging.perqara.com/login/advokat'
String actualurl = WebUI.getUrl()
//Test step
WebUI.setText(findTestObject('Object Repository/lawyer landing page/nomor ponsel atau email advokat'), 'noel@perqara.com')
WebUI.setText(findTestObject('Object Repository/lawyer landing page/kata sandi advokat'), '12345678')
WebUI.click(findTestObject('Object Repository/lawyer landing page/tombol masuk landing page advokat'))
WebUI.setText(findTestObject('Object Repository/lawyer landing page/otp 1 advokat'), '1')
WebUI.setText(findTestObject('Object Repository/lawyer landing page/otp 2 advokat'), '1')
WebUI.setText(findTestObject('Object Repository/lawyer landing page/otp 3 advokat'), '1')
WebUI.setText(findTestObject('Object Repository/lawyer landing page/otp 4 advokat'), '1')
WebUI.setText(findTestObject('Object Repository/lawyer landing page/otp 5 advokat'), '1')
WebUI.setText(findTestObject('Object Repository/lawyer landing page/otp 6 advokat'), '1')
WebUI.click(findTestObject('Object Repository/lawyer landing page/tombol verifikasi advokat page'))


if (WebUI.verifyElementVisible(findTestObject('Object Repository/lawyer landing page/offline status'), FailureHandling.OPTIONAL)) {
	WebUI.click(findTestObject('Object Repository/lawyer landing page/radiobutton up'))
	WebUI.click(findTestObject('Object Repository/lawyer landing page/radio button online'))
	WebUI.click(findTestObject('Object Repository/lawyer landing page/tombol perbarui'))
	
} else (WebUI.verifyElementVisible(findTestObject('Object Repository/lawyer landing page/online status'), FailureHandling.OPTIONAL)) 
	
ResponseObject loginResponse = WS.sendRequest(findTestObject('Object Repository/login client'))

// assert loginResponse.getStatusCode() == 200

ResponseObject otpResponse = WS.sendRequest(findTestObject('Object Repository/otp login client'))



