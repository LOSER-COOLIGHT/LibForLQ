import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import java.io.*;

public class AI extends MIDlet
{
	Display display;
	MainCanvas mc;
	public AI(){
		display=Display.getDisplay(this);
		mc=new MainCanvas();
		display.setCurrent(mc);
	}
	public void startApp(){
	}
	public void destroyApp(boolean unc){
	}
	public void pauseApp(){
	}
}
class MainCanvas extends Canvas
{
	Image imgUp0;//上移并拢
	Image imgUp1;//上移左脚
	Image imgUp2;//上移右脚
	Image imgDown0;//下移并拢
	Image imgDown1;//下移左脚
	Image imgDown2;//下移右脚
	Image imgLeft0;//左移并拢
	Image imgLeft1;//左移左脚
	Image imgLeft2;//左移右脚
	Image imgRight0;//右移并拢
	Image imgRight1;//右移左脚
	Image imgRight2;//右移右脚
	Image currentImg;//当前英雄图片
	int X;//英雄x坐标
	int Y;//英雄y坐标
	static int actionLast2;//上两步的动作
	static int actionLast;//上一步的动作
	int flag;//步数哨兵

	public MainCanvas(){

		try{
			imgUp0=Image.createImage("/sayo14.png");
			imgUp1=Image.createImage("/sayo24.png");
			imgUp2=Image.createImage("/sayo04.png");

			imgDown0=Image.createImage("/sayo10.png");
			imgDown1=Image.createImage("/sayo00.png");
			imgDown2=Image.createImage("/sayo20.png");

			imgLeft0=Image.createImage("/sayo12.png");
			imgLeft1=Image.createImage("/sayo02.png");
			imgLeft2=Image.createImage("/sayo22.png");

			imgRight0=Image.createImage("/sayo16.png");
			imgRight1=Image.createImage("/sayo06.png");
			imgRight2=Image.createImage("/sayo26.png");
			
			currentImg=imgUp0;	
			X=120;
			Y=100;
			flag=0;
		}
		catch(IOException e){
			e.printStackTrace();
	
		}

	} 

	public void paint(Graphics g){
		g.setColor(0,220,220);
		g.fillRect(0,0,getWidth(),getHeight());
		g.drawImage(currentImg,X,Y,0);
	}

	public void keyPressed(int keyCode){

		int action=getGameAction(keyCode);

		if(action==actionLast&&actionLast!=actionLast2){
			flag=0;//转向步调重置
		}

		switch(action){
			case UP:
				currentImg=imgUp0;
				if(actionLast==UP){
					Y-=6;
					if(Y<0){
						Y=0;//上边界控制
					}
					flag++;
					if(flag%4==1){
						currentImg=imgUp1;
					}
					else if(flag%4==3){
						currentImg=imgUp2;
					}
					else if(flag%4==0||flag%4==2){
						currentImg=imgUp0;
					}
				}
			
			break;
			case DOWN:
				currentImg=imgDown0;
				if(actionLast==DOWN){
					Y+=6;
					if(Y>getHeight()-10){
						Y=getHeight()-10;//下边界控制
					}
					flag++;
					if(flag%4==1){
						currentImg=imgDown1;
					}
					else if(flag%4==3){
						currentImg=imgDown2;
					}
					else if(flag%4==0||flag%4==2){
						currentImg=imgDown0;
					}
				}
			
			break;
			case RIGHT:
				currentImg=imgRight0;
				if(actionLast==RIGHT){
					X+=6;
					if(X>getWidth()-10){
						X=getWidth()-10;//右边界控制
					}
					flag++;
					if(flag%4==1){
						currentImg=imgRight1;
					}
					else if(flag%4==3){
						currentImg=imgRight2;
					}
					else if(flag%4==0||flag%4==2){
						currentImg=imgRight0;
					}
				}
			
			break;
			case LEFT:
				currentImg=imgLeft0;
				if(actionLast==LEFT){
					X-=6;
					if(X<0){
						X=0;//左边界控制
					}
					flag++;
					if(flag%4==1){
						currentImg=imgLeft1;
					}
					else if(flag%4==3){
						currentImg=imgLeft2;
					}
					else if(flag%4==0||flag%4==2){
						currentImg=imgLeft0;
					}
				}
			
			break;
		}
		actionLast2=actionLast;//记录上两步
		actionLast=action;//记录上一步
		repaint();
	}
}