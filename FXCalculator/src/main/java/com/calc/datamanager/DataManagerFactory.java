package com.calc.datamanager;

/**
 * This class is responsible to instantiate datamanager object depending on the type provided.
 * Ideally object instantiation should be replaced with a framework like Spring Dependency Injection but given usage of external libraries is out of scope
 * of this application, hence this factory class would serve the purpose.
 *
 */
public class DataManagerFactory {
	
	public static final String DB = "DB";
	
	public static final String FILE = "FILE";
	
	private static DataManager dataManager ;
	
	public static DataManager getDataManager(String type) {
		if( dataManager != null ) {
			return dataManager;
		}
		switch(type){
	        case FILE:
	        	dataManager = new FileDataManagerImpl();
	        	break;
	        case DB:
	        	dataManager = new DBDataManagerImpl();
	        	break;
	    }
		return dataManager;
	}

}
