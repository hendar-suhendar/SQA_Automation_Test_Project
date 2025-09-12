package generic

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.configuration.RunConfiguration
import java.io.File
import config.TempGlobalVariables as TG

public class ReportUtils {

	static void createNewFolder(String tc_name) {

		// Path folder base untuk testcase
		String folderPath = "Reports/CustomPDFReport/" + tc_name
		File folder = new File(folderPath)

		// Jika folder testcase belum ada, buat terlebih dahulu
		if (!folder.exists()) {
			folder.mkdirs()
			println "Folder testcase dibuat: ${folder.absolutePath}"
		}

		List<Integer> numbers = []

		// Ambil angka dari setiap folder Run di dalam folder testcase
		folder.listFiles()?.each { f ->
			if (f.isDirectory()) {
				String strNum = f.name.replaceAll("\\D", "")
				if (strNum) numbers << strNum.toInteger()
			}
		}

		// Tentukan nomor Run baru
		int newNumber = (numbers ? numbers.max() : 0) + 1
		File newFolder = new File(folder, "Run${newNumber}")

		// Buat folder Run baru jika belum ada
		if (!newFolder.exists()) {
			newFolder.mkdirs()
			println "Folder Run baru berhasil dibuat: ${newFolder.absolutePath}"
		}
	}



	static String captureScreenshot() {
		try {
			// Ambil nama test case aman untuk folder
			def safeFolderName = TG.get('Test_Case_Name')
			String baseDir = RunConfiguration.getProjectDir() + "/Reports/CustomPDFReport/" + safeFolderName

			File baseFolder = new File(baseDir)
			if (!baseFolder.exists()) {
				println "Folder base tidak ditemukan: ${baseDir}"
				return null
			}

			// ===== Cari folder Run dengan nomor terbesar =====
			File targetFolder = null
			baseFolder.listFiles()?.each { f ->
				if (f.isDirectory() && f.name.startsWith("Run")) {
					if (targetFolder == null) {
						targetFolder = f
					} else {
						int currentNum = (f.name.replaceAll("\\D", "") ?: "0").toInteger()
						int maxNum = (targetFolder.name.replaceAll("\\D", "") ?: "0").toInteger()
						if (currentNum > maxNum) targetFolder = f
					}
				}
			}

			if (targetFolder == null) {
				println "Tidak ada folder Run di ${baseFolder.absolutePath}"
				return null
			}

			// ===== Tentukan nomor screenshot berikutnya =====
			List<Integer> existingNumbers = []
			targetFolder.listFiles()?.each { file ->
				if (file.name.startsWith("screenshot_")) {
					def numStr = file.name.replaceAll("\\D", "")
					if (numStr) existingNumbers << numStr.toInteger()
				}
			}
			int nextNumber = existingNumbers ? existingNumbers.max() + 1 : 1

			// ===== Nama file screenshot =====
			String screenshotFile = targetFolder.getAbsolutePath() + "/screenshot_" + nextNumber + ".png"

			// Ambil screenshot
			WebUI.takeScreenshot(screenshotFile)
			println "Screenshot berhasil disimpan di: ${screenshotFile}"
			return screenshotFile
		} catch (Exception e) {
			println "Screenshot failed: " + e.message
			return null
		}
	}


	static String errorCaptureScreenshot(String status) {
		try {
			// Ambil nama test case aman untuk folder
			def safeFolderName = TG.get('Test_Case_Name')
			String baseDir = RunConfiguration.getProjectDir() + "/Reports/CustomPDFReport/" + safeFolderName

			File baseFolder = new File(baseDir)
			if (!baseFolder.exists()) {
				println "Folder base tidak ditemukan: ${baseDir}"
				return null
			}

			// ===== Cari folder Run dengan nomor terbesar =====
			File targetFolder = null
			baseFolder.listFiles()?.each { f ->
				if (f.isDirectory() && f.name.startsWith("Run")) {
					if (targetFolder == null) {
						targetFolder = f
					} else {
						int currentNum = (f.name.replaceAll("\\D", "") ?: "0").toInteger()
						int maxNum = (targetFolder.name.replaceAll("\\D", "") ?: "0").toInteger()
						if (currentNum > maxNum) targetFolder = f
					}
				}
			}

			if (targetFolder == null) {
				println "Tidak ada folder Run di ${baseFolder.absolutePath}"
				return null
			}

			// ===== Tentukan nomor screenshot berikutnya =====
			List<Integer> existingNumbers = []
			targetFolder.listFiles()?.each { file ->
				if (file.name.startsWith("screenshot_")) {
					def numStr = file.name.replaceAll("\\D", "")
					if (numStr) existingNumbers << numStr.toInteger()
				}
			}
			int nextNumber = existingNumbers ? existingNumbers.max() + 1 : 1

			// ===== Nama file screenshot =====
			String screenshotFile = targetFolder.getAbsolutePath() + "/screenshot_" + nextNumber +"_"+status+ ".png"

			// Ambil screenshot
			WebUI.takeScreenshot(screenshotFile)
			println "Screenshot berhasil disimpan di: ${screenshotFile}"
			return screenshotFile
		} catch (Exception e) {
			println "Screenshot failed: " + e.message
			return null
		}
	}
}