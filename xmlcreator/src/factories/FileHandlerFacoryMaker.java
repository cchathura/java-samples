package factories;

public class FileHandlerFacoryMaker {
public static FileHandlerAbstractFactory getFileHandlerFactory(String Choice){
	if(Choice.equalsIgnoreCase("esbapifilehandler")){
		return new EsbXmlFileHandlerForAPIs();
	}
	return null;
}
}
