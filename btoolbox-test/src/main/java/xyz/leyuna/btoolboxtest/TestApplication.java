package xyz.leyuna.btoolboxtest;

import xyz.leyuna.btoolbox.util.EasyExcelUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LeYuna
 * @email 365627310@qq.com
 * @date 2022-05-16
 */
public class TestApplication {

    public static void main(String[] args) {

        TestBean t = new TestBean();
        t.setTestName("ttt");
        List<TestBean> list = new ArrayList<>();
        list.add(t);
        EasyExcelUtil.downloadXlsx(list,"E:/test.xlsx",TestBean::getTestName);
    }
}
