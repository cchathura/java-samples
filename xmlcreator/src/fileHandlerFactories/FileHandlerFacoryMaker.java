package fileHandlerFactories;

public class FileHandlerFacoryMaker {
public static FileHandlerAbstractFactory getFileHandlerFactory(String Choice){
	if(Choice.equalsIgnoreCase("esbapifilehandler")){
		return new EsbXmlFileHandlerForAPIs();
	}else if(Choice.equalsIgnoreCase("dssServiceFileHandler")){
		return new DssXmlFileHandlerForServices();
	}else if(Choice.equalsIgnoreCase("apimApiFileHandler")){
		return new APIMApiFilehandler();
	}else if(Choice.equalsIgnoreCase("esbUtilitiesFileHandler")){
		return new EsbUtilitiesFileHandler();
	}
	return null;
}
}
