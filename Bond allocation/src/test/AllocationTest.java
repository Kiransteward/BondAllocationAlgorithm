package test;

import com.company.*;
import com.company.Bonds.BasicBond;
import com.company.DataTypes.AssetClass;
import com.company.DataTypes.Rating;
import com.company.Deals.BasicDeal;
import org.junit.BeforeClass;
import org.junit.Test;


import java.util.*;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertEquals;

public class AllocationTest {
    protected static List<Deal> deals;
    protected static List<Bond> bonds;
    protected static GreedyAllocator allocator;
    @BeforeClass
    public static void setup(){
        deals = new ArrayList();
        bonds = new ArrayList();
        HashMap<AssetClass, Double> deal1Asset = new HashMap();
        deal1Asset.put(AssetClass.CORPORATE, 300.0);
        HashMap<Rating, Double> deal1Rating = new HashMap();
        deal1Rating.put(Rating.AAA,500.0);
        BasicDeal deal1 = new BasicDeal(1000, deal1Asset, deal1Rating);
        deals.add(deal1);
        BasicBond bond = new BasicBond(100, 8, AssetClass.CORPORATE, Rating.AAA);
        bonds.add(bond);
        allocator = new GreedyAllocator(deals,bonds);

    }
    @Test
    public void computeLoanTest(){
        assertEquals(allocator.computeLoan(),1000.0);
    }
    @Test
    public void allocate_should_return_loan(){
        assertEquals(allocator.allocate(), 200.0);
    }
    @Test(expected = IllegalArgumentException.class)
    public void assign_unconstrained_should_throw() {
        List<Deal> copyDeals = deals.stream().map(deal -> new BasicDeal(-100, deal.getMinAssetRequirements(),deal.getMinCreditRatings())).collect(Collectors.toList());
        GreedyAllocator allocator = new GreedyAllocator(copyDeals,bonds);
        Map<Deal, Map<Bond,Double>> allocations = new HashMap<>();
        allocator.assignUnconstrained(allocations);
    }
    @Test
    public void assign_unconstrained_should_assign_correctly(){
        Map<Deal, Map<Bond,Double>> allocations = new HashMap<>();
        List<Bond> bonds = List.of(new BasicBond(100, 8, AssetClass.MUNICIPLE, Rating.BB));
        GreedyAllocator allocator = new GreedyAllocator(deals, bonds);
        allocator.assignUnconstrained(allocations);
        assertEquals(allocations.size(), 1);
        assertEquals(allocations.get(deals.get(0)).get(bonds.get(0)), 200.0);

    }


}
