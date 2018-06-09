public class DynamicClassKit {

	public static Object getInstanceOf(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessError, IllegalAccessException, LinkageError {
	    return getInstanceOf(className, className.getClass().getClassLoader());
    }

	public static Object getInstanceOf(String className, ClassLoader classLoader) throws ClassNotFoundException, InstantiationException, IllegalAccessError, IllegalAccessException, LinkageError {
		String errMsg= "";
		Class c= null;
		Object obj= null;

		try {
			c= Class.forName(className, true, classLoader);

			if (c != null) {
				obj= c.newInstance();
			} else {
				errMsg= "class.forName() returned null when attempting to load '"+ className +"'";
				throw new InstantiationException(errMsg);
			}
			
		} catch (ClassNotFoundException cnfx) {
			errMsg= "unkown resource '"+ className +"' [ClassNotFoundException]";
			throw new ClassNotFoundException(errMsg, cnfx);
		} catch (InstantiationException ix) {
			errMsg= "could not instantiate '"+ className +"' ["+ ix.getMessage() +"]";
			throw new InstantiationException(errMsg);
		} catch (IllegalAccessException iax) {
			errMsg= "access denied '"+ className +"' ["+ iax.getMessage() +"]";
			throw new IllegalAccessException(errMsg);
		} catch (ExceptionInInitializerError eiie) {
			errMsg= eiie.getMessage();
			throw new ExceptionInInitializerError(errMsg);
		} catch (LinkageError le) {
			errMsg= le.getMessage();
			throw new LinkageError(errMsg);
		}		
		
		return obj;
	}
	
}
