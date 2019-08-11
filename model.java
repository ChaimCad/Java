import java.sql.*;
import javax.swing.*;
import java.util.*;

class model extends JFrame
{
    private int bdid;
    private String bdtipo, bdaltera;
    private String fsql;
    private String url, usuario, senha, drive;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pstmt;
    private Statement stmt;
    
    public Model()
    {
        bdid = 0;
        bdnome = "";
        bdaltera = "";
        fsql = "";
        con = null;
        usuario = "postgres";
        senha = "sqladmin";
        drive = "org.postgresql.Driver";
        url = "jdbc:postgresql://localhost:5432/banco73b2017";
        url = "jdbc:postgresql://200.145.153.163:5432/banco73b2017";
    }
    
    public void setId(String c)
    {
        bdid = Integer.parseInt(c);
    }
    public void setTipo(String n)
    {
        bdtipo = n;
    }
    public void setAltera(String a)
    {
        bdaltera = a;
    }
    public int getId()
    {
        return bdid;
    }
    public String getTipo()
    {
        return bdtipo;
    }
    public String getAltera()
    {
        return bdaltera;
    }
    
    public void cadastro()
    {
        fsql = "insert into usuario(id,nome,idade,email,senha) values (DEFAULT,?)";
        try
        {
            pstmt = con.prepareStatement(fsql);
            pstmt.setString(1,bdtipo);
            if(pstmt.execute() == null)
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
        fsql = "delete from usuario where id=?";
        try
        {
            pstmt = con.prepareStatement(fsql);
            pstmt.setInt(1,bdid);
            if(pstmt.execute() == null)
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
        fsql = "update usuario set " + bdaltera + "=? where id=?";
        try
        {
            pstmt = con.prepareStatement(fsql);
            pstmt.setString(1,bdnome);
            pstmt.setInt(2,bdid);
            if(pstmt.execute() == null)
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
    public void connect()
    {
        try
        {
            Class.forName(drive);
            con = DriverManager.getConnection(url,usuario,senha);
            //JOptionPane.showMessageDialog(null,"ok");
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
    public ArrayList pegadados()
    {
        ArrayList dados;
        dados = new ArrayList();
        fsql = "select * from tabela2";
        try
        {
            stmt = con.createStatement();
            rs = stmt.executeQuery(fsql);
            while(rs.next())
            {
                bdid = rs.getInt("id");
                bdtipo = rs.getString("tipo");
                dados.add(bdid);
                dados.add(bdnome);
            }
            stmt.close();
        }
        catch(Exception erro)
        {
            JOptionPane.showMessageDialog(null,"Erro na leitura:" + erro);
        }
        return dados;
    }
    public boolean procura(String id)
    {
        fsql = "Select * from tabela2 where id=?";
        try
        {
            pstmt = con.prepareStatement(fsql);
            int idd = Integer.parseInt(id);
            pstmt.setInt(1, idd);
            rs = pstmt.executeQuery();
            if(rs.next())
            {
                return true;
            }
            pstmt.close();
        }
        catch(Exception erroi)
        {
            JOptionPane.showMessageDialog(null,"Erro procura:" + erroi);
        }
        return false;
    }
    public String pegatipo(String id)
    {
        fsql = "Select * from tabela2 where id=?";
        try
        {
            pstmt = con.prepareStatement(fsql);
            int idd = Integer.parseInt(id);
            pstmt.setInt(1, idd);
            rs = pstmt.executeQuery();
            if(rs.next())
            {
                return rs.getString("tipo");
            }
            pstmt.close();
        }
        catch(Exception erroi)
        {
            JOptionPane.showMessageDialog(null,"Erro procura:" + erroi);
        }
        return "vazio";
    }
    public String retorna()
    {
        String volta = "";
        fsql = "select * from tabela2";
        try
        {
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(fsql);
            if(rs.last())
            {
                volta = rs.getString("id");
                //JOptionPane.showMessageDialog(null,"ultimo"+volta);
                return volta;
            }
        }
        catch(Exception erroi)
        {
            JOptionPane.showMessageDialog(null,"Erro leitura :" + erroi);
        }
        return volta;
    }
}