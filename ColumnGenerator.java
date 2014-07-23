package generating;

import java.util.Calendar;

public interface ColumnGenerator {
	public String generateColumnValue(Calendar[] benchTime, String[] tradeDate,
			boolean[] tradeDateGenerateflag,boolean[] tradeIdExistence,int rowsNumber);
}
