package ConexaoBancoDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author GrupoDarkPoower Java
 */
public class ConexaoMySQL implements IConexao{
    
    private final boolean status = false;
    private String mensagem = "";   //variavel que vai informar o status da conexao
    
    Connection con;
    private Statement statement;
    private ResultSet resultSet;

    @Override
    public Connection conectar() {
        try {            
            Class.forName("com.mysql.jdbc.Driver");
            //con = DriverManager.getConnection("jdbc:mysql://localhost/DarkPOOwer?user=adminlinear&password=@2013linear");
            String url = "jdbc:mysql://localhost/DarkPOOwer";
 
            String username = "darkpoower";        //nome de um usuário de seu BD      
 
            String password = "dk123456";      //sua senha de acesso
 
            con = DriverManager.getConnection(url, username, password);           
            return con;            
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null,"Erro ao tentar conectar no banco\n"
                                                 + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            
            JOptionPane.showMessageDialog(null,"Erro ao tentar conectar no banco\n"
                                                + "Veifique o serviço do MYSQL" + ex.getMessage());
        }
     
        throw new UnsupportedOperationException("Nao suportado");
    }
        
    

    @Override
    public void desconectar() {
        try {
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Nao foi possivel desconectar" + ex.getMessage());
        }
    }
    
     /**
     * Executa consultas SQL
     * @param pSQL
     * @return int
     */
    public boolean executarSQL(String pSQL){
        try {
            //createStatement de con para criar o Statement
            this.setStatement(getCon().createStatement());

            // Definido o Statement, executamos a query no banco de dados
            this.setResultSet(getStatement().executeQuery(pSQL));
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro - " + ex.getMessage() + JOptionPane.ERROR);
            JOptionPane.showMessageDialog(null, "Erro - " + ex.getClass() + JOptionPane.ERROR);
            return false;
        }
        return true;
    }
    
    public boolean executarUpdateDeleteSQL(String pSQL){
        try {
            
            //createStatement de con para criar o Statement
            this.setStatement(getCon().createStatement());

            // Definido o Statement, executamos a query no banco de dados
            getStatement().executeUpdate(pSQL);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro - " + ex.getMessage() + JOptionPane.ERROR);
            JOptionPane.showMessageDialog(null, "Erro - " + ex.getClass() + JOptionPane.ERROR);
            return false;
        }
        return true;
    }
    
    /**
     * Executa insert SQL
     * @param pSQL
     * @return boolean
     */
    public int insertSQL(String pSQL){
        int status = 0;
        try {
            //createStatement de con para criar o Statement
            this.setStatement(getCon().createStatement());

            // Definido o Statement, executamos a query no banco de dados
            this.getStatement().executeUpdate(pSQL);
            
            //consulta o ultimo id inserido
            this.setResultSet(this.getStatement().executeQuery("SELECT last_insert_id();"));
            
            //recupera o ultimo id inserido
            while(this.resultSet.next()){
                status = this.resultSet.getInt(1);
            }
            
            //retorna o ultimo id inserido
            return status;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro - " + ex.getMessage() + JOptionPane.ERROR);
            JOptionPane.showMessageDialog(null, "Erro - " + ex.getClass() + JOptionPane.ERROR);
            return status;
        }
    }

    /**
     * encerra a conexão corrente
     * @return boolean
     */
    public boolean fecharConexao(){
       try {
           if((this.getResultSet() != null) && (this.statement != null)){
               this.getResultSet().close();
               this.statement.close();
           }
           this.getCon().close();
           return true;
       } catch(SQLException e) {
           JOptionPane.showMessageDialog(null, e.getMessage());
       }
       return false;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return this.status;
    }

    /**
     * @return the mensagem
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * @return the statement
     */
    public Statement getStatement() {
        return statement;
    }

    /**
     * @return the resultSet
     */
    public ResultSet getResultSet() {
        return resultSet;
    }

    /**
     * @param mensagem the mensagem to set
     */
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    /**
     * @return the con
     */
    public Connection getCon() {
        return con;
    }

    /**
     * @param con the con to set
     */
    public void setCon(Connection con) {
        this.con = con;
    }

    /**
     * @param statement the statement to set
     */
    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    /**
     * @param resultSet the resultSet to set
     */
    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }
}
