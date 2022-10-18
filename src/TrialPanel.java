import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

public class TrialPanel extends JPanel {
    private ArrayList<Dot> dots;
    private ArrayList<Square> squares;

    private int size = 20;

    private int validCenterRangeX;
    private int validCenterRangeY;
    private int interval;

    private Random random;

    private boolean hasUnique;

    private int width = 500;
    private int height = 500;


    public TrialPanel() {
        setBackground(Color.GRAY);
        setPreferredSize(new Dimension(width,height));
        dots = new ArrayList<Dot>();
        squares = new ArrayList<Square>();
        interval = 150;
        random = new Random();

        validCenterRangeX = width - (size*2);
        validCenterRangeY = height - (size*2);


    }
   /* public boolean doesDotCollide(Dot dot){
        for(Dot d: dots){
            if(dot.isInside(d.getCenter())){
                return true;
            }
        }
        return false;
    }

    public boolean doesDotCollideWithSquare(Dot dot){
        for(Square s: squares){
            if(dot.isInside(s.getCenter())){
                return true;
            }
        }
        return false;
    }

    public boolean doesSquareCollideWithDot(Square square){
        for(Dot d: dots){
            if(square.isInside(d.getCenter())){
                return true;
            }
        }
        return false;
    }
    public boolean doesSquareCollide(Square square){
        for(Square s: squares){
            if(square.isInside(s.getCenter())){
                return true;
            }
        }
        return false;
    }*/

    /*public void generateDot(Color color, boolean square){
        Point newPosition = new Point((random.nextInt(validCenterRangeX)), random.nextInt(validCenterRangeY));
        Dot tempDot = new Dot(newPosition, size, color);
        if(!square) {
            if (!doesDotCollide(tempDot)) {
                dots.add(tempDot);
            } else {
                generateDot(color, false);
            }
        }
        else{
            if (!doesDotCollideWithSquare(tempDot)) {
                dots.add(tempDot);
            } else {
                generateDot(color, true);
            }
        }
    }*/

    /*public void generateSquare( Color color){
        Point newPosition = new Point((random.nextInt(validCenterRangeX)), random.nextInt(validCenterRangeY));
        Square tempSquare = new Square(newPosition, size, color);
        if(!doesSquareCollideWithDot(tempSquare)) {
            if (!doesSquareCollide(tempSquare)) {
                squares.add(tempSquare);
            } else {
                generateSquare(color);
            }
        }
        else{
            if(!doesSquareCollideWithDot(tempSquare)){
                squares.add(tempSquare);
            }
            else{
                generateSquare(color);
            }
        }
    }
    */

    public void clearDotsNSquares(){
        dots.clear();
        squares.clear();
    }
    public boolean doDotCollide(Dot dot){
        for(Dot d: dots){
            if(dot.isInside(d.getCenter())){
                return true;
            }
        }
        for(Square s: squares){
            if(dot.isInside(s.getCenter())){
                return true;
            }
        }
        return false;
    }
    public boolean doSquareCollide(Square square){
        for(Dot d:dots){
            if(square.isInside(d.getCenter())){
                return true;
            }
        }
        for(Square s: squares){
            if(square.isInside(s.getCenter())){
                return true;
            }
        }
        return false;
    }

    public void generateDot(Color color){
        Point newPosition = new Point((random.nextInt(validCenterRangeX-size)), random.nextInt(validCenterRangeY-size));
        Dot tempDot = new Dot(newPosition, size, color);
        if(doDotCollide(tempDot)){
            generateDot(color);
        }
        else{
            dots.add(tempDot);
        }

    }

    public void generateDots(int numDots, Color color, boolean hasDifferentColor, Color color2){
        if(hasDifferentColor){
            numDots -= 1;
        }
        for(int i = 0; i < numDots; i++){
            generateDot(color);
        }
        if(hasDifferentColor){
            generateDot(color2);
        }

    }

    public void generateSquare(Color color){
        Point newPosition = new Point((random.nextInt(validCenterRangeX-size)), random.nextInt(validCenterRangeY-size));
        Square tempSquare = new Square(newPosition, size, color);
        if(doSquareCollide(tempSquare)){
            generateSquare(color);
        }
        else{
            squares.add(tempSquare);
        }

    }

    public void generateSquares(int numSquares, Color color, boolean hasDifferentColor, Color color2){
        if(hasDifferentColor){
            numSquares-=1;
        }
        for(int i = 0; i < numSquares; i++){
            generateSquare(color);
        }
        if(hasDifferentColor){
            generateSquare(color2);
        }
    }



    public void ColorsOnly(SubjectInfo subject){
        hasUnique = random.nextBoolean();
        generateDots(subject.getNumDistractors(), Color.BLUE, hasUnique, Color.RED);
    }

    public void ShapesOnly(SubjectInfo subject){
        hasUnique = random.nextBoolean();
        if(hasUnique){
            generateDots(subject.getNumDistractors()-1, Color.RED, false, Color.RED);
            generateSquares(1, Color.RED, false, Color.RED);
        }
        else{
            generateDots(subject.getNumDistractors(), Color.RED, false, Color.RED);
        }
    }

    public void ShapesAndColors(SubjectInfo subject){
        hasUnique = random.nextBoolean();
        int numSquares = subject.getNumDistractors()/2;
        int numDots = subject.getNumDistractors() - numSquares;
        if(hasUnique){
            generateDots(numDots, Color.BLUE, true, Color.RED);
            generateSquares(numSquares, Color.RED, false, Color.RED);
        }
        else{
            generateDots(numDots, Color.BLUE, false, Color.BLUE);
            generateSquares(numSquares, Color.RED, false, Color.RED);
        }

    }
    public boolean HasUnique() {
        return hasUnique;
    }

    public void setHasUnique(boolean hasUnique) {
        this.hasUnique = hasUnique;
    }

    @Override
    protected synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Dot d:dots){
            d.paint(g);
        }
        for(Square s:squares){
            s.paint(g);
        }
        repaint();

    }
}
