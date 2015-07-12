package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Atividade;


/**
 *
 * @author Amanda
 */
public class AtividadeDao {
                                                                                                                                                                                                            
    static Connection conexao;
	
    public AtividadeDao(Connection conexaoBD) {
        this.conexao = conexaoBD;    
    }

    public boolean validarDados(Atividade atividade) throws SQLException {	
        try {
			PreparedStatement pstmt = conexao.prepareStatement("select * from atividade where nome_atividade ='"+atividade.getNome_atividade()+"' and grupoEtario_atividade ='"+atividade.getGrupoEtario_atividade()+"'");
                        ResultSet rs = pstmt.executeQuery();
			String nome = null;
                        int grupo_etario;
			
			while(rs.next()) {
                            nome = rs.getString("nome_atividade");
                            grupo_etario = rs.getInt("grupoEtario_atividade");
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
    
     
    public int buscarIdAtividade(Atividade atividade) throws SQLException {	
       int id = 0;
        try {
		PreparedStatement pstmt = conexao.prepareStatement("select pk_atividade from atividade where nome_atividade ='"+atividade.getNome_atividade()+"'");
			
		ResultSet rs = pstmt.executeQuery();
                        
                while(rs.next())  
                {  
                   id = rs.getInt("pk_atividade");   
                }

		} catch(SQLException e) {
            System.out.println("Erro = "+e.getMessage());
      
		}		
        return id;
    }
    

    public String inserirAtividade(Atividade atividade) throws SQLException {
         try{
            Statement statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             
            String sqlCadastraUsuario = "INSERT INTO ATIVIDADE (nome_atividade, descricao_atividade,grupoEtario_atividade,qtdMoedas_atividade) VALUES ('"+atividade.getNome_atividade()+"','"+atividade.getDescricao_atividade()+"','"+atividade.getGrupoEtario_atividade()+"','"+atividade.getQtdMoedas_atividade()+"');";
            statement.executeUpdate(sqlCadastraUsuario);
            
           
            atividade.setPk_atividade(buscarIdAtividade(atividade));

            return "Cadastro de Atividade Realizado com Sucesso!";
        }
        catch(SQLException e){
            return e.toString();
        }     
    }
    
}
