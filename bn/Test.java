package bn;

import bn.core.BayesianNetwork;
import bn.base.*;
import bn.parser.*;
import bn.inference.*;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        // If the first argument is not a number, we're running exact inferencer
        if (!Character.isDigit(args[0].charAt(0))) {
            ExactInference exact = new ExactInference();
            // parse xml file
            if (args[0].endsWith("xml")) {
                XMLBIFParser xmlbifparser = new XMLBIFParser();
                Distribution distribution;
                // Read network from the file given
                try {
                    BayesianNetwork network = xmlbifparser.readNetworkFromFile(args[0]);
                    Assignment assignment = new Assignment();
                    for (int i = 2; i < args.length - 1; i += 2)
                        // read arguments from command line
                        assignment.put(network.getVariableByName(args[i]), new StringValue(args[i + 1]));

                    // take the variables and run exact inference
                    distribution = exact.query(network, network.getVariableByName(args[1]), assignment);
                    System.out.println(distribution);
                } catch (IOException | ParserConfigurationException | SAXException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            // parse bif
            else {

                BIFParser bifparser;
                Distribution distribution;
                try {
                    bifparser = new BIFParser(new FileInputStream(args[0]));
                    try {
                        BayesianNetwork network = bifparser.parseNetwork();
                        Assignment assignment = new Assignment();
                        for (int i = 2; i < args.length - 1; i += 2)
                            // read arguments from command line
                            assignment.put(network.getVariableByName(args[i]), new StringValue(args[i + 1]));

                        distribution = exact.query(network, network.getVariableByName(args[1]), assignment);
                        System.out.println(distribution);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        }
        // If the first argument is a number, we're either doing reject or weight
        // approximate
        else {
            int sampleAmount = Integer.parseInt(args[0]);
            int algorithm = args[args.length - 1].equalsIgnoreCase("rejection") ? 1 : 2;
            LikelihoodWeighting likelihood = new LikelihoodWeighting();
            RejectionSampling rejection = new RejectionSampling();
            // parse xml file
            if (args[1].endsWith("xml")) {
                XMLBIFParser xmlbifparser = new XMLBIFParser();
                Distribution distribution;
                // Read network from the file given
                try {
                    BayesianNetwork network = xmlbifparser.readNetworkFromFile(args[1]);
                    Assignment assignment = new Assignment();
                    for (int i = 3; i < args.length - 2; i += 2)
                        // read arguments from command line
                        assignment.put(network.getVariableByName(args[i]), new StringValue(args[i + 1]));

                    // if alg is 1, run rejection, otherwise run likelihood
                    if (algorithm == 1)
                        distribution = rejection.query((bn.base.BayesianNetwork) network, network.getVariableByName(args[2]), assignment,
                                sampleAmount);
                    else
                        distribution = likelihood.query(network, network.getVariableByName(args[2]), assignment,
                                sampleAmount);
                    System.out.println(distribution);
                } catch (IOException | ParserConfigurationException | SAXException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            // parse bif
            else {
                BIFParser bifparser;
                Distribution distribution;
                try {
                    bifparser = new BIFParser(new FileInputStream(args[1]));
                    try {
                        BayesianNetwork network = bifparser.parseNetwork();
                        Assignment assignment = new Assignment();
                        for (int i = 3; i < args.length - 2; i += 2)
                            // read arguments from command line
                            assignment.put(network.getVariableByName(args[i]), new StringValue(args[i + 1]));
                        // if alg is 1, run rejection, otherwise run likelihood
                        if (algorithm == 1)
                            distribution = rejection.query((bn.base.BayesianNetwork) network, network.getVariableByName(args[2]), assignment,
                                    sampleAmount);
                        else
                            distribution = likelihood.query(network, network.getVariableByName(args[2]), assignment,
                                    sampleAmount);
                        System.out.println(distribution);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
    }
}
