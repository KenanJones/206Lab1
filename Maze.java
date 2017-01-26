import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.io.*;

public class Maze implements KeyListener{
private JFrame frame;
private JPanel panel;
private JTextField field;
private JLabel label;
private JTextArea textArea;

private int[][] maze;
private int xLoc, yLoc;
private ArrayList<String> history;
private String options;

   public Maze(){
   frame = new JFrame();
   frame.setSize(200,200);
   frame.setLocation(500,300);
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

   /*for(int row = 0; row < maze.length; row++){
      for(int col = 0; col < maze[row].length; col++){
         System.out.print(maze[row][col]);
      }
   System.out.println();
   }*/
   Random random = new Random();
   boolean test = true;
   while(test){
      yLoc = random.nextInt(maze.length);
      xLoc = random.nextInt(maze[0].length);
   if(maze[yLoc][xLoc] == 0) test = false;
   }
   System.out.println(xLoc + ", " + yLoc);
   options = "";
   frame.setVisible(true);
   showOptions();
   }

   public void buildPanel(){
   panel = new JPanel();
   panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
   field = new JTextField();
   field.addKeyListener(this);
   //field.setVisible(false);
   field.setMaximumSize(new Dimension(1,1));
   panel.add(field);
   label = new JLabel();
   label.setAlignmentX(0.5f);
   panel.add(label);
   textArea = new JTextArea(5, 20); 
   textArea.setEditable(false);
   textArea.setLineWrap(true);
   textArea.setWrapStyleWord(true);
   panel.add(textArea);
   frame.add(panel);
   }

   public static void main (String[] args) throws IOException{
   new Maze();
   }
   
   public void move(int x, int y){
   if(x == -1 && options.contains("left")){xLoc--; history.add("left");}
   if(x == 1 && options.contains("right")){xLoc++; history.add("right");}
   if(y == -1 && options.contains("up"))  {yLoc--; history.add("up");}
   if(y == 1 && options.contains("down")) {yLoc++; history.add("down");}
   if(maze[yLoc][xLoc] == -1){
      System.out.println("Congratulations! You Escaped!");
      System.out.println("You Moved: " + history);
      System.exit(0);}
   showOptions();
   }
   
   public void showOptions(){
   getOptions();
   //System.out.println(options);
   label.setText(options);
   textArea.setText("History: " + history);
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
   boolean test = true;
   while(test){
      filename = JOptionPane.showInputDialog("please enter a maze file name.");
      if(filename == null)filename = "maze1.txt";
      try{
         System.out.println("try1");
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
   return filename;
   }

   public void keyPressed(KeyEvent e){
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
