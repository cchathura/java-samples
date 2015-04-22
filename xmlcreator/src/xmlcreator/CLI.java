package xmlcreator;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;

import deploymentFactories.DeploymentAbstarctFactory;
import deploymentFactories.DeploymentFactoryMaker;
import PropertyLoader.DeploymentPropertyLoader;

public class CLI {
	final static Logger logger = Logger.getLogger(CLI.class);
	private String[] args = null;

	private Options options = new Options();

	public CLI(String[] args) {
		this.args = args;
		options.addOption("h", "help", false, "show help.");
		options.addOption("v", "var", true, "Here you can set parameter .");
		//options.addOption("o", "outputFile", true, "set output parameter.");

	}

	public void parse() {
		System.out.println("inside parse");

		CommandLineParser parser = new BasicParser();

		CommandLine cmd = null;

		try {

			cmd = parser.parse(options, args);

			//TODO set option to which server need to deploy 
			
			if (cmd.hasOption("v")) {
				logger.info("Using cli argument -v=" + cmd.getOptionValue("v"));
				String[] apinames = cmd.getOptionValue("v").split(",");
				logger.info("run for selected APIs");
				DeploymentAbstarctFactory deploymentFactory =DeploymentFactoryMaker.getDeploymentFactory("esbapiDeployment");
				deploymentFactory.createDeploymentXML(DeploymentPropertyLoader.getInstanse().getDeploymentFile(), apinames);

				// Whatever you want to do with the setting goes here

			} else {
				logger.info("run for all APIs to create deployment xml");
				createDeploymentXmlAllApi();
			}

		} catch (ParseException e) {

			logger.info("Failed to parse comand line properties", e);

		}

	}
	
	void createDeploymentXmlAllApi(){
		DeploymentAbstarctFactory deploymentFactory =DeploymentFactoryMaker.getDeploymentFactory("esbapiDeployment");
		deploymentFactory.createDeploymentXML(DeploymentPropertyLoader.getInstanse().getDeploymentFile());
	}

}
