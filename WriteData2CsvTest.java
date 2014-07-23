package generating;

//import static org.junit.Assert.*;

import org.junit.Test;

public class WriteData2CsvTest {


	//genarateData(String outPutPath, String filename, int m,String typename)
	@Test
	public void testGenerateData() {
		String outPutPath = "D:\\javaProgram\\HbaseClient\\data\\";
		int m=500;
		
		//String typename="swap";
		String typename="future";
		String filename =m+"_rows_"+typename;
		
		new WriteData2Csv().generateData(outPutPath, filename, m,typename);
	}
}
