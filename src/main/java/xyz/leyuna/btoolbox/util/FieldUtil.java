package xyz.leyuna.btoolbox.util;

import xyz.leyuna.btoolbox.function.FieldNameFuction;

import java.beans.Introspector;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;

/**
 * @author LeYuna
 * @email 365627310@qq.com
 * @date 2022-05-16
 */
public class FieldUtil {

    public static String getFieldName(FieldNameFuction fieldNameFuction) {
        try {
            Method method = fieldNameFuction.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(Boolean.TRUE);
            SerializedLambda serializedLambda = (SerializedLambda) method.invoke(fieldNameFuction);
            String getter = serializedLambda.getImplMethodName();
            String fieldName = Introspector.decapitalize(getter.replace("get", ""));
            return fieldName;
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}
