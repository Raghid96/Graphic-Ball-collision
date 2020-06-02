import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;

public class Run
  extends JFrame 
  implements ActionListener, ItemListener
{
  private BoxFrame box;
  private JLabel nrOfParticles = new JLabel("Number Balls");
  private JLabel delay = new JLabel("The Delay");
  
  private JTextField nr = new JTextField("20" ,7);
  private JTextField msec = new JTextField("40" ,7);
  
  private Vector velocity = new Vector(5, -5);
  private int number;
  private int milliseconds;
  
  private JLabel label = new JLabel();
  private JButton startB = new JButton("Start");
  private JButton stopB = new JButton("Stop");
  private JButton quitB = new JButton("Quit");
  private JButton pauseB = new JButton("Pause");
  
  private JLabel pickAVelocity = new JLabel("Pick a Velocity");
  private int snf;
  
  private JLabel pickASize = new JLabel("Pick a Size");
  private int size = 12;
  private JRadioButton small = new JRadioButton("Small", false);
  private JRadioButton medium = new JRadioButton("Medium", true);
  private JRadioButton big = new JRadioButton("Big", false);
  private ButtonGroup group2 = new ButtonGroup();

  
  public Run() {
    super("Menu");
    
    nr.addActionListener(this);
    msec.addActionListener(this);
    
    startB.addActionListener(this);
    stopB.addActionListener(this);
    quitB.addActionListener(this);
    pauseB.addActionListener(this);
    
    small.addItemListener(this);
    medium.addItemListener(this);
    big.addItemListener(this);
    
    nrOfParticles.setOpaque(true);
    nrOfParticles.setForeground(Color.black);
    nrOfParticles.setFont(new Font("Calibri", Font.BOLD, 12));
    nrOfParticles.setHorizontalAlignment(JLabel.CENTER);
    
    label.setOpaque(true);
    label.setIcon(new ImageIcon("ball.jpg"));
    
    
    delay.setOpaque(true);
    delay.setForeground(Color.black);
    delay.setFont(new Font("Calibri", Font.BOLD, 12));
    delay.setHorizontalAlignment(JLabel.CENTER);

    pickAVelocity.setHorizontalAlignment(JLabel.CENTER);
    pickAVelocity.setFont(new Font("Calibri", Font.BOLD, 12));
    
    pickASize.setHorizontalAlignment(JLabel.CENTER);
    pickASize.setFont(new Font("Calibri", Font.BOLD, 12));
    
    group2.add(small);
    group2.add(medium);
    group2.add(big);
    
    //Layout
    nrOfParticles.setBounds(20, 10, 100, 40);
    nr.setBounds(125, 14, 75, 30);
    delay.setBounds(20, 50, 100, 30);
    msec.setBounds(125, 54, 75, 30);
    
    pickASize.setBounds(70, 165, 100, 40);
    small.setBounds(10, 195, 75, 40);
    medium.setBounds(80, 195, 100, 40);
    big.setBounds(170, 195, 75, 40);
    
    pauseB.setBounds(75, 250, 100, 40);
    startB.setBounds(20, 290, 100, 40);
    stopB.setBounds(130, 290, 100, 40);
    quitB.setBounds(75, 330, 100, 40);
    
    label.setBounds(20, 10, 200, 300);
    
    this.add(nrOfParticles);
    this.add(nr);
    this.add(delay);
    this.add(msec);
    
    this.add(pickASize);
    this.add(small);
    this.add(medium);
    this.add(big);
    
    this.add(startB);
    this.add(stopB);
    this.add(quitB);
    this.add(pauseB);

    this.add(label);
    this.setLayout(null);
    this.setPreferredSize(new Dimension(250,400));

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();
    this.setVisible(true);
  }
  
  public void actionPerformed(ActionEvent e) {
    Scanner scNr = new Scanner(nr.getText());
    Scanner scMsec = new Scanner(msec.getText());
    
    if (scNr.hasNextInt() ) { 
      number = scNr.nextInt();
    } else {
      number = 10; // default nr of particles
    }
    if (scMsec.hasNextInt()) {
      milliseconds = scMsec.nextInt();
    } else {
      milliseconds = 30; // default delay
    }
    
    if (e.getSource() == startB) {
      box = new BoxFrame(800, 600, number, milliseconds, velocity, size);
    } else if (e.getSource() == stopB) {
      box.dispose();
    } else if (e.getSource() == quitB) {
      System.exit(0);
    } else if (e.getSource() == pauseB && pauseB.getText().equals("Pause")) {
      pauseB.setText("Resume");
      box.getBox().paused(0); // pause
    } else if (e.getSource() == pauseB && pauseB.getText().equals("Resume")) {
      pauseB.setText("Pause");
      box.getBox().paused(1); // resume
    }
  }
  
  public void itemStateChanged(ItemEvent e) {
    /*
    if (e.getSource() == slow) {
      velocity = new Vector(3, -3);
    } else if (e.getSource() == normal) {
      velocity = new Vector(5, -5);
    } else if (e.getSource() == fast) {
      velocity = new Vector(10, -10);
    }*/
    
    if (e.getSource() == small) {
      size = 5;
    } else if (e.getSource() == medium) {
      size = 12;
    } else if (e.getSource() == big) {
      size = 25;
    }
  }
  
  public static void main(String[] args) {
     Run bu = new Run(); 
  } 
  
}