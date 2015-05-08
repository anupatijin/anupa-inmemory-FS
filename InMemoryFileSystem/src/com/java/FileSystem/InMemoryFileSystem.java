package com.java.FileSystem;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/* 
 * This program implements following:
 * 1. Create folder         - Parameter: Absolute folder path
 * 2. Create file           - Parameter: Absolute file path
 * 3. Add content to a file - Parameter: Content to write; Absolute path to a file
 * 4. Copy files            - Parameter: Absolute path to a source file; Absolute path to a destination file. (If destination file exists, it will be overwritten)
 * 5. Display file contents - Parameter: Absolute path to a file; Print file content.
 * 6. List folder contents  - Parameter: Absolute path to a folder; Print folder contents.
 * 7. Search file by name   - Parameter: Input file name; Prints list of absolute paths to files with matching names.
 * 8. Wild Search on file   - Parameter: Absolute path to a starting folder and file name; Outputs list of absolute paths to files with matching names.
 * 9. Copy folders          - Parameter: Absolute path to source folder, Absolute path to destination folder
 */
public class InMemoryFileSystem {

	private String fileNameToSearch;

	private List<String> result = new ArrayList<String>();

	public String getFileNameToSearch() { 

		return fileNameToSearch;
	}
	public void setFileNameToSearch(String fileNameToSearch) { 

		this.fileNameToSearch = fileNameToSearch;

	}

	public List<String> getResult() { 

		return result;

	}

