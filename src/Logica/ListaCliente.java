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
public class ListaCliente {
    
    private int max;
    private int cant;
    private Cliente[] lista;
    
    public ListaCliente(int max){
        lista = new Cliente[max];
        cant = 0;
        this.max=max;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public Cliente[] getLista() {
        return lista;
    }

    public void setLista(Cliente[] lista) {
        this.lista = lista;
    }
    
    public boolean addCliente(Cliente c){
        if(cant<max){
            lista[cant]=c;
            cant++;
            return true;
        }else
            return false;
    }
    
    public Cliente getClienteI(int i){
        if(i>=0 && i<cant)
            return lista[i];
        else
            return null;
    }
    
    public Cliente buscarCliente(String rut){
	int j;
	for(j=0;j<cant;j++){
		if(lista[j].getRut().equalsIgnoreCase(rut))
			break;
	}
	if(j==cant)
		return null;
	else
		return lista[j];
    }
    
}