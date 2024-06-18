import javax.swing.*;
import java.awt.*;

public class SubpanelGUIPanel extends JPanel {
    JLabel number;  
    JTextField userIn;  //One new field, also because an action listener will need to access it.
    int num;
    public TetrisPanel tetris;
    private PanelSwapPanel myOwner;
   public westSubpanel westy;
   public eastSubpanel easty;
    public SubpanelGUIPanel(PanelSwapPanel p)
    {
      myOwner = p;
      setPreferredSize(new Dimension(TetrisPanel.WIDTH*2,TetrisPanel.HEIGHT));
      setLayout(new BorderLayout());
   
      tetris = new TetrisPanel(this);
      repaint();
      revalidate();
      add(tetris, BorderLayout.CENTER);

      easty = new eastSubpanel(this);
      repaint();
      revalidate();  
      add(easty, BorderLayout.EAST);

      westy = new westSubpanel(tetris);
      repaint();
      revalidate();
      add(westy,BorderLayout.WEST);

      

      
   }
   public void endGame()
   {
      myOwner.switchToEndScreenFromTetrisPanel();
   }
   
   public void requestTetrisFocus() {
      tetris.requestFocusInWindow();
  }
  public void pause()
  {
      myOwner.switchToPauseFromTetrisPanel();
  }
   
   
   
}
class westSubpanel extends JPanel
{
   private String[] fileNames;
   private JLabel holdLabel;
   private JLabel holdPiece;

