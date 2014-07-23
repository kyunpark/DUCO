package generating;

import java.util.Calendar;
import java.util.Random;

public class TradeIDColumnGenerator implements ColumnGenerator {
/*	// 产生m个不重复数字
	public  int[] generateColumnValue(int m, int n) {
		int[] v = new int[m];
		boolean[] b = new boolean[n];
		Random r = new Random();
		double multiPulier= (Math.pow(10, 9) - Math.pow(10, 7)) / m;
		do {
			int x = r.nextInt(n);
			if (!b[x]) {
				v[--m] = (int) (Math.abs(x * multiPulier) + (int) Math.pow(10, 7));
				b[x] = true;
			}
		} while (m > 0);
		return v;
	}*/

	@Override
	public String generateColumnValue(Calendar[] benchTime, String[] tradeDate,
			boolean[] tradeDateGenerateflag,boolean[] tradeIdExistence,int rowsNumber) {
		// TODO Auto-generated method stub
		String fd  =new String("Trade ID");
		int v=0;
		Random r = new Random();
		double multiPulier= (Math.pow(10, 9) - Math.pow(10, 7)) / rowsNumber;
		boolean createSuccess=false;
		do {
			int x=r.nextInt(rowsNumber);
			if(!tradeIdExistence[x]){
				v= (int) (Math.abs(x * multiPulier) + (int) Math.pow(10, 7));
				tradeIdExistence[x]=true;
				createSuccess=true;
			}
		}while(createSuccess==false);
		fd=String.valueOf(v);
		return fd;
	}






}
