/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestiondepartamentos_t9;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author a
 */
public class BdDepartamento {
        private Departamento departamento = new Departamento ();
      
        //Mete registros en la base de datos
        public boolean altas (Departamento departamento){
          int resultadoUpdate=0;
          try{
              //llamada al driver de mysql (siempre es igual para mysql)
              Class.forName("com.mysql.jdbc.Driver");
              //Conexion con la base de datos. Parámetros: nombre servidor(a través de jdbc:mysql//ruta, nombre usuario y password
              Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/xxx",
                      "usuario1","1234");
              
              //Sentencia sql, con interrogaciones para sustituir los parámetros por valores concretos
              String sql ="insert into departamento values (?,?,?,?)";
              PreparedStatement sentenciaSql= conexion.prepareStatement(sql);
              
              //Valores con forma determinada que quiero meter (rellenar los parámetros ???)parámetros: posicion, nombre del parámetro
              sentenciaSql.setInt(1, departamento.getCodigo());
              sentenciaSql.setString(2, departamento.getNombre());
              sentenciaSql.setInt(3, departamento.getID_localizacion());
              sentenciaSql.setInt(4, departamento.getID_manager());
              
              //excuteUpdate lanza todas las sentencias de modificación de datos. Manda la instruccion a mySql. Devuelve un valor entero, dependiendo de si el resultado ha sido correcto(1) o no (0)
              resultadoUpdate=sentenciaSql.executeUpdate();
              conexion.close();
              //Paso el resultado de excuteUpdate a un booleano para que se entienda mejor. Si el resultado es correcto = true, si el resultado es incorrecto =false.
              if(resultadoUpdate==1){
                  return true;
              } else {
                  return false;
              }
              
          
          }
          catch(Exception ex){
              
              System.out.println (ex.getMessage());

            }
         return false;
    }
        
 
    
    //Buscar por codigo y borrar el registro
     public boolean borrarRegistro(int codigo){
         
         int resultadoUpdate=0;
        try{
              //llamada al driver de mysql (siempre es igual para mysql)
              Class.forName("com.mysql.jdbc.Driver");
              //Conexion con la base de datos. Parámetros: nombre servidor(a través de jdbc:mysql//ruta, nombre usuario y password
              Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/xxx",
                      "usuario1","1234");
              
              //Leer registros
               String sql ="delete from departamento where codigo=(?)";
              PreparedStatement sentenciaSql= conexion.prepareStatement(sql);
               
               sentenciaSql.setInt(1, codigo);
            
             //excuteUpdate lanza todas las sentencias de modificación de datos. Manda la instruccion a mySql. Devuelve un valor entero, dependiendo de si el resultado ha sido correcto(1) o no (0)
              resultadoUpdate=sentenciaSql.executeUpdate();
              conexion.close();
              //Paso el resultado de excuteUpdate a un booleano para que se entienda mejor. Si el resultado es correcto = true, si el resultado es incorrecto =false.
              if(resultadoUpdate==1){
                  return true;
              } else {
                  return false;
              }
          
        }
        catch(Exception ex){
              
              System.out.println (ex.getMessage());

            }
      return false;   
        
    }
     //Escribir el código del registro que se quiere modificar, y en los campos correspondientes los cambios 
     public boolean modificarRegistro(Departamento departamento){
         
         int resultadoUpdate=0;
       try{
           
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/xxx","usuario1","1234");
           
            
            String sql = "update departamento set nombre=?, ID_localizacion=?, ID_manager=? where codigo=?"; 
             PreparedStatement sentenciaSql=conexion.prepareStatement(sql);
             
              
              sentenciaSql.setString(1, departamento.getNombre());
              sentenciaSql.setInt(2, departamento.getID_localizacion());
              sentenciaSql.setInt(3, departamento.getID_manager());
              sentenciaSql.setInt(4, departamento.getCodigo());
            
              //Aqui executeUpdate sin parámetro
             resultadoUpdate = sentenciaSql.executeUpdate();
            
            sentenciaSql.close();
            conexion.close ();
         
         if(resultadoUpdate==1){
                  return true;
              } else {
                  return false;
              }
         
       } catch (Exception ex) {
            System.out.println (ex.getMessage());
       }
       return false;
     }
     
  public Departamento buscarRegistro(int codigo){
      
      Departamento dep=null;
      int codigoActual = 0;
      String nombreActual="";
      int ID_localizacionActual=0;
      int ID_managerActual=0;
      
      
        try {
            //cargar Driver
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/xxx","usuario1","1234");
           
            //Consulta
            String sql = "select * from departamento where codigo like '%"+codigo+"%'"; 
             Statement sentenciaSql=conexion.createStatement();
             
             
             //Conjunto de registros puede contener 0, 1, o varios registros. En este caso filtra por código, así que va a contener 0 o 1 registros.
             ResultSet result = sentenciaSql.executeQuery(sql);
      
            //Si tiene registro siguiente devuelve true, y los valores del registro actual.  Si no tiene, devuelve false, y dep será null 
            if (result.next()){
                codigoActual = result.getInt(1) ;
                nombreActual = result.getString(2);
                ID_localizacionActual = result.getInt(3);
                ID_managerActual = result.getInt(4);
                dep = new Departamento (codigoActual, nombreActual, ID_localizacionActual, ID_managerActual);
            } else {
              JOptionPane.showMessageDialog(null,"No se encuentra ese registro");  
            
            }
            
            //Cerrar base de datos
            sentenciaSql.close();
            conexion.close ();
            
            
        } catch (Exception ex) {
            System.out.println (ex.getMessage());
        }
        return dep;
    
    }
  
  
  //El metodo excuteQuery devuelve resultSet y  se recorre el resultSet y para cada registro. Se crea un objetoDepartamento y se mete en un array
  public ArrayList<Departamento> buscarTodosLosRegistros (){
      
     
      ArrayList<Departamento> arrayDepartamentos= new ArrayList <Departamento>();
      int codigoActual = 0;
      String nombreActual="";
      int ID_localizacionActual=0;
      int ID_managerActual=0;
      Departamento dep = new Departamento ();
      
       try{
            //Cargar driver y conectar con la base de datos
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/xxx","usuario1","1234");
           
            //Consulta a la base de datos
            String sql = "select * from departamento";
            Statement sentenciaSql= conexion.createStatement();
             
             
             //Conjunto de registros leidos de la base de datos. Clase similar a una lista
             ResultSet result = sentenciaSql.executeQuery(sql);
             
                 // Recorremos el resultado, mientras haya registros para leer, y escribimos el resultado en pantalla
                 //Los índices 1, 2, 3 y 4 indican qué columna de la tabla de base de datos necesito. También se puede usar un string con el nombre de la columna.
                 while (result.next()){
                    codigoActual = result.getInt(1) ;
                    nombreActual = result.getString(2);
                    ID_localizacionActual = result.getInt(3);
                    ID_managerActual = result.getInt(4);
                    dep = new Departamento (codigoActual, nombreActual, ID_localizacionActual, ID_managerActual);
                    arrayDepartamentos.add(dep);
                    
                  }
                         
     
            //Cierra la base de datos
            sentenciaSql.close();
            conexion.close ();
            
        }catch (Exception ex) {
            System.out.println (ex.getMessage());
        }
  

     return arrayDepartamentos;

}
}
     
    
     
     
     
     

