import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;

public class sonic extends MIDlet implements Runnable {
  private MainCanvas main = new MainCanvas(this);
  
  public static boolean LoadOK;
  
  private Display display;
  
  public void vibrate(int paramInt) {
    try {
      this.display.vibrate(paramInt);
    } catch (Throwable throwable) {}
  }
  
  public sonic() {
    Display.getDisplay(this).setCurrent((Displayable)this.main);
    this.display = Display.getDisplay(this);
    (new Thread(this)).start();
  }
  
  public void startApp() {
    if (!LoadOK)
      try {
        LoadOK = true;
      } catch (Throwable throwable) {} 
  }
  
  public void run() {
    this.main.initAll();
    this.main.GameMain();
  }
  
  public void pauseApp() {
    MainCanvas.moveRsm = true;
    MainCanvas.drawRsm = true;
  }
  
  public void destroyApp(boolean paramBoolean) {}
  
  public String GetAppProperty(String paramString) {
    return getAppProperty(paramString);
  }
  
  public void doExit() {
    try {
      destroyApp(false);
      notifyDestroyed();
    } catch (Exception exception) {}
  }
}