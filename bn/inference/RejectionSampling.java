package bn.inference;

import java.util.ArrayList;
import java.util.List;

import bn.core.*;
import bn.base.Distribution;
import bn.base.Assignment;

public class RejectionSampling {
    
    public Assignment pSample(BayesianNetwork bn)
    {
        Assignment tempAssignment = new Assignment();
        List<RandomVariable> list = bn.getVariablesSortedTopologically();
        for(RandomVariable Xi:list)
        {
            for(Value i:Xi.getDomain())
            {
                
            }
        }

    }
}
