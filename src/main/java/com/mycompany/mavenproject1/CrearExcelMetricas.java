package com.mycompany.mavenproject1;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class CrearExcelMetricas {

    public static void exportarIMC(double altura, double peso, double imc) {
        String nombreArchivo = "metricas.xlsx";
        Workbook libro;
        Sheet hoja;

        try {
            File archivoExcel = new File(nombreArchivo);

            if (archivoExcel.exists()) {
                try (FileInputStream fis = new FileInputStream(archivoExcel)) {
                    libro = new XSSFWorkbook(fis);
                }
                hoja = libro.getSheet("Métricas");
                if (hoja == null) {
                    hoja = libro.createSheet("Métricas");
                }
            } else {
                libro = new XSSFWorkbook();
                hoja = libro.createSheet("Métricas");

                Row filaCabecera = hoja.createRow(0);
                filaCabecera.createCell(0).setCellValue("Altura (m)");
                filaCabecera.createCell(1).setCellValue("Peso (kg)");
                filaCabecera.createCell(2).setCellValue("IMC");

                hoja.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));
            }

            int ultimaFila = hoja.getLastRowNum() + 1;
            Row fila = hoja.createRow(ultimaFila);

            fila.createCell(0).setCellValue(altura);
            fila.createCell(1).setCellValue(peso);

            hoja.addMergedRegion(new CellRangeAddress(ultimaFila, ultimaFila, 2, 3));
            Cell celdaIMC = fila.createCell(2);
            celdaIMC.setCellValue(imc);

            hoja.autoSizeColumn(0);
            hoja.autoSizeColumn(1);
            hoja.setColumnWidth(2, 20 * 256);
            hoja.setColumnWidth(3, 5 * 256);

            try (FileOutputStream archivoOut = new FileOutputStream(nombreArchivo)) {
                libro.write(archivoOut);
            }

            libro.close();
            System.out.println("Métricas exportadas correctamente a " + nombreArchivo);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}