	public static void main(String[] args) throws IOException {

		String sourceFilePath = "C:/Users/Anupa/Desktop/comcast/cfile.txt";
		String destFilePath = "C:/Users/Anupa/Desktop/comcast/dfile.txt";
		String srcFolderName = "C:/Users/Anupa/Desktop/comcast";
		String dstFolderName = "C:/Users/Anupa/Desktop/comcastdst";
		String matchingFileName = "cfile";
		String content = "This is the content to write into file.";
		Scanner in = new Scanner(System.in);

		System.out.println(
				"Enter one of these options:\n\n" +
						"\t1. Create folder\n"  +
						"\t2. Create file\n" +
						"\t3. Add content to a file\n" +
						"\t4. Copy files\n" +
						"\t5. Display file contents\n" +
						"\t6. List folder contents\n" +
						"\t7. Search file by name\n" +
						"\t8. Wild Search on file\n" + 
						"\t9. Copy folders \n" +
				"\t10. Run All options ( with code defaults ) \n");

		System.out.println("\n Enter option:");
		int option = in.nextInt();
		in.nextLine();
		try {

			switch (option) {

			case 1: /* 1. Create a folder. */

				System.out.println("Enter folder path for create : ");
				String folderName = in.nextLine();
				System.out.println("You entered " + folderName);
				createFolder(folderName);
				System.out.println("Folder created: "+ folderName);
				break;

			case 2:/* 2. Create source file. */

				System.out.println("Enter file path for create : ");
				sourceFilePath = in.nextLine();
				createFile(sourceFilePath);
				System.out.println("Source File: "+ sourceFilePath);
				break;

			case 3:/* 3. Write data to source file. */
			
				System.out.println("Enter Source File path : ");
				sourceFilePath = in.nextLine();
				System.out.println("Enter Content: ");
				content = in.nextLine();
				writeContentToFile(sourceFilePath, content);
				System.out.println("Content: " + content + " ; updated to file: " + sourceFilePath);
				break;

			case 4:/* 4. Copy files. */
				
				System.out.println("Enter Source File path : ");
				sourceFilePath = in.nextLine();
				System.out.println("Enter Destination File path : ");
				destFilePath = in.nextLine();
				createFile(destFilePath);
				System.out.println("Destination File: "+ destFilePath);
				copyFileContents(sourceFilePath, destFilePath);
				break;

			case 5:/* 5. Display file contents. */
				
				System.out.println("Enter File path : ");
				destFilePath = in.nextLine();
				System.out.print("Displaying file contents:");
				displayFileContents(destFilePath);
				break;

			case 6:/* 6.List folder contents. */
				
				System.out.println("Enter folder path : ");
				srcFolderName = in.nextLine();
				System.out.println("Listing directory file content's absolute paths:");
				listFolderContents(srcFolderName);
				break;
				
			case 7:/* 7. Search for a file by name. */
				
				System.out.println("Enter directory path : ");
				srcFolderName = in.nextLine();
				System.out.println("Enter file name : ");
				String Filename = in.nextLine();
				System.out.println("Searching file by name:");
				searchFileByName(srcFolderName, Filename);
				break;
				
			case 8:/* 8. Output files matching in a given directory. */
				
				System.out.println("Enter directory path : ");
				srcFolderName = in.nextLine();
				System.out.println("Enter file name : ");
				matchingFileName = in.nextLine();
				System.out.println("Wild Searching file by name:");
				searchMatchingFilesByName(srcFolderName, matchingFileName);
				break;
				
			case 9:/* 9. Copy folders. */
				
				System.out.println("Enter source directory path : ");
				srcFolderName = in.nextLine();
				System.out.println("Enter dest directory path : ");
				dstFolderName = in.nextLine();
				System.out.println("Copying source directory to destination directory:");
				copyFolder(srcFolderName, dstFolderName);
				break;
				
			default:
				System.out.println("Invalid input: Going with option 10\n");
			
			case 10: /* 10. Run all default value */

				/* 1. Create a folder. */
				createFolder(srcFolderName);
				System.out.println("Folder created: "+ srcFolderName);

				/* 2. Create source file. */
				createFile(sourceFilePath);
				System.out.println("Source File: "+ sourceFilePath);

				/* 3. Write data to source file. */
				writeContentToFile(sourceFilePath, content);
				System.out.println("Content: " + content + " ; updated to file: " + sourceFilePath);

				/* Create destination file. */
				createFile(destFilePath);
				System.out.println("Destination File: "+ destFilePath);

				/* 4. Copy files. */
				copyFileContents(sourceFilePath, destFilePath);

				/* 5. Display file contents. */
				System.out.print("Displaying file contents:");
				displayFileContents(destFilePath);

				/* 6.List folder contents. */
				System.out.println("Listing directory file content's absolute paths:");
				listFolderContents(srcFolderName);

				/* 7. Search for a file by name. */
				System.out.println("Searching file by name:");
				searchFileByName(srcFolderName, "cfile.txt");

				/* 8. Output files matching in a given directory. */
				System.out.println("Wild Searching file by name:");
				searchMatchingFilesByName(srcFolderName, matchingFileName);

				/* 9. Copy folders. */
				System.out.println("Copying source directory to destination directory:");
				copyFolder(srcFolderName, dstFolderName);

				break; // end of Switch - case
			}
			
			in.close();
			
		} catch (IOException e) {
			System.out.println("Exception!!");
			e.printStackTrace();
		}
		
		System.out.println("Done. Exiting");
		System.exit(0);

	}

	public static void createFolder(String path) {
		File f = new File(path);
		f.mkdirs();
	}

	public static void createFile(String path) throws IOException {
		File file = new File(path);
		if (!file.exists()) {
			file.createNewFile();
		}
	}
	public static void writeContentToFile(String path, String content) throws IOException {
		File file = new File(path);
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(content);
		bw.close();
	}

	public static void displayFileContents(String path) throws IOException {
		FileInputStream fs = new FileInputStream(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(fs));
		String sline;
		while((sline=br.readLine())!=null) {
			System.out.println(sline);
		}
		br.close();
	}

