import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
//import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
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


ResponseObject loginResponse = WS.sendRequest(findTestObject('Object Repository/login lawyer'))

assert loginResponse.getStatusCode() == 200

ResponseObject otpResponse = WS.sendRequest(findTestObject('Object Repository/otp login lawyer'))

// Step 2: Parse Login Response to Extract Token
assert otpResponse.getStatusCode() == 200 // Ensure otp validate was successful

def jsonResponse = new JsonSlurper().parseText(otpResponse.getResponseText())

GlobalVariable.authToken = jsonResponse.data.token

println "Bearer Token: ${GlobalVariable.authToken}"

ResponseObject setOnlineResponse = WS.sendRequest(findTestObject('Object Repository/set online lawyer'))

def onlineresponse = new JsonSlurper().parseText(setOnlineResponse.getResponseText())

println "response lawyer online:" + onlineresponse


