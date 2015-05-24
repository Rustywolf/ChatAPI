package codes.rusty.chatapi.util;

import java.lang.reflect.Method;

public class MethodWrapper {

    private final Method method;
    
    public MethodWrapper(Method method) {
        this.method = method;
        this.method.setAccessible(true);
    }
    
    public Object invoke(Object instance, Object... arguments) {
        
        if (method.getParameterCount() != 0 && arguments == null) {
            throw new IllegalArgumentException("Arguments expected, receieved null!");
        }
        
        if(arguments != null && method.getParameterCount() != arguments.length) {
            throw new IllegalArgumentException("Invalid amount of arguments passed!");
        }
        
        try {
            if (arguments == null) {
                return method.invoke(instance);
            } else {
                return method.invoke(instance, arguments);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
}
