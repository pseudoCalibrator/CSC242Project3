package Inference;

import bn.*;
import bn.core.BayesianNetwork;

public class LikelihoodWeighting
{
    private static class weightedAssignment
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
                copy.set(rand, a.get(rand));
                tempWeight *= bn.getProb(rand, copy);
            }
            else
                copy.set(v.ProbSample().randomSample(bn, rand, copy));
        }
    }
}