	public static String returnFileContents(String path) throws IOException {
		StringBuilder stringBuilder=new StringBuilder();
		FileInputStream fs = new FileInputStream(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(fs));
		String sline;
		while((sline=br.readLine())!=null) {
			System.out.println(sline);
			stringBuilder.append(sline);
		   }
		br.close();
		return stringBuilder.toString();
	   }
	public static void listFolderContents(String FolderPath) throws IOException {
		File f1 = new File(FolderPath);
		File[] files = f1.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				System.out.print("Directory:");
			} else {
				System.out.print("file:");
			}
			System.out.println(file.getCanonicalPath());
		}
	}

	public static void searchFileByName(String FolderPath, String FileName) {
		InMemoryFileSystem fileSearch = new InMemoryFileSystem();

		fileSearch.searchDirectory(new File(FolderPath), FileName);

		int count = fileSearch.getResult().size(); 

		if(count == 0) { 
			System.out.println("\nNo result found!");
		} else {
			System.out.println("\nFound " + count + " result!\n"); 

			for (String matched : fileSearch.getResult()) {
			}
		}
	}
	public static void copyFileContents(String source, String dest)  {
		
		LinkedList<String> ll = new LinkedList<String>();
		try{
		FileInputStream fin = new FileInputStream(source);
		BufferedReader br = new BufferedReader(new InputStreamReader(fin));
		String sline;
		
		while((sline=br.readLine())!=null) {
				ll.add(sline);
			}
		br.close();
		}
		catch(IOException e)
	     {
	    	System.out.println("Enter the sourcefile");	    	 
		 }
		
        try{
		FileOutputStream fout = new FileOutputStream(dest);
		BufferedWriter brout = new BufferedWriter(new OutputStreamWriter(fout));
		int i;
		int len = ll.size();

		for(i=0; i<=len-1; i++){ 
			brout.write(ll.get(i));
			brout.newLine();
		}
		brout.close();
		}
        catch(IOException e)
	     {
	    	System.out.println("Enter the destinationfile");	    	 
		 }
	   }
	 


	public void searchDirectory(File directory, String fileNameToSearch) {

		setFileNameToSearch(fileNameToSearch);

		if (directory.isDirectory()) { 
			search(directory); 
		} else { 
			System.out.println(directory.getAbsoluteFile() + " is not a directory!"); 
		}
	}

	private void search(File file) {

		if (file.isDirectory()) {
			System.out.println("Searching directory ... " + file.getAbsoluteFile());
			//do you have permission to read this directory? 
			if (file.canRead()) { 
				for (File temp : file.listFiles()) { 
					if (temp.isDirectory()) {
						searchDirectory(temp, getFileNameToSearch());
					} else {
						search(temp);
						if (getFileNameToSearch().equals(temp.getName().toLowerCase())) {
							result.add(temp.getAbsoluteFile().toString());
						}
					}
				}
			} else {
				System.out.println(file.getAbsoluteFile() + "Permission Denied");
			}
		} 

	}

	public static void searchMatchingFilesByName(String searchFolder,
			final String matchingFileName) {
		File dir = new File(searchFolder); 

		System.out.println("Search in directory : "+ searchFolder); 

		FilenameFilter filter = new FilenameFilter() { 

			public boolean accept (File dir, String name)    {
				return name.startsWith(matchingFileName);          
			}
		}; 

		String[] children = dir.list(filter); 

		if (children == null) { 
			System.out.println("No mathcing file found");
		} else { 
			System.out.println("Absolute path of matched file/s are: "); 

			for (int i=0; i<children.length; i++) { 
				String filename = children[i];
				System.out.println(searchFolder+"\\" + filename);
			} 
		}
	}

	public static void copyFolder(String srcFolderName, String dstFolderName) {
		File srcFolder = new File(srcFolderName); 
		File destFolder = new File(dstFolderName); 

		if (!srcFolder.exists()) { 
			System.out.println("Source directory does not exist."); 
			System.exit(0); 
		} else { 
			try {
				copyFolderInternal(srcFolder,destFolder);
			} catch(IOException e) { 
				e.printStackTrace(); 
				System.exit(0); 
			}
		}
	}
	public static void copyFolderInternal(File src, File dest) throws IOException { 

		if(src.isDirectory()) {
			if(!dest.exists()) {
				dest.mkdir();
				System.out.println("Directory copied from "
						+ src + "  to " + dest);
			}
			String files[] = src.list();
			for (String file : files) {
				File srcFile = new File(src, file);
				File destFile = new File(dest, file);
				copyFolderInternal(srcFile,destFile);
			}
		} else {
			InputStream in = new FileInputStream(src);
			OutputStream out = new FileOutputStream(dest);
			byte[] buffer = new byte[1024]; 
			int length;

			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}

			in.close();
			out.close();
			System.out.println("File copied from " + src + " to " + dest);
		}
	}
}

