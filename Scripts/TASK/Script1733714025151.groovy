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
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import groovy.json.JsonSlurper
import com.kms.katalon.core.testobject.ResponseObject
import groovy.json.JsonOutput

//backend Lawyer login dengan menggunakan valid credentials
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

//Login klien
WebUI.openBrowser('https://staging.perqara.com')
WebUI.maximizeWindow()
WebUI.click(findTestObject('Object Repository/perqara landing page/landing page-close banner'))
WebUI.click(findTestObject('Object Repository/perqara landing page/landing page-masuk'))
WebUI.setText(findTestObject('Object Repository/perqara landing page/modal login-nomor ponsel atau email'), 'noelchristoper@gmail.com')
WebUI.setText(findTestObject('Object Repository/perqara landing page/modal login-kata sandi'), '121298Noel@')
WebUI.click(findTestObject('Object Repository/perqara landing page/modal login-masuk'))
WebUI.waitForPageLoad(2)
WebUI.setText(findTestObject('Object Repository/perqara landing page/modal otp-otp 1'), '1')
WebUI.setText(findTestObject('Object Repository/perqara landing page/modal otp-otp 2'), '1')
WebUI.setText(findTestObject('Object Repository/perqara landing page/modal otp-otp 3'), '1')
WebUI.setText(findTestObject('Object Repository/perqara landing page/modal otp-otp 4'), '1')
WebUI.setText(findTestObject('Object Repository/perqara landing page/modal otp-otp 5'), '1')
WebUI.setText(findTestObject('Object Repository/perqara landing page/modal otp-otp 6'), '1')
WebUI.click(findTestObject('Object Repository/perqara landing page/modal otp-konfirmasi'))
WebUI.click(findTestObject('Object Repository/perqara landing page/tombol OK'))
WebUI.click(findTestObject('Object Repository/perqara landing page/close modal otp'))

//konsultasi
WebUI.click(findTestObject('Object Repository/perqara landing page/landing page-tombol Konsultasi Sekarang'))
WebUI.click(findTestObject('Object Repository/perqara landing page/modal konsultasi hukum-lihat semua advokat'))
WebUI.click(findTestObject('Object Repository/perqara landing page/pop-up button lanjut pilih advokat'))
WebUI.setText(findTestObject('Object Repository/Daftar Advokat/field Cari Advokat'), 'noel')
WebUI.click(findTestObject('Object Repository/Daftar Advokat/ikon search'))
WebUI.waitForPageLoad(5)
WebUI.click(findTestObject('Object Repository/Daftar Advokat/tombol Konsultasi'))

//if(WebUI.verifyElementClickable(findTestObject('Object Repository/Daftar Advokat/tombol Konsultasi'), FailureHandling.OPTIONAL)) {
//	WebUI.click(findTestObject('Object Repository/Daftar Advokat/tombol Konsultasi'))
//} else {
//	WebUI.verifyElementVisible(findTestObject('Object Repository/Daftar Advokat/informasi advokat sedang menangani kasus lain'))
//}

WebUI.click(findTestObject('Object Repository/Daftar Advokat/popup Pilih Konsultasi'))
WebUI.click(findTestObject('Object Repository/Daftar Advokat/Perdata'))
WebUI.setText(findTestObject('Object Repository/Proses Pemesanan/field deskripsi masalah'), '[Testing] Automation Script')
WebUI.click(findTestObject('Object Repository/Proses Pemesanan/tombol Ke Pembayaran'))
WebUI.click(findTestObject('Object Repository/Proses Pemesanan/tombol Ke Pembayaran_modal info pembayaran'))
WebUI.scrollToElement(findTestObject('Object Repository/Proses Pemesanan/radio button E-Wallet'), 5)
WebUI.click(findTestObject('Object Repository/Proses Pemesanan/radio button E-Wallet'))
WebUI.click(findTestObject('Object Repository/Proses Pemesanan/tombol bayar'))
WebUI.waitForPageLoad(5)
WebUI.switchToWindowIndex(1)
WebUI.click(findTestObject('Object Repository/Pembayaran Xendit/Dropdown Ewallet'))
WebUI.click(findTestObject('Object Repository/Pembayaran Xendit/ewallet ovo'))
WebUI.click(findTestObject('Object Repository/Pembayaran Xendit/simulasikan pembayaran'))
WebUI.delay(5)
WebUI.switchToWindowTitle("Perqara - Konsultasi Hukum Online")
//WebUI.waitForPageLoad(10)
//WebUI.click(findTestObject('Object Repository/Proses Pemesanan/button_Cek Status'))
//
//WebUI.waitForPageLoad(5)
WebUI.refresh()
WebUI.delay(2)
WebUI.click(findTestObject('Object Repository/Waiting Room/tombol_Masuk Waiting Room'))

//Melakukan pengecekan terhadap konsultasi yang sedang berlangsung
ResponseObject ongoingconsultation = WS.sendRequest(findTestObject('Object Repository/api/ongoing consultation'))
//Verifikasi ketika terdapat konsultasi yang sedang berlangsung, maka consid dan roomkey memiliki value
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


WebUI.click(findTestObject('Object Repository/Accept Konsultasi/bintang 5 advokat'))
WebUI.setText(findTestObject('Object Repository/Accept Konsultasi/review'), 'I think this Lawyers answer is good')
// WebUI.scrollToElement(findTestObject('Object Repository/Accept Konsultasi/bintang 5 pelayanan perqara'), 5)
WebUI.click(findTestObject('Object Repository/Accept Konsultasi/bintang 5 pelayanan perqara'))
WebUI.click(findTestObject('Object Repository/Accept Konsultasi/tombol kirim'))

WebUI.closeBrowser()
