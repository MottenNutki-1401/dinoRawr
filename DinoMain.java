import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
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
        
    //draw dino (logic only) position + size
    //we put all the game states here
    int dinoX = 50;
    int dinoY= 100;
    int dinoWidth= 45;
    int dinoHeight= 45;
    //simple gravity mechanics
    boolean isJumping= false;
    int groundY=311; //this is like a floor, the higher the number it is the lower it gets
    //but this doesnt determine how the dinoY moves, but here the falling condition depends of this


                //key press logic / Constructor 
                        public GamePanel () { 
                        setFocusable(true);
                        addKeyListener(this);

                        Timer timers = new Timer(20, e->{ //unit = miliseconds
                            //1000ms=1sec 1000/20 =50 ms =>game updates 50 times per second(movement)

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
    
    @Override 
        protected void paintComponent (Graphics g) { //you can change any letter here
            super.paintComponent(g); //paintCompnent is a reserve keyword

            //drawing dino using the variable constraints (from above)
            g.setColor(Color.decode("#fffd80"));
            g.fillRect(dinoX,dinoY,dinoWidth,dinoHeight);
    
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