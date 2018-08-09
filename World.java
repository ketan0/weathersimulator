import java.io.File;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.net.URL;
import java.net.URLEncoder;
import java.net.URI;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.*;
import org.w3c.dom.*;

//TO DO:
//change key codes for demo mode
//lightning
//user input

public class World
{
  public static void main(String[] args)
  {
    Display display = new Display(512, 484);
    display.run();
  }
  
  private ArrayList<Sprite> sprites;
  private int width;
  private int height;
  private boolean isRaining;
  private boolean isSnowing;
  private boolean isRainingHard;
  private boolean isSnowingHard;
  private boolean isThundering;
  private boolean isHailing;
  private boolean isDusty;
  private boolean isWindy;
  private boolean isCloudy;
  private boolean isDay;
  private int red;
  private int green;
  private int blue;
  private int sunrise;
  private int sunset;
  private int time;
  private int temp;
  private int weatherCode;
  private String weatherDescription;
  private String weatherLocation;
  private boolean demo;
  private String high;
  private String low;
  
  public World(int w, int h)
  {
    width = w;
    height = h;
    isRaining=false;
    isSnowing=false;
    isRainingHard=false;
    isSnowingHard=false;
    isHailing=false;
    isDusty=false;
    isWindy=false;
    isCloudy=false;
    isDay=true;
    isThundering=false;
    demo=false;
    high="";
    low="";
    red=135;
    green=206;
    blue=235;
    sprites = new ArrayList<Sprite>();
    sprites.add(new SunSprite());
    for(int i=0;i<5;i++)
      sprites.add(new ShadeSprite(200, 0, 200, 360, "lightning.png"));
    for(int i=0;i<60;i++)
      sprites.add(new ShadeSprite("gray.png"));
    sprites.add(new Sprite(0,150,512,384,"grass.png"));
    sprites.add(new CloudSprite(0.075, 0, 0, 40, 192, 108));
    sprites.add(new CloudSprite(0.075, 0, 312, 40, 192, 108));
    for(int i=0;i<60;i++)
      sprites.add(new ShadeSprite("sand.png"));
    sprites.add(new Sprite(0, 0, 0, 0, "square.png"));
    sprites.add(new DigitSprite());
    sprites.add(new DigitSprite(0,2));
    sprites.add(new ControlSprite());
    sunrise=0;
    sunset=0;
    time=0;
    temp=10;
    weatherCode=0;
    weatherDescription="";
    weatherLocation="";
    changeWeatherTo("Taipei");
  }
  
  public void stepAll()
  {
    for(int i=0;i<sprites.size();i++)
    {
      if(sprites.get(i).isAlive())
        sprites.get(i).step(this);
      if(!sprites.get(i).exists())
      {
        sprites.remove(i);
        i--;
      }
    }
  }
  
  public int getWidth()
  {
    return width;
  }
  
  public int getHeight()
  {
    return height;
  }
  
  public boolean isRaining()
  {
    return isRaining;
  }
  
  public boolean isSnowing()
  {
    return isSnowing;
  }
  
  public boolean isRainingHard()
  {
    return isRainingHard;
  }
  
  public boolean isSnowingHard()
  {
    return isSnowingHard;
  }
  
  public boolean isHailing()
  {
    return isHailing;
  }
  
  public boolean isWindy()
  {
    return isWindy;
  }
  
  public boolean isDusty()
  {
    return isDusty;
  }
  
  public boolean isCloudy()
  {
    return isCloudy;
  }
  
  public boolean isThundering()
  {
    return isThundering;
  }
  
  public boolean isDay()
  {
    return isDay;
  }
  
  public int getRed()
  {
    return red;
  }
  
  public int getGreen()
  {
    return green;
  }
  
  public int getBlue()
  {
    return blue;
  }
  
  public void setRed(int r)
  {
    red=r;
  }
  
  public void setGreen(int g)
  {
    green=g;
  }
  
  public void setBlue(int b)
  {
    blue=b;
  }
  
  public void setRaining(boolean r)
  {
    isRaining=r;
  }
  
  public void setSnowing(boolean s)
  {
    isSnowing=s;
  }
  
  public void setDay(boolean d)
  {
    isDay=d;
  }
  
  public int getNumSprites()
  {
    return sprites.size();
  }
  
  public Sprite getSprite(int index)
  {
    return sprites.get(index);
  }
  
  public ArrayList<Sprite> getSprites()
  {
    return sprites;
  }
  
  public void addSprite(Sprite sprite)
  {
    sprites.add(sprite);
  }
  
  public void addSprite(int i, Sprite sprite)
  {
    sprites.add(i, sprite);
  }
  
  public void removeSprite(int i)
  {
    sprites.remove(i);
  }
  
  public void setAlive(int i, boolean alive)
  {
    sprites.get(i).setAlive(alive);
  }
  
  public int getSunrise()
  {
    return sunrise;
  }
  
