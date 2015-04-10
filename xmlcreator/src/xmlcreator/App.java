package xmlcreator;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import PropertyLoader.PropertyLoader;
import factories.DeploymentAbstarctFactory;
import factories.DeploymentFactoryMaker;

public class App {

	public static void main(String[] args) {
		DeploymentAbstarctFactory deploymentFactory =DeploymentFactoryMaker.getDeploymentFactory("esbapiDeployment");
		//deploymentFactory.createDeploymentXML(PropertyLoader.getInstanse().getDeploymentFile());
		String argument = "EmployeeAPI";
		String[] argarr=argument.split(",");
		deploymentFactory.createDeploymentXML(PropertyLoader.getInstanse().getDeploymentFile(),argarr);
		 //new CLI(args).parse();

	}

}
