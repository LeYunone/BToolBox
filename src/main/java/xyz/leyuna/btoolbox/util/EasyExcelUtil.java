package xyz.leyuna.btoolbox.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import org.apache.poi.ss.formula.functions.T;
import xyz.leyuna.btoolbox.function.FieldNameFuction;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;

/**
 * @author pengli
 * @create 2022-04-14 16:32
 * 封装Alibaba Easy Excel
 */
public class EasyExcelUtil {

    @Resource
    private static HttpServletResponse response;

    /**
     * 定义ResponseType的设置方式
     */
    static enum ResponType {
        /**
         * application/octet-stream 下载方式
         */
        OCTET_STREAM(1);

        ResponType(Integer type) {
            this.type = type;
        }

        private Integer type;

        protected void setResponse(HttpServletResponse response) {
            switch (type) {
                case 1:
                    response.setHeader("Content-Disposition", "attachment;filename=export.xlsx");
                    response.setContentType("application/octet-stream");
                    response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
                    break;
                default:
                    break;
            }
        }

    }

    public static <T> void downloadXlsx(Collection<T> collection) {
        downloadXlsx(collection, ResponType.OCTET_STREAM);
    }

    /**
     * 导出集合指定的字段 ， 不需要collection中用@ExcelProperty 标注，创建虚拟类
     *
     * @param collection
     * @param function
     */
    public static <T, R> void downloadXlsx(Collection<T> collection,FieldNameFuction<T, R> function) throws IOException {
        T next = collection.iterator().next();
        Class clazz = next.getClass();
        //分析属性，排除除了指定属性外所有属性
        Field[] fields = clazz.getDeclaredFields();
        Set<String> set = new HashSet<>();
        for (Field field : fields) {
            set.add(field.getName());
        }
        //获得属性名
        String fieldName = FieldUtil.getFieldName(function);
        set.remove(fieldName);
        EasyExcel.write(response.getOutputStream(),clazz).excludeColumnFiledNames(set).sheet().doWrite(new ArrayList(collection));
    }

    /**
     * 下载
     *
     * @param collection 集合
     */
    public static void downloadXlsx(Collection<?> collection, EasyExcelUtil.ResponType responType) {
        responType.setResponse(response);
        try {
            EasyExcel.write(response.getOutputStream(), T.class).sheet().doWrite((List) collection);
        } catch (IOException e) {
        }
    }
}



