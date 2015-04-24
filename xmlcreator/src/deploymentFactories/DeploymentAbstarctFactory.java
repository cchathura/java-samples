package deploymentFactories;

import java.io.File;
import java.util.List;

/**
 * @author camarathunga
 *
 */
public abstract class DeploymentAbstarctFactory {
	

	/**
	 * @param outputXML
	 */
	public abstract void createDeploymentFile(String outputXML);
	/**
	 * @param outputXML
	 * @param dirs
	 */
	public abstract void createDeploymentFile(String outputXML, String...dirs);
}
