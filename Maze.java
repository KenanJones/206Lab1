import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.io.*;

public class Maze implements KeyListener{

   private JFrame frame;
   private JTextField field;

   private int[][] maze;
   private int xLoc, yLoc;
   private ArrayList<String> history, options;

   public Maze(){

      getMazeFile("maze1.txt");
      buildDisplay();
      startGame(0);
   }

public Maze(String mazeFileName){

      getMazeFile(mazeFileName);
      buildDisplay();
      startGame(0);
   }

public Maze(String mazeFileName, int monsters){

      getMazeFile(mazeFileName);
      buildDisplay();
      startGame(monsters);
   }

   public static void main (String[] args) throws IOException{
      if(args.length >= 1)
         new Maze(args[0]);
      else new Maze();
      }

   public void buildDisplay(){
      frame = new JFrame();
      frame.setSize(300,15);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      field = new JTextField();
      field.addKeyListener(this);
      frame.add(field);
   }

   public void getMazeFile(String filename){
      try{
         testFile(filename);
         buildMaze(filename);
      }
      catch(IOException e){
         try{
            testFile(filename += ".txt");
            buildMaze(filename);
         }
         catch(IOException ex){
	          System.out.println("invalid maze file");
            System.exit(1);
         }
      }
   }
   
   public void testFile(String filename) throws IOException{
      File testFile = new File(filename);
      Scanner testScan = new Scanner(testFile);
   }

   public void buildMaze(String filename) throws IOException{
	    File file = new File(filename);
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
      inputFile.close();
  }

   public void startGame(int numberOfMonsters){
      history = new ArrayList<String>();
      options = new ArrayList<String>();
      Random random = new Random();
      int i = 0;
      while(i < numberOfMonsters){
         int y = random.nextInt(maze.length);
         int x = random.nextInt(maze[0].length);
         if(maze[y][x] == 0){
            maze[y][x] = 2;
            i++;
         }
      }
      boolean test = true;
      while(test){
         yLoc = random.nextInt(maze.length);
         xLoc = random.nextInt(maze[0].length);
         if(maze[yLoc][xLoc] == 0){
            test = false;
         }
      }
      System.out.println(xLoc + ", " + yLoc);
      showOptions();
   }

   public void walk(String direction){
   move(direction);
   }
   
   public void move(String direction){

      if(direction.equals("left") && options.contains("left")){xLoc--; history.add("left");}
      else if(direction.equals("right") && options.contains("right")){xLoc++; history.add("right");}
      else if(direction.equals("up") && options.contains("up"))  {yLoc--; history.add("up");}
      else if(direction.equals("down") && options.contains("down")) {yLoc++; history.add("down");}
      else{System.out.print("invalid move! ");}
      if(maze[yLoc][xLoc] == -1){
         System.out.printf("Congratulations! You Escaped in %d moves!\n",history.size());
         System.out.println("You Moved: " + history);
         System.exit(0);
      }
      if(maze[yLoc][xLoc] == 2){
         System.out.println("Monster! Run!");
         runFromMonster();
      }
   }

   public void showOptions(){
      getOptions();
      System.out.println("Options: " + options);
      frame.setVisible(true);
      field.requestFocusInWindow();
   }

   public void showHistory(){
      System.out.println("Move History: " + history);
   }

   public void getOptions(){
      options.clear();
      if(maze[yLoc-1][xLoc]!=1)options.add("up");
      if(maze[yLoc+1][xLoc]!=1)options.add("down");
      if(maze[yLoc][xLoc-1]!=1)options.add("left");
      if(maze[yLoc][xLoc+1]!=1)options.add("right");
   }
   
   public void runFromMonster(){
      for(int i = 0; i < 5; i++){
         getOptions();
         Random random = new Random();
         move(options.get(random.nextInt(options.size())));
      }
   }

   public void keyPressed(KeyEvent e){
      //System.out.println(e.getKeyCode());
      switch(e.getKeyCode()){
         case 37: walk("left");break;
         case 38: walk("up");break;
         case 39: walk("right");break;
         case 40: walk("down");break;
         default: showHistory();
      }
   showOptions();
   }
   public void keyTyped(KeyEvent e) {}
   public void keyReleased(KeyEvent e) {}
}
