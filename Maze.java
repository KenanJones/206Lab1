import java.awt.event.*;
<<<<<<< HEAD:Maze.java
=======
<<<<<<< HEAD:Maze2.java
=======

>>>>>>> ebd7945b91c93d5d83978ac092839b7613e26673:Maze.java
>>>>>>> master:Maze2.java
import java.util.*;
import javax.swing.*;
import java.io.*;

<<<<<<< HEAD:Maze.java
public class Maze implements KeyListener{
=======
<<<<<<< HEAD:Maze2.java
public class Maze2 implements KeyListener{
   
   private JFrame frame;
   private JTextField field;

=======


public class Maze2 implements KeyListener{

   
>>>>>>> master:Maze2.java

   private JFrame frame;
   private JTextField field;

<<<<<<< HEAD:Maze.java
=======


>>>>>>> ebd7945b91c93d5d83978ac092839b7613e26673:Maze.java
>>>>>>> master:Maze2.java
   private int[][] maze;
   private int xLoc, yLoc;
<<<<<<< HEAD:Maze.java
=======
<<<<<<< HEAD:Maze2.java
   private ArrayList<String> history, options;

   public Maze2(){
      
      getMazeFile();
      buildDisplay();
      startGame();
   }

   public static void main (String[] args) throws IOException{
      new Maze2();
      }

   public void buildDisplay(){
      frame = new JFrame();
      frame.setSize(300,15);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      field = new JTextField();
      field.addKeyListener(this);
      frame.add(field);
   }
   
   public void move(int x, int y){
      if(x == -1 && options.contains("left")){xLoc--; history.add("left");}
      else if(x == 1 && options.contains("right")){xLoc++; history.add("right");}
      else if(y == -1 && options.contains("up"))  {yLoc--; history.add("up");}
      else if(y == 1 && options.contains("down")) {yLoc++; history.add("down");}
      else{System.out.print("invalid move! ");}
      if(maze[yLoc][xLoc] == -1){
         System.out.printf("Congratulations! You Escaped in %d moves!\n",history.size());
         System.out.println("You Moved: " + history);
         System.exit(0);
      }
      showOptions();
   }

