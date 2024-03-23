
package DAO;


import DTO.FuncionarioDTO;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author Leonardo
 */
public class FuncionarioDAO {
    
    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<FuncionarioDTO> lista = new ArrayList<>();
   
    
   
    public void cadastrarFuncionario(FuncionarioDTO objfuncionariodto){
    
        String sql = "insert into funcionario (nome_funcionario, endereco_funcionario, cargo_funcionario) values (?,?,?)";
        
        conn = new ConexaoDAO().conectaBD();
        
        try {
            
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objfuncionariodto.getNome_funcionario());
            pstm.setString(2, objfuncionariodto.getEndereco_funcionario());
            pstm.setString(3, objfuncionariodto.getCargo_funcionario());
       
            
            pstm.execute();
            pstm.close();
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO: Cadastrar" + erro);
        }
    }
    
    public ArrayList<FuncionarioDTO> PesquisarFuncionario(){
    
    String sql = "select * from funcionario";
    conn = new ConexaoDAO().conectaBD();
    
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while(rs.next()){
            
                FuncionarioDTO objfuncionarioDTO = new FuncionarioDTO();
                objfuncionarioDTO.setId_funcionario(rs.getInt("id_funcionario"));
                objfuncionarioDTO.setNome_funcionario(rs.getString("nome_funcionario"));
                objfuncionarioDTO.setEndereco_funcionario(rs.getString("endereco_funcionario"));    
                objfuncionarioDTO.setCargo_funcionario(rs.getString("cargo_funcionario"));    
                
                lista.add(objfuncionarioDTO);
            }
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO Pesquisar: " + erro);
        }
        return lista;
    }
    
    public void alterarFuncionario(FuncionarioDTO objfuncionariodto){
        String sql = "update funcionario set nome_funcionario = ?, endereco_funcionario = ?, cargo_funcionario = ? where id_funcionario = ?";
    
        conn = new ConexaoDAO().conectaBD();
        
        try {
            
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objfuncionariodto.getNome_funcionario());
            pstm.setString(2, objfuncionariodto.getEndereco_funcionario());
            pstm.setString(3, objfuncionariodto.getCargo_funcionario());
            pstm.setInt(4, objfuncionariodto.getId_funcionario());
            
            pstm.execute();
            pstm.close();
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO: Alterar" + erro);
        }
    }
    
    public void excluirFuncionario(FuncionarioDTO objfuncionariodto){
    
        String sql = "delete from funcionario where id_funcionario = ?";
       
        conn = new ConexaoDAO().conectaBD();
        
        try {
            
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, objfuncionariodto.getId_funcionario());
            
            pstm.execute();
            pstm.close();
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO: Excluir" + erro);
        }
        
    }
    
    public ResultSet listarCargo(){
    
    conn = new ConexaoDAO().conectaBD();
    String sql = "SELECT * FROM cargo ORDEM BY descricao-cargo";
        
        try {
            
            pstm = conn.prepareStatement(sql);
            return pstm.executeQuery();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
