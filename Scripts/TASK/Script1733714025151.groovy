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

//backend login and online-offline lawyer
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

//konsultasi
WebUI.click(findTestObject('Object Repository/perqara landing page/landing page-tombol Konsultasi Sekarang'))
WebUI.click(findTestObject('Object Repository/perqara landing page/modal konsultasi hukum-lihat semua advokat'))
WebUI.setText(findTestObject('Object Repository/Daftar Advokat/field Cari Advokat'), 'noel')
WebUI.click(findTestObject('Object Repository/Daftar Advokat/ikon search'))
if(WebUI.verifyElementClickable(findTestObject('Object Repository/Daftar Advokat/tombol Konsultasi'), FailureHandling.OPTIONAL)) {
	WebUI.click(findTestObject('Object Repository/Daftar Advokat/tombol Konsultasi'))
} else {
	WebUI.verifyElementVisible(findTestObject('Object Repository/Daftar Advokat/informasi advokat sedang menangani kasus lain'))
}
WebUI.click(findTestObject('Object Repository/Daftar Advokat/popup Pilih Konsultasi'))
WebUI.click(findTestObject('Object Repository/Daftar Advokat/Perdata'))
WebUI.setText(findTestObject('Object Repository/Proses Pemesanan/field deskripsi masalah'), '[Testing] Automation Script')
WebUI.click(findTestObject('Object Repository/Proses Pemesanan/tombol Ke Pembayaran'))
WebUI.click(findTestObject('Object Repository/Proses Pemesanan/tombol Ke Pembayaran_modal info pembayaran'))
WebUI.click(findTestObject('Object Repository/Proses Pemesanan/tombol bayar'))
WebUI.waitForPageLoad(5)
WebUI.switchToWindowIndex(1)
WebUI.click(findTestObject('Object Repository/Pembayaran Xendit/dropdown E-wallet'))
WebUI.click(findTestObject('Object Repository/Pembayaran Xendit/OVO'))
WebUI.click(findTestObject('Object Repository/Pembayaran Xendit/tombol Click here to simulate your payment with OVO'))
WebUI.waitForPageLoad(10)
WebUI.switchToWindowTitle("Perqara - Konsultasi Hukum Online")
WebUI.waitForPageLoad(10)
WebUI.refresh()
WebUI.waitForPageLoad(10)
// WebUI.click(findTestObject('Object Repository/Proses Pemesanan/tombol Cek Status'))
// WebUI.waitForPageLoad(10)

WebUI.click(findTestObject('Object Repository/Accept Konsultasi/tombol_Masuk Room Chat'))
// WebUI.waitForPageLoad(10)
// Thread.sleep(10000)
WebUI.refresh()

WebUI.click(findTestObject('Object Repository/Accept Konsultasi/bintang 5 advokat'))
WebUI.setText(findTestObject('Object Repository/Accept Konsultasi/review'), '[Testing] Automation script')
WebUI.click(findTestObject('Object Repository/Accept Konsultasi/bintang 5 pelayanan perqara'))
WebUI.click(findTestObject('Object Repository/Accept Konsultasi/tombol kirim'))


