package com.library.common;

import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The type Reflect helper.
 */
public class ReflectHelper {
    private final static Logger logger = Logger.getLogger(ReflectHelper.class);


    /**
     * Sets method.
     *
     * @param obj       the obj
     * @param fieldName the field name
     * @param value     the value
     */
    @SuppressWarnings("unchecked")
    public static void setMethod(Object obj, String fieldName, Object value) {
        Class cls = obj.getClass();
        try {
            Class fieldType = cls.getDeclaredField(fieldName).getType();
            Method method = cls.getDeclaredMethod("set" + StringHelper.capitalFirstLetter(fieldName), fieldType);
            if (String.class.equals(fieldType)) {
                method.invoke(obj, value.toString());
            } else if (byte.class.equals(fieldType)) {
                method.invoke(obj, Byte.parseByte(value.toString()));
            } else if (Byte.class.equals(fieldType)) {
                method.invoke(obj, Byte.valueOf(value.toString()));
            } else if (boolean.class.equals(fieldType)) {
                method.invoke(obj, Boolean.parseBoolean(value.toString()));
            } else if (Boolean.class.equals(fieldType)) {
                method.invoke(obj, Boolean.valueOf(value.toString()));
            } else if (short.class.equals(fieldType)) {
                method.invoke(obj, Short.parseShort(value.toString()));
            } else if (Short.class.equals(fieldType)) {
                method.invoke(obj, Short.valueOf(value.toString()));
            } else if (int.class.equals(fieldType)) {
                method.invoke(obj, Integer.parseInt(value.toString()));
            } else if (Integer.class.equals(fieldType)) {
                method.invoke(obj, Integer.valueOf(value.toString()));
            } else if (long.class.equals(fieldType)) {
                method.invoke(obj, Long.parseLong(value.toString()));
            } else if (Long.class.equals(fieldType)) {
                method.invoke(obj, Long.valueOf(value.toString()));
            } else if (float.class.equals(fieldType)) {
                method.invoke(obj, Float.parseFloat(value.toString()));
            } else if (Float.class.equals(fieldType)) {
                method.invoke(obj, Float.valueOf(value.toString()));
            } else if (double.class.equals(fieldType)) {
                method.invoke(obj, Double.parseDouble(value.toString()));
            } else if (Double.class.equals(fieldType)) {
                method.invoke(obj, Double.valueOf(value.toString()));
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | NoSuchFieldException e) {
            logger.error(e.getMessage(),e);
        }
    }

    /**
     * Sets method.
     *
     * @param obj       the obj
     * @param fieldName the field name
     * @param value     the value
     * @param fieldType the field class
     */
    @SuppressWarnings("unchecked")
    public static void setMethod(Object obj, String fieldName, Object value, Class fieldType) {
        Class cls = obj.getClass();
        try {
            Method method = cls.getDeclaredMethod("set" + StringHelper.capitalFirstLetter(fieldName), fieldType);
            if (String.class.equals(fieldType)) {
                method.invoke(obj, value.toString());
            } else if (byte.class.equals(fieldType)) {
                method.invoke(obj, Byte.parseByte(value.toString()));
            } else if (Byte.class.equals(fieldType)) {
                method.invoke(obj, Byte.valueOf(value.toString()));
            } else if (boolean.class.equals(fieldType)) {
                method.invoke(obj, Boolean.parseBoolean(value.toString()));
            } else if (Boolean.class.equals(fieldType)) {
                method.invoke(obj, Boolean.valueOf(value.toString()));
            } else if (short.class.equals(fieldType)) {
                method.invoke(obj, Short.parseShort(value.toString()));
            } else if (Short.class.equals(fieldType)) {
                method.invoke(obj, Short.valueOf(value.toString()));
            } else if (int.class.equals(fieldType)) {
                method.invoke(obj, Integer.parseInt(value.toString()));
            } else if (Integer.class.equals(fieldType)) {
                method.invoke(obj, Integer.valueOf(value.toString()));
            } else if (long.class.equals(fieldType)) {
                method.invoke(obj, Long.parseLong(value.toString()));
            } else if (Long.class.equals(fieldType)) {
                method.invoke(obj, Long.valueOf(value.toString()));
            } else if (float.class.equals(fieldType)) {
                method.invoke(obj, Float.parseFloat(value.toString()));
            } else if (Float.class.equals(fieldType)) {
                method.invoke(obj, Float.valueOf(value.toString()));
            } else if (double.class.equals(fieldType)) {
                method.invoke(obj, Double.parseDouble(value.toString()));
            } else if (Double.class.equals(fieldType)) {
                method.invoke(obj, Double.valueOf(value.toString()));
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            logger.error(e.getMessage(),e);
        }
    }

    /**
     * Sets method.
     *
     * @param cls       the cls
     * @param fieldName the field name
     * @param value     the value
     * @param fieldType the field class
     */
    @SuppressWarnings("unchecked")
    public static void setMethod(Class cls, String fieldName, Object value, Class fieldType) {
        try {
            Method method = cls.getDeclaredMethod("set" + StringHelper.capitalFirstLetter(fieldName), fieldType);
            if (String.class.equals(fieldType)) {
                method.invoke(cls, value.toString());
            } else if (byte.class.equals(fieldType)) {
                method.invoke(cls, Byte.parseByte(value.toString()));
            } else if (Byte.class.equals(fieldType)) {
                method.invoke(cls, Byte.valueOf(value.toString()));
            } else if (boolean.class.equals(fieldType)) {
                method.invoke(cls, Boolean.parseBoolean(value.toString()));
            } else if (Boolean.class.equals(fieldType)) {
                method.invoke(cls, Boolean.valueOf(value.toString()));
            } else if (short.class.equals(fieldType)) {
                method.invoke(cls, Short.parseShort(value.toString()));
            } else if (Short.class.equals(fieldType)) {
                method.invoke(cls, Short.valueOf(value.toString()));
            } else if (int.class.equals(fieldType)) {
                method.invoke(cls, Integer.parseInt(value.toString()));
            } else if (Integer.class.equals(fieldType)) {
                method.invoke(cls, Integer.valueOf(value.toString()));
            } else if (long.class.equals(fieldType)) {
                method.invoke(cls, Long.parseLong(value.toString()));
            } else if (Long.class.equals(fieldType)) {
                method.invoke(cls, Long.valueOf(value.toString()));
            } else if (float.class.equals(fieldType)) {
                method.invoke(cls, Float.parseFloat(value.toString()));
            } else if (Float.class.equals(fieldType)) {
                method.invoke(cls, Float.valueOf(value.toString()));
            } else if (double.class.equals(fieldType)) {
                method.invoke(cls, Double.parseDouble(value.toString()));
            } else if (Double.class.equals(fieldType)) {
                method.invoke(cls, Double.valueOf(value.toString()));
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            logger.error(e.getMessage(),e);
        }
    }

    public static void setValue(Object obj, Field field, String value) {
        Object fieldType = field.getType();
        try {
            if (String.class.equals(fieldType)) {
                field.set(obj, value);
            } else if (byte.class.equals(fieldType)) {
                field.set(obj, Byte.parseByte(value));
            } else if (Byte.class.equals(fieldType)) {
                field.set(obj, Byte.valueOf(value));
            } else if (boolean.class.equals(fieldType)) {
                field.set(obj, Boolean.parseBoolean(value));
            } else if (Boolean.class.equals(fieldType)) {
                field.set(obj, Boolean.valueOf(value));
            } else if (short.class.equals(fieldType)) {
                field.set(obj, Short.parseShort(value));
            } else if (Short.class.equals(fieldType)) {
                field.set(obj, Short.valueOf(value));
            } else if (int.class.equals(fieldType)) {
                field.set(obj, Integer.parseInt(value));
            } else if (Integer.class.equals(fieldType)) {
                field.set(obj, Integer.valueOf(value));
            } else if (long.class.equals(fieldType)) {
                field.set(obj, Long.parseLong(value));
            } else if (Long.class.equals(fieldType)) {
                field.set(obj, Long.valueOf(value));
            } else if (float.class.equals(fieldType)) {
                field.set(obj, Float.parseFloat(value));
            } else if (Float.class.equals(fieldType)) {
                field.set(obj, Float.valueOf(value));
            } else if (double.class.equals(fieldType)) {
                field.set(obj, Double.parseDouble(value));
            } else if (Double.class.equals(fieldType)) {
                field.set(obj, Double.valueOf(value));
            }
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * Add method.
     *
     * @param parent    the parent
     * @param sub       the sub
     * @param fieldName the field name
     * @param subClass  the sub class
     */
    @SuppressWarnings("unchecked")
    public static void addMethod(Object parent, Object sub, String fieldName, Class subClass) {
        Class cls = parent.getClass();
        try {
            Method method = cls.getDeclaredMethod("add" + fieldName, subClass);
            method.invoke(parent, sub);
        } catch (NoSuchMethodException e) {
            Method method = null;
            try {
                method = cls.getDeclaredMethod("set" + fieldName, subClass);
                method.invoke(parent, sub);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
                ex.printStackTrace();
            }

        } catch (InvocationTargetException | IllegalAccessException e) {
            logger.error(e.getMessage(),e);
        }
    }

    /**
     * Gets method.
     *
     * @param obj   the obj
     * @param field the field
     * @return the method
     */
    @SuppressWarnings("unchecked")
    public static Object getMethod(Object obj, String field) {
        Class cls = obj.getClass();
        try {
            Method getMethod = cls.getDeclaredMethod("get" + StringHelper.capitalFirstLetter(field));
            return getMethod.invoke(obj);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static Method getMethod(Class<?> clazz, String name) {
        while (clazz != Object.class) {
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().equals(name)) {
                    return method;
                }
            }
            clazz = clazz.getSuperclass();
        }
        return null;
    }

    private static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        while (clazz != Object.class) {
            Collections.addAll(fields, clazz.getDeclaredFields());
            clazz = clazz.getSuperclass();
        }
        return fields;
    }

    /**
     * Gets object info.
     *
     * @param obj the obj
     * @return the object info
     */
    public static String getObjectInfo(Object obj) {
        StringBuilder stringBuilder = new StringBuilder();
        Class<?> clazz = obj.getClass();
        List<Field> fields = getAllFields(clazz);
        Method method = getMethod(clazz, "toString");
        if (method != null) {
            try {
                return method.invoke(obj).toString();
            } catch (IllegalAccessException | InvocationTargetException e) {
                logger.error(e.getMessage(),e);
            }
        } else {
            for (Field field : fields) {
                try {
                    boolean accessFlag = field.isAccessible();
                    field.setAccessible(true);
                    String varName = field.getName();
                    Object varValue = field.get(obj);
                    stringBuilder.append(varName);
                    stringBuilder.append("=");
                    stringBuilder.append(varValue);
                    stringBuilder.append(", ");
                    field.setAccessible(accessFlag);
                } catch (Exception e) {
                    logger.error(e.getMessage(),e);
                }
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }

        return stringBuilder.toString();
    }

    /**
     * Gets collections info.
     *
     * @param list the list
     * @return the collections info
     */
    public static String getCollectionsInfo(List<?> list) {
        if (list.size() == 0) {
            return "null";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Object obj : list) {
            if (obj.getClass().isPrimitive()) {
                stringBuilder.append(obj.toString());
                stringBuilder.append(", ");
            } else {
                stringBuilder.append(getObjectInfo(obj));
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 通过反射创建实例
     *
     * @param <T>       the type parameter
     * @param className the class name
     * @return the t
     */
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(String className) {
        T instance;
        try {
            Class<?> commandClass = ClassHelper.loadClass(className);
            instance = (T) commandClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * 通过反射创建实例
     *
     * @param <T>          the type parameter
     * @param commandClass the class
     * @return the t
     */
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(Class<?> commandClass) {
        T instance;
        try {
            instance = (T) commandClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return instance;
    }
}
