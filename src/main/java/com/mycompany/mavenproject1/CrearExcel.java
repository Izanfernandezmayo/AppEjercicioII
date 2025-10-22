package com.mycompany.mavenproject1;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class CrearExcel {

    public static void exportarEntrenamiento(String grupo, String ejercicio) {
        String nombreArchivo = "entrenamientos.xlsx";

        try (Workbook libro = new XSSFWorkbook()) {
            Sheet hoja = libro.createSheet("Entrenamientos");

            Row filaCabecera = hoja.createRow(0);
            filaCabecera.createCell(0).setCellValue("Grupo muscular");
            filaCabecera.createCell(1).setCellValue("Ejercicio");

            Row fila = hoja.createRow(1);
            fila.createCell(0).setCellValue(grupo);
            fila.createCell(1).setCellValue(ejercicio);

            hoja.autoSizeColumn(0);
            hoja.autoSizeColumn(1);

            try (FileOutputStream archivo = new FileOutputStream(nombreArchivo)) {
                libro.write(archivo);
                System.out.println("Archivo Excel creado: " + nombreArchivo);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}