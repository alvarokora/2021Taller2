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
        System.out.println("----------\nBienvenido\n----------");
        System.out.print("Ingrese rut de usuario: ");
        String rut = s.next();
        System.out.print("Ingrese contraseña del usuario: ");
        String contraseña = s.next();
        String opcion = "";
        if(lectura(sistema)){
            System.out.println("Uno de los archivos no existe");
        }else{
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
                            contraseña = s.next();
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
                    while(sistema.comprobarContraseña(rut, contraseña)==false){
                        System.out.print("Contraseña ingresada erronea, ingrese nuevamente: ");
                        contraseña = s.next();
                    }
                    System.out.print("1) Datos Cliente\n2) Agregar Saldo\n3) Cambiar Contraseña\n4) Vender Vehiculo\n5) Comprar Vehiculo\n6) Cerrar Sesion\n7) Cerrar Sistema\nIngrese Opcion: ");
                    opcion = s.next();
                    if(opcion.equalsIgnoreCase("7"))
                        break;
                    while(!opcion.equalsIgnoreCase("6")){
                        while(!opcion.equalsIgnoreCase("1") && !opcion.equalsIgnoreCase("2") && !opcion.equalsIgnoreCase("3") && !opcion.equalsIgnoreCase("4") && !opcion.equalsIgnoreCase("5") && !opcion.equalsIgnoreCase("6")){
                            System.out.print("Opcion ingresada erronea, ingrese nuevamente: ");
                            opcion = s.next();
                        }
                        if(opcion.equalsIgnoreCase("1")){
                            System.out.println(sistema.obtenerDatosCliente(rut));
                        }
                        if(opcion.equalsIgnoreCase("2")){
                            System.out.print("Ingrese saldo a agregar al usuario: ");
                            double saldoNuevo = s.nextDouble();
                            while(saldoNuevo<=0){
                                System.out.print("Saldo ingresado erroneo, ingrese nuevamente: ");
                                saldoNuevo = s.nextDouble();
                            }
                            System.out.print("Ingrese contraseña para confirmar: ");
                            contraseña = s.next();
                            while(sistema.comprobarContraseña(rut, contraseña)==false){
                                System.out.print("Contraseña ingresada erronea, ingrese nuevamente: ");
                                contraseña = s.next();
                            }
                            sistema.agregarSaldo(rut, contraseña, saldoNuevo);
                        }
                        if(opcion.equalsIgnoreCase("3")){
                            System.out.print("Ingrese contraseña actual del usuario: ");
                            contraseña = s.next();
                            while(sistema.comprobarContraseña(rut, contraseña)==false){
                                System.out.print("Contraseña ingresada erronea, ingrese nuevamente: ");
                                contraseña = s.next();
                            }
                            System.out.print("Ingrese contraseña nueva: ");
                            String nuevaContraseña = s.next();
                            System.out.print("Ingrese nuevamente la contraseña: ");
                            String nuevaContraseñaConfirmacion = s.next();
                            while(comprobarContraseña(nuevaContraseña,nuevaContraseñaConfirmacion)==false){
                                System.out.print("Confirmacion de contraseña erronea, ingrese nuevamente: ");
                                nuevaContraseñaConfirmacion = s.next();
                            }
                            sistema.cambiarContraseña(rut, contraseña, nuevaContraseña, nuevaContraseñaConfirmacion);
                        }
                        if(opcion.equalsIgnoreCase("4")){
                            System.out.println(sistema.obtenerVehiculosCliente(rut));
                            if(!sistema.obtenerVehiculosCliente(rut).equalsIgnoreCase("El cliente no tiene vehiculos")){
                                System.out.print("Ingrese placa del vehiculo a vender: ");
                                String placa = s.next();
                                while(sistema.comprobarPlaca(rut, placa)==false){
                                    System.out.print("Placa ingresada erronea, ingrese nuevamente: ");
                                    placa = s.next();
                                }
                                System.out.print("Confirme si es que lo vendera (si/no): ");
                                String confirmacion = s.next();
                                while(!confirmacion.equalsIgnoreCase("si") && !confirmacion.equalsIgnoreCase("no")){
                                    System.out.print("La opcion ingresada es erronea, ingrese nuevamente (si/no): ");
                                    confirmacion = s.next();
                                }
                                if(confirmacion.equalsIgnoreCase("si")){
                                    sistema.venderVehiculo(rut, placa, true);
                                    System.out.println("Vehiculo vendido");
                                }else{
                                    sistema.venderVehiculo(rut, placa, false);
                                    System.out.println("Vehiculo no vendido");
                                }
                            }
                        }
                        if(opcion.equalsIgnoreCase("5")){
                            System.out.println(sistema.obtenerVehiculosVenta());
                            if(!sistema.obtenerVehiculosVenta().equalsIgnoreCase("No hay vehiculos a la venta")){
                                System.out.print("Ingrese placa del vehiculo a comprar: ");
                                String placa = s.next();
                                while(sistema.comprobarPlacaVenta(placa)==false){
                                    System.out.print("Placa ingresada erronea, ingrese nuevamente: ");
                                    placa = s.next();
                                }
                                System.out.println(sistema.comprarVehiculo(rut, placa));
                            }
                        }
                        System.out.print("1) Datos Cliente\n2) Agregar Saldo\n3) Cambiar Contraseña\n4) Vender Vehiculo\n5) Comprar Vehiculo\n6) Cerrar Sesion\n7) Cerrar Sistema\nIngrese Opcion: ");
                        opcion = s.next();
                    }
                    if(opcion.equalsIgnoreCase("6")){
                        System.out.println("---------------\nCerrando Sesion\n---------------");
                    }
                    if(opcion.equalsIgnoreCase("7"))
                        break;
                }
                System.out.print("Ingrese rut de usuario: ");
                rut = s.next();
                System.out.print("Ingrese contraseña del usuario: ");
                contraseña = s.next();
            }
        }
        System.out.println("-----------------------------\nGracias por ocupar el sistema\n-----------------------------");
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