import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Menu extends JFrame implements ActionListener, MenuListener
{
    private JMenuBar menu;
    private JMenu menuArq, menuTransf, menuRel, menuSobre, menuSair;
    private JMenuItem itemTxt, itemSqlUsu, itemSqlPecas, itemSair, itemTransfTxt, itemTransfSql, itemUsu, itemPecas;
    private ImageIcon imagem;
    private JLabel lblImg;
    
    public Menu()
    {
        super("Peças Porim");
        
        setLayout(null);
        this.getContentPane().setBackground(new Color(220,80,220));
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(725, 420);
        setLocation((screen.width - 725) / 2, (screen.height - 420) / 2);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    	
        menu = new JMenuBar();
        
    	menuArq = new JMenu("Arquivo");
    	menuTransf = new JMenu("Transfere");
    	menuRel = new JMenu("Relatorio");
    	menuSobre = new JMenu("Sobre");
    	menuSair = new JMenu("Sair");
    	
    	itemTxt = new JMenuItem("Texto");
    	itemSqlUsu = new JMenuItem("SQL - usuario");
    	itemSqlPecas = new JMenuItem("SQL - pecas");
    	itemSair = new JMenuItem("Sair");
    	itemTransfTxt = new JMenuItem("Txt -> SQL");
    	itemTransfSql = new JMenuItem("SQL -> Txt");
    	itemUsu = new JMenuItem("Por usuario");
    	itemPecas = new JMenuItem("Por pecas");
        
        imagem = new ImageIcon("gabinete.jpg");
        
        lblImg = new JLabel(imagem);
        
    	itemTxt.addActionListener(this);
    	itemSqlUsu.addActionListener(this);
    	itemSqlPecas.addActionListener(this);
    	itemSair.addActionListener(this);
    	itemTransfTxt.addActionListener(this);
    	itemTransfSql.addActionListener(this);
    	itemUsu.addActionListener(this);
    	itemPecas.addActionListener(this);
        
    	menuArq.add(itemTxt);
    	menuArq.add(itemSqlUsu); 
    	menuArq.add(itemSqlPecas); 
    	menuArq.addSeparator();	  
    	menuArq.add(itemSair);
    	
    	menuTransf.add(itemTransfTxt);
    	menuTransf.add(itemTransfSql);
    	
    	menuRel.add(itemUsu);
    	menuRel.add(itemPecas);

    	menuSobre.addMenuListener(this);
    	menuSair.addMenuListener(this);
        
    	menu.add(menuArq);
    	menu.add(menuTransf);
    	menu.add(menuRel);
    	menu.add(menuSobre);
        menu.add(menuSair);

        ImageIcon nova = new ImageIcon(imagem.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
        lblImg.setIcon(nova);
        
        lblImg.setBounds(10,10,700,300);
        
        setJMenuBar(menu);

        add(lblImg);

    	setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == itemTxt)
        {
    	    JOptionPane.showMessageDialog(null,"Cadastro arquivo texto","Cadastro",-1);
        }
        else if(ae.getSource() == itemSqlUsu)
        {
            setVisible(false);
            new SqlUsu();
        }
        else if(ae.getSource() == itemSqlPecas)
        {
            setVisible(false);
            new SqlPecas();
        }
        else if(ae.getSource() == itemSair)
        {
            System.exit(0);
        }
        else if(ae.getSource() == itemTransfSql)
        {

        }
        else if(ae.getSource() == itemTransfTxt)
        {

        }
        else if(ae.getSource() == itemUsu)
        {
			new RelatorioUsuario();
		}
		else if(ae.getSource() == itemPecas)
		{
			new RelatorioPecas();
		}
    }
    
	public void menuSelected(MenuEvent me)
	{   
		if(me.getSource() == menuSobre)
		{
            ImageIcon foto = new ImageIcon("user1.jpg");
            ImageIcon nova = new ImageIcon(foto.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
            JOptionPane.showMessageDialog(null,"Trabalho 2019\nAndre Moco Chaim numero 04\nBruno Danzi Melo numero 05\nCTI - Bauru","Sobre",-1,nova);
		}
		else if(me.getSource() == menuSair)
		{
            if(JOptionPane.showConfirmDialog(this,"Confirma Sair?","Confirma Saida",2) == 0)
            {
                System.exit(0);
            }
		}
    }
    
    public void menuDeselected(MenuEvent me) { }
    
    public void menuCanceled(MenuEvent me) { }
    
    public static void main(String args[])
    {
        new Menu();
    }
}
