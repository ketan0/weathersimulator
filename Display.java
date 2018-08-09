import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Display extends JComponent implements KeyListener, MouseListener
{
  private static Map<String, Image> images = new HashMap<String, Image>();
  
  public static Image getImage(String name)
  {
    try
    {
      Image image = images.get(name);
      if (image == null)
      {
        URL url = Display.class.getResource(name);
        if (url == null)
          throw new RuntimeException("unable to load image:  " + name);
        image = ImageIO.read(url);
        images.put(name, image);
      }
      return image;
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }
  
  private JFrame frame;
  private int key;
  private int mouseX;
  private int mouseY;
  private World world;
  
  public Display(final int width, final int height)
  {
    key = -1;
    mouseX = -1;
    mouseY = -1;
    
    try
    {
      SwingUtilities.invokeAndWait(new Runnable() { public void run() {
        world = new World(width, height);
        frame = new JFrame();
        frame.setTitle("World");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(Display.this);
        addMouseListener(Display.this);
        frame.getContentPane().add(Display.this);
        frame.pack();
        frame.setVisible(true);
      }});
    }
    catch(Exception e)
    {
      throw new RuntimeException(e);
    }
  }
  
  public void paintComponent(Graphics g)
  {
    try
    {
      world.paintComponent(g);
    }
    catch(Exception e)
    {
      e.printStackTrace();  //show error
      setVisible(false);  //stop drawing so we don't keep getting the same error
    }
  }
  
  public void run()//use this method
  {
    while (true)
    {
      frame.setTitle(world.getTitle());
      world.stepAll();
      repaint();
      try { Thread.sleep(10); } catch(Exception e) { }
      if (key != -1)
      {
        if(key==78)
        {
          world.changeWeatherTo(JOptionPane.showInputDialog(this, "Enter a location:"));
          world.setDemo(false);
        }
        world.keyPressed(key);
        key = -1;
      }
      if (mouseX != -1)
      {
        world.mouseClicked(mouseX, mouseY);
        mouseX = -1;
        mouseY = -1;
      }
    }
  }
  
  public void keyPressed(KeyEvent e)
  {
    key = e.getKeyCode();
  }
  
  public void keyReleased(KeyEvent e)
  {
  }
  
  public void keyTyped(KeyEvent e)
  {
  }
  
  public void mousePressed(MouseEvent e)
  {
    mouseX = e.getX();
    mouseY = e.getY();
  }
  
  public void mouseReleased(MouseEvent e)
  {
  }
  
  public void mouseClicked(MouseEvent e)
  {
  }
  
  public void mouseEntered(MouseEvent e)
  {
  }
  
  public void mouseExited(MouseEvent e)
  {
  }
}