package xmlcreator;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import deploymentFactories.DeploymentAbstarctFactory;
import deploymentFactories.DeploymentFactoryMaker;
import PropertyLoader.DeploymentPropertyLoader;

public class App {

	public static void main(String[] args) {
		//DeploymentAbstarctFactory deploymentFactory =DeploymentFactoryMaker.getDeploymentFactory("esbapiDeployment");
		//deploymentFactory.createDeploymentXML(DeploymentPropertyLoader.getInstanse().getDeploymentFile());
		//String argument = "EmployeeAPI";
		//String argument = "Attendance,OnBoarding_Services";
		//String[] argarr=argument.split(",");
	    //deploymentFactory.createDeploymentXML(DeploymentPropertyLoader.getInstanse().getDeploymentFile(),argarr);
		 //new CLI(args).parse();
	   // DeploymentAbstarctFactory deploymentFactory =DeploymentFactoryMaker.getDeploymentFactory("dssserviceDeployment");
	   // deploymentFactory.createDeploymentXML(DeploymentPropertyLoader.getInstanse().getDeploymentFile(),argarr);
		
		//test DSS
		/*String argument = "Attendance,OnBoarding_Services";
		String[] argarr=argument.split(",");
		DeploymentAbstarctFactory deploymentFactory =DeploymentFactoryMaker.getDeploymentFactory("dssserviceDeployment");
		deploymentFactory.createDeploymentXML("Release1.0");*/
		
		//test esb
	/*	DeploymentAbstarctFactory deploymentFactory =DeploymentFactoryMaker.getDeploymentFactory("esbapiDeployment");
		//deploymentFactory.createDeploymentXML("Realease-1.1");
		String argument = "EmployeeAPI";
		String[] argarr=argument.split(",");
		//deploymentFactory.createDeploymentFile("Realease-1.1",argarr);
		deploymentFactory.createDeploymentFile("Realease-1.1");
		//test apim
		/*
		String argument = "EmployeeAPI,ProjectAPI";
		String[] argarr=argument.split(",");
	    DeploymentAbstarctFactory deploymentFactory =DeploymentFactoryMaker.getDeploymentFactory("apimApiDeployment");
	    deploymentFactory.createDeploymentXML("Release-1.0",argarr);*/
		
		
		//test utills
		//DeploymentAbstarctFactory deploymentFactory =DeploymentFactoryMaker.getDeploymentFactory("esbUtilitiesFileHandler");
		//deploymentFactory.createDeploymentFile("Realease-1.1");
		new CLI(args).parse();
	}

}
