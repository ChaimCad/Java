import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class Inicio extends JFrame implements ActionListener
{
    JLabel lblTitulo, lblTexto1, lblTexto2;
    JButton btnEntrar, btnCadastro, btnSair;

    public Inicio()
    {
        super("[Nome do bagui]");

        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(400,200,600,400);

        lblTitulo = new JLabel("[Nome do bagui]");
        lblTexto1 = new JLabel("saldkjhbasdjfhnasjdfasdjfasdjfdjfnasdklfjnasdlfkjnasdlfijnSDLFIJASDLJFHNASJHFGBKLJASDJFLASDFJHASBF");
        lblTexto2 = new JLabel("askljdflaksjdhfaksjdhf asdkfjhaslfjhaskjhdskljfhasldf skdjhfsdf");

        btnEntrar = new JButton("Entrar");
        btnCadastro = new JButton("Cadastro");
        btnSair = new JButton("Sair");

        lblTitulo.setBounds(200,20,200,70);
        lblTexto1.setBounds(10,20,180,300);
        lblTexto2.setBounds(400,20,180,300);

        btnEntrar.setBounds(80,320,90,40);
        btnCadastro.setBounds(240,320,90,40);
        btnSair.setBounds(400,320,90,40);

        btnEntrar.addActionListener(this);
        btnCadastro.addActionListener(this);
        btnSair.addActionListener(this);

        add(lblTitulo);
        add(lblTexto1);
        add(lblTexto2);

        add(btnEntrar);
        add(btnCadastro);
        add(btnSair);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == btnEntrar)
        {
            
        }
        else if(ae.getSource() == btnCadastro)
        {
            setVisible(false);
            new Cadastro();
        }
        else if(ae.getSource() == btnSair)
        {
            System.exit(0);
        }
    }

    public static void main(String tx[])
    {
        new Inicio();
    }
}