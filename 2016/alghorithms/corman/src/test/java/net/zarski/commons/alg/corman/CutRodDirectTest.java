package net.zarski.commons.alg.corman;

import static org.junit.Assert.*;

import java.util.concurrent.TimeoutException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.statements.FailOnTimeout;
import org.junit.rules.Timeout;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class CutRodDirectTest extends BaseTest{

	public final CutRod cutRod = new CutRodDirect(prices());
	@Test
	public void cutRodOfLengthZero(){
		assertEquals(0,cutRod.solve(0));
	}
	
	@Test
	public void doNotCutRod(){
		assertEquals(1,cutRod.solve(1));
		assertEquals(5,cutRod.solve(2));
		assertEquals(8,cutRod.solve(3));
		assertEquals(17,cutRod.solve(6));
		assertEquals(22,cutRod.solve(8));
		assertEquals(30,cutRod.solve(10));
	} 
	
	@Test
	public void cutRodOfLengthFourOptimally(){
		assertEquals(10, cutRod.solve(4));
	}
	
	@Test
	public void cutLongRod(){
		assertEquals(31,cutRod.solve(11));
		assertEquals(40,cutRod.solve(14));
		assertEquals(120,cutRod.solve(40));
	}
	
	@Test(timeout=1)
	public void cutLongRodTimout(){
		cutRod.solve(120);
	}
	
	

	
}
