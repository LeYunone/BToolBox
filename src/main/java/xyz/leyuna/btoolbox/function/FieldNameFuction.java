package xyz.leyuna.btoolbox.function;

import org.apache.poi.ss.formula.functions.T;

import java.io.Serializable;
import java.util.function.Function;

/**
 * @author LeYuna
 * @email 365627310@qq.com
 * @date 2022-05-13
 */
@FunctionalInterface
public interface  FieldNameFuction<T,R> extends Serializable, Function<T,R>   {

}
