import java.sql.*;
import javax.swing.*;
import java.util.*;

class BancoPecas extends JFrame
{
    private int bdid;
    private String bdtipo, bdnome, bdaltera, fsql, url, usuario, senha, drive;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstmt;
    private Statement stmt;

    public BancoPecas()
    {
        bdid = 0;
        bdtipo = "";
        bdnome = "";
        bdaltera = "";
        fsql = "";
        con = null;
        usuario = "postgres";
        senha = "tijolo123";
        drive = "org.postgresql.Driver";
        url = "jdbc:postgresql://localhost:5433/hardware";
    }
    
    public int getId()
    {
        return this.bdid;
    }
    public String getTipo()
    {
        return this.bdtipo;
    }
    public String getNome()
    {
        return this.bdnome;
    }
    public String getAltera()
    {
        return this.bdaltera;
    }

    public void setId(String id)
    {
        this.bdid = Integer.parseInt(id);
    }
    public void setTipo(String tipo)
    {
        this.bdtipo = tipo;
    }
    public void setNome(String nome)
    {
        this.bdnome = nome;
    }
    public void setAltera(String altera)
    {
        this.bdaltera = altera;
    }


    public void connect()
    {
        try
        {
            Class.forName(drive);
            con = DriverManager.getConnection(url,usuario,senha);
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
        }
        catch(Exception erro)
        {
            JOptionPane.showMessageDialog(null,"Erro na desconexao: " + erro);
        }
    }
    

    public void inserir()
    {
        fsql = "insert into pecas(id,nome,espec,garantia) values (DEFAULT,?)";
        try
        {
            pstmt = con.prepareStatement(fsql);
            pstmt.setString(1,bdtipo);
            if(!pstmt.execute())
            {
                JOptionPane.showMessageDialog(null,"Erro no cadastro!!");
            }
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
            pstmt.setInt(1,bdid);
            if(!pstmt.execute())
            {
                JOptionPane.showMessageDialog(null,"Erro na exclusao!!");
            }
            pstmt.close();
        }
        catch(Exception erro)
        {
            JOptionPane.showMessageDialog(null,"Erro na exclusao: " + erro);
        }
    }
    public void alterar()
    {
        fsql = "update pecas set ?=? where id=?";
        try
        {
            pstmt = con.prepareStatement(fsql);
            pstmt.setString(1,bdaltera);
            pstmt.setString(2,bdnome);
            pstmt.setInt(3,bdid);
            if(!pstmt.execute())
            {
                JOptionPane.showMessageDialog(null,"Erro na alteracao!!");
            }
            pstmt.close();
        }
        catch(Exception erro)
        {
            JOptionPane.showMessageDialog(null,"Erro na alteracao: " + erro);
        }
    }
}