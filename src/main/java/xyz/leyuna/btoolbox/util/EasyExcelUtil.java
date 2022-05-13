package xyz.leyuna.btoolbox.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import org.apache.poi.ss.formula.functions.T;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Function;

/**
 * @author pengli
 * @create 2022-04-14 16:32
 * 封装Alibaba Easy Excel
 */
public class EasyExcelUtil {

    @Resource
    private HttpServletResponse response;

    private Function function;

    /**
     * 定义ResponseType的设置方式
     */
    enum ResponType {
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

    class exportClass {
        @ExcelProperty
        Object object;
    }

    public void downloadXlsx(Collection<T> collection) {
        this.downloadXlsx(collection, ResponType.OCTET_STREAM);
    }

    /**
     * 导出集合指定的字段 ， 不需要collection中用@ExcelProperty 标注，创建虚拟类
     *
     * @param collection
     * @param function
     */
    public void downloadXlsx(Collection<T> collection, Function<T, Object> function) {
        Class clazz = T.class;
        //分析属性，排除除了指定属性外所有属性
        Field[] fields = clazz.getFields();
        Set<String> set = new HashSet<>();
        for(Field field:fields){
            set.add(field.getName());
        }

    }

    /**
     * 下载
     *
     * @param collection 集合
     */
    public void downloadXlsx(Collection<T> collection, EasyExcelUtil.ResponType responType) {
        responType.setResponse(response);
        try {
            EasyExcel.write(response.getOutputStream(), T.class).sheet().doWrite((List) collection);
        } catch (IOException e) {
        }
    }
}



