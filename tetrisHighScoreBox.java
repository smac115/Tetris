import javax.swing.*;
import java.awt.*;
public class tetrisHighScoreBox {

   public tetrisHighScoreBox()
   {
   }
   public void drawMe(Graphics g)
   {
      ImageIcon coffee = new ImageIcon("highScoreBox.png");
      g.drawImage(coffee.getImage(), 0, 0, 250, 200,null);
   }
}