   private JLabel pieceDescription;
   private TetrisPanel tetris;

   
   public westSubpanel(TetrisPanel t)
   {
      tetris = t;
      fileNames = new String[7];
      fileNames[0] = "fullPieceT.png";
      fileNames[1] = "fullPieceL.png";
      fileNames[2] = "fullPieceO.png";
      fileNames[3] = "fullPieceJ.png";
      fileNames[4] = "fullPieceZ.png";
      fileNames[5] = "fullPieceS.png";
      fileNames[6] = "fullPieceI.png";

      setLayout(new GridLayout(3, 1));
      setPreferredSize(new Dimension(TetrisPanel.WIDTH/2, TetrisPanel.HEIGHT));
      setBackground(Color.BLACK);
      setOpaque(true);

      holdLabel = new JLabel("Hold");
      holdLabel.setForeground(Color.WHITE);
      holdLabel.setFont(new Font("Monospace", Font.BOLD, 30));
      add(holdLabel);

      holdPiece = new JLabel(new ImageIcon("fullPieceBlank.png"));
      
      add(holdPiece);

      pieceDescription = new JLabel("");
      pieceDescription.setForeground(Color.WHITE);
      pieceDescription.setFont(new Font("Monospace",Font.BOLD,10));
      add(pieceDescription);
   }
   public void loadHold()
   {
      //0 is t
      //1 is l
      //2 is o
      //3 is J
      //4 is Z
      //5 is S
      //6 is I
      int holdNumber = tetris.getHoldPiece();
      String file = fileNames[holdNumber];
      holdPiece.setIcon(new ImageIcon(file));
      add(holdPiece,1);
   }
   public void printDescription(String type)
   {
      TetrisPiece a = new TetrisPiece(type);
      pieceDescription.setText(a.getPieceDescription());
      add(pieceDescription,2);
   }
}
class eastSubpanel extends JPanel
{
   private JLabel nextLabel;
   private JLabel next1;
   private JLabel next2;
   private JLabel next3;
   private JLabel next4;
   private JLabel next5;
   private JLabel scoreLabel;
   private JLabel myScore;
   private String[] fileNames;
   private SubpanelGUIPanel owner;
   public eastSubpanel(SubpanelGUIPanel myOwner)
   {
      owner = myOwner;
      fileNames = new String[7];
      fileNames[0] = "fullPieceI.png";
      fileNames[1] = "fullPieceJ.png";
      fileNames[2] = "fullPieceL.png";
      fileNames[3] = "fullPieceO.png";
      fileNames[4] = "fullPieceS.png";
      fileNames[5] = "fullPieceZ.png";
      fileNames[6] = "fullPieceT.png";

      setLayout(new GridLayout(8, 1));
      setPreferredSize(new Dimension(TetrisPanel.WIDTH/2, TetrisPanel.HEIGHT));
      setBackground(Color.BLACK);
      setOpaque(true);

      nextLabel = new JLabel("Next");
      nextLabel.setForeground(Color.WHITE);
      nextLabel.setFont(new Font("Monospace", Font.BOLD, 30));
      add(nextLabel);
      loadNext();
      scoreLabel = new JLabel("Score");
      scoreLabel.setForeground(Color.WHITE);
      scoreLabel.setFont(new Font("Monospace", Font.BOLD, 30));
      add(scoreLabel);
      myScore = new JLabel("0");
      myScore.setForeground(Color.WHITE);
      myScore.setFont(new Font("Monospace", Font.BOLD, 30));
      add(myScore);
   }
   public void loadNext() {
      int[] pieces = new int[5];
      for (int i = 0; i < 5; i++) {
         int randomPiece = (int)(Math.random() * 7);
         pieces[i] = randomPiece;
         if(randomPiece == 0)
         {
            owner.tetris.addSinceL();
            owner.tetris.addSinceO();
            owner.tetris.addSinceJ();
            owner.tetris.addSinceZ();
            owner.tetris.addSinceS();
            owner.tetris.addSinceI();
         }
      else if(randomPiece == 1)
      {
         owner.tetris.addSinceT();
         owner.tetris.addSinceO();
         owner.tetris.addSinceJ();
         owner.tetris.addSinceZ();
         owner.tetris.addSinceS();
         owner.tetris.addSinceI();
      }
      else if(randomPiece == 2)
      {
         owner.tetris.addSinceL();
         owner.tetris.addSinceT();
         owner.tetris.addSinceJ();
         owner.tetris.addSinceZ();
         owner.tetris.addSinceS();
         owner.tetris.addSinceI();
      }
      else if(randomPiece == 3)
      
      {
         owner.tetris.addSinceL();
         owner.tetris.addSinceO();
         owner.tetris.addSinceT();
         owner.tetris.addSinceZ();
         owner.tetris.addSinceS();
         owner.tetris.addSinceI();
      }
      else if(randomPiece == 4)
      {
         owner.tetris.addSinceL();
         owner.tetris.addSinceO();
         owner.tetris.addSinceJ();
         owner.tetris.addSinceT();
         owner.tetris.addSinceS();
         owner.tetris.addSinceI();
      }
      else if(randomPiece == 5)
      {
         owner.tetris.addSinceL();
         owner.tetris.addSinceO();
         owner.tetris.addSinceJ();
         owner.tetris.addSinceZ();
         owner.tetris.addSinceT();
         owner.tetris.addSinceI();
      }
      else
      {
         owner.tetris.addSinceL();
         owner.tetris.addSinceO();
         owner.tetris.addSinceJ();
         owner.tetris.addSinceZ();
         owner.tetris.addSinceS();
         owner.tetris.addSinceT();
      }
         
      }
      next1 = new JLabel(new ImageIcon(fileNames[pieces[0]],fileNames[pieces[0]]));
      next2 = new JLabel(new ImageIcon(fileNames[pieces[1]],fileNames[pieces[1]]));
      next3 = new JLabel(new ImageIcon(fileNames[pieces[2]],fileNames[pieces[2]]));
      next4 = new JLabel(new ImageIcon(fileNames[pieces[3]],fileNames[pieces[3]]));
      next5 = new JLabel(new ImageIcon(fileNames[pieces[4]],fileNames[pieces[4]]));
      add(next1); add(next2); add(next3); add(next4); add(next5); 
   }
   public int bumpNext(ImageIcon newNext) {
      String onDeck = next1.getIcon().toString();
      next1.setIcon(next2.getIcon());
      next2.setIcon(next3.getIcon());
      next3.setIcon(next4.getIcon());
      next4.setIcon(next5.getIcon());
      next5.setIcon(newNext);
      //returns what the piece that should show right now is...
      if(onDeck == "fullPieceT.png")
      {
         return 0;
      }
      else if(onDeck == "fullPieceL.png")
      {
         return 1; 
      }
      else if(onDeck == "fullPieceO.png")
      {
         return 2;
      }
      else if(onDeck == "fullPieceJ.png")
      {
         return 3;
      }
      else if(onDeck == "fullPieceZ.png")
      {
         return 4;
      }
      else if(onDeck == "fullPieceS.png")
      {
         return 5;
      }
      else 
      {
         return 6;
      }
   }
   public void setScore(int score) {
      myScore.setText("" + score);
   }
}