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

    public static boolean comprobarContraseña(String contraseña, String confirmacionContraseña){
        if(confirmacionContraseña.equalsIgnoreCase(contraseña))
            return true;
        else
            return false;
    }
    
    public static void menu(SistemaTaller2 sistema, Scanner s){
        boolean resp = true;
        System.out.print("Ingrese rut de usuario: ");
        String rut = s.next();
        while(true){
            if(rut.equalsIgnoreCase("admin")){

            }else{
                while(sistema.comprobarRut(rut)==false){
                    System.out.print("Rut ingresado no esta en sistema, desea registrar un nuevo cliente? (si/no): ");
                    rut = s.next();
                    while(!rut.equalsIgnoreCase("si") && !rut.equalsIgnoreCase("no")){
                        System.out.print("Opcion ingresada erronea, ingrese nuevamente (si/no): ");
                        rut = s.next();
                    }
                    if(rut.equalsIgnoreCase("si")){
                        System.out.print("Ingrese rut del nuevo cliente: ");
                        rut = s.next();
                        System.out.print("Ingrese nombre del nuevo cliente: ");
                        String nombre = s.next();
                        System.out.print("Ingrese apellido del nuevo cliente: ");
                        String apellido = s.next();
                        System.out.print("Ingrese contraseña del nuevo cliente: ");
                        String contraseña = s.next();
                        System.out.print("Ingrese contraseña nuevamente: ");
                        String confirmacionContraseña = s.next();
                        while(comprobarContraseña(contraseña,confirmacionContraseña)==false){
                            System.out.print("Confirmacion de contraseña erronea, ingrese nuevamente: ");
                            confirmacionContraseña = s.next();
                        }
                        System.out.print("Ingrese saldo del nuevo cliente: ");
                        double saldo = s.nextDouble();
                        while(saldo<0){
                            System.out.print("Saldo ingresado erroneo, ingrese nuevamente: ");
                            saldo = s.nextDouble();
                        }
                        sistema.ingresarCliente(rut, nombre, apellido, contraseña, saldo);
                    }else{
                        System.out.print("Ingrese rut de usuario: ");
                        rut = s.next();
                    }
                }
                System.out.print("1) Datos Cliente\n2) Agregar Saldo\n3) Cambiar Contraseña\n4) Vender Vehiculo\n5) Comprar Vehiculo\n6) Cerrar Sesion\n7) Cerrar Sistema\nIngrese Opcion: ");
                String opcion = s.next();
                if(opcion.equalsIgnoreCase("7"))
                    break;
                while(!opcion.equalsIgnoreCase("6")){
                    while(!opcion.equalsIgnoreCase("1") && !opcion.equalsIgnoreCase("2") && !opcion.equalsIgnoreCase("3") && !opcion.equalsIgnoreCase("4") && !opcion.equalsIgnoreCase("5") && !opcion.equalsIgnoreCase("6")){
                        System.out.print("Opcion ingresada erronea, ingrese nuevamente: ");
                        opcion = s.next();
                    }
                    // vas en esta parte haciendo las opciones
                }
            }
        }
        System.out.println("---------------\nCerrando Sesion\n---------------");
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        SistemaTaller2 sistema = new SistemaTaller2Impl();
        Scanner s = new Scanner(System.in);
        
        menu(sistema,s);
        
        Vehiculo v = new Auto(2010,10,"f7","toyota",1000);
        Motocicleta.setRevisionTecnica(100);
        
        System.out.println(Math.round(v.precioVenta()*100.0)/100.0);  //Math.round(input*100.0)/100.0 para ponerlo con 2 decimales
        System.out.println(Math.round(v.precioCompra()*100.0)/100.0);
        
    }
    
}