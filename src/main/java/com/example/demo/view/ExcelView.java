package com.example.demo.view;

import com.example.demo.dto.ProductDto;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class ExcelView extends AbstractXlsView {

    @Override
    protected void buildExcelDocument(
        Map<String, Object> model,
        Workbook workbook,
        HttpServletRequest request,
        HttpServletResponse response) throws Exception {

        response.setHeader("Content-Disposition", "attachment; filename=\"result.xls\"");

        Sheet sheet = workbook.createSheet("Products ");
        sheet.setDefaultColumnWidth(30);

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("id");
        header.createCell(1).setCellValue("name");
        header.createCell(2).setCellValue("quantity");
        header.createCell(3).setCellValue("brand");

        int rowCount = 1;
        List<ProductDto> productDtos = (List<ProductDto>) model.get("products");

        for (ProductDto product : productDtos){
            Row productRow =  sheet.createRow(rowCount++);
            productRow.createCell(0).setCellValue(product.getId());
            productRow.createCell(1).setCellValue(product.getName());
            productRow.createCell(2).setCellValue(product.getQuantity());
            productRow.createCell(3).setCellValue(product.getBrand());

        }

    }

}
