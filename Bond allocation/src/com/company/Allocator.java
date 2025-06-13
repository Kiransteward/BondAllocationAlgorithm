package com.company;

import java.util.*;

//Can define
public interface Allocator {
    double computeLoan();
    double allocate();
    Map<Deal, Map<Bond, Double>> assignUnconstrained(Map<Deal, Map<Bond, Double>> allocations);
}


























