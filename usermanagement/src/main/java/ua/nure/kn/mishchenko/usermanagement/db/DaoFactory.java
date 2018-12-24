package ua.nure.kn.mishchenko.usermanagement.db;

import java.io.IOException;
import java.util.Properties;

public abstract class DaoFactory {
	
	protected static final String DAO_FACTORY = "dao.factory";
	protected static final String USER_DAO = "dao.UserDao";
	protected static Properties properties;
	
	private static DaoFactory instance;
	
	static {
		properties = new Properties();
		try {
			properties.load(DaoFactory.class.getClassLoader().getResourceAsStream("settings.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static DaoFactory getInstance() {
		if(instance == null) {
			Class<?> factoryClass;
			try {
				factoryClass = Class.forName(properties.getProperty(DAO_FACTORY));
				instance = (DaoFactory) factoryClass.newInstance();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return instance;
	}
	
	protected DaoFactory() {
	}
	
	protected ConnectionFactory getConnectionFactory() {
		
		return new ConnectionFactoryImpl(properties);
	}
	
	public abstract UserDao getUserDao();
	
	public static void init(Properties prop) {
		properties = prop;
		instance = null;
	}

}
