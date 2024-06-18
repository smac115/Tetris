import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class TetrisPanel extends JPanel
{
   //tetris-related fields




   //DO NOT TOUCH DO NOT TOUCH DO NOT TOUCH DO NOT TOUCH!!!!!!!!!!!!!
   public static final int SQUARE = 30;
   public static final int WIDTH = SQUARE * 10;
   public static final int HEIGHT = SQUARE * 22;
   ////////////////////////////////////////////////////////////////////








   private SubpanelGUIPanel gui;

   public static ArrayList<Block> fallenBlocks;
   public static boolean[][] placedSquares;
   private static ArrayList<Integer> clearLines;
   private boolean timeStop;
   //Most of this is the same as AnimationPanel
   private static final Color BACKGROUND = Color.DARK_GRAY;
   
   private BufferedImage myImage;  
   private Graphics myBuffer;
   
   private Timer t;
   private static int frames;
   private int fallDelay;
   public int landDelay;
   public boolean addLandDelay;

   private int sinceI;
   private int sinceO;
   private int sinceS;
   private int sinceZ;
   private int sinceJ;
   private int sinceL;
   private int sinceT;

   private static ArrayList<Animatable> animationObjects;
   
   private boolean up;
   private boolean w;
   private boolean a;
   private boolean space;
   private boolean esc;

   private int lines;
   private int score;
   private boolean spun;
   private int b2b;
   private int combo;

   private boolean gameOver;
   private int gameOverDelay;
   private boolean swappedToEndScreen;
   
   private static FallingPiece falling;
   private static FallingPiece hold;
   private boolean usedHold;

   private boolean firstHoldHasHappened=false;
   //constructors
      public Clip loadClip( String filename )
      {
      Clip in = null;

      try
      {
         AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(filename));
         in = AudioSystem.getClip();
         in.open( audioIn );
      }
      catch( Exception e )
      {
         e.printStackTrace();
      }

      return in;
   }
   
   public TetrisPanel(SubpanelGUIPanel arg)
   {
      lines = 0;
      fallDelay = 100;
      score = 0;
      spun = false;
      b2b = 0;
      combo = 0;

      frames = 1;

      gameOver = false;
      gameOverDelay = 0;
      swappedToEndScreen = false;

      timeStop = false;
      gui = arg;//thats the owner of this panel
      setFocusable(true);//so we can use key input
      setPreferredSize(new Dimension(WIDTH, HEIGHT));
      placedSquares = new boolean[22][10];
      fallenBlocks = new ArrayList<Block>();

      myImage =  new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); 
      myBuffer = myImage.getGraphics(); 
      myBuffer.setColor(Color.DARK_GRAY);    
      myBuffer.fillRect(0,0,WIDTH,HEIGHT);

      sinceI = 0;
      sinceO = 0;
      sinceS = 0;
      sinceZ = 0;
      sinceJ = 0;
      sinceL = 0;
      sinceT = 0;
      
      myBuffer.setColor(Color.WHITE);
    
      animationObjects = new ArrayList<Animatable>();  

      falling = new TetrisBlockT(this);//starts as T
      animationObjects.add(falling);

      t = new Timer(5, new AnimationListener());
      t.start();  //Animation starts, but square -won't move yet...

      addMouseListener(new MovementListener());
      

      addKeyListener(new Key());  //Key is a private class defined below
      setFocusable(true);  //Don't forget this!
      
      usedHold = false;

      //key input buddies
      up = false;
      w = false;
      a = false;

      landDelay = 0;
      addLandDelay = false;

      //music 
      
      clearLines = new ArrayList<Integer>();
      Clip music = loadClip("TetrisGoodLoop.wav");
      music.loop(Clip.LOOP_CONTINUOUSLY);
   }
   public boolean getFirstHoldHasHappened()
   {
      return firstHoldHasHappened;
   }
   public void deletePiece(FallingPiece piece)
   {
      Block[] blocks = piece.getBlocks();
      for(int i = 0;i<4;i++)
      {
         fallenBlocks.add(blocks[i]);
      }
      ArrayList<Block> newFallenBlocks = new ArrayList<Block>();
      for (Block myBlock : fallenBlocks) {//for each block in fallen blocks
         if ((myBlock.getY() == blocks[0].getY() && myBlock.getX() == blocks[0].getX())|| (myBlock.getY() == blocks[1].getY() && myBlock.getX() == blocks[1].getX()) || (myBlock.getY() == blocks[2].getY() && myBlock.getX() == blocks[2].getX()) || (myBlock.getY() == blocks[3].getY() && myBlock.getX() == blocks[3].getX())) {
            
            placedSquares[myBlock.getY()/SQUARE][myBlock.getX() / SQUARE] = false;//make the boolean value of that square false again
         } else {//otherwise...
            newFallenBlocks.add(myBlock);//add it to a list of new fallen blocks
         }
      }
      fallenBlocks.clear();//clear current fallen blocks
      for (Block block : newFallenBlocks) {//add new fallen blocks
         fallenBlocks.add(block);
      }

   }
   public void holdPiece(FallingPiece piece)
   {
      
      FallingPiece placeHolder;
      
      if(hold != null)
      {
         animationObjects.remove(animationObjects.indexOf(falling));
         placeHolder = hold; //put hold in a placeholder
         hold = piece; //make the current falling piece the hold piece...
         falling = placeHolder; //make the hold piece the current falling piece!
         animationObjects.add(falling);
         hold.resetPiece();
      }
      else
      {
         hold = piece;
         hold.resetPiece();
         newPiece();
      }

   }
   public int getHoldPiece()
   {
      //0 is t
      //1 is l
      //2 is o
      //3 is J
      //4 is Z
      //5 is S
      //6 is I
      if(hold == null)
      {
         return -1;
      }
      else if(hold.getType()=="t")
      {
         return 0;
      }
      else if(hold.getType()=="l")
      {
         return 1;
      }
      else if(hold.getType()=="o")
      {
         return 2;
      }
      else if(hold.getType()=="j")
      {
         return 3;
      }
      else if(hold.getType()=="z")
      {
         return 4;
      }
      else if(hold.getType()=="s")
      {
         return 5;
      }
      else 
      {
         return 6;
      }
   }

   public void resume() {
      timeStop = false;
   }

   public void animate()
   {
      myBuffer.setColor(BACKGROUND);
      myBuffer.fillRect(0,0,WIDTH,HEIGHT);
      myBuffer.setColor(Color.WHITE);
      //make grid
      for(int yStep = 0; yStep<=20;yStep++)
      {
         int yLoc = yStep*SQUARE + SQUARE * 2;
         myBuffer.drawLine(0, yLoc, WIDTH, yLoc);
      }
      for(int xStep = 0; xStep < 10; xStep++)
      {
         int xLoc = xStep*SQUARE;
         myBuffer.drawLine(xLoc, SQUARE * 2, xLoc, HEIGHT);
      }
      //Loop through fallING block
      for(Animatable animationObject : animationObjects)
      {
         animationObject.step();
         animationObject.drawMe(myBuffer);  

      }
      // Loop through fallEN blocks
      for (Block fallenBlock : fallenBlocks) {
            fallenBlock.drawMe(myBuffer);
      }
      if (falling.canMoveDown()) {
         addLandDelay = false;
         landDelay = 0;
      }
      //move down every so often
      if(frames%fallDelay==0) {
         if (falling.canMoveDown()) {
            spun = false;
         }
         falling.moveDown();

      }
      //delay before it locks in
      if (addLandDelay && !timeStop) {
         landDelay++;
      }
      //lock it in if its been waiting too long
      if (landDelay > 50) {
         placePiece();
      }

      if (lines > 0 && lines % 10 == 0) {
         if (fallDelay > 3) {
            fallDelay -= 3;
         }
      }

      //game over code
      if (gameOver && !timeStop) {
         timeStop = true;
      }
      if (gameOver && timeStop) {
         myBuffer.setColor(Color.BLACK);
         myBuffer.fillRect(SQUARE * 2, SQUARE * 3, SQUARE * 6, SQUARE * 3);
         myBuffer.setColor(Color.WHITE);
         myBuffer.setFont(new Font("Sans Serif", Font.BOLD, 30));
         myBuffer.drawString("Game Over", SQUARE * 2 + 8, SQUARE * 4 + 20);
         gameOverDelay++;
      }
      //waits to switch panel
      if (gameOverDelay > 200 && !swappedToEndScreen) {
         gui.endGame();
         swappedToEndScreen = true;
      }
      //as long as we haven't stopped time, keep the game going
      if (!timeStop) {
         frames++;
      }
      repaint();
   }

   public void stopTime() {
      timeStop = true;
   }
   public void paintComponent(Graphics g)  
   {
      //draw buffer
      g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);  
   }

   public static void playClip( Clip clip )//music
   {
      if( clip.isRunning() ) {clip.stop();
            clip.setFramePosition( 0 );
      }
   }

   public int getScore() {
      return score;
   }
   public boolean checkClear(int row) {
      for (int c = 0; c < 10; c++) {//loops 10 times (amount of squares in a row)
         if (!placedSquares[row][c]) {//if the line has even one place where its not a block...
            return false;//we shouldnt clear
         }
      }
      return true;//otherwise, sure
   }
   
   public void clearLine(int row)//part of clearing line
   {
      ArrayList<Block> newFallenBlocks = new ArrayList<Block>();
      for (Block myBlock : fallenBlocks) {//for each block in fallen blocks
         if (myBlock.getY() / SQUARE == row) {//if the block is in the row we are clearing...
            placedSquares[row][myBlock.getX() / SQUARE] = false;//make the boolean value of that square false again
         } else {//otherwise...
            newFallenBlocks.add(myBlock);//add it to a list of new fallen blocks
         }
      }
      fallenBlocks.clear();//clear current fallen blocks
      for (Block block : newFallenBlocks) {//add new fallen blocks
         fallenBlocks.add(block);
      }
      for (Block block : fallenBlocks) {//for each of the new fallen blocks
         if (block.getY() / SQUARE < row) {//if it was in a higher row?
            block.setY(block.getY() + SQUARE);//move it down
         }
      }
      boolean stopShift;
      for(int i = row - 1; i > -1; i--)//Start from the row we're clearing -1, while its greater than -1 and going "up"
      {
         stopShift = true;//stop shift is true
         for (int j = 0; j < 10; j++) {
            if (placedSquares[i][j]) {//if the row has a block...
               placedSquares[i+1][j] = true;//move the bool value down
               placedSquares[i][j] = false;//make the place it used to be false
               stopShift = false;//now its false
            }
         }
         if (stopShift) {
            break;
         }
      }
      //gui.easty.setScoreLabel(score);
   }
   
   public void placePiece() {
      clearLines.clear();
      for (int i = 0; i < 4; i++) {
         fallenBlocks.add(falling.getBlocks()[i]);
         placedSquares[falling.getBlocks()[i].getLocation().getY() / SQUARE][falling.getBlocks()[i].getLocation().getX() / SQUARE] = true;
      }
      landDelay = 0;
      addLandDelay = false;

      int clearedLines = 0;
      Coordinate range = falling.yRange();
      for (int i = range.getY(); i <= range.getX(); i++) {
         if (checkClear(i)) {
            clearLine(i);
            clearedLines++;
         }
      }
      int addScore = 0;
      if (!timeStop) {
         addScore += Math.pow(10, clearedLines);
         if (clearedLines == 4) {
            addScore *= b2b + 1;
            b2b++;
         } else if (spun && clearedLines > 0) {
            addScore += 10000;
            addScore *= b2b + 1;
            b2b++;
         } else if (clearedLines > 0 && clearedLines < 4) {
            b2b = 0;
         }
         if (clearedLines == 0) {
            combo = 0;
         } else {
            combo++;
         }
         if (fallenBlocks.isEmpty()) {
            addScore += 1000000;
         }
         score += addScore * (combo + 1);
         gui.easty.setScore(score);
      }
      usedHold = false;
      if (falling.checkGameOver()) {
         gameOver = true;
      } else {
         newPiece();  
      } 
   }
   
   public void addSinceL(){sinceL++;}
   public void addSinceJ(){sinceJ++;}
   public void addSinceT(){sinceT++;}
   public void addSinceS(){sinceS++;}
   public void addSinceZ(){sinceZ++;}
   public void addSinceO(){sinceO++;}
   public void addSinceI(){sinceI++;}

   private void newPiece() {
      
      animationObjects.remove(animationObjects.indexOf(falling));
      int currentPiece;
      //setting next piece
      int randomPiece = (int)(Math.random() * 6);

      //anti drought code
      if(sinceT > 10){randomPiece = 0;sinceT = 0;}
      else if(sinceL > 10){randomPiece = 1;sinceL = 0;}
      else if(sinceO > 10){randomPiece = 2;sinceO = 0;}
      else if(sinceJ > 10){randomPiece = 3;sinceJ = 0;}
      else if(sinceZ > 10){randomPiece = 4;sinceZ = 0;}
      else if(sinceS > 10){randomPiece = 5;sinceS = 0;}
      else if(sinceI > 10){randomPiece = 6;sinceI = 0;}

      //T:0, L:1, O:2, J:3, Z:4, S:5, I:6
      if(randomPiece == 0)
      {
         currentPiece = gui.easty.bumpNext(new ImageIcon("fullPieceT.png"));
         sinceL++;
         sinceO++;
         sinceJ++;
         sinceZ++;
         sinceS++;
         sinceI++;
      }
      else if(randomPiece == 1)
      {
         currentPiece = gui.easty.bumpNext(new ImageIcon("fullPieceL.png"));
         sinceT++;
         sinceO++;
         sinceJ++;
         sinceZ++;
         sinceS++;
         sinceI++;
      }
      else if(randomPiece == 2)
      {
         currentPiece = gui.easty.bumpNext(new ImageIcon("fullPieceO.png"));
         sinceL++;
         sinceT++;
         sinceJ++;
         sinceZ++;
         sinceS++;
         sinceI++;
      }
      else if(randomPiece == 3)
      
      {
         currentPiece = gui.easty.bumpNext(new ImageIcon("fullPieceJ.png"));
         sinceL++;
         sinceO++;
         sinceT++;
         sinceZ++;
         sinceS++;
         sinceI++;
      }
      else if(randomPiece == 4)
      {
         currentPiece = gui.easty.bumpNext(new ImageIcon("fullPieceZ.png"));
         sinceL++;
         sinceO++;
         sinceJ++;
         sinceT++;
         sinceS++;
         sinceI++;
      }
      else if(randomPiece == 5)
      {
         currentPiece = gui.easty.bumpNext(new ImageIcon("fullPieceS.png"));
         sinceL++;
         sinceO++;
         sinceJ++;
         sinceZ++;
         sinceT++;
         sinceI++;
      }
      else
      {
         currentPiece = gui.easty.bumpNext(new ImageIcon("fullPieceI.png"));
         sinceL++;
         sinceO++;
         sinceJ++;
         sinceZ++;
         sinceS++;
         sinceT++;
      }

      //setting current piece
      if(currentPiece == 0)
      {
         falling = new TetrisBlockT(this);
         sinceT = 0;
      }
      else if(currentPiece == 1)
      {
         falling = new TetrisBlockL(this); 
         sinceL = 0;
      }
      else if(currentPiece == 2)
      {
         falling = new TetrisBlockO(this);
         sinceO = 0;
      }
      else if(currentPiece == 3)
      {
         falling = new TetrisBlockJ(this);
         sinceJ = 0;
      }
      else if(currentPiece == 4)
      {
         falling = new TetrisBlockZ(this);
         sinceZ = 0;
      }
      else if(currentPiece == 5)
      {
         falling = new TetrisBlockS(this);
         sinceS = 0;
      }
      else if(currentPiece == 6)
      {
         falling = new TetrisBlockI(this);
         sinceI = 0;
      }
      
      animationObjects.add(falling);
   }
   
   public void startLand() {
      addLandDelay = true;
   }
   
   //instance methods
   

   
   
   //private classes
   private class AnimationListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)  //Gets called over and over by the Timer
      {
         animate();
      }
   }
   
   private class MovementListener implements MouseListener
   {
      //NOT WORKING. IT CANT TELL WHAT PIECE IS IN THE MOUSE INPUTTED SPOT
      @Override
      public void mouseClicked(MouseEvent e) {
         // Get the mouse click coordinates relative to the subpanel
         int xValue = e.getX();
         int yValue = e.getY();
         
         // Calculate the grid square
         int xSpace = xValue / SQUARE;
         int ySpace = yValue / SQUARE;

         String type = "";
         if(placedSquares[ySpace][xSpace])
         {
            for(Block block : fallenBlocks)
            {
               if(block.getX()/SQUARE == xSpace && block.getY()/SQUARE == ySpace){
                  type = block.getType();
                  break;
               }
            }
            gui.westy.printDescription(type);
         }
      }
      @Override
      public void mousePressed(MouseEvent e) {}
      @Override
      public void mouseReleased(MouseEvent e) {}
      @Override
      public void mouseEntered(MouseEvent e) {}
      @Override
      public void mouseExited(MouseEvent e) {}
      
   }
   private class Key extends KeyAdapter //Make ONE class that EXTENDS KeyAdapter, and tell it what to do when keys are pressed or released
   {
      public void keyPressed(KeyEvent e) //Make ONE method for key presses; this is overridden, and will be called whenever a key is pressed
      {
         if (!timeStop) {
         if(e.getKeyCode() == KeyEvent.VK_LEFT) //e.getKeyCode() lets us retrieve which key was just pushed.  !left lets us know the user isn't already holding the left arrow down.
         {
            falling.moveLeft();  //Subtract 2 from Square's dX value, effectively setting the value to 0.
         }
         if(e.getKeyCode() == KeyEvent.VK_RIGHT) //e.getKeyCode() lets us retrieve which key was just pushed.  !left lets us know the user isn't already holding the left arrow down.
         { 
            falling.moveRight();
         }
         if(e.getKeyCode() == KeyEvent.VK_DOWN) //e.getKeyCode() lets us retrieve which key was just pushed.  !left lets us know the user isn't already holding the left arrow down.
         {
            if (falling.canMoveDown()) {
               spun = false;
            }
            falling.softDrop();
         }
         if(e.getKeyCode() == KeyEvent.VK_UP && !up)
         {
            if (falling.canMoveDown()) {
               spun = false;
            }
            falling.hardDrop();
            up = true;
         }
         if(e.getKeyCode() == KeyEvent.VK_W && !w) {
               falling.rotate(true);
               w = true;
               spun = true;
         }
         if(e.getKeyCode() == KeyEvent.VK_A && !a) {
            falling.rotate(false);
            a = true;
            spun = true;
         }
         if(e.getKeyCode() == KeyEvent.VK_ESCAPE && !esc) {
            gui.pause();
            timeStop = true;
         }
         if(e.getKeyCode() == KeyEvent.VK_SPACE && !space) {
            firstHoldHasHappened = true;
            if (!usedHold) {
               holdPiece(falling); 
               usedHold = true;
            }
               deletePiece(falling);
            gui.westy.loadHold();
            
         }
      }
      }
      public void keyReleased(KeyEvent e) //Also overridden; ONE method that will be called any time a key is released
      {
         if(e.getKeyCode() == KeyEvent.VK_UP) {
            up = false;
         }
         if(e.getKeyCode() == KeyEvent.VK_W) {
            w = false;
         }
         if(e.getKeyCode() == KeyEvent.VK_A) {
            a = false;
         }
         if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            esc = false;
         }
         if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            space = false;
         }
      }
   
   } 
   @Override
    public void addNotify() {
        super.addNotify();
        requestFocusInWindow();
    }  
}