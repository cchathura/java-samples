package factories;

import java.io.File;
import java.util.List;

/**
 * @author camarathunga
 *
 */
public abstract class DeploymentAbstarctFactory {
	
	/**
	 * @param dir
	 * @return
	 */
	public abstract List<String> getMergingXMLS(String dir);
	/**
	 * @param dir
	 * @return
	 */
	public abstract File getInitXML(String dir);
	/**
	 * @param outputXML
	 */
	public abstract void createDeploymentXML(String outputXML);
	/**
	 * @param outputXML
	 * @param dirs
	 */
	public abstract void createDeploymentXML(String outputXML, String...dirs);
}
