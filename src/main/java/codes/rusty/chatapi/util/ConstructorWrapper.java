package codes.rusty.chatapi.util;

public class ConstructorWrapper {

    private final Class clazz;
    private Class[] parameters = null;
    
    public ConstructorWrapper(Class clazz) {
        this.clazz = clazz;
    }
    
    public ConstructorWrapper withArgs(Class... parameters) {
        if (this.parameters != null) {
            throw new IllegalStateException("ConstructorWrapper has been locked!");
        }
        
        this.parameters = parameters;
        return this;
    }
    
    public Object construct(Object... arguments) {
        if (parameters == null && arguments != null) {
            throw new IllegalArgumentException("Arguments passed when not expected!");
        }
        
        if((parameters != null && arguments == null) || (parameters.length != arguments.length)) {
            throw new IllegalArgumentException("Invalid amount of arguments passed!");
        }
        
        try {
            if (parameters == null) {
                return clazz.newInstance();
            } else {
                return clazz.getConstructor(parameters).newInstance(arguments);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
}
