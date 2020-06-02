public class Vector {
  private double x, y;
  
  // Constructor
  public Vector(double x, double y) {
    this.x = x;
    this.y = y;
  }
  
  public String toString() {
    return "[" + this.x + ", " + this.y + "]";
  }
  
  // Vector addition
  public Vector add(Vector v) {
    return new Vector(this.x + v.x, this.y + v.y);
  }
  
  // Vector subtraction
  public Vector sub(Vector v) {
    return new Vector(this.x - v.x, this.y - v.y);
  }
  
  // Multiplication
  public double dot(Vector v) {
    return this.x*v.x + this.y*v.y;
  }
  
  // Scale with d
  public Vector scale(double d) { 
    return new Vector(this.x*d, this.y*d);
  }
  
  // Distance to vector
  public double distance(Vector v) {
    return Math.sqrt(Math.pow(this.x - v.x, 2) + Math.pow(this.y - v.y, 2));
  }
  
  // Length of vector
  public double length() {
    return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
  }
  
  public double getX() {
    return this.x;
  }
  
  public double getY() {
    return this.y;
  }
  
  public Vector flipSignX() { 
    return new Vector(-this.x, this.y);
  }
  
  public Vector flipSignY() {
    return new Vector(this.x, -this.y);
  }
  
  public static Vector randomVector(double length) {
    double ratio = Math.random();
    double x = ratio*length;
    double y = Math.sqrt(Math.pow(length, 2) - Math.pow(x, 2));
    return new Vector(x, y);
  }
  
  public double angle() {
    return Math.atan2(this.y, this.x);
  }
  
  public static Vector polar(double length, double angle) {
    double ratio = Math.tan(angle);
    double x = -Math.sqrt(length/(2*Math.pow(ratio, 2)));
    double y = Math.sqrt(Math.pow(length, 2) - Math.pow(x, 2));
    return new Vector(x, y);
  }
  
}