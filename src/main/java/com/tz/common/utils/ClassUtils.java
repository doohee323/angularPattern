package com.tz.common.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 :
 * 설    명 : 클래스(Class)와 관련된 Utility Class
 * 작 성 자 : LHF
 * 작성일자 :
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일          이  름    사유
 * ---------------------------------------------------------------
 *
 * ---------------------------------------------------------------
 * </pre>
 *
 * @version 1.0
 */
public class ClassUtils {

    /**
     * log 처리를 위한 변수 선언
     */
    private static final Logger logger = LoggerFactory.getLogger(ClassUtils.class);

    /**
     * <pre>
     * 클래스 로더를 반환한다. 단, 파라미터로 주어진 <code>path</code>가 유효하면 URLClassLoader를 반환한다.
     * </pre>
     *
     * @param path
     * @return ClassLoader
     */
    public static ClassLoader getClassLoader(String path){
        try{
            URL url = new URL("file://" + path.replace('\\', '/'));
            return new URLClassLoader(new URL[] { url }, Thread.currentThread().getContextClassLoader());
        }catch(Exception e){
            return Thread.currentThread().getContextClassLoader();
        }
    }

    /**
     * <pre>
     * 지정한 이름에 해당하는 Class 객체를 반환한다.
     * </pre>
     *
     * @param name 클래스 name
     * @return Class 객체
     * @throws Exception
     */
    public static Class getClass(String name) throws Exception{
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class classGroup = null;
        try{
            classGroup = classLoader.loadClass(name.trim());
        }catch(ClassNotFoundException e){
            throw new Exception(e);
        }
        return classGroup;
    }

    /**
     * <pre>
     * 클래스의 group를 return한다.
     * </pre>
     *
     * @param name 클래스 name
     * @return Object
     * @throws Exception
     */
    public static Object getGroup(String name) throws Exception{
        Object group = null;
        try{
            Class classGroup = getClass(name);
            group = classGroup.newInstance();
        }catch(IllegalAccessException e){
            throw new Exception(e);
        }catch(InstantiationException e){
            throw new Exception(e);
        }
        return group;
    }

    /**
     * <pre>
     * 클래스의 group를 return한다.
     * </pre>
     *
     * @param name
     * @param classParam
     * @param params
     * @return Object
     * @throws Exception
     */
    public static Object getGroup(String name, Class[] classParam, Object[] params) throws Exception{
        Object group = null;
        try{
            Class classGroup = getClass(name);
            Constructor classConstructor = classGroup.getConstructor(classParam);
            group = classConstructor.newInstance(params);
        }catch(IllegalAccessException e){
            throw new Exception(e);
        }catch(InstantiationException e){
            throw new Exception(e);
        }catch(NoSuchMethodException e){
            throw new Exception(e);
        }catch(InvocationTargetException e){
            throw new Exception(e);
        }

        return group;
    }

    /**
     * <pre>
     * 해당 객체의 패키지 경로를 반환한다.
     * </pre>
     *
     * @param o
     * @return package path
     */
    public static String getPackagePath(Object o){
        return o.getClass().getPackage().getName().replace('.', '/');
    }

    /**
     * <pre>
     * 메소드명을 조회하는 메소드
     * </pre>
     *
     * @param aObj
     * @param aMethodNm
     * @return
     */
    public static Method getMethod(Object aObj, String aMethodNm){
        Method method = null;
        Method methods[] = aObj.getClass().getMethods();
        for(int i = 0; i < methods.length; i++){
            if(methods[i].getName().equals(aMethodNm)){
                method = methods[i];
            }
        }
        return method;
    }

    /**
     * <pre>
     * invokeSimple
     * </pre>
     *
     * @param aObj
     * @param aMethodNm
     * @param aArg
     * @exception
     */
    public static void invokeSimple(Object aObj, String aMethodNm, Object aArg) {
        try{
            Method method = getMethod(aObj, aMethodNm);
            if(method == null){
                logger.debug("메소드 없음 : " + aMethodNm);
            }else{
                method.invoke(aObj, aArg);
            }
        }catch(Exception se){
            logger.debug("메소드 없음 : " + aMethodNm);
        }
    }

    /**
     * <pre>
     * dynamic call method
     * </pre>
     *
     * @param aObj
     * @param aMethodNm
     * @param aArg
     * @return Object
     */
    public static Object invokeMethod(Object aObj, String aMethodNm, Object aArg){
        try{
            Method method = getMethod(aObj, aMethodNm, false);
            if(method == null){
                logger.debug("메소드 없음 : " + aMethodNm);
                return new String();
            }else{
                if(aArg != null){
                    return method.invoke(aObj, aArg);
                }
                return null;
            }
        }catch(Exception se){
            logger.debug("invokeMethod !!! -> " + aMethodNm);
        }
        return null;
    }

    /**
     * <pre>
     * dynamic call simple method
     * </pre>
     *
     * @param aObj
     * @param aMethodNm
     * @return Object
     */
    public static Object invokeSimple(Object aObj, String aMethodNm) throws Exception{
        try{
            Method method = getMethod(aObj, aMethodNm, false);
            if(method == null){
                logger.debug("메소드 없음 : " + aMethodNm);
                return new String();
            }else{
                Class[] param = method.getParameterTypes();
                if(param.length > 0)
                    return null;
                Object[] strField = new Object[0];
                return method.invoke(aObj, strField);
            }
        }catch(Exception se){
            logger.debug("메소드 없음 : " + aMethodNm);
            throw new Exception(se);
        }
    }

    /**
     * <pre>
     * dynamic get method
     * </pre>
     *
     * @param aObj
     * @param aMethodNm
     * @param bChk primitive check
     * @return Method
     */
    public static Method getMethod(Object aObj, String aMethodNm, boolean bChk){
        Method method = null;
        Method methods[] = aObj.getClass().getMethods();

        for(int i = 0; i < methods.length; i++){
            if(methods[i].getName().equals(aMethodNm)){
                if(bChk){
                    Class[] param = methods[i].getParameterTypes();
                    if(param[0].getName().equals("java.lang.String") || param[0].getName().equals("int")
                            || param[0].getName().equals("double") || param[0].getName().equals("long")
                            || param[0].getName().equals("float") || param[0].getName().equals("boolean")){
                    }else{
                        method = methods[i];
                        break;
                    }
                }else{
                    method = methods[i];
                    break;
                }
            }
        }
        return method;
    }

    /**
     * <pre>
     * dynamic set value
     * </pre>
     *
     * @param aObj
     * @param aCol
     * @param aVal
     * @return void
     */
    public static void setValue(Object aObj, String aCol, Object aVal){
        try{
            Field[] fields = aObj.getClass().getDeclaredFields();

            for(Field field : fields){
                if(field.getName().equals(aCol)){
                    field.setAccessible(true);
                    if(field.toGenericString().indexOf(" static ") > -1
                            || field.toGenericString().indexOf(" final ") > -1)
                        return;
                    field.set(aCol, aVal);
                    field.setAccessible(false);
                    return;
                }
            }
        }catch(Exception se){
        }
    }
}