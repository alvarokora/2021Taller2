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
public class ListaVehiculo {
    
    private int max;
    private Vehiculo[] lista;
    private int cant;
    
    public ListaVehiculo(int max){
        lista = new Vehiculo[max];
        cant = 0;
        this.max=max;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public Vehiculo[] getLista() {
        return lista;
    }

    public void setLista(Vehiculo[] lista) {
        this.lista = lista;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }
    
    public boolean eliminarVehiculo(String placa){
	boolean resp;
	int i;
	for(i=0;i<cant;i++){
		if(lista[i].getPlaca().equalsIgnoreCase(placa)){
			break;
		}
	}
	if(i==cant){
		resp=false;
	}else{
		for(int j=i;j<cant-1;j++){
			lista[j]=lista[j+1];
		}
		cant--;
		resp=true;
	}
	return resp;
    }
    
    public Vehiculo getVehiculoI(int i){
        if(i>=0 && i<cant)
            return lista[i];
        else
            return null;
    }
    
}