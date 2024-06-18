//Coordinate Class: an x and y value with some spice

//fields
//-x: :-: we know this
//-y: ""
//methods
//(nothing graphics or anything)
//-rotate right: changes the COORDINATE
//-rotate left: changes the COORDINATE 
//-toString (hehe.. i made that!): obvi, just a toString


public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int myX, int myY) {
        x = myX;
        y = myY;
    }

//accessor
    public int getX(){return x;}
    public int getY(){return y;}

//modifyer
    public void setX(int myX){x = myX;}
    public void setY(int myY){y = myY;}

    public void setCoord(int myX, int myY) {
        x = myX;
        y = myY;
    }

//methods
    public void rotateLeft(Coordinate center) {
        //gets the coordinate of the center of the block
        int centerX = center.getX();
        int centerY = center.getY();

        int startX = x;
        //rotates
        x = centerX + (y - centerY);
        y = centerY + (centerX - startX); 
    }

    public void rotateRight(Coordinate center) {
        int centerX = center.getX();
        int centerY = center.getY();

        int startX = x;

        x = centerX + (centerY - y);
        y = centerY - (centerX - startX);
    }

    public String toString()
    {
        return "("+x+","+y+")";
    }
}