  public int getSunset()
  {
    return sunset;
  }
  
  public void setSunrise(int n)
    
  {
    sunrise=n;
  }
  
  public void setSunset(int n)
  {
    sunset=n;
  }
  
  public int getTime()
  {
    return time;
  }
  
  public void setTime(int n)
  {
    time=n;
  }
  
  public int getTemp()
  {
    return temp;
  }
  
  public void removeAllDigitSprites()
  {
    for(int i=0;i<sprites.size();i++)
    {
      if(sprites.get(i).getImage().equals("0.png")||sprites.get(i).getImage().equals("1.png")||sprites.get(i).getImage().equals("2.png")||sprites.get(i).getImage().equals("3.png")||sprites.get(i).getImage().equals("4.png")||sprites.get(i).getImage().equals("5.png")||sprites.get(i).getImage().equals("6.png")||sprites.get(i).getImage().equals("7.png")||sprites.get(i).getImage().equals("8.png")||sprites.get(i).getImage().equals("9.png"))
      {   
        sprites.remove(i);
        i--;
      }
    }
  }
  
  public void setTemp(int n)
  {
    removeAllDigitSprites();
    temp=n;
    if (temp<10)
    {
      sprites.add(sprites.size()-2, new DigitSprite(temp, 2));
    }
    else if (temp<100)
    {
      sprites.add(sprites.size()-2, new DigitSprite(temp/10, 1));
      sprites.add(sprites.size()-2, new DigitSprite(temp%10, 2));
    }
    else
    {
      sprites.add(sprites.size()-2, new DigitSprite(temp/100, 0));
      sprites.add(sprites.size()-2, new DigitSprite((temp/10)%10, 1));
      sprites.add(sprites.size()-2, new DigitSprite(temp%10, 2));
    }
  }
  
  public int getWeatherCode()
  {
    return weatherCode;
  }
  
  public void setWeatherCode(int n)
  {
    weatherCode=n;
    setAllFalse();
    isCloudy=true;
    if((n>=0&&n<=4)||(n>=37&&n<=40)||n==45||n==47)
    {
      isThundering=true;
      isRainingHard=true;
    }
    else if(n>=5&&n<=7)
    {
      isRaining=true;
      isSnowing=true;
    }
    else if(n==8||n==9)
      isRaining=true;
    else if((n>=10&&n<=12)||n==18)
      isRainingHard=true;
    else if(n==13||n==14)
      isSnowingHard=true;
    else if(n==15||n==16||(n>=41&&n<=43)||n==46)
      isSnowing=true;
    else if(n==17)
      isHailing=true;
    else if(n==19||n==21)
    {
      isDusty=true;
      isCloudy=false;
    }
    else if(n==23||n==24)
    {
      isWindy=true;
      isCloudy=false;
    }
    else if(n==25||(n>=31&&n<=34)||n==36)
      isCloudy=false;
  }
  
  public void setAllFalse()
  {
    isThundering=false;
    isRaining=false;
    isRainingHard=false;
    isSnowing=false;
    isSnowingHard=false;
    isHailing=false;
    isDusty=false;
    isWindy=false;
    isCloudy=false;
  }
  
  public String getWeatherDescription()
  {
    return weatherDescription;
  }
  
  public void setWeatherDescription(String n)
  {
    weatherDescription=n;
  }
  
  public String getWeatherLocation()
  {
    return weatherLocation;
  }
  
  public void setWeatherLocation(String n)
  {
    weatherLocation=n;
  }
  
  public void mouseClicked(int x, int y)
  {
  }
  
//  if((n>=0&&n<=4)||(n>=37&&n<=40)||n==45||n==47)
//    {
//      isThundering=true;
//      isRainingHard=true;
//    }
//    else if(n>=5&&n<=7)
//    {
//      isRaining=true;
//      isSnowing=true;
//    }
//   else if(n==8||n==9)
//      isRaining=true;
//    else if((n>=10&&n<=12)||n==18)
//      isRainingHard=true;
//    else if(n==13||n==14)
//      isSnowingHard=true;
//    else if(n==15||n==16||(n>=41&&n<=43)||n==46)
//      isSnowing=true;
//    else if(n==17)
//      isHailing=true;
//    else if(n==19||n==21)
//    {
//      isDusty=true;
//      isCloudy=false;
//    }
//    else if(n==23||n==24)
//    {
//      isWindy=true;
//      isCloudy=false;
//    }
//    else if(n==25||(n>=31&&n<=34)||n==36)
//      isCloudy=false;
//  }
  
  public void setDemo(boolean d)
  {
    demo=d;
  }
  
