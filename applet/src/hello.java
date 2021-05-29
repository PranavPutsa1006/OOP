import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
public class hello extends Applet
{
	public void paint(Graphics g)
	{
		g.setColor(Color.RED);
		g.drawString("Welcome",40,40);
		g.setColor(Color.BLUE);
		g.drawString("Pranav",40,50);
	}
}