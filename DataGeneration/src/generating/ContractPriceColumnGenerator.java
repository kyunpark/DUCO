package generating;

import java.util.Calendar;
import java.util.Random;

public class ContractPriceColumnGenerator implements ColumnGenerator{
	public String generateColumnValue(Calendar[] benchTime, String[] tradeDate,
			boolean[] tradeDateGenerateflag,boolean[] tradeIdExistence,int rowsNumber){
		String fd = new String();
		Random r = new Random();
		int iii = (int) (((int) Math.round(r.nextInt() / 100000)) * Math
				.pow(10, 2));
		fd = String.valueOf(Math.abs(iii));
	
		if (fd.length() > 4)
			fd = Stringinsert(fd, ",", fd.length() - 4);
		if (fd.length() > 9)
			fd = Stringinsert(fd, ",", fd.length() - 8);
		if (fd.length() > 14)
			fd = Stringinsert(fd, ",", fd.length() - 13);
		return fd;
	}
	// ���ַ���a��tλ�ô������ַ���b
	private String Stringinsert(String a, String b, int t) {
		return a.substring(0, t) + b + a.substring(t + 1, a.length());
	}

}
