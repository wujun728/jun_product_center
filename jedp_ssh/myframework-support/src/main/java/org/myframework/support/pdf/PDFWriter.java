package org.myframework.support.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.myframework.commons.util.StringUtils;
import org.myframework.support.csv.parser.AbstractParser;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFWriter extends AbstractParser {

	public PDFWriter() {
		super("PdfWriter");
	}

	@Override
	public void processRows(File input) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String[]> parseRows(File input) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public File writeRows(List<Object[]> rows) {
		File tmpFile;
		try {
			tmpFile = File.createTempFile(UUID.randomUUID().toString(), ".pdf");
			writeRows(rows, tmpFile.getAbsolutePath());
		} catch (IOException e) {
			logger.error(e.getMessage());
			return null;
		}
		return tmpFile;
	}
	@Override
	public File writeRows(List<Object[]> rows, String filepath) {
		File pdffile = new File(filepath);
		try {
			writeRows(rows,pdffile);
		} catch (Exception e) {

		}
		return pdffile;
	}


	public void writeRows(List<Object[]> rows ,File file) throws Exception  {
		// 1.建立com.lowagie.text.Document对象的实例。
		Document document = new Document();
		BaseFont bfChinese = BaseFont.createFont("STSong-Light",
                "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font fontChinese = new Font(bfChinese, 12, Font.NORMAL);
		// 2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
		PdfWriter.getInstance(document, new FileOutputStream(file));
		// 3.打开文档。
		document.open();

		// 4.
		PdfPTable table =null;
		for (int i = 0; i < rows.size(); i++) {
			if(i==0) {
				table = new PdfPTable(rows.get(0).length);
			}
			Object[] row = rows.get(i);
			for (Object cell : row) {
				table.addCell(new Phrase(StringUtils.asString(cell),fontChinese));
			}
		}
		table = ( table==null )? createEmptyTable() : table;

		document.add(table);

		// 5.
		document.close();


	}

	protected PdfPTable createEmptyTable() throws DocumentException, IOException {
		BaseFont bfChinese = BaseFont.createFont("STSong-Light",
                "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font fontChinese = new Font(bfChinese, 12, Font.NORMAL);
		PdfPTable table = new PdfPTable(1);
		table.addCell( new Phrase("NO DATA" ,fontChinese ));
		return table ;
	}

	public static void main(String[] args) throws IOException {
		List<Object[]> rows = Arrays
				.asList(new Object[][] {
						{ "1997", "Ford", "E350", "ac, abs, moon", "3000.00" },
						{ "1999", "Chevy", "Venture \"Extended Edition\"", "",
								"4900.00" },
						{ "1996", "Jeep", "Grand中文 Cherokee",
								"MUST SELL!\nair, moon roof, loaded", "4799.00" },
						{},
						{ "1999", "Chevy",
								"Venture \"Extended Edition, Very Large\"",
								null, "5000.00" },
						{ null, "", "Venture \"Extended Edition\"", null,
								"4900.00" }, });
		PDFWriter PDFWriter = new PDFWriter();
		File file = PDFWriter.writeRows(rows);
		System.out.println(file.getAbsolutePath());
	}
}
