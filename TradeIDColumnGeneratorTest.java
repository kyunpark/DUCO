package generating;

//import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

public class TradeIDColumnGeneratorTest {

	@Test
	public void testGenerateColumnValue() {
		Calendar [] benchTime = new Calendar[1];
		boolean[] tradeDateGenerateflag=new boolean[1];
		String[] tradeDate = new String[1];
		int rowsNumber=50;
		boolean[] tradeIdExistence = new boolean[rowsNumber];
		//new TradeIDColumnGenerator().generateColumnValue(50, 50);
		new TradeIDColumnGenerator().generateColumnValue(benchTime,tradeDate,tradeDateGenerateflag,tradeIdExistence,rowsNumber);	
		//fail("Not yet implemented");
	}

	

}
