package deploymentFactories;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.apache.log4j.helpers.Loader;
import org.xml.sax.SAXException;

import fileHandlerFactories.FileHandlerAbstractFactory;
import fileHandlerFactories.FileHandlerFacoryMaker;
import Constants.Constants;
import PropertyLoader.DeploymentPropertyLoader;
import Utill.XMLCreatorUtill;
import Utill.XMLCreatorUtillImpl;



public class ESBAPIDeploymentFactory extends DeploymentAbstarctFactory {
	final static Logger logger = Logger.getLogger(ESBAPIDeploymentFactory.class);
	
	
	/* (non-Javadoc)
	 * @see factories.DeploymentAbstarctFactory#createDeploymentXML(java.lang.String)
	 */
	@Override
	public
	void createDeploymentFile(String outputXML) {
		//get all include APIs
		
		File apiDir = new File(DeploymentPropertyLoader.getInstanse().getSvnDir()+File.separator+Constants.ESB_APIs_DIRECTORY);
	
		List<File> files = Arrays.asList(apiDir.listFiles());
		//get file handler factory
		FileHandlerAbstractFactory filehandlerfactory = FileHandlerFacoryMaker.getFileHandlerFactory("esbapifilehandler");
		// to hold svn directory
		String svnDir = DeploymentPropertyLoader.getInstanse().getSvnDir()+File.separator; 
		// service/esb/apis
		
		String esbSequenceDeployDirName= DeploymentPropertyLoader.getInstanse().getEsbSequenceDeploymentDirName();
		
		for(File file : files){
			if(file.isFile()){
				continue;
			}
			File initConfigFile=filehandlerfactory.getInitXMLFile(file);
			List<File> xmlfiles = filehandlerfactory.getMergingXMLFilesWithExclude(new File(file.getAbsolutePath()+File.separator+Constants.ESB_APIs_OPERATIONS_DIRECTORY));
			XMLCreatorUtill xmlCreatorUtill = new XMLCreatorUtillImpl();
			if(initConfigFile!=null && xmlfiles !=null){
				//to hold deployment scrript path and file name 
				//file save at svn root folder + path from deployment.propery file
				String deploymentDir=svnDir+Constants.DEPLOYMENT_DIR_NAME+File.separator+outputXML+File.separator+DeploymentPropertyLoader.getInstanse().getEsbApiDeploymentDir()+File.separator+file.getName();
				String deploymentScriptFile =deploymentDir+File.separator +DeploymentPropertyLoader.getInstanse().getEsbAPIDeploymentFileNamePostFix();
				
				//copy sequnce files
				List<File> sequnceList = new ArrayList<File>();
				String operationsDir = file.getAbsolutePath()+File.separator+Constants.ESB_APIs_OPERATIONS_DIRECTORY;
				if(new File(operationsDir).exists()){
				 sequnceList = filehandlerfactory.getDeploymentFiles(new File(operationsDir));
				}
				
			try {
				xmlCreatorUtill.mergeXML(initConfigFile, deploymentScriptFile, xmlfiles);
				for(File sefile : sequnceList){
					xmlCreatorUtill.copyFiles(sefile, deploymentDir+File.separator+esbSequenceDeployDirName+File.separator+sefile.getName());
				}
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}else{
				logger.warn("expected xml files notavailbe in " + file);
			}
		}
		
	}

	
	/**
	 * @param outputXML
	 * @param dirs
	 */
	public
	void createDeploymentFile(String outputXML, String...dirs) {
		
		FileHandlerAbstractFactory filehandlerfactory = FileHandlerFacoryMaker.getFileHandlerFactory("esbapifilehandler");
		// service/esb/apis
		//to hold svn directory
		String svnDir = DeploymentPropertyLoader.getInstanse().getSvnDir()+File.separator;
		String apisDirPath = DeploymentPropertyLoader.getInstanse().getSvnDir()+File.separator+Constants.ESB_APIs_DIRECTORY+File.separator;
		String esbSequenceDeployDirName= DeploymentPropertyLoader.getInstanse().getEsbSequenceDeploymentDirName();
		for(String dirName : dirs){
			File apiDir = new File(apisDirPath+dirName);
			if(apiDir.isFile()){
				continue;
			}
			
			
			File initConfigFile=filehandlerfactory.getInitXMLFile(apiDir);
			
			List<File> xmlfiles = filehandlerfactory.getMergingXMLFilesWithExclude(new File(apiDir.getAbsolutePath()+File.separator+Constants.ESB_APIs_OPERATIONS_DIRECTORY));
			
			XMLCreatorUtill xmlCreatorUtill = new XMLCreatorUtillImpl();
			if(initConfigFile!=null && xmlfiles !=null){
				
				String deploymentDir=Constants.DEPLOYMENT_DIR_NAME+File.pathSeparator+outputXML+File.separator+DeploymentPropertyLoader.getInstanse().getEsbApiDeploymentDir()+File.separator+dirName;
				String deploymentScriptFile =deploymentDir+File.separator+DeploymentPropertyLoader.getInstanse().getEsbAPIDeploymentFileNamePostFix();
				
				//copy sequnce files
				List<File> sequnceList = new ArrayList<File>();
				String operationsDir = apiDir.getAbsolutePath()+File.separator+Constants.ESB_APIs_OPERATIONS_DIRECTORY;
				if(new File(operationsDir).exists()){
				 sequnceList = filehandlerfactory.getDeploymentFiles(new File(operationsDir));
				}
			try {
				xmlCreatorUtill.mergeXML(initConfigFile, svnDir+deploymentScriptFile, xmlfiles);
				//copy sequnce xmls
				for(File file : sequnceList){
					xmlCreatorUtill.copyFiles(file, svnDir+deploymentDir+File.separator+esbSequenceDeployDirName+File.separator+file.getName());
				}
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}else{
				logger.warn("expected xml files notavailbe in " + apiDir);
			}
		}
		
	}
}
