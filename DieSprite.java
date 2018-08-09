import java.util.*;

public class DieSprite extends MobileSprite
{
  
  public DieSprite(double x, double y, double theLeft, double theTop, int theWidth, int theHeight, String theImage)
  {
    super(x, y, theLeft, theTop, theWidth, theHeight, theImage);
  }
  
  public void step(World world)
  {
    super.step(world);
    if(getLeft()+getWidth()>=world.getWidth()||getLeft()<=0)
      setVx(-getVx());      
    else if(getTop()+getHeight()>=world.getHeight()||getTop()<=0)     
      setVy(-getVy());
    ArrayList<Sprite> sprites=world.getSprites();
    for(int i=0;i<sprites.size();i++)
    {
      if(isOverlappingWith(sprites.get(i))&&!sprites.get(i).equals(this))
        setAlive(false);
    }
  }
}