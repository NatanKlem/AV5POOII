package dao;

import factory.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Motoristas;

public class MotoristasDAO {
    
    private Connection connection;
    int id_motoristas;
    String nome_motoristas;
    String nascimento_motoristas;
    String cnh_motoristas;
    
    public MotoristasDAO(){
       
        this.connection = new Conexao().getConnection();
        
    }
    
    public void inserir(Motoristas motoristas){
    
        String sql = "INSERT INTO motoristas (nome_motoristas, nascimento_motoristas, cnh_motoristas) VALUES(?,?,?)";
        
       try{
           PreparedStatement stmt = connection.prepareStatement(sql);
           stmt.setString(1, motoristas.getNome_motoristas());
           stmt.setString(2, motoristas.getNascimento_motoristas());
           stmt.setString(3, motoristas.getCnh_motoristas());
           stmt.execute();
           stmt.close();
            
       }
       catch(SQLException u){
           throw  new RuntimeException(u);
       } 
          
    }
    
    public Motoristas consultar(Motoristas motoristas){
        
        Motoristas consulta = new Motoristas();
        String sql = "select nome_motoristas, nascimento_motoristas, cnh_motoristas from motoristas where id_motoristas = ?";
        try {
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, String.valueOf(motoristas.getId_motoristas()));
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                consulta.setNome_motoristas(rs.getString(1));
                consulta.setNascimento_motoristas(rs.getString(2));
                consulta.setCnh_motoristas(rs.getString(3));
            }
            else{
                consulta = null;
            }
            
            rs.close();
            stmt.close();
            
            
        } catch (SQLException u) {
            
            throw new RuntimeException(u);
            
            
        }
        
         return(consulta);
        
        
    }
    
    public void atualizar (Motoristas motoristas){
    
        PreparedStatement stmt = null; 
        
        try {
            stmt = connection.prepareStatement("update motoristas set nome_motoristas=?, nascimento_motoristas=?, cnh_motoristas=? where id_motoristas=? ");
            stmt.setString(1, motoristas.getNome_motoristas());
            stmt.setString(2, motoristas.getNascimento_motoristas());
            stmt.setString(3, motoristas.getCnh_motoristas());
            stmt.setInt(4, motoristas.getId_motoristas());
            
            stmt.executeUpdate();
            stmt.close(); 
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
 
    
    }
    
        public void deletar (Motoristas motoristas){
    
        PreparedStatement stmt = null; 
        
        try {
            stmt = connection.prepareStatement("delete from motoristas where id_motoristas=? ");
            stmt.setInt(1, motoristas.getId_motoristas());
            
            stmt.close(); 
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
 
    
    }
    
}
