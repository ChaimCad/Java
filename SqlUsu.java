import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;

class SqlUsu extends JFrame implements ActionListener, MouseListener, KeyListener
{
    private DefaultTableModel modelo;
    private JTable tabela;
    private JScrollPane grade;
    private JButton btnNovo, btnAlterar, btnExcluir, btnSalvar, btnCancelar, btnSair;
    private JLabel lblPkUsu, lblNomeUsu, lblIdadeUsu, lblEmailUsu, lblSenhaUsu, lblFkPeca;
    private JTextField txtPkUsu, txtNomeUsu, txtIdadeUsu, txtEmailUsu, txtSenhaUsu;
    private JComboBox cmbFkPeca;
    
    private String pkUsu, nomeUsu, idadeUsu, emailUsu, senhaUsu, fkPeca;
    private int linhaltera;

    private BancoUsuario banco;

    public SqlUsu()
    {
        super("Arquivo SQL - TABELA USUARIOS");
        
        setLayout(null);
    	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(120, 200, 110));
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
        
        lblPkUsu = new JLabel("Pk Usuario:");
        lblNomeUsu = new JLabel("Nome Usuario:");
        lblIdadeUsu = new JLabel("Idade Usuario:");
        lblEmailUsu = new JLabel("Email Usuario:");
        lblSenhaUsu = new JLabel("Senha Usuario:");
        lblFkPeca = new JLabel("Fk Peca:");
                
        txtPkUsu = new JTextField(4);
        txtNomeUsu = new JTextField(30);
        txtIdadeUsu = new JTextField(3);
        txtEmailUsu = new JTextField(10);
        txtSenhaUsu = new JTextField(10);
        
        cmbFkPeca = new JComboBox();
        
        BancoPecas b = new BancoPecas();
        
        b.connect();
        
        ArrayList a = new ArrayList();
        a = b.pegadados();
        
        for(int i = 0; i < a.size(); i++)
        {
        	i++;
        	cmbFkPeca.addItem(a.get(i));
        	i += 2;
        }
        
        b.disconnect();
        b = null;
        
        banco = new BancoUsuario();
        
    	modelo.addColumn("PkUsu");
    	modelo.addColumn("Nome");
    	modelo.addColumn("Idade");
    	modelo.addColumn("Email");
    	modelo.addColumn("Senha");
    	modelo.addColumn("fkPeca");
    	
    	tabela.addMouseListener(this);
    	tabela.addKeyListener(this);
    	
        tabela.getTableHeader().setReorderingAllowed(false);
        
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
        btnSair.setBounds(36,450,100,30);
        
        lblPkUsu.setBounds(40,252,80,15);
        lblNomeUsu.setBounds(40,287,90,15);
        lblIdadeUsu.setBounds(40,322,90,15);
        lblEmailUsu.setBounds(40,357,90,15);
        lblSenhaUsu.setBounds(40,392,90,15);
        lblFkPeca.setBounds(40,427,80,15);
        
        txtPkUsu.setBounds(120,250,180,20);
        txtNomeUsu.setBounds(130,285,170,20);
        txtIdadeUsu.setBounds(130,320,170,20);
        txtEmailUsu.setBounds(130,355,170,20);
        txtSenhaUsu.setBounds(130,390,170,20);
        
        cmbFkPeca.setBounds(100,425,200,20);
        
        grade.setBounds(22,18,807,206);

        btnNovo.addActionListener(this);
        btnAlterar.addActionListener(this);
        btnExcluir.addActionListener(this);
        btnSalvar.addActionListener(this);
        btnCancelar.addActionListener(this);
        btnSair.addActionListener(this);
        
        btnSalvar.setEnabled(false);
        btnCancelar.setEnabled(false);
        
        txtPkUsu.setToolTipText("Pk Usuario");
        txtNomeUsu.setToolTipText("Nome Usuario(a)");
        txtIdadeUsu.setToolTipText("Idade Usuario(a)");
        txtEmailUsu.setToolTipText("Email Usuario(a)");
        txtSenhaUsu.setToolTipText("Senha Usuario(a)");
        cmbFkPeca.setToolTipText("Fk Peca");
        
        txtPkUsu.setEnabled(false);
        txtNomeUsu.setEnabled(false);
        txtIdadeUsu.setEnabled(false);
        txtEmailUsu.setEnabled(false);
        txtSenhaUsu.setEnabled(false);
        
        cmbFkPeca.setEnabled(false);
    
        grade.setToolTipText("Grade de dados");

        add(grade);

        add(btnNovo);
        add(btnAlterar);
        add(btnExcluir);
        add(btnSalvar);
        add(btnCancelar);
        add(btnSair);
        
        add(lblPkUsu);
        add(lblNomeUsu);
        add(lblIdadeUsu);
        add(lblEmailUsu);
        add(lblSenhaUsu);
        add(lblFkPeca);
        
        add(txtPkUsu);
        add(txtNomeUsu);
        add(txtIdadeUsu);
        add(txtEmailUsu);
        add(txtSenhaUsu);
        
