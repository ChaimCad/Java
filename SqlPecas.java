import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;

class SqlPecas extends JFrame implements ActionListener, MouseListener, KeyListener
{
    private DefaultTableModel modelo;
    private JTable tabela;
    private JScrollPane grade;
    private JButton btnNovo, btnAlterar, btnExcluir, btnSalvar, btnCancelar, btnSair;
    private JLabel lblPkPeca, lblNomePeca, lblEspecPeca, lblGarantiaPeca;
    private JTextField txtPkPeca, txtNomePeca, txtGarantiaPeca;
    private JTextArea txaEspecPeca;
    
    private String pkPeca, nomePeca, especPeca, garantiaPeca;
    private int linhaltera;

    private BancoPecas banco;

    public SqlPecas()
    {
        super("Arquivo SQL - TABELA PECAS");
        
        setLayout(null);
    	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(102, 102, 255));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(new Dimension(866, 509));
        setLocation((screenSize.width - 866) / 2,(screenSize.height - 509) / 2);
        setResizable(false);

    	modelo = new DefaultTableModel()
    	{
            public boolean isCellEditable(int lin, int col)
            {
                return false;
            }
        };
        
    	tabela = new JTable(modelo);
    	
        grade = new JScrollPane(tabela);
        
        btnNovo = new JButton("Novo");
        btnAlterar = new JButton("Alterar");
        btnExcluir = new JButton("Excluir");
        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");
        btnSair = new JButton("Sair");
        
        lblPkPeca = new JLabel("Pk Peca:");
        lblNomePeca = new JLabel("Nome Peca:");
        lblEspecPeca = new JLabel("Especificacao Peca:");
        lblGarantiaPeca = new JLabel("Garantia Peca:");
                
        txtPkPeca = new JTextField(4);
        txtNomePeca = new JTextField(30);
        txtGarantiaPeca = new JTextField(10);
        
        txaEspecPeca = new JTextArea(5,20);
        
        banco = new BancoPecas();
        
    	modelo.addColumn("PkPeca");
    	modelo.addColumn("Nome");
    	modelo.addColumn("Especificacao");
    	modelo.addColumn("Garantia");
    	
    	tabela.addMouseListener(this);
    	tabela.addKeyListener(this);
    	
        tabela.getTableHeader().setReorderingAllowed(false);
        
    	tabela.getColumnModel().getColumn(0).setMaxWidth(80);
        
        btnNovo.setToolTipText("Incluir dados");
        btnAlterar.setToolTipText("Alterar dados");
        btnExcluir.setToolTipText("Excluir dados");
        btnSalvar.setToolTipText("Salvar dados");
        btnCancelar.setToolTipText("Cancelar operacao");
        btnSair.setToolTipText("Fechar o programa");

        btnNovo.setBounds(690,250,100,30);
        btnAlterar.setBounds(690,290,100,30);
        btnExcluir.setBounds(690,330,100,30);
        btnSalvar.setBounds(690,370,100,30);
        btnCancelar.setBounds(690,410,100,30);
        btnSair.setBounds(36,440,100,30);
        
        lblPkPeca.setBounds(40,252,80,15);
        lblNomePeca.setBounds(40,287,90,15);
        lblEspecPeca.setBounds(40,322,130,15);
        lblGarantiaPeca.setBounds(40,407,90,15);
        
        txtPkPeca.setBounds(100,250,270,20);
        txtNomePeca.setBounds(120,285,250,20);
        txtGarantiaPeca.setBounds(130,405,240,20);
        
        txaEspecPeca.setBounds(170,320,200,70);
        
        grade.setBounds(22,18,807,206);

        btnNovo.addActionListener(this);
        btnAlterar.addActionListener(this);
        btnExcluir.addActionListener(this);
        btnSalvar.addActionListener(this);
        btnCancelar.addActionListener(this);
        btnSair.addActionListener(this);
        
        btnSalvar.setEnabled(false);
        btnCancelar.setEnabled(false);
        
