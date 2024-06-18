import javax.swing.*;
import java.awt.*;
public class tetrisHomescreen {

   public tetrisHomescreen()
   {
   }
   public void drawMe(Graphics g)
   {
      ImageIcon coffee = new ImageIcon("tetrisHomescreen.png");
      g.drawImage(coffee.getImage(), 0, 0, TetrisPanel.WIDTH*2, TetrisPanel.HEIGHT, null);
   }

}
