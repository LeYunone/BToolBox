package xyz.leyuna.btoolboxtest;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author LeYuna
 * @email 365627310@qq.com
 * @date 2022-05-16
 */
@Data
public class TestBean {

    @ExcelProperty(value = "testId")
    private String id;

    @ExcelProperty(value = "testName")
    private String testName;
}
