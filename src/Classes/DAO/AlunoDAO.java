/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.DAO;

import Classes.Aluno;
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
public class AlunoDAO {
    
        public void create(Aluno a){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO aluno(nome,matricula,cpf,endereco,telefone,turma) VALUES(?,?,?,?,?,?)");
            stmt.setString(1,a.getNome());
            stmt.setInt(2, a.getMatricula());
            stmt.setLong(3, a.getCpf());
            stmt.setString(4, a.getEndereco());
            stmt.setInt(5,a.getTelefone());
            stmt.setString(6, a.getTurma());
            
            
            stmt.executeUpdate();
            
            
            
            
            JOptionPane.showMessageDialog(null,"Salvo com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null," Erro  ao salvar AQUIIII!" + ex);
        }
            finally{
                ConnectionFactory.closeConnection(con, stmt);
        }  
    }
    
            
    public List<Aluno> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Aluno> alunos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("Select * FROM aluno");
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Aluno aluno = new Aluno();
                
                aluno.setNome(rs.getString("nome"));
                aluno.setMatricula(rs.getInt("matricula"));
                aluno.setCpf(rs.getLong("cpf"));
                aluno.setEndereco(rs.getString("endereco"));
                aluno.setTelefone(rs.getInt("telefone"));
                aluno.setTurma(rs.getString("turma"));
                alunos.add(aluno);
                        
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            
            ConnectionFactory.closeConnection(con, stmt, rs);
                    }
        
        return alunos;
        
        
        
        
    }
    
    
      public void update(Aluno a){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE aluno SET nome = ?, cpf = ?, endereco = ?, telefone = ?, turma =? WHERE matricula = ?");
            stmt.setString(1,a.getNome());
            stmt.setLong(2, a.getCpf());
            stmt.setString(3, a.getEndereco());
            stmt.setInt(4,a.getTelefone());
            stmt.setString(5, a.getTurma());
            stmt.setInt(6, a.getMatricula());

            stmt.executeUpdate();
            
            
            
            
            JOptionPane.showMessageDialog(null,"Atualizado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null," Erro  ao atualizar! " + ex);
        }
            finally{
                ConnectionFactory.closeConnection(con, stmt);
        }  
    }
    
     public void delete(Aluno a){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM aluno WHERE matricula = ?");
         
            stmt.setInt(1, a.getMatricula());
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
