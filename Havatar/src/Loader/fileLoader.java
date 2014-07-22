package Loader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import setFormat.setStandardForamt
import classification.classifier

public class fileLoader {
//  read data from .csv file and then store the classified data into hbase via filter() function.
public void filter(){

  fis=new FileInputStream(file.getPath());
   reader= new BufferedReader(new InputStreamReader(fis));
				    	  
   HTable table = new HTable(config, "duco_test");		    	  
				    	  
   String[] strArray=null;
   String ss;
   int count =0;
   while((ss=reader.readLine()) != null){
				    						    		 			    		  
       Put p = new Put(Bytes.toBytes( String.valueOf(count)));
					    		  
       for(int i=0; i< strArray.length; i++){
					    			  
       if( strArray[i].matches(regexPaymentFreq) && mPaymentFreq ==""){
		       mPaymentFreq = strArray[i];				    
       }
       else if(strArray[i].matches(regexDates)){
	         dates.add(strArray[i]);	    				                 
       }
       else if(strArray[i].matches(regexCurrency) && mCurrency == ""){
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

    //export data from hbase to output.csv
    public void exportData(){
        fos=new FileOutputStream( "output.csv");
        writer= new BufferedWriter(new OutputStreamWriter(fos));    
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


}

}
