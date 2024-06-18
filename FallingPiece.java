import javax.swing.*;
import java.awt.*;
//Falling piece: In a wrap? An abstract class with methods necessary for every piece that falls

//Fields
//-blocks: a block array that stores the four blocks needed for each tetris piece
//-coord: a Coordinate array that stores the four coordinates for each block
//-owner: the Tetris panel

//Constructor
//initiates the blocks used and the coordinates of those blocks. This changes depending on the type of 
//tetris piece

//Methods
//-Abstract buddies: 
public abstract class FallingPiece implements Animatable {
    private Block[] blocks = new Block[4];
    private Coordinate[] coords = new Coordinate[4];
    private TetrisPanel owner;
    private String type;

    public FallingPiece(String myType, TetrisPanel myOwner){
        type = myType;
        owner = myOwner;
        if (myType == "i") {
            ImageIcon block = new ImageIcon("imageI.jpg","imageI.jpg");
            Coordinate coo1 = new Coordinate(TetrisPanel.SQUARE * 3,TetrisPanel.SQUARE);
            Coordinate coo2 = new Coordinate(TetrisPanel.SQUARE * 4,TetrisPanel.SQUARE);
            Coordinate coo3 = new Coordinate(TetrisPanel.SQUARE * 5,TetrisPanel.SQUARE);
            Coordinate coo4 = new Coordinate(TetrisPanel.SQUARE * 6,TetrisPanel.SQUARE);
    
            blocks[0] = new Block(block, coo1);
            blocks[1] = new Block(block, coo2);
            blocks[2] = new Block(block, coo4);
            blocks[3] = new Block(block, coo3);
        } 
        else if (myType == "t") {
            ImageIcon block = new ImageIcon("imageT.jpg","imageT.jpg");
            Coordinate coo1 = new Coordinate(TetrisPanel.SQUARE * 4,TetrisPanel.SQUARE);
            Coordinate coo2 = new Coordinate(TetrisPanel.SQUARE * 5,TetrisPanel.SQUARE); //Centerpiece
            Coordinate coo3 = new Coordinate(TetrisPanel.SQUARE * 6,TetrisPanel.SQUARE);
            Coordinate coo4 = new Coordinate(TetrisPanel.SQUARE * 5,0);
    
            blocks[0] = new Block(block, coo1);
            blocks[1] = new Block(block, coo4);
            blocks[2] = new Block(block, coo3);
            blocks[3] = new Block(block, coo2);
        }
        else if (myType == "j") {
            ImageIcon block = new ImageIcon("imageJ.jpg","imageJ.jpg");
            Coordinate coo1 = new Coordinate(TetrisPanel.SQUARE * 4, 0);
            Coordinate coo2 = new Coordinate(TetrisPanel.SQUARE * 5, TetrisPanel.SQUARE); //Centerpiece
            Coordinate coo3 = new Coordinate(TetrisPanel.SQUARE * 4, TetrisPanel.SQUARE);
            Coordinate coo4 = new Coordinate(TetrisPanel.SQUARE * 6, TetrisPanel.SQUARE);

            blocks[0] = new Block(block, coo1);
            blocks[1] = new Block(block, coo4);
            blocks[2] = new Block(block, coo3);
            blocks[3] = new Block(block, coo2);
        }
        else if (myType == "l") {
            ImageIcon block = new ImageIcon("imageL.jpg","imageL.jpg");
            Coordinate coo1 = new Coordinate(TetrisPanel.SQUARE * 6, 0);
            Coordinate coo2 = new Coordinate(TetrisPanel.SQUARE * 5, TetrisPanel.SQUARE); //Centerpiece
            Coordinate coo3 = new Coordinate(TetrisPanel.SQUARE * 6, TetrisPanel.SQUARE);
            Coordinate coo4 = new Coordinate(TetrisPanel.SQUARE * 4, TetrisPanel.SQUARE);

            blocks[0] = new Block(block, coo1);
            blocks[1] = new Block(block, coo4);
            blocks[2] = new Block(block, coo3);
            blocks[3] = new Block(block, coo2);
        }
        else if (myType == "o") {
            ImageIcon block = new ImageIcon("imageO.jpg","imageO.jpg");
            //no centerpiece
            Coordinate coo1 = new Coordinate(TetrisPanel.SQUARE * 4, 0);
            Coordinate coo2 = new Coordinate(TetrisPanel.SQUARE * 5, 0); 
            Coordinate coo3 = new Coordinate(TetrisPanel.SQUARE * 4, TetrisPanel.SQUARE);
            Coordinate coo4 = new Coordinate(TetrisPanel.SQUARE * 5, TetrisPanel.SQUARE);

            blocks[0] = new Block(block, coo1);
            blocks[1] = new Block(block, coo4);
            blocks[2] = new Block(block, coo3);
            blocks[3] = new Block(block, coo2);
        }
        else if (myType == "s") {
            ImageIcon block = new ImageIcon("imageS.jpg","imageS.jpg");

            Coordinate coo1 = new Coordinate(TetrisPanel.SQUARE * 4, TetrisPanel.SQUARE);
            Coordinate coo2 = new Coordinate(TetrisPanel.SQUARE * 5, TetrisPanel.SQUARE); //centerpiece
            Coordinate coo3 = new Coordinate(TetrisPanel.SQUARE * 5, 0);
            Coordinate coo4 = new Coordinate(TetrisPanel.SQUARE * 6, 0);

            blocks[0] = new Block(block, coo1);
            blocks[1] = new Block(block, coo4);
            blocks[2] = new Block(block, coo3);
            blocks[3] = new Block(block, coo2);
        }
        else if (myType == "z") {
            ImageIcon block = new ImageIcon("imageZ.jpg","imageZ.jpg");

            Coordinate coo1 = new Coordinate(TetrisPanel.SQUARE * 4, 0);
            Coordinate coo2 = new Coordinate(TetrisPanel.SQUARE * 5, TetrisPanel.SQUARE); //centerpiece
            Coordinate coo3 = new Coordinate(TetrisPanel.SQUARE * 5, 0);
            Coordinate coo4 = new Coordinate(TetrisPanel.SQUARE * 6, TetrisPanel.SQUARE);

            blocks[0] = new Block(block, coo1);
            blocks[1] = new Block(block, coo4);
            blocks[2] = new Block(block, coo3);
            blocks[3] = new Block(block, coo2);
        }
        for (int i = 0; i < 4; i++) {
            coords[i] = blocks[i].getLocation();
        }
    }
    public String getType()
    {
        return type;
    }
    public Block[] getBlocks() {return blocks;}
    public Coordinate[] getCoords() {return coords;}

