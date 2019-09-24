import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.extensions.*;
import net.sf.jasperreports.view.JasperViewer;
import java.sql.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.lang.*;
import java.io.*;

public class RelatorioUsuario
{
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    
    JasperReport jasperR;
	JasperPrint jasperP;
	JRResultSetDataSource jrRS;
	Map parametros;
        
    public RelatorioUsuario()
    {
        try
        {
            con = null;
            String url = "jdbc:postgresql://localhost:5433/hardware";
            String usuario = "postgres";
            String senha = "tijolo123";
            String driver = "org.postgresql.Driver";
       
            Class.forName(driver);
            con = DriverManager.getConnection(url,usuario,senha);
            stmt = con.createStatement();
                        
            rs = stmt.executeQuery("select * from usuario order by id");
            jrRS = new JRResultSetDataSource(rs);
            parametros = new HashMap();
            
            JasperPrint impressao = JasperFillManager.fillReport("C:\\Users\\ra1757066\\Desktop\\Java\\report_User.jasper",parametros,jrRS);
            JasperViewer viewer = new JasperViewer(impressao,false);
            viewer.show();
        
            con.close();
            stmt.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Erro relatorio: " + e.getMessage());
        }
    }
    
    public static void main (String tx[])
    {
		new RelatorioUsuario();
    }
}