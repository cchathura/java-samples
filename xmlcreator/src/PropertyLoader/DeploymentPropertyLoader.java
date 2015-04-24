package PropertyLoader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import Constants.Constants;

public class DeploymentPropertyLoader implements PropertyLoader {
	
static String lock="lock";
private static DeploymentPropertyLoader instance =null;

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
 */
String esbApiDeploymentDir;

/**
 * to hold DSS service config directory
 */
String dssServiceConfigDir;

/**
 * 
 */
String dssServiceDeploymentDir;

/**
 * 
 */
String apimInstructionDocName;
/**
 * 
 */
String apimApisContaingDir;
/**
 * 
 */
String apimApisDeploymentDir;
/**
 * 
 */
String apimDeploymentFileNamePostFix;
/**
 * 
 */
String esbAPIDeploymentFileNamePostFix;

/**
 * 
 */
String esbSequenceDeploymentDirName;
/**
 * 
 */
String esbUtilitiesDirName;
/**
 * 
 */
String esbUtilitiesDeploymentDir;
/**
 * 
 */
String esbUtilitiesXSLTDeploymentDirName;
/**
 * 
 */
String esbUtilitiesEPDeploymentDirName;

/**
 * 
 */
String esbUtilitiesCommonSequenceDeploymentDirName;

/**
 * @return
 */
public String getEsbUtilitiesEPDeploymentDirName() {
	return esbUtilitiesEPDeploymentDirName;
}

/**
 * @return
 */
public String getEsbUtilitiesCommonSequenceDeploymentDirName() {
	return esbUtilitiesCommonSequenceDeploymentDirName;
}
/**
 * @return
 */
public String getEsbUtilitiesXSLTDeploymentDirName() {
	return esbUtilitiesXSLTDeploymentDirName;
}
/**
 * @return
 */
public String getEsbUtilitiesDeploymentDir() {
	return esbUtilitiesDeploymentDir;
}
/**
 * @return
 */
public String getEsbUtilitiesDirName() {
	return esbUtilitiesDirName;
}
/**
 * @return
 */
public String getEsbSequenceDeploymentDirName() {
	return esbSequenceDeploymentDirName;
}
/**
 * @return
 */
public String getEsbAPIDeploymentFileNamePostFix() {
	return esbAPIDeploymentFileNamePostFix;
}
/**
 * @return
 */
public String getApimDeploymentFileNamePostFix() {
	return apimDeploymentFileNamePostFix;
}
/**
 * @return
 */
public String getApimApisDeploymentDir() {
	return apimApisDeploymentDir;
}
/**
 * @return
 */
public String getApimApisContaingDir() {
	return apimApisContaingDir;
}
/**
 * @return
 */
public String getApimInstructionDocName() {
	return apimInstructionDocName;
}
/**
 * @return
 */
public String getDssServiceDeploymentDir() {
	return dssServiceDeploymentDir;
}
/**
 * @return
 */
public String getDssServiceConfigDir() {
	return dssServiceConfigDir;
}
/**
 * @return
 */
public String getEsbApiDeploymentDir() {
	return esbApiDeploymentDir;
}
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

private DeploymentPropertyLoader(){
	
	init();
}
public static DeploymentPropertyLoader getInstanse(){
synchronized (lock) {
	if(instance==null){
		instance = new DeploymentPropertyLoader();
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
			esbApiDeploymentDir =prop.getProperty(Constants.ESB_APIS_DEPLOMENT_DIR_KEY);
			dssServiceConfigDir = prop.getProperty(Constants.DSS_SERVICE_CONFIG_DIR_KEY,"DSS/ServiceConfig");
			dssServiceDeploymentDir =prop.getProperty(Constants.DSS_SERVICE_DEPLOYMENT_DIR_KEY,"DSS/ServiceConfig");
			apimInstructionDocName=prop.getProperty(Constants.APIM_INSTRUCTION_FILE_NAME_KEY,"insructionDOC.docx");
			apimApisContaingDir=prop.getProperty(Constants.APIM_API_DIR_KEY,"APIM");
			apimApisDeploymentDir=prop.getProperty(Constants.APIM_API_DEPLOYMENT_DIR_KEY,"deployment/APIM-APIs-Deployments");
			apimDeploymentFileNamePostFix=prop.getProperty(Constants.APIM_API_DEPLOYMENT_FILE_POST_FIX_KEY,"APIM-Deployment-File-Name-Post-Fix");
			esbAPIDeploymentFileNamePostFix=prop.getProperty(Constants.ESB_API_DEPLOYMENT_FILE_POST_FIX_KEY,"ESB-API-Deployment-File-Name-Post-Fix");
			esbSequenceDeploymentDirName=prop.getProperty(Constants.ESB_SEQUENCE_DEPLOYMENT_DIR_NAME_KEY,"sequence");
			esbUtilitiesDirName=prop.getProperty(Constants.ESB_UTILITIES_DIR_KEY,"ESB/Utilities");
			esbUtilitiesDeploymentDir=prop.getProperty(Constants.ESB_UTILITIES_DEPLOYMENT_DIR_KEY,"ESB-Utilities-Deployment-Dir");
			esbUtilitiesXSLTDeploymentDirName= prop.getProperty(Constants.ESB_UTILITIES_XSLT_DEPLOYMENT_DIR_NAME_KEY,"ESB-Utilities-xslt-deployment-dir-name");
			esbUtilitiesEPDeploymentDirName = prop.getProperty(Constants.ESB_UTILITIES_EP_DEPLOYMENT_DIR_NAME_KEY);
			esbUtilitiesCommonSequenceDeploymentDirName=prop.getProperty(Constants.ESB_UTILITIES_COMMON_SEQUENCE_DEPLOYMENT_DIR_NAME_KEY);
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

