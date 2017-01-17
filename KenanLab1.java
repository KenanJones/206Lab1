import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class KenanLab1 implements KeyListener
{
private JFrame frame;
private JTextField field;
private JLabel label;
private JLabel label2;

private int[][] maze;
private int xLoc, yLoc;
private ArrayList<String> history;
private String options;
private static boolean finished = false;

   public KenanLab1()
   {
   frame = new JFrame();
   frame.setSize(200,200);
   frame.setLocation(500,300);
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   buildPanel();
   
   history = new ArrayList<String>();
   maze = new int[16][8];
   for(int col = 0; col < maze.length; col++)
      {
      for(int row = 0; row < maze[col].length; row++)
         {
         if(col==0 || col == maze.length || row == 0 || row == maze[col].length)
            maze[col][row] = 1;
         }
      }
   Random random = new Random();
   xLoc = random.nextInt(12)+ 2;
   yLoc = random.nextInt(4) + 2;
   options = "";
   frame.setVisible(true);
   showOptions();
   }
   
   public void buildPanel()
   {
   field = new JTextField();
   field.addKeyListener(this);
   frame.add(field);
   label = new JLabel();
   frame.add(label);
   label2 = new JLabel();
   frame.add(label2);
   }
   
   public static void main (String[] args)
   {
   new KenanLab1();
   if(finished)
      System.out.println("Congratulations!\nYou Survived!");
   }
   
   public void move(int x, int y)
   {
   if(x == -1 && options.contains("left")){xLoc--; history.add("left");}
   if(x == 1 && options.contains("right")){xLoc++; history.add("right");}
   if(y == -1 && options.contains("up"))  {xLoc--; history.add("up");}
   if(y == 1 && options.contains("down")) {xLoc++; history.add("down");}
   showOptions();
   }
   
   public void showOptions()
   {
   getOptions();
   //System.out.println(options);
   label.setText(options);
   label2.setText("History: " + history);
   }
   
   public void getOptions()
   {
   options = "";
   if(maze[xLoc-1][yLoc]==0)options += "left, ";
   if(maze[xLoc+1][yLoc]==0)options += "right, ";
   if(maze[xLoc][yLoc-1]==0)options += "up, ";
   if(maze[xLoc][yLoc+1]==0)options += "down, ";
   }
   
   public void keyPressed(KeyEvent e)
   {
   //System.out.println(e.getKeyCode());
   switch(e.getKeyCode())
      {
      case 37:move(-1,0);break;
      case 38:move(0,-1);break;
      case 39:move(1,0);break;
      case 40:move(0,1);break;
      }
   }
   public void keyTyped(KeyEvent e) {}
   public void keyReleased(KeyEvent e) {}
}
