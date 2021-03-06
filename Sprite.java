import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.imageio.*;

public class Sprite
{
  private double left;  //the x-coordinate of the left edge of the sprite
  private double top;   //the y-coordinate of the top edge of the sprite
  private int width;
  private int height;
  private String image;
  private boolean alive;
  private boolean exists;
  
  public Sprite(double theLeft, double theTop, int theWidth, int theHeight, String theImage)
  {
    left = theLeft;
    top = theTop;
    width = theWidth;
    height = theHeight;
    setImage(theImage);
    alive=true;
    exists=true;
  }
  
  public boolean contains(int x, int y)
  {
    return x>=getLeft()&&x<=getLeft()+getWidth()&&y>=getTop()&&y<=getTop()+getHeight();
  }
  
  public boolean isOverlappingWith(Sprite sprite)
  {
    return left<sprite.getLeft()+sprite.getWidth()&&sprite.getLeft()<left+width
           &&top<sprite.getTop()+sprite.getHeight()&&sprite.getTop()<top+height;
  }
  
  public boolean isAlive()
  {
    return alive;
  }
  
  public boolean exists()
  {
    return exists;
  }
  
  public void setAlive(boolean a)
  {
    alive=a;
  }
  
  public void setExists(boolean e)
  {
    exists=e;
  }
  
  public double getLeft()
  {
    return left;
  }
  
  public void setLeft(double l)
  {
    left = l;
  }
  
  public double getTop()
  {
    return top;
  }
  
  public void setTop(double t)
  {
    top = t;
  }
  
  public int getWidth()
  {
    return width;
  }
  
  public void setWidth(int w)
  {
    width = w;
  }
  
  public int getHeight()
  {
    return height;
  }
  
  public void setHeight(int h)
  {
    height = h;
  }
  
  public String getImage()
  {
    return image;
  }
  
  public void setImage(String i)
  {
    image = i;
  }
  
  public void step(World world)
  {
  }
}
