/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Dominio.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author defGrupo()
 */
public class app {
    
    public static boolean lectura(SistemaTaller2 sistema){
        try{
            
            File arch = new File("clientes.txt");
            Scanner s = new Scanner(arch);
            while(s.hasNextLine()){
                String linea = s.nextLine();
                Scanner s1 = new Scanner(linea);
                s1.useDelimiter(",");
                String rut = s.next();
                String nombre = s.next();
                String apellido = s.next();
                String contraseña = s.next();
                int cantidad = s.nextInt();
                double saldo = s.nextDouble();
                sistema.ingresarCliente(rut, nombre, apellido, contraseña, saldo);
            }
            s.close();
            
            arch = new File("vehiculos.txt");
            s = new Scanner(arch);
            while(s.hasNextLine()){
                String linea = s.nextLine();
                Scanner s1 = new Scanner(linea);
                s1.useDelimiter(",");
                String rut = s1.next();
                String modelo = s1.next();
                String placa = s1.next();
                double precio = s1.nextDouble();
                int año = s1.nextInt();
                String tipo = s1.next();
                if(!rut.equalsIgnoreCase("En venta")){
                    if(tipo.equalsIgnoreCase("Auto")){
                        int rendimiento = s.nextInt();
                        sistema.asociarAutoCliente(rut, año, rendimiento, placa, modelo, precio);
                    }else{
                        sistema.asociarMotocicletaCliente(rut, año, placa, modelo, precio);
                    }
                }else{
                    if(tipo.equalsIgnoreCase("Auto")){
                        int rendimiento = s.nextInt();
                        sistema.ingresarAuto(modelo, placa, precio, año, rendimiento);
                    }else{
                        sistema.ingresarMotocicleta(modelo, placa, precio, año);
                    }
                }
            }
            s.close();
            
            return true;
        }catch(FileNotFoundException ex){
            return false;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Vehiculo v = new Auto(2010,10,"f7","toyota",1000);
        Motocicleta.setRevisionTecnica(100);
        
        System.out.println(Math.round(v.precioVenta()*100.0)/100.0);  //Math.round(input*100.0)/100.0 para ponerlo con 2 decimales
        System.out.println(Math.round(v.precioCompra()*100.0)/100.0);
        
    }
    
}