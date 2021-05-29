import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.util.Random;
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
public class tlightss extends Applet implements ActionListener, Runnable, MouseListener
{
String msg= " ";
TextField tf;
Thread t;

public boolean game_over=false;
static boolean gameOver=false;
static String score="";
public void init()

{
setBackground(Color.black);
t = new Thread(this);
t.start();
this.addMouseListener(this);
Random rand=new Random();
int c=((rand.nextInt())%6)+1;

numgenerators g=new numgenerators();
final TextField tf=new TextField();

tf.setBounds(400,500, 80,30);
Button b=new Button("Click Here");  
b.setBounds(400,550,80,30);  
b.addActionListener(new ActionListener(){  
public void actionPerformed(ActionEvent e){  
        tf.setText(g.number(1, c));  
        score=score+g.number(1, c);
    }
});
final TextField tf2=new TextField();

tf2.setBounds(600,500, 80,30);  
Button b2=new Button("Click Here");  
b2.setBounds(600,550,80,30);  
b2.addActionListener(new ActionListener(){  
public void actionPerformed(ActionEvent e){  
        tf2.setText(g.number(2, c));
        score=score+g.number(2, c);
    }
});
final TextField tf3=new TextField();

tf3.setBounds(800,500, 80,30);  
Button b3=new Button("Click Here");  
b3.setBounds(800,550,80,30);  
b3.addActionListener(new ActionListener(){  
public void actionPerformed(ActionEvent e){  
        tf3.setText(g.number(3, c));
        score=score+g.number(3, c);
    }
});
final TextField tf4=new TextField();
tf4.setBounds(1000,500, 80,30);  
Button b4=new Button("Click Here");  
b4.setBounds(1000,550,80,30);  
b4.addActionListener(new ActionListener(){  
public void actionPerformed(ActionEvent e){  
        tf4.setText(g.number(4, c));
        score=score+g.number(4, c);
    }
});
final TextField tf5=new TextField();
tf5.setBounds(1200,500, 80,30);  
Button b5=new Button("Click Here");  
b5.setBounds(1200,550,80,30);  
b5.addActionListener(new ActionListener(){  
public void actionPerformed(ActionEvent e){  
        tf5.setText(g.number(5, c));
        score=score+g.number(5, c);
    }
});
final TextField tf6=new TextField();

tf6.setBounds(1400,500, 80,30);  
Button b6=new Button("Click Here");  
b6.setBounds(1400,550,80,30);  
b6.addActionListener(new ActionListener(){  
public void actionPerformed(ActionEvent e){  
        tf6.setText(g.number(6, c));
        score=score+g.number(6, c);
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
public void start()
{
	
}

public void run(){
tlightss b=new tlightss();
try{

while(true){
// Request repaint
gameOver=b.getgameOver();
repaint();
Thread.sleep(20);
}
}
catch(Exception e){
}
}
private boolean getgameOver() {
	// TODO Auto-generated method stub
	return false;
	}


public void update(Graphics g){
paint(g);
tlightss b=new tlightss();
if(score!="000000"||score!="00000"||score!="0000"||score!="000"||score!="00"||score!="0")
{
	 gameOver=true;
}
else
{
	gameOver=false;
}
b.getgameOver();

}
public void paint(Graphics g){
	g.setFont(new Font("arial",Font.PLAIN,30));
	g.setColor(Color.gray);
	g.fillRect(450,10,250,80);
	g.setColor(Color.blue);
	g.drawString("Score:  "+score,480,50);
	tlightss b1=new tlightss();
g.setColor(Color.red);
Font f2=new Font("Arial",Font.BOLD,50);
g.setFont(f2);
g.drawString(msg, 6, 100);
Dimension d = getSize();
g.setColor(Color.yellow);
int x = (int)(Math.random() * d.width);
int y = (int)(Math.random() * d.height);
g.fillOval(x, y, 15, 15);
if (gameOver==true)
{
	setBackground(Color.WHITE);
	g.setColor(Color.black);
	Font f=new Font("Arial",Font.BOLD,50);
	g.setFont(f);
	g.drawString("GAME OVER", 300, 300);
	Font f1=new Font("Arial",Font.BOLD,40);
	g.setFont(f1);
	g.drawString("Score: "+score, 400, 350);
}
}
@Override
public void actionPerformed(ActionEvent arg0) {
// TODO Auto-generated method stub

}
@Override
public void mouseClicked(MouseEvent arg0) {
	// TODO Auto-generated method stub
	t.start();
}
@Override
public void mouseEntered(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseExited(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}
@Override
public void mousePressed(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseReleased(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}


}