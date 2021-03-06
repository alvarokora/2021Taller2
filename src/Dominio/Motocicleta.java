/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

/**
 *
 * @author deGrupo()
 */
public class Motocicleta extends Vehiculo{

    private static int revisionTecnica;
    private int año;

    public Motocicleta(int año, String placa, String modelo, double precio) {
        super(placa, modelo, precio);
        this.año = año;
    }

    public static int getRevisionTecnica() {
        return revisionTecnica;
    }

    public static void setRevisionTecnica(int revisionTecnica) {
        Motocicleta.revisionTecnica = revisionTecnica;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }
    
    @Override
    public double precioCompra() {
        return (super.getPrecio()/((double)77/100));
    }

    @Override
    public double precioVenta() {
        return (super.getPrecio()*0.87)-revisionTecnica;
    }   
    
}