package Utill;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public interface XMLCreatorUtill {

	public  void mergeXML(String initConfigFile, String outputFile, String... XMLFiles)
			throws ParserConfigurationException, SAXException, IOException;


	public  void mergeXML(File initConfigFile, String outputFile, List<File> xmlList)
			throws ParserConfigurationException, SAXException, IOException;
	public  void copyFiles(File soourceFile ,String OutputName) throws IOException;
	
	public void deleteDir(File dir) throws IOException;
}