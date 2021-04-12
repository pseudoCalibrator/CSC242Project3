package bn;

import bn.*;
import bn.parser.*;
import bn.inference.*;
import bn.core.*;

public class Test {
    public static void main(String[] args) {
        // If the first argument is not a number, we're running exact inferencer
        if (!Character.isDigit(args[0].charAt(0))) {
            ExactInference exact = new ExactInference();
            // parse xml file
            if (args[0].endsWith("xml")) {
                XMLBIFParser xmlparser = new XMLBIFParser();
                // Read network from the file given
                BayesianNetwork network = xmlparser.readNetworkFromFile(args[0]);
                Assignment assignment = new Assignment();
            }
            // parse bif
            else {

            }
        }
        // If the first argument is a number, we're either doing reject or weight
        // approximate
        else {
            // parse xml file
            if (args[0].endsWith("xml")) {

            }
            // parse bif
            else {

            }
        }
    }
}
