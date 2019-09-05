import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class Cadastro extends JFrame implements ActionListener, KeyListener
{
    JLabel lblTitulo, lblNome, lblIdade, lblEmail, lblSenha;
    JTextField txtNome, txtEmail, txtSenha;
    JSpinner spnIdade;
    JButton btnCadastro, btnVoltar, btnSair;
    SpinnerModel model;
    String nome, email, senha;
    int idade;

    //Model mod;
    BancoUsuario bu;

    public Cadastro()
    {
        super("Cadastro");

        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(400,200,600,500);
        setResizable(false);

        nome = "";
        idade = 0;
        email = "";
        senha = "";

        //mod = new Model();
        bu = new BancoUsuario();

        model = new SpinnerNumberModel(15, 15, 90, 1);

        lblTitulo = new JLabel("Cadastro");
        lblNome = new JLabel("Nome:");
        lblIdade = new JLabel("Idade:");
        lblEmail = new JLabel("Email:");
        lblSenha = new JLabel("Senha:");

        txtNome = new JTextField(30);
        txtEmail = new JTextField(30);
        txtSenha = new JTextField(10);

        spnIdade = new JSpinner(model);

        btnCadastro = new JButton("Cadastrar-se");
        btnVoltar = new JButton("Voltar");
        btnSair = new JButton("Sair");

        lblTitulo.setBounds(200,20,200,70);
        lblNome.setBounds(140,100,100,40);
        lblIdade.setBounds(140,160,100,40);
        lblEmail.setBounds(140,220,100,40);
        lblSenha.setBounds(140,280,100,40);

        txtNome.setBounds(250,100,200,40);
        txtEmail.setBounds(250,220,200,40);
        txtSenha.setBounds(250,280,200,40);
        
        spnIdade.setBounds(250,160,200,40);

        txtNome.addKeyListener(this);
        txtEmail.addKeyListener(this);
        txtSenha.addKeyListener(this);
        
        spnIdade.addKeyListener(this);

        btnCadastro.setBounds(240,350,120,40);
        btnVoltar.setBounds(100,400,140,40);
        btnSair.setBounds(360,400,140,40);

        btnCadastro.addActionListener(this);
        btnVoltar.addActionListener(this);
        btnSair.addActionListener(this);

        add(lblTitulo);
        add(lblNome);
        add(lblIdade);
        add(lblEmail);
        add(lblSenha);

        add(txtNome);
        add(txtEmail);
        add(txtSenha);
        
        add(spnIdade);

        add(btnCadastro);
        add(btnVoltar);
        add(btnSair);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == btnCadastro)
        {
            if(txtNome.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,"Preencha todos os campos!!");
                txtNome.requestFocus();
            }
            else if(txtEmail.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,"Preencha todos os campos!!");
                txtEmail.requestFocus();
            }
            else if(txtSenha.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,"Preencha todos os campos!!");
                txtSenha.requestFocus();
            }
            else
            {
                try
                {
                    nome = txtNome.getText();
                    email = txtEmail.getText();
                    senha = txtSenha.getText();
                    
                    idade = Integer.parseInt(spnIdade.getValue()+"");
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"Erro ao cadastrar-se!! " + ex.getMessage());
                    return;
                }
                
                String tudo = nome + "," + idade + "," + email + "," + senha;
                bu.setTipo(tudo);
                bu.connect();
                bu.inserir();
                bu.disconnect();
            }
        }
        else if(ae.getSource() == btnVoltar)
        {
            setVisible(false);
            new Inicio();
        }
        else if(ae.getSource() == btnSair)
        {
            System.exit(0);
        }
    }

    public void keyTyped(KeyEvent ke)
    {
        if(ke.getSource() == txtNome)
        {
            if(txtNome.getText().length() >= 30) ke.consume();
            if(((ke.getKeyChar() >= KeyEvent.VK_0 && ke.getKeyChar() <= KeyEvent.VK_9) || (ke.getKeyChar() == KeyEvent.VK_ENTER || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE)))
            {
                ke.consume();
            }
        }
        else if(ke.getSource() == txtEmail)
        {
            if(txtEmail.getText().length() >= 30) ke.consume();
            if(((ke.getKeyChar() == KeyEvent.VK_ENTER || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE)))
            {
                ke.consume();
            }
        }
        else if(ke.getSource() == txtSenha)
        {
            if(txtSenha.getText().length() >= 10) ke.consume();
            if(((ke.getKeyChar() == KeyEvent.VK_ENTER || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE)))
            {
                ke.consume();
            }
        }
    }
    public void keyPressed(KeyEvent ke) {  }
    public void keyReleased(KeyEvent ke) {  }
}