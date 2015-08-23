package kxw.com.util;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import java.util.*;
import java.io.*;
/**
 * Java Reflection Cookbook
 *
 * @author Michael Lee
 * @since 2006-8-23
 * @version 0.1a
 */

public class Reflection {
    /**
     * 得到某个对象的公共属性
     *
     * @param owner, fieldName
     * @return 该属性对象
     * @throws Exception
     *
     */
    public Object getProperty(Object owner, String fieldName) throws Exception {
        Class ownerClass = owner.getClass();

        Field field = ownerClass.getField(fieldName);

        Object property = field.get(owner);

        return property;
    }

    /**
     * 得到某类的静态公共属性
     *
     * @param className   类名
     * @param fieldName   属性名
     * @return 该属性对象
     * @throws Exception
     */
    public Object getStaticProperty(String className, String fieldName)
            throws Exception {
        Class ownerClass = Class.forName(className);

        Field field = ownerClass.getField(fieldName);

        Object property = field.get(ownerClass);

        return property;
    }


    /**
     * 执行某对象方法
     *
     * @param owner
     *            对象
     * @param methodName
     *            方法名
     * @param args
     *            参数
     * @return 方法返回值
     * @throws Exception
     */
    public Object invokeMethod(Object owner, String methodName, Object[] args)
            throws Exception {

        Class ownerClass = owner.getClass();

        Class[] argsClass = new Class[args.length];

        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }

        Method method = ownerClass.getMethod(methodName, argsClass);

        return method.invoke(owner, args);
    }


      /**
     * 执行某类的静态方法
     *
     * @param className
     *            类名
     * @param methodName
     *            方法名
     * @param args
     *            参数数组
     * @return 执行方法返回的结果
     * @throws Exception
     */
    public Object invokeStaticMethod(String className, String methodName,
            Object[] args) throws Exception {
        Class ownerClass = Class.forName(className);

        Class[] argsClass = new Class[args.length];

        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }

        Method method = ownerClass.getMethod(methodName, argsClass);

        return method.invoke(null, args);
    }



    /**
     * 新建实例
     *
     * @param className
     *            类名
     * @param args
     *            构造函数的参数
     * @return 新建的实例
     * @throws Exception
     */
    public Object newInstance(String className, Object[] args) throws Exception {
        Class newoneClass = Class.forName(className);

        Class[] argsClass = new Class[args.length];

        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }

        Constructor cons = newoneClass.getConstructor(argsClass);

        return cons.newInstance(args);

    }



    /**
     * 是不是某个类的实例
     * @param obj 实例
     * @param cls 类
     * @return 如果 obj 是此类的实例，则返回 true
     */
    public boolean isInstance(Object obj, Class cls) {
        return cls.isInstance(obj);
    }

    /**
     * 得到数组中的某个元素
     * @param array 数组
     * @param index 索引
     * @return 返回指定数组对象中索引组件的值
     */
    public Object getByArray(Object array, int index) {
        return Array.get(array,index);
    }

    public void print(Object owner) throws Exception{

        if(owner==null) return ;

        Class ownerClass = owner.getClass();
        Field[] filelds = ownerClass.getDeclaredFields();
        Method[] methods = ownerClass.getMethods();

        for (int i = 0; i < filelds.length; i++) {

          String name = filelds[i].getName();

          String getMethodName = "get" + name.substring(0, 1).toUpperCase() +
              name.substring(1, name.length());
          String value = (String)this.invokeMethod(owner, getMethodName,
                                                   new Object[0]);

          System.out.println(name+":"+value);
        }

    }

    public void printInOneLine(Object owner) throws Exception{

        Class ownerClass = owner.getClass();
        Field[] filelds = ownerClass.getDeclaredFields();
        Method[] methods = ownerClass.getMethods();

        for (int i = 0; i < filelds.length; i++) {

          String name = filelds[i].getName();

          String getMethodName = "get" + name.substring(0, 1).toUpperCase() +
              name.substring(1, name.length());
          String value = (String)this.invokeMethod(owner, getMethodName,
                                                   new Object[0]);

          System.out.print(value+" ");
        }
        System.out.println();
    }

    public  void printList(List list) throws Exception{

        String str="";

        for (int i = 0; i < list.size(); i++) {

          Object owner = (Object)list.get(i);
          Class ownerClass = owner.getClass();
          Field[] filelds = ownerClass.getDeclaredFields();
          Method[] methods = ownerClass.getMethods();

          for (int j = 0; j < filelds.length; j++) {

            String name = filelds[j].getName();

            String getMethodName = "get" + name.substring(0, 1).toUpperCase() +
                name.substring(1, name.length());
            String value = (String)this.invokeMethod(owner, getMethodName,
                                                     new Object[0]);
            //if(i==0) str+=name+" ";
            if(value==null) value=" ";
            str+= value +"";
            //str += empty(value,32)+" ";
            //System.out.print(value+" ");
          }
          str+="\n";
        }

        FileWriter writer = new FileWriter("D://1.txt");
        writer.write(str);
        writer.close();
    }

    public static void write(String str) throws Exception{

      FileWriter writer = new FileWriter("D://test.txt");
      writer.write(str);
      writer.close();
    }


    public String zero(String s,int bits){

      int len = s.length();
      if(len>=bits) return s;

      StringBuffer sb = new StringBuffer();

      for(int i=0;i<bits-len;i++){
        sb.append("0");
      }
      sb.append(s);

      return sb.toString();
    }

    public String empty(String s,int bits){

      int len = s.length();
      if(len>=bits) return s;

      StringBuffer sb = new StringBuffer();
      sb.append(s);

      for(int i=0;i<bits-len;i++){
        sb.append(" ");
      }


      return sb.toString();
    }

    public static void main(String[] args) throws Exception{

      Reflection r = new Reflection();
      //System.out.print(r.getProperty(new com.elmt.common.Constants(),"YES_NO")) ;
      //System.out.print(r.getStaticProperty("com.elmt.common.Constants","YES_NO")) ;
      //com.elmt.dataelement.domain.dto.DataElementBean db = new com.elmt.dataelement.domain.dto.DataElementBean();
      //db.setElmtype("a");
      //System.out.print(r.invokeMethod(db,"getElmtype",new Object[0]));
      //r.print(db);


    }

}

