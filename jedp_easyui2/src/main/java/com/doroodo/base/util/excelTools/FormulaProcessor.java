package com.doroodo.base.util.excelTools;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bsh.Interpreter;


public class FormulaProcessor {
	private static FormulaProcessor self = null;
	
	private Pattern pattern = Pattern.compile("\\$(\\d+)");
	private FormulaProcessor(){
		
	}
	
	public static FormulaProcessor getInstance(){
		if (self == null)
			self = new FormulaProcessor();
		return self;	
	}
	
	public void fillValue(TableDataRow row){
		HashSet<Integer> computed = new HashSet<Integer>();
		for (TableDataCell cell:row.getCells()){			
			TableColumn thc = row.getTable().getTableHeader().getColumnAt(cell.getColumnIndex());
			int type = thc.getColumnType();
			if (type == TableColumn.COLUMN_TYPE_FORMULA && thc.getAggregateRule() != null){
				String f = convertFormula(thc.getAggregateRule());
				try{
					Interpreter process = new Interpreter();
					process.getNameSpace().importCommands("com.eastcom.kaas.web.report.bean");					
					process.set("row", row);
					process.set("computed", computed);
					
					Object ret = process.eval(f);
					double v = (Double)ret;
					cell.setValue(v);
					computed.add(cell.getColumnIndex());
				} catch (Exception e){
					e.printStackTrace();
					cell.setValue(0);
					cell.setValue("-");
					computed.add(cell.getColumnIndex());
				}
			}
		}
	}
	
	private String convertFormula(String formula){
		Matcher m = pattern.matcher(formula);
		StringBuffer sb = new StringBuffer();
		while(m.find()){
			m.appendReplacement(sb, "getValue(row, " + m.group(1) + ")");
		}
		m.appendTail(sb);
		
		return sb.toString();
	}
}
