package helpers

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.configuration.RunConfiguration
class BaseHelper {

	/**
	 * Membuat TestObject dari locator string
	 */
	protected TestObject getTestObject(String name, String locator, String type) {
		TestObject to = new TestObject(name)
		switch(type.toLowerCase()) {
			case "id":
				to.addProperty("id", ConditionType.EQUALS, locator)
				break
			case "xpath":
				to.addProperty("xpath", ConditionType.EQUALS, locator)
				break
			case "css":
				to.addProperty("css", ConditionType.EQUALS, locator)
				break
			case "class":
			case "classname":   // tambahan untuk fleksibilitas
				to.addProperty("class", ConditionType.EQUALS, locator)
				break
			default:
				throw new IllegalArgumentException("Locator type not supported: " + type)
		}
		return to
	}

	/**
	 * Highlight element untuk debugging
	 */
	protected void highlightElement(TestObject to) {
		try {
			WebElement element = WebUiCommonHelper.findWebElement(to, 5)
			String originalStyle = element.getAttribute("style")
			String highlightStyle = "border: 3px solid red; border-style: dashed;"
			WebUI.executeJavaScript("arguments[0].setAttribute('style', '" + highlightStyle + "')", Arrays.asList(element))
			Thread.sleep(300) // sejenak supaya terlihat
			WebUI.executeJavaScript("arguments[0].setAttribute('style', '" + originalStyle + "')", Arrays.asList(element))
		} catch (Exception e) {
			logInfo("Highlight failed: " + e.getMessage())
		}
	}

	// ==============================
	// WebUI Actions (STOP on failure)
	// ==============================
	protected void click(TestObject to) {
		highlightElement(to)
		WebUI.click(to, FailureHandling.STOP_ON_FAILURE)
	}

	protected void setText(TestObject to, String value) {
		highlightElement(to)
		WebUI.setText(to, value, FailureHandling.STOP_ON_FAILURE)
	}
	protected void updateText(TestObject to, String value) {
		highlightElement(to)

		WebUI.clearText(to, FailureHandling.STOP_ON_FAILURE)

		WebUI.setText(to, value, FailureHandling.STOP_ON_FAILURE)
	}

	protected void setEncryptedText(TestObject to, String value) {
		highlightElement(to)
		WebUI.setEncryptedText(to, value, FailureHandling.STOP_ON_FAILURE)
	}
	protected void clearInput(TestObject to, String value) {
		WebUI.clearText(to)
		highlightElement(to)
	}

	protected void clearText(TestObject to) {
		highlightElement(to)
		WebUI.clearText(to, FailureHandling.STOP_ON_FAILURE)
	}

	protected String getText(TestObject to) {
		highlightElement(to)
		return WebUI.getText(to, FailureHandling.STOP_ON_FAILURE)
	}

	protected void selectDropdownByValue(TestObject to, String value) {
		highlightElement(to);
		WebUI.selectOptionByValue(to, value, false);
	}

	protected void setCheckbox(TestObject to, boolean value) {
		highlightElement(to)

		WebElement element = WebUI.findWebElement(to, 10, FailureHandling.CONTINUE_ON_FAILURE)
		boolean checked = element.isSelected()

		if (value && !checked) {
			WebUI.click(to, FailureHandling.CONTINUE_ON_FAILURE)
		} else if (!value && checked) {
			WebUI.click(to, FailureHandling.CONTINUE_ON_FAILURE)
		}
	}

	protected void uploadFile(TestObject to, String filePath) {
		highlightElement(to);
		WebUI.uploadFile(to, filePath, FailureHandling.STOP_ON_FAILURE);
	}

	protected void uploadFileFromProject(TestObject to, String fileName) {
		highlightElement(to)
		String projectDir = RunConfiguration.getProjectDir()
		String filePath = projectDir + "/Pictures/" + fileName
		WebUI.uploadFile(to, filePath, FailureHandling.STOP_ON_FAILURE)
	}

	// ==============================
	// Verification (CONTINUE on failure)
	// ==============================
	void verifyElementAndTextVisible(TestObject to, String expectedText) {
		WebUI.delay(2, FailureHandling.CONTINUE_ON_FAILURE)
		highlightElement(to)

		// hapus spasi awal/akhir & semua whitespace berturut-turut menjadi satu spasi
		String actualText = WebUI.getText(to, FailureHandling.CONTINUE_ON_FAILURE)
				.trim()
				.replaceAll("\\s+", " ")
		println(actualText)

		WebUI.verifyMatch(actualText, expectedText, false, FailureHandling.CONTINUE_ON_FAILURE)
	}

