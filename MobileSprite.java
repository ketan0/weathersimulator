import java.util.*;

public class MobileSprite extends Sprite
{
  private double vx;
  private double vy;
  
  public MobileSprite(double x, double y, double theLeft, double theTop, int theWidth, int theHeight, String theImage)
  {
    super(theLeft, theTop, theWidth, theHeight, theImage);
    vx=x;
    vy=y;
  }
  
  public double getVx()
  {
    return vx;
  }
  
  public double getVy()
  {
    return vy;
  }
  
  public void setVx(double x)
  {
    vx=x;
  }
  
  public void setVy(double y)
  {
    vy=y;
  }
  
  public void set(double x, double y)
  {
    vx=x;
    vy=y;
  }
  
  public void step(World world)
  {
    setLeft(getLeft()+vx);
    setTop(getTop()+vy);
  }
}