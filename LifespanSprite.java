public class LifespanSprite extends MobileSprite
{
  private int lifespan; //in seconds
  
  public LifespanSprite(double x, double y, double theLeft, double theTop, int theWidth, int theHeight, String theImage)
  {
    super(x, y, theLeft, theTop, theWidth, theHeight, theImage);
    lifespan=1000;
  }
  public LifespanSprite(int l, double x, double y, double theLeft, double theTop, int theWidth, int theHeight, String theImage)
  {
    super(x, y, theLeft, theTop, theWidth, theHeight, theImage);
    lifespan=100*l;
  }
  
  public void step(World world)
  {
    if(lifespan>0)
    {
      setLeft(getLeft()+getVx());
      setTop(getTop()+getVy());
      if(getLeft()+getWidth()>=world.getWidth()||getLeft()<=0)
        setVx(-getVx());
      else if(getTop()+getHeight()>=world.getHeight()||getTop()<=0)
        setVy(-getVy());
      lifespan--;
    }
    else
      setAlive(false);
  }
  
  public int getLifespan()
  {
    return lifespan;
  }
}