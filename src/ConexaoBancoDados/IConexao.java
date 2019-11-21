package ConexaoBancoDados;

import java.sql.Connection;

/**
 *
 * @author GrupoATPS Java
 */
public interface IConexao {
    
    /**
     *
     * @return
     */
    public abstract Connection conectar();
    
    /**
     * @see 
     */
    public abstract void desconectar();
}
