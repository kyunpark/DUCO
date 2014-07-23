package generating;

import java.util.Calendar;
import java.util.Random;

public class CurrencyColumnGenerator implements ColumnGenerator{
	public String generateColumnValue(Calendar[] benchTime, String[] tradeDate,
			boolean[] tradeDateGenerateflag,boolean[] tradeIdExistence,int rowsNumber){
		String fd = new String();
		Random r = new Random();

		switch (Math.abs(r.nextInt() % 3)) {
		case 0:
			fd = "USD";
			break;
		case 1:
			fd = "GBP";
			break;
		case 2:
			fd = "EUR";
			break;
		}
		return fd;
	}



}
