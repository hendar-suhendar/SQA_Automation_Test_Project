package generic

import com.kms.katalon.core.testdata.TestData

class ExcelUtils {

	/**
	 * Cari row pertama yang valid (misal kolom pertama tidak kosong)
	 * Return 1-based index
	 */
	static int checkData(TestData data) {
		int rowCount = data.getRowNumbers()

		for (int i = 1; i <= rowCount; i++) {
			def value = data.getValue(1, i)
			if (value != null && value.toString().trim() != "") {
				return i
			}
		}
		return 1 // fallback minimal 1
	}
}
