package bn.inference;

import bn.*;

public class ProbSample {
    public static Object randSample(BayesianNetwork bn, RandomVariable v, Assignment a)
    {
        double rand = Math.random();
        double comp = 0.0;

        for(int i = 0; i < v.getDomain().size(); i++)
        {
            Assignment copy = a.copy();
            copy.set(v, v.getDomain.get(i));
            comp += bn.getProb(v, copy);
            if(rand <= comp)
                return v.getDomain().get(i);
        }

        return null;
    }
}
