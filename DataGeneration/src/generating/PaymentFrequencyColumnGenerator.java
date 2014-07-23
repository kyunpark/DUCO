package generating;

import java.util.Calendar;
import java.util.Random;

public class PaymentFrequencyColumnGenerator implements ColumnGenerator{
	public String generateColumnValue(Calendar[] benchTime, String[] tradeDate,
			boolean[] tradeDateGenerateflag,boolean[] tradeIdExistence,int rowsNumber){
		String fd = new String();
		Random r = new Random();
		switch (Math.abs(r.nextInt() % 4)) {
		case 0:
			fd = "1M";
			break;
		case 1:
			fd = "3M";
			break;
		case 2:
			fd = "6M";
			break;
		case 3:
			fd = "1Y";
			break;
		}
		return fd;
	}



}
