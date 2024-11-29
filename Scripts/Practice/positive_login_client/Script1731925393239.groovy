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


//Pre-condition login client
//Pengguna berada pada landing page perqara
WebUI.openBrowser('https://staging.perqara.com')
WebUI.maximizeWindow()
WebUI.click(findTestObject('Object Repository/perqara landing page/close banner'))
WebUI.click(findTestObject('Object Repository/perqara landing page/tombol masuk landing page'))
WebUI.setText(findTestObject('Object Repository/perqara landing page/nomor ponsel atau email'), 'noelchristoper@gmail.com')
WebUI.setText(findTestObject('Object Repository/perqara landing page/kata sandi'), '121298Noel@')
WebUI.click(findTestObject('Object Repository/perqara landing page/tombol masuk modal'))
WebUI.waitForPageLoad(2)
WebUI.setText(findTestObject('Object Repository/perqara landing page/otp 1'), '1')
WebUI.setText(findTestObject('Object Repository/perqara landing page/otp 2'), '1')
WebUI.setText(findTestObject('Object Repository/perqara landing page/otp 3'), '1')
WebUI.setText(findTestObject('Object Repository/perqara landing page/otp 4'), '1')
WebUI.setText(findTestObject('Object Repository/perqara landing page/otp 5'), '1')
WebUI.setText(findTestObject('Object Repository/perqara landing page/otp 6'), '1')
WebUI.click(findTestObject('Object Repository/perqara landing page/tombol masuk otp'))