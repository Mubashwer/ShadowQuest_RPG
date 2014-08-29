/* SWEN20003 Object Oriented Software Development
 * RPG Game Engine
 * Author: <Your name> <Your login>
 */

import org.newdawn.slick.SlickException;

/** Represents the camera that controls our viewpoint.
 */
public class Camera
{

    /** The unit this camera is following */
    private Player unitFollow;
    
    /** The width and height of the screen */
    /** Screen width, in pixels. */
    public final int screenwidth;
    /** Screen height, in pixels. */
    public final int screenheight;

    
    /** The camera's position in the world, in x and y coordinates. */
    private int xPos;
    private int yPos;

    
    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    
    /** Create a new World object. 
     * @throws SlickException */
    public Camera(Player player, int screenwidth, int screenheight) throws SlickException
    {   
        this.screenwidth = screenwidth;
        this.screenheight = screenheight;
        unitFollow = player;
        xPos = unitFollow.getxPos() - this.screenwidth/2;
        yPos = unitFollow.getyPos() - this.screenheight/2;
        
        
    }

    /** Update the game camera to recentre it's viewpoint around the player 
     */
    public void update()
    throws SlickException
    {
        xPos = unitFollow.getxPos() - screenwidth/2;
        yPos = unitFollow.getyPos() - screenheight/2;
    }
    
    /** Returns the minimum x value on screen 
     */
    public int getMinX(){
        return 0;
    }
    
    /** Returns the maximum x value on screen 
     */
    public int getMaxX(){
       return 96 * 72; 
    }
    
    /** Returns the minimum y value on screen 
     */
    public int getMinY(){
        return 0;
    }
    
    /** Returns the maximum y value on screen 
     */
    public int getMaxY(){
        return 96 * 72;
    }

    /** Tells the camera to follow a given unit. 
     */
    public void followUnit(Object unit)
    throws SlickException
    {
        // TO DO: Fill In
    }
    
}