   public void getMazeFile(){
=======

>>>>>>> master:Maze2.java
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

   public static void main (String[] args){
      if(args.length >= 2)
         new Maze(args[0],Integer.valueOf(args[1]));
      else if(args.length >= 1)
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
<<<<<<< HEAD:Maze.java
=======

>>>>>>> ebd7945b91c93d5d83978ac092839b7613e26673:Maze.java
>>>>>>> master:Maze2.java
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

<<<<<<< HEAD:Maze.java
   public void buildMaze(String filename) throws IOException{
	   File file = new File(filename);
      Scanner inputFile = new Scanner(file);
	   maze = new int[inputFile.nextInt()][inputFile.nextInt()];
      inputFile.nextLine();//consumes an empty line left by nextInt
      int row = 0;
      while(inputFile.hasNext()){
         String input = inputFile.nextLine();
         for(int col = 0; col < input.length(); col++){
            if(input.charAt(col) == 'x')
               maze[row][col] = 1;
            else if(input.charAt(col) == 'f')
               maze[row][col] = -1;
=======

   public void buildMaze(String filename){

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
<<<<<<< HEAD:Maze2.java
         inputFile.close();
      }catch(IOException e){
         System.out.println("invalid maze file");
         System.exit(1);
      }
   }
   
    public String getFileInput(){
      String filename = "";
      Scanner keyboard = new Scanner(System.in);
=======

         inputFile.close();

   

    /*public String getFileInput(){

      String filename = "";

      Scanner keyboard = new Scanner(System.in);

>>>>>>> ebd7945b91c93d5d83978ac092839b7613e26673:Maze.java
      boolean test = true;

      while(test){
<<<<<<< HEAD:Maze2.java
         System.out.print("please enter a maze file name.");
         filename = keyboard.nextLine();
         if(filename.equals(null))filename = "maze1.txt";
         try{
            testFile(filename);
=======

         System.out.print("please enter a maze file name.");

         filename = keyboard.nextLine();

         if(filename.equals(null))filename = "maze1.txt";

         try{

            testFile(filename);

>>>>>>> ebd7945b91c93d5d83978ac092839b7613e26673:Maze.java
            test = false;

         }

         catch(IOException e){

            try{
<<<<<<< HEAD:Maze2.java
               testFile(filename += ".txt");
=======

               testFile(filename += ".txt");

>>>>>>> ebd7945b91c93d5d83978ac092839b7613e26673:Maze.java
               test = false;

            }

            catch(IOException ex){

            }

>>>>>>> master:Maze2.java
         }
         row++;
      }
      inputFile.close();
  }

<<<<<<< HEAD:Maze.java
   public void startGame(int numberOfMonsters){
=======
      return filename;
<<<<<<< HEAD:Maze2.java
   }
   
   public void startGame(){
      history = new ArrayList<String>();
      options = new ArrayList<String>();
      Random random = new Random();
      boolean test = true;
      while(test){
         yLoc = random.nextInt(maze.length);
         xLoc = random.nextInt(maze[0].length);
         if(maze[yLoc][xLoc] == 0){
            test = false;
         }
      }
      System.out.println(xLoc + ", " + yLoc);
      frame.setVisible(true);
      showOptions();
   }
   
   public void showOptions(){
      getOptions();
      System.out.println("Options: " + options);
      field.grabFocus();
   }

   public void showHistory(){
      System.out.println("Move History: " + history);
      showOptions();
   }

   public void getOptions(){
      options.clear();
      if(maze[yLoc-1][xLoc]!=1)options.add("up");
      if(maze[yLoc+1][xLoc]!=1)options.add("down");
      if(maze[yLoc][xLoc-1]!=1)options.add("left");
      if(maze[yLoc][xLoc+1]!=1)options.add("right");
   }
   
   public void testFile(String filename) throws IOException{
      File testFile = new File(filename);
      Scanner testScan = new Scanner(testFile);
   }

=======

   }*/

   

   public void startGame(){

>>>>>>> master:Maze2.java
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

   public void move(String direction, boolean running){

      if(direction.equals("left") && options.contains("left")){xLoc--; history.add("left");}
      else if(direction.equals("right") && options.contains("right")){xLoc++; history.add("right");}
      else if(direction.equals("up") && options.contains("up"))  {yLoc--; history.add("up");}
      else if(direction.equals("down") && options.contains("down")) {yLoc++; history.add("down");}
      else{System.out.print("invalid move! ");}
      if(running){
         if(maze[yLoc][xLoc] == -1){
            switch(direction){
               case "left": move("right",true); break;
               case "right": move("left",true); break;
               case "up": move("down",true); break;
               case "down": move("up",true); break;
            }
         }
      }else{
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
      ArrayList<String> historyCopy = new ArrayList<>(history);
      for(int i = 0; i < 5; i++){
         getOptions();
         Random random = new Random();
         move(options.get(random.nextInt(options.size())), true);
      }
      history = historyCopy;
   }

<<<<<<< HEAD:Maze.java
   public void keyPressed(KeyEvent e){
      switch(e.getKeyCode()){
         case 37: move("left",false);break;
         case 38: move("up", false);break;
         case 39: move("right", false);break;
         case 40: move("down", false);break;
=======


>>>>>>> ebd7945b91c93d5d83978ac092839b7613e26673:Maze.java
   public void keyPressed(KeyEvent e){

      //System.out.println(e.getKeyCode());

      switch(e.getKeyCode())

         {
<<<<<<< HEAD:Maze2.java
         case 37: move(-1,0);break;
         case 38: move(0,-1);break;
         case 39: move(1,0);break;
         case 40: move(0,1);break;
         default: showHistory();
       }
   }
=======

         case 37: move(-1,0);break;

         case 38: move(0,-1);break;

         case 39: move(1,0);break;

         case 40: move(0,1);break;

>>>>>>> master:Maze2.java
         default: showHistory();
      }
   showOptions();
   }
<<<<<<< HEAD:Maze.java
=======

>>>>>>> ebd7945b91c93d5d83978ac092839b7613e26673:Maze.java
>>>>>>> master:Maze2.java
   public void keyTyped(KeyEvent e) {}
   public void keyReleased(KeyEvent e) {}
}
