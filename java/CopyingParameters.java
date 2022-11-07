package pl.wit.projekt;

public class CopyingParameters {
	//source path
	String srcPath = null;
	//destination path
	String destPath = null;
	//number of threads
	Integer threadNo = null;
	
	
	
	/**
	 * Instantiates a new copying parameters.
	 *
	 * @param srcPath the src path
	 * @param destPath the dest path
	 * @param threadNo the thread no
	 */
	public CopyingParameters(String srcPath, String destPath, Integer threadNo) {
		this.srcPath = srcPath;
		this.destPath = destPath;
		this.threadNo = threadNo;
	}
	
	/**
	 * Gets the src path.
	 *
	 * @return the source path
	 */
	public String getSrcPath() {
		return srcPath;
	}
	
	/**
	 * Gets the dest path.
	 *
	 * @return the destination path
	 */
	public String getDestPath() {
		return destPath;
	}
	
	/**
	 * Gets the thread number.
	 *
	 * @return the thread number
	 */
	public Integer getThreadNo() {
		return threadNo;
	}
	
	/**
	 * Sets the src path.
	 *
	 * @param srcPath the source path to set
	 */
	public void setSrcPath(String srcPath) {
		this.srcPath = srcPath;
	}
	
	/**
	 * Sets the dest path.
	 *
	 * @param destPath the destination path to set
	 */
	public void setDestPath(String destPath) {
		this.destPath = destPath;
	}
	
	/**
	 * Sets the thread number.
	 *
	 * @param threadNo the thread number to set
	 */
	public void setThreadNo(Integer threadNo) {
		this.threadNo = threadNo;
	}
	

}
