/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author jprez
 */
public class empresaDTO {
    private int id;
    private String nome, endereco, tel, divida;
    
    public String getNome(){
        return nome;
    }
    
    public void setNome (String nome){
        this.nome = nome;
    }
    
    public String getEndereco(){
        return endereco;
    }
    
    public void setEndereco (String endereco){
        this.endereco = endereco;
    }
    
    public String getTel(){
        return tel;
    }
    
    public void setTel (String tel){
        this.tel = tel;
    }
    
    public String getDivida(){
        return divida;
    } 
    
    public void setDivida (String divida){
        this.divida = divida;
    }
    
    public int getId(){
        return id;
    }
    
    public void setId (int id) {
        this.id = id;
    }
}
