/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

/**
 *
 * @author defGrupo()
 */
public class Auto extends Vehiculo{
    
    private static int revisionTecnica;
    private int año;
    private int rendimiento;

    public Auto(int año, int rendimiento, String placa, String modelo, double precio) {
        super(placa, modelo, precio);
        this.año = año;
        this.rendimiento = rendimiento;
    }

    public static int getRevisionTecnica() {
        return revisionTecnica;
    }

    public static void setRevisionTecnica(int revisionTecnica) {
        Auto.revisionTecnica = revisionTecnica;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getRendimiento() {
        return rendimiento;
    }

    public void setRendimiento(int rendimiento) {
        this.rendimiento = rendimiento;
    }

    @Override
    public double precioCompra() {
        return (super.getPrecio()/((double)81/100))+revisionTecnica;
    }

    @Override
    public double precioVenta() {
        return super.getPrecio()/((double)(81+rendimiento)/100);
    }
    
}