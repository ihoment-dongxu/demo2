package com.example.demo.controller;

import com.google.common.collect.Lists;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * @author dongxu
 * @create 2024-11-13 10:03
 */
public class ExcelHelperController {

    // 忽略的列
    public static final List<Integer> IGNORE_ROW_INDEX_ARR = Lists.newArrayList(3);

    public static void main(String[] args) {
        String excelFilePath = "/Users/dongxu/Desktop/海外仓&17track物流方式映射V2.xlsx"; // 替换为实际的文件路径

        try (FileInputStream fis = new FileInputStream(new File(excelFilePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // 获取第一个sheet
            Iterator<Row> rowIterator = sheet.iterator(); // 获取行迭代器

            // 定义表中的列名
            String[] columns = {"oversea_warehouse", "logistics_product", "logistics_product_name", "seventeen_track_carrier", "seventeen_track_carrier_id"};

            // 遍历每一行
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                StringBuilder sql = new StringBuilder("INSERT INTO seventeen_track_warehouse_mapping (");

                StringBuilder valuesPart = new StringBuilder(") VALUES (");

                // 临时存储列名和对应的值
                StringBuilder columnsPart = new StringBuilder();

                // 遍历每一列
                int columnIndex = 0;
                boolean first = true; // 用于判断是否是第一列，以便处理逗号
                for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
                    // 不用的列索引写在这里
                    if (IGNORE_ROW_INDEX_ARR.contains(i)) {
                        continue;
                    }

                    // 只处理不被忽略的列
                    if (columnIndex < columns.length) {
                        if (!first) {
                            columnsPart.append(", ");
                            valuesPart.append(", ");
                        }
                        columnsPart.append(columns[columnIndex]);
                        valuesPart.append(getCellValue(row.getCell(i)));
                        first = false;
                    }
                    columnIndex++;
                }

                // 完成列名和对应值的拼接
                sql.append(columnsPart);
                sql.append(valuesPart);
                sql.append(");");

                // 输出生成的 SQL 语句
                System.out.println(sql.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 获取单元格的值
    private static String getCellValue(Cell cell) {
        if (cell == null) {
            return ""; // 如果是空单元格，插入 NULL
        }

        switch (cell.getCellType()) {
            case STRING:
                return "'" + cell.getStringCellValue() + "'";
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return "'" + cell.getDateCellValue() + "'";
                } else {
                    return String.valueOf((int) cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }
}
