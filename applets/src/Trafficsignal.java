import java.awt.*;
import java.applet.*;
import java.awt.event.*;
/*<applet code="Traffic" width=700 height=600>
</applet>*/
public class Trafficsignal extends Applet implements Runnable
{
Thread t;
int c,j=0;
String s;
Font f;
public void init()
{
	s=new String();
	s="Stop";
	c=1;
	f=new Font("Arial",Font.BOLD,50);
	t=new Thread(this);
}
public void start()
{
t.start();
}
public void run()
{
for(int i=0;i<100;i++)
{
try
{
	if(c==1)
	{
		Thread.sleep(2000);
		repaint();
		c=2;
		s="Ready";
	}
	else if(c==2)
	{
		Thread.sleep(1000);
		repaint();
		c=3;
		s="Go";
	}
	else if(c==3)
	{
		Thread.sleep(3000);
		repaint();
		c=1;
		s="Stop";
	}
}
catch(Exception e)
{
}
}}
public void paint(Graphics g)
{
	g.setFont(f);
	g.fillRoundRect(80, 80, 100, 200, 10, 10);
	g.setColor(Color.GRAY);
	g.fillRect(100, 280, 60, 100);
	g.setColor(Color.black);
	if(c==1)
	{
		g.drawString(s, 200, 150);
		g.setColor(Color.red);
		g.fillOval(100, 100, 60, 60);
	}
	else if(c==2)
	{
		g.drawString(s, 200, 200);
		g.setColor(Color.yellow);
		g.fillOval(100, 150, 60, 60);
	}
	else if(c==3)
	{
		g.drawString(s, 200, 250);
		g.setColor(Color.green);
		g.fillOval(100, 200, 60, 60);
	}
}
}
