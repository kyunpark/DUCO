package generating;

import java.util.Random;

public class TradeIDColumnGenerator {

	public int[] generateTradeID(int m, int n)
	{
		int[] v = new int[m];
		boolean[] b = new boolean[n];
		Random r = new Random();
		double multiP = (Math.pow(10, 9) - Math.pow(10, 7)) / m;
		
		do
		{
			int x = r.nextInt(n);
			if (!b[x])
			{
				v[--m] = (int) (Math.abs(x * multiP) + (int) Math.pow(10, 7));
				b[x] = true;
			}
		}
		while (m > 0);
		return v;
	}
	
}
