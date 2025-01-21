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
import org.openqa.selenium.Keys

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import com.kms.katalon.core.testobject.ResponseObject

//Lawyer login dengan menggunakan valid credentials
ResponseObject loginResponse = WS.sendRequest(findTestObject('Object Repository/api/login lawyer'))
assert loginResponse.getStatusCode() == 200

//Lawyer memasukkan kode otp valid
ResponseObject otp = WS.sendRequest(findTestObject('Object Repository/api/otp login lawyer'))
assert otp.getStatusCode() == 200
def otpresponse = new JsonSlurper().parseText(otp.getResponseText())
//Verifikasi apakah lawyer sudah berhasil login dengan melakukan pengecekan bearer token
GlobalVariable.authToken = otpresponse.data.token
println "Bearer Token: ${GlobalVariable.authToken}"

//Lawyer set status online
ResponseObject setonline = WS.sendRequest(findTestObject('Object Repository/api/set online lawyer'))
//Verifikasi bahwa lawyer telah berhasil online
def setonlineresponse = new JsonSlurper().parseText(setonline.getResponseText())
println "response lawyer online:" + setonlineresponse

//Melakukan pengecekan terhadap konsultasi yang sedang berlangsung
ResponseObject ongoingconsultation = WS.sendRequest(findTestObject('Object Repository/api/ongoing consultation'))
//Verifikasi ketika terdapa konsultasi yang sedang berlangsung, maka consid dan roomkey memiliki value
def ongoingconsultationresponse = new JsonSlurper().parseText(ongoingconsultation.getResponseText())
GlobalVariable.consid = ongoingconsultationresponse.data.id
println "id konsultasi: ${GlobalVariable.consid}"
GlobalVariable.roomkey = ongoingconsultationresponse.data.room_key
println "room key: ${GlobalVariable.roomkey}"

//Menerima permintaan konsultasi
//Set up payload consid sebagai body request untuk menyetujui, menolak ataupun mengakhiri konsultasi
def approvalid = GlobalVariable.consid[0] as Integer

def considpayload = JsonOutput.toJson([
	consultation_id: approvalid
	])

println ('approval json: ' + considpayload)

//Menerima permintaan konsultasi yang sedang aktif
ResponseObject consultationapproval = WS.sendRequest(findTestObject('Object Repository/api/approve', [
										  ('body'): considpayload
										  ]))

println "" + consultationapproval.getStatusCode()

def consultationapprovalresponse = new JsonSlurper().parseText(consultationapproval.getResponseText())

println "response approval: " + consultationapprovalresponse

//Lawyer mengakhiri sesi konsultasi
ResponseObject endconsultation = WS.sendRequest(findTestObject('Object Repository/api/end consultation', [
									('body'): considpayload
									]))

println "" + endconsultation.getStatusCode()

def endconsultationresponse = new JsonSlurper().parseText(endconsultation.getResponseText())

println "response end_consultation:" + endconsultationresponse

//Lawyer mengirim ringkasan konsultasi
//Set up value roomkey sebagai value dari body request summary
def roomkey = GlobalVariable.roomkey[0] as String

def roomkeypayload = JsonOutput.toJson([
	room_key: roomkey
	])

println "roomkey payload" + roomkeypayload

ResponseObject lawyerauthorization = WS.sendRequest(findTestObject('Object Repository/api/authorize',[
										('room_key'): roomkeypayload
										]))

println "" + lawyerauthorization.getStatusCode()

def lawyerauthorizationresponse = new JsonSlurper().parseText(lawyerauthorization.getResponseText())

println "response authorization" + lawyerauthorizationresponse


ResponseObject posttohistory = WS.sendRequest(findTestObject('Object Repository/api/post history',[
								  ('room_key'): roomkeypayload
								  ]))

println "" + posttohistory.getStatusCode()
//Set up summary payload
def postsummarypayload = JsonOutput.toJson([
	room_key: roomkey,
    matter: "[Testing] Backend automation",
    legal_basis: "[Testing] Backend automation",
    analysis: "[Testing] Backend automation",
    conclusion: "[Testing] Backend automation",
	skill_id: 6,
	skill_type_id: 37
])

println "summary payload:" + postsummarypayload

ResponseObject setsummary = WS.sendRequest(findTestObject('Object Repository/api/set summary',[
								('summary_payload'): postsummarypayload
								]))

def setsummaryresponse = new JsonSlurper().parseText(setsummary.getResponseText())

println "response summary:" + setsummaryresponse
