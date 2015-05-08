package com.java.FileSystemTest;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;






import junit.framework.Assert;






import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.java.FileSystem.InMemoryFileSystem;

import java.io.*;
import java.lang.Object;


@RunWith(Parameterized.class)

public class fileCopyParameterized {

private String destinationfile;

private String sourcefile;




public fileCopyParameterized(String destinationfile , String sourcefile){



this.destinationfile = destinationfile;

this.sourcefile = sourcefile;

}

 InMemoryFileSystem file;

@Before

public void setup(){

	file = new InMemoryFileSystem();


}

@Parameters

public static Collection<Object[]> testData(){
	


Object[][] data = new Object[][]{ {"C:/Users/Anupa/Documents/anupa.txt","C:/Users/Anupa/Documents/anupacopy.txt"}};


return Arrays.asList(data);


}



@Test

public void fileCopyParameterized() throws IOException{
	

file.copyFileContents(sourcefile,destinationfile);

StringBuffer stringBuffer=new StringBuffer();
FileInputStream fs = new FileInputStream(sourcefile);
BufferedReader br = new BufferedReader(new InputStreamReader(fs));

FileInputStream ds = new FileInputStream(destinationfile);
BufferedReader dr = new BufferedReader(new InputStreamReader(ds));
String sline;
while((sline=br.readLine())!=null) {
	String destLine = dr.readLine();
	assertNotNull("Expected had more lines then the actual.", destLine);
	assertEquals(sline, destLine);
	System.out.println(sline);
	stringBuffer.append(sline);
}
assertNull("Actual had more lines then the expected.", dr.readLine());
br.close();
dr.close();
//org.junit.Assert.assertTrue(destinationfile.contentEquals(stringBuffer));

}
}