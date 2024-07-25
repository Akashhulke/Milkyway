package com.milkyway.dto;

import java.util.Comparator;

public class DateComparatorDto implements Comparator<MilkCollectionDto>{

	@Override
	public int compare(MilkCollectionDto o1, MilkCollectionDto o2) {
		
		return o1.getDate().compareTo(o2.getDate());
	}

}
