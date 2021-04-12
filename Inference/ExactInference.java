package Inference;
import java.util.List;

import bn.core.BayesianNetwork;

public class ExactInference {
    public double enumAll(BayesianNetwork bn, Assignment a, int i)
    {
        List<RandomVariable> list = bn.getVariablesSortedTopologically();
        if(i >= list.size())
            return 1.0;
        
        RandomVariable rand = list.get(i);
        if(a.containsKey(rand))
        {
            a = a.copy();
            return bn.getProb(rand, a) * enumAll(bn, a, i+1);
        } 
        else
        {
            double res = 0;
            for(int ii = 0; ii < rand.getDomain().size(); ii++)
            {
                a.put(rand, rand.getDomain().get(ii));
                Assignment aCopy = a.copy();
                double probability = bn.getProb(rand, aCopy);
                double enum2 = enumAll(bn, aCopy, i+1);
                res += probability*enum2;
            }

            return res;
        }
    }

    public Distribution query(BayesianNetwork bn, RandomVariable rand, Assignment a)
    {
        Distribution done = new Distribution(rand);
        for(int i = 0; i < rand.getDomain().size(); i++)
		{  
			Assignment clone_a = a.copy();
			clone_a.set(rand, rand.getDomain().get(i));
			done.put(rand.getDomain().get(i), enumerate_All(bn, clone_a, 0));
		}
		done.normalize();
		return done;
    }

    public ExactInference()
    {
        ;
    }
}
