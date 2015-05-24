package codes.rusty.chatapi.util;

import java.lang.reflect.Field;

public class FieldWrapper {

    private final Field field;
    
    public FieldWrapper(Field field) {
        this.field = field;
        this.field.setAccessible(true);
    }
    
    public Object get(Object instance) {
        try {
            return field.get(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public void set(Object instance, Object value) {
        try {
            field.set(instance, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
