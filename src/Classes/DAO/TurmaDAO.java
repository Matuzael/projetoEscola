/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.DAO;
import Classes.Turma;
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
public class TurmaDAO {
    
        public void create(Turma t){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO turma(nome,idTurma,sala,turno) VALUES(?,?,?,?)");
            stmt.setString(1,t.getNome());
            stmt.setInt(2, t.getIdTurma());
            stmt.setString(3,t.getSala());
            stmt.setString(4, t.getTurno());
                       
            
            stmt.executeUpdate();
            
            
            
            
            JOptionPane.showMessageDialog(null,"Salvo com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null," Erro  ao salvar AQUIIII!" + ex);
        }
            finally{
                ConnectionFactory.closeConnection(con, stmt);
        }  
    }
    
            
    public List<Turma> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Turma> turmas = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("Select * FROM turma");
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Turma t = new Turma();
                
                t.setNome(rs.getString("nome"));
                t.setIdTurma(rs.getInt("idTurma"));
                t.setSala(rs.getString("sala"));
                t.setTurno(rs.getString("turno"));
                turmas.add(t);
                        
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            
            ConnectionFactory.closeConnection(con, stmt, rs);
                    }
        
        return turmas;
        
        
        
        
    }
    
    
      public void update(Turma t){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE turma SET nome = ?, sala = ?, turno = ? WHERE idTurma = ?");
            stmt.setString(1,t.getNome());
            stmt.setString(2, t.getSala());
            stmt.setString(3, t.getTurno());
            stmt.setInt(4, t.getIdTurma());
            
            stmt.executeUpdate();
            
            
            
            
            JOptionPane.showMessageDialog(null,"Atualizado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null," Erro  ao atualizar! " + ex);
        }
            finally{
                ConnectionFactory.closeConnection(con, stmt);
        }  
    }
    
     public void delete(Turma t){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM funcionario WHERE idFunc = ?");
         
            stmt.setInt(1, t.getIdTurma());
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
