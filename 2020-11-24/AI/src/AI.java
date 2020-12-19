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
// class MainCanvas extends Canvas 
class MainCanvas extends Canvas implements Runnable
{
	Thread thread;

	Image[][] hero;
	Image[][] boss;
	Image currentImg;//当前英雄图片
	Image currentBossImg;

	int heroX;//英雄x坐标
	int heroY;//英雄y坐标
	
	int bossX;
	int bossY;
	
	int flag;//步数哨兵
	int bossFlag;

	int actionLast2;//上两步的动作
	int actionLast;//上一步的动作

	int bossActionLast2;
	int bossActionLast;

	public MainCanvas(){

		try{
			hero=new Image[3][4];
			boss=new Image[4][2];

			//第一维步子：0左，1正，2右
			//第二维方向：0下，2左，4上，6右
			for(int i=0;i<hero.length;i++){
				for(int j=0;j<hero[i].length;j++){
					hero[i][j]=Image.createImage("/sayo"+i+2*j+".png");
				}
			}

			//第一维方向：0下，2左，4上，6右
			//第二维步子：0左，1右
			for(int i=0;i<boss.length;i++){
				for(int j=0;j<boss[i].length;j++){
					boss[i][j]=Image.createImage("/manuke0"+2*i+j+".png");
				}
			}

			currentImg=hero[1][0];	
			// currentBossImg=Image.createImage("/manuke000.png");
			currentBossImg=boss[0][0];
				
			heroX=120;
			heroY=100;
			bossX=0;
			bossY=0;
			flag=0;
			bossFlag=0;

			thread=new Thread(this);
			thread.start();
		}
		catch(IOException e){
			e.printStackTrace();
	
		}

	} 

	public void paint(Graphics g){
		g.setColor(0,220,220);
		g.fillRect(0,0,getWidth(),getHeight());
		g.drawImage(currentImg,heroX,heroY,0);
		g.drawImage(currentBossImg,bossX,bossY,0);
	}

	public void keyPressed(int keyCode){

		int action=getGameAction(keyCode);

		if(action==actionLast&&actionLast!=actionLast2){
			flag=0;//转向步调重置
		}

		

		switch(action){
			case UP:
				currentImg=hero[1][2];
				if(actionLast==UP){
					heroY-=6;
					if(heroY<0){
						heroY=0;//上边界控制
					}
					this.move(hero,2);
				}
			
			break;
			case DOWN:
				currentImg=hero[1][0];
				if(actionLast==DOWN){
					heroY+=6;
					if(heroY>getHeight()-20){
						heroY=getHeight()-20;//下边界控制
					}
					this.move(hero,0);
				}
			
			break;
			case RIGHT:
				currentImg=hero[1][3];
				if(actionLast==RIGHT){
					heroX+=6;
					if(heroX>getWidth()-20){
						heroX=getWidth()-20;//右边界控制
					}
					this.move(hero,3);
				}
			
			break;
			case LEFT:
				currentImg=hero[1][1];
				if(actionLast==LEFT){
					heroX-=6;
					if(heroX<0){
						heroX=0;//左边界控制
					}
					this.move(hero,1);
				}
			
			break;
		}
		actionLast2=actionLast;//记录上两步
		actionLast=action;//记录上一步

		

		repaint();
	}

	public void move(Image[][] img,int n){
		flag++;
		if(flag%4==0||flag%4==2){
			currentImg=img[1][n];
		}
		else if(flag%4==1){
			currentImg=img[0][n];
		}
		else if(flag%4==3){
			currentImg=img[2][n];
		}
	}

	public int boss(){
		int difX;
		int difY;
		difX=bossX-heroX;
		difY=bossY-heroY;
		if(Math.abs(difX)>Math.abs(difY)){
			if(bossX>heroX){
				bossX-=3;
				return 2;
			}
			else{
				bossX+=3;
				return 6;
			}
		}
		else{
			if(bossY>heroY){
				bossY-=3;
				return 4;
			}
			else{
				bossY+=3;
				return 0;
			}
		}
		
	}

	public void run(){
		while(true){
			try{
				Thread.sleep(500);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}

			int bossAction=boss();

			if(bossAction==bossActionLast&&bossActionLast!=bossActionLast2){
				bossFlag=0;
			}

			if(bossAction>=0){
				if(bossFlag%2==0){
					switch(bossAction){
						case 0:currentBossImg=boss[0][0];bossFlag++;break;
						case 2:currentBossImg=boss[1][0];bossFlag++;break;
						case 4:currentBossImg=boss[2][0];bossFlag++;break;
						case 6:currentBossImg=boss[3][0];bossFlag++;break;
					}
				}
				else if(bossFlag%2==1){
					switch(bossAction){
						case 0:currentBossImg=boss[0][1];bossFlag++;break;
						case 2:currentBossImg=boss[1][1];bossFlag++;break;
						case 4:currentBossImg=boss[2][1];bossFlag++;break;
						case 6:currentBossImg=boss[3][1];bossFlag++;break;
					}
				}
				bossActionLast2=bossActionLast;//记录上两步
				bossActionLast=bossAction;//记录上一步
			}
			else{
				currentBossImg=boss[0][0];
			}
			repaint();
		}
		
		
	}

}