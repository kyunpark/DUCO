package generating;

import java.util.Calendar;
import java.util.Random;

public class ContractSizeColumnGenerator implements ColumnGenerator{
	public String generateColumnValue(Calendar[] benchTime, String[] tradeDate,
			boolean[] tradeDateGenerateflag,boolean[] tradeIdExistence,int rowsNumber){
		String fd = new String();
		Random r = new Random();
		//10到300自然数
		float rfr = 10 + Math.round(290 * r.nextFloat());
		//rfr = (float) Math.round(rfr * 100) / 100;
		fd = String.valueOf(rfr);
		return fd;
	}


}