	void verifyElementAndTextInitial(TestObject to, String expectedInitial) {
		WebUI.waitForElementVisible(to, 5, FailureHandling.CONTINUE_ON_FAILURE)
		highlightElement(to)

		String fullText = WebUI.getText(to, FailureHandling.CONTINUE_ON_FAILURE).trim()
		String actualInitial = fullText.split('\n')[-1].trim()  // ambil baris terakhir

		WebUI.verifyMatch(actualInitial, expectedInitial, false, FailureHandling.CONTINUE_ON_FAILURE)
	}

	protected void verifyVisible(TestObject to) {
		highlightElement(to)
		WebUI.verifyElementVisible(to, FailureHandling.CONTINUE_ON_FAILURE)
	}


	protected void verifyNotVisible(TestObject to) {
		highlightElement(to)
		WebUI.verifyElementNotVisible(to, FailureHandling.CONTINUE_ON_FAILURE)
	}

	protected void verifyText(TestObject to, String expectedText) {
		highlightElement(to)
		WebUI.verifyElementText(to, expectedText, FailureHandling.CONTINUE_ON_FAILURE)
	}

	protected boolean VerifyElementPresent(TestObject to, int timeout = 5) {
		highlightElement(to)
		return WebUI.verifyElementPresent(to, timeout, FailureHandling.CONTINUE_ON_FAILURE)
	}

	protected void waitForVisible(TestObject to, int timeout = 10) {
		WebUI.waitForElementVisible(to, timeout, FailureHandling.CONTINUE_ON_FAILURE)
	}

	protected void waitForClickable(TestObject to, int timeout = 10) {
		WebUI.waitForElementClickable(to, timeout, FailureHandling.STOP_ON_FAILURE)
	}

	// ==============================
	// Logging
	// ==============================
	protected void logInfo(String message) {
		KeywordUtil.logInfo(message)
	}
	protected void logPassed(String message) {
		KeywordUtil.markPassed(message)
	}
	protected void logFailed(String message) {
		KeywordUtil.markFailed(message)
	}

	// ==============================
	// Dynamic TestObject Helper
	// ==============================
	protected TestObject dynamicXPath(String name, String baseXPath, int index) {
		String xpath = "(${baseXPath})[${index}]"
		return getTestObject(name, xpath, "xpath")
	}


	// ==============================
	// Handling Alert Pop Up
	// ==============================

	// Klik tombol OK pada alert popup
	protected void okButtonAlertPopup() {
		if (WebUI.waitForAlert(2, FailureHandling.CONTINUE_ON_FAILURE)) {
			WebUI.delay(5)
			WebUI.acceptAlert(FailureHandling.CONTINUE_ON_FAILURE)
			WebUI.comment("OK button clicked on alert popup")
		} else {
			WebUI.comment("No alert found to click OK")
		}
	}

	// Klik tombol Cancel pada alert popup
	protected void cancelButtonAlertPopup() {
		if (WebUI.waitForAlert(2, FailureHandling.CONTINUE_ON_FAILURE)) {
			WebUI.dismissAlert(FailureHandling.CONTINUE_ON_FAILURE)
			WebUI.comment("Cancel button clicked on alert popup")
		} else {
			WebUI.comment("No alert found to click Cancel")
		}
	}

	// Verifikasi teks alert popup
	protected void verifyTextAlertPopup(String expectedText) {
		if (WebUI.waitForAlert(2, FailureHandling.CONTINUE_ON_FAILURE)) {
			String actualText = WebUI.getAlertText(FailureHandling.CONTINUE_ON_FAILURE)
			WebUI.comment("Alert text: " + actualText)
			WebUI.verifyEqual(actualText, expectedText,FailureHandling.CONTINUE_ON_FAILURE)
			WebUI.comment("verify equals message on alert popup")
		} else {
			WebUI.comment("No alert found to verify text")
		}
	}

