package codes.rusty.chatapi.util;

import org.bukkit.Bukkit;

public class ReflectionUtil {

    public static final String version;
    public static final String nms;
    public static final String obc;
    static {
        version = Bukkit.getServer().getClass().getCanonicalName().split("\\.")[3];
        nms = "net.minecraft.server." + version;
        obc = "org.bukkit.craftbukkit." + version;
    }
    
    public static ConstructorWrapper getNMSConstructor(String className) {
        return new ConstructorWrapper(getNMSClass(className));
    }
    
    public static ConstructorWrapper getOBCConstructor(String className) {
        return new ConstructorWrapper(getOBCClass(className));
    }
    
    public static Class getNMSClass(String className) {
        return getClass(nms, className);
    }
    
    public static Class getOBCClass(String className) {
        return getClass(obc, className);
    }
    
    public static MethodWrapper getNMSMethod(String className, String methodName, Class... parameters) {
        return getMethod(getNMSClass(className), methodName, parameters);
    }
    
    public static MethodWrapper getOBCMethod(String className, String methodName, Class... parameters) {
        return getMethod(getOBCClass(className), methodName, parameters);
    }
    
    public static MethodWrapper getMethod(Class clazz, String methodName, Class... parameters) {
        try {
            return new MethodWrapper(clazz.getMethod(methodName, parameters));
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("No such method found!");
        }
    }
    
    public static FieldWrapper getNMSField(String className, String fieldName) {
        return getField(getNMSClass(className), fieldName);
    }
    
    public static FieldWrapper getOBCField(String className, String fieldName) {
        return getField(getOBCClass(className), fieldName);
    }
    
    public static FieldWrapper getField(Class clazz, String fieldName) {
        try {
            return new FieldWrapper(clazz.getField(fieldName));
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException("No such field found!");
        }
    }
    
    private static Class getClass(String pkg, String className) {
        try {
            return Class.forName(pkg + "." + className);
        } catch (Exception e) {
            throw new IllegalArgumentException("Unknown class name passed!");
        }
    }
    
}
