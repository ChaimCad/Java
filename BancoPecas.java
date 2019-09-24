import java.sql.*;
import javax.swing.*;
import java.util.*;

class BancoPecas extends JFrame
{
    private String bdid, bdnome, bdespec, bdgarantia, fsql, url, usuario, senha, drive;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstmt;
    private Statement stmt;

    public BancoPecas()
    {
        bdid = "";
        bdnome = "";
        bdespec = "";
        bdgarantia = "";
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
    public String getEspec()
    {
        return this.bdespec;
    }
    public String getGarantia()
    {
        return this.bdgarantia;
    }

    public void setId(String id)
    {
        this.bdid = id;
    }
    public void setNome(String nome)
    {
        this.bdnome = nome;
    }
    public void setEspec(String espec)
    {
        this.bdespec = espec;
    }
    public void setGarantia(String garantia)
    {
        this.bdgarantia = garantia;
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
        fsql = "select * from pecas order by id";
        try
        {
            stmt = con.createStatement();
            rs = stmt.executeQuery(fsql);
            while(rs.next())
            {
                bdid = rs.getString("id");
                bdnome = rs.getString("nome");
                bdespec = rs.getString("espec");
                bdgarantia = rs.getString("garantia");
                
                dados.add(bdid);
                dados.add(bdnome);
                dados.add(bdespec);
                dados.add(bdgarantia);
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
        fsql = "insert into pecas (id,nome,espec,garantia) values (?,?,?,?)";
        try
        {
            pstmt = con.prepareStatement(fsql);
            
            pstmt.setInt(1,Integer.parseInt(bdid));
            pstmt.setString(2,bdnome);
            pstmt.setString(3,bdespec);
            pstmt.setString(4,bdgarantia);
            
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
        fsql = "delete from pecas where id=?";
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
        fsql = "update pecas set nome=?, espec=?, garantia=? where id=?";
        try
        {
            pstmt = con.prepareStatement(fsql);
            
            pstmt.setString(1,bdnome);
            pstmt.setString(2,bdespec);
            pstmt.setString(3,bdgarantia);
            pstmt.setInt(4,Integer.parseInt(bdid));
            
            pstmt.execute();
            
            pstmt.close();
        }
        catch(Exception erro)
        {
            JOptionPane.showMessageDialog(null,"Erro na alteracao: " + erro);
        }
    }
}
