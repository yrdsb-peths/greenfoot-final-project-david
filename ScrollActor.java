import greenfoot.*;

/**
 * An actor in a ScrollWorld.
 * Will move with camera if isCameraFollower is true.
 * Will follow the background if isCameraFollower is false.
 * Actors that are not subclasses of this class will follow the camera.
 */
public abstract class ScrollActor extends Actor
{
    private int camX, camY; // Location with reference to camera coordinates ((0,0) is at the center of the visible world/screen)
    private int globalX, globalY; // Location relative to the larger space
    private boolean isCameraFollower; // Whether or not actor will move with the camera
    private ScrollWorld world; // World that the actor is in (null if no world)
    public boolean timer = false;
    
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
    
    public void setTimer(boolean state){
        timer = state;
    }
    
    /** Sets your location relative to the visible world. */
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
    
    public int getGlobalX(){
        return globalX;
    }
    
    public int getGlobalY(){
        return globalY;
    }
    
    /** Sets your location in the larger space. */
    public void setGlobalLocation(int x, int y)
    {
        setLocation(x -world.getCameraX() -world.getWidth() /2, y -world.getCameraY() -world.getHeight() /2);
    }
    
    /** Returns the ScrollWorld you're in. */
    public ScrollWorld getWorld()
    {
        return world;
    }
    
    /** Returns whether the actor is a camera follower or not. */
    public boolean isCameraFollower()
    {
        return isCameraFollower;
    }
    
    /** Sets whether or not the ScrollActor will follow the camera. */
    void setIsCameraFollower(boolean value)
    {
        this.isCameraFollower = value;
        this.world = (ScrollWorld) super.getWorld();
        if (value) {
            setLocation(getX(), getY());
        }
    }
}