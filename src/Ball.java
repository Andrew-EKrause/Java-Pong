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
 * @author Andrew Krause
 * CS120 Section 01
 * Fall 2019
 * 
 * Programming Assignment 7
 * November 21, 2019
 */

import java.awt.Color;

import javax.swing.JLabel;

public class Ball 
{
	// Global Instance variables 
	// for ball object
	private Oval ball;
	private JLabel end;
	private int ballSize = 20;
	private int batLength = 100;
	private int ballSpeed = 1;
	private int upRight = 1;
	private int upLeft = 2;
	private int downRight = 3;
	private int downLeft = 4;
	private int Counter;
	private int Direction;
	private int winSize = 500;
	private int gameOver = 100;
	private GameWindow window;
	
	// Method creates game ball with attributes
	public Ball ( int x, int y, GameWindow window )
	{
		ball = new Oval(x, y, ballSize, ballSize);
		ball.setBackground(Color.red);
		window.add(ball);
		window.repaint();
		this.window = window;
		Direction = (int)(Math.random() * 2 + 1);
	}
	
	// Method moves the ball in different directions
	public void move()
	{	
		// Instantiation of movement variables
	    int x = ball.getX();
		int y = ball.getY();
		int Right = window.getWidth() - ballSize;
		int Base = window.getBottomEdge();
		 
		/**
		 * Primary movements: upRight, downRight, 
		 * upLeft, and downLeft
		 */
		
		// Movement is up and to the right
		if (Direction == upRight)
		{
			ball.setLocation(x + ballSpeed, y - ballSpeed);
		}
		
		// Movement is up and to the left
		else if (Direction == upLeft)
		{
			ball.setLocation(x - ballSpeed, y - ballSpeed);
		}
		
		// Movement is down and to the right
		else if (Direction == downRight)
		{
			ball.setLocation(x + ballSpeed, y + ballSpeed);
		}
		
		// Movement is down and to the left
		else if (Direction == downLeft)
		{
			ball.setLocation(x - ballSpeed, y + ballSpeed);
		}
		
		/**
		 * Movements for when the ball comes in contact
		 * with the left edge, right edge, or top edge 
		 * of the window
		 */
		
		// upLeft and hitting left corner of window
		if ((x <= 0) && (y <= 0))
		{
			Direction = downRight;
		}
		
		// upRight and hitting right corner of window
		else if ((x >= Right) && (y <= 0))
		{
			Direction = downLeft;
		}
		
		// upRight and hitting right side of window
		else if ((Direction == upRight) && (x >= Right))
		{
			Direction = upLeft;
		}
		
		// upLeft and hitting left side of window
		else if ((Direction == upLeft) && (x <= 0))
		{
			Direction = upRight;
		}
		
		// downRight and hitting right side of window
		else if ((Direction == downRight) && (x >= Right))
		{
			Direction = downLeft;
		}
		
		// downLeft and hitting left side of window
		else if ((Direction == downLeft) && (x <= 0))
		{
			Direction = downRight;
		}
		
		// upRight and hitting top of window
		else if ((Direction == upRight) && (y <= 0))
		{
			Direction = downRight;
		}
		
		// upLeft and hitting top of window
		else if ((Direction == upLeft) && (y <= 0))
		{
			Direction = downLeft;
		}
		
		// If hitting bottom of window
		else if ((Direction == downRight || Direction == downLeft) && (y >= Base))
		{
			window.stopTimer();
		    end = new JLabel( "Game Over");
		    end.setBackground(Color.RED);
		    end.setBounds( (winSize/2 - gameOver/3), (winSize/2 - gameOver/3), gameOver, gameOver);
		    window.add( end );
		    window.repaint();
		}	
		  	
	}

	// Method checks when the ball hits the bat
	public void checkBatHit( Bat b )
	{
		// Instantiation of variables for collision
		int batX = b.getX();
		int batY = b.getY();
		int ballX = ball.getX();
		int ballY = ball.getY();
		
		// Movement is down and to the right and hits bat 					
		if ((Direction == downRight) && (ballY >= batY - ballSize) && (ballY <= batY - ballSize + ballSpeed) && (ballX >= batX) && (ballX <= batX + batLength))
		{
			Counter++;
				
			// Increases speed every five 
			// times the ball hits the bat
			if (Counter % 5 == 0)
			{
				ballSpeed++;
			}
				
			Direction = upRight;
		}
		
		//FIXME -- WHENEVER SPEED IS 5 AND DIRECTION IS DOWNLEFT, THE BALL GOES THROUGH THE BOARD...ASK PROFESSOR FOR SUGGESTIONS\\
		
		// Movement is down and to the left and hits bat 			
		else if ((Direction == downLeft) && (ballY >= batY - ballSize) && (ballY <= batY - ballSize + ballSpeed) && (ballX >= batX) && (ballX <= batX + batLength))
		{
			Counter++;
				
			// Increases speed every five 
			// times the ball hits the bat
			if (Counter % 5 == 0)
			{
				ballSpeed++;
			}
				
			Direction = upLeft;
		}

	}
	
	// Method returns the number of bounces
	// of the ball off of the bat
	public int getBounces()
	{
		return Counter;
	}
	
	// Method returns the speed of the ball
	public int getSpeed()
	{
		return ballSpeed;
	}
	
}