	/**
	 * Verify bahwa data ada di tabel sesuai kombinasi kolom dan nilai
	 * @param tableId - id dari tabel (misal: "sample-table-2")
	 * @param colIndexes - list index kolom yang akan dicek (mulai dari 1)
	 * @param expectedValues - list nilai yang harus sesuai dengan colIndexes
	 * Contoh calling : 
	 * verifyDataTableDynamic(locator/attribute, [2, 3], ["suhendar", "suhendar@gmail.com"])
	 * verifyDataTableDynamic(locator/attribute, [2, 3, 4], ["suhendar", "suhendar@gmail.com", "sample_.jpg"])
	 */
	protected void verifyDataTableDynamic(TestObject to, List<Integer> colIndexes, List<String> expectedValues) {
		WebUI.delay(5, FailureHandling.CONTINUE_ON_FAILURE)

		// 🔹 Highlight dulu tabel utama
		highlightElement(to)

		if (colIndexes.size() != expectedValues.size()) {
			KeywordUtil.markFailed("Jumlah kolom dan jumlah nilai tidak sama!") // tetap lanjut
			return
		}

		// Ambil semua row dari tabel
		TestObject tableRows = new TestObject('tableRows')
		tableRows.addProperty('xpath', ConditionType.EQUALS, to.findPropertyValue('xpath') + "/tbody/tr")

		int rowCount = WebUI.findWebElements(tableRows, 10, FailureHandling.CONTINUE_ON_FAILURE).size()
		WebUI.comment("Jumlah row ditemukan: " + rowCount)

		int matchCount = 0

		// Loop semua row
		for (int i = 1; i <= rowCount; i++) {
			boolean rowMatch = true

			for (int j = 0; j < colIndexes.size(); j++) {
				int colIndex = colIndexes[j]
				String expectedValue = expectedValues[j]

				String cellXpath = to.findPropertyValue('xpath') + "/tbody/tr[${i}]/td[${colIndex}]"
				TestObject cell = new TestObject("cell_${i}_${colIndex}")
				cell.addProperty('xpath', ConditionType.EQUALS, cellXpath)

				String actualValue = WebUI.getText(cell, FailureHandling.CONTINUE_ON_FAILURE).trim()

				// 🔹 Highlight cell yang sedang dicek
				highlightElement(cell)

				if (!actualValue.equalsIgnoreCase(expectedValue)) {
					rowMatch = false
					break
				}
			}

			if (rowMatch) {
				matchCount++
			}
		}

		// Verifikasi hasil (tetap lanjut walaupun gagal)
		if (matchCount == 0) {
			KeywordUtil.markFailed("Data tidak ditemukan di tabel: " + expectedValues) // markFailed biar lanjut
		} else if (matchCount > 1) {
			KeywordUtil.markFailed("Duplikat ditemukan untuk data: " + expectedValues + " (count=" + matchCount + ")")
		} else {
			WebUI.comment("Data ditemukan dan unik: " + expectedValues)
		}
	}

