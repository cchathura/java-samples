package factories;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.apache.log4j.helpers.Loader;
import org.xml.sax.SAXException;

import xmlcreator.XMLCreatorUtill;
import xmlcreator.XMLCreatorUtillImpl;
import Constants.Constants;
import PropertyLoader.PropertyLoader;



public class ESBAPIDeploymentFactory extends DeploymentAbstarctFactory {
	final static Logger logger = Logger.getLogger(ESBAPIDeploymentFactory.class);
	@Override
	public
	List<String> getMergingXMLS(String dir) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public
	File getInitXML(String dir) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	@Override
	public
	void createDeploymentXML(String outputXML) {
		//get all include APIs
		
		File apiDir = new File(PropertyLoader.getInstanse().getSvnDir()+File.separator+Constants.ESB_APIs_Directory);
	
		List<File> files = Arrays.asList(apiDir.listFiles());
		//get file handler factory
		FileHandlerAbstractFactory filehandlerfactory = FileHandlerFacoryMaker.getFileHandlerFactory("esbapifilehandler");
		// service/esb/apis
		for(File file : files){
			if(file.isFile()){
				continue;
			}
			File initConfigFile=filehandlerfactory.getInitXMLFile(file);
			List<File> xmlfiles = filehandlerfactory.getMergingXMLFilesWithExclude(new File(file.getAbsolutePath()+File.separator+Constants.ESB_APIs_OPERATIONS_DIRECTORY));
			XMLCreatorUtill xmlCreatorUtill = new XMLCreatorUtillImpl();
			if(initConfigFile!=null && xmlfiles !=null){
			try {
				xmlCreatorUtill.mergeXML(initConfigFile, file.getAbsolutePath()+File.separator+outputXML, xmlfiles);
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
	void createDeploymentXML(String outputXML, String...dirs) {
		
		FileHandlerAbstractFactory filehandlerfactory = FileHandlerFacoryMaker.getFileHandlerFactory("esbapifilehandler");
		// service/esb/apis
		for(String dirName : dirs){
			File apiDir = new File(PropertyLoader.getInstanse().getSvnDir()+File.separator+Constants.ESB_APIs_Directory+File.separator+dirName);
			if(apiDir.isFile()){
				continue;
			}
			File initConfigFile=filehandlerfactory.getInitXMLFile(apiDir);
			List<File> xmlfiles = filehandlerfactory.getMergingXMLFilesWithExclude(new File(apiDir.getAbsolutePath()+File.separator+Constants.ESB_APIs_OPERATIONS_DIRECTORY));
			XMLCreatorUtill xmlCreatorUtill = new XMLCreatorUtillImpl();
			if(initConfigFile!=null && xmlfiles !=null){
			try {
				xmlCreatorUtill.mergeXML(initConfigFile, apiDir.getAbsolutePath()+File.separator+outputXML, xmlfiles);
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
