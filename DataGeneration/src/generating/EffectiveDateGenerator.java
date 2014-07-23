package generating;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EffectiveDateGenerator {
	
	private SimpleDateFormat sdf = new SimpleDateFormat;
	
	public String generateEffectiveDate(Calendar bench[])
	{
		String pattern = "dd/MM/yyyy";
		String fd = new String();
		
		Calendar now = bench[0];
		TradeDateGenerator tdg = new TradeDateGenerator();
		int td = parseInt(tdg.generateTradeDate(bench), 10);
		
		now.add(Calendar.DATE, (int)(Math.floor(td) + 5));
		now = getWorkDay(now);
		fd = getDateFormat(now, pattern);
		
	}
	
	private int parseInt(String generateTradeDate, int i) {
		// TODO Auto-generated method stub
		return 0;
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
