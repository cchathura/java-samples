package factories;

import java.io.File;
import java.util.List;

/**
 * @author camarathunga
 *
 */
public abstract class FileHandlerAbstractFactory {

	/**
	 * @param dir
	 * @return
	 */
	public abstract List<File> getMergingXMLFiles(File dir);
	
	/**
	 * @param dir
	 * @return
	 */
	public abstract File getInitXMLFile(File dir);
	
	public abstract List<File> getMergingXMLFilesWithExclude(File dir);
}
