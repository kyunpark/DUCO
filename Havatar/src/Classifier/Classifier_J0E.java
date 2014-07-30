package Classifier;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader; 
import java.io.FileWriter; 
import java.io.IOException; 
 
public class Classifier_J0E{ 

		String regexDates = "((\\d){1,4}-(\\d){1,4}-(\\d){1,4})|((\\d){1,4}/(\\d){1,4}/(\\d){1,4})";
		String regexPaymentFreq = "([1-9]{1,2}[yY])|([1-9]{1,2}[mM])";
		String regexCurrency = "[A-Z]{3}";
		String regexPrice = "[1-9]{1,10}";
		String regexNotional = "([1-9]{1,10})|([1-9]{1,3}\\,[1-9]{3}\\,[1-9]{3})";
		String regexBuySell= "([BB][Uu][Yy])|([SS][Ee][Ll][Ll])|([Bb])|(Ss)";
		String regexRate = "[1-9]{1,3}\\.[01-9]+%?";
		String regexUnitOfTrading = "[1-9]{1,3}\\,[1-9]{3}\\,[1-9]{3}";
		String regexContractSize = "[1-9]{1,3}\\.[0]+%?";
		String regexPartiesNames = "[Aa-Zz]{1,20}"
				
		private void readFileFromHBase() {
			try { 
				File csv = new File("E:/Dropbox/UCL/Intern/DATA/10000Futures.csv");
				BufferedReader br = new BufferedReader(new FileReader(csv));
	            br.close();
	        } catch (FileNotFoundException e) { 
	            e.printStackTrace(); 
	        }
		}

		try{
			if(file != null){
				HTable table = new HTable(config, "duco_test");		    	  
			 	String[] strArray=null;
			 	int count =0;
				while((ss=reader.readLine()) != null){
				strArray= ss.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"); 
				for(int i=0; i< strArray.length; i++){
				strArray[i].trim();
				ArrayList<String> dates = new ArrayList<String>();
				String mPaymentFreq="";
				String mCurrency="";
				String mPrice="";
				String mRate="";
				String mBuySell="";  
				String mCategory="";
				boolean isUnknown =false;
						    		  
				Put p = new Put(Bytes.toBytes( String.valueOf(count)));
			 	for(int i=0; i< strArray.length; i++){
			 		if( strArray[i].matches(regexPaymentFreq) && mPaymentFreq ==""){
			 			mPaymentFreq = strArray[i];				    
			 		}else if(strArray[i].matches(regexDates)){
			 			dates.add(strArray[i]);
			 		}else if(strArray[i].matches(regexCurrency) && mCurrency == ""){
			 			mCurrency = strArray[i];  
					} else if (strArray[i].matches(regexPrice) && mPrice == ""){
						mPrice = strArray[i].substring(1, strArray[i].length()-1);
					}else if (strArray[i].matches(regexRate) && mRate == ""){
						mRate = strArray[i];
					}else if(strArray[i].matches(regexBuySell) && mBuySell == ""){
						mBuySell = strArray[i];
					}else if(strArray[i].matches(regexTradeID) && mTradeID == ""){
			 			mTradeID = strArray[i];  
					}else{
						isUnknown =true;
					}
				}	   
			 	reader.close();		         
				}
			 }
			 catch(Exception ex){
			 }
		}
		
		public class writeHTable {
			public static void main(String[] args) { 
				try { 
					for (Result r = scanner.next(); r != null; r = scanner.next()){
						mCategory = Bytes.toString( r.getValue(Bytes.toBytes("category"), Bytes.toBytes("type")));
						writer.write("category:" + mCategory);
						writer.write(",");
						
						mTradeDate = Bytes.toString( r.getValue(Bytes.toBytes("dates"), Bytes.toBytes("trade_date")));
						writer.write(",");
						
						mMaturity=Bytes.toString( r.getValue(Bytes.toBytes("dates"), Bytes.toBytes("maturity")));                 		 
						writer.write("maturity:" + mMaturity);
						writer.write(",");
						
						if(mCategory.equals("Swap") ){
							mPaymentFreq =  Bytes.toString( r.getValue(Bytes.toBytes("dates"), Bytes.toBytes("payment_freq")));
							writer.write("payment_freq:" + mPaymentFreq);
							writer.write(",");
						}
						
						mPrice = Bytes.toString( r.getValue(Bytes.toBytes("price"), Bytes.toBytes("value")));                 		 
						writer.write("price:" + mPrice.replaceAll(",", ""));
						writer.write(",");
						
						mBuySell=Bytes.toString( r.getValue(Bytes.toBytes("buysell"), Bytes.toBytes("value")));                 		 
						writer.write("buysell:" + mBuySell);
						writer.write(",");
						
						mCurrency = Bytes.toString( r.getValue(Bytes.toBytes("currency"), Bytes.toBytes("value")));                 		 
						writer.write("currency:" + mCurrency);
						writer.write(",");
			   			            
						mRate = Bytes.toString( r.getValue(Bytes.toBytes("rate"), Bytes.toBytes("value")));                 		 
						writer.write("rate:" + mRate);
						writer.write(",");
					}
				} catch (IOException e) { 
				} 
			} 
		}
