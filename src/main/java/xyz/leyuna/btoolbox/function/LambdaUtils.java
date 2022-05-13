package xyz.leyuna.btoolbox.function;

import java.beans.Introspector;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.function.Function;

/**
 * @author LeYuna
 * @email 365627310@qq.com
 * @date 2022-05-13
 */
public class LambdaUtils {

    interface FieldNameFuction<T,R> extends Serializable, Function<T,R> {
        static String getFieldName(Object lambda){
            try {
                Method method = lambda.getClass().getDeclaredMethod("writeReplace");
                method.setAccessible(Boolean.TRUE);
                SerializedLambda serializedLambda = (SerializedLambda) method.invoke(lambda);
                String getter = serializedLambda.getImplMethodName();
                String fieldName = Introspector.decapitalize(getter.replace("get", ""));
                return fieldName;
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String getFieldName(FieldNameFuction fieldNameFuction){
        return FieldNameFuction.getFieldName(fieldNameFuction);
    }
}
