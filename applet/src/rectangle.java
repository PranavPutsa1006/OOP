import java.awt.*;
import java.applet.*;
public class rectangle extends Applet
{
	public void paint(Graphics g)
	{
		g.setColor(Color.DARK_GRAY);
		g.drawRect(80, 80, 150, 100);
		g.setColor(Color.RED);
		g.fillRect(80, 80, 150, 100);
	}
}