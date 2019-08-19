import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class Cadastro extends JFrame implements ActionListener
{
    JLabel lblTitulo, lblNome, lblIdade, lblEmail, lblSenha;
    JTextField txtNome, txtIdade, txtEmail, txtSenha;
    JButton btnCadastro, btnVoltar, btnSair;
    String nome, email, senha;
    int idade;

    Model mod;

    public Cadastro()
    {
        super("Cadastro");

        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(400,200,600,500);

        nome = "";
        idade = 0;
        email = "";
        senha = "";

        mod = new Model();

        lblTitulo = new JLabel("Cadastro");
        lblNome = new JLabel("Nome:");
        lblIdade = new JLabel("Idade:");
        lblEmail = new JLabel("Email:");
        lblSenha = new JLabel("Senha:");

        txtNome = new JTextField(30);
        txtIdade = new JTextField(3);
        txtEmail = new JTextField(30);
        txtSenha = new JTextField(10);

        btnCadastro = new JButton("Cadastrar-se");
        btnVoltar = new JButton("Voltar");
        btnSair = new JButton("Sair");

        lblTitulo.setBounds(200,20,200,70);
        lblNome.setBounds(140,100,100,40);
        lblIdade.setBounds(140,160,100,40);
        lblEmail.setBounds(140,220,100,40);
        lblSenha.setBounds(140,280,100,40);

        txtNome.setBounds(250,100,200,40);
        txtIdade.setBounds(250,160,200,40);
        txtEmail.setBounds(250,220,200,40);
        txtSenha.setBounds(250,280,200,40);

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
        add(txtIdade);
        add(txtEmail);
        add(txtSenha);

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
            else if(txtIdade.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null,"Preencha todos os campos!!");
                txtIdade.requestFocus();
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
                    idade = Integer.parseInt(txtIdade.getText());
                    email = txtEmail.getText();
                    senha = txtSenha.getText();
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"Erro ao cadastrar-se!! " + ex.getMessage());
                    return;
                }
                
                String tudo = nome + "," + idade + "," + email + "," + senha;
                mod.setTipo(tudo);
                mod.connect();
                mod.cadastro();
                mod.disconnect();
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
}