package generating;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class DeliveryDateColumnGenerator implements ColumnGenerator{
	private SimpleDateFormat sdf = new SimpleDateFormat();
	public String generateColumnValue(Calendar[] benchTime, String[] tradeDate,
			boolean[] tradeDateGenerateflag,boolean[] tradeIdExistence,int rowsNumber){
			//Calendar now = Calendar.getInstance();
		String fd = new String();
		if(tradeDateGenerateflag[0]==true){
			String pattern = "dd/MM/yyyy";
			
			Random r = new Random();
			//now.set(2014, 1, 1);
			// trade date ֮�� 2��5���ÿ���µĵ�3������
			Calendar now = benchTime[0];
			now.add(Calendar.DATE,
					(int) (Math.floor(2*365 + Math.abs(365*3*r.nextFloat()))));
			now = get3thursday(now);
			fd = getDateFormat(now, pattern);
		}
		else{
			new TradeDateColumnGenerator().generateColumnValue(benchTime, tradeDate, tradeDateGenerateflag,tradeIdExistence,rowsNumber);
			tradeDateGenerateflag[0]=true;
			String pattern = "dd/MM/yyyy";
			
			Random r = new Random();
			//now.set(2014, 1, 1);
			// trade date ֮�� 2��5���ÿ���µĵ�3������
			Calendar now = benchTime[0];
			now.add(Calendar.DATE,
					(int) (Math.floor(2*365 + Math.abs(365*3*r.nextFloat()))));
			now = get3thursday(now);
			fd = getDateFormat(now, pattern);
			
		}
		return fd;
	}

	// ��3������
	private synchronized Calendar get3thursday(java.util.Calendar gc) {
		/**
		 * ��ϸ��ƣ� ����Ϊ���µ�3�� ������
		 */
		//gc.get(Calendar.DAY_OF_WEEK_IN_MONTH)
		switch (gc.get(Calendar.DAY_OF_WEEK_IN_MONTH)) {
		case (1):
			gc.add(Calendar.DATE, 14);
			break;
		case (2):
			gc.add(Calendar.DATE, 7);
			break;
		case (3):
			gc.add(Calendar.DATE, 0);
			break;
		case (4):
			gc.add(Calendar.DATE, -7);
			break;
		case (5):
			gc.add(Calendar.DATE, -14);
			break;
		
		
		}
		switch (gc.get(Calendar.DAY_OF_WEEK)) {
		
		case (Calendar.SUNDAY):
			gc.add(Calendar.DATE, -3);
			break;
		case (Calendar.SATURDAY):
			gc.add(Calendar.DATE, -2);
			break;
		case (Calendar.MONDAY):
			gc.add(Calendar.DATE, 3);
			break;
		case (Calendar.TUESDAY):
			gc.add(Calendar.DATE, 2);
			break;
		case (Calendar.WEDNESDAY):
			gc.add(Calendar.DATE, 1);
			break;
		case (Calendar.FRIDAY):
			gc.add(Calendar.DATE, -1);
			break;
		
		}
		
		// return gc.getTime();
		return gc;
	}
	// �������ڵ���Ӧ��ʽ
	private synchronized String getDateFormat(java.util.Calendar cal,
			String pattern) {
		return getDateFormat(cal.getTime(), pattern);
	}

	// �������ڵ���Ӧ��ʽ
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
