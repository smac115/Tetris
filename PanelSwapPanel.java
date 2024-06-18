import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanelSwapPanel extends JPanel
{
   private JFrame myOwner;  //The JFrame that contains this panel (!)

   private loadingScreen ls;  //A subpanel that will get swapped out for...
   public SubpanelGUIPanel tetris;  
   private EndScreen es;
   private PausePanel pause;
   private ScorePanel highScore;

   
   public PanelSwapPanel(JFrame f)
   {
      //setPreferredSize(new Dimension(TetrisPanel.WIDTH,TetrisPanel.HEIGHT));
      myOwner = f;  //Store a reference to the JFrame that I belong to (!)
      setLayout(new BorderLayout());
      //Make and add first subpanel
      ls = new loadingScreen(this);  //Pass **MYSELF** to AddPanel (!)
      add(ls,BorderLayout.CENTER);
      
      tetris = new SubpanelGUIPanel(this);
      tetris.tetris.stopTime();
      pause = new PausePanel(this);
      highScore = new ScorePanel(this);
      es = new EndScreen(this);
      repaint();
      revalidate();
      myOwner.pack();
      //We **don't** add results!  Just make sure it's ready.
   }
   public void switchToTetrisPanelFromLoadingScreen()
   {
      System.out.println("Switched panels");
      //All of these commands are necessary, in this order, to remove a subpanel,
      //add another one, then cause the JFrame to figure itself out again, including
      //resizing if necessary.  If you leave something out, you'll get weird behavior.
      remove(ls);
      add(tetris,BorderLayout.CENTER);
      tetris.tetris.resume();
      repaint();
      revalidate();
      myOwner.pack();  //Again, note - I'm giving the JFrame that contains this panel a command! (!)

      // Ensuring focus request occurs after component hierarchy update
      SwingUtilities.invokeLater(() -> tetris.requestTetrisFocus());
      //takes the function as a parameter... arrow thing?
   }
   public void switchToEndScreenFromTetrisPanel()//goes to end
   {
      System.out.println("Switched panels again");
      remove(tetris);
      es.updateScore();
      es.compareScore(tetris.tetris.getScore());
      add(es,BorderLayout.CENTER);
      repaint();
      revalidate();
      myOwner.pack();  //Again, note - I'm giving the JFrame that contains this panel a command! (!)

   }
   public void switchToPauseFromTetrisPanel()
   {
      remove(tetris);
      add(pause,BorderLayout.CENTER);
      repaint();
      revalidate();
      myOwner.pack();
   }
   public void switchToGameFromPause()
   {
      remove(pause);
      add(tetris,BorderLayout.CENTER);
      
      repaint();
      revalidate();
      myOwner.pack();
   }
   public void switchToPauseFromHighScore()
   {
      remove(highScore);
      add(pause,BorderLayout.CENTER);
      
      repaint();
      revalidate();
      myOwner.pack();
   }
   public void switchToHighScoreFromPause()
   {
      remove(pause);
      add(highScore,BorderLayout.CENTER);
      
      repaint();
      revalidate();
      myOwner.pack();
   }

}
class ScorePanel extends JPanel{
   private PanelSwapPanel myOwner;
   private highScoreReader hsR;
   public ScorePanel(PanelSwapPanel p)
   {
      myOwner = p;  //Store a reference to the PanelSwapPanel that I belong to (!)
      setPreferredSize(new Dimension(500, 450)); //Set size here.
      setLayout(new BorderLayout());  //Rows then columns
       
      JLabel scoreLabel = new JLabel("Here are the top 10 scores");
      scoreLabel.setFont(new Font("Sans Serif", Font.BOLD, 24));
      scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
      add(scoreLabel, BorderLayout.NORTH);

      JPanel scores = new JPanel();
      scores.setLayout(new GridLayout(11, 3));
      JLabel nameLabel = new JLabel("Name");
      nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
      scores.add(nameLabel);
      JLabel scoreLabelInScorePanel = new JLabel("Score");
      scoreLabelInScorePanel.setHorizontalAlignment(SwingConstants.CENTER);
      scores.add(scoreLabelInScorePanel);
      JLabel dateLabel = new JLabel("Date achieved");
      dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
      scores.add(dateLabel);

      hsR = new highScoreReader("highScores.txt");

      for(int i = 0;i<10;i++)
      {
         scores.add(new JLabel(hsR.getNames()[i]));
         scores.add(new JLabel(String.valueOf(hsR.getScores()[i])));
         scores.add(new JLabel(hsR.getDates()[i]));
      }

      add(scores, BorderLayout.CENTER);

      JPanel buttons = new JPanel();
      buttons.setLayout(new GridLayout(1,1));

      JButton goToGame = new JButton("Back to Pause");
      goToGame.addActionListener(new toPauseListener());
      buttons.add(goToGame);
      add(buttons,BorderLayout.SOUTH);//r2 col2

   }
   
  private class toPauseListener implements ActionListener
  {
     public void actionPerformed(ActionEvent e)
     {
         myOwner.switchToPauseFromHighScore();
     }
  }
   
}