    public abstract Coordinate[] rotateRight(Block[] arg);
    public abstract Coordinate[] rotateLeft(Block[] arg);

    public void resetPiece() {
    if (type == "i") {
        ImageIcon block = new ImageIcon("imageI.jpg","imageI.jpg");
        Coordinate coo1 = new Coordinate(TetrisPanel.SQUARE * 3,TetrisPanel.SQUARE);
        Coordinate coo2 = new Coordinate(TetrisPanel.SQUARE * 4,TetrisPanel.SQUARE);
        Coordinate coo3 = new Coordinate(TetrisPanel.SQUARE * 5,TetrisPanel.SQUARE);
        Coordinate coo4 = new Coordinate(TetrisPanel.SQUARE * 6,TetrisPanel.SQUARE);

        blocks[0] = new Block(block, coo1);
        blocks[1] = new Block(block, coo2);
        blocks[2] = new Block(block, coo4);
        blocks[3] = new Block(block, coo3);
        } 
        else if (type == "t") {
            ImageIcon block = new ImageIcon("imageT.jpg","imageT.jpg");
            Coordinate coo1 = new Coordinate(TetrisPanel.SQUARE * 4,TetrisPanel.SQUARE);
            Coordinate coo2 = new Coordinate(TetrisPanel.SQUARE * 5,TetrisPanel.SQUARE); //Centerpiece
            Coordinate coo3 = new Coordinate(TetrisPanel.SQUARE * 6,TetrisPanel.SQUARE);
            Coordinate coo4 = new Coordinate(TetrisPanel.SQUARE * 5,0);
    
            blocks[0] = new Block(block, coo1);
            blocks[1] = new Block(block, coo4);
            blocks[2] = new Block(block, coo3);
            blocks[3] = new Block(block, coo2);
        }
        else if (type == "j") {
            ImageIcon block = new ImageIcon("imageJ.jpg","imageJ.jpg");
            Coordinate coo1 = new Coordinate(TetrisPanel.SQUARE * 4, 0);
            Coordinate coo2 = new Coordinate(TetrisPanel.SQUARE * 5, TetrisPanel.SQUARE); //Centerpiece
            Coordinate coo3 = new Coordinate(TetrisPanel.SQUARE * 4, TetrisPanel.SQUARE);
            Coordinate coo4 = new Coordinate(TetrisPanel.SQUARE * 6, TetrisPanel.SQUARE);

            blocks[0] = new Block(block, coo1);
            blocks[1] = new Block(block, coo4);
            blocks[2] = new Block(block, coo3);
            blocks[3] = new Block(block, coo2);
        }
        else if (type == "l") {
            ImageIcon block = new ImageIcon("imageL.jpg","imageL.jpg");
            Coordinate coo1 = new Coordinate(TetrisPanel.SQUARE * 6, 0);
            Coordinate coo2 = new Coordinate(TetrisPanel.SQUARE * 5, TetrisPanel.SQUARE); //Centerpiece
            Coordinate coo3 = new Coordinate(TetrisPanel.SQUARE * 6, TetrisPanel.SQUARE);
            Coordinate coo4 = new Coordinate(TetrisPanel.SQUARE * 4, TetrisPanel.SQUARE);

            blocks[0] = new Block(block, coo1);
            blocks[1] = new Block(block, coo4);
            blocks[2] = new Block(block, coo3);
            blocks[3] = new Block(block, coo2);
        }
        else if (type == "o") {
            ImageIcon block = new ImageIcon("imageO.jpg","imageO.jpg");
            //no centerpiece
            Coordinate coo1 = new Coordinate(TetrisPanel.SQUARE * 4, 0);
            Coordinate coo2 = new Coordinate(TetrisPanel.SQUARE * 5, 0); 
            Coordinate coo3 = new Coordinate(TetrisPanel.SQUARE * 4, TetrisPanel.SQUARE);
            Coordinate coo4 = new Coordinate(TetrisPanel.SQUARE * 5, TetrisPanel.SQUARE);

            blocks[0] = new Block(block, coo1);
            blocks[1] = new Block(block, coo4);
            blocks[2] = new Block(block, coo3);
            blocks[3] = new Block(block, coo2);
        }
        else if (type == "s") {
            ImageIcon block = new ImageIcon("imageS.jpg","imageS.jpg");

            Coordinate coo1 = new Coordinate(TetrisPanel.SQUARE * 4, TetrisPanel.SQUARE);
            Coordinate coo2 = new Coordinate(TetrisPanel.SQUARE * 5, TetrisPanel.SQUARE); //centerpiece
            Coordinate coo3 = new Coordinate(TetrisPanel.SQUARE * 5, 0);
            Coordinate coo4 = new Coordinate(TetrisPanel.SQUARE * 6, 0);

            blocks[0] = new Block(block, coo1);
            blocks[1] = new Block(block, coo4);
            blocks[2] = new Block(block, coo3);
            blocks[3] = new Block(block, coo2);
        }
        else if (type == "z") {
            ImageIcon block = new ImageIcon("imageZ.jpg","imageZ.jpg");

            Coordinate coo1 = new Coordinate(TetrisPanel.SQUARE * 4, 0);
            Coordinate coo2 = new Coordinate(TetrisPanel.SQUARE * 5, TetrisPanel.SQUARE); //centerpiece
            Coordinate coo3 = new Coordinate(TetrisPanel.SQUARE * 5, 0);
            Coordinate coo4 = new Coordinate(TetrisPanel.SQUARE * 6, TetrisPanel.SQUARE);

            blocks[0] = new Block(block, coo1);
            blocks[1] = new Block(block, coo4);
            blocks[2] = new Block(block, coo3);
            blocks[3] = new Block(block, coo2);
        }
        for (int i = 0; i < 4; i++) {
            coords[i] = blocks[i].getLocation();
        }
    }

