package io.github.talelin.latticy.util;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
/**
 * Created by zhanyuxiang on 2017/7/24.
 */
public class ExcelUtil {
    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);


    public static void exportExcel(JSONObject table, ServletOutputStream os) {

        //定义数据
        String title = table.getString("title");
        JSONArray head = table.getJSONArray("head");
        JSONArray body = table.getJSONArray("body");

        //创建sheet页
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(title);

        // 设置表头
        HSSFRow headRow = sheet.createRow(0);
        for (int i = 0; i < head.size(); i++) {
            JSONObject props = head.getJSONObject(i);
            HSSFCell cell = headRow.createCell(i);
            cell.setCellValue(props.getString("name"));
            sheet.setColumnWidth(i, 3000);
        }

        // 设置内容
        for (int i = 0; i < body.size(); i++) {
            HSSFRow bodyRow = sheet.createRow(i + 1);
            JSONObject line = body.getJSONObject(i);
            for (int j = 0; j < head.size(); j++) {
                HSSFCell cell = bodyRow.createCell(j);
                JSONObject props = head.getJSONObject(j);
                String key = props.getString("key");
                cell.setCellValue(line.getString(key));
            }
        }

        try {
            wb.write(os);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
