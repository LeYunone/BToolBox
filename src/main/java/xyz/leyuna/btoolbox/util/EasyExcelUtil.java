package xyz.leyuna.btoolbox.util;

import com.alibaba.excel.EasyExcel;

/**
 * @author pengli
 * @create 2022-04-14 16:32
 * 封装Alibaba Easy Excel
 */
public class EasyExcelUtil {

    public void read(){
        EasyExcel.read().sheet().doRead();
    }
}



