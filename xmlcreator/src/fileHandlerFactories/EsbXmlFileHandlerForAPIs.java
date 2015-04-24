package fileHandlerFactories;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.log4j.Logger;

import Constants.Constants;

/**
 * 
 * @author camarathunga
 * 
 */
public class EsbXmlFileHandlerForAPIs extends FileHandlerAbstractFactory {
	/**
	 * logger instace for login
	 */
	final static Logger logger = Logger
			.getLogger(EsbXmlFileHandlerForAPIs.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * factories.FileHandlerAbstractFactory#getMergingXMLFiles(java.io.File)
	 * 
	 * @param merge file included directory
	 */
	@Override
	public List<File> getMergingXMLFiles(File dir) {

		// File OperationsDir = new File(dir);
		if (dir.exists()) {
			Collection<File> files = FileUtils
					.listFiles(
							dir,
							new RegexFileFilter(
									Constants.ESB_API_OPERATIONS_MAIN_CONFIG_FILE_NAME),
							DirectoryFileFilter.DIRECTORY);
			if (files.size() == 0) {
				logger.warn(Constants.ESB_API_OPERATIONS_MAIN_CONFIG_FILE_NAME
						+ "not availble in " + dir);
				return null;
			}
			List<File> fileList = new ArrayList<File>(files);

			return fileList;
		} else {
			logger.warn("directory not available" + dir);
			return null;
		}
	}

	public List<File> getMergingXMLFilesWithExclude(File dir) {
		if (dir.exists()) {
			// File OperationsDir = new File(dir);
			List<File> fileList = new ArrayList<File>();
			Collection<File> files = FileUtils.listFilesAndDirs(dir,
					new RegexFileFilter("^*"), DirectoryFileFilter.DIRECTORY);

			for (File file : files) {
				if (file.isDirectory()) {

					if (!(new File(file, "release.properties").exists())
							&& new File(
									file,
									Constants.ESB_API_OPERATIONS_MAIN_CONFIG_FILE_NAME)
									.exists()) {
						//System.out.println("name " + file.getAbsolutePath());
						fileList.add(new File(
								file,
								Constants.ESB_API_OPERATIONS_MAIN_CONFIG_FILE_NAME));
					}else if((new File(file, "release.properties").exists())
							&& new File(
									file,
									Constants.ESB_API_OPERATIONS_MAIN_CONFIG_FILE_NAME)
									.exists()){
						Properties prop = new Properties();
						InputStream input = null;
					 //to check prorty
						try {
							
							input = new FileInputStream(file.getAbsolutePath()+File.separator+"release.properties");
							prop.load(input);
							if(prop.getProperty(Constants.RELEASE_PROPERTY_IS_DEPLOY_KEY,"No").equalsIgnoreCase("yes")){
								fileList.add(new File(
										file,
										Constants.ESB_API_OPERATIONS_MAIN_CONFIG_FILE_NAME));
							}
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}finally {
							if (input != null) {
								try {
									input.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						 
						// load a properties file
					
				 
						
					}

				}
			}
			}
			/*if (fileList.size() <= 0) {
				return null;
			} else {*/

				return fileList;
			//}
		} else {
			logger.warn("directory not available" + dir);
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see factories.FileHandlerAbstractFactory#getInitXMLFile(java.io.File)
	 * 
	 * @param initconfig file containg dierctory
	 */
	@Override
	public File getInitXMLFile(File dir) {
		File initConfigFile = new File(dir + File.separator
				+ Constants.ESB_API_INIT_CONFIG_FILE_NAME);
		if (initConfigFile.exists()) {
			return initConfigFile;
		} else {
			logger.warn(Constants.ESB_API_INIT_CONFIG_FILE_NAME
					+ "not availble in " + dir);
			return null;
		}

	}

	/* (non-Javadoc)
	 * @see fileHandlerFactories.FileHandlerAbstractFactory#getDeploymentFiles(java.io.File)
	 * 
	*/
	@Override
	public List<File> getDeploymentFiles(File dir) {
		
		List<File> operationsFile = new ArrayList<File>();
		
			
			
		for(String fileName: dir.list()){
			String opertionDir = dir.getAbsolutePath()+File.separator+fileName;
			String sequnceDir = opertionDir + File.separator+Constants.ESB_API_SEQUNCE_DIRECTORY;
		if(new File(opertionDir).isDirectory() && new File(sequnceDir).exists() && !(new File(opertionDir,"release.properties").exists())){
			//FileNameExtensionFilter filter = new FileNameExtensionFilter("text only","txt");
			
			File f[] = getFilesInDir(new File(sequnceDir));
			if(f !=null && f.length>0){
			for(File file : f){
				operationsFile.add(file);
			}
			}
			//System.out.println("Chathura testing "+ f[0].getAbsolutePath());
			}
		
		else if(new File(opertionDir).isDirectory() && new File(sequnceDir).exists() && new File(opertionDir,"release.properties").exists()){
			if(checkDeploymetProperty(new File(opertionDir,"release.properties"))){
				File f[] = getFilesInDir(new File(sequnceDir));
				if(f !=null && f.length>0){
				for(File file : f){
					operationsFile.add(file);
				}
				}
			}
		}
		
		
	}
		return operationsFile;
	}
	
	/**
	 * @param dir
	 * @return
	 
	private File[] getFilesInDir(File dir){
		FilenameFilter filter = new FilenameFilter() {
			
			@Override
			public boolean accept(File arg0, String arg1) {
				return arg1.toLowerCase().endsWith(".xml");
			}
		};
		return dir.listFiles(filter);
	}*/
	

}
