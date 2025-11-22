package generic

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testdata.TestData
import internal.GlobalVariable

class TestDataUtils {

	/**
	 * Load Test Data otomatis berdasarkan Test Suite / Test Case
	 * - Feature diambil dari TS_xxx Feature atau TCxx_..._Feature
	 * - Test Case ID di Excel sama dengan nama Test Case
	 */
	static void loadTestData(String testCaseName = null) {

		String sourceName = RunConfiguration.getExecutionSourceName()
		String featureName = null
		String tdPath = null
		def data = null

		// --- Ambil feature dari TS atau TC ---
		if (sourceName != null && sourceName.startsWith("TS_")) {
			// TS_Admin Feature -> Admin
			featureName = sourceName.replaceFirst("^TS_", "").split("\\s+")[0]
		} else if (sourceName != null && sourceName.contains("TC")) {
			// TC01_Positive_Admin Add Successfull -> Admin
			def tcFileName = sourceName.split("/").last()
			def matchFeature = tcFileName =~ /TC\d+_(?:Positive|Negative)_(\w+)/
			if (matchFeature.find()) {
				featureName = matchFeature.group(1)
			}
		}

		if (featureName != null) {
			tdPath = "Data Files/Test Data Excel/TD_" + featureName
			try {
				data = TestDataFactory.findTestData(tdPath)
			} catch(Exception e) {
				println "⚠ Test Data ${tdPath} tidak ditemukan: ${e.message}"
				GlobalVariable.currentTestData = null
				GlobalVariable.currentIndex = 1
				return
			}
		} else {
			println "⚠ Tidak bisa menentukan feature dari: ${sourceName}"
			GlobalVariable.currentTestData = null
			GlobalVariable.currentIndex = 1
			return
		}

		// --- Ambil TC ID persis dari testCaseName ---
		if (testCaseName == null) {
			// fallback ambil nama terakhir dari path
			testCaseName = sourceName.split("/").last()
		}

		// --- Tentukan row berdasarkan TC ID di Excel ---
		int rowIndex = 1
		int rowCount = data.getRowNumbers()
		for (int i = 1; i <= rowCount; i++) {
			String tcId = data.getValue("Test Case ID", i)
			if (tcId != null && tcId.trim() == testCaseName.trim()) {
				rowIndex = i
				break
			}
		}

		GlobalVariable.currentTestData = data
		GlobalVariable.currentIndex = rowIndex
		println "✔ Loaded Test Data: ${tdPath} | Row: ${rowIndex} for Test Case: ${testCaseName}"
	}

	static void ensureTestData(String testCaseName = null) {
		if (GlobalVariable.currentTestData == null) {
			loadTestData(testCaseName)
		}
	}
}
