import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    public static int[] controls;
    private String function;
    private PanelSwapPanel myOwner;
    JLabel[] controlLabels;
    JButton[] controlButtons;

    public ControlPanel(PanelSwapPanel p) {
        setPreferredSize(new Dimension(400, 800));
        myOwner = p;
        addKeyListener(new Key());
        setFocusable(true);
        //in order, hard drop, soft drop, move right, move left, rotate right, rotate left, hold
        int[] myControls = {KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT, KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_SPACE};
        controls = myControls;
        controlLabels = new JLabel[7];
        controlButtons = new JButton[7];

        function = null;
        function = "";
        setLayout(new GridLayout(3, 1));
        JLabel directions = new JLabel("Click a button to choose the control to change, then press the desired key");
        directions.setFont(new Font("Sans Serif", Font.BOLD, 18));
        add(directions);

        //labels showing what key the function is set to
        JPanel labels = new JPanel();
        labels.setLayout(new GridLayout(1, 7));
        JLabel hardDrop = new JLabel(KeyEvent.getKeyText(KeyEvent.VK_UP));
        labels.add(hardDrop);
        controlLabels[0] = hardDrop;
        JLabel softDrop = new JLabel(KeyEvent.getKeyText(KeyEvent.VK_DOWN));
        labels.add(softDrop);
        controlLabels[1] = softDrop;
        JLabel moveRight = new JLabel(KeyEvent.getKeyText(KeyEvent.VK_RIGHT));
        labels.add(moveRight);
        controlLabels[2] = moveRight;
        JLabel moveLeft = new JLabel(KeyEvent.getKeyText(KeyEvent.VK_LEFT));
        labels.add(moveLeft);
        controlLabels[3] = moveLeft;
        JLabel rotateRight = new JLabel(KeyEvent.getKeyText(KeyEvent.VK_W));
        labels.add(rotateRight);
        controlLabels[4] = rotateRight;
        JLabel rotateLeft = new JLabel(KeyEvent.getKeyText(KeyEvent.VK_A));
        labels.add(rotateLeft);
        controlLabels[5] = rotateLeft;
        JLabel hold = new JLabel(KeyEvent.getKeyText(KeyEvent.VK_SPACE));
        labels.add(hold);
        controlLabels[6] = hold;
        add(labels);

        //buttons that display the function and allow the player to change the key
        JPanel buttons = new JPanel();
        JButton hardDropButton = new JButton("Hard Drop");
        buttons.add(hardDropButton);
        controlButtons[0] = hardDropButton;
        JButton softDropButton = new JButton("Soft Drop");
        buttons.add(softDropButton);
        controlButtons[1] = softDropButton;
        JButton moveRightButton = new JButton("Move Right");
        buttons.add(moveRightButton);
        controlButtons[2] = moveRightButton;
        JButton moveLeftButton = new JButton("Move Left");
        buttons.add(moveLeftButton);
        controlButtons[3] = moveLeftButton;
        JButton rotateRightButton = new JButton("Rotate Right");
        buttons.add(rotateRightButton);
        controlButtons[4] = rotateRightButton;
        JButton rotateLeftButton = new JButton("Rotate Left");
        buttons.add(rotateLeftButton);
        controlButtons[5] = rotateLeftButton;
        JButton holdButton = new JButton("Hold");
        buttons.add(holdButton);
        controlButtons[6] = holdButton;
        add(buttons);

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
    private class Key extends KeyAdapter //Make ONE class that EXTENDS KeyAdapter, and tell it what to do when keys are pressed or released
    {
        public void keyPressed(KeyEvent e) //Make ONE method for key presses; this is overridden, and will be called whenever a key is pressed
        {
        }
        public void keyReleased(KeyEvent e) //Also overridden; ONE method that will be called any time a key is released
        {
        }
   } 
}
