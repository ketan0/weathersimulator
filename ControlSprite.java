public class ControlSprite extends Sprite
{
  private int count;
  private int count2;
  private int count3;
  
  public ControlSprite()
  {
    super(0, 0, 0, 0, "square.png");
    count=0;
    count2=0;
    count3=0;
  }
  
  public void step(World world)
  {
    if(world.isThundering())
    {
      while(count3<5)
      {
      world.setAlive(count3+1, true);
      count3++;
      }
      pause(10);
      while(count3>0)
      {
      world.setAlive(count3, false);
      count3--;
      }
    }
    else if(count3>0)
    {
      world.setAlive(count3, false);
      count3--;
    }
    if(world.isCloudy())
    {
      world.setAlive(67, true);
      world.setAlive(68, true);
      if(count<60)
      {
        world.setAlive(count+6, true);
        count++;
      }
    }
    else
    {
      world.setAlive(67, false);
      world.setAlive(68, false);
      if(count>0)
      {
        world.setAlive(count+5, false);
        count--;
      }
    }
    if(world.isDusty()&&count2<60)
    {
      world.setAlive(count2+69, true);
      count2++;
    }
    else if(count2>0)
    {
      world.setAlive(count2+68, false);
      count2--;
    }
    if(world.isDay())
    {
      if(world.isRaining()||world.isSnowing()||world.isRainingHard()||world.isSnowingHard()||world.isHailing())
        changeSkyColor(world, 54, 110, 137);
      else //is clear, or windy/hot/cold
        changeSkyColor(world, 135, 206, 235);
    }
    else
      changeSkyColor(world, 0, 0, 50);
  }
  
  public void pause(int n)
  {
    try { Thread.sleep(n); } catch(Exception e) { }
  }
  
  public void changeSkyColor(World world, int r, int g, int b)
  {
    if(world.getRed()>r)
      world.setRed(world.getRed()-1);
    else if(world.getRed()<r)
      world.setRed(world.getRed()+1);
    if(world.getGreen()>g)
      world.setGreen(world.getGreen()-1);
    else if(world.getGreen()<g)
      world.setGreen(world.getGreen()+1);
    if(world.getBlue()>b)
      world.setBlue(world.getBlue()-1);
    if(world.getBlue()<b)
      world.setBlue(world.getBlue()+1);
    pause(10);
  }
}