        txtPkPeca.setToolTipText("Pk Peca");
        txtNomePeca.setToolTipText("Nome Peca");
        txtGarantiaPeca.setToolTipText("Garantia da peca");
        
        txaEspecPeca.setToolTipText("Especificacao da peca");
        
        txtPkPeca.setEnabled(false);
        txtNomePeca.setEnabled(false);
        txtGarantiaPeca.setEnabled(false);
        
        txaEspecPeca.setEnabled(false);
    
        grade.setToolTipText("Grade de dados");

        add(grade);

        add(btnNovo);
        add(btnAlterar);
        add(btnExcluir);
        add(btnSalvar);
        add(btnCancelar);
        add(btnSair);
        
        add(lblPkPeca);
        add(lblNomePeca);
        add(lblEspecPeca);
        add(lblGarantiaPeca);
        
        add(txtPkPeca);
        add(txtNomePeca);
        add(txtGarantiaPeca);
        
        add(txaEspecPeca);
        
        linhaltera = -1;
        
        habilitaTudo(false, 1);

        leGradeSql();
        
        setVisible(true);
    }
    
    public void leGradeSql()
    {
        ArrayList vetor = new ArrayList();
        try
        {
            banco.connect();
            vetor = banco.pegadados();
            if(vetor.isEmpty())
            {
                JOptionPane.showMessageDialog(null,"Tabela de pecas vazia!!");
                return;
            }
            else
            {
                for(int j = 0; j < vetor.size(); j++)
                {
                    pkPeca = "" + vetor.get(j);
                    j++;
                    nomePeca = "" + vetor.get(j);
                    j++;
                    especPeca = "" + vetor.get(j);
                    j++;
                    garantiaPeca = "" + vetor.get(j);
                    
                    String linha[] = { pkPeca, nomePeca, especPeca, garantiaPeca };
                    modelo.addRow(linha);
                }
           }
           banco.disconnect();
        }
        catch(Exception erro)
    	{
    	  	JOptionPane.showMessageDialog(null,"Erro: " + erro);
    	}	
    }
    
    public void	keyPressed(KeyEvent ke)
    {
        if(ke.getSource() == txtPkPeca)
        {

        }
        else if(ke.getSource() == txtNomePeca)
        {

        }
        else if(ke.getSource() == txaEspecPeca)
        {

        }
        else if(ke.getSource() == txtGarantiaPeca)
        {

        }
    }
    public void	keyReleased(KeyEvent ke) { }
	public void	keyTyped(KeyEvent ke) { }

	public void	mouseClicked(MouseEvent me) { }
	public void	mouseEntered(MouseEvent me) { }
	public void	mouseExited(MouseEvent me) { }
	public void	mousePressed(MouseEvent me) { }
    public void	mouseReleased(MouseEvent me)
    {
		int line = tabela.getSelectedRow();
		txtPkPeca.setText(tabela.getValueAt(line,0) + "");
		txtNomePeca.setText(tabela.getValueAt(line,1) + "");
		txaEspecPeca.setText(tabela.getValueAt(line,2) + "");
		txtGarantiaPeca.setText(tabela.getValueAt(line,3) + "");
	}
    
	public void actionPerformed(ActionEvent ae)
	{
        if(ae.getSource() == btnSair)
        {
        	setVisible(false);
        	new Menu();
        }
        else if(ae.getSource() == btnNovo)
        {
			habilitaTudo(true, 1);
			
			txtPkPeca.setText("" + (tabela.getRowCount() + 1));
			
			txtPkPeca.setEditable(false);
			txtNomePeca.setEditable(true);
			txaEspecPeca.setEditable(true);
			txtGarantiaPeca.setEditable(true);
			
			txtNomePeca.grabFocus();
			
			linhaltera = -1;
			
			return;
		}
        else if(ae.getSource() == btnAlterar)
        {
            linhaltera = tabela.getSelectedRow();
			if(linhaltera == -1)
			{
				JOptionPane.showMessageDialog(null,"Selecione uma linha!!");
				return; 
			}	
			habilitaTudo(true, 2);
			txtPkPeca.setEditable(false);	
			txtPkPeca.setText(tabela.getValueAt(linhaltera,0) + "");
			txtNomePeca.setText(tabela.getValueAt(linhaltera,1) + "");
			txaEspecPeca.setText(tabela.getValueAt(linhaltera,2) + "");
			txtGarantiaPeca.setText(tabela.getValueAt(linhaltera,3) + "");
			txtNomePeca.grabFocus();
			return;
		}
        else if(ae.getSource() == btnExcluir)
        {
			int linex = tabela.getSelectedRow();
			if(linex == -1)
			{
				JOptionPane.showMessageDialog(null,"Selecione uma linha!!","Excluindo",-1);
				return;
			}
			if(JOptionPane.showConfirmDialog(null,"Tem certeza que deseja excluir " + tabela.getValueAt(linex,1) + "?","Exclusao",0) == 0)
			{
                banco.connect();
                
                banco.setId("" + tabela.getValueAt(linex,0));
                
                banco.excluir();
                
                modelo.removeRow(linex);
                
                limpaTudo();
                
                banco.disconnect();
			}
			return;
		}
		else if(ae.getSource() == btnSalvar)
		{
			if(txtNomePeca.getText().length() == 0)
			{
                txtNomePeca.grabFocus();
				return;
            }
            else if(txaEspecPeca.getText().length() == 0)
            {
                txaEspecPeca.grabFocus();
                return;
            }
			if(linhaltera == -1)
			{
			    banco.connect();
			    
			    pkPeca = txtPkPeca.getText();
			    nomePeca = txtNomePeca.getText();
			    especPeca = txaEspecPeca.getText();
			    garantiaPeca = txtGarantiaPeca.getText();
			    
			    banco.setId(pkPeca);
			    banco.setNome(nomePeca);
			    banco.setEspec(especPeca);
			    banco.setGarantia(garantiaPeca);
			    
			    banco.inserir();
			    
			 	String linha[] = { pkPeca, nomePeca, especPeca, garantiaPeca };
                modelo.addRow(linha);
                
			    banco.disconnect();
			}
			else
			{
                banco.connect();
			    
			    pkPeca = txtPkPeca.getText();
			    nomePeca = txtNomePeca.getText();
			    especPeca = txaEspecPeca.getText();
			    garantiaPeca = txtGarantiaPeca.getText();
			    
			    banco.setId(pkPeca);
			    banco.setNome(nomePeca);
			    banco.setEspec(especPeca);
			    banco.setGarantia(garantiaPeca);
			    
			    banco.alterar();
			    
				tabela.setValueAt(txtPkPeca.getText(), linhaltera, 0);
				tabela.setValueAt(txtNomePeca.getText(), linhaltera, 1);
				tabela.setValueAt(txaEspecPeca.getText(), linhaltera, 2);
				tabela.setValueAt(txtGarantiaPeca.getText(), linhaltera, 3);
				
			    banco.disconnect();
			}
			
			habilitaTudo(false, 1);
			return;
		}
        if(ae.getSource() == btnCancelar)
        {
			habilitaTudo(false, 2);
			return;
		}
	}

    public void habilitaTudo(boolean logico, int tlimpa)
    {
        if(tlimpa == 1) limpaTudo();
    	txtPkPeca.setEnabled(logico);
        txtNomePeca.setEnabled(logico);
        txaEspecPeca.setEnabled(logico);
        txtGarantiaPeca.setEnabled(logico);
    	
    	btnNovo.setEnabled(!logico);
    	btnAlterar.setEnabled(!logico);
    	btnExcluir.setEnabled(!logico);
    	tabela.setEnabled(!logico);
    	
    	btnSalvar.setEnabled(logico);
        btnCancelar.setEnabled(logico);
    }
    public void limpaTudo()
    {
    	txtPkPeca.setText("");
        txtNomePeca.setText("");
        txaEspecPeca.setText("");
        txtGarantiaPeca.setText("");
    }
    
    public static void main(String tx[])
    {
        new SqlPecas();
    }
}