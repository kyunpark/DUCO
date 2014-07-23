package generating;

import java.util.Calendar;
import java.util.Random;

public class InterestRateColumnGenerator implements ColumnGenerator{
	public String generateColumnValue(Calendar[] benchTime, String[] tradeDate,
			boolean[] tradeDateGenerateflag,boolean[] tradeIdExistence,int rowsNumber){
		String fd = new String();
		Random r = new Random();
		float rfr = 3 + 2 * r.nextFloat();
		rfr = (float) Math.round(rfr * 100) / 100;
		fd = String.valueOf(rfr);
		return fd;
	}



}
