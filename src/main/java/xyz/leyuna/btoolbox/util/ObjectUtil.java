package xyz.leyuna.btoolbox.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

/**
 * @author LeYuna
 * @email 365627310@qq.com
 * @date 2022-05-16
 */
public class ObjectUtil<T> {

    public static <T>Class getCollectionClazz(Collection<T> collection){
        return getCollectionClazz(collection,0);
    }

    public static <T>Class getCollectionClazz(Collection<T> collection,int index){
        Class<? extends Collection> aClass = collection.getClass();
        Type type = collection.getClass().getSuperclass().getGenericSuperclass();
        if(type instanceof  ParameterizedType){
            //如果有泛型才返回 否则空
            ParameterizedType genericSuperclass = (ParameterizedType)type;
            //返回顶级接口的指定泛型
            Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
            return actualTypeArguments[index].getClass();
        }
        return null;
    }
}