    public void updateCoords() {
        for (int i = 0; i < 4; i++) {
            coords[i] = blocks[i].getLocation();
        }
    }
    public Coordinate yRange() {
        int max = 0; 
        int min = 21;
        for (int i = 0; i < 4; i++) {
            if (blocks[i].getY() / TetrisPanel.SQUARE > max) {
                max = blocks[i].getY() / TetrisPanel.SQUARE;
            }
            if (blocks[i].getY() / TetrisPanel.SQUARE < min) {
                min = blocks[i].getY() / TetrisPanel.SQUARE;
            }
        }
        return new Coordinate(max, min);
    }

    public void rotate(boolean right) {
        Coordinate[] newCoords;
        if (right) {
            newCoords = this.rotateRight(blocks);
        } else {
            newCoords = this.rotateLeft(blocks);
        }
        for (int i = 0; i < 4; i++) {
            blocks[i].setLocation(newCoords[i]);
        }
        updateCoords();
    }
    public void step() {}
    
    public void drawMe(Graphics g) {
        for (int i = 0; i < 4; i++) {
            blocks[i].drawMe(g);
        }
    }
    
    public boolean checkGameOver() {
        for (int i = 0; i < 4; i++) {
            if (coords[i].getY() < TetrisPanel.SQUARE * 2) {
                return true;
            }
        }
        return false;
    }

