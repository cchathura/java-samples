package fileHandlerFactories;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import Constants.Constants;

/**
 * @author camarathunga
 *
 */
public abstract class FileHandlerAbstractFactory {
	/**
	 * logger instace for login
	 */
	final static Logger logger = Logger
			.getLogger(FileHandlerAbstractFactory.class);

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
	
	/**
	 * This is for get deployment files without merging with others. 
	 * 
	 * @param dir
	 * @return
	 */
	public abstract List<File> getDeploymentFiles(File dir);
	
	/**
	 * @param dir
	 * @return
	 */
	public File[] getFilesInDir(File dir){
		FilenameFilter filter = new FilenameFilter() {
			
			@Override
			public boolean accept(File arg0, String arg1) {
				return arg1.toLowerCase().endsWith(".xml");
			}
		};
		return dir.listFiles(filter);
	}
	
	/**
	 * @param pFile
	 * @return
	 */
	public boolean checkDeploymetProperty(File pFile){
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(pFile);
			prop.load(input);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("error while reading property file " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("error while reading property file " + e.getMessage());
		}
		
		if(prop.getProperty(Constants.RELEASE_PROPERTY_IS_DEPLOY_KEY,"No").equalsIgnoreCase("yes")){
			return true;
		}
		return false;
	}
}
