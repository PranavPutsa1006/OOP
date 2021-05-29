import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.util.Random;
import java.lang.Math;
/*<applet code="buttons3" width=200 height=300>
</applet> */

class numgenerators
{
	String number(int q,int c)
	{
		if(c==q)
		{
			return "1";
		}
		else
		{
			return "0";
		}
	}
}
public class tlights extends Applet implements ActionListener, Runnable
{
int flag=0;
int co=0,la=0;
String msg= " ";
TextField tf;
Thread t;
Message m=new Message();
public void init()

{
setBackground(Color.black);
t = new Thread(this);
t.start();
Random rand=new Random();
int c=((rand.nextInt())%6)+1;

numgenerators g=new numgenerators();
final TextField tf=new TextField(); 

tf.setBounds(400,500, 80,30);
Button b=new Button("REVEAL NOW!");  
b.setBounds(400,550,80,30);  
b.addActionListener(new ActionListener(){  
public void actionPerformed(ActionEvent e){  
        tf.setText(g.number(1, c)); 
        if(g.number(1, c)=="1")
        {
        	flag=1;
        	la=0;
		}
        else
        {
        	co++;
        }
    }
}); 
final TextField tf2=new TextField(); 

tf2.setBounds(600,500, 80,30);  
Button b2=new Button("REVEAL NOW!");  
b2.setBounds(600,550,80,30);  
b2.addActionListener(new ActionListener(){  
public void actionPerformed(ActionEvent e){  
        tf2.setText(g.number(2, c));
        if(g.number(2, c)=="1")
        {
        	flag=1;
        	la=1;
        }
        else
        	co++;
    }
}); 
final TextField tf3=new TextField(); 

tf3.setBounds(800,500, 80,30);  
Button b3=new Button("REVEAL NOW");  
b3.setBounds(800,550,80,30);  
b3.addActionListener(new ActionListener(){  
public void actionPerformed(ActionEvent e){  
        tf3.setText(g.number(3, c));
        if(g.number(3, c)=="1")
        {
        	flag=1;
        	la=2;
		}
        else
        	co++;
    }
}); 
final TextField tf4=new TextField(); 
tf4.setBounds(1000,500, 80,30);  
Button b4=new Button("REVEAL NOW");  
b4.setBounds(1000,550,80,30);  
b4.addActionListener(new ActionListener(){  
public void actionPerformed(ActionEvent e){  
        tf4.setText(g.number(4, c));
        if(g.number(4, c)=="1")
        {
        	flag=1;
        	la=3;
		}
        else
        	co++;
    }
}); 
final TextField tf5=new TextField(); 
tf5.setBounds(1200,500, 80,30);  
Button b5=new Button("REVEAL NOW");  
b5.setBounds(1200,550,80,30);  
b5.addActionListener(new ActionListener(){  
public void actionPerformed(ActionEvent e){  
        tf5.setText(g.number(5, c));
        if(g.number(5, c)=="1")
        {
        	flag=1;
        	la=4;
		}
        else
        	co++;
    }
}); 
final TextField tf6=new TextField(); 

tf6.setBounds(1400,500, 80,30);  
Button b6=new Button("REVEAL NOW");  
b6.setBounds(1400,550,80,30);  
b6.addActionListener(new ActionListener(){  
public void actionPerformed(ActionEvent e){  
        tf6.setText(g.number(6, c));
        if(g.number(6, c)=="1")
        {
        	flag=1;
        	la=5;
		}
        else
        	co++;
    }
});
add(tf);
add(tf2);
add(tf3);
add(tf4);
add(tf5);
add(tf6);
add(b);
add(b2);
add(b3);
add(b4);
add(b5);
add(b6);
setLayout(null);  
setVisible(true);  

//tf.setEchoChar('m');
}


public void run(){
	
	try{
		
		while(true){
			// Request repaint
			repaint();
			Thread.sleep(20);
		}
	}
	catch(Exception e){
	}	
}
public void update(Graphics g){
	paint(g);
}
public void paint(Graphics g){
g.drawString(msg, 6, 100);
Dimension d = getSize();
Random randem=new Random();
Font cf=g.getFont();
Font newFont=cf.deriveFont(cf.getSize()*100);
Font mf=new Font("Times new roman",1,60);
g.setFont(mf);
g.setColor(Color.blue);
g.drawString("Let's Do Some GAMBLING!",420,120);
Font mf2=new Font("Times new roman",1,30);
g.setFont(mf2);
g.setColor(Color.red);
g.drawString("INSTRUCTIONS",12,200);
Font mf3=new Font("Times new roman",1,25);
g.setFont(mf3);
g.setColor(Color.white);
g.drawString("Click on REVEAL NOW to reveal the corresponding bits and win the CASH!!",18,230);
int c=randem.nextInt();
if(c%3==0)
{
	g.setColor(Color.yellow);
}
else if(c%3==1)
{
	g.setColor(Color.WHITE);
}else
{
	g.setColor(Color.green);
}
if(flag==1)
{
	m.mess(getGraphics(),la);
	return;
}else if(co==6)
{
	m.mess(getGraphics(),co);
}
int x = (int)(Math.random() * d.width);
int y = (int)(Math.random() * d.height);
g.fillOval(x, y, 10, 10);
}
public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
	
}
}
class Message
{
	public void mess(Graphics g,int cp)
	{
		g.setColor(Color.black);
		g.drawRect(0, 0, 5000, 5000);
		g.fillRect(0, 0, 5000, 5000);
		g.setColor(Color.red);
		Font cf=g.getFont();
		Font newFont=cf.deriveFont(cf.getSize()*100);
		Font mf=new Font("Times new roman",1,70);
		g.setFont(mf);
		if(cp!=6)
			g.drawString("YOU HAVE WON:"+Math.pow(10, 5-cp),350,750);
		else if(cp==6)
			g.drawString("YOU HAVE LOST THE GAME",350,750);
			
	}
}