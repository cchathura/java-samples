package deploymentFactories;

public class DeploymentFactoryMaker {

	public static DeploymentAbstarctFactory getDeploymentFactory(String choice){
		if(choice.equalsIgnoreCase("esbapiDeployment")){
			return new ESBAPIDeploymentFactory();
		}else if(choice.equalsIgnoreCase("dssserviceDeployment")){
			return new DSSServiceDeploymentFactory();
		}else if(choice.equalsIgnoreCase("apimApiDeployment")){
			return new APIMApisDeploymentFactory();
		}else if(choice.equalsIgnoreCase("esbUtilitiesFileHandler")){
			return new ESBUtilitiesDeploymentFactory();
		}
		
		return null;
	}
}
