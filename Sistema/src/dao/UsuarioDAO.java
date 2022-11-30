/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import modelo.Login;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import modelo.empresaDTO;

/**
 *
 * @author jprez
 */
public class UsuarioDAO{
    
    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<empresaDTO> lista = new ArrayList<>();
    
    
    public ResultSet autenticacaoUsuario(Login objlogin)  {
        conn = new ConnectionFactory().getConnection();
        
        try {
            String sql = "select * from administrador where senha = ? and usuario = ?";
            
            java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, objlogin.getSenha());
            pstm.setString(2, objlogin.getUsuario());
            
            ResultSet rs = pstm.executeQuery();
            return rs;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "UsuarioDAO: " + erro);
            return null;
        }
    }    
    
    public void cadastrarMercado (empresaDTO objcanaisDTO) {
        
        String sql = "INSERT INTO clientes (nome , endereco, tel, divida )" + "VALUES (?, ?, ?, ?)";
        
        conn = new ConnectionFactory().getConnection();
        
        try {
            
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1, objcanaisDTO.getNome());
            pstm.setString(2, objcanaisDTO.getEndereco());
            pstm.setString(3, objcanaisDTO.getTel());
            pstm.setString(4, objcanaisDTO.getDivida());            
            pstm.execute();
            pstm.close();
            
        }   catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "cadastrarEmpresa" + erro);
        }
    }
    
    
    
    
    public ArrayList<empresaDTO> listarCanais() {
        
        String sql = "select * from clientes ";
        conn = new ConnectionFactory().getConnection();
        
        try{
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                mercadoDTO objmercadoDTO = new empresaDTO();
                objmercadoDTO.setId(rs.getInt("id"));
                objmercadoDTO.setNome(rs.getString("nome"));
                objmercadoDTO.setMarca(rs.getString("endereco"));
                objmercadoDTO.setPreco(rs.getString("tel"));
                objmercadoDTO.setTipo(rs.getString("divida"));

                lista.add(objmercadoDTO);

            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "CanaisDAO" + erro);
        }
        return lista;
    }
    
 public void excluirCadastros(empresaDTO objempresaDTO){
     String sql = "delete from clientes where id = ? ";
     conn = new ConnectionFactory().getConnection();
     
     try {

            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setInt(1, objempresaDTO.getId());

            pstm.execute();
            pstm.close();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "cadastrarCanal" + erro);
        }

    }
 
 public void alterarCadastro(empresaDTO objempresaDTO){
    String sql = "UPDATE clientes SET nome = ?, endereco = ?, tel = ?, divida = ? WHERE id = ?";    
   
    conn = new ConnectionFactory().getConnection();
    
    
      try {

           pstm = (PreparedStatement) conn.prepareStatement(sql);
           pstm.setString(1, objempresaDTO.getNome());
           pstm.setString(2, objempresaDTO.getEndereco());
           pstm.setString(3, objempresaDTO.getTel());
           pstm.setString(4, objempresaDTO.getDivida());
           pstm.setInt(6, objempresaDTO.getId());
            

           pstm.execute();
           pstm.close();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "AlterarCliente" + erro);
        }
 
 }
 
 public Vector  pesquisar(String pesq) throws Exception {
     Vector  tb = new Vector ();
     
     conn = new ConnectionFactory().getConnection();
     String sql = "select * from clientes where nome like '" + pesq + "%'";
     PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
     
     
     ResultSet rs = ps.executeQuery();
     while(rs.next()){
         Vector nl = new Vector();
         nl.add(rs.getInt("id"));
         nl.add(rs.getString("nome"));
         nl.add(rs.getString("endereco"));
         nl.add(rs.getString("tel"));
         nl.add(rs.getString("divida"));
         
         tb.add(nl);
         
     }
     return tb;
 }
 }
    
    
