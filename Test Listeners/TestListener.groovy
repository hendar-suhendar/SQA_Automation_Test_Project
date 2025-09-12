import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import com.kms.katalon.core.annotation.*
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import helpers.PdfReportGenerator
import config.TempGlobalVariables as TG
import internal.GlobalVariable
import generic.ReportUtils
import generic.TestDataUtils
import com.kms.katalon.core.testdata.TestData

class TestListener {

	// ====== Storage untuk laporan ======
	static List<Map<String,Object>> testCases = []
	static Map<String,Long> startTimes = [:]

	// ===== Before Test Suite =====
	@BeforeTestSuite
	def beforeTestSuite(TestSuiteContext testSuiteContext) {
		// Running with
		if (GlobalVariable.Single_Open_Browser == true) {
			new generic.WebActions().openBrowser('Chrome', GlobalVariable.Web_Url)
		}
		testCases.clear()
		startTimes.clear()
		println "[INFO] Mulai Test Suite: ${testSuiteContext.getTestSuiteId()}"
	}

	// ===== Before Test Case =====
	@BeforeTestCase
	def beforeTestCase(TestCaseContext testCaseContext) {
		// Ambil nama Test Case dari context
		String testCaseName = testCaseContext.getTestCaseId().split("/").last()
		TestDataUtils.loadTestData(testCaseName)
		
		if(GlobalVariable.Single_Open_Browser==false) {
			new generic.WebActions().openBrowser('Chrome', GlobalVariable.Web_Url)
		}else {
			println("Open Browser is NOT set for single running.")
		}
		
		String tcName = testCaseContext.getTestCaseId().tokenize("/")[-1]
		TG.set('Test_Case_Name', tcName)

		// Buat folder Run
		ReportUtils.createNewFolder(tcName)

		// Simpan waktu mulai eksekusi (ms)
		startTimes[tcName] = System.currentTimeMillis()
	}

	// ===== After Test Case =====
	@AfterTestCase
	def afterTestCase(TestCaseContext testCaseContext) {
		if(GlobalVariable.Close_Session_TC==true) {
			WebUI.closeBrowser()
		}
		
		
		//ambil description
		
		/*==== Inisialisasi Active Test Data ====*/
		TestData data = GlobalVariable.currentTestData
		int index = GlobalVariable.currentIndex
		
		
		String tcName   = testCaseContext.getTestCaseId().split("/")[-1]
		// Hitung execution time (detik)
		long startTime = startTimes[tcName] ?: System.currentTimeMillis()
		long endTime   = System.currentTimeMillis()
		double execTimeInSeconds = (endTime - startTime) / 1000.0
		
		
		String rawStatus = testCaseContext.getTestCaseStatus() ?: "NOT_EXECUTED"
		// Normalisasi status
		String status
		switch(rawStatus.toUpperCase()) {
			case "PASSED": status = "PASS"; break
			case "FAILED": status = "FAIL"; break
			case "ERROR":  status = "FAIL"; break
			default:       status = rawStatus
		}



		println "[INFO] TC: ${tcName}, Status=${status}, Time=${String.format('%.2f', execTimeInSeconds)} detik"

		// ===== Folder untuk screenshot =====
		def projectDir = RunConfiguration.getProjectDir()
		def baseFolder = new File("${projectDir}/Reports/CustomPDFReport/${tcName}")
		if (!baseFolder.exists()) baseFolder.mkdirs()

		// Ambil Run terakhir
		File targetRunFolder = null
		def runFolders = baseFolder.listFiles()?.findAll { it.isDirectory() && it.name.startsWith("Run") } ?: []
		if (runFolders) {
			targetRunFolder = runFolders.max { f ->
				def num = f.name.replaceAll("\\D", "")
				num ? num.toInteger() : 0
			}
		}
		if (!targetRunFolder) {
			targetRunFolder = new File(baseFolder, "Run1")
			targetRunFolder.mkdirs()
		}

		// ===== Screenshot Handling =====
		List<String> screenshots = []

		if (status.equalsIgnoreCase("FAIL")) {
			// Screenshot khusus FAIL
			String screenshotFile = "${targetRunFolder.absolutePath}/screenshot_FAIL.png"
			try {
				WebUI.takeScreenshot(screenshotFile)
				screenshots << screenshotFile
				println "[INFO] Screenshot gagal disimpan: ${screenshotFile}"
			} catch (Exception e) {
				println "[ERROR] Gagal ambil screenshot: ${e.message}"
			}
		} else {
			// Kalau PASS → tidak ambil baru, cukup kosong atau ambil existing
			targetRunFolder.listFiles()?.findAll {
				it.isFile() && it.name.toLowerCase().startsWith("screenshot_")
			}?.sort { a, b ->
				def numA = (a.name.replaceAll("\\D","") ?: "0").toInteger()
				def numB = (b.name.replaceAll("\\D","") ?: "0").toInteger()
				return numA <=> numB
			}?.each { file -> screenshots << file.absolutePath }
		}

		// Remarks
		String remarks = (status.equalsIgnoreCase("PASS")) ? "Test case passed" : "Error displayed"

		// Tambahkan ke list
		testCases << [
			id        : tcName,
			name      : data.getValue('Description', index),
			status    : status,
			time      : execTimeInSeconds, // sudah dalam detik
			screenshot: screenshots,
			remarks   : remarks
		]
	}

	// ===== After Test Suite =====
	@AfterTestSuite
	def afterTestSuite(TestSuiteContext testSuiteContext) {
		if(GlobalVariable.Close_Session_TS==true) {
		WebUI.closeBrowser()
		}
		
		String testSuiteName = testSuiteContext.getTestSuiteId().split("/")[-1]
		String timestamp = new Date().format("yyyyMMdd_HHmmss")
		String pdfPath = RunConfiguration.getProjectDir() + "/Reports/CustomPDFReport/${testSuiteName}_Report_${timestamp}.pdf"

		PdfReportGenerator.generateReport(pdfPath, testCases, testSuiteName)
		println "[INFO] PDF report berhasil dibuat: ${pdfPath}"

		// Summary di console
		int total  = testCases.size()
		int passed = testCases.count { it.status == "PASS" }
		int failed = testCases.count { it.status == "FAIL" }
		double passRate = total > 0 ? (passed * 100.0 / total) : 0
		println "[SUMMARY] Total=${total}, Passed=${passed}, Failed=${failed}, PassRate=${String.format('%.2f%%', passRate)}"
		
		
	}
}
