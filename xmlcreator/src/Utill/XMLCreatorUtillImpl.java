package Utill;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class XMLCreatorUtillImpl implements XMLCreatorUtill {
	/**
	 * Logger instance
	 */
	final static Logger logger = Logger.getLogger(XMLCreatorUtillImpl.class);
	
			
	/* (non-Javadoc)
	 * @see xmlcreator.XMLCreatorUtill#mergeXML(java.lang.String, java.lang.String)
	 */
	@Override
	public void mergeXML(String initConfigFile, String OutputFile, String...XMLFiles) throws ParserConfigurationException, SAXException, IOException{
		logger.info("startmerge files");
			File initConfigfile = new File(initConfigFile);// main confing file
			//Create the documentBuilderFactory
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
          //Create the documentBuilder
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            //document for initial configurations
            Document initDocument = documentBuilder.parse(initConfigfile);
            
            Element initDocumentElement = initDocument.getDocumentElement();
            
            for(String filename : XMLFiles){
            	File resourceFile = new File(filename);
            	//Create the Document  by parsing the file
                Document document = documentBuilder.parse(resourceFile);
                
                initDocument.getDocumentElement().appendChild( initDocument.importNode(document.getFirstChild(), true));
            }
            
            
            initDocument.replaceChild(initDocumentElement, initDocumentElement);
            
            try {
				writeToXML(initDocument,new File(OutputFile));
			} catch (TransformerFactoryConfigurationError e) {
				logger.error("Error in Transform Factry creation "+e.getMessage());
			} catch (TransformerException e) {
				logger.error("Error in Transform "+e.getMessage());
			}
            
         

      
    }
	
	/**
	 * 
	 * @param doc
	 * @param outputFile
	 * @throws TransformerFactoryConfigurationError
	 * @throws TransformerException
	 */
	private void writeToXML(Document doc, File outputFile) throws TransformerFactoryConfigurationError, TransformerException{
		
        Transformer tFormer =
                TransformerFactory.newInstance().newTransformer();
        tFormer.setOutputProperty(OutputKeys.METHOD, "xml");
        tFormer.setOutputProperty(OutputKeys.INDENT, "yes");
        tFormer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        tFormer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

        
		Source source = new DOMSource(doc);
		if(outputFile.exists()){
			outputFile.delete();
		}
			
			try {
				File parent = outputFile.getParentFile();
				if(!parent.exists() && !parent.mkdirs()){
					logger.info("Couldn't create dir: " + parent);
				   throw new IllegalStateException("Couldn't create dir: " + parent);
				}
				//outputFile.createNewFile();
			} catch(IllegalStateException e){
				
			}
		
        Result result = new StreamResult(outputFile);
        tFormer.transform(source, result);

	}
/**
 * 
 */
	@Override
	public void mergeXML(File initConfigFile, String outputFile,
			List<File> xmlList) throws ParserConfigurationException,
			SAXException, IOException {
 
		logger.info("startmerge files");
		
		//Create the documentBuilderFactory
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
      //Create the documentBuilder
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        //document for initial configurations
        Document initDocument = documentBuilder.parse(initConfigFile);
                
        Element initDocumentElement = initDocument.getDocumentElement();
        
        //this hack for remove first child in main config file 
        //it only for example
       Element element =(Element) initDocument.getElementsByTagName("resource").item(0);
       if(element != null){
       element.getParentNode().removeChild(element);
       }
     // System.out.println("chathura testing "+nodelist.getLength());
       
        for(File file : xmlList){
        	
        	//Create the Document  by parsing the file
            Document document = documentBuilder.parse(file);
            
            initDocument.getDocumentElement().appendChild( initDocument.importNode(document.getFirstChild(), true));
            logger.info( "File merged :"+file.getParentFile());
        }
        
        
        initDocument.replaceChild(initDocumentElement, initDocumentElement);
        
        try {
        	logger.info("file write " +outputFile );
        	writeToXML(initDocument,new File(outputFile));
		} catch (TransformerFactoryConfigurationError e) {
			logger.error("Error in Transform Factry creation "+e.getMessage());
		} catch (TransformerException e) {
			logger.error("Error in Transform "+e.getMessage());
		}

		
		
	}

@Override
public void copyFiles(File soourceFile, String OutputName) throws IOException {
	logger.info("copy file "+soourceFile +" to " +OutputName);
	FileUtils.copyFile(soourceFile, new File(OutputName));
	
}

public void deleteDir(File dir) throws IOException{
	FileUtils.deleteDirectory(dir);
}
	
	
	
}