        add(cmbFkPeca);
        
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
                JOptionPane.showMessageDialog(null,"Tabela de usuarios vazia!!");
                return;
            }
            else
            {
                for(int j = 0; j < vetor.size(); j++)
                {
                    pkUsu = "" + vetor.get(j);
                    j++;
                    nomeUsu = "" + vetor.get(j);
                    j++;
                    idadeUsu = "" + vetor.get(j);
                    j++;
                    emailUsu = "" + vetor.get(j);
                    j++;
                    senhaUsu = "" + vetor.get(j);
                    j++;
                    fkPeca = "" + vetor.get(j);
                    
                    String linha[] = { pkUsu, nomeUsu, idadeUsu, emailUsu, senhaUsu, fkPeca };
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
        if(ke.getSource() == txtPkUsu)
        {

        }
        else if(ke.getSource() == txtNomeUsu)
        {

        }
        else if(ke.getSource() == txtIdadeUsu)
        {

        }
        else if(ke.getSource() == txtEmailUsu)
        {

        }
        else if(ke.getSource() == txtSenhaUsu)
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
		txtPkUsu.setText(tabela.getValueAt(line,0) + "");
		txtNomeUsu.setText(tabela.getValueAt(line,1) + "");
		txtIdadeUsu.setText(tabela.getValueAt(line,2) + "");
		txtEmailUsu.setText(tabela.getValueAt(line,3) + "");
		txtSenhaUsu.setText(tabela.getValueAt(line,4) + "");
		cmbFkPeca.setSelectedIndex(Integer.parseInt(tabela.getValueAt(line,5) + "") - 1);
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
			
			txtPkUsu.setText("" + (tabela.getRowCount() + 1));
			
			txtPkUsu.setEditable(false);
			txtNomeUsu.setEditable(true);
			txtIdadeUsu.setEditable(true);
			txtEmailUsu.setEditable(true);
			txtSenhaUsu.setEditable(true);
			cmbFkPeca.setEditable(true);
			
			txtNomeUsu.grabFocus();
			
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
			txtPkUsu.setEditable(false);	
			txtPkUsu.setText(tabela.getValueAt(linhaltera,0) + "");
			txtNomeUsu.setText(tabela.getValueAt(linhaltera,1) + "");
			txtIdadeUsu.setText(tabela.getValueAt(linhaltera,2) + "");
			txtEmailUsu.setText(tabela.getValueAt(linhaltera,3) + "");
			txtSenhaUsu.setText(tabela.getValueAt(linhaltera,4) + "");
			cmbFkPeca.setSelectedIndex(Integer.parseInt(tabela.getValueAt(linhaltera,5) + "") - 1);
			txtNomeUsu.grabFocus();
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
			if(txtNomeUsu.getText().length() == 0)
			{
                txtNomeUsu.grabFocus();
				return;
            }
            else if(txtIdadeUsu.getText().length() == 0)
            {
                txtIdadeUsu.grabFocus();
                return;
            }
            else if(txtEmailUsu.getText().length() == 0)
            {
                txtEmailUsu.grabFocus();
                return;
            }
            else if(txtSenhaUsu.getText().length() == 0)
            {
            	txtSenhaUsu.grabFocus();
            	return;
            }
            else if(cmbFkPeca.getSelectedIndex() == -1)
            {
            	cmbFkPeca.grabFocus();
            	return;
            }
			if(linhaltera == -1)
			{
			    banco.connect();
			    
			    pkUsu = txtPkUsu.getText();
			    nomeUsu = txtNomeUsu.getText();
			    idadeUsu = txtIdadeUsu.getText();
			    emailUsu = txtEmailUsu.getText();
			    senhaUsu = txtSenhaUsu.getText();
			    fkPeca = cmbFkPeca.getSelectedIndex() + 1 + "";
			    
			    banco.setId(pkUsu);
			    banco.setNome(nomeUsu);
			    banco.setIdade(idadeUsu);
			    banco.setEmail(emailUsu);
			    banco.setSenha(senhaUsu);
			    banco.setIdPeca(fkPeca);
			    
			    banco.inserir();
			    
			 	String linha[] = { pkUsu, nomeUsu, idadeUsu, emailUsu, senhaUsu, fkPeca };
                modelo.addRow(linha);
                
			    banco.disconnect();
			}
			else
			{
                banco.connect();
			    
			    pkUsu = txtPkUsu.getText();
			    nomeUsu = txtNomeUsu.getText();
			    idadeUsu = txtIdadeUsu.getText();
			    emailUsu = txtEmailUsu.getText();
			    senhaUsu = txtSenhaUsu.getText();
			    fkPeca = cmbFkPeca.getSelectedIndex() + 1 + "";
			    
			    banco.setId(pkUsu);
			    banco.setNome(nomeUsu);
			    banco.setIdade(idadeUsu);
			    banco.setEmail(emailUsu);
			    banco.setSenha(senhaUsu);
			    banco.setIdPeca(fkPeca);
			    
			    banco.alterar();
			    
				tabela.setValueAt(txtPkUsu.getText(), linhaltera, 0);
				tabela.setValueAt(txtNomeUsu.getText(), linhaltera, 1);
				tabela.setValueAt(txtIdadeUsu.getText(), linhaltera, 2);
				tabela.setValueAt(txtEmailUsu.getText(), linhaltera, 3);
				tabela.setValueAt(txtSenhaUsu.getText(), linhaltera, 4);
				tabela.setValueAt(cmbFkPeca.getSelectedIndex() + 1 + "", linhaltera, 5);
				
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
    	txtPkUsu.setEnabled(logico);
        txtNomeUsu.setEnabled(logico);
        txtIdadeUsu.setEnabled(logico);
        txtEmailUsu.setEnabled(logico);
        txtSenhaUsu.setEnabled(logico);
        cmbFkPeca.setEnabled(logico);
    	
    	btnNovo.setEnabled(!logico);
    	btnAlterar.setEnabled(!logico);
    	btnExcluir.setEnabled(!logico);
    	tabela.setEnabled(!logico);
    	
    	btnSalvar.setEnabled(logico);
        btnCancelar.setEnabled(logico);
    }
    public void limpaTudo()
    {
    	txtPkUsu.setText("");
        txtNomeUsu.setText("");
        txtIdadeUsu.setText("");
        txtEmailUsu.setText("");
        txtSenhaUsu.setText("");
        cmbFkPeca.setSelectedIndex(-1);
    }
    
    public static void main(String tx[])
    {
        new SqlUsu();
    }
}