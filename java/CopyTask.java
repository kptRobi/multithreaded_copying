package pl.wit.projekt;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
/**
 * @author Robert Lamperski
 */
public class CopyTask implements Runnable {
	//path of copied file
	String sourceFile = null;
	//path of target fle
	String targetFile = null;
	//path of copy direcory
	String copyDir = null;
	//path of paste
	String pasteDir = null;
	

	/**
	 * Instantiates a new copy task. Takes source, destination and target file
	 *
	 * @param copyDir the copy dir
	 * @param pasteDir the paste dir
	 * @param sourceFile the source file
	 */
	public CopyTask(String copyDir, String pasteDir, String sourceFile) {
		this.copyDir = copyDir;
		this.pasteDir = pasteDir;
		this.sourceFile = sourceFile;
		this.targetFile = sourceFile.replace(copyDir, pasteDir);
	}

	/**
	 * Copy single file.
	 */
	@Override
	public void run() {
		try {
			/*	getting paths for files not in main directory and creating directories for them.
			*	Files.createDirectories conveniently does nothing if directory already exists.
			*/
			int toChop = this.targetFile.lastIndexOf('\\');
			String newDirPath = this.targetFile.substring(0, toChop);
			Files.createDirectories(Path.of(newDirPath));
			
			//copying
			Files.copy(Path.of(this.sourceFile), Path.of(this.targetFile));
			
			//printing thread id and copied file
			System.out.println(Thread.currentThread().getId() + ": " + this.sourceFile);
			
		} catch (FileAlreadyExistsException e) {
			System.out.println("Plik " + this.targetFile + " już istnieje!");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("ups, mamy problem");
		}

	}
}
