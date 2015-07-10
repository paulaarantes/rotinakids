package Dao;

import static Dao.LoginDao.conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.IResponsavel;
import model.Responsavel;

/**
 *
 * @author Amanda
 */
public class ResponsavelDao implements IResponsavel{

    
    static Connection conexao;
	
    public ResponsavelDao(Connection conexaoBD) {
        this.conexao = conexaoBD;
              
    }

    @Override
    public boolean validarDados(Responsavel responsavel) throws SQLException {	
        try {
			PreparedStatement pstmt = conexao.prepareStatement("select * from responsavel where login ='"+responsavel.getLogin()+"'");
			
			ResultSet rs = pstmt.executeQuery();
			
			String login = null;
			
			while(rs.next()) {
				login = rs.getString("login");
			
			}
			
			if(login != null) {
				return true;
			}
			else {
				return false;
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
    }
    
    
    @Override
    public int buscarId(Responsavel responsavel) throws SQLException {	
       int id = 0;
        try {
		PreparedStatement pstmt = conexao.prepareStatement("select pk_usuario from usuario where cpf ='"+responsavel.getCpf()+"'");
			
		ResultSet rs = pstmt.executeQuery();
                        
                while(rs.next())  
                {  
                   id = rs.getInt("pk_usuario");   

                    System.out.println(id);
                        }

		} catch(SQLException e) {
            System.out.println("Erro = "+e.getMessage());
      
		}		
        return id;
    }

    @Override
    public String inserirResponsavel(Responsavel responsavel) throws SQLException {
         try{
            Statement statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             
            String sqlCadastraUsuario = "INSERT INTO USUARIO (nome_usuario, cpf) VALUES ('"+responsavel.getNome_usuario()+"','"+responsavel.getCpf()+"');";
            System.out.println(sqlCadastraUsuario);
            statement.executeUpdate(sqlCadastraUsuario);
            
            
            responsavel.setPk_responsavel(buscarId(responsavel)); 
            String sqlCadastraResponsavel = "INSERT INTO RESPONSAVEL (pk_responsavel,login, senha) VALUES ('"+responsavel.getPk_responsavel()+"','"+responsavel.getLogin()+"','"+responsavel.getSenha()+"');";
            System.out.println(sqlCadastraResponsavel);
            statement.executeUpdate(sqlCadastraResponsavel);
            

            return "Cadastro Realizado com Sucesso!";
        }
        catch(SQLException e){
            return e.toString();

    }
