import java.awt.event.*;
import java.util.*;

public class KenanLab1 implements KeyListener
{
private int[][] maze;
private int xLoc, yLoc;
   public static void main (String[] args)
   {
   maze = new int[8][16]
   Random random = new Random();
   xLoc = random.nextInt(16);
   yLoc = random.nextInt(8);
   }
   public void keyPressed(KeyEvent e)
   {
   
   }
   public void keyTyped(KeyEvent e) {}
   public void keyReleased(KeyEvent e) {}
}
