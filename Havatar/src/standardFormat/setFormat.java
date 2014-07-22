package standardFormat;
   
import java.io.Serializable;    
 
public class setForamt implements Serializable{ 
	public void format{

		// create regular expressions for each field of input data for classification
		String regexPaymentFreq = "([1-9]{1,2}[yY])|([1-9]{1,2}[mM])";
		String regexDates = "((\\d){1,4}-(\\d){1,4}-(\\d){1,4})|((\\d){1,4}/(\\d){1,4}/(\\d){1,4})";
		String regexCurrency = "[A-Z]{3}";
		  .
		  .
		  .

} 