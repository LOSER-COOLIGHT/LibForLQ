import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import java.io.*;

public class AI extends MIDlet{
    Display display;
    MainCanvas mc;
    public AI{
        display=Display.getDisplay(this);
        mc=new MainCanvas();
        display.setcurrent(mc);
    }
    public void startApp(){}
    public void destroyApp(boolean unc){}
    public void pauseApp(){}
}

class MainCanvas extends Canvas{
    Image img,img1,img2,img3,img4;
    public MainCanvas(){
        try{
            img1=Image.createImage("/sayo10.png");//正or下
            img2=Image.createImage("/sayo12.png");//左
            img3=Image.createImage("/sayo16.png");//右
            img4=Image.createImage("/sayo14.png");//上
            img=img1;
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public void paint(Graphics g){
        g.setColor(0,0,0);
        g.fillRect(0,0,getWidth(),getHeight());
        g.drawImage(img,110,150,0);
    }
    public void Keypressed(int keyCode){
        int action=getGameAction(keyCode);
        if(action==LEFT){
            img=img2;
        }
        else if(action=RIGHT){
            img=img3;
        }
        else if(action=UP){
            img=img4;
        }
        else if(action=DOWN){
            img=img1;
        }
    }
}