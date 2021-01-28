package org.myframework.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * ver:	1.0 
 * log:	2007-5-29
 */
public class Debug
{
	private boolean debug = true;
	
	/**
	 */
	private Map recordMap = new HashMap();
	
	/**
	 */
	private String desc;
	
	public Debug( String desc )
	{
		this.desc = desc;
	}
	
	public String getDesc()
	{
		return this.desc;
	}

	public void setDebug(boolean debug)
	{
		this.debug = debug;
	}
	
	public void setDesc(String desc)
	{
		this.desc = desc;
	}

	/**
	 *  
	 * @param action
	 */
	public void start( String action )
	{
		if( !debug ) return;
		Record record = (Record)recordMap.get(action);
		if( record == null )
		{
			record = new Record(action);
			recordMap.put( action , record );
		}
		record.setStart( System.currentTimeMillis() );
	}
	/**
	 *  
	 * @param action
	 */
	public void end( String action )
	{
		if( !debug ) return;
		Record record = (Record)recordMap.get(action);
		if( record == null )
		{
			record = new Record(action);
			recordMap.put( action , record );
		}
		record.setEnd( System.currentTimeMillis() );
	}
	
	/**
	 * 
	 * @return
	 */
	public List getRecords()
	{
		return new ArrayList( recordMap.values() );
	}
	

	public String toString()
	{
		if( !debug ) return "";
		StringBuffer buffer = new StringBuffer(512);
		buffer.append("\n------------------------------------------ Debug " + this.desc + " End ------------------------------------------ \n{");
		for( Iterator iter = recordMap.values().iterator() ; iter.hasNext();)
		{
			Record record = (Record)iter.next();
			String action = record.getAction();
			long start = record.getStart();
			long end = record.getEnd();
			buffer.append("\n	" + action + " --------------------- tooks [" + (end - start) + "] millis." );
		}
		buffer.append("\n}\n");
		return buffer.toString();
	}
	
	
	public static void main(String[] args){
		Debug debug = new Debug ("锟斤拷时锟斤拷锟斤拷");
		debug.start("abc");
		for (int i = 0 ;i<100000000 ;i ++){
			;
		}
		debug.end("abc");
    	System.out.println(debug);
	}
	
	public static class Record
	{
		String action;
		long start;
		long end;
		
		public Record(String action)
		{
			this.action = action;
		}

		public long getEnd()
		{
			return end;
		}

		public void setEnd(long end)
		{
			this.end = end;
		}

		public String getAction()
		{
			return action;
		}

		public void setAction(String action)
		{
			this.action = action;
		}

		public long getStart()
		{
			return start;
		}

		public void setStart(long start)
		{
			this.start = start;
		}
	}
}
