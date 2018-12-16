/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.DAO;

import Classes.Aula;
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
 * @author matuz
 */
public class AulaDAO {
    
    public void create(Aula a) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("INSERT INTO aula (idAula,professor,disciplina,turma,lcal,hora) VALUES (?,?,?,?,?,?)");
            stmt.setInt(1,a.getIdAula());
            stmt.setString(2,a.getProfessor());
            stmt.setString(3,a.getDisciplina());
            stmt.setString(4,a.getTurma());
            stmt.setString(5,a.getLocal());
            stmt.setString(6,a.getHora());
            
            stmt.executeUpdate();
                  
            
            JOptionPane.showMessageDialog(null,"Salvo com sucesso!");
                        
        } catch(SQLException sqle){
            JOptionPane.showMessageDialog(null,"Erro ao executar o método create" + sqle);
        }
        finally{
            ConnectionFactory.closeConnection(con,stmt);
        }  
    }
    
        public List<Aula> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Aula> aulas = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("Select * FROM aula");
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Aula a = new Aula();
                
                a.setIdAula(rs.getInt("idAula"));
                a.setProfessor(rs.getString("professor"));
                a.setDisciplina(rs.getString("disciplina"));
                a.setTurma(rs.getString("turma"));
                a.setLocal(rs.getString("lcal"));
                a.setHora(rs.getString("hora"));
                aulas.add(a);
                        
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            
            ConnectionFactory.closeConnection(con, stmt, rs);
                    }
        
        return aulas;
        }
        
         public void update(Aula a) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("UPDATE aula SET professor =?, disciplina=? ,turma=?,local=?,hora=? WHERE idAula=?");
            stmt.setString(1,a.getProfessor());
            stmt.setString(2,a.getDisciplina());
            stmt.setString(3, a.getTurma());
            stmt.setString(4,a.getLocal());
            stmt.setString(5,a.getHora());
            stmt.setInt(6,a.getIdAula());
            
            stmt.executeUpdate();
                        
        } catch(SQLException sqle){
            JOptionPane.showMessageDialog(null,"Erro ao executar o método update ");
        }
        finally{
            ConnectionFactory.closeConnection(con,stmt);
        }  
    }
    
    public void delete(Aula a){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM aula WHERE idAula = ?");
         
            stmt.setInt(1, a.getIdAula());
            stmt.executeUpdate();
            
            
            
            
            JOptionPane.showMessageDialog(null,"Excluído com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null," Erro  ao Excluir! " + ex);
        }
            finally{
                ConnectionFactory.closeConnection(con, stmt);
        } 
    }
        
        public List<Aula> readAulasMarcadas(String turma){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Aula> aulas = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("Select * FROM aula WHERE turma=?");
            stmt.setString(1, turma);
            rs = stmt.executeQuery();
            
            while (rs.next()){
                
                if( rs.getString("turma").equals(turma)){
                Aula a = new Aula();
                
                a.setIdAula(rs.getInt("idAula"));
                a.setProfessor(rs.getString("professor"));
                a.setDisciplina(rs.getString("disciplina"));
                a.setTurma(rs.getString("turma"));
                a.setLocal(rs.getString("lcal"));
                a.setHora(rs.getString("hora"));
                aulas.add(a);
                }
                
                        
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            
            ConnectionFactory.closeConnection(con, stmt, rs);
                    }
        
        return aulas;
        }
           
    }