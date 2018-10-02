/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeoo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JPanel;

/**
 *
 * @author Eloá
 */
public class Serpente extends JPanel{
    public int largura;
    Graphics g;
    
    public boolean jogar = true;
    public int qtdMaca=6;
    Scanner sc;
    ArrayList<Corpo> corps = new ArrayList<Corpo>();
    ArrayList<Maca> macas = new ArrayList<Maca>();
    
    public int direction = 4;
    
    public Serpente(Graphics g, int largura){
        this.g = g;
        this.largura = largura;
        sc = new Scanner (System.in);
        
    }
    public void jogar(){
        criarSerpente();
        while(jogar==true){
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, Janela.larguraJa, Janela.alturaJa);
            
            criarMaca();
            desenhoMaca();
            desenhoSerpent();
            showScore();
            
            sleep(70);
            
            move();
            checkCollision();
            
            
        } 
    }
    
    public void sleep(int time){
        try{
            Thread.sleep(time);
            
        }catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }
    
    public void criarMaca(){
        int randX,randY;
        Boolean creation = true;
        
        while(macas.size()< qtdMaca){
            creation=true;
            int widthJa =((Janela.larguraJa-20)/10)-2;
            int heightJa =((Janela.alturaJa-20)/10)-2;
            
            randX = (int)((Math.random()*(widthJa))+3);
            randY = (int)((Math.random()*(heightJa))+3);
            
            randX =(randX*10);
            randY=(randY*10);
            
            for(int i=0; i<corps.size(); i++){
                Corpo seExiste = corps.get(i);
                
                if(randX == seExiste.posX && randY == seExiste.posY){
                    creation =false;
                }
            }
            
            if(creation==true){
                macas.add(new Maca(randX, randY, Color.RED));
            }
            
        }
        
        
    }
    public void criarSerpente(){
     for (int j= 0; j<this.largura;j++){
        int altura;
        altura =((int)Janela.alturaJa/2)/10;
        altura*=10;
        if(j==0){
            corps.add(new Corpo(Janela.larguraJa/2+((j)*10),altura,Color.YELLOW));
        }
        else if(j>0)
            corps.add(new Corpo(Janela.larguraJa/2+((j)*10),altura,Color.WHITE));    
    }
    }
    
    public void desenhoMaca(){
        for(int x=0; x<macas.size();x++){
            Maca m =macas.get(x);
            
            g.setColor(m.couleur);
            g.fillOval(m.posX,m.posY, 10, 10);
             
        }
    }
    
    public void desenhoSerpent(){
       for(int i=0; i<corps.size();i++){
           Corpo a;
           a = corps.get(i);
           g.setColor(a.couleur);
           g.fillOval(a.posX, a.posY, 11, 11);
            
           
       }
       }  
    public void showScore(){
        g.setFont(new Font("TimesRoman", Font.PLAIN,20));
        g.drawString(Integer.toString(corps.size()), 10, Janela.alturaJa-10);
    }
    //Colisao maca
    public void checkCollision(){
        for(int i =0; i<macas.size();i++){
            Maca checkMaca = macas.get(i);
            Corpo checkSerpente = corps.get(0);
            Corpo lastguraPosicao = corps.get(corps.size()-1);
            
            if(checkMaca.posX ==checkSerpente.posX && checkMaca.posY == checkSerpente.posY){
                macas.remove(i);
                corps.add(new Corpo((200)+((lastguraPosicao.posX)+10), 0, Color.WHITE));
            }
            
        }
        //Colisao corpo serpente
        for(int i =1; i<corps.size();i++){
            Corpo corpsSerpente = corps.get(i);
            Corpo cabecaSerpente = corps.get(0);
            
            if(cabecaSerpente.posX == corpsSerpente.posX && cabecaSerpente.posY== corpsSerpente.posY)
                jogar=false;
        }
        //Se passar a janela
        Corpo cabecaSerpent = corps.get(0);
        if(cabecaSerpent.posX < 10)
            jogar = false;
        if(cabecaSerpent.posX >(Janela.larguraJa-20))
            jogar = false;
        if(cabecaSerpent.posY < 30)
            jogar = false;
        if(cabecaSerpent.posY >(Janela.alturaJa-20))
            jogar = false;
        
        
    }

   public void move(){
       int px, py;
       for(int i = corps.size()-1;i>0;i--){
           Corpo a;
           a = corps.get(i-1);
           px = a.posX;
           py = a.posY;
           a = corps.get(i);
           a.posX = px;
           a.posY = py;
       }
       Corpo b1 = corps.get(0);
       
       if(direction ==1)
           b1.posY -=10;
       
       if(direction ==2)
           b1.posX +=10;
       if(direction ==3)
           b1.posY +=10;
       if(direction ==4)
           b1.posX -=10;
       
       
   }
    
    }
    
    
    

