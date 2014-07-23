package generating;

import java.util.Calendar;
import java.util.Random;

public class BuyorSellColumnGenerator implements ColumnGenerator{
	public String generateColumnValue(Calendar[] benchTime, String[] tradeDate,
			boolean[] tradeDateGenerateflag,boolean[] tradeIdExistence,int rowsNumber){
		String fd = new String();
		Random r = new Random();
		int radomint = r.nextInt();
		if (radomint > 0)
			fd = "buy";
		else
			fd = "sell";
		return fd;
	}



}
