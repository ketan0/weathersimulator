import java.util.*;

public class SnowSprite extends MobileSprite
{
  private double gravity;
  
  public SnowSprite(double y, double theLeft, double theTop, int theWidth, int theHeight)
  {
    super(Math.random()-0.5, y, theLeft, theTop, theWidth, theHeight, "snowflake.png");
    gravity=0.005;
  }
  
  public void step(World world)
  {
    setTop(getTop()+getVy());
    setLeft(getLeft()+getVx());
    if(getTop()>=world.getHeight())
      setExists(false);
    ArrayList<Sprite> sprites=world.getSprites();
    for(Sprite sprite: sprites)
    {
      if(isOverlappingWith(sprite)&&!sprite.equals(this))
        setVx(-getVx());
    }
    setVy(getVy()+gravity);
  }
}