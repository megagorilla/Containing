package com.nhl.containing.tests;

import junit.framework.TestCase;

import com.nhl.containing.ListData;

public class ListDataTest extends TestCase {
	public void testListData() {
		ListData ld = new ListData(10, "99999");
		assertEquals("99999", ld.getTime());
	}
}