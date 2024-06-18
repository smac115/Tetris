import javax.swing.*;

public class GuiPanel extends JPanel
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame("GUI with BorderLayout");
      frame.setSize(600, 600);
      frame.setLocation(20, 20);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(new PanelSwapPanel(frame));
      frame.pack();
      frame.setVisible(true);
   }
}
