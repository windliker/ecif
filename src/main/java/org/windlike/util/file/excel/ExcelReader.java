package org.windlike.util.file.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ExcelReader {
    // 日志打印
    private static Logger logger = Logger.getLogger(ExcelReader.class.getName());
    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";

    public static Workbook getWorkbook(InputStream inputStream, String fileType) throws IOException {
        Workbook workbook = null;
        if (XLS.equalsIgnoreCase(fileType)) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (XLSX.equalsIgnoreCase(fileType)) {
            workbook = new XSSFWorkbook(inputStream);
        }
        return workbook;
    }

    public static List<ExcelDataVO> readExcel(String fileName) {
        Workbook workbook = null;
        FileInputStream inputStream = null;

        try {
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            File excelFile = new File(fileName);
            if (!excelFile.exists()) {
                logger.warning("excel文件不存在！");
                return null;
            }

            // 获取excel
            inputStream = new FileInputStream(excelFile);
            workbook = ExcelReader.getWorkbook(inputStream, fileType);
            // 读取excel内容
            List<ExcelDataVO> resultDataList = parseExcel(workbook);
            return resultDataList;
        } catch (Exception e) {
            logger.warning("解析Excel失败, 文件名" + fileName + ",错误" + e.getMessage());
            return null;
        } finally {
            try {
                if (workbook != null) {
                    workbook.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                logger.warning("关闭数据流出错" + e.getMessage());
                return null;
            }
        }
    }

    public static List<ExcelDataVO> readExcel(MultipartFile file) {

        Workbook workbook = null;

        try {
            // 获取Excel后缀名
            String fileName = file.getOriginalFilename();
            if (fileName == null || fileName.isEmpty() || fileName.lastIndexOf(".") < 0) {
                logger.warning("解析Excel失败，因为获取到的Excel文件名非法！");
                return null;
            }
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());

            // 获取Excel工作簿
            workbook = getWorkbook(file.getInputStream(), fileType);

            // 读取excel中的数据
            List<ExcelDataVO> resultDataList = parseExcel(workbook);

            return resultDataList;
        } catch (Exception e) {
            logger.warning("解析Excel失败，文件名：" + file.getOriginalFilename() + " 错误信息：" + e.getMessage());
            return null;
        } finally {
            try {
                if (null != workbook) {
                    workbook.close();
                }
            } catch (Exception e) {
                logger.warning("关闭数据流出错！错误信息：" + e.getMessage());
                return null;
            }
        }
    }

    // 解析excel数据
    private static List<ExcelDataVO> parseExcel(Workbook workbook) {
        List<ExcelDataVO> resultDataList = new ArrayList<>();
        // 解析工作表sheet
        for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
            Sheet sheet = workbook.getSheetAt(sheetNum);
            if (sheet == null) {
                continue;
            }
            // 解析首行
            int firstRowNum = sheet.getFirstRowNum();
            Row firstRow = sheet.getRow(firstRowNum);
            if (firstRow == null) {
                logger.warning("解析excel 首行没有读到任何数据！");
            }
            // 解析第2行至尾行数据
            int rowStart = firstRowNum + 1;
            int rowEnd = sheet.getPhysicalNumberOfRows();
            for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row == null) {
                    continue;
                }
                ExcelDataVO resultData = convertRowToData(row);
                if (resultData == null) {
                    logger.warning("第" + row.getRowNum() + "行数据为空，已忽略！");
                    continue;
                }
                resultDataList.add(resultData);
            }
        }
        return resultDataList;
    }

    // 将单元格内容转为字符串
    private static String convertCellValueToString(Cell cell) {
        if (cell == null) {
            return null;
        }
        String returnValue = null;
        switch (cell.getCellType()) {
            case NUMERIC:
                Double doubleValue = cell.getNumericCellValue();
                // 格式化科学计算法，取一位整数
                DecimalFormat df = new DecimalFormat("0");
                returnValue = df.format(doubleValue);
                break;
            case STRING:
                returnValue = cell.getStringCellValue();
                break;
            case BOOLEAN:
                Boolean booleanValue = cell.getBooleanCellValue();
                returnValue = booleanValue.toString();
                break;
            case FORMULA:
                returnValue = cell.getCellFormula();
            case BLANK:
            case _NONE:
            case ERROR:
            default:
                break;
        }
        return returnValue;
    }

    // 行数据转为对象
    private static ExcelDataVO convertRowToData(Row row) {
        ExcelDataVO resultData = new ExcelDataVO();
        Cell cell = null;
        int cellNum = 0;
        // 获取姓名
        cell = row.getCell(cellNum++);
        String name = convertCellValueToString(cell);
        resultData.setName(name);
        // 获取年龄
        cell = row.getCell(cellNum++);
        String ageStr = convertCellValueToString(cell);
        if (ageStr == null || "".equalsIgnoreCase(ageStr)) {
            resultData.setAge(null);
        } else {
            resultData.setAge(Integer.parseInt(ageStr));
        }
        // 获取居住地
        cell = row.getCell(cellNum++);
        String location = convertCellValueToString(cell);
        resultData.setLocation(location);
        // 获取职业
        cell = row.getCell(cellNum);
        String job = convertCellValueToString(cell);
        resultData.setJob(job);

        return resultData;
    }
}
