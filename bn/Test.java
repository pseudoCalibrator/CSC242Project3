package bn;

import bn.parser.*;
import bn.inference.*;
import bn.core.*;
import bn.base.Assignment;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        // If the first argument is not a number, we're running exact inferencer
        if (!Character.isDigit(args[0].charAt(0))) {
            ExactInference exact = new ExactInference();
            // parse xml file
            if (args[0].endsWith("xml")) {
                XMLBIFParser xmlparser = new XMLBIFParser();
                // Read network from the file given
                try {
                    BayesianNetwork network = xmlparser.readNetworkFromFile(args[0]);
                } catch (IOException | ParserConfigurationException | SAXException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
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
