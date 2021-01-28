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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AbstractParser {

	protected Log logger =  LogFactory.getLog(this.getClass());

	private final String name;
	private int rowCount;
	private int blackhole; // something to keep values from processed objects to
							// avoid unwanted JIT's dead code removal

	protected String encoding = "GBK";

	protected AbstractParser(String name) {
		this.name = name;
	}

	public final String getName() {
		return name;
	}

	protected boolean process(Object row) {
		if (row == null) {
			return false;
		}

		blackhole += System.identityHashCode(row);
		rowCount++;
		return true;
	}

	public void resetRowCount() {
		rowCount = 0;
	}

	public int getRowCount() {
		return rowCount;
	}

	public String getBlackhole() {
		return String.valueOf(blackhole);
	}

	protected Reader toReader(File input) {
		try {

			return new FileReader(input);
		} catch (FileNotFoundException e) {
			throw new IllegalStateException(e);
		}
	}

	protected Reader toReader(File input, String encoding) {
		try {
			return new InputStreamReader(new FileInputStream(input),encoding);
		} catch (FileNotFoundException e) {
			throw new IllegalStateException(e);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
	}

	protected Writer toWriter(File out) {
		try {
			return new FileWriter(out);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	protected Writer toWriter(File out, String encoding) {
		try {
			return new OutputStreamWriter(new FileOutputStream(out,true),encoding);
		} catch (FileNotFoundException e) {
			throw new IllegalStateException(e);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public abstract void processRows(File input);

	/**
	 * 读取CSV文件内容到list数组
	 * @param input
	 * @return
	 */
	public abstract List<String[]> parseRows(File input);

	/**
	 * 将数据写入临时文件
	 * @param input
	 * @return
	 */
	public abstract File writeRows(List<Object[]> rows);

	/**
	 * 将数据写入指定文件
	 * @param input
	 * @param filepath
	 * @return
	 */
	public abstract File writeRows(List<Object[]> rows,String filepath) ;

}
