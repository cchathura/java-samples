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
		options.addOption("r", "Release", true, "Here you can set Relese version number");
		options.addOption("c" ,"DeploymentComponent",true, "here you can set deploycomponent");
		options.addOption("s", "Sources", true, "Here you can set Source for deployment");
		//options.addOption("o", "outputFile", true, "set output parameter.");

	}

	public void parse() {
		System.out.println("inside parse");

		CommandLineParser parser = new BasicParser();

		CommandLine cmd = null;

		try {

			cmd = parser.parse(options, args);

			//TODO set option to which server need to deploy 
			
			if (cmd.hasOption("r") && cmd.hasOption("c")) {
				logger.info("Set release version" + cmd.getOptionValue("r") );
				logger.info("Set Deployment component as " + cmd.getOptionValue("c") );
				//get relevant deployment factory
				//DeploymentAbstarctFactory deploymentFactory =DeploymentFactoryMaker.getDeploymentFactory(cmd.getOptionValue("c"));
			//	deploymentFactory.createDeploymentFile(cmd.getOptionValue("r"));
				//String[] sources = cmd.getOptionValue("s").split(",");
				String release =cmd.getOptionValues("r")[0];
				if(cmd.getOptionValues("c")[0].equals("esb")){
					
					
					if(cmd.hasOption("s")){
						String[] sources = cmd.getOptionValue("s").split(",");
						deployESB(release,sources);
					}else{
						deployESB(release);
					}
				}else if(cmd.getOptionValues("c")[0].equals("dss")){
					if(cmd.hasOption("s")){
						String[] sources = cmd.getOptionValue("s").split(",");
						deployDSS(release,sources);
					}else{
						deployDSS(release);
					}
				}else if(cmd.getOptionValues("c")[0].equals("apim")){
					if(cmd.hasOption("s")){
						String[] sources = cmd.getOptionValue("s").split(",");
						deployAPIM(release,sources);
					}else{
						deployAPIM(release);
					}
				}
				//logger.info("run for selected APIs");
				
				

			}else if(cmd.hasOption("r") && cmd.hasOption("c") && cmd.hasOption("s"))
			{
				String[] sources = cmd.getOptionValue("s").split(",");
				if(cmd.getOptionValues("c")[0].equals("esb")){
					String release =cmd.getOptionValues("r")[0];
					deployESB(release,sources);
				}
			}
			/*else if(cmd.hasOption("r") && cmd.hasOption("c") && cmd.hasOption("s")){	
				logger.info("Set release version" + cmd.getOptionValue("r") );
				logger.info("Set Deployment component as " + cmd.getOptionValue("c") );
				logger.info("Set sources as " + cmd.getOptionValue("s") );
				//get relevant deployment factory
				DeploymentAbstarctFactory deploymentFactory =DeploymentFactoryMaker.getDeploymentFactory(cmd.getOptionValue("c"));
				String[] sources = cmd.getOptionValue("s").split(",");
				deploymentFactory.createDeploymentFile(cmd.getOptionValue("r"),sources);
			} */
			else {
				logger.info("no argumet parse");
				help();
			}

		} catch (ParseException e) {

			logger.info("Failed to parse comand line properties", e);

		}

	}
	
	
private void help(){
	logger.info("Usage");
	logger.info("-r -> Here you can set Relese version number");
	logger.info("-c -> here you can set deploycomponent " );
	logger.info("-s -> here you can set sources to deploy and this is optional " );

	
}
private void deployESB(String Release, String...dir){
	//dploy APIs
	logger.info("start deployESB");
		
			if(dir.length>0){
				DeploymentAbstarctFactory deploymentFactory =DeploymentFactoryMaker.getDeploymentFactory("esbapiDeployment");
				deploymentFactory.createDeploymentFile(Release,dir);
				deploymentFactory =DeploymentFactoryMaker.getDeploymentFactory("esbUtilitiesFileHandler");
				deploymentFactory.createDeploymentFile(Release);
			}else{
			DeploymentAbstarctFactory deploymentFactory =DeploymentFactoryMaker.getDeploymentFactory("esbapiDeployment");
			deploymentFactory.createDeploymentFile(Release);
			deploymentFactory =DeploymentFactoryMaker.getDeploymentFactory("esbUtilitiesFileHandler");
			deploymentFactory.createDeploymentFile(Release);
			}
		
	
	
}

private void deployDSS(String Release, String...dir){
	//dploy APIs
	logger.info("start deployDSS");
		
			if(dir.length>0){
				DeploymentAbstarctFactory deploymentFactory =DeploymentFactoryMaker.getDeploymentFactory("dssserviceDeployment");
				deploymentFactory.createDeploymentFile(Release,dir);
				
			}else{
			DeploymentAbstarctFactory deploymentFactory =DeploymentFactoryMaker.getDeploymentFactory("dssserviceDeployment");
			deploymentFactory.createDeploymentFile(Release);
			
			}
		
	
	
}

private void deployAPIM(String Release, String...dir){
	
	logger.info("start deployAPIM");
		
			if(dir.length>0){
				DeploymentAbstarctFactory deploymentFactory =DeploymentFactoryMaker.getDeploymentFactory("apimApiDeployment");
				deploymentFactory.createDeploymentFile(Release,dir);
				
			}else{
			DeploymentAbstarctFactory deploymentFactory =DeploymentFactoryMaker.getDeploymentFactory("apimApiDeployment");
			deploymentFactory.createDeploymentFile(Release);
			
			}
		
	
	
}



}
