package artificialIntelligenceUnit;

import java.util.Comparator;

/**
 * @author Matthew Nestor
 * @filename AStarComparator.java
 * @package artificialIntelligenceUnit
 * @project HostSide
 * @date 16/10/2013
 */

public class AStarComparator implements Comparator<Node>
{
    public int compare(Node n1, Node n2)
    {
	if(n1.getAStarCost() < n2.getAStarCost())
	    return -1;
	else if(n1.getAStarCost() > n2.getAStarCost())
	    return 1;
	else
	    {
		if(n1.getPriority() < n2.getPriority())
		    return -1;
		else if(n1.getPriority() > n2.getPriority())
		    return 1;
		else
		    return 0;
	    }
    }
}