  public void keyPressed(int key)
  {
    if(key==68)
    {
      demo=!demo;
      if(!demo)
      {
        changeWeatherTo(weatherLocation);
        sprites.add(sprites.size()-3, new DigitSprite());
      }
    }
    if(demo)
    {
      if(key==49)
      {
        setAllFalse();
        isRaining=true;
        isCloudy=true;
      }
      else if(key==50)
      {
        setAllFalse();
        isRainingHard=true;
        isCloudy=true;
      }
      else if(key==51)
      {
        setAllFalse();
        isSnowing=true;
        isCloudy=true;
      }
      else if(key==52)
      {
        setAllFalse();
        isSnowingHard=true;
        isCloudy=true;
      }
      else if(key==53)
      {
        setAllFalse();
        isHailing=true;
        isCloudy=true;
      }
      else if(key==54)
      {
        setAllFalse();
        isThundering=true;
        isRainingHard=true;
        isCloudy=true;
      }
      else if(key==55)
      {
        setAllFalse();
        isDusty=true;
        isCloudy=true;
      }
      else if(key==56)
      {
        setAllFalse();
        isWindy=true;
        isCloudy=true;
      }
      else if(key==57)
      {
        setAllFalse();
      }
      else if(key==48)
        isDay=!isDay;
      else if(key==37)
        ((SunSprite)sprites.get(0)).subtractMinute();
      else if(key==39)
        ((SunSprite)sprites.get(0)).addMinute();
    }
  }
  
  public String getTitle() 
  {
    if(!demo)
      return "Weather Simulator: "+weatherDescription+" in "+weatherLocation;
    else 
      return "Weather Simulator: Demo Mode";
  }
  
  public void paintComponent(Graphics g)
  {
    g.setColor(new Color(red, green, blue));
    g.fillRect(0, 0, width, height);
    for (int i = 0; i < sprites.size(); i++)
    {
      if(sprites.get(i).isAlive())
      {
        Sprite sprite = sprites.get(i);
        g.drawImage(Display.getImage(sprite.getImage()),
                    (int)sprite.getLeft(), (int)sprite.getTop(),
                    sprite.getWidth(), sprite.getHeight(), null);
      }
    }
  }
  
    public void changeWeatherTo(String regionname)
  {
    InputStream inputXml = null;
    try
    {
      inputXml  = new URL("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22"+URLEncoder.encode(regionname, "UTF-8")+"%22)&format=xml&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys").openConnection().getInputStream();
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(inputXml);
      System.out.println(regionname);
      weatherLocation=regionname;
      NodeList nodi = doc.getElementsByTagName("yweather:condition");
      if (nodi.getLength() > 0)
      {
        Element nodo = (Element)nodi.item(0);
        String text = nodo.getAttribute("text");
        Element nodo1 = (Element)nodi.item(0);
        String code = nodo1.getAttribute("code");
        Element nodo2 = (Element)nodi.item(0);
        String temps = nodo2.getAttribute("temp");
        Element nodo3 = (Element)nodi.item(0);
        String date = nodo3.getAttribute("date");
        System.out.println("Text: " + text);
        System.out.println("Code: " + code);
        System.out.println("Temp: " + temps);
        System.out.println("Date: " + date);
        weatherDescription=text;
        setWeatherCode(Integer.parseInt(code));
        setTemp(Integer.parseInt(temps));
        if(date.substring(date.indexOf(":")-2,date.indexOf(":")-1).equals(" "))
          time=convert(date.substring(date.indexOf(":")-1, date.indexOf(":")+6));
        else
          time=convert(date.substring(date.indexOf(":")-2, date.indexOf(":")+6));
      }
      nodi = doc.getElementsByTagName("yweather:astronomy");
      if (nodi.getLength() > 0)
      {
        Element nodo = (Element)nodi.item(0);
        String sunrises = nodo.getAttribute("sunrise");
        Element nodo1 = (Element)nodi.item(0);
        String sunsets = nodo1.getAttribute("sunset");
        System.out.println("sunrise: "+sunrises+" \nsunset: "+sunsets);
        setSunrise(convert(sunrises));
        setSunset(convert(sunsets));
      }
      nodi = doc.getElementsByTagName("yweather:astronomy");
      if (nodi.getLength() > 0)
      {
        Element nodo = (Element)nodi.item(0);
        high = nodo.getAttribute("high");
        Element nodo1 = (Element)nodi.item(0);
        low = nodo1.getAttribute("low");
      }
      }
    catch (Exception ex)
    {
      System.out.println(ex.getMessage());
    }
    finally
    {
      try
      {
        if (inputXml != null)
          inputXml.close();
      }
      catch (IOException ex)
      {
        System.out.println(ex.getMessage());
      }
    }
    }
  
  public static int convert(String s)
  {
    if(s.substring(s.length()-2).equals("am"))
      return Integer.parseInt(s.substring(0, s.indexOf(":")))%12*60+Integer.parseInt(s.substring(s.indexOf(":")+1, s.indexOf(":")+3));
    else
      return Integer.parseInt(s.substring(0, s.indexOf(":")))%12*60+720+Integer.parseInt(s.substring(s.indexOf(":")+1, s.indexOf(":")+3));
  }
}