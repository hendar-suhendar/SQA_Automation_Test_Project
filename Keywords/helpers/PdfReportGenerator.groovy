package helpers
import com.kms.katalon.core.configuration.RunConfiguration
import com.itextpdf.text.BaseColor
import com.itextpdf.text.Chunk
import com.itextpdf.text.Document
import com.itextpdf.text.Element
import com.itextpdf.text.Font
import com.itextpdf.text.FontFactory
import com.itextpdf.text.Image
import com.itextpdf.text.PageSize
import com.itextpdf.text.Paragraph
import com.itextpdf.text.Phrase
import com.itextpdf.text.Rectangle
import com.itextpdf.text.pdf.PdfPCell
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfWriter

import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.List
import java.util.Map

class PdfReportGenerator {

	static void generateReport(String filePath, List<Map<String,Object>> testCases, String moduleName) {
		Document document = new Document(PageSize.A4, 36, 36, 54, 36)
		PdfWriter.getInstance(document, new FileOutputStream(filePath))
		document.open()

		// ===== Header Table =====
		PdfPTable headerTable = new PdfPTable(3)
		headerTable.widthPercentage = 100
		headerTable.setWidths([1, 3, 1] as float[])

		PdfPCell leftLogoCell = new PdfPCell()
		leftLogoCell.border = Rectangle.NO_BORDER

		PdfPCell centerTextCell = new PdfPCell()
		centerTextCell.border = Rectangle.NO_BORDER
		centerTextCell.horizontalAlignment = Element.ALIGN_CENTER
		Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK)
		Font dateFont = FontFactory.getFont(FontFactory.HELVETICA, 8, BaseColor.DARK_GRAY)
		String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
		Paragraph headerText = new Paragraph()
		headerText.add(new Chunk("Automation Test Result\n", headerFont))
		headerText.add(new Chunk("Executed at: " + now, dateFont))
		headerText.alignment = Element.ALIGN_CENTER
		headerText.add(Chunk.NEWLINE)
		headerText.add(Chunk.NEWLINE)
		centerTextCell.addElement(headerText)

		// Ambil path logo dari folder project
		String logoPath = RunConfiguration.getProjectDir() + "/Pictures/Logo.png";
		Image comLogo = Image.getInstance(logoPath);
		comLogo.scaleToFit(70, 70);

