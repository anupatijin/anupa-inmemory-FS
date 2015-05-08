package com.java.FileSystemTest;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;

import com.java.FileSystem.InMemoryFileSystem;

public class Parameterized extends Suite{
	
	

	private InMemoryFileSystem file;

	@RunWith(Parameterized.class)

	public class TestAdd {

	private int expectResult;

	private int firstVal;

	private int secVal;

	public TestAdd(int expectResult, int firstVal, int secVal){

	this.expectResult = expectResult;

	this.firstVal = firstVal;

	this.secVal = secVal;

	}

	

	@Before

	public void setup(){

	


	}

	@Parameters

	public Collection<Object[]> testData(){

	Object[][] data = new Object[][]{ {6,2,4},{7,4,3},{8,2,6}};


	return Arrays.asList(data);


	}



	@Test

	public void testAdd(){

	org.junit.Assert.assertEquals(expectResult, add.add(firstVal, secVal));

	}
	@Test
	public void test() {
		
	}

}
}
