package org.windlike.util.file.excel;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description 示例程序入口类
 * @author windlike 2020/3/1
 * @modifylog
 */
public class MainTest {
    private static Logger logger = Logger.getLogger(MainTest.class.getName());
    public static void main(String[] args) {
        /* 读excel文件 */
        // excel文件完整路径
        String excelFileName = "D:\\IdeaProjects\\Algorithms\\data\\sparsearray\\test.xlsx";
        List<ExcelDataVO> readResult = ExcelReader.readExcel(excelFileName);
        logger.info(readResult);
        // TODO 业务逻辑

        /* 写excel文件 */
        // 创建需要写入的数据列表
        List<ExcelDataVO> dataVOList = new ArrayList<>(2);
        ExcelDataVO dataVO = new ExcelDataVO();
        dataVO.setName("杨小明");
        dataVO.setAge(19);
        dataVO.setLocation("广州");
        dataVO.setJob("硕士生");
        ExcelDataVO dataVO2 = new ExcelDataVO();
        dataVO2.setName("柳小花");
        dataVO2.setAge(22);
        dataVO2.setLocation("深圳");
        dataVO2.setJob("博士生");
        ExcelDataVO dataVO3 = new ExcelDataVO();
        dataVO3.setName("Java");
        dataVO3.setAge(21);
        dataVO3.setJob("算法工程师");
        dataVOList.add(dataVO);
        dataVOList.add(dataVO2);
        dataVOList.add(dataVO3);

        // 写入数据到工作簿对象内
        Workbook workbook = ExcelWriter.exportData(dataVOList);

        // 以文件的形式输出工作簿对象
        FileOutputStream fileOut = null;
        try {
            String exportFilePath = "D:\\IdeaProjects\\Algorithms\\data\\sparsearray\\test.xlsx";
            File exportFile = new File(exportFilePath);
            if (!exportFile.exists()) {
                exportFile.createNewFile();
            }

            fileOut = new FileOutputStream(exportFilePath);
            workbook.write(fileOut);
            fileOut.flush();
        } catch (Exception e) {
            logger.warn("输出Excel时发生错误，错误原因：" + e.getMessage());
//            logger.warning("输出Excel时发生错误，错误原因：" + e.getMessage());
        } finally {
            try {
                if (null != fileOut) {
                    fileOut.close();
                }
                if (null != workbook) {
                    workbook.close();
                }
            } catch (IOException e) {
                logger.warn("关闭数据流出错！错误信息：" + e.getMessage());
//                logger.warning("关闭数据流出错！错误信息：" + e.getMessage());
            }
        }
    }
}
