package bn.inference;

import bn.*;
import bn.core.*;

public class ProbSample {
    public static Value randSample(BayesianNetwork bn, RandomVariable v, Assignment a)
    {
        double rand = Math.random();
        double comp = 0.0;

        for(Value i:v.getDomain())
        {
            Assignment copy = a.copy();
            copy.put(v, i);
            comp += bn.getProbability(v, copy);
            if(rand <= comp)
                return i;
        }

        return null;
    }
}
