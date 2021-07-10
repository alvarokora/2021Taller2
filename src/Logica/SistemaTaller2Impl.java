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
    public boolean comprobarContraseña(String rut, String contraseña){
        for(int i=0;i<listaCliente.getCant();i++){
            if(listaCliente.getClienteI(i).getRut().equalsIgnoreCase(rut)){
                if(listaCliente.getClienteI(i).getContraseña().equalsIgnoreCase(contraseña))
                    return true;
            }
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
        for(int i=0;i<listaCliente.getCant();i++){
            if(listaCliente.getClienteI(i).getRut().equalsIgnoreCase(rut)){
                listaCliente.getClienteI(i).setSaldo(listaCliente.getClienteI(i).getSaldo()+saldoNuevo);
                break;
            }
        }
    }

    @Override
    public boolean cambiarContraseña(String rut, String contraseña, String nuevaContraseña, String nuevaContraseñaConfirmacion) {
        for(int i=0;i<listaCliente.getCant();i++){
            if(listaCliente.getClienteI(i).getRut().equalsIgnoreCase(rut)){
                listaCliente.getClienteI(i).setContraseña(nuevaContraseña);
                return true;
            }
        }
        return false;
    }

    @Override
    public String obtenerVehiculosCliente(String rut) {
        String r = "";
        for(int i=0;i<listaCliente.getCant();i++){
            if(listaCliente.getClienteI(i).getRut().equalsIgnoreCase(rut)){
                for(int j=0;j<listaCliente.getClienteI(i).getInventario().getCant();j++){
                    r+="Placa: "+listaCliente.getClienteI(i).getInventario().getVehiculoI(j).getPlaca()+", Precio: "+listaCliente.getClienteI(i).getInventario().getVehiculoI(j).getPrecio();
                    if(listaCliente.getClienteI(i).getInventario().getVehiculoI(j) instanceof Motocicleta){
                        Vehiculo m = (Motocicleta) listaCliente.getClienteI(i).getInventario().getVehiculoI(j);
                        r+=", Precio de venta: "+(Math.round(m.precioVenta()*100.0)/100.0)+"\n";
                    }
                    if(listaCliente.getClienteI(i).getInventario().getVehiculoI(j) instanceof Auto){
                        Vehiculo a = (Auto) listaCliente.getClienteI(i).getInventario().getVehiculoI(j);
                        r+=", Precio de venta: "+(Math.round(a.precioVenta()*100.0)/100.0)+"\n";
                    }
                }
                if(listaCliente.getClienteI(i).getInventario().getCant()==0)
                    return "El cliente no tiene vehiculos";
                break;
            }
        }
        return r;
    }
    
    @Override
    public boolean comprobarPlaca(String rut, String placa){
        for(int i=0;i<listaCliente.getCant();i++){
            if(listaCliente.getClienteI(i).getRut().equalsIgnoreCase(rut)){
                for(int j=0;j<listaCliente.getClienteI(i).getInventario().getCant();j++){
                    if(listaCliente.getClienteI(i).getInventario().getVehiculoI(j).getPlaca().equalsIgnoreCase(placa))
                        return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean venderVehiculo(String rut, String placa, boolean confirmacion) {
        if(confirmacion==false)
            return false;
        else{
            for(int i=0;i<listaCliente.getCant();i++){
                if(listaCliente.getClienteI(i).getRut().equalsIgnoreCase(rut)){
                    Vehiculo v = listaCliente.getClienteI(i).getInventario().buscarVehiculo(placa);
                    listaVenta.addVehiculo(v);
                    if(v instanceof Motocicleta){
                        v = (Motocicleta) listaCliente.getClienteI(i).getInventario().buscarVehiculo(placa);
                        listaCliente.getClienteI(i).setSaldo(listaCliente.getClienteI(i).getSaldo()+v.precioVenta());
                    }
                    if(v instanceof Auto){
                        v = (Auto) listaCliente.getClienteI(i).getInventario().buscarVehiculo(placa);
                        listaCliente.getClienteI(i).setSaldo(listaCliente.getClienteI(i).getSaldo()+v.precioVenta());
                    }
                    listaCliente.getClienteI(i).getInventario().eliminarVehiculo(placa);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String obtenerVehiculosVenta() {
        String r = "";
        if(listaVenta.getCant()==0)
            return "No hay vehiculos a la venta";
        else{
            for(int i=0;i<listaVenta.getCant();i++){
                r+="Placa: "+listaVenta.getVehiculoI(i).getPlaca()+", Precio: "+listaVenta.getVehiculoI(i).getPrecio()+"\n";
            }
        }
        return r;
    }
    
    @Override
    public boolean comprobarPlacaVenta(String placa){
        if(listaVenta.buscarVehiculo(placa)==null)
            return false;
        else
            return true;
    }

    @Override
    public String comprarVehiculo(String rut, String placa) {
        Vehiculo v = listaVenta.buscarVehiculo(placa);
        if(v instanceof Auto){
            v = (Auto) listaVenta.buscarVehiculo(placa);
        }
        if(v instanceof Motocicleta){
            v = (Motocicleta) listaVenta.buscarVehiculo(placa);
        }
        if(listaCliente.buscarCliente(rut).getSaldo()-v.precioCompra()<0)
            return "El cliente no tiene suficiente saldo para comprar el vehiculo";
        else{
            listaVenta.eliminarVehiculo(placa);
            listaCliente.buscarCliente(rut).getInventario().addVehiculo(v);
            listaCliente.buscarCliente(rut).setSaldo(listaCliente.buscarCliente(rut).getSaldo()-v.precioCompra());
            return "Vehiculo comprado";
        }
    }

    @Override
    public String obtenerInformacionCliente(String rut) {
        String r = "";
        for(int i=0;i<listaCliente.buscarCliente(rut).getInventario().getCant();i++){
            r+="Modelo: "+listaCliente.buscarCliente(rut).getInventario().getVehiculoI(i).getModelo()+", Precio: "+((listaCliente.buscarCliente(rut).getInventario().getVehiculoI(i).getPrecio())/730)+"(USD)\n";
        }
        if(listaCliente.buscarCliente(rut).getInventario().getCant()==0)
            return "El cliente no tiene vehiculos";
        else
            return r;
    }

    @Override
    public String obtenerMayorCantidadVehiculos() {
        String r = "";
        int indiceClienteMayor = 0;
        int mayor = -9999999;
        for(int i=0;i<listaCliente.getCant();i++){
            if(listaCliente.getClienteI(i).getInventario().getCant()>=mayor){
                indiceClienteMayor = i;
                mayor=listaCliente.getClienteI(i).getInventario().getCant();
            }
        }
        r+="Rut: "+listaCliente.getClienteI(indiceClienteMayor).getRut();
        for(int i=0;i<listaCliente.getClienteI(indiceClienteMayor).getInventario().getCant();i++){
            r+="\nPlaca: "+listaCliente.getClienteI(indiceClienteMayor).getInventario().getVehiculoI(i).getPlaca()+", Modelo: "+listaCliente.getClienteI(indiceClienteMayor).getInventario().getVehiculoI(i).getModelo();
            if(listaCliente.getClienteI(indiceClienteMayor).getInventario().getVehiculoI(i) instanceof Motocicleta){
                Vehiculo m = (Motocicleta) listaCliente.getClienteI(indiceClienteMayor).getInventario().getVehiculoI(i);
                r+=", Precio de venta: "+(Math.round(m.precioVenta()*100.0)/100.0);
            }
            if(listaCliente.getClienteI(indiceClienteMayor).getInventario().getVehiculoI(i) instanceof Auto){
                Vehiculo a = (Auto) listaCliente.getClienteI(indiceClienteMayor).getInventario().getVehiculoI(i);
                r+=", Precio de venta: "+(Math.round(a.precioVenta()*100.0)/100.0);
            }
        }
        return r;
    }

    @Override
    public String obtenerVehiculosVentaUSD() {
        String r = "";
        for(int i=0;i<listaVenta.getCant();i++){
            r+="Placa: "+listaVenta.getVehiculoI(i).getPlaca()+", Modelo: "+listaVenta.getVehiculoI(i).getModelo();
            if(listaVenta.getVehiculoI(i) instanceof Motocicleta){
                Vehiculo m = (Motocicleta) listaVenta.getVehiculoI(i);
                r+=", Precio de compra: "+((Math.round(m.precioCompra()*100.0)/100.0)/730)+"(USD), Precio de venta: "+((Math.round(m.precioVenta()*100.0)/100.0)/730)+"(USD)\n";
            }
            if(listaVenta.getVehiculoI(i) instanceof Auto){
                Vehiculo a = (Auto) listaVenta.getVehiculoI(i);
                r+=", Precio de compra: "+((Math.round(a.precioCompra()*100.0)/100.0)/730)+"(USD), Precio de venta: "+((Math.round(a.precioVenta()*100.0)/100.0)/730)+"(USD)\n";
            }
        }
        return r;
    }

    @Override
    public String obtenerGanancia() {
        String r = "";
        for(int i=0;i<listaVenta.getCant();i++){
            r+="Placa: "+listaVenta.getVehiculoI(i).getPlaca()+", Modelo: "+listaVenta.getVehiculoI(i).getModelo();
            if(listaVenta.getVehiculoI(i) instanceof Motocicleta){
                Vehiculo m = (Motocicleta) listaVenta.getVehiculoI(i);
                r+=", Ganancia: "+((Math.round(m.precioCompra()*100.0)/100.0)-(Math.round(m.precioVenta()*100.0)/100.0))+"\n";
            }
            if(listaVenta.getVehiculoI(i) instanceof Auto){
                Vehiculo a = (Auto) listaVenta.getVehiculoI(i);
                r+=", Ganancia: "+((Math.round(a.precioCompra()*100.0)/100.0)-(Math.round(a.precioVenta()*100.0)/100.0))+"\n";
            }
        }
        return r;
    }
    
    @Override
    public void cerrarSistema(ListaCliente listaClienteActualizado, ListaVehiculo listaVentaActualizado){
        listaClienteActualizado = listaCliente;
        listaVentaActualizado = listaVenta;
    }
    
}