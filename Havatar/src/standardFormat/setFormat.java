package standardFormat;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.io.Serializable;    
 
public class setForamt{ 

		// create regular expressions for each field of input data for classification
		//String regexID = "([1-9]{1,10})";
		String regexDates = "((\\d){1,4}-(\\d){1,4}-(\\d){1,4})|((\\d){1,4}/(\\d){1,4}/(\\d){1,4})";
		String regexPaymentFreq = "([1-9]{1,2}[yY])|([1-9]{1,2}[mM])";
		String regexCurrency = "[A-Z]{3}";
		String regexPrice = "[1-9]{1,10}";
		String regexNotional = "([1-9]{1,10})|([1-9]{1,3}\\,[1-9]{3}\\,[1-9]{3})";
		String regexBuySell= "([BB][Uu][Yy])|([SS][Ee][Ll][Ll])|([Bb])|(Ss)";
		String regexRate = "[1-9]{1,3}\\.[01-9]+%?";
		String regexUnitOfTrading = "[1-9]{1,3}\\,[1-9]{3}\\,[1-9]{3}";
		String regexContractSize = "[1-9]{1,3}\\.[0]+%?";
		String regexPartiesNames = "[Aa-Zz]{1,20}"
	
		public Classifier loadClassifier(File inputFile)
				throws FileNotFoundException, IOException, ClassNotFoundException {

				                                           
				        // Here we load a serialized classifier from a file.                                               

				        Classifier classifier;

				        ObjectInputStream ois =
				            new ObjectInputStream (new FileInputStream (serializedFile));
				        classifier = (Classifier) ois.readObject();
				        ois.close();

				        return classifier;
				    }
				    Dataset dataForClassification = FileHandler.loadDataset(new File("devtools/data/iris.data"), 4, ",");
				   /* Counters for correct and wrong predictions. */
				int correct = 0, wrong = 0;
				/* Classify all instances and check with the correct class values */
				for (Instance inst : dataForClassification) {
				    Object predictedClassValue = knn.classify(inst);
				    Object realClassValue = inst.classValue();
				    if (predictedClassValue.equals(realClassValue))
				        correct++;
				    else
				        wrong++;
				        
				        Dataset data = FileHandler.loadDataset(new File("devtools/data/iris.data"), 4, ",");
				 /* Contruct a KNN classifier that uses 5 neighbors to make a
				  *decision. */
				Classifier knn = new KNearestNeighbors(5);
				knn.buildClassifier(data);

} 