/**
 * This program uses classes, methods, if-statements, 
 * and key controls to create a simple game. The user 
 * can play the game by pressing the 'J' and 'K' keys 
 * to move a bat at the bottom of the screen. The 
 * bat is meant to be used for preventing a ball, 
 * that is moving randomly around the window, from
 * hitting the bottom edge of the window. The goal
 * of the game is to get as many bounces off of the
 * bat as you can. After every five bounces off of  
 * the bat, the speed of the ball will increase.
 * 
 * @author M. Allen
 * @author Andrew Krause
 * CS120 Section 01
 * Fall 2019
 * 
 * Programming Assignment 7
 * November 21, 2019
 */

import java.awt.Color;
import javax.swing.JLabel;

public class Driver
{
	// Instantiation of variables
    private GameWindow window;
    private JLabel bounces, speed;
    private Bat bat;
    private Ball ball;

    /**
     * post : Driver constructor run and game set-up performed
     */
    public static void main( String[] args )
    {
        Driver d = new Driver();
        d.makeGame();
    }

    /**
     * post: constructor sets up window and creates all needed objects
     */
    private void makeGame()
    {
        // Create window with animating timer and keyboard input response.
        window = new GameWindow( this );
        window.setTitle( "Bounce, bounce, bounce..." );
        window.setLocation( 50, 50 );
        window.setSize( 500, 500 );
        window.setBackground( Color.white );

        // Add ball and bat to window.
        ball = new Ball( window.getWidth() / 2 - 10, window.getHeight() / 2 - 10, window );
        bat = new Bat( window.getWidth() / 2 - 50, window.getHeight() - 50, window );

        // add labels for speed and number of bounces
        bounces = new JLabel( "Bounces: " + ball.getBounces() );
        bounces.setBounds( 10, 10, 150, 20 );
        window.add( bounces );
        speed = new JLabel( "Speed: " + ball.getSpeed() );
        speed.setBounds( 10, 30, 150, 20 );
        window.add( speed );

        window.startTimer();
        window.repaint();
    }

    /**
     * @param action : an integer describing the action taken post: the method
     *            corresponding to the appropriate action will be taken
     */
    public void handleAction( int action )
    {
        if ( action == GameWindow.MOVE )
        {
        	ball.checkBatHit( bat );
            ball.move();
            ball.checkBatHit( bat );
            bat.move();
            ball.checkBatHit( bat );
            bounces.setText( "Bounces: " + ball.getBounces() );
            speed.setText( "Speed: " + ball.getSpeed() );
        }
        else
        {
            bat.setDirection( action );
        }
    }
}
