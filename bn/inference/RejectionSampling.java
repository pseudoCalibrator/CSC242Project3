package bn.inference;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import bn.core.*;
import bn.base.Distribution;
import bn.base.Assignment;
import bn.base.BayesianNetwork;

public class RejectionSampling {
    
    public Assignment pSample(BayesianNetwork bn)
    {
        Assignment tempAssignment = new Assignment();
        List<RandomVariable> list = bn.getVariablesSortedTopologically();
        for(RandomVariable Xi:list)
        {
            ArrayList<Double> weightList = new ArrayList<>();
            for(Value i:Xi.getDomain())
            {
                tempAssignment.put(Xi, i);
                weightList.add(bn.getNodeForVariable(Xi).getCPT().get(i, tempAssignment));
            }

            double rand = Math.random();
            double total = 0.0;

            int iter = 0;
            for(Value ii:Xi.getDomain())
            {
                total += weightList.get(iter);
                if(rand <= total)
                {
                    tempAssignment.put(Xi, ii);
                    break;
                }

                iter++;
            }
        }

        return tempAssignment;
    }

    public Distribution query(BayesianNetwork bn, RandomVariable rand, Assignment a, int i)
    {
        Distribution done = new Distribution(rand);
        for(Value v:rand.getDomain())
            done.set(v, 0);
        for(int ii = 0; ii < i; ii++)
        {
            boolean check = true;
            Assignment e = pSample(bn);
            Set<Entry<RandomVariable, Value>> eSet = e.entrySet();
            for(Entry<RandomVariable, Value> c:eSet)
            {
                if(!c.getValue().equals(e.get(c.getKey())))
                {
                    check = false;
                    break;
                }
            }
            if(check)
            {
                done.set(e.get(rand), done.get(e.get(rand)) + 1);
            }
        }
        done.normalize();
        return done;
    }

    public RejectionSampling()
    {
        ;
    }
}
