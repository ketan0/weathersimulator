import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SunSprite extends MobileSprite
{
  private int count;
  
  public SunSprite()
  {
    super(0, 0, 150, 150, 50, 50, "sun.png");
    count=0;
  }
  
  public void step(World world)
  {
    double mins=world.getTime();
    double sunrise=world.getSunrise();
    double sunset=world.getSunset();
    int x=(int)((mins-sunrise)/(sunset-sunrise)*512)+count;
    setLeft(x);
    setTop((int)(Math.pow((x-256),2)/190+10));
    if(getTop()>=346)
      world.setDay(false);
    else
      world.setDay(true);
  }
  
  public void addMinute()
  {
    count++;
  }
  
  public void subtractMinute()
  {
    count--;
  }
}