		// Buat PdfPCell dengan gambar di dalamnya
		PdfPCell rightLogoCell = new PdfPCell(comLogo);
		rightLogoCell.setBorder(Rectangle.NO_BORDER);
		rightLogoCell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		rightLogoCell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);


		headerTable.addCell(leftLogoCell)
		headerTable.addCell(centerTextCell)
		headerTable.addCell(rightLogoCell)

		document.add(headerTable)
		document.add(Chunk.NEWLINE)

		// ===== Summary Table =====
		int total = testCases.size()
		int passed = testCases.count { it.get("status")?.toString()?.equalsIgnoreCase("PASS") }
		int failed = testCases.count { it.get("status")?.toString()?.equalsIgnoreCase("FAIL") }
		int passRate = total > 0 ? Math.round((passed * 100.0) / total) as int : 0

		Map<String,Object> summary = [
			"Feature/Module"   : moduleName,
			"Total Test Cases" : total,
			"Passed"           : passed,
			"Failed"           : failed,
			"Pass Rate"        : passRate.toString() + "%"
		]

		Font summaryKeyFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8)
		Font summaryValFont = FontFactory.getFont(FontFactory.HELVETICA, 8)

		PdfPTable summaryTable = new PdfPTable(2)
		summaryTable.setWidths([1.5, 2.5] as float[])
		summaryTable.widthPercentage = 43
		summaryTable.horizontalAlignment = Element.ALIGN_LEFT

		BaseColor headerBg = new BaseColor(220, 230, 241)
		summary.each { k, v ->
			PdfPCell keyCell = new PdfPCell(new Phrase(k, summaryKeyFont))
			keyCell.border = Rectangle.NO_BORDER
			keyCell.backgroundColor = headerBg
			keyCell.padding = 4
			summaryTable.addCell(keyCell)

			PdfPCell valCell = new PdfPCell(new Phrase(v.toString(), summaryValFont))
			valCell.border = Rectangle.NO_BORDER
			valCell.padding = 4
			summaryTable.addCell(valCell)
		}
		summaryTable.spacingAfter = 8f
		document.add(summaryTable)

		// ===== Detail Table =====
		PdfPTable table = new PdfPTable(6)
		table.widthPercentage = 100
		table.setWidths([1.5, 2.5, 1, 2, 5, 1.5] as float[])

		Font tblHeaderFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 6, BaseColor.WHITE)
		BaseColor tblHeaderColor = new BaseColor(74, 126, 187)

		[
			"Test Case ID",
			"Test Case Name",
			"Status",
			"Execution Time",
			"Screenshot",
			"Remarks"
		].each { header ->
			PdfPCell cell = new PdfPCell(new Phrase(header, tblHeaderFont))
			cell.border = Rectangle.NO_BORDER
			cell.backgroundColor = tblHeaderColor
			cell.horizontalAlignment = Element.ALIGN_CENTER
			cell.verticalAlignment = Element.ALIGN_MIDDLE
			cell.padding = 6
			table.addCell(cell)
		}

		Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 5)
		float tableTotalRelative = 1 + 2.5 + 1 + 2 + 5 + 2
		float pageWidth = PageSize.A4.width - 36*2
		float cellWidthScreenshot = (5 / tableTotalRelative) * pageWidth
		float cellHeightScreenshot = 120f

		testCases.each { tc ->
			// ID & Name
			["id", "name"].each { key ->
				PdfPCell dataCell = new PdfPCell(new Phrase(tc.get(key)?.toString() ?: "-", dataFont))
				dataCell.border = Rectangle.NO_BORDER
				table.addCell(dataCell)
			}

			// Status
			String status = tc.get("status")?.toString() ?: "UNKNOWN"
			BaseColor statusColor = status.equalsIgnoreCase("PASS") ? new BaseColor(144,238,144) :
					status.equalsIgnoreCase("FAIL") ? new BaseColor(255,182,193) :
					new BaseColor(173,216,230)
			PdfPCell statusCell = new PdfPCell(new Phrase(status, dataFont))
			statusCell.border = Rectangle.NO_BORDER
			statusCell.backgroundColor = statusColor
			statusCell.horizontalAlignment = Element.ALIGN_CENTER
			table.addCell(statusCell)

			// Execution Time
			double seconds = 0
			try {
				def raw = tc.get("time").toString()
				seconds = raw.isNumber() ? raw.toDouble() : 0
			} catch (Exception ignored) {}
			PdfPCell timeCell = new PdfPCell(new Phrase(String.format("%.2f seconds", seconds), dataFont))
			timeCell.border = Rectangle.NO_BORDER
			timeCell.horizontalAlignment = Element.ALIGN_CENTER
			table.addCell(timeCell)

			// Screenshot
			PdfPCell imgCell = new PdfPCell()
			imgCell.border = Rectangle.NO_BORDER
			imgCell.padding = 5

			if (tc.get("screenshot") && tc.get("screenshot") instanceof List) {
				List<String> filteredScreenshots = status.equalsIgnoreCase("FAIL") ?
						(tc.get("screenshot") as List<String>).findAll { it.toLowerCase().endsWith("_fail.png") } :
						(tc.get("screenshot") as List<String>)

				filteredScreenshots.each { path ->
					try {
						Image img = Image.getInstance(path.toString())
						img.scaleToFit(cellWidthScreenshot, cellHeightScreenshot)
						img.setAlignment(Element.ALIGN_CENTER)
						imgCell.addElement(img)
						imgCell.addElement(new Chunk(" ", FontFactory.getFont(FontFactory.HELVETICA, 2f)))
					} catch (Exception e) {
						imgCell.addElement(new Phrase("Image not found: " + path, dataFont))
					}
				}
			} else {
				imgCell.addElement(new Phrase("-", dataFont))
			}
			table.addCell(imgCell)

			// Remarks
			PdfPCell remarkCell = new PdfPCell(new Phrase(tc.get("remarks")?.toString() ?: "-", dataFont))
			remarkCell.border = Rectangle.NO_BORDER
			table.addCell(remarkCell)
		}

		document.add(table)
		document.add(Chunk.NEWLINE)

		// ===== Footer =====
		// Font
		Font boldItalicFont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 6); // bold + miring
		Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 4);                  // normal

		// Buat Paragraph dengan leading kecil (jarak antar baris)
		Paragraph footer = new Paragraph();
		footer.setLeading(8f); // jarak antar baris, bisa dicoba-coba untuk kecilkan spasi
		footer.setAlignment(Element.ALIGN_LEFT);

		// Baris pertama: bold + miring
		footer.add(new Chunk("Prepared by Suhendar (NgulikSQA)\n", boldItalicFont));

		// Baris kedua: normal
		footer.add(new Chunk("SQA Engineer | Automation Testing | Reporting", normalFont));

		document.add(footer);

		document.close()
		println "PDF report generated at: ${filePath}"
	}
}
