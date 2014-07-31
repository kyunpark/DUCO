package Loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

public class fileLoader {
//  read data from .csv file and then store the classified data into hbase via filter() function.
	public void filter(){

		fis = new FileInputStream(File.getPath());
		reader = new BufferedReader(new InputStreamReader(fis));
				    	  
		HTable table = new HTable(config, "duco_test");		    	  
				    	  
		String[] strArray=null;
		String ss;
		int count = 0;

		while((ss = reader.readLine()) != null)
		{       
			Put p = new Put(Bytes.toBytes( String.valueOf(count)));
					    		  
			for(int i=0; i< strArray.length; i++)
			{
				if( strArray[i].matches(regexPaymentFreq) && mPaymentFreq =="")
				{		       
					mPaymentFreq = strArray[i];				    
				}
       
				else if(strArray[i].matches(regexDates))
				{	         
					dates.add(strArray[i]);	    				                 
				}
       
				else if(strArray[i].matches(regexCurrency) && mCurrency == "")
				{	        	
					mCurrency = strArray[i];  
				} 
				 
				.               
				.                              
				.	    								    		
				p.add(Bytes.toBytes("dates"),Bytes.toBytes("trade_date"),Bytes.toBytes(dates.get(0)));   
	   
				p.add(Bytes.toBytes("dates"),Bytes.toBytes("maturity"),Bytes.toBytes(dates.get(1)));
       
				p.add(Bytes.toBytes("dates"),Bytes.toBytes("payment_freq"),Bytes.toBytes(mPaymentFreq)
       
				p.add(Bytes.toBytes("category"), Bytes.toBytes("type"),Bytes.toBytes(mCategory));
       
				p.add(Bytes.toBytes("price"), Bytes.toBytes("value"),Bytes.toBytes(mPrice));                
				.                              
				.                              
				.
				       
				table.put(p);	    		  
	     
				reader.close();
				    	  
			}

		}

	}

}
