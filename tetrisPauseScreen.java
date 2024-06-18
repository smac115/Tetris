import javax.swing.*;
import java.awt.*;
public class tetrisPauseScreen {
    

   public tetrisPauseScreen()
   {
   }
   public void drawMe(Graphics g)
   {
      ImageIcon coffee = new ImageIcon("pauseScreenThing.png");
      g.drawImage(coffee.getImage(), 0, 0, TetrisPanel.WIDTH*2, TetrisPanel.HEIGHT/4, null);
   }

}

