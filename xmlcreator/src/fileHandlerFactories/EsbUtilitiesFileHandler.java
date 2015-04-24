package fileHandlerFactories;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import Constants.Constants;
import PropertyLoader.DeploymentPropertyLoader;
import Utill.XMLCreatorUtill;
import Utill.XMLCreatorUtillImpl;

public class EsbUtilitiesFileHandler extends FileHandlerAbstractFactory {
	/**
	 * logger instace for login
	 */
	final static Logger logger = Logger
			.getLogger(EsbUtilitiesFileHandler.class);

	@Override
	public List<File> getMergingXMLFiles(File dir) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File getInitXMLFile(File dir) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<File> getMergingXMLFilesWithExclude(File dir) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see fileHandlerFactories.FileHandlerAbstractFactory#getDeploymentFiles(java.io.File)
	 * get utility files
	 */
	@Override
	public List<File> getDeploymentFiles(File dir) {

		List<File> deploymentFiles = new ArrayList<File>();
		if(new File(dir,Constants.DEPLOYMENT_PROPERTY_FILE_NAME).exists()){
			//load list for copy files
			Properties prop = new Properties();
			InputStream input = null;
			String deploymentFileList="";
			try {
				input = new FileInputStream(new File(dir,Constants.DEPLOYMENT_PROPERTY_FILE_NAME));
				prop.load(input);
				deploymentFileList = prop.getProperty(Constants.ESB_UTILITIES_DEPLOYMENT_FILES_KEY,"donotcopy");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				logger.error("error while reading property file " + e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("error while reading property file " + e.getMessage());
			}finally{
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			
			}
			
			//copy files 
			String[] Files =deploymentFileList.split(",");
			
			for(String fileName : Files){
				if(new File(dir,fileName).exists()){
					deploymentFiles.add(new File(dir,fileName));
				}
			}
			
		}else if(dir.exists()){
			// get all xml files
			
			deploymentFiles = Arrays.asList(getFilesInDir(dir));
		}else{
			logger.warn("directory not exists " + dir.getAbsolutePath());
		}

		//Utilities dir
		
		return deploymentFiles;
	}
	
	
}
