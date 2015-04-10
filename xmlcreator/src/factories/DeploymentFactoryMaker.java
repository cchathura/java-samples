package factories;

public class DeploymentFactoryMaker {

	public static DeploymentAbstarctFactory getDeploymentFactory(String choice){
		if(choice.equalsIgnoreCase("esbapiDeployment")){
			return new ESBAPIDeploymentFactory();
		}
		return null;
	}
}
