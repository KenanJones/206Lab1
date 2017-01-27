//the frame does not ever get the focus unless I click it.
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.io.*;

public class Maze2 implements KeyListener{
   private JFrame frame;
   private JPanel panel;
   private JTextField field;

   private int[][] maze;
   private int xLoc, yLoc;
   private ArrayList<String> history;
   private String options;

   public Maze2(){
      frame = new JFrame();
      frame.setSize(111,111);
      frame.setLocation(-100,-100);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setFocusable(true);
      buildPanel();

      history = new ArrayList<String>();

      try{
         File file = new File(getFileInput());
         Scanner inputFile = new Scanner(file);
         maze = new int[inputFile.nextInt()][inputFile.nextInt()];
         inputFile.nextLine();//consumes an empty line left by nextInt
         int row = 0;
         while(inputFile.hasNext()){
            String input = inputFile.nextLine();
            for(int i = 0; i < input.length(); i++){
               if(input.charAt(i) == 'x')
                  maze[row][i] = 1;
               else if(input.charAt(i) == 'f')
                  maze[row][i] = -1;
            }
         row++;
         }
      }catch(IOException e){
         System.out.println("invalid maze file");
         System.exit(1);
      }

      Random random = new Random();
      boolean test = true;
      while(test){
         yLoc = random.nextInt(maze.length);
         xLoc = random.nextInt(maze[0].length);
      if(maze[yLoc][xLoc] == 0) test = false;
      }
      System.out.println(xLoc + ", " + yLoc);
      options = "";
      setFocus();
      showOptions();
      }

   public void buildPanel(){
      panel = new JPanel();
      field = new JTextField(1);
      field.addKeyListener(this);
      panel.add(field);
      frame.add(panel);
      }

   public static void main (String[] args) throws IOException{
      new Maze2();
      }

   public void move(int x, int y){
      if(x == -1 && options.contains("left")){xLoc--; history.add("left");}
      if(x == 1 && options.contains("right")){xLoc++; history.add("right");}
      if(y == -1 && options.contains("up"))  {yLoc--; history.add("up");}
      if(y == 1 && options.contains("down")) {yLoc++; history.add("down");}
      if(maze[yLoc][xLoc] == -1){
         System.out.printf("Congratulations! You Escaped in %d moves!\n",history.size());
         System.out.println("You Moved: " + history);
         System.exit(0);
      }
      showOptions();
   }

   public void showOptions(){
      getOptions();
      System.out.println("Options: " + options);
      setFocus();
   }

   public void showHistory(){
      System.out.println("History: " + history);
      showOptions();
   }

   public void getOptions(){
      options = "";
      if(maze[yLoc-1][xLoc]!=1)options += "up, ";
      if(maze[yLoc+1][xLoc]!=1)options += "down, ";
      if(maze[yLoc][xLoc-1]!=1)options += "left, ";
      if(maze[yLoc][xLoc+1]!=1)options += "right, ";
   }

   public String getFileInput(){
      String filename = "";
      Scanner keyboard = new Scanner(System.in);
      boolean test = true;
      while(test){
         System.out.println("please enter a maze file name.");
         filename = keyboard.nextLine();
         if(filename == null)filename = "maze1.txt";
         try{
            File testFile = new File(filename);
            Scanner testScan = new Scanner(testFile);
            test = false;
         }
         catch(IOException e){
            try{
               filename += ".txt";
               File testFile = new File(filename);
               Scanner testScan = new Scanner(testFile);
               test = false;
            }
            catch(IOException ex){
            }
         }
      }
      keyboard.close();
      return filename;
   }
    //setVisible(true) gives frame the focus, and then field requests it.
   public void setFocus(){
      frame.setVisible(true);
      field.requestFocusInWindow();
   }

   public void keyPressed(KeyEvent e){
      System.out.println(e.getKeyCode());
      switch(e.getKeyCode())
         {
         case 37: move(-1,0);break;
         case 38: move(0,-1);break;
         case 39: move(1,0);break;
         case 40: move(0,1);break;
         default: showHistory();
       }
   }
   public void keyTyped(KeyEvent e) {}
   public void keyReleased(KeyEvent e) {}
}
