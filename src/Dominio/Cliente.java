/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Logica.*;

/**
 *
 * @author defGrupo()
 */
public class Cliente {
    
    private String rut;
    private String nombre;
    private String apellido;
    private String contraseña;
    private ListaVehiculo inventario;
    private double saldo;

    public Cliente(String rut, String nombre, String apellido, String contraseña, double saldo) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contraseña = contraseña;
        this.saldo = saldo;
        inventario = new ListaVehiculo(1000);
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public ListaVehiculo getInventario() {
        return inventario;
    }

    public void setInventario(ListaVehiculo inventario) {
        this.inventario = inventario;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
}