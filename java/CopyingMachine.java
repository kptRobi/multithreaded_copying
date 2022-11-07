package pl.wit.projekt;

import java.io.File;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * @author Robert Lamperski
 */
public class CopyingMachine {
	
	ExecutorService es = null;
	//path to main copy directory
	String copyDir = null;
	//path to main paste directory
	String pasteDir = null;
	//number of threads
	Integer ThreadNo = null;
	
	/**
	 * Instantiates a new copying machine.
	 *
	 * @param cp the cp
	 */
	public CopyingMachine(CopyingParameters cp) {
		this.es = Executors.newFixedThreadPool(cp.threadNo);
		this.copyDir = cp.srcPath;
		this.pasteDir = cp.destPath;
		this.ThreadNo = cp.threadNo;
	}
	
	/**
	 * Finds files under given path.
	 *
	 * @param dirToListFiles the dir to list files
	 * @return the linked list
	 */
	
	public LinkedList<String> findFiles(String dirToListFiles){
		//setup
		LinkedList<String> listOfFiles = new LinkedList<String>();
		File root = new File(dirToListFiles);
		
		//find files in main directory given
		String[] tmpArray = root.list();
		
		for(String s: tmpArray) {
			File f = new File(dirToListFiles + "\\" + s.toString());
			//if file in main directory isn't directory, add to list as file, else find files in it
			if(!f.isDirectory()) {
				listOfFiles.add(f.toString());
			}else {
				//new list for files in that directory
				LinkedList<String> tmpList = new LinkedList<String>();
				tmpList = findFiles(f.toString());
				
				//if directory is empty add just it itself to the list
				if(tmpList.isEmpty()) {
					listOfFiles.add(f.toString());
				}else {
					listOfFiles.addAll(tmpList);
				}
			}
		}
		return listOfFiles;
	}

	/**
	 * Copy all files and directories.
	 */
	public void copyAllFiles() {
		//setup
		ExecutorService es = Executors.newFixedThreadPool(this.ThreadNo);
		LinkedList<String> allFilesList = this.findFiles(copyDir);
		int fileCount = allFilesList.size();
		
		//multithreaded previously found files - one thread per file
		for(int i = 0; i < fileCount; i++) {
			es.execute(new CopyTask(this.copyDir, this.pasteDir, allFilesList.pop()));
		}
		
		//wainting for everything to end
		try {
			es.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return;
	}
}
