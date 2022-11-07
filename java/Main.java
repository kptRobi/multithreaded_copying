package pl.wit.projekt;
/**
 * @author Robert Lamperski
 */
public class Main {

	public static void main(String[] args) {
		//user input
		String copyFrom = "C:\\COPY_TEST\\from";
		String copyTo = "C:\\COPY_TEST\\to";
		Integer threads = 5;
		
		//parameter setup
		CopyingParameters cp = new CopyingParameters(copyFrom, copyTo, threads);
		
		//copying
		CopyingMachine cm = new CopyingMachine(cp);
		cm.copyAllFiles();
	}
}
