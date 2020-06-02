import java.awt.*;
import java.util.ArrayList;
public class Ball{
  
  // Variables for the ball
  private Vector pos;
  private Vector vel;
  private int rad;
  private int type;
  private Color col;
  private Box box;
  
  // Constructor
  
  public Ball(Box box, Vector pos, Vector vel, int rad){
    this.box = box;
    this.vel = vel;
    this.pos = pos;
    this.rad = rad;
    this.type = (int)(3*Math.random() + 0.5); //For colors
    // SETTING THREE COLORS
    if(type == 0){
      this.col = Color.GREEN;//new Color((float) (4./5), (float) (1./5) , (float) (3./5), (float) (Math.random()*0.4 + 0.6));
    }else if(type == 2){
      this.col = Color.GRAY;//new Color((float) (1./5), (float) (3./5), (float) (4./5), (float) (Math.random()*0.4 + 0.6)); //blue
    }else if(type == 1){
      this.col = Color.red;
    }else{
      this.col = Color.blue;
    }
  }
  // Move for the ball
  public void move(){
    this.pos = pos.add(vel);
    
    // if ball out of boundary of top and bottom
    if((pos.getY() > box.getHeight() - this.rad - 1 && vel.getY() > 0) || 
       (pos.getY() < this.rad+1 && vel.getY()< 0)){
      this.vel = vel.flipSignY();
    }
    
    // If ball out of boundary of right and left wall
    if ((pos.getX() > box.getWidth()-this.rad-1 && vel.getX() > 0) || // right
        (pos.getX() < this.rad+1) && vel.getX() < 0) { // left
      this.vel = vel.flipSignX();  
    }
    
  }
  
  
  public Vector setVelPos(Ball b, Vector ref_vel, Vector ref_pos, int ref_size){
    Vector dV = b.vel.sub(ref_vel); // Velocity
    Vector dS = b.pos.sub(ref_pos); //Position
    double scalar = dV.dot(dS)/(dS.dot(dS));
    double res =  b.rad/ref_size;
    
    return ref_vel.add(dS.scale(scalar*0.5));
  }
  
  
  // Collision with another ball Kolla PDF
  public void coll(Ball b){

    if(this.type == b.type){
      if(this.rad > b.rad){
        this.rad += b.rad;
        this.vel = setVelPos(b, this.vel, this.pos, this.rad);
        // Parametrar från pdf part 1
        //this.vel = this.vel.add(dS.scale(dVdS*0.5)); 
        b.rad = 0;
      }else{
        b.rad += this.rad;
        b.vel = setVelPos(this, b.vel, b.pos, b.rad);
        //b.vel = b.vel.add(dS2.scale(dV2dS2*0.5));
        this.rad = 0;
      }
    }else{ // Not same sort
      //Ball 1
      Vector dV = b.vel.sub(this.vel);
      Vector dS = b.pos.sub(this.pos);
      double scalar = (dV.dot(dS))/(dS.dot(dS));
      double res = b.rad/this.rad;
      res = 1;
      
      //Ball2
      Vector dVp = this.vel.sub(b.vel);
      double scalarP = (dVp.dot(dS))/(dS.dot(dS));
      double res2 = this.rad/b.rad;
      res2 = 1;
      
      this.vel = this.vel.add(dS.scale(scalar*res));
      b.vel = b.vel.add(dS.scale(scalarP*res2));
    }
  }
 
  
  public Vector getPosition() {
    return pos;
  }
  
  public Vector getVelocity() {
    return vel;
  }
  
  public int getSize() {
    return rad;
  }
  
  public int getType() {
    return type;
  }
  
  public void setPosition(Vector v) {
    this.pos = v;
  }
  
  public void setVelocity(Vector v) {
    this.vel = v;
  }
  
  public boolean isSizeZero() {
    return rad == 0;
  }
  
  public void paint(Graphics g){
    g.setColor(col);
    g.fillOval((int)(pos.getX() - rad), (int)(pos.getY() - rad),(int)(2*rad), (int)(2*rad));
  }
  /*
   public void paint(Graphics g) {
   g.setColor(col);
   g.fillOval((int) (pos.getX()-rad), (int) (pos.getY()-rad), 2*rad, 2*rad);
   g.setColor(new Color((float) 1, (float) 1, (float) 1, (float) 0.3));
   g.fillOval((int) (pos.getX()-rad/1.2), (int) (pos.getY()-(rad/1.2)), rad, rad);
   g.fillOval((int) (pos.getX()-rad/1.5), (int) (pos.getY()-(rad/1.5)), rad/2, rad/2);
   }
   */
}
