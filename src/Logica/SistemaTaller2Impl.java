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
public class SistemaTaller2Impl implements SistemaTaller2{

    private ListaVehiculo listaVenta;
    private ListaCliente listaCliente;
    
    public SistemaTaller2Impl(){
        listaVenta = new ListaVehiculo(1000);
        listaCliente = new ListaCliente(1000);
    }
    
    @Override
    public boolean ingresarCliente(String rut, String nombre, String apellido, String contraseña, double saldo) {
        Cliente c = new Cliente(rut,nombre,apellido,contraseña,saldo);
        return listaCliente.addCliente(c);
    }

    @Override
    public boolean ingresarAuto(String modelo, String placa, double precio, int año, int rendimiento) {
        Vehiculo a = new Auto(año,rendimiento,placa,modelo,precio);
        return listaVenta.addVehiculo(a);
    }

    @Override
    public boolean ingresarMotocicleta(String modelo, String placa, double precio, int año) {
        Vehiculo m = new Motocicleta(año,placa,modelo,precio);
        return listaVenta.addVehiculo(m);
    }

    @Override
    public void ingresarRevisionTecnica(int revisionTecnica1, int revisionTecnica2) {
        Auto.setRevisionTecnica(revisionTecnica1);
        Motocicleta.setRevisionTecnica(revisionTecnica2);
    }

    @Override
    public boolean asociarAutoCliente(String rut, int año, int rendimiento, String placa, String modelo, double precio) {
        int i = 0;
        for(i=0;i<listaCliente.getCant();i++){
            if(listaCliente.getClienteI(i).getRut().equalsIgnoreCase(rut))
                break;
        }
        Vehiculo a = new Auto(año,rendimiento,placa,modelo,precio);
        return listaCliente.getClienteI(i).getInventario().addVehiculo(a);
    }
    
    @Override
    public boolean asociarMotocicletaCliente(String rut, int año, String placa, String modelo, double precio){
        int i = 0;
        for(i=0;i<listaCliente.getCant();i++){
            if(listaCliente.getClienteI(i).getRut().equalsIgnoreCase(rut))
                break;
        }
        Vehiculo m = new Motocicleta(año,placa,modelo,precio);
        return listaCliente.getClienteI(i).getInventario().addVehiculo(m);
    }

    @Override
    public boolean comprobarRut(String rut){
        for(int i=0;i<listaCliente.getCant();i++){
            if(listaCliente.getClienteI(i).getRut().equalsIgnoreCase(rut))
                return true;
        }
        return false;
    }

    @Override
    public String obtenerDatosCliente(String rut) {
        String r = "";
        int i = 0;
        for(i=0;i<listaCliente.getCant();i++){
            if(listaCliente.getClienteI(i).getRut().equalsIgnoreCase(rut))
                break;
        }
        r+="Nombre: "+listaCliente.getClienteI(i).getNombre()+", Apellido: "+listaCliente.getClienteI(i).getApellido()+"\nVehiculos:";
        if(listaCliente.getClienteI(i).getInventario().getCant()<=0){
            r+="\nNo tiene ningun vehiculo";
        }else{
            for(int j=0;j<listaCliente.getClienteI(i).getInventario().getCant();j++){
                if(listaCliente.getClienteI(i).getInventario().getVehiculoI(j) instanceof Motocicleta){
                    Motocicleta m = (Motocicleta) listaCliente.getClienteI(i).getInventario().getVehiculoI(j);
                    r+="\nPlaca: "+m.getPlaca()+", Tipo: Motocicleta, Año: "+m.getAño();
                }
                if(listaCliente.getClienteI(i).getInventario().getVehiculoI(j) instanceof Auto){
                    Auto a = (Auto) listaCliente.getClienteI(i).getInventario().getVehiculoI(j);
                    r+="\nPlaca: "+a.getPlaca()+", Tipo: Auto, Año: "+a.getAño()+", Rendimiento: "+a.getRendimiento()+"(Km/Lt)";
                }
            }
        }
        return r;
    }

    @Override
    public void agregarSaldo(String rut, String contraseña, double saldoNuevo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean cambiarContraseña(String rut, String contraseña, String nuevaContraseña, String nuevaContraseñaConfirmacion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtenerVehiculosCliente(String rut) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean venderVehiculo(String rut, String placa, boolean confirmacion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtenerVehiculosVenta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void comprarVehiculo(String rut, String placa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtenerInformacionCliente(String rut) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtenerMayorCantidadVehiculos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtenerVehiculosVentaUSD() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtenerGanancia() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}