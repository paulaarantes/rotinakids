package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Filho;
import model.Responsavel;

/**
 *
 * @author Amanda
 */
public class FilhoDao {
                                                                                                                                                                                                    
    static Connection conexao;
    Responsavel responsavel;
	
    public FilhoDao(Connection conexaoBD,Responsavel responsavel) {
        this.conexao = conexaoBD;
        this.responsavel = responsavel;         
    }

    public boolean validarDados(Filho filho, Responsavel responsavel) throws SQLException {	
        try {
			PreparedStatement pstmt = conexao.prepareStatement("select * from usuario where cpf ='"+filho.getCpf()+"'");
			ResultSet rs = pstmt.executeQuery();
			String nome = null;
			
			while(rs.next()) {
                            nome = rs.getString("nome_usuario");
			}
			
			if(nome != null) {
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
    
   
    public int buscarId(Filho filho) throws SQLException {	
       int id = 0;
        try {
		PreparedStatement pstmt = conexao.prepareStatement("select pk_usuario from usuario where cpf ='"+filho.getCpf()+"'");
			
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


    public String inserirFilho(Filho filho) throws SQLException {
         try{
            Statement statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             
            String sqlCadastraUsuario = "INSERT INTO USUARIO (nome_usuario, cpf) VALUES ('"+filho.getNome_usuario()+"','"+filho.getCpf()+"');";
            statement.executeUpdate(sqlCadastraUsuario);
            
            
            filho.setPk_filho(buscarId(filho)); 
            String sqlCadastraFilho = "INSERT INTO FILHO (pk_filho,cod_responsavel,idade_filho) VALUES ('"+filho.getPk_filho()+"','"+filho.getCod_responsavel()+"','"+filho.getIdade_filho()+"');";
            statement.executeUpdate(sqlCadastraFilho);

            return "Cadastro de Filho Realizado com Sucesso!";
        }
        catch(SQLException e){
            return e.toString();
        }     
    }

}
