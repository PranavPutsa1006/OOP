import java.applet.*;
import java.awt.*;
public class smiley extends Applet
{
	//Drawing Smiley
	public void paint(Graphics g)
	{
		g.setColor(Color.BLUE);
		g.drawOval(100, 100, 150, 150);
		g.drawOval(140, 140, 15, 15);
		g.setColor(Color.BLUE);
		g.fillOval(140, 140, 15, 15);
		g.drawOval(195, 140, 15, 15);
		g.setColor(Color.BLUE);
		g.fillOval(195, 140, 15, 15);
		g.drawArc(150, 190, 50, 20, 180, 180);
	}
}