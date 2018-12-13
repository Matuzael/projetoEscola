/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.DAO;


import Classes.Professor;
import Conexao.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author 20161174010020
 */
public class ProfessorDAO {
    
       public void create(Professor p){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO professor(nome,matricula,cpf,disciplina,telefone,remuneracao) VALUES(?,?,?,?,?,?)");
            stmt.setString(1,p.getNome());
            stmt.setInt(2, p.getMatricula());
            stmt.setDouble(3, p.getCpf());
            stmt.setString(4, p.getDisciplina());
            stmt.setInt(5, p.getTelefone());
            stmt.setDouble(6, p.getRemuneracao());
            
            stmt.executeUpdate();
            
            
            
            
            JOptionPane.showMessageDialog(null,"Salvo com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null," Erro  ao salvar AQUIIII!" + ex);
        }
            finally{
                ConnectionFactory.closeConnection(con, stmt);
        }  
    }
    
            
    public List<Professor> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Professor> professores = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("Select * FROM professor");
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Professor prof = new Professor();
                
                prof.setNome(rs.getString("nome"));
                prof.setMatricula(rs.getInt("matricula"));
                prof.setCpf(rs.getInt("cpf"));
                prof.setDisciplina(rs.getString("disciplina"));
                prof.setTelefone(rs.getInt("Telefone"));
                prof.setRemuneracao(rs.getDouble("remuneracao"));
                professores.add(prof);
                        
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            
            ConnectionFactory.closeConnection(con, stmt, rs);
                    }
        
        return professores;
        
        
        
        
    }
    
    
      public void update(Professor p){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE professor SET nome = ?, cpf = ?, disciplina = ?, telefone =?, remuneracao =? WHERE matricula = ?");
            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getCpf());
            stmt.setString(3, p.getDisciplina());
            stmt.setInt(4,p.getTelefone());
            stmt.setDouble(5, p.getRemuneracao());
            stmt.setInt(6,p.getMatricula());

            stmt.executeUpdate();
            
            
            
            
            JOptionPane.showMessageDialog(null,"Atualizado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null," Erro  ao atualizar! " + ex);
        }
            finally{
                ConnectionFactory.closeConnection(con, stmt);
        }  
    }
    
     public void delete(Professor p){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM professor WHERE matricula = ?");
         
            stmt.setInt(1, p.getMatricula());
            stmt.executeUpdate();
            
            
            
            
            JOptionPane.showMessageDialog(null,"Excluído com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null," Erro  ao Excluir! " + ex);
        }
            finally{
                ConnectionFactory.closeConnection(con, stmt);
        }  
    }
    
    
}
