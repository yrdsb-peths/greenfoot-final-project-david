import greenfoot.*;

/**
 * An actor in a ScrollWorld.
 * Will move with camera if isCameraFollower is true.
 * Will follow the background if isCameraFollower is false.
 * Actors that are not subclasses of this class will follow the camera.
 * 
 * Note: Parts of the the code were made with the reference to Sven van Nigtevecht's code
 * 
 * @author David Jiang 
 * @version 2022/06/16
 */
public abstract class ScrollActor extends Actor
{
    private boolean isCameraFollower;
    private ScrollWorld world;
    public boolean timer = false;
    
    // Corrdinate systems
    private int camX, camY;
    private int globalX, globalY;
    
    /** Sets up a new ScrollActor. */
    public ScrollActor()
    {
        camX = 0;
        camY = 0;
        globalX = 0;
        globalY = 0;
        isCameraFollower = false;
        world = null;
    }
    
    /** 
     * Sets your location relative to the visible world. 
     * 
     * @param x The x coordinate using Greenfoot's coordinate system
     * @param y The y coordinate using Greenfoot's coordinate system
     */
    public void setLocation(int x, int y)
    {
        if (world == null) return;
        super.setLocation(x,y);
        int halfWorldWidth = world.getWidth() /2;
        int halfWorldHeight = world.getHeight() /2;
        camX = x -halfWorldWidth;
        camY = y -halfWorldHeight;
        globalX = x +(world.getCameraX() -halfWorldWidth);
        globalY = y +(world.getCameraY() -halfWorldHeight);
    }
    
    /**
     * Gets the x coordinate of the actor relative using the global coordinate system
     * 
     * @return The x coordinate of the actor in the global coordinate system
     */
    public int getGlobalX(){
        return globalX;
    }
    
    /**
     * Gets the y coordinate of the actor relative using the global coordinate system
     * 
     * @return The y coordinate of the actor in the global coordinate system
     */
    public int getGlobalY(){
        return globalY;
    }
    
    /**
     * Gets the y coordinate of the actor relative using the camera coordinate system
     * 
     * @return The y coordinate of the actor in the camera coordinate system
     */
    public int getCamY(){
        return camY;
    }
    
    /** 
     * Sets your location in the global coordinate system.
     * 
     * @param x The x coordinate of the global space to be set to
     * @param y The y coordinate of teh global space to be set to
     */
    public void setGlobalLocation(int x, int y)
    {
        setLocation(x -world.getCameraX() -world.getWidth() /2, y -world.getCameraY() -world.getHeight() /2);
    }
    
    /** 
     * Gets the ScrollWorld that the actor is currently in
     * 
     * @return The ScrollWorld that the actor is currently in
     */
    public ScrollWorld getWorld()
    {
        return world;
    }
    
    /** 
     * Returns whether the actor is a camera follower or not. 
     * 
     * @return Whther the actor follows the camera
     */
    public boolean isCameraFollower()
    {
        return isCameraFollower;
    }
    
    /** 
     * Sets whether or not the ScrollActor will follow the camera. 
     * 
     * @param value Whether or not the ScrollActor follows the camera. True if yes. 
     */
    void setIsCameraFollower(boolean value)
    {
        this.isCameraFollower = value;
        this.world = (ScrollWorld) super.getWorld();
        if (value) {
            setLocation(getX(), getY());
        }
    }
}