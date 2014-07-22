
public class fileExport {
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
