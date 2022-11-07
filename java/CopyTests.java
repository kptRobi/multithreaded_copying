package pl.wit.projekt;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

/**
 * @author Robert Lamperski
 */
public class CopyTests {

	// test copying directory
	String copyFrom = "C:\\from";
	// test paste directory
	String copyTo = "C:\\to";
	// number of threads used
	Integer threads = 5;

	/**
	 * Test copying files.
	 */
	@Test
	public void testFiles() {
		// number of files to create
		int testFileAmount = 5;

		// clean directories
		try {
			FileUtils.forceDelete(new File(copyFrom));
			FileUtils.forceDelete(new File(copyTo));
		} catch (IOException e1) {
		}
		
		//creating test files 
		try {
			Files.createDirectories(Path.of(copyTo));
			Files.createDirectories(Path.of(copyFrom));
			for (int i = 0; i < testFileAmount; i++) {
				Files.createFile(Path.of(copyFrom + "\\" + i + ".txt"));
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Nie udało sie utworzyć folderow");
		}

		// parameter setup
		CopyingParameters cp = new CopyingParameters(copyFrom, copyTo, threads);
		// copying
		CopyingMachine cm = new CopyingMachine(cp);
		cm.copyAllFiles();

		File check = new File(copyTo);
		File[] copiedFiles = check.listFiles();
		assertEquals(testFileAmount, copiedFiles.length);

	}

	/**
	 * Test copying direcories.
	 */
	@Test
	public void testDirs() {
		int testFileAmount = 5;
		
		//clean directories
		try {
			FileUtils.forceDelete(new File(copyFrom));
			FileUtils.forceDelete(new File(copyTo));
		} catch (IOException e1) {
		}
		
		//creating test directories
		try {
			Files.createDirectories(Path.of(copyTo));
			Files.createDirectories(Path.of(copyFrom));
			for (int i = 0; i < testFileAmount; i++) {
				Files.createDirectory(Path.of(copyFrom + "\\" + i));
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Nie udało sie utworzyć folderow");
		}

		// parameter setup
		CopyingParameters cp = new CopyingParameters(copyFrom, copyTo, threads);
		// copying
		CopyingMachine cm = new CopyingMachine(cp);
		cm.copyAllFiles();

		//testing
		File check = new File(copyTo);
		String[] copiedFiles = check.list();
		assertEquals(testFileAmount, copiedFiles.length);
	}

	/**
	 * Test finding files.
	 */
	@Test
	public void testFindFiles() {
		
		//clean directories
		try {
			FileUtils.forceDelete(new File(copyFrom));
			FileUtils.forceDelete(new File(copyTo));
		} catch (IOException e1) {
		}
		
		//creating test files and directories
		try {
			Files.createDirectories(Path.of(copyFrom + "\\1\\2\\3\\4\\5"));
			Files.createFile(Path.of(copyFrom + "\\1\\2\\plik.txt"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Nie udało sie utworzyć folderow");
		}

		// parameter setup
		CopyingParameters cp = new CopyingParameters(copyFrom, copyTo, threads);

		// copying
		CopyingMachine cm = new CopyingMachine(cp);
		LinkedList<String> testList = new LinkedList<String>();
		testList = cm.findFiles(copyFrom);

		// test
		String[] testArray = { "C:\\from\\1\\2\\3\\4\\5", "C:\\from\\1\\2\\plik.txt" };
		assertArrayEquals(testArray, testList.toArray());
	}

	/**
	 * Test dir hierarchy.
	 */
	@Test
	public void testHierarchy() {
		
		//clean directories
		try {
			FileUtils.forceDelete(new File(copyFrom));
			FileUtils.forceDelete(new File(copyTo));
		} catch (IOException e1) {
		}
		
		//creating test files
		try {
			Files.createDirectories(Path.of(copyTo));
			Files.createDirectories(Path.of(copyFrom));
			Files.createDirectories(Path.of(copyFrom + "\\1\\2\\3\\4\\5"));
			Files.createFile(Path.of(copyFrom + "\\1\\2\\3\\4\\\\5\\file.txt"));
			Files.createFile(Path.of(copyFrom + "\\1\\2\\plik.txt"));
			Files.createFile(Path.of(copyFrom + "\\p.txt"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Nie udało sie utworzyć folderow");
		}

		// parameter setup
		CopyingParameters cp = new CopyingParameters(copyFrom, copyTo, threads);
		// copying
		CopyingMachine cm = new CopyingMachine(cp);
		cm.copyAllFiles();
		
		//testing
		LinkedList<String> copiedFiles = new LinkedList<String>();
		copiedFiles = cm.findFiles(copyTo);
		assertEquals(3, copiedFiles.size());
	}

}
