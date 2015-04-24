package deploymentFactories;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import fileHandlerFactories.FileHandlerAbstractFactory;
import fileHandlerFactories.FileHandlerFacoryMaker;
import Constants.Constants;
import PropertyLoader.DeploymentPropertyLoader;
import Utill.XMLCreatorUtill;
import Utill.XMLCreatorUtillImpl;

public class ESBUtilitiesDeploymentFactory extends DeploymentAbstarctFactory{
	final static Logger logger = Logger.getLogger(ESBUtilitiesDeploymentFactory.class);
	@Override
	public void createDeploymentFile(String outputXML) {
		String svnDir= DeploymentPropertyLoader.getInstanse().getSvnDir();
		String esbUtilitiesDir= svnDir+File.separator+DeploymentPropertyLoader.getInstanse().getEsbUtilitiesDirName();
		FileHandlerAbstractFactory fileHandlerFactory = FileHandlerFacoryMaker.getFileHandlerFactory("esbUtilitiesFileHandler");
		//get XSLTs 
		List<File> utilityFiles = new ArrayList<File>();
		utilityFiles=fileHandlerFactory.getDeploymentFiles(new File(esbUtilitiesDir+File.separator+Constants.ESB_UTILITIES_XSLT_DIR_NAME));
		//copy files
		
		String UtilitiesDeploymentDir = DeploymentPropertyLoader.getInstanse().getSvnDir()+File.separator+Constants.DEPLOYMENT_DIR_NAME+File.separator+outputXML+File.separator+DeploymentPropertyLoader.getInstanse().getEsbUtilitiesDeploymentDir();
		String xsltDeploymentDir =UtilitiesDeploymentDir+File.separator+DeploymentPropertyLoader.getInstanse().getEsbUtilitiesXSLTDeploymentDirName(); 
	
		copyUtilityFiles(utilityFiles,xsltDeploymentDir);
		
		
		//copy End point 
		
		utilityFiles=fileHandlerFactory.getDeploymentFiles(new File(esbUtilitiesDir+File.separator+Constants.ESB_UTILITIES_EP_DIR_NAME));
		String epDeploymentDir =UtilitiesDeploymentDir+File.separator+DeploymentPropertyLoader.getInstanse().getEsbUtilitiesEPDeploymentDirName();
		copyUtilityFiles(utilityFiles, epDeploymentDir);
	
		//copy Comman sequnce
		utilityFiles=fileHandlerFactory.getDeploymentFiles(new File(esbUtilitiesDir+File.separator+Constants.ESB_UTILITIES_COMMON_SEQUENCE_DIR_NAME));
		String CommonSeqDeploymentDir =UtilitiesDeploymentDir+File.separator+DeploymentPropertyLoader.getInstanse().getEsbUtilitiesCommonSequenceDeploymentDirName();
		copyUtilityFiles(utilityFiles, CommonSeqDeploymentDir);
		
	}

	@Override
	public void createDeploymentFile(String outputXML, String... dirs) {
		// TODO Auto-generated method stub
		
	}
	
	private void copyUtilityFiles(List<File> files, String dirPath){
		XMLCreatorUtill xmlCreatorUtil = new XMLCreatorUtillImpl();
		for(File file: files){
			try {
				xmlCreatorUtil.copyFiles(file,dirPath+File.separator+file.getName() );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("error while copy xlsts " + e.getMessage());
			}
		}
	}

}
