public class TetrisBlockT extends FallingPiece
{
    private int position;
    private Coordinate[][] lOffsets;
    private Coordinate[][] rOffsets;

    public TetrisBlockT(TetrisPanel myOwner)
    {
        super("t", myOwner);
        //all oreientations for rotating right
        rOffsets = new Coordinate[4][5];
        rOffsets[0][0] = new Coordinate(0, 0); rOffsets[0][1] = new Coordinate(-1, 0); rOffsets[0][2] = new Coordinate(-1, -1); rOffsets[0][3] = new Coordinate(0, 2); rOffsets[0][4] = new Coordinate(-1, 2);
        rOffsets[1][0] = new Coordinate(0, 0); rOffsets[1][1] = new Coordinate(1, 0); rOffsets[1][2] = new Coordinate(1, -1); rOffsets[1][3] = new Coordinate(0, -2); rOffsets[1][4] = new Coordinate(1, -2);
        rOffsets[2][0] = new Coordinate(0, 0); rOffsets[2][1] = new Coordinate(1, 0); rOffsets[2][2] = new Coordinate(1, -1); rOffsets[2][3] = new Coordinate(0, 2); rOffsets[2][4] = new Coordinate(1, 2);
        rOffsets[3][0] = new Coordinate(0, 0); rOffsets[3][1] = new Coordinate(-1, 0); rOffsets[3][2] = new Coordinate(-1, 1);  rOffsets[3][3] = new Coordinate(0, -2);  rOffsets[3][4] = new Coordinate(-1, -2);

        //all orientations for rotating left
        lOffsets = new Coordinate[4][5];
        lOffsets[1][0] = new Coordinate(0, 0); lOffsets[1][1] = new Coordinate(1, 0); lOffsets[1][2] = new Coordinate(1, 1);  lOffsets[1][3] = new Coordinate(0, -2);  lOffsets[1][4] = new Coordinate(1, -2);
        lOffsets[2][0] = new Coordinate(0, 0); lOffsets[2][1] = new Coordinate(-1, 0); lOffsets[2][2] = new Coordinate(-1, -1); lOffsets[2][3] = new Coordinate(0, -2); lOffsets[2][4] = new Coordinate(-1, 2);
        lOffsets[3][0] = new Coordinate(0, 0); lOffsets[3][1] = new Coordinate(-1, 0); lOffsets[3][2] = new Coordinate(-1, 1); lOffsets[3][3] = new Coordinate(0, -2); lOffsets[3][4] = new Coordinate(-1, -2);
        lOffsets[0][0] = new Coordinate(0, 0); lOffsets[0][1] = new Coordinate(1, 0); lOffsets[0][2] = new Coordinate(1, -1); lOffsets[0][3] = new Coordinate(0, 2); lOffsets[0][4] = new Coordinate(1, 2);
    }
    
    private Coordinate[] basicRotate(Coordinate[] arg, boolean right) { //Cannot be abstract in FallingPiece because i has a different form of basic rotation
        Coordinate[] newCoords = new Coordinate[4];//makes a set of new coordinates
        for (int i = 0; i < 4; i++) {//loops through each of those and makes them the old coordinates
            newCoords[i] = arg[i];
        }
        for (int i = 0; i < 3; i++) {
            if (right) {//goes right if we wanna go right
            newCoords[i].rotateRight(newCoords[3]);
            } else {//..otherwise goes left
                newCoords[i].rotateLeft(newCoords[3]);
            }
        }
        return newCoords;

    }
    public Coordinate[] rotateRight(Block[] arg) {
        Coordinate[] cancelled = new Coordinate[4];//a list of all of the coordinates that were canceled
        Coordinate[] rotated = new Coordinate[4];//a list of all of the coordinates that were rotated
        for (int i = 0; i < 4; i++) {//each element in both lists
            //sets to the x and y of the original block lists
            cancelled[i] = new Coordinate(arg[i].getLocation().getX(), arg[i].getLocation().getY());
            rotated[i] = new Coordinate(arg[i].getLocation().getX(), arg[i].getLocation().getY());
        }
        rotated = basicRotate(rotated, true);//does a basic rotate with the original coords, going right
        Coordinate[] shifted = new Coordinate[4];//the shifted coordinate list of 4
        for (int i = 0; i < 4; i++) {//makes it equal to the rotated right value
            shifted[i] = new Coordinate(rotated[i].getX(), rotated[i].getY());
        }
        for (int i = 0; i < 5; i++) {//5 times loop
            for (int j = 0; j < 4; j++) {//4 times loop
                //sets shifted[j] x to the 
                //itself + 
                //the orientation it would be in
                shifted[j].setX(shifted[j].getX() + rOffsets[position][i].getX() * TetrisPanel.SQUARE);
                shifted[j].setY(shifted[j].getY() + rOffsets[position][i].getY() * TetrisPanel.SQUARE);
            }
            if (!isBlocked(shifted)) {//if this is inside another piece or out of bounds
                if (position != 3) {
                    position++;//shift the position a bit
                } else {
                    position = 0;//otherwise its fine
                }
                return shifted;
            } else {
                //if its not inside anything else, set the final rotated position to original attempt
                for (int k = 0; k < 4; k++) {
                    shifted[k].setX(rotated[k].getX());
                    shifted[k].setY(rotated[k].getY());
                }
            }
        }
        return cancelled;//return the canceled option
    }
    public Coordinate[] rotateLeft(Block[] arg) {//same as right, but left
        Coordinate[] cancelled = new Coordinate[4];
        Coordinate[] rotated = new Coordinate[4];
        for (int i = 0; i < 4; i++) {
            cancelled[i] = new Coordinate(arg[i].getLocation().getX(), arg[i].getLocation().getY());
            rotated[i] = new Coordinate(arg[i].getLocation().getX(), arg[i].getLocation().getY());
        }
        rotated = basicRotate(rotated, false);
        Coordinate[] shifted = new Coordinate[4];
        for (int i = 0; i < 4; i++) {
            shifted[i] = new Coordinate(rotated[i].getX(), rotated[i].getY());
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                shifted[j].setX(shifted[j].getX() + lOffsets[position][i].getX() * TetrisPanel.SQUARE);
                shifted[j].setY(shifted[j].getY() + lOffsets[position][i].getY() * TetrisPanel.SQUARE);
            }
            if (!isBlocked(shifted)) {
                if (position != 0) {
                    position--;
                } else {
                    position = 3;
                }
                return shifted;
            } else {
                for (int k = 0; k < 4; k++) {
                    shifted[k].setX(rotated[k].getX());
                    shifted[k].setY(rotated[k].getY());
                }
            }
        }
        return cancelled;
    }
}
