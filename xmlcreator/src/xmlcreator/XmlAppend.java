package xmlcreator;

 import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

    public class XmlAppend {

        public static void main(String[] args) {
            try {
                File xmlFile = new File("test.xml");
                File xmlFile2 = new File("test2.xml");
                //Create the documentBuilderFactory
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                //Create the documentBuilder
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                //Create the Document  by parsing the file
                Document document = documentBuilder.parse(xmlFile);
                Document document2 = documentBuilder.parse(xmlFile2);
                //Get the root element of the xml Document;
                //System.out.println("firstchildnode "+document.getFirstChild().getNodeName());
                Element documentElement = document.getDocumentElement();
                System.out.println("documentElement:" + documentElement.toString());
                //Get childNodes of the rootElement
                //Create a textNode element
               /* Element textNode = document.createElement("chathura");
                Text value = document.createTextNode("2");
                textNode.setTextContent("2");
                //Create a Node element
                Element nodeElement = document.createElement("newone");
                //append textNode to Node element;
                nodeElement.appendChild(textNode);
                //append Node to rootNode element
                documentElement.appendChild(nodeElement);*/
                Element newNode = document2.getDocumentElement();
                System.out.println("chathura testing "+newNode);
               // document.adoptNode(document2.getFirstChild());
                document.getDocumentElement().appendChild( document.importNode(document2.getFirstChild(), true));
                document.getDocumentElement().appendChild( document.importNode(document2.getFirstChild(), true));
                		document.replaceChild(documentElement, documentElement);
                Transformer tFormer =
                        TransformerFactory.newInstance().newTransformer();
    //  Set output file to xml
                tFormer.setOutputProperty(OutputKeys.METHOD, "xml");
    //  Write the document back to the file
                Source source = new DOMSource(document);
                Result result = new StreamResult(xmlFile);
                tFormer.transform(source, result);


            } catch (SAXException ex) {
                Logger.getLogger(XmlAppend.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(XmlAppend.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(XmlAppend.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TransformerConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerFactoryConfigurationError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }