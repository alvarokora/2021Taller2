/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author defGrupo()
 */
public interface SistemaTaller2 {
    
    public boolean ingresarCliente(String rut, String nombre, String apellido, String contraseña, double saldo);
    public boolean ingresarAuto(String modelo, String placa, double precio, int año, int rendimiento);
    public boolean ingresarMotocicleta(String modelo, String placa, double precio, int año);
    public void ingresarRevisionTecnica(int revisionTecnica1, int revisionTecnica2);
    public boolean asociarAutoCliente(String rut, int año, int rendimiento, String placa, String modelo, double precio);
    public boolean asociarMotocicletaCliente(String rut, int año, String placa, String modelo, double precio);
    public boolean comprobarRut(String rut);
    public boolean comprobarContraseña(String rut, String contraseña);
    public String obtenerDatosCliente(String rut);
    public void agregarSaldo(String rut, String contraseña, double saldoNuevo);
    public boolean cambiarContraseña(String rut, String contraseña, String nuevaContraseña, String nuevaContraseñaConfirmacion);
    public String obtenerVehiculosCliente(String rut);
    public boolean comprobarPlaca(String rut, String placa);
    public boolean venderVehiculo(String rut, String placa, boolean confirmacion);
    public String obtenerVehiculosVenta();
    public boolean comprobarPlacaVenta(String placa);
    public String comprarVehiculo(String rut, String placa);
    public String obtenerInformacionCliente(String rut);
    public String obtenerMayorCantidadVehiculos();
    public String obtenerVehiculosVentaUSD();
    public String obtenerGanancia();
    
}