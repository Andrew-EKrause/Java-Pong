/**
 * Draws a simple graphical rectangle.
 * 
 * @author Dave Riley
 * @author M. Allen
 */
import javax.swing.*;
import java.awt.*;

@SuppressWarnings( "serial" )
public class Rectangle extends JComponent
{
    /**
     * post: getX() == x and getY() == y and getWidth() == w and getHeight() ==
     * h and getBackground() == Color.black
     */
    public Rectangle( int x, int y, int w, int h )
    {
        super();
        setBounds( x, y, w, h );
        setBackground( Color.black );
    }
    
    /**
     * post: Draws a filled Rectangle and the upper left corner is ( getX(),
     * getY() ) and the rectangle's dimensions are getWidth() and getHeight()
     * and the rectangle's color is getBackground()
     */
    public void paint( Graphics g )
    {
        g.setColor( getBackground() );
        g.fillRect( 0, 0, getWidth(), getHeight() );
        paintChildren( g );
    }
}