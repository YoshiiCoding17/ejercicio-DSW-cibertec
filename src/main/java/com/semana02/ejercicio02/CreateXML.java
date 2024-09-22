package com.semana02.ejercicio02;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class CreateXML {
  
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente(1,"Juan","Perez","12345678");
        Cliente cliente2 = new Cliente(2,"Maria","Lopez","87654321");
        Cliente cliente3 = new Cliente(3,"Carlos","Gomez","45678912");
        
        ArrayList<Cliente> clientes = new ArrayList<>();
        clientes.add(cliente1);
        clientes.add(cliente2);
        clientes.add(cliente3);
    
        System.out.println(clientes);
    
        FileWriter fileWriter = null;

        try {
            File file = new File("C:/test/clientes_berru_G1.xml");
            fileWriter = new FileWriter(file);

            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            xmlMapper.writeValue(fileWriter, clientes);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
