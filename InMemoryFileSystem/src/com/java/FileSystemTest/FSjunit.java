package com.java.FileSystemTest;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.java.FileSystem.InMemoryFileSystem;

public class FSjunit {

	private InMemoryFileSystem file;
	@Before
	
	public void setup(){
		file = new InMemoryFileSystem();
	}
	
	
	@Test
	public void testFolder() throws IOException {
		
				   
				   String testFolderPath="C:/Users/Anupa/Documents";
				   file.createFolder(testFolderPath);
				   
				   
				   File expectedFolder =new File(testFolderPath);
				   assertTrue(expectedFolder.exists());
				   
	
				   		   
				   
				   
	}
	
	@Test
	public void testFile() throws IOException{
		  String testFilePath="C:/Users/Anupa/Documents/anupa.txt";
		  file.createFile(testFilePath);
		  File expectedFile =new File(testFilePath);
		  assertTrue(expectedFile.exists());
	}
	
	@Test
	public void testWrite() throws IOException{
		String testFilePath="C:/Users/Anupa/Documents/anupa.txt";
		String expected =new String("my data");
		InMemoryFileSystem.writeContentToFile(testFilePath, "my data");
		final String result = InMemoryFileSystem.returnFileContents(testFilePath);
		assertEquals(expected,result);
	}
	
	
	

	
		@After
		
		public void tearDown(){
			
			file=null;
			
		
	}

}
