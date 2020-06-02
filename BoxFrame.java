import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

public class BoxFrame
  extends JFrame 
  implements ActionListener
{
  private Box theWorld;
  private Timer timer;
  private static Vector velocity;
  
  
  public BoxFrame(int width, int height,
                  int nrOfParticles, int delay, Vector velocity, int size) {
    theWorld = new Box(width, height, nrOfParticles, new Vector(width*Math.random(), height*Math.random()), velocity, size);
    timer = new Timer(delay, this);
    this.velocity = velocity;
    add(theWorld);
    pack();
    setVisible(true);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    timer.start();
  }
  
  public Box getBox() {
    return theWorld;
  }
  
  public void actionPerformed(ActionEvent e) {
    theWorld.step();
  }
  
  public static void main(String[] args) { 
    BoxFrame box = new BoxFrame(600, 600, 15, 30, new Vector(5, -5), 10);
  }
}