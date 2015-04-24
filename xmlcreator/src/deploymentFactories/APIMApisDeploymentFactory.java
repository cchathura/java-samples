package deploymentFactories;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;

import org.apache.log4j.Logger;

import Constants.Constants;
import PropertyLoader.DeploymentPropertyLoader;
import Utill.XMLCreatorUtill;
import Utill.XMLCreatorUtillImpl;
import fileHandlerFactories.FileHandlerAbstractFactory;
import fileHandlerFactories.FileHandlerFacoryMaker;

/**
 * @author camarathunga
 *
 */
public class APIMApisDeploymentFactory extends DeploymentAbstarctFactory {
	final static Logger logger = Logger
			.getLogger(APIMApisDeploymentFactory.class);
	/* (non-Javadoc)
	 * @see deploymentFactories.DeploymentAbstarctFactory#createDeploymentXML(java.lang.String)
	 */
	@Override
	public void createDeploymentFile(String outputXML) {
		FileHandlerAbstractFactory fileHandlerFactory = FileHandlerFacoryMaker.getFileHandlerFactory("apimApiFileHandler");
		
		//iterate over API directories
		String svnPath=DeploymentPropertyLoader.getInstanse().getSvnDir();
		String apimApiDirPath =svnPath+File.separator+DeploymentPropertyLoader.getInstanse().getApimApisContaingDir();
		String apimApiDeployDir=svnPath+File.separator+Constants.DEPLOYMENT_DIR_NAME+File.separator+outputXML+File.separator+DeploymentPropertyLoader.getInstanse().getApimApisDeploymentDir()+File.separator;
		
		List<File> instructionDocs =fileHandlerFactory.getDeploymentFiles(new File(apimApiDirPath));
		XMLCreatorUtill xmlCreatorUtill = new XMLCreatorUtillImpl();
		try {
			xmlCreatorUtill.deleteDir(new File(apimApiDeployDir));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			logger.error("error while delete file " + e1.getMessage());
		}
		for(File file: instructionDocs){
			
			String deploymentFile = apimApiDeployDir+File.separator+file.getParentFile().getName()+File.separator+DeploymentPropertyLoader.getInstanse().getApimDeploymentFileNamePostFix();
			try {
				xmlCreatorUtill.copyFiles(file,deploymentFile);
			} catch (IOException e) {
				
				logger.error("error while copy file " + e.getMessage());
			}
		}
		
	}

	@Override
	public void createDeploymentFile(String outputXML, String... dirs) {
		
		
		FileHandlerAbstractFactory fileHandlerFactory = FileHandlerFacoryMaker.getFileHandlerFactory("apimApiFileHandler");
		
		//iterate over API directories
		String svnPath=DeploymentPropertyLoader.getInstanse().getSvnDir();
		String apimApiDirPath =svnPath+File.separator+DeploymentPropertyLoader.getInstanse().getApimApisContaingDir();
		String apimApiDeployDir=svnPath+File.separator+Constants.DEPLOYMENT_DIR_NAME+File.separator+outputXML+File.separator+DeploymentPropertyLoader.getInstanse().getApimApisDeploymentDir()+File.separator;
		
		XMLCreatorUtill xmlCreatorUtill = new XMLCreatorUtillImpl();
		//clear deployment dir
		try {
			xmlCreatorUtill.deleteDir(new File(apimApiDeployDir));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			logger.error("error while delete file " + e1.getMessage());
		}
		for(String fileName : dirs){
			if(new File(apimApiDirPath+File.separator+fileName).exists()){
				List<File> files =fileHandlerFactory.getDeploymentFiles(new File(apimApiDirPath+File.separator + fileName));
				for(File file : files){
					String outputFilename=apimApiDeployDir+File.separator+fileName+File.separator+DeploymentPropertyLoader.getInstanse().getApimDeploymentFileNamePostFix();
					try {
						xmlCreatorUtill.copyFiles(file,outputFilename);
					} catch (IOException e) {
						
						logger.error("error while copy file " + e.getMessage());
					}
				}
			}
		}
		
		
	}

}
