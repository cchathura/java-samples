package fileHandlerFactories;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;

import Constants.Constants;

public class DssXmlFileHandlerForServices extends FileHandlerAbstractFactory{

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

	@Override
	public List<File> getDeploymentFiles(File dir) {
		List<File> configFiles = new ArrayList<File>();
		if(dir.exists()){
			Collection<File> files = FileUtils.listFilesAndDirs(dir,
					new RegexFileFilter("^*"), DirectoryFileFilter.DIRECTORY);
			for(File file : files){
				if(file.isDirectory()){
					if(new File(file,Constants.DSS_SERVICE_CONFIG_FILE_NAME).exists() && !(new File(file,"release.properties").exists())){
						configFiles.add(new File(file,Constants.DSS_SERVICE_CONFIG_FILE_NAME));
					}else if(new File(file,Constants.DSS_SERVICE_CONFIG_FILE_NAME).exists() && (new File(file,"release.properties").exists())){
						Properties prop = new Properties();
						InputStream input = null;
					 //to check prorty
						try {
							
							input = new FileInputStream(file.getAbsolutePath()+File.separator+"release.properties");
							prop.load(input);
							if(prop.getProperty(Constants.RELEASE_PROPERTY_IS_DEPLOY_KEY,"No").equalsIgnoreCase("yes")){
								configFiles.add(new File(
										file,
										Constants.DSS_SERVICE_CONFIG_FILE_NAME));
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
		
		
		}
return configFiles;
}
}
