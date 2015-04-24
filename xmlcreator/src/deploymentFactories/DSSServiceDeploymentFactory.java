package deploymentFactories;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import fileHandlerFactories.FileHandlerAbstractFactory;
import fileHandlerFactories.FileHandlerFacoryMaker;
import Constants.Constants;
import PropertyLoader.DeploymentPropertyLoader;
import Utill.XMLCreatorUtill;
import Utill.XMLCreatorUtillImpl;

public class DSSServiceDeploymentFactory extends DeploymentAbstarctFactory {
	final static Logger logger = Logger
			.getLogger(DSSServiceDeploymentFactory.class);

	@Override
	public void createDeploymentFile(String outputXML) {
		// get service config dir
		FileHandlerAbstractFactory dssXmlFileHanlderFactory = FileHandlerFacoryMaker
				.getFileHandlerFactory("dssServiceFileHandler");
		String dssServiceConfigFile = DeploymentPropertyLoader.getInstanse()
				.getSvnDir()
				+ File.separator
				+ DeploymentPropertyLoader.getInstanse()
						.getDssServiceConfigDir();
		// get every service config files
		List<File> configFileList = dssXmlFileHanlderFactory
				.getDeploymentFiles(new File(dssServiceConfigFile));
		XMLCreatorUtill xmlCreatorUtill = new XMLCreatorUtillImpl();
		String svnDir = DeploymentPropertyLoader.getInstanse().getSvnDir();
		String dssServiceDeployDir = svnDir
				+ File.separator+Constants.DEPLOYMENT_DIR_NAME+File.separator+outputXML+File.separator
				+ DeploymentPropertyLoader.getInstanse()
						.getDssServiceDeploymentDir()+File.separator;
		// delete output dir
		try {
			logger.info("clear deployment dir" + dssServiceDeployDir);
			xmlCreatorUtill.deleteDir(new File(dssServiceDeployDir));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			logger.error("Error while deleting dir " + dssServiceDeployDir);
			logger.error("Error while deleting dir " + e1.getMessage());
		}
		for (File file : configFileList) {
			String outputFileName = dssServiceDeployDir + File.separator
					+ file.getParentFile().getName()+File.separator
					+ Constants.DSS_SERVICE_DEPLOYMENT_POST_FIX;
			try {
				xmlCreatorUtill.copyFiles(file, outputFileName);
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}

	}

	@Override
	public void createDeploymentFile(String outputXML, String... dirs) {
		// get service config dir

		FileHandlerAbstractFactory dssXmlFileHanlderFactory = FileHandlerFacoryMaker
				.getFileHandlerFactory("dssServiceFileHandler");
		XMLCreatorUtill xmlCreatorUtill = new XMLCreatorUtillImpl();
		String dssServiceConfigFile = DeploymentPropertyLoader.getInstanse()
				.getSvnDir()
				+ File.separator
				+ DeploymentPropertyLoader.getInstanse()
						.getDssServiceConfigDir();

		String svnDir = DeploymentPropertyLoader.getInstanse().getSvnDir();
		String dssServiceDeployDir = svnDir
				+ File.separator+Constants.DEPLOYMENT_DIR_NAME+File.separator+outputXML+File.separator
				+ DeploymentPropertyLoader.getInstanse()
						.getDssServiceDeploymentDir();

		// clear deployment dir
		try {
			logger.info("clear deployment dir" + dssServiceDeployDir);
			xmlCreatorUtill.deleteDir(new File(dssServiceDeployDir));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			logger.error("Error while deleting dir " + dssServiceDeployDir);
			logger.error("Error while deleting dir " + e1.getMessage());
		}

		for (String dir : dirs) {
			List<File> configFileList = dssXmlFileHanlderFactory
					.getDeploymentFiles(new File(dssServiceConfigFile
							+ File.separator + dir));

			for (File file : configFileList) {
				String outputFileName = dssServiceDeployDir + File.separator
						+ file.getParentFile().getName()+File.separator
						+ Constants.DSS_SERVICE_DEPLOYMENT_POST_FIX;
				try {
					xmlCreatorUtill.copyFiles(file, outputFileName);
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
		}

	}

}
