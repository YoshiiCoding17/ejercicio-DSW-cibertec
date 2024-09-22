package com.semana02.ejercicio06;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.semana02.ejercicio05.MySqlDBConexion;

public class GenerateZipCatalogo {
   public static void main(String[] args) {
         GenerateZipCatalogo generateZipCatalogo = new GenerateZipCatalogo();
         generateZipCatalogo.procesar();
    }

    public void procesar(){
         ArrayList<Catalogo> catalogos = new ArrayList<Catalogo>();
        //1 Generar el arraylist de catalogos de la base de datos
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = MySqlDBConexion.getConexion();
            String query = "SELECT * FROM catalogo";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idCatalogo = resultSet.getInt("idCatalogo");
                String descripcion = resultSet.getString("descripcion");
                Catalogo catalogo = new Catalogo(idCatalogo, descripcion);
                catalogos.add(catalogo);
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        System.out.println(catalogos);

        //2 Generar el archivo JSON de los catalogos
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("C:/test/catalogo.json");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(catalogos, fileWriter);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //3 Generar el archivo excel de los catalogos
        FileWriter fileWriterXML = null;
        String[] HEADERS = { "ID Catalogo", "Descripcion"};
        String SHEET_NAME = "Catálogos";
        int[] COLUMN_WIDTHS = { 3000, 7000, 3000, 3000 };

        try {
            FileOutputStream fileOut = new FileOutputStream("C:/test/catalogo.xlsx");
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet(SHEET_NAME);

            // Establecer el ancho de las columnas
            for (int i = 0; i < COLUMN_WIDTHS.length; i++) {
                sheet.setColumnWidth(i, COLUMN_WIDTHS[i]);
            }

            // Crear la fila de encabezado
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < HEADERS.length; i++) {
                headerRow.createCell(i).setCellValue(HEADERS[i]);
            }

            // Crear las filas de los catalogos
            for (int i = 0; i < catalogos.size(); i++) {
                Catalogo catalogo = catalogos.get(i);
                sheet.createRow(i + 1).createCell(0).setCellValue(catalogo.getIdCatalogo());
                sheet.getRow(i + 1).createCell(1).setCellValue(catalogo.getDescripcion());
            }
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
         
        }

        //4 Generar el archivo ZIP del JSON y XML
       String[] files = { "C:/test/catalogo.json", "C:/test/catalogo.xlsx" };

       try {
            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream("C:/test/catalogoComprimido.zip"));
            for (String ruta : files) {
                System.out.println("Agregando archivo: " + ruta);

                //crear un archivo
                File file = new File(ruta);
                FileInputStream fis = new FileInputStream(file);
                
                //Crear un archivo en el zip
                ZipEntry zipEntry = new ZipEntry(file.getName());
                zipOut.putNextEntry(zipEntry);

                //Escribir el contenido del archivo
                int byteLeidos = 0;
                while( (byteLeidos = fis.read()) != -1){
                    zipOut.write(byteLeidos);
                }

                fis.close();
                zipOut.closeEntry();
            }
            zipOut.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
