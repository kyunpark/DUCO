package generating;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class EffectiveDateColumnGenerator implements ColumnGenerator{
	private SimpleDateFormat sdf = new SimpleDateFormat();
	public String generateColumnValue(Calendar[] benchTime, String[] tradeDate,
			boolean[] tradeDateGenerateflag,boolean[] tradeIdExistence,int rowsNumber){
		String fd = new String();
		if(tradeDateGenerateflag[0]==true){
			Calendar now = benchTime[0];
			String pattern = "dd/MM/yyyy";
			Random r = new Random();
			//now.set(2014, 1, 1);
			// trade date 之后 3到20个工作日
			now.add(Calendar.DATE,
					(int) (Math.floor(Math.abs(3 + 17*r.nextFloat()))));
			now = getWorkDay(now);
			fd = getDateFormat(now, pattern);
		}
		else{
			new TradeDateColumnGenerator().generateColumnValue(benchTime, tradeDate, tradeDateGenerateflag,tradeIdExistence,rowsNumber);
			tradeDateGenerateflag[0]=true;
			Calendar now = benchTime[0];
			String pattern = "dd/MM/yyyy";
			Random r = new Random();
			//now.set(2014, 1, 1);
			// trade date 之后 3到20个工作日
			now.add(Calendar.DATE,
					(int) (Math.floor(Math.abs(3 + 17*r.nextFloat()))));
			now = getWorkDay(now);
			fd = getDateFormat(now, pattern);
			
		}
		
		return fd;
	}
	// 周末变为工作日
	private synchronized Calendar getWorkDay(java.util.Calendar gc) {
		/**
		 * 详细设计： 1.如果date是星期日，则加1天 2.如果date是星期六，则减1天 3.否则减1天
		 */
		switch (gc.get(Calendar.DAY_OF_WEEK)) {
		case (Calendar.SUNDAY):
			gc.add(Calendar.DATE, 1);
			break;
		case (Calendar.SATURDAY):
			gc.add(Calendar.DATE, -1);
			break;
		}
		// return gc.getTime();
		return gc;
	}
	// 返回日期的相应格式
	private synchronized String getDateFormat(java.util.Calendar cal,
			String pattern) {
		return getDateFormat(cal.getTime(), pattern);
	}

	// 返回日期的相应格式
	private synchronized String getDateFormat(java.util.Date date,
			String pattern) {
		synchronized (sdf) {
			String str = null;
			sdf.applyPattern(pattern);
			str = sdf.format(date);
			return str;
		}
	}



}
