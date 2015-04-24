package fileHandlerFactories;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;

import Constants.Constants;
import PropertyLoader.DeploymentPropertyLoader;

public class APIMApiFilehandler extends FileHandlerAbstractFactory {

	@Override
	public List<File> getMergingXMLFiles(File dir) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File getInitXMLFile(File dir) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<File> getMergingXMLFilesWithExclude(File dir) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see factories.FileHandlerAbstractFactory#getConfigXMLs(java.io.File) in
	 * here return the instruction manual as cofiguration files because in APIM,
	 * most of the time doing manual configurations
	 */
	@Override
	public List<File> getDeploymentFiles(File dir) {
		List<File> instructionFiles = new ArrayList<File>();
		if (dir.exists()) {
			Collection<File> files = FileUtils.listFilesAndDirs(dir,
					new RegexFileFilter("^*"), DirectoryFileFilter.DIRECTORY);
			for (File file : files) {
				if (file.isDirectory()) {
					if (new File(file, DeploymentPropertyLoader.getInstanse()
							.getApimInstructionDocName()).exists()
							&& !(new File(file, "release.properties").exists())) {
						instructionFiles.add(new File(file,
								DeploymentPropertyLoader.getInstanse()
										.getApimInstructionDocName()));
					} else if (new File(file, DeploymentPropertyLoader
							.getInstanse().getApimInstructionDocName())
							.exists()
							&& new File(file, "release.properties").exists()) {
						Properties prop = new Properties();
						InputStream input = null;
						// to check prorty
						try {

							input = new FileInputStream(file.getAbsolutePath()
									+ File.separator + "release.properties");
							prop.load(input);
							if (prop.getProperty(
									Constants.RELEASE_PROPERTY_IS_DEPLOY_KEY,
									"No").equalsIgnoreCase("yes")) {
								instructionFiles
										.add(new File(
												file,
												DeploymentPropertyLoader.getInstanse()
												.getApimInstructionDocName()));
							}
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} finally {
							if (input != null) {
								try {
									input.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}

							// load a properties file

						}
					}

				}
			}

		}
		return instructionFiles;
	}
}
