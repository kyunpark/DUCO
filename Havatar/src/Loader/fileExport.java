package Loader;

import java.io.BufferedWriter;
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

public class fileExport {
	
	public void exportData()
	{	        
		fos = new FileOutputStream("output.csv");	        
		writer = new BufferedWriter(new OutputStreamWriter(fos));    
	    HTable table = new HTable(config, "duco_test");
				    	  
	    String mPaymentFreq="";
	    String mCurrency="";
	    String mPrice="";
	    String mRate="";
	       .
	       .          
	       .
				    	  
	    Scan s = new Scan();
	    s.addFamily(Bytes.toBytes("category"));
	    s.addFamily(Bytes.toBytes("dates"));
	    s.addFamily(Bytes.toBytes("price"));
	       .
	       .
	       .			    	  
	    ResultScanner scanner = table.getScanner(s);
	    try	        
	    {	            
	    	for (Result r = scanner.next(); r != null; r = scanner.next())	            
	    	{		              
	    		mCategory = Bytes.toString( r.getValue(Bytes.toBytes("category"), Bytes.toBytes("type")));
	            writer.write("category:" + mCategory);
	            writer.write(",");
	    			            
	            mTradeDate = Bytes.toString( r.getValue(Bytes.toBytes("dates"), Bytes.toBytes("trade_date")));
	    	    writer.w rite("trade_date:" + mTradeDate);
	            writer.write(",");
	  			        . 
	                    .
	                    .                	
	  			
	        }
	    }
	    
	     scanner.close();
	     writer.close();
	}                
}
