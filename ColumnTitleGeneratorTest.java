package generating;

import static org.junit.Assert.*;

import org.junit.Test;

public class ColumnTitleGeneratorTest {

	@Test
	public void testGenerateColumnValueArray() {
		String[] titleArray = new String[50];  
		int [] titleOrder = new int[50];
		int [] numberOfColumn = new int[1]; // 标题的数量
		String typename = "swap";
		//ctg.generateColumnValueArray(typename, titleArray, titleOrder, numberOfColumn);
		 new ColumnTitleGenerator().generateColumnValueArray(typename, titleArray, titleOrder, numberOfColumn);
		//fail("Not yet implemented");
	}

	@Test
	public void testGenerateNoRepeatArray() {
		fail("Not yet implemented");
	}

}
