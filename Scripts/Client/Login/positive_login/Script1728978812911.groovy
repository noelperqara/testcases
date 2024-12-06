import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import javax.servlet.annotation.WebInitParam

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


//Pre-condition
//Pengguna berada pada landing page perqara
WebUI.openBrowser('https://staging.perqara.com')
WebUI.maximizeWindow()
WebUI.click(findTestObject('Object Repository/perqara landing page/landing page-close banner'))

//Test step
//1. Klien klik tombol 'Masuk' pada landing page
WebUI.click(findTestObject('Object Repository/perqara landing page/landing page-masuk'))

//2. Klien memasukkan nomor ponsel atau email pada modal login
WebUI.setText(findTestObject('Object Repository/perqara landing page/modal login-nomor ponsel atau email'), 'noelchristoper@gmail.com')
WebUI.setText(findTestObject('Object Repository/perqara landing page/modal login-kata sandi'), '121298Noel@')

//3. Klien klik tombol 'Masuk' pada modal login
WebUI.click(findTestObject('Object Repository/perqara landing page/modal login-masuk'))
WebUI.waitForPageLoad(2)

//4. Klien memasukkan kode OTP valid
WebUI.setText(findTestObject('Object Repository/perqara landing page/modal otp-otp 1'), '1')
WebUI.setText(findTestObject('Object Repository/perqara landing page/modal otp-otp 2'), '1')
WebUI.setText(findTestObject('Object Repository/perqara landing page/modal otp-otp 3'), '1')
WebUI.setText(findTestObject('Object Repository/perqara landing page/modal otp-otp 4'), '1')
WebUI.setText(findTestObject('Object Repository/perqara landing page/modal otp-otp 5'), '1')
WebUI.setText(findTestObject('Object Repository/perqara landing page/modal otp-otp 6'), '1')

//5. Klien klik tombol 'Masuk' pada modal OTP
WebUI.click(findTestObject('Object Repository/perqara landing page/modal otp-konfirmasi'))

//Expected result
WebUI.verifyElementVisible(findTestObject('Object Repository/perqara landing page/landing page-login-dropdown akun'))

//Actual result
WebUI.takeFullPageScreenshot()

WebUI.closeBrowser()