	/**
	 * Verify data ada di tabel sesuai kombinasi kolom dan nilai
	 */
	protected void verifyDataTableExist(TestObject to, List<Integer> colIndexes, List<String> expectedValues) {
		WebUI.delay(3, FailureHandling.CONTINUE_ON_FAILURE)

		// Highlight tabel utama
		highlightElement(to)

		if (colIndexes.size() != expectedValues.size()) {
			KeywordUtil.markFailed("Jumlah kolom dan jumlah nilai tidak sama!")
			return
		}

		// Ambil semua row
		TestObject tableRows = new TestObject('tableRows')
		tableRows.addProperty('xpath', ConditionType.EQUALS, to.findPropertyValue('xpath') + "/tbody/tr")

		List<WebElement> rows = WebUI.findWebElements(tableRows, 10, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.comment("Jumlah row ditemukan: " + rows.size())

		int matchCount = 0

		for (int i = 1; i <= rows.size(); i++) {
			boolean rowMatch = true

			for (int j = 0; j < colIndexes.size(); j++) {
				int colIndex = colIndexes[j]
				String expectedValue = expectedValues[j]

				String cellXpath = to.findPropertyValue('xpath') + "/tbody/tr[${i}]/td[${colIndex}]"
				TestObject cell = new TestObject("cell_${i}_${colIndex}")
				cell.addProperty('xpath', ConditionType.EQUALS, cellXpath)

				String actualValue = WebUI.getText(cell, FailureHandling.OPTIONAL).trim()

				highlightElement(cell)
				WebUI.comment("Row ${i}, Col ${colIndex}: '${actualValue}' vs '${expectedValue}'")

				if (!actualValue.equalsIgnoreCase(expectedValue)) {
					rowMatch = false
					break
				}
			}

			if (rowMatch) {
				matchCount++
			}
		}

		if (matchCount == 0) {
			KeywordUtil.markFailed("❌ Data tidak ditemukan di tabel: " + expectedValues)
		} else if (matchCount > 1) {
			KeywordUtil.markFailed("⚠️ Duplikat ditemukan untuk data: " + expectedValues + " (count=" + matchCount + ")")
		} else {
			WebUI.comment("✅ Data ditemukan dan unik: " + expectedValues)
		}
	}

	/**
	 * Verify data sudah tidak ada di tabel (berhasil delete)
	 */
	protected void verifyDataTableNotExist(TestObject to, List<Integer> colIndexes, List<String> expectedValues) {
		WebUI.delay(3, FailureHandling.CONTINUE_ON_FAILURE)

		highlightElement(to)

		TestObject tableRows = new TestObject('tableRows')
		tableRows.addProperty('xpath', ConditionType.EQUALS, to.findPropertyValue('xpath') + "/tbody/tr")

		List<WebElement> rows = WebUI.findWebElements(tableRows, 10, FailureHandling.CONTINUE_ON_FAILURE)

		boolean found = false

		for (int i = 1; i <= rows.size(); i++) {
			boolean rowMatch = true

			for (int j = 0; j < colIndexes.size(); j++) {
				int colIndex = colIndexes[j]
				String expectedValue = expectedValues[j]

				String cellXpath = to.findPropertyValue('xpath') + "/tbody/tr[${i}]/td[${colIndex}]"
				TestObject cell = new TestObject("cell_${i}_${colIndex}")
				cell.addProperty('xpath', ConditionType.EQUALS, cellXpath)

				String actualValue = WebUI.getText(cell, FailureHandling.OPTIONAL).trim()

				if (!actualValue.equalsIgnoreCase(expectedValue)) {
					rowMatch = false
					break
				}
			}

			if (rowMatch) {
				found = true
				break
			}
		}

		if (found) {
			KeywordUtil.markFailed("❌ Data masih ditemukan di tabel: " + expectedValues)
		} else {
			WebUI.comment("✅ Data sudah tidak ada di tabel (berhasil dihapus): " + expectedValues)
		}
	}


	def chooseToClickByUniqueValue(TestObject table, String uniqueValue, Object target) {
		WebUI.delay(2)
		String targetXpath = ""
		if (target instanceof TestObject) {
			targetXpath = target.findPropertyValue("xpath")
		} else if (target instanceof String) {
			targetXpath = target
		} else {
			KeywordUtil.markFailed("target harus berupa String atau TestObject")
			return
		}

		// sisanya sama seperti sebelumnya
		TestObject tableRows = new TestObject('tableRows')
		tableRows.addProperty('xpath', ConditionType.EQUALS, table.findPropertyValue('xpath') + "/tbody/tr")

		List<WebElement> rows = WebUI.findWebElements(tableRows, 10)
		boolean found = false

		for (int i = 1; i <= rows.size(); i++) {
			String rowXpath = table.findPropertyValue('xpath') + "/tbody/tr[${i}]/td"
			TestObject rowCols = new TestObject("row_${i}")
			rowCols.addProperty("xpath", ConditionType.EQUALS, rowXpath)

			List<WebElement> cols = WebUI.findWebElements(rowCols, 5, FailureHandling.OPTIONAL)

			for (int j = 1; j <= cols.size(); j++) {
				String cellXpath = table.findPropertyValue('xpath') + "/tbody/tr[${i}]/td[${j}]"
				TestObject cell = new TestObject("cell_${i}_${j}")
				cell.addProperty("xpath", ConditionType.EQUALS, cellXpath)

				String actualValue = WebUI.getText(cell, FailureHandling.OPTIONAL).trim()
				WebUI.comment("Checking row ${i}, col ${j}: [${actualValue}] vs [${uniqueValue}]")

				if (actualValue.equalsIgnoreCase(uniqueValue)) {
					// Klik target element di row ini
					String btnXpath = table.findPropertyValue('xpath') + "/tbody/tr[${i}]${targetXpath}"
					TestObject targetBtn = new TestObject("targetBtn_${i}")
					targetBtn.addProperty("xpath", ConditionType.EQUALS, btnXpath)

					highlightElement(targetBtn)
					WebUI.click(targetBtn, FailureHandling.STOP_ON_FAILURE)

					WebUI.comment("Klik sukses untuk unique value: ${uniqueValue}")
					found = true
					break
				}
			}

			if (found) break
		}

		if (!found) {
			KeywordUtil.markWarning("Unique value '${uniqueValue}' tidak ditemukan di tabel")
		}
	}
	/**
	 * Handle confirmation alert with verification
	 * @param expectedText - expected text in alert (optional, bisa "" kalau tidak perlu cek)
	 * @param action - "OK" to accept, "CANCEL" to dismiss
	 */
	protected void handleConfirmationAlert(String expectedText, String action) {
		try {
			WebUI.waitForAlert(5)

			String alertText = WebUI.getAlertText()
			WebUI.comment("⚠️ Alert detected with message: " + alertText)

			// Verify text kalau expectedText tidak kosong
			if (expectedText && !alertText.equalsIgnoreCase(expectedText)) {
				KeywordUtil.markWarning("⚠️ Alert text does not match! Expected: '${expectedText}', Actual: '${alertText}'")
			}

			// Action
			if (action.equalsIgnoreCase("OK")) {
				WebUI.acceptAlert()
				WebUI.comment("✅ Accepted alert (clicked OK)")
			} else if (action.equalsIgnoreCase("CANCEL")) {
				WebUI.dismissAlert()
				WebUI.comment("❌ Dismissed alert (clicked Cancel)")
			} else {
				WebUI.comment("⚠️ Invalid action. Use 'OK' or 'CANCEL'")
			}
		} catch (Exception e) {
			WebUI.comment("❌ No alert detected. Error: " + e.message)
		}
	}
}
