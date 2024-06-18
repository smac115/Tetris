import javax.swing.*;
import java.awt.*;
public class tetrisHoldBox {
    
    private int x;
    private int y;
    public tetrisHoldBox(int thisx, int thisy)
    {
        x = thisx;
        y = thisy;
    }
    public void drawMe(Graphics g)
    {
      ImageIcon coffee = new ImageIcon("holdBox.png");
      g.drawImage(coffee.getImage(), x, y, 130, 117, null);
    }

}
