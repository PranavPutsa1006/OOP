import java.awt.*;
import java.applet.*;
import java.awt.event.*;
/*<applet code="Traffic" width=700 height=600>
</applet>*/
public class Trafficsignals extends Applet implements Runnable,ItemListener
{
	String msg="";
    Checkbox  stop,ready,go;
    CheckboxGroup cbg;
    Thread t;
    int i=0,a=1,j=0;
    public void init()
    {
                cbg = new CheckboxGroup();
                stop = new Checkbox("Stop", cbg, false);
                ready = new Checkbox("Ready", cbg, false);
                go= new Checkbox("Go", cbg, false);
                add(stop);
                add(ready);
                add(go);
                stop.addItemListener(this);
                ready.addItemListener(this);
                go.addItemListener(this);
               
    }
    public void start()
    {
    	t=new Thread(this);
    	t.start();
    }
    public void itemStateChanged(ItemEvent arg0) 
    {
    	repaint();    	
    }
    public void run()
    {
    	for(i=20;i>=0;i--)//countdown
    		{
    		try
    		{
    			Thread.sleep(3000);
    		}
    		catch(Exception e)
    		{
    			System.out.println(e);
    		}
    		if((i<=20 && i>3))//red
    			{
    			a=1;
    			repaint();
    			}
    		else if((i<=3 && i>0))//yellow
    			{
    			a=2;
    			repaint();
    			}
    		else if(i==0)//green
    			{
    			for(j=0;j<20;j++)
    			{
    				a=3;
    				try
    				{
    					Thread.sleep(1000);
    				}
    				catch(Exception e)
    				{
    					System.out.println(e);
    				}
    				repaint();
    			}
    			if(j==20)//end of green(return to red)
    				{
    				run();
    				}
    			}
    		}
    	repaint();
}
public void paint(Graphics g)
{
	msg=cbg.getSelectedCheckbox().getLabel();
	g.setColor(Color.black);//pole top
	g.fillRect(150,150,50,150);
	g.drawRect(150,150,50,150);
	g.setColor(Color.black);//POLE UP
	g.fillRect(150,150,50,150);
	g.drawRect(150,150,50,150);
	g.setColor(Color.GRAY);//POLE DOWN
	g.fillRect(165,300,20,155);
	g.drawRect(165,300,20,155);
	g.drawOval(150,150,50,50);//RED
	g.drawOval(150,200,50,50);//YELLOW
	g.drawOval(150,250,50,50);//GREEN
	g.setColor(Color.red);//COUNTDOWN STOP
	g.drawString(""+i,50,50);

	if((a==1&&msg.equals("Stop")==false)||(a==1&&msg.equals("Stop")==true))//REDSIGNAL
		{
		g.setColor(Color.red);
		g.fillOval(150,150,50,50);
		g.drawOval(150,150,50,50);
		g.setColor(Color.black);
		g.drawString("STOP", 50, 150);
		}
	else if(a!=1&&msg.equals("Stop")==false&&(a==2&&msg.equals("Ready")==false)||(a==2&&msg.equals("Ready")==true))//YELLOWSIGNAL
		{
		g.setColor(Color.yellow);
		g.fillOval(150,200,50,50);
		g.drawOval(150,200,50,50);
		g.setColor(Color.black);
		g.drawString("READY",50,200);
		}
	else if((a==3||msg.equals("Go")))//GREENSIGNAL
		{
		g.setColor(Color.blue);//countdown
		g.drawString(""+j,150,50);
		g.setColor(Color.green);
		g.fillOval(150,250,50,50);
		g.drawOval(150,250,50,50);
		g.setColor(Color.black);
		g.drawString("GO",50,250);
		}
	}
}