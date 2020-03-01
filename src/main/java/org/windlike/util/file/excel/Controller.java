package org.windlike.util.file.excel;

import org.apache.poi.hpsf.Date;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Logger;

public class Controller {
    private static Logger logger = Logger.getLogger(ExcelReader.class.getName());

    @PostMapping("/uploadExcel")
    public ResponseEntity<?> uploadImage(MultipartFile file) {
        // 检查前台数据合法性
        if (file == null || file.isEmpty()) {
            logger.warning("上传的商品数据excel文件为空，上传时间"+ new Date());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            // 解析Excel
            List<ExcelDataVO> parseResult = ExcelReader.readExcel(file);
            // TODO 业务逻辑
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.warning("上传的商品数据excel文件为空,上传时间" + new Date());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

//    // 前台页面发送请求到后台Controller时的JS代码可参考：
//    var url = "/exportExcel";
//    window.location=url;
//    @GetMapping("/exportExcel")
//    public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
//        Workbook workbook = null;
//        OutputStream out = null;
//        try {
//            // todo 根据业务需求获取需要写入Excel的数据列表 dataList
//
//            // 生成Excel工作簿对象并写入数据
//            workbook = ExcelWriter.exportData(dataList);
//
//            // 写入Excel文件到前端
//            if(null != workbook){
//                String excelName = "示例Excel导出";
//                String fileName = excelName + DateUtil.format(new Date(), DateUtil.SHORT_DATE) + ".xlsx";
//                fileName = new String(fileName.getBytes("UTF-8"),"iso8859-1");
//                response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
//                response.setContentType("application/x-download");
//                response.setCharacterEncoding("UTF-8");
//                response.addHeader("Pargam", "no-cache");
//                response.addHeader("Cache-Control", "no-cache");
//                response.flushBuffer();
//                out = response.getOutputStream();
//                workbook.write(out);
//                out.flush();
//            }
//        } catch (Exception e) {
//            logger.WARNING("写入Excel过程出错！错误原因：" + e.getMessage());
//        } finally {
//            try {
//                if (null != workbook) {
//                    workbook.close();
//                }
//                if (null != out) {
//                    out.close();
//                }
//            } catch (IOException e) {
//                logger.WARNING("关闭workbook或outputStream出错！");
//            }
//        }
//    }
}
