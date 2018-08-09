public class DigitSprite extends Sprite
{
  public DigitSprite(int digit, int position)
  {
      super(221+position*79, 0, 89, 123, digit+".png");
      if(position==0)
        setLeft(getLeft()+5);
  }
  public DigitSprite()
  {
      super(464, 10, 30, 30, "degree.png");
  }
  
  public void setAlive(boolean a)
  {
    super.setAlive(true);
  }
}