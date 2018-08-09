public class HeavySprite extends MobileSprite
{
  public double gravity;
  
  public HeavySprite(double x, double y, double theLeft, double theTop, int theWidth, int theHeight, String theImage)
  {
    super(x, y, theLeft, theTop, theWidth, theHeight, theImage);
    gravity=0.1;
  }
  public HeavySprite(double g, double x, double y, double theLeft, double theTop, int theWidth, int theHeight, String theImage)
  {
    super(x, y, theLeft, theTop, theWidth, theHeight, theImage);
    gravity=g;
  }
  
  public void step(World world)
  {
    super.step(world);
    if(getLeft()+getWidth()==world.getWidth()||getLeft()==0)
      setVx(-getVx());
    else if(getLeft()+getWidth()>=world.getWidth()||getLeft()<0)
    {
      setLeft(getLeft()-getVx());
      setVx(-Math.abs(getVx()));
    }
    if(getTop()+getHeight()==world.getHeight()||getTop()==0)
      setVy(-getVy());
    else if(getTop()+getHeight()>=world.getHeight()||getTop()<0)
    {
      setTop(getTop()-getVy());
      setVy(-Math.abs(getVy()));
    }
    setVy(getVy()+gravity);
  }
  
  public double getGravity()
  {
    return gravity;
  }
}