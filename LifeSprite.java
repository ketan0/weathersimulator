public class LifeSprite extends MobileSprite
{
  public LifeSprite(double x, double y, double theLeft, double theTop, int theWidth, int theHeight, String theImage)
  {
    super(x, y, theLeft, theTop, theWidth, theHeight, theImage);
  }
  
  public void step(World world)
  {
    setLeft(getLeft()+getVx());
    setTop(getTop()+getVy());
    if((getLeft()+getWidth()>=world.getWidth()||getLeft()<=0)||getTop()+getHeight()>=world.getHeight()||getTop()<=0)
      setAlive(false);
  }
}