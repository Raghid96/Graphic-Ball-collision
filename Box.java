
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class Box extends JPanel{
  
  // Array of balls
  private ArrayList<Ball> b = new ArrayList<Ball>();
  private boolean paused = false;
  private JLabel pause = new JLabel("PAUSED");
  
  
  // Contructor of Box
  public Box(int width, int height, int nOpar, Vector pos, Vector vel, int rad){
    b.add(new Ball(this, pos, vel, rad));
    for (int i = 0 ; i< nOpar; i++){
      b.add(new Ball(this, new Vector(Math.random()*width, Math.random()*height), vel, (int) (rad*Math.random())+2));
    }
    this.setPreferredSize(new Dimension(width, height));  
    this.setBackground(Color.black);
    this.setBorder(new LineBorder(Color.white, 1));
    this.setLayout(null);
    
    pause.setForeground(Color.white);
    pause.setFont(new Font("Calibri", Font.BOLD, 40));
    pause.setHorizontalAlignment(JLabel.CENTER);
    
    pause.setBounds(200, 280, 200, 40);
    this.add(pause);
  }
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (int i = 0; i < b.size(); i++) {
      b.get(i).paint(g);
    }
  }
  
  public void paused(int i){
    if (i == 0) {
      paused = true;
    } else {
      paused = false;
    }
  }
  
  public void step() {
    if (paused) {
      pause.setVisible(true);
    } else {
      pause.setVisible(false);
      for (int i = 0; i < b.size(); i++) {
        b.get(i).move();
        for (int j = 0; j < b.size(); j++) {
          if (i != j &&
              b.get(i).getPosition().distance(b.get(j).getPosition())
                <= b.get(i).getSize() + b.get(j).getSize()) {
            b.get(i).coll(b.get(j));
          }
          if (b.get(i).isSizeZero()) { 
            b.remove(i); 
            if (i != 0) {              
              i--;
            }
            if (j >= i && j != 0) {
              j--;
            }
          }
          if (b.get(j).isSizeZero()) {
            b.remove(j);
            if (j != 0) {
              j--;
            }
            if (i >= j && i != 0) {
              i--;
            }
          }
        }
      }
      repaint();
    }
  }
}