import javax.swing.*;
import java.awt.*;

//Rq class overview:
//This class has information for each tetris piece (some parts tbd)
/////////////////////////////////////////////////////////////
//Fields:
//-Image: There are image icons of each piece. The entir piece, not a block
//-Type: type is just what kind of piece it is (Ex. T, L, J)
///////////////////////////////////////////////////////////
//Methods
//-drawMe: draws the image.
//-getPieceDescription: returns a string about the piece.
//////////////////////////////////////////////////////////////////
//fin. 
//-Jada

public class TetrisPiece {
    private ImageIcon myImage;
    private String myType;
    public TetrisPiece(String type)
    {
        myType = type;

        if(myType == "T")
        {
            myImage = new ImageIcon("fullPieceT.png");
        }
        else if(myType == "J")
        {
            myImage = new ImageIcon("fullPieceJ.png");;
        }
        else if(myType == "L")
        {
            myImage = new ImageIcon("fullPieceL.png");;
        }
        else if (myType == "O")
        {
            myImage = new ImageIcon("fullPieceO.png");;
        }
        else if(myType == "I")
        {
            myImage = new ImageIcon("fullPieceI.png");;
        }
        else if(myType == "S")
        {
            myImage = new ImageIcon("fullPieceS.png");;
        }
        else if(myType == "Z")
        {
            myImage = new ImageIcon("fullPieceZ.png");;
        }
    }
    public void drawMe(Graphics g)
    {
        g.drawImage(myImage.getImage(), 0, TetrisPanel.HEIGHT/4, TetrisPanel.WIDTH*2, TetrisPanel.HEIGHT, null);

    }
    public String getPieceDescription()
    {
        if(myType == "t")
        {
            return "This is a T piece.";
        }
        else if(myType == "j")
        {
            return "This is a J piece.";
        }
        else if(myType == "l")
        {
            return "This is a L piece.";
        }
        else if (myType == "o")
        {
            return "This is a O piece.";
        }
        else if(myType == "i")
        {
            return "This is a I piece."; 
        }
        else if(myType == "s")
        {
            return "This is a S piece.";
        }
        else if(myType == "z")
        {
            return "This is a Z piece.";
        }
        else
        {
            return "N/A";
        }
    }
}
