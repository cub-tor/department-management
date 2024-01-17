/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestiondepartamentos_t9;
import java.sql.*;

/**
 *
 * @author a
 */
public class Departamento {
    
    private  int codigo;
    private String nombre;
    private int ID_localizacion;
    private int ID_manager;

    
    //Constructores
    public Departamento() {
    }

    public Departamento(int codigo, String nombre, int ID_localizacion, int ID_manager) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.ID_localizacion = ID_localizacion;
        this.ID_manager = ID_manager;
    }

    //Getter
    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getID_localizacion() {
        return ID_localizacion;
    }

    public int getID_manager() {
        return ID_manager;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setID_localizacion(int ID_localizacion) {
        this.ID_localizacion = ID_localizacion;
    }

    public void setID_manager(int ID_manager) {
        this.ID_manager = ID_manager;
    }
    
    
    
}
