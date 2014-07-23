package generating;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
//import java.util.Calendar;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class SellerAndBuyerPartiesColumnGenerator implements ColumnGenerator{

	public String generateColumnValue(Calendar[] benchTime, String[] tradeDate,
			boolean[] tradeDateGenerateflag,boolean[] tradeIdExistence,int rowsNumber){
		
		String fd = new String();
		Random r = new Random();
		
		int order=Math.round(1993*Math.abs(r.nextFloat()));
		String[] parties = new String[1993];
		String filepath="C:\\Users\\wenhao shen\\Desktop\\project\\swap_demo_data_13_scenario_01\\swap_demo_data_internal_13.csv";
		int col=15;
	
		readCsvColumn(filepath,col,parties);
		fd = parties[order];
		return fd;
	}
	private void readCsvColumn(String filepath, int col,String[] rowdata) {
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(filepath));
			String line = null;
			int index = 0;
			int ii;
			while ((line = reader.readLine()) != null) {
			
					String[] stra = line.split("\"");
					int i = 0;
					String[] temp;
					List<String> result = new ArrayList<String>();
					for (String s : stra) {
						// 如果是""内的字符串则直接写入到结果集中，否则分隔,号，获取子字符串

						if (i % 2 == 0) // 不在引号内
						{
							temp = s.split(",");
							if (temp.length > 0) {
								if (i < 2) {
									for (String ts : temp)
										result.add(ts);
								} else {
									int its = 0;
									for (String ts : temp) {
										if (its > 0)
											result.add(ts);
										its++;
									}
								}
							}
						} else // 在引号内
						{
							result.add(s);
						}

						i++;
					}

					ii = 0;
					for (String s : result) {
						//coldata[ii] = s;
						if(ii==col-1)
						{
							if(index>0)
								rowdata[index-1]=s;
							
						}
						ii++;
					}

				index++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
