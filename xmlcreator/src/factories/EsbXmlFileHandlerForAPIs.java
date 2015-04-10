package factories;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.log4j.Logger;

import Constants.Constants;

/**
 * 
 * @author camarathunga
 * 
 */
public class EsbXmlFileHandlerForAPIs extends FileHandlerAbstractFactory {
	/**
	 * logger instace for login
	 */
	final static Logger logger = Logger
			.getLogger(EsbXmlFileHandlerForAPIs.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * factories.FileHandlerAbstractFactory#getMergingXMLFiles(java.io.File)
	 * 
	 * @param merge file included directory
	 */
	@Override
	public List<File> getMergingXMLFiles(File dir) {

		// File OperationsDir = new File(dir);
		if (dir.exists()) {
			Collection<File> files = FileUtils
					.listFiles(
							dir,
							new RegexFileFilter(
									Constants.ESB_API_OPERATIONS_MAIN_CONFIG_FILE_NAME),
							DirectoryFileFilter.DIRECTORY);
			if (files.size() == 0) {
				logger.warn(Constants.ESB_API_OPERATIONS_MAIN_CONFIG_FILE_NAME
						+ "not availble in " + dir);
				return null;
			}
			List<File> fileList = new ArrayList<File>(files);

			return fileList;
		} else {
			logger.warn("directory not available" + dir);
			return null;
		}
	}

	public List<File> getMergingXMLFilesWithExclude(File dir) {
		if (dir.exists()) {
			// File OperationsDir = new File(dir);
			List<File> fileList = new ArrayList<File>();
			Collection<File> files = FileUtils.listFilesAndDirs(dir,
					new RegexFileFilter("^*"), DirectoryFileFilter.DIRECTORY);

			for (File file : files) {
				if (file.isDirectory()) {

					if (!(new File(file, "release.properties").exists())
							&& new File(
									file,
									Constants.ESB_API_OPERATIONS_MAIN_CONFIG_FILE_NAME)
									.exists()) {
						System.out.println("name " + file.getAbsolutePath());
						fileList.add(new File(
								file,
								Constants.ESB_API_OPERATIONS_MAIN_CONFIG_FILE_NAME));
					}

				}
			}
			if (fileList.size() <= 0) {
				return null;
			} else {

				return fileList;
			}
		} else {
			logger.warn("directory not available" + dir);
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see factories.FileHandlerAbstractFactory#getInitXMLFile(java.io.File)
	 * 
	 * @param initconfig file containg dierctory
	 */
	@Override
	public File getInitXMLFile(File dir) {
		File initConfigFile = new File(dir + File.separator
				+ Constants.ESB_API_INIT_CONFIG_FILE_NAME);
		if (initConfigFile.exists()) {
			return initConfigFile;
		} else {
			logger.warn(Constants.ESB_API_INIT_CONFIG_FILE_NAME
					+ "not availble in " + dir);
			return null;
		}

	}

}
