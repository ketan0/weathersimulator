public class ShadeSprite extends Sprite
{
  public ShadeSprite(String img)
  {
    super(0,0, 512, 512, img);
    setAlive(false);
  }
  public ShadeSprite(int left, int top, int width, int height, String img)
  {
    super(left, top, width, height, img);
    setAlive(false);
  }
}