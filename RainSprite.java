public class RainSprite extends MobileSprite
{
  private double gravity;
  
  public RainSprite(double y, double theLeft, double theTop, int theWidth, int theHeight)
  {
    super(0, y, theLeft, theTop, theWidth, theHeight, "raindrop.png");
    gravity=0.1;
  }
  
  public void step(World world)
  {
    setTop(getTop()+getVy());
    if(getTop()>=world.getHeight())
      setExists(false);
    setVy(getVy()+gravity);
  }
}