class PausePanel extends JPanel{
   private PanelSwapPanel myOwner;
   private tetrisPauseScreen pS;
   private tetrisWhiteScreen wS;
   private String score;
   
   public PausePanel(PanelSwapPanel p)
  {
      score = Integer.toString(0);
      myOwner = p;  //Store a reference to the PanelSwapPanel that I belong to (!)
      setPreferredSize(new Dimension(TetrisPanel.WIDTH*2, TetrisPanel.HEIGHT)); //Set size here.
      setLayout(new BorderLayout());  //Rows then columns
       
      pS = new tetrisPauseScreen();
      wS = new tetrisWhiteScreen();

      JPanel buttons = new JPanel();
      buttons.setLayout(new GridLayout(1,2));
      JButton goHighscore = new JButton("Highscore");
      goHighscore.addActionListener(new highScoreListener());
      buttons.add(goHighscore);//r2 col2

      JButton goToGame = new JButton("Play");
      goToGame.addActionListener(new toGameListener());
      buttons.add(goToGame);
      
      add(buttons,BorderLayout.SOUTH);//r2 col2

      JPanel centerPart = new JPanel();
      centerPart.setLayout(new GridLayout(2,1));
      JLabel title = new JLabel("Pause");
      title.setFont(new Font("Serif", Font.BOLD, 30));
      title.setHorizontalAlignment(SwingConstants.CENTER);
      centerPart.add(title);
      JLabel scoreLabel = new JLabel("Current Score: " + score);
      scoreLabel.setFont(new Font("Serif", Font.BOLD, 20));
      scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);

      //add(centerPart);
   }
   
   public void paintComponent(Graphics g)
   {
      pS.drawMe(g);
      wS.drawMe(g);
   }
   private class highScoreListener implements ActionListener
  {
     public void actionPerformed(ActionEvent e)
     {
        myOwner.switchToHighScoreFromPause();
     }
  }
  private class toGameListener implements ActionListener
  {
     public void actionPerformed(ActionEvent e)
     {
        myOwner.switchToGameFromPause();
        myOwner.tetris.tetris.resume();
     }
  }
   
}
class loadingScreen extends JPanel{
   private PanelSwapPanel myOwner;
   private tetrisHomescreen tH;
   
   public loadingScreen(PanelSwapPanel p)
   {
      myOwner = p;  //Store a reference to the PanelSwapPanel that I belong to (!)
      setPreferredSize(new Dimension(TetrisPanel.WIDTH*2, TetrisPanel.HEIGHT)); //Set size here.
      setLayout(new BorderLayout());  //Rows then columns
       
      tH = new tetrisHomescreen();
       
      JButton goGame = new JButton();
      goGame.setIcon(new ImageIcon("playButton.png"));
      goGame.addActionListener(new goGameListener());
      add(goGame,BorderLayout.SOUTH);
   }
   public void paintComponent(Graphics g)
   {
      tH.drawMe(g);
   }
   private class goGameListener implements ActionListener
  {
     public void actionPerformed(ActionEvent e)
     {
        myOwner.switchToTetrisPanelFromLoadingScreen();
     }
  }
}
class EndScreen extends JPanel
{
   private PanelSwapPanel myOwner;
   private JLabel myScore;
   private JLabel comparison;
   private int score;
   
   public EndScreen(PanelSwapPanel p)
   {
      myOwner = p;  //Store a reference to the PanelSwapPanel that I belong to (!)
      setPreferredSize(new Dimension(TetrisPanel.WIDTH, TetrisPanel.HEIGHT / 2)); //Set size here.
      setLayout(new GridLayout(3, 1));  //Rows then columns
      
      JLabel gameOver = new JLabel("Game Over!");
      gameOver.setFont(new Font("Serif", Font.BOLD, 20));
      gameOver.setHorizontalAlignment(SwingConstants.CENTER);
      add(gameOver);
      myScore = new JLabel("Your score was: " + myOwner.tetris.tetris.getScore());
      myScore.setFont(new Font("Serif", Font.BOLD, 20));
      myScore.setHorizontalAlignment(SwingConstants.CENTER);
      add(myScore);
      comparison = new JLabel("");
      comparison.setFont(new Font("Serif", Font.BOLD, 20));
      comparison.setHorizontalAlignment(SwingConstants.CENTER);
      add(comparison);
      
   }
   public void updateScore() {
      score = myOwner.tetris.tetris.getScore();
      myScore.setText("Your score was: " + score);
   }
   public void compareScore(int score) {
      try {
         highScoreReader pastScores = new highScoreReader("highScores.txt");
         int[] scores = pastScores.getScores();
         for (int i = 0; i < 10; i++) {
            if (score > scores[i]) {
               int position = i;
               String myName = JOptionPane.showInputDialog("Your highscore made the top 10, enter a name:");
               String myDate = JOptionPane.showInputDialog("Enter today's date:");
               pastScores.bumpScores(position, myName, score, myDate);
               comparison.setText("Your score made the top 10!");
               pastScores.updateFile();
               return;
            }
         }
         comparison.setText("Your score did not make the top 10. Skill issue.");

      } catch (Exception e) {
         System.out.println("Failed to load file " + e);
      }
   }
}