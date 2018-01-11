//AP Computer Science A - Cumberland High School - Written by Eric Georgio
import javafx.embed.swing.JFXPanel;
import java.net.URL;
import javafx.scene.media.*;

public class GameMusicPlayer
{
  private final JFXPanel fxPanel = new JFXPanel();
  private String fileName;
  private URL resource;
  private Media media;
  private MediaPlayer mediaPlayer;
  public GameMusicPlayer(String fileName) //Initializes the music player given a valid .mp3 file name. Use the file path if the .mp3 file is stored in a different directory.
  {
    try
    {
      this.fileName = new String(fileName);
      resource = getClass().getResource(fileName);
      media = new Media(resource.toString());
      mediaPlayer = new MediaPlayer(media);
      mediaPlayer.setAutoPlay(true);
    }
    catch (Exception e)
    {
      System.out.println("Error finding audio file (this message should never appear)!");
    }
  }
  public void setFileName(String newFileName) //Changes the .mp3 file that the media player is using
  {
    fileName = newFileName;
  }
  public String getFileName()
  {
    return fileName;
  }
  private MediaPlayer getMediaPlayer() //This is used throughout this program and should not be references externally
  {
    return mediaPlayer;
  }
  public void setVolume(double volume) //Changes the volume of the song playing. The volume must be a double between 0.0 (muted) and 1.0 (max volume) inclusive
  {
    if (volume >= 0 && volume <= 1)
    {
       getMediaPlayer().setVolume(volume);
    }
    else
    {
      System.out.println("Invalid value for the volume (this message should never appear)!");
    }
  }
  public double getVolume() //Returns the value of the volume
  {
    return getMediaPlayer().getVolume();
  }
  public boolean isMute() //checks to see if the player is currently muted. Returns true if muted (no music currently playing), false if not muted (music currently playing)
  {
    return getMediaPlayer().isMute();
  }
  public void play() //Plays the specific .mp3 file that the MediaPlayer is using
  {
    try
    {
      getMediaPlayer().play();
    }
    catch (Exception e)
    {
      System.out.println("Error playing audio file (this message should never appear)!");
    }
  }
  public void stop() //Stops the specific .mp3 file that the MediaPlayer is using. If it is played again, it will start at the beginning
  {
    try
    {
      getMediaPlayer().stop();
    }
    catch (Exception e)
    {
      System.out.println("Error stopping audio file (this message should never appear)!");
    }
  }
  public void pause() //Pauses the specific .mp3 file that the MediaPlayer is using. If it is played again, it will start at the time it was paused
  {
    try
    {
      getMediaPlayer().pause();
    }
    catch (Exception e)
    {
      System.out.println("Error pausing audio file (this message should never appear)!");
    }
  }
}

//In another program, all that you would need to use this object would be the following syntax:
//GameMusicPlayer gmp = new GameMusicPlayer("FILE NAME HERE.mp3");
//Make sure that the .mp3 file is in the same directory as your program. If the file is in a different directory, you will have to specify the exact file path
//You would then have access to all of the methods contained within, including play(), pause(), and stop()