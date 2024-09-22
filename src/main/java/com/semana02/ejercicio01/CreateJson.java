package com.semana02.ejercicio01;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CreateJson {
    public static void main(String[] args) {
        
        
        Auto auto = new Auto(1, "Toyota");
        System.out.println(auto);
        Auto auto2 = new Auto(2, "Nissan");
        System.out.println(auto2);
        Auto auto3 = new Auto(3, "Mazda");

        ArrayList<Auto> autos = new ArrayList<>();
        autos.add(auto);
        autos.add(auto2);
        autos.add(auto3);
        System.out.println(autos);

        FileWriter fileWriter = null;

        try{
            File file = new File("C:/test/autos_berru_G1.json");
            fileWriter = new FileWriter(file);
            
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(autos, fileWriter);
            System.out.println("Archivo JSON creado correctamente");

        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }finally {

            try {

                fileWriter.close();

            } catch (Exception e) {

                e.printStackTrace();

            }

        }
    }
}
