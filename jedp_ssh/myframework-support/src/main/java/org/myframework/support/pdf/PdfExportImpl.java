package org.myframework.support.pdf;

import org.myframework.support.csv.impl.CsvExportImpl;
import org.myframework.support.csv.parser.AbstractParser;

public class PdfExportImpl extends CsvExportImpl implements PdfExport{
	AbstractParser parser = new PDFWriter();

	public AbstractParser getParser() {
		return parser;
	}

	public void setParser(AbstractParser parser) {
		super.setParser(parser);
	}

}
