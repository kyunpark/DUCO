package Classifier;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Dialog;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;

import javax.swing.JTextArea;

import java.io.IOException;

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



public class mainForm {

	String regexPaymentFreq = "([0-9]{0,2}[yY])|([0-9]{0,2}[mM])";
	String regexDates = "((\\d){1,4}-(\\d){1,4}-(\\d){1,4})|((\\d){1,4}/(\\d){1,4}/(\\d){1,4})";
	String regexCurrency = "[A-Z]{2}";
	String regexRate = "[1-9]{1,5}\\.[01-9]+%?";
	String regexBuySell= "([BB][Dd][Yy])|([SS][Ee][Ll][LL])";
	String regexPrice ="\"-?(\\d){6,7}(,\\c\\d\\d)*\"";
	String regexTradeID = "[a-zA-Z0-6]{5}";
	
	private JFrame frame;
	private JFileChooser fc;
	JTextArea import_path;
	JButton btnImport; 
	File file;
	URL location;
    private FileInputStream fis = null ;
	private BufferedReader reader =null;
	private FileOutputStream fos= null;
    private BufferedWriter writer= null;
    org.apache.hadoop.conf.Configuration config = HBaseConfiguration.create();
  
        
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainForm window = new mainForm();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Classifier loadClassifier(File serializedFile)	throws FileNotFoundException, IOException, ClassNotFoundException {
                                           
        // Here we load a serialized classifier from a file.                                               

        Classifier classifier;

        ObjectInputStream ois = new ObjectInputStream (new FileInputStream (serializedFile));
        classifier = (Classifier) ois.readObject();
        ois.close();

        return classifier;
	}
    
	Dataset dataForClassification = FileHandler.loadDataset(new File("devtools/data/iris.data"), 4, ",");
    
	/* Counters for correct and wrong predictions. */
    
	int correct = 0, wrong = 0;

    /* Classify all instances and check with the correct class values */

    for (Instance inst : dataForClassification) {
    Object predictedClassValue = knn.classify(inst);
    Object realClassValue = inst.classValue();
    if (predictedClassValue.equals(realClassValue))
        correct++;
    else
        wrong++;
        
        Dataset data = FileHandler.loadDataset(new File("devtools/data/iris.data"), 4, ",");
 /* Contruct a KNN classifier that uses 5 neighbors to make a
  *decision. */
Classifier knn = new KNearestNeighbors(5);
knn.buildClassifier(data);


	/**
	 * Create the application.
	 */
	public mainForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
	
		
		
	    import_path = new JTextArea();
	    import_path.setLineWrap(true);
		import_path.setBounds(188, 90, 228, 29);
		frame.getContentPane().add(import_path);
		
	    location = mainForm.class.getProtectionDomain().getCodeSource().getLocation();
		import_path.setText(location.getFile());
		
		
		
		String word = "Trade Id";
	
		import_path.setText( String.valueOf( word.matches(regexTradeID))); 
		import_path.setVisible(false);
		//import_path.setText( String.valueOf( word.substring(1, word.length()-1))); 
		
		
	    fc = new JFileChooser();
		
		btnImport = new JButton("Import");
		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(btnImport);

	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	                file = fc.getSelectedFile();
	                
	                import_path.setText(file.getPath());
	                
	          
	                
	                
	            } else {
	            	  import_path.setText("");
	            	
	            }
	         
				
				
			}
		});
		
		btnImport.setBounds(59, 90, 117, 29);
		frame.getContentPane().add(btnImport);
		
		JButton btnStart = new JButton("start classification");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					if(file != null){
						
						  fis=new FileInputStream(file.getPath());
				    	  reader= new BufferedReader(new InputStreamReader(fis));
				    	  
				    	  HTable table = new HTable(config, "duco_test");		    	  
				    	  
				    	  String[] strArray=null;
					      String ss;
					      int count =0;
				    	  
					      while((ss = reader.readLine()) != null){
				    		  //strArray = ss.split(",");
				    		  strArray= ss.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"); // http://stackoverflow.com/questions/1757065/splitting-a-comma-separated-string-but-ignoring-commas-in-quotes
				    		  
				    		  for(int i = 0; i < strArray.length; i++)
					    		    strArray[i].trim();
				    		  private static String[] CATEGORIES = { "soc.religion.christian", "talk.religion.misc", "alt.atheism", "misc.forsale" };
        
				    		  Classification classification = new Classification(CATEGORIES[i]);

				    		  Classified<CharSequence> classified = new Classified<CharSequence>(text,classification);

				    		  classifier.handle(classified);
				    		  
				    		  
				    		  ArrayList<String> dates = new ArrayList<String>();
				    		  String mPaymentFreq="";
				    		  String mCurrency="";
				    		  String mPrice="";
				    		  String mRate="";
				    		  String mBuySell="";  
				    		  String mCategory="";
				    		  String mTradeID="";
				    		  boolean isUnknown =false;
				    		  
				    		
					    		
					    		  
					    		  if(mCategory == "Futures"){
					    			  p.add(Bytes.toBytes("dates"), Bytes.toBytes("trade_date"),Bytes.toBytes(dates.get(0)));
					    			  p.add(Bytes.toBytes("dates"), Bytes.toBytes("maturity"),Bytes.toBytes(dates.get(1)));
					    		  }
					    		  else if(mCategory == "Swap"){
					    			  p.add(Bytes.toBytes("dates"), Bytes.toBytes("trade_date"),Bytes.toBytes(dates.get(0)));
					    			  p.add(Bytes.toBytes("dates"), Bytes.toBytes("maturity"),Bytes.toBytes(dates.get(1)));
					    			  p.add(Bytes.toBytes("dates"), Bytes.toBytes("payment_freq"),Bytes.toBytes(mPaymentFreq));
					    		  }
					    		  p.add(Bytes.toBytes("category"), Bytes.toBytes("type"),Bytes.toBytes(mCategory));
					    		  p.add(Bytes.toBytes("price"), Bytes.toBytes("value"),Bytes.toBytes(mPrice));
				    			  p.add(Bytes.toBytes("rate"),Bytes.toBytes("value"),Bytes.toBytes(mRate));
				    			  p.add(Bytes.toBytes("currency"),Bytes.toBytes("value"),Bytes.toBytes(mCurrency));
				    			  p.add(Bytes.toBytes("buysell"),Bytes.toBytes("value"),Bytes.toBytes(mBuySell));
				    			  p.add(Bytes.toBytes("trade_id"),Bytes.toBytes("value"),Bytes.toBytes(mTradeID));
					    		  table.put(p);
				    		  
				    		 
				    		  
				    		  
				    	
					    	  
				    		  count++;
				    		  if(count >=50){
				    			  break;
				    		  }
				    	  }
				    		   
				    	  reader.close();
				    	  
				    	  JOptionPane.showMessageDialog(frame,
				    			    "Saving the data successfully to hbase",
				    			    "Save Completed",
				    			    JOptionPane.PLAIN_MESSAGE);
					         
					         
					}
				}
				catch(Exception ex){
					
				}
				
			
			}
		});
		btnStart.setBounds(59, 148, 161, 29);
		frame.getContentPane().add(btnStart);
		
		
		
		

		JButton btnExport = new JButton("Export");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					
					 // fos=new FileOutputStream(".\\src\\Classification\\output.txt");
			    	  fos=new FileOutputStream( "output.csv");
			    	  writer= new BufferedWriter(new OutputStreamWriter(fos));    
					
			    	  HTable table = new HTable(config, "duco_test");
			    	  
			    	  String mPaymentFreq="";
		    		  String mCurrency="";
		    		  String mPrice="";
		    		  String mRate="";
		    		  String mBuySell="";  
		    		  String mCategory="";
		    		  String mTradeID="";
		    		  String mTradeDate="";
		    		  String mMaturity= "";
			    	  
			    	  Scan s = new Scan();

                      //s.addColumn(Bytes.toBytes("Id"), Bytes.toBytes("col1"));
                      //s.addColumn(Bytes.toBytes("Name"), Bytes.toBytes("col2"));
			    	  s.addFamily(Bytes.toBytes("category"));
			    	  
                      try
                      {
                         for (Result r = scanner.next(); r != null; r = scanner.next())
                         {
                             //System.out.println("Found row : " + rr);
                        	mCategory = Bytes.toString( r.getValue(Bytes.toBytes("category"), Bytes.toBytes("type")));
                        	writer.write("category:" + mCategory);
    			            writer.write(",");
    			            
    			            mTradeDate = Bytes.toString( r.getValue(Bytes.toBytes("dates"), Bytes.toBytes("trade_date")));
    			            writer.write("trade_date:" + mTradeDate);
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
                        	
  			             			                
			                mTradeID = Bytes.toString( r.getValue(Bytes.toBytes("trade_id"), Bytes.toBytes("value")));                 		 
			                writer.write("trade_id:" + mTradeID);
			          
			                writer.write("\n");
                         }
                      } finally
                      {
                             // Make sure you close your scanners when you are done!
                             scanner.close();
                             writer.close();
                      }
			    	  
				
		    		 
                      JOptionPane.showMessageDialog(frame,
			    			    "Loading the data successfully from hbase to output.csv",
			    			    "Load Completed",
			    			    JOptionPane.PLAIN_MESSAGE);
		    		  
		    		 
					
				}
				catch(Exception ex){
					
				}
			
			}
		});
		btnExport.setBounds(59, 206, 117, 29);
		frame.getContentPane().add(btnExport);
		
		
	}

}
