package generating;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class TradeDateGenerator {

	private SimpleDateFormat sdf = new SimpleDateFormat();
	
	public String generateTradeDate(Calendar bench[])
	{
		//Calendar now = Calendar.getInstance();
		String pattern = "dd/MM/yyyy";
		String fd = new String();
		Random r = new Random();
		
		Calendar now = bench[0];
		now.add(Calendar.DATE, (int)(Math.floor(Math.abs(365 * 2 * r.nextFloat()))));
		now = getWorkDay(now);
		fd = getDateFormat(now, pattern);
		
		return fd;
		
	}
	
	private synchronized String getDateFormat(java.util.Calendar cal, String pattern)
	{
		return getDateFormat(cal.getTime(), pattern);
	}
	
	private synchronized String getDateFormat(java.util.Date date, String pattern)
	{
		synchronized(sdf)
		{
			String str = null;
			sdf.applyPattern(pattern);
			str = sdf.format(date);
			return str;
		}
	}
	
	private synchronized Calendar getWorkDay(java.util.Calendar gc)
	{
		switch (gc.get(Calendar.DAY_OF_WEEK))
		{
			case(Calendar.SUNDAY):
				gc.add(Calendar.DATE, 1);
			break;
			case(Calendar.SATURDAY):
				gc.add(Calendar.DATE, -1);
			break;
		}
		return gc;
	}
}
