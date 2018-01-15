//AP Computer Science A - Cumberland High School - Written by Eric Georgio
import java.io.*;
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class GameTitleCard //Displays a title card (compatible image types: (.jpg, .gif, .png) for a specific size (length x width)
{
  private String inFile;
  private final int length; 
  private final int width;
  
  private BufferedImage img;
  private ImageIcon icon;
  private JFrame frame;
  private JLabel label;
  
    public GameTitleCard(String inFile, int imageLength, int imageWidth) //You NEED to pass in the file name (inFile), length of the image you want to display, and width of the image you want to display (no default constructor). 
    {
      this.length = imageLength;
      this.width = imageWidth;
      try
      {
        this.inFile = inFile;
        img = ImageIO.read(new File(getInFile()));
        icon = new ImageIcon(img);
        frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(getLength(), getWidth());
        JLabel label = new JLabel();
        label.setIcon(icon);
        frame.add(label);
      }
      catch (IOException ioe)
      {
        System.out.println("Error displaying title card (this message should never appear)!");
      }
    }
    //getters and setters -- note that there are not setters for values listed as "final" or values that should never otherwise change once the object is created
    public String getInFile()
    {
      return inFile;
    }
    public void setInFile(String newFileName)
    {
      inFile = newFileName;
    }
    public int getLength()
    {
      return length;
    }
    public int getWidth()
    {
      return width;
    }
    public JFrame getFrame()
    {
      return frame;
    }
    public void isVisible(boolean visible) //This is the method that you will call to display or hide your title card
    {
      getFrame().setVisible(visible);
    }
}

//In another program, all that you would need to use this object would be the following syntax:
//GameTitleCard gmt = new GameTitleCard("FILE NAME HERE.jpg");   The following image types are compatible: .jpg, .gif, .png
//Make sure that the image file is in the same directory as your program. If the file is in a different directory, you will have to specify the exact file path
//You will then be able to display your title card by saying gmt.isVisible(true) or hide it by saying gmt.isVisible(false)