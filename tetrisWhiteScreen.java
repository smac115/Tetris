import javax.swing.*;
import java.awt.*;
public class tetrisWhiteScreen {
    

   public tetrisWhiteScreen()
   {
   }
   public void drawMe(Graphics g)
   {
      ImageIcon coffee = new ImageIcon("whiteScreen.png");
      g.drawImage(coffee.getImage(), 0, TetrisPanel.HEIGHT/4, TetrisPanel.WIDTH*2, TetrisPanel.HEIGHT, null);
   }

}

