import java.sql.*;
import javax.swing.*;
import java.util.*;

class BancoUsuario extends JFrame
{
    private String bdid, bdnome, bdidade, bdemail, bdsenha, bdfkPeca, fsql, url, usuario, senha, drive;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstmt;
    private Statement stmt;

    public BancoUsuario()
    {
        bdid = "";
        bdnome = "";
        bdidade = "";
        bdemail = "";
        bdsenha = "";
        bdfkPeca = "";
        fsql = "";
        con = null;
        usuario = "postgres";
        senha = "postgres";
        drive = "org.postgresql.Driver";
        url = "jdbc:postgresql://localhost:5432/hardware";
    }
    
    public String getId()
    {
        return this.bdid;
    }
    public String getNome()
    {
        return this.bdnome;
    }
    public String getIdade()
    {
        return this.bdidade;
    }
    public String getEmail()
    {
        return this.bdemail;
    }
    public String getSenha()
    {
        return this.bdsenha;
    }
    public String getIdPeca()
    {
    	return this.bdfkPeca;
    }

    public void setId(String id)
    {
        this.bdid = id;
    }
    public void setNome(String nome)
    {
        this.bdnome = nome;
    }
    public void setIdade(String idade)
    {
        this.bdidade = idade;
    }
    public void setEmail(String email)
    {
        this.bdemail = email;
    }
    public void setSenha(String senha)
    {
        this.bdsenha = senha;
    }
    public void setIdPeca(String idPeca)
    {
    	this.bdfkPeca = idPeca;
    }


    public void connect()
    {
    	try
        {
        	if(con == null)
    		{
	            Class.forName(drive);
	            con = DriverManager.getConnection(url,usuario,senha);
    		}
        }
        catch(Exception erro)
        {
            JOptionPane.showMessageDialog(null,"Erro na conexao: " + erro);
        }
    }
    public void disconnect()
    {
        try
        {
            con.close();
            con = null;
        }
        catch(Exception erro)
        {
            JOptionPane.showMessageDialog(null,"Erro na desconexao: " + erro);
        }
    }

	public ArrayList pegadados()
    {
        ArrayList dados;
        dados = new ArrayList();
        fsql = "select * from usuario order by id";
        try
        {
            stmt = con.createStatement();
            rs = stmt.executeQuery(fsql);
            while(rs.next())
            {
                bdid = rs.getString("id");
                bdnome = rs.getString("nome");
                bdidade = rs.getString("idade");
                bdemail = rs.getString("email");
                bdsenha = rs.getString("senha");
                bdfkPeca = rs.getString("id_peca");
                
                dados.add(bdid);
                dados.add(bdnome);
                dados.add(bdidade);
                dados.add(bdemail);
                dados.add(bdsenha);
                dados.add(bdfkPeca);
            }
            
            stmt.close();
        }
        catch(Exception erro)
        {
            JOptionPane.showMessageDialog(null,"Erro na leitura:" + erro);
        }
        return dados;
    }

    public void inserir()
    {
        fsql = "insert into usuario (id,nome,idade,email,senha,id_peca) values (?,?,?,?,?,?)";
        try
        {
            pstmt = con.prepareStatement(fsql);
            
            pstmt.setInt(1,Integer.parseInt(bdid));
            pstmt.setString(2,bdnome);
            pstmt.setInt(3,Integer.parseInt(bdidade));
            pstmt.setString(4,bdemail);
            pstmt.setString(5,bdsenha);
            pstmt.setInt(6,Integer.parseInt(bdfkPeca));
            
            pstmt.execute();
            
            pstmt.close();
        }
        catch(Exception erro)
        {
            JOptionPane.showMessageDialog(null,"Erro no cadastro: " + erro);
        }
    }
    public void excluir()
    {
        fsql = "delete from usuario where id=?";
        try
        {
            pstmt = con.prepareStatement(fsql);
            pstmt.setInt(1,Integer.parseInt(bdid));
            
            pstmt.execute();
            
            pstmt.close();
        }
        catch(Exception erro)
        {
            JOptionPane.showMessageDialog(null,"Erro na exclusao: " + erro);
        }
    }
    public void alterar()
    {
        fsql = "update usuario set nome=?, idade=?, email=?, senha=?, id_peca=? where id=?";
        try
        {
            pstmt = con.prepareStatement(fsql);
            
            pstmt.setString(1,bdnome);
            pstmt.setInt(2,Integer.parseInt(bdidade));
            pstmt.setString(3,bdemail);
            pstmt.setString(4,bdsenha);
            pstmt.setInt(5,Integer.parseInt(bdfkPeca));
            pstmt.setInt(6,Integer.parseInt(bdid));
            
            pstmt.execute();
            
            pstmt.close();
        }
        catch(Exception erro)
        {
            JOptionPane.showMessageDialog(null,"Erro na alteracao: " + erro);
        }
    }
}
