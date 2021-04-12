package bn.inference;
import java.util.List;

import bn.core.BayesianNetwork;
import bn.core.RandomVariable;
import bn.core.Value;
import bn.core.Assignment;
import bn.base.Distribution;

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
            return bn.getProbability(rand, a) * enumAll(bn, a, i+1);
        } 
        else
        {
            double res = 0;
            for(Value ii:rand.getDomain())
            {
                a.put(rand, ii);
                Assignment aCopy = a.copy();
                double probability = bn.getProbability(rand, aCopy);
                double enum2 = enumAll(bn, aCopy, i+1);
                res += probability*enum2;
            }

            return res;
        }
    }

    public Distribution query(BayesianNetwork bn, RandomVariable rand, Assignment a)
    {
        Distribution done = new Distribution(rand);
        for(Value i:rand.getDomain())
		{  
			Assignment copy = a.copy();
			copy.put(rand, i);
			done.put(i, enumAll(bn, copy, 0));
		}
		done.normalize();
		return done;
    }

    public ExactInference()
    {
        ;
    }
}
