package generating;

import java.util.Calendar;
import java.util.Random;

public class FloatingRateColumnGenerator implements ColumnGenerator{
	public String generateColumnValue(Calendar[] benchTime, String[] tradeDate,
			boolean[] tradeDateGenerateflag,boolean[] tradeIdExistence,int rowsNumber){
		String fd = new String();
		Random r = new Random();
		// +1.00 µ½ +1.99Ö®¼ä
		float rfr = 1 + r.nextFloat();
		rfr = (float) Math.round(rfr * 100) / 100;
		fd = String.valueOf(rfr);
		fd = "+"+fd;
		return fd;
	}


}
