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

WebUI.openBrowser('https://staging.perqara.com/')

WebUI.maximizeWindow()

WebUI.click(findTestObject('Object Repository/perqara landing page/close banner'))

WebUI.click(findTestObject('Object Repository/perqara landing page/tombol daftar landing page'))

String expectedurl = 'https://staging.perqara.com/register'

String actualurl = WebUI.getUrl()

WebUI.verifyElementVisible(findTestObject('Object Repository/perqara landing page/halaman daftar/form pendaftaran'))

WebUI.verifyElementVisible(findTestObject('Object Repository/perqara landing page/halaman daftar/tooltip data pendukung'))

WebUI.verifyElementVisible(findTestObject('Object Repository/perqara landing page/halaman daftar/link syarat dan ketentuan'))

WebUI.verifyElementVisible(findTestObject('Object Repository/perqara landing page/halaman daftar/link kebijakan privasi'))

WebUI.verifyElementVisible(findTestObject('Object Repository/perqara landing page/halaman daftar/link masuk di sini'))

WebUI.verifyElementVisible(findTestObject('perqara landing page/halaman daftar/tombol advokat perqara register'))

WebUI.takeScreenshot()

WebUI.closeBrowser()