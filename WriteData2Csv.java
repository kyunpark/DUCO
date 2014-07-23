package generating;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



public class WriteData2Csv {
	public SimpleDateFormat sdf = new SimpleDateFormat();
	// 生成m行数据,保存到outPutPath/filename文件中
	public File generateData(String outPutPath, String filename, int rowsNumber,String typename) {
		File csvFile = null;
		BufferedWriter csvFileOutputStream = null;
		try {
			csvFile = new File(outPutPath + filename + ".csv");
			File parent = csvFile.getParentFile();
			if (parent != null && !parent.exists()) {
				parent.mkdirs();
			}
			csvFile.createNewFile();
			
            // 生成文件标题，顺序
			ColumnTitleGenerator ctg=new ColumnTitleGenerator();
			String[] titleArray = new String[50];  
			int [] titleOrder = new int[50];
			int [] numberOfColumn = new int[1]; // 标题的数量
			ctg.generateColumnValueArray(typename, titleArray, titleOrder, numberOfColumn);
			// GB2312使正确读取分隔符","
			csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(csvFile), "GB2312"), 1024);
			// 写入文件头部
			 System.out.println(numberOfColumn[0]);
			 String tos = new String();
			 tos="";
			 String row = new String();
			for (int i=0;i<numberOfColumn[0];i++) {
				
				csvFileOutputStream.write(titleArray[i]);
				row += "\"" + titleArray[i] + "\",";
				if(typename.equals("swap")==true)
                tos += titleOrder[i]+",";
				else if(typename.equals("future")==true)
					tos += titleOrder[i]-12+",";
				if (i<numberOfColumn[0]) {
					csvFileOutputStream.write(",");
				}
			}
			System.out.println(tos);
			System.out.println("has generated 1th row data：" + row);

            /*	"Trade ID", "Trade date", "Effective date",
			"Termination date", "Payment frequency", "Notional",
			"Currency", "Fixed rate", "Floating rate",
			"Seller and buyer parties", "Buy or sell", "Contract price"
			
			"trade date", "delivery date",
			"unit of trading", "contract price", "contract size",
			"currency", "interest rate", "buy/sell", "trade id" */
			List<ColumnGenerator>   columnGeneratorList = new ArrayList<ColumnGenerator>();
			columnGeneratorList.add(new TradeIDColumnGenerator());//0
			columnGeneratorList.add(new TradeDateColumnGenerator());//1
			columnGeneratorList.add(new EffectiveDateColumnGenerator());//2
			columnGeneratorList.add(new TerminationDateColumnGenerator());//3
			columnGeneratorList.add(new PaymentFrequencyColumnGenerator());//4
			columnGeneratorList.add(new NotionalColumnGenerator());//5
			columnGeneratorList.add(new CurrencyColumnGenerator());//6
			columnGeneratorList.add(new FixedRateColumnGenerator());//7
			columnGeneratorList.add(new FloatingRateColumnGenerator());//8
			columnGeneratorList.add(new SellerAndBuyerPartiesColumnGenerator());//9
			columnGeneratorList.add(new BuyorSellColumnGenerator());//10
			columnGeneratorList.add(new ContractPriceColumnGenerator());//11
			columnGeneratorList.add(new TradeDateColumnGenerator());//12
			columnGeneratorList.add(new DeliveryDateColumnGenerator());//13
			columnGeneratorList.add(new UnitOfTradingColumnGenerator());//14
			columnGeneratorList.add(new ContractPriceColumnGenerator());//15
			columnGeneratorList.add(new ContractSizeColumnGenerator());//16
			columnGeneratorList.add(new CurrencyColumnGenerator());//17
			columnGeneratorList.add(new InterestRateColumnGenerator());//18
			columnGeneratorList.add(new BuyorSellColumnGenerator());//19
			columnGeneratorList.add(new TradeIDColumnGenerator());//20
			
			// 生成数据并，写入文件内容
			
			Calendar [] benchTime = new Calendar[1];
			boolean[] tradeDateGenerateflag=new boolean[1];
			String[] tradeDate = new String[1];
			boolean[] tradeIdExistence = new boolean[rowsNumber];
			float rowsCount = 1;
			String fd = new String();
			
			for (int ii = 0; ii < rowsNumber; ii++) {
				csvFileOutputStream.newLine();
				
				row = "";
				benchTime[0]=Calendar.getInstance();
				tradeDateGenerateflag[0]=false;
				tradeDate[0]="tradeDate";
				
				for (int i=0;i<numberOfColumn[0];i++) 
				{
					
					fd=columnGeneratorList.get(titleOrder[i]-1).generateColumnValue(benchTime,tradeDate,tradeDateGenerateflag,tradeIdExistence,rowsNumber);		
					
					if (fd.indexOf(",") == -1)
						csvFileOutputStream.write(fd);
					else
						csvFileOutputStream.write("\"" + fd + "\"");
					if (i<numberOfColumn[0]) {
						csvFileOutputStream.write(",");
					}
					row += "\"" + fd + "\"";
				    fd="fd";
				}
				

				rowsCount = ii + 1;
				//System.out.println("已生成第" + (int) r0 + "行数据：" + row);
				System.out.println("has generated " + (int) rowsCount + "th row data：" + row);
				
			}
			csvFileOutputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				csvFileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return csvFile;
	}
}
