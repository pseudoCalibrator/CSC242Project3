package bn.inference;

import bn.*;
import bn.core.*;

public class LikelihoodWeighting
{
    public static class weightedAssignment
    {
        Assignment a;
        double weight;
        weightedAssignment(Assignment a, double weight)
        {
            this.a = a;
            this.weight = weight;
        }
    }

    private static weightedAssignment weightedSample(BayesianNetwork bn, Assignment a)
    {
        double tempWeight = 1.0;
        Assignment copy = a.copy();
        for(RandomVariable rand:bn.getVariablesSortedTopologically())
        {
            if(a.containsKey(rand))
            {
                copy.put(rand, a.get(rand));
                tempWeight *= bn.getProbability(rand, copy);
            }
            else
                copy.put(rand, ProbSample.randSample(bn, rand, copy));
        }
    }
}