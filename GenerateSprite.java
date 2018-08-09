public class GenerateSprite extends MobileSprite
{
  private int count;
  
  public GenerateSprite(double x, double y, double theLeft, double theTop, int theWidth, int theHeight, String theImage)
  {
    super(x, y, theLeft, theTop, theWidth, theHeight, theImage);
    count=0;
  }
  
  public void step(World world)
  {
    count++;
    if(world.isWindy())
      setVx(0.5);
    else
      setVx(0.15);
    super.step(world);
    if(getLeft()+getWidth()<=0)
      setLeft(world.getWidth());
    else if(getLeft()>=world.getWidth())
      setLeft(0-getWidth());
    if(getTop()+getHeight()<=0)
      setTop(world.getHeight());
    else if(getTop()>=world.getHeight())
      setTop(0-getHeight());
    if(world.isRaining()&&count%5==0)
      world.addSprite(new RainSprite(0.1, (int)(Math.random()*(getWidth()-20)+getLeft()+10), (int)(Math.random()*20+getTop()+getHeight()-25), 7, 15));
    else if(world.isRainingHard())
      world.addSprite(new RainSprite(0.1, (int)(Math.random()*(getWidth()-20)+getLeft()+10), (int)(Math.random()*20+getTop()+getHeight()-25), 7, 15));
    if(world.isSnowing()&&count%20==0)
      world.addSprite(new SnowSprite(0.1, (int)(Math.random()*(getWidth()-20)+getLeft()+10), (int)(Math.random()*20+getTop()+getHeight()-25), 20, 20));
    else if(world.isSnowingHard()&&count%10==0)
      world.addSprite(new SnowSprite(0.1, (int)(Math.random()*(getWidth()-20)+getLeft()+10), (int)(Math.random()*20+getTop()+getHeight()-25), 20, 20));
    else if(world.isHailing())
      world.addSprite(new HailSprite(0.1, (int)(Math.random()*(getWidth()-20)+getLeft()+10), (int)(Math.random()*20+getTop()+getHeight()-25), 10, 10));
  }
  
  
}