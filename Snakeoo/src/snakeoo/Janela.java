/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeoo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Eloá
 */
public class Janela extends JFrame implements KeyListener{
    JPanel container = new JPanel();
    public static int larguraJa = 600;
    public static int alturaJa = 550;

    Serpente anaconda;
    
    JLabel gameOver = new JLabel();
    
    public Janela(){
        this.setTitle("Snake");
        this.setSize(larguraJa,alturaJa);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        container.setBackground(Color.RED);
        
        gameOver.setBounds((larguraJa/2)-40,0,100,20);
        
        this.addKeyListener(this);
        this.setContentPane(container);
        this.setVisible(true);
        
        anaconda = new Serpente(this.getGraphics(), 4);
        
    }
    
    public void startGame(){
        anaconda.jogar();
        gameOver();
    }
    
   public void gameOver(){
        gameOver.setText("Game Over !");
        gameOver.setFont(new Font("Dialog",Font.ITALIC,15));
        container.add(gameOver);
        container.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(java.awt.event.KeyEvent evt) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    if(evt.getKeyCode()==KeyEvent.VK_UP && anaconda.direction!=3)
        anaconda.direction =1;
     if(evt.getKeyCode()==KeyEvent.VK_RIGHT && anaconda.direction!=4)
        anaconda.direction =2;
      if(evt.getKeyCode()==KeyEvent.VK_DOWN && anaconda.direction!=1)
        anaconda.direction =3;
       if(evt.getKeyCode()==KeyEvent.VK_LEFT && anaconda.direction!=2)
        anaconda.direction =4;
    
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }
}
