package generating;

import java.util.Random;

public class ColumnTitleGenerator {
	public void generateColumnValueArray(String typename,String [] titleArray,int [] titleOrder,int [] numberOfColumn) {
		//String[] titleArray = new String[20];
		Random random = new Random(); 
/*		int remainder=random.nextInt()%3;
		//String[] swapTitleArray = new String [];
		switch(remainder)
		{
		case(0):

			break;
		case(1):

			break;
		case(2):
			break;
			
		
		}*/
		String[] swapTitleArray = { "Trade ID", "Trade date", "Effective date",
				"Termination date", "Payment frequency", "Notional",
				"Currency", "Fixed rate", "Floating rate",
				"Seller and buyer parties", "Buy or sell", "Contract price" };
		String[] futureTitleArray = { "Trade Date", "Delivery Date",
				"Unit of Trading", "Contract Price", "Contract Size",
				"Currency", "Interest Rate", "Buy/Sell", "Trade ID" };
		
		if(typename.equals("swap")==true){
			int m = swapTitleArray.length;
			numberOfColumn[0]=m;
			System.out.println(m);
			int [] temp = new int[m];
			temp= generateNoRepeatArray(m, m);
			for(int i=0;i<m;i++)
				titleOrder[i]=temp[i];
			for(int i=0;i<m;i++)
				titleArray[i]=swapTitleArray[titleOrder[i]-1];
			
			// 随机列名
			//Random random = new Random(); 
			int numberOfRandomTitle=random.nextInt(m);
			for(int i=0;i<numberOfRandomTitle;i++){
				int randomOrder = random.nextInt(m);
			    titleArray[randomOrder]=getRandomString(random.nextInt(6)+1);
			}
			
			// 随机去掉 某列 
			randomRemoveColumn(1,titleArray,titleOrder,numberOfColumn);
			randomRemoveColumn(12,titleArray,titleOrder,numberOfColumn);
			
			
			
			
			
		}
		else if(typename.equals("future")==true){
			int m = futureTitleArray.length;
			numberOfColumn[0]=m;
			System.out.println(m);
			int [] temp = new int[m];
			temp= generateNoRepeatArray(m, m);
			for(int i=0;i<m;i++)
				titleOrder[i]=temp[i];
			for(int i=0;i<m;i++)
				titleArray[i]=futureTitleArray[titleOrder[i]-1];
			for(int i=0;i<m;i++)
				titleOrder[i]=temp[i]+12;
			// 随机列名
			//Random random = new Random(); 
			int numberOfRandomTitle=random.nextInt(m);
			for(int i=0;i<numberOfRandomTitle;i++){
				int randomOrder = random.nextInt(m);
			    titleArray[randomOrder]=getRandomString(random.nextInt(1));
			}
			randomRemoveColumn(9+12,titleArray,titleOrder,numberOfColumn);
		}
		else{
		   System.out.println("error: make the typename = swap or future!");
		}
		 //System.out.println(titleArray);
		
	}
	// 产生m个不重复数字
	public  int[] generateNoRepeatArray(int m, int n) {
		int[] v = new int[m];
		boolean[] b = new boolean[n];
		Random r = new Random();
		do {
			int x = 1+Math.round((n-1)*r.nextFloat());
			if (!b[x-1]) {
				v[m-1] = x;
				m--;
				b[x-1] = true;
			}
		} while (m > 0);
		return v;
	}
	public  String getRandomString(int length) { //length表示生成字符串的长度
	    String base = "abcdefghijklmnopqrstuvwxyz";   
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();   
	 }   
	public void randomRemoveColumn(int col,String [] titleArray,int [] titleOrder,int [] numberOfColumn){
		// 随机去掉 某列 
		Random random = new Random(); 
		int m = numberOfColumn[0];
		int remainder=random.nextInt()%3;
		int [] temp1 = new int[m-1];
		String[] tempstr = new String[m-1];
		if(remainder==0) // trade id =1 
		{
			int ti=0;
			for(int i=0;i<m;i++){
				if(titleOrder[i]!=col)
				{	temp1[ti]=titleOrder[i];
				    tempstr[ti] =titleArray[i]; 
				    ti++;
				}	
			}
			
			m=m-1;
			numberOfColumn[0]=m;
			for(int i=0;i<m;i++){
				titleOrder[i]=temp1[i];
				titleArray[i]=tempstr[i]; 
			}
		}
	}

}
