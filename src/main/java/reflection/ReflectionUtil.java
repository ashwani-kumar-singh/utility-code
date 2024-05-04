package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ReflectionUtil {

    public static List<String> getFieldNames(Object object) {
        List<String> fieldNames = new ArrayList<>();
        Class<?> clazz = object.getClass();
        //we can inspect private fields declared in the class we are dealing with by calling the getDeclaredFields
        for (Field field : clazz.getDeclaredFields())
            fieldNames.add(field.getName());
        return fieldNames;
    }

    public static String getSimpleName(Object obj) {
        Class<?> clazz = obj.getClass();
        return clazz.getSimpleName();
    }

    public static String getName(Object obj) {
        Class<?> clazz = obj.getClass();
        return clazz.getName();
    }

    public static String getCanonicalName(Object obj) {
        Class<?> clazz = obj.getClass();
        return clazz.getCanonicalName();
    }

    public static String getSimpleName(String className) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(className);
        return clazz.getSimpleName();
    }

    public static String getName(String className) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(className);
        return clazz.getName();
    }

    public static String getCanonicalName(String className) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(className);
        return clazz.getCanonicalName();
    }

    public static boolean isPublic(String className) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(className);
        int mods = clazz.getModifiers();
        return Modifier.isPublic(mods);
    }

    public static boolean isAbstract(String className) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(className);
        int mods = clazz.getModifiers();
        return Modifier.isAbstract(mods);
    }

    public static Package getPackage(Object object) {
        Class<?> clazz = object.getClass();
        return clazz.getPackage();
    }

    public static Class<?> getSuperclass(Object object) {
        Class<?> clazz = object.getClass();
        return clazz.getSuperclass();
    }

    public static Class<?>[] getInterfaces(Object object) {
        Class<?> clazz = object.getClass();
        return clazz.getInterfaces();
    }

    public static Constructor<?> getConstructor(Object object) throws NoSuchMethodException {
        Class<?> clazz = object.getClass();
        return clazz.getConstructor();
    }

    public static Constructor<?> getConstructor(String classPath, Class<?>... clazz) throws NoSuchMethodException, ClassNotFoundException {
        return Class.forName(classPath).getConstructor(clazz);
    }


    public static List<String> getDeclaredMethodNames(Object obj) {
        Class<?> clazz = obj.getClass();
        List<String> methodNames = new ArrayList<>();
        //returns an array of all public methods of the class.
        for (Method method : clazz.getDeclaredMethods())
            methodNames.add(method.getName());
        return methodNames;
    }

    public static List<String> getMethodNames(Object obj) {
        Class<?> clazz = obj.getClass();
        List<String> methodNames = new ArrayList<>();
        // returns an array of all public methods of the class and superclasses.
        for (Method method : clazz.getMethods())
            methodNames.add(method.getName());
        return methodNames;
    }

    public static Method getDeclaredMethod(Object object, String methodName) throws NoSuchMethodException {
        // current object's class method
        return object.getClass().getDeclaredMethod(methodName);
    }

    public static Method getDeclaredMethod(Object object, String methodName, Class<?>... clazz) throws NoSuchMethodException {
        // current object's class method
        return object.getClass().getDeclaredMethod(methodName, clazz);
    }

    public static Method getMethod(Object object, String methodName) throws NoSuchMethodException {
        Class<?> clazz = object.getClass();
        //super class method
        return clazz.getMethod(methodName);
    }

    public static Method getDeclaredMethod(String classPath, String methodName, Class<?>... clazz) throws NoSuchMethodException, ClassNotFoundException {
        return Class.forName(classPath).getDeclaredMethod(methodName, clazz);
    }


}
