package projetorotinakids;

import Controller.LoginMock;
import Dao.ConexaoMySQLDao;
import Dao.LoginDao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import model.Login;
import view.LoginVo;
import view.ResponsavelVo;

/**
 *
 * @author Amanda
 */
public class Main_ProjetoRotinaKids {

    public static void main(String[] args) throws SQLException {
        
        
        ConexaoMySQLDao conexaoMySQLDao = new  ConexaoMySQLDao();
        Scanner entrada_opcaoMenuPrincial = new Scanner(System.in);
        int opcaoMenuPrincipal = 0;
        
       
        do{
            
            System.out.println(" ________________________________________");
            System.out.println("|                                        |");
            System.out.println("|           ROTINA KIDS - LOGIN          |");
            System.out.println("|________________________________________|");
            System.out.println("|  Digite uma das opcoes:                |");
            System.out.println("| 1- Fazer Login                         |");
            System.out.println("| 2- Cadastrar Responsavel               |");
            System.out.println("| 3- Sair                                |");
            System.out.println("|________________________________________|");

            opcaoMenuPrincipal = entrada_opcaoMenuPrincial.nextInt();

            if(opcaoMenuPrincipal == 1){
                LoginVo loginVo = new LoginVo(conexaoMySQLDao.getConexao());
                loginVo.telaLogin();

            }
            else if(opcaoMenuPrincipal == 2){
                ResponsavelVo ResponsavelVo = new ResponsavelVo(conexaoMySQLDao.getConexao());
                ResponsavelVo.telaCadastroResponsavel();
            }
            else if(opcaoMenuPrincipal == 3){
            }
            else{
                System.out.println("Digite uma opcao valida!");
            }
        }while(opcaoMenuPrincipal != 3);
    }  
}
