import java.awt.*;

public class Square {
    protected int size;
    protected Color color;
    protected double x;
    protected double y;

    public Square(Point center, int size, Color color){
        this.x = center.x;
        this.y = center.y;
        this.size = size*2;
        this.color = color;
    }

    public int getTop(){
        return (int) y - (size/2);
    }
    public int getBottom(){
        return (int) y + (size/2);
    }
    public int getLeft(){
        return (int) x - (size/2);
    }
    public int getRight(){
        return (int) x +(size/2);
    }

    public Point getCenter() {
        return new Point((int)x, (int)y);
    }

    public void setCenter(Point p){
        x = p.x;
        y = p.y;
    }

    public boolean isInside(Point p){
        Point center = new Point((int)x,(int)y);
        return p.distance(center)<size;
    }

    public Rectangle getRegion(){
        return  new Rectangle(getLeft(), getTop(), size, size);
    }

    void paint(Graphics g){
        g.setColor(color);
        g.fillRect(getLeft(), getTop(), size, size);
    }

    public void setColor(Color c){
        this.color = c;
    }
}
