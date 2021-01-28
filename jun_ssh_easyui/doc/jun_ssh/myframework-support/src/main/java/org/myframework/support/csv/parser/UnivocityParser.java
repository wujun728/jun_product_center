/*******************************************************************************
 * Copyright 2014 uniVocity Software Pty Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.myframework.support.csv.parser;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.univocity.parsers.common.ParsingContext;
import com.univocity.parsers.common.processor.AbstractRowProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;

public class UnivocityParser extends AbstractParser {

	public UnivocityParser() {
		super("uniVocity CSV parser");
	}

	@Override
	public void processRows(File input) {
		CsvParserSettings settings = new CsvParserSettings();

		// turning off features enabled by default
		settings.setIgnoreLeadingWhitespaces(false);
		settings.setIgnoreTrailingWhitespaces(false);
		settings.setSkipEmptyLines(false);
		settings.setColumnReorderingEnabled(false);
		settings.setRowProcessor(new AbstractRowProcessor() {
			@Override
			public void rowProcessed(String[] row, ParsingContext context) {
				process(row);
			}
		});

		CsvParser parser = new CsvParser(settings);
		parser.parse(toReader(input,encoding));
	}

	@Override
	public List<String[]> parseRows(File input) {
		CsvParserSettings settings = new CsvParserSettings();
		CsvParser parser = new CsvParser(settings);
		return parser.parseAll(toReader(input,encoding));
	}

	@Override
	public File writeRows(List<Object[]> rows) {
		File tmpFile ;
		try {
			tmpFile = File.createTempFile(UUID.randomUUID().toString(), ".csv");
			writeRows(rows,tmpFile.getAbsolutePath());
		} catch (IOException e) {
			logger.error(e.getMessage());
			return null;
		}
		return tmpFile;
	}

	@Override
	public File writeRows(List<Object[]> rows, String filepath) {
		logger.debug("写入数据到"+filepath);
		File csvfile = new File(filepath); 
		Writer outputWriter = toWriter(csvfile,encoding);
		CsvWriter writer = new CsvWriter(outputWriter, new CsvWriterSettings());
		writer.writeRowsAndClose(rows);
		return csvfile;
	}


	public static void main(String[] args) throws IOException {
		List<Object[]> rows = Arrays
				.asList(new Object[][] {
						{ "1997", "Ford", "E350", "ac, abs, moon", "3000.00" },
						{ "1999", "Chevy", "Venture \"Extended Edition\"", "",
								"4900.00" },
						{ "1996", "Jeep", "Grand Cherokee",
								"MUST SELL!\nair, moon roof, loaded", "4799.00" },
						{},
						{ "1999", "Chevy",
								"Venture \"Extended Edition, Very Large\"",
								null, "5000.00" },
						{ null, "", "Venture中文 \"Extended Edition\"", null,
								"4900.00" }, });
		UnivocityParser UnivocityParser = new UnivocityParser();
		UnivocityParser.writeRows(rows);
 		UnivocityParser.writeRows(rows,"d://efg1.csv");
//		byte[] BOM = new byte[] { (byte)0xEF, (byte)0xBB, (byte)0xBF };
//		File file =  new File("d://efg1.csv");
//
//		List<String[]> rowStrings = UnivocityParser.parseRows(file);
//		for (String[] strings : rowStrings) {
//			System.out.println(Arrays.asList(strings));
//		}

	}


}
