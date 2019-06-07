package com.vladislavk.testcrud.service;

import com.vladislavk.testcrud.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Vladislav Klochkov
 * @project testcrud
 * @date 2019-06-07
 */


@Component
@NoArgsConstructor
@Data
public class WriteToFileExcel {
    private static Logger logger = LoggerFactory.getLogger(WriteToFileExcel.class);

    private static SimpleDateFormat dateFormat = new SimpleDateFormat();

    public void downloadFileExcel(HttpServletResponse response, List<Product> products) {
        try {
            String fileName = "Search_" + dateFormat.format(new Date()) + ".xlsx";
            response.setHeader("Content-Disposition", "inline; filename=" + fileName);
            response.setContentType("application/vnd.ms-excel");
            writeToFileExcel(response, products);
        } catch (Exception e) {
            logger.error("Ошибка записи в файл - {}", e.getMessage());
        }
    }

    private synchronized void writeToFileExcel(HttpServletResponse response, List<Product> products) {
        try {
            ServletOutputStream outputStream = response.getOutputStream();

            // Создаем книгу
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook();

            // Создаем лист
            XSSFSheet sheet = xssfWorkbook.createSheet("Product");

            // Строка шапки
            XSSFRow rowHead = sheet.createRow(0);
            Cell head0 = rowHead.createCell(0);
            head0.setCellValue("№");
            Cell head1 = rowHead.createCell(1);
            head1.setCellValue("Name");
            Cell head2 = rowHead.createCell(2);
            head2.setCellValue("Brand");
            Cell head3 = rowHead.createCell(3);
            head3.setCellValue("Price");
            Cell head4 = rowHead.createCell(4);
            head4.setCellValue("Quantity");

            // Заполняем строки
            int rowNum = 2;
            for (Product product : products) {
                XSSFRow row = sheet.createRow(rowNum);
                Cell cell0 = row.createCell(0);
                cell0.setCellValue(product.getId());
                Cell cell1 = row.createCell(1);
                cell1.setCellValue(product.getName());
                Cell cell2 = row.createCell(2);
                cell2.setCellValue(product.getBrand());
                Cell cell3 = row.createCell(3);
                cell3.setCellValue(product.getPrice());
                Cell cell4 = row.createCell(4);
                cell4.setCellValue(product.getQuantity());
                rowNum++;
            }

            // Записываем
            xssfWorkbook.write(outputStream);
            outputStream.flush();
            outputStream.close();

        } catch (IOException e) {
            logger.error("Ошибка записи в файл - {}", e.getMessage());
        }
    }
}