    public boolean isBlocked(Coordinate[] arg) {
        for (int i = 0; i < 4; i++) {
            if (arg[i].getY() / TetrisPanel.SQUARE > 21) {
                return true;
            } else if (arg[i].getY() / TetrisPanel.SQUARE < 0) {
                return true;
            }else if (arg[i].getX() / TetrisPanel.SQUARE < 0) {
                return true;
            } else if (arg[i].getX() / TetrisPanel.SQUARE > 9) {
                return true;
            } else if (TetrisPanel.placedSquares[arg[i].getY() / TetrisPanel.SQUARE][arg[i].getX() / TetrisPanel.SQUARE]) {
                return true;
            }
        }
        return false;
    }
    public boolean canMoveLeft() {
        for (int i = 0; i < 4; i++) {
            if (coords[i].getX() / TetrisPanel.SQUARE < 1) {
                return false;
            } else if (TetrisPanel.placedSquares[coords[i].getY() / TetrisPanel.SQUARE][coords[i].getX() / TetrisPanel.SQUARE - 1]) {
                return false;
            }
        }
        return true;
    }
    public void moveLeft() {
        if (canMoveLeft()) {
            for (int i = 0; i < 4; i++) {
                int currentX = blocks[i].getLocation().getX();
                int currentY = blocks[i].getLocation().getY();
                blocks[i].setLocation(new Coordinate(currentX - TetrisPanel.SQUARE, currentY));
            }
        }
        updateCoords();
    }
    public boolean canMoveRight() {
        for (int i = 0; i < 4; i++) {
            if (coords[i].getX() / TetrisPanel.SQUARE >= 9) {
                return false;
            } else if (TetrisPanel.placedSquares[coords[i].getY() / TetrisPanel.SQUARE][coords[i].getX() / TetrisPanel.SQUARE + 1]) {
                return false;
            }
        }
        return true;
    }
    public void moveRight() {
        if (canMoveRight()) {
            for (int i = 0; i < 4; i++) {
                int currentX = blocks[i].getLocation().getX();
                int currentY = blocks[i].getLocation().getY();
                blocks[i].setLocation(new Coordinate(currentX + TetrisPanel.SQUARE, currentY));
            }
        }
        updateCoords();
    }

    public boolean canMoveDown() {
        for (int i = 0; i < 4; i++) {
            if (coords[i].getY() / TetrisPanel.SQUARE >= 21) {
                return false;
            } else if (TetrisPanel.placedSquares[coords[i].getY() / TetrisPanel.SQUARE  + 1][coords[i].getX() / TetrisPanel.SQUARE]) {
                return false;
            }
        }
        owner.landDelay = 0;
        owner.addLandDelay = false;
        return true;
    }
    public void moveDown() {
        if (canMoveDown()) {
            for (int i = 0; i < 4; i++) {
                int currentX = blocks[i].getLocation().getX();
                int currentY = blocks[i].getLocation().getY();
                blocks[i].setLocation(new Coordinate(currentX, currentY + TetrisPanel.SQUARE));
            }
            updateCoords();
        } else {
            owner.startLand();
        }
    }
    public void softDrop() {
        if (canMoveDown()) {
            for (int i = 0; i < 4; i++) {
                blocks[i].setLocation(new Coordinate(blocks[i].getLocation().getX(), blocks[i].getLocation().getY() + TetrisPanel.SQUARE));
                updateCoords();
            }
        }
        else {
            owner.placePiece();
        }
    }
    public void hardDrop() {
        while (canMoveDown()) {
            for (int i = 0; i < 4; i++) {
                blocks[i].setLocation(new Coordinate(blocks[i].getLocation().getX(), blocks[i].getLocation().getY() + TetrisPanel.SQUARE));
                updateCoords();
            }
        }
        owner.placePiece();
    }
}