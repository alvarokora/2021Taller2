/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Dominio.*;

/**
 *
 * @author defGrupo()
 */
public class app {

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