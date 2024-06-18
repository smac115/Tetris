import javax.swing.*;
import java.awt.*;

//This makes an object... a Block (it gets kinda complicated but keep that in mind)
//Fields
//-blockImage: this is an image icon of the color block that the piece needs (Ex. the T block has purple)
//-location: This is a coordinate (see coordinate class for more)
//-dX: changes the blocks location by this factor
//Methods
//-step: we know this...
//-drawMe: we know this...
////////////////////////////////////////////
//fin.
//Jada

public class Block implements Animatable
{
   private ImageIcon blockImage;
   private Coordinate location;
   private String type;
   private int dX;

//constructors
   public Block(ImageIcon myBlockImage, Coordinate myCoordinate)
   {
      blockImage = myBlockImage;
      location = myCoordinate;
      if(myBlockImage.toString() == "imageI.jpg"){
         type = "i";
      }
      else if(myBlockImage.toString() == "imageL.jpg"){
         type = "l";
      }
      else if(myBlockImage.toString() == "imageJ.jpg"){
         type = "j";
      }
      else if(myBlockImage.toString() == "imageZ.jpg"){
         type = "z";
      }
      else if(myBlockImage.toString() == "imageS.jpg"){
         type = "s";
      }
      else if(myBlockImage.toString() == "imageO.jpg"){
         type = "o";
      }
      else if(myBlockImage.toString() == "imageT.jpg"){
         type = "t";
      }

      dX = 0;
   }
//accessors
   public ImageIcon getImage(){return blockImage;}
   public Coordinate getLocation(){return location;}
   public int getDX(){return dX;}
   public int getX(){return location.getX();}
   public int getY(){return location.getY();}

   public String getType(){System.out.println(type);return type;}
//modifiers
   public void setDX(int dXvalue){dX = dXvalue;}
   public void setLocation(Coordinate newLocation) {location = newLocation;}
   public void setImage(ImageIcon myImage){blockImage = myImage;}
   public void setY(int myY){location.setY(myY);}

//methods
   public void step() {
      location.setCoord(location.getX()+dX,location.getY());
   }

   public void drawMe(Graphics g)
   {
      g.drawImage(blockImage.getImage(), location.getX() , location.getY(), TetrisPanel.SQUARE, TetrisPanel.SQUARE, null);
   }
}