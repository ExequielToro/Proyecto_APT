/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;

public class ProductoDao {
    Conexion conn;

    public ProductoDao() {
        
        conn = new Conexion();
    }
    
    
    
    public List<Producto> fun_mostrarProducto()
    {
        Connection acceso = conn.getCnn();
        Producto objProd = null;
        List<Producto> lista = new ArrayList();
        try {
            CallableStatement cs = acceso.prepareCall("{call PRODUCTO_pkg.proc_mostrarTodosProducto(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(1);
            while (rs.next())
            {
                objProd = new Producto();
                objProd.setId_producto(rs.getInt(1));
                objProd.setNombre(rs.getString(2));
                objProd.setPrecio(rs.getInt(3));
                objProd.setStock(rs.getInt(4));
                objProd.setBtu(rs.getInt(5));
                objProd.setMarca(rs.getString(6));
                lista.add(objProd);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
    
    }            
    
    public Producto fun_mostrarunProducto(int p_id_producto)
    {
        Connection acceso = conn.getCnn();
        Producto objProd = new Producto();
        
        try {
            CallableStatement cs = acceso.prepareCall("{call PRODUCTO_pkg.proc_mostrarUnProducto(?,?)}");
            cs.setInt(1, p_id_producto);
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(2);
            while (rs.next())
            {
                
                objProd.setId_producto(rs.getInt(1));
                objProd.setNombre(rs.getString(2));
                objProd.setPrecio(rs.getInt(3));
                objProd.setStock(rs.getInt(4));
                objProd.setBtu(rs.getInt(5));
                objProd.setMarca(rs.getString(6));
               
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return objProd;
    
    }   

public boolean fun_actualizarStock(int codigo_prod, int cantidad, int precio )
{
    Connection acceso = conn.getCnn();
    boolean result = false;
    
        try {
            
            CallableStatement cs = acceso.prepareCall("{call Producto_pkg.upd_descuentaStock(?,?,?)}");
            cs.setInt(1, codigo_prod);
            cs.setInt(2,cantidad);
            cs.setInt(3,precio);
           
            
            if (!cs.execute())
                result = true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    
    
}
public boolean fun_eliminarProducto(int p_id_producto) {
    Connection acceso = conn.getCnn();
    boolean result = false;
    
    try {
        CallableStatement cs = acceso.prepareCall("{call Producto_pkg.del(?)}");
        cs.setInt(1, p_id_producto);
        
        int rowsAffected = cs.executeUpdate();
        
        if (rowsAffected > 0) {
            result = true;
        }
    } catch (SQLException ex) {
        Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return result;
}


public boolean fun_ingresarProducto(
            int p_PRECIO,
            int p_BTU,
            String p_MARCA,
            String p_NOMBRE,
            int p_STOCK)
{
    Connection acceso = conn.getCnn();
    boolean result = false;
    
        try {
            
            CallableStatement cs = acceso.prepareCall("{call Producto_pkg.ins(?,?,?,?,?)}");
            cs.setInt(1, p_PRECIO);
            cs.setInt(2, p_BTU);
            cs.setString(3, p_MARCA);
            cs.setString(4, p_NOMBRE);
            cs.setInt(5,p_STOCK);
            
            if (!cs.execute())
                result = true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    
    
}



}

