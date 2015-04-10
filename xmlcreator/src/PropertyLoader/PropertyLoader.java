package PropertyLoader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import Constants.Constants;

public class PropertyLoader {
	
static String lock="lock";
private static PropertyLoader instance =null;

// variable List
/**
 * 
 */
String svnDir;

/**
 * 
 */
String esbAPIDir;

/**
 * 
 */
String deploymentFile;

/**
 * 
 * @return
 */
public String getEsbAPIDir() {
	return esbAPIDir;
}
/**
 * 
 * @return
 */
public String getSvnDir() {
	return this.svnDir;
}

/**
 * @return
 */
public String getDeploymentFile() {
	return this.deploymentFile;
}

private PropertyLoader(){
	
	init();
}
public static PropertyLoader getInstanse(){
synchronized (lock) {
	if(instance==null){
		instance = new PropertyLoader();
		return instance;
	}else{
		return instance;
	}
}
}
private void init(){
	//load propertyes
	Properties prop = new Properties();
	InputStream input = null;
	try {
		input = new FileInputStream(Constants.DEPLOYMENT_PROERTY_FILE_KEY);
		try {
			prop.load(input);
			//get property values
			esbAPIDir=prop.getProperty(Constants.ESB_API_DIR_KEY);
			svnDir=prop.getProperty(Constants.SVN_DIR_KEY);
			deploymentFile=prop.getProperty(Constants.DEPLOYMENT_FILE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (FileNotFoundException e) {
	
		e.printStackTrace();
	}finally {
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	
}
}
}

