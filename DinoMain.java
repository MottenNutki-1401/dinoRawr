import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import java.util.ArrayList;
public class DinoMain{
    public static void main (String [] args){

        JFrame gameF = new JFrame();

        gameF.setSize(700,400);
        gameF.setTitle("DinoRawr!!!");
        gameF.setLocationRelativeTo(null);



        GamePanel containF = new GamePanel();

        containF.setSize(699,399);
        containF.setBackground(Color.decode("#000260"));
        gameF.add(containF);
        gameF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameF.setVisible(true);

    }
}
//creating a new class called GamePanel
    // extends JPanel=> means we make GamePanel a JPanel 
    //@Override => we're replacing a method from a parent class with our 
    //own version in this case its a (JPanel)
 class GamePanel extends JPanel implements KeyListener{ //i got an error when i put it inside the DinoMain class so i had to put it outside
     ArrayList <Integer> obstacley = new ArrayList <> ();

    //score mechanics
    int score = 0;
        
    //draw dino (logic only) position + size
    //we put all the game states here
    int dinoX = 50;
    int dinoY= 311;
    int dinoWidth= 45;
    int dinoHeight= 45;

    //put all of the materials here

   



    
    //simple gravity mechanics
    boolean isJumping= false;
    int groundY=311; //this is like a floor, the higher the number it is the lower it gets
    //but this doesnt determine how the dinoY moves, but here the falling condition depends of this
    //obstacle 
    int obstaX = 600;
    int obstaY = groundY;
    int obstaWidth = 20;
    int obstaHeight = 35;

    


                //key press logic / Constructor 
                        public GamePanel () { 
                        setFocusable(true);
                        addKeyListener(this);
                        

                        //materials in here
                        obstacley.add(700);
                        obstacley.add(900);
                        obstacley.add(1050);
                        obstacley.add(1120);
                       
                            
                //Timer Loop Logic
                        Timer timers = new Timer(20, e->{ //unit = miliseconds
                            //1000ms=1sec 1000/20 =50 ms =>game updates 50 times per second(movement)
                             // obstaX -= 5; //This needs to be outside, player movement and world movement should not be depend opn each other
                            //before this was inside the (jumping statements)

                            //obstacle logic

                               //I must repeat the obstacle when it fly away from the pov (goes off screen)
                              //Repeat ObstaX logic
                              score++;

                              for (int i=0; i<obstacley.size();i++) {// "for (each index: on the list)"
                                
                              int x = obstacley.get (i); //read obstaX value
                              x-=5;
                              if(x<-obstaWidth) //when its out of screen we cant see
                              {
                                x = 700;
                              }
                              obstacley.set(i,x); //updated stored values
                            }
                            //Jumping statements_
                            if (isJumping) {
                                dinoY-=5; //go up
                                

                            if(dinoY <=200){
                                isJumping=false;

                            }
                            } else if (dinoY < groundY){
                                dinoY+=5; //fallback

                            }

                            repaint();

                        });  timers.start();

                        }
    // Obstacle logic
    @Override 
        protected void paintComponent (Graphics g) { //you can change any letter here
            super.paintComponent(g); //paintCompnent is a reserve keyword
             
            //groundline
            g.setColor(Color.WHITE);
            g.drawLine(0,groundY,700,groundY);
            //drawing dino using the variable constraints (from above)
            g.setColor(Color.decode("#fffd80"));
            g.fillRect(dinoX,dinoY-dinoHeight,dinoWidth,dinoHeight); //both should have the same Y logic so they become aligned
            
            g.setColor(Color.decode("#ff70d4"));
         for  (int xo : obstacley) {
            g.fillRect(xo, obstaY-obstaHeight,obstaWidth, obstaHeight); //Y is not important as of the moment
            g.setFont(new Font ("Horizon", Font.BOLD,20));
            g.drawString("Score: " +score, 500,40); // 600=> how farfrom left, 50=> how far from top
           System.out.println(obstacley);
        }  

          // g.fillRect( obstaX,obstaY, obstaWidth, obstaHeight); //width and Height determines if its lying down or up
           // This should be in order : width x width will be height
        }

        //key Listener Methods
    @Override 
    public void keyPressed (KeyEvent e){
           if (e.getKeyCode() == KeyEvent.VK_SPACE){
            dinoY -= 80;
            
            repaint();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
    
    @Override
     public void keyTyped(KeyEvent e){
        
     }
            
    }
//Quick Note:
// Why its not dinoY=-5? instead we wrote dinoY -=5
//because if we did the first one we are assigning a new value of Y, it will become dinoY = 5
//but when we did the dinoY -= 5, we just substract a value of dinoY

//state control logic = 