import java.util.*;

public class KillSprite extends MobileSprite
{
  
  public KillSprite(double x, double y, double theLeft, double theTop, int theWidth, int theHeight, String theImage)
  {
    super(x, y, theLeft, theTop, theWidth, theHeight, theImage);
  }
  
  public void step(World world)
  {
    setLeft(getLeft()+getVx());
    setTop(getTop()+getVy());
    if(getLeft()+getWidth()>=world.getWidth()||getLeft()<=0)
      setVx(-getVx());      
    else if(getTop()+getHeight()>=world.getHeight()||getTop()<=0)   
      setVy(-getVy());
    ArrayList<Sprite> sprites=world.getSprites();
    for(int i=0;i<sprites.size();i++)
    {
      if(isOverlappingWith(sprites.get(i))&&!sprites.get(i).equals(this))
        sprites.get(i).setAlive(false);
    }
  }
}