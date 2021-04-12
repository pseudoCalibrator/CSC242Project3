package bn.inference;

import bn.core.*;
import bn.base.Distribution;

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

    public static weightedAssignment weightedSample(BayesianNetwork bn, Assignment a)
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

        return new weightedAssignment(copy, tempWeight);
    }

    public Distribution query(BayesianNetwork bn, RandomVariable rand, Assignment a, int i)
    {
        Distribution done = new Distribution(rand);
        for(Value ii:rand.getDomain())
            done.set(ii, 0.0);
        
        for(int iii = 0; iii < i; iii++)
        {
            weightedAssignment wa = weightedSample(bn, a);
            Assignment tempAssignment = wa.a;
            double tempWeight = wa.weight;
            done.set(tempAssignment.get(rand), done.get(tempAssignment.get(rand)) + tempWeight);
        }
        
        done.normalize();
        return done;
    }

    public LikelihoodWeighting()
    {
        ;
    }
}