import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
class Teclado extends JFrame implements KeyListener
{
    public void keyTyped(KeyEvent e)
    {
        if(e.getSource() == f1)
        {
            if(f1.getText().length() > 4) e.consume();
            if(!((e.getKeyChar() >= KeyEvent.VK_0 && e.getKeyChar() <= KeyEvent.VK_9) || (e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == KeyEvent.VK_BACK_SPACE)))
            {
                e.consume();
            }
        }
    }
    public void keyPressed(KeyEvent e) {  }
    public void keyReleased(KeyEvent e) {  }
    JTextField f1,f2;

    public Teclado()
    {
        super("teste");
        setBounds(10,10,400,400);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        f1 = new JTextField(20);
        f1.addKeyListener(this);
        f2 = new JTextField(20);
        add(f1);
        add(f2);
        setVisible(true);
    }
    public static void main(String[] args) {
        new teclado();
    }
}