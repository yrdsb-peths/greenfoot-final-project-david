import greenfoot.*;
import java.util.ArrayList;

/**
 * A scrollable world
 * The entire area (inculding non-visible parts) is called the larger space.
 * The visible area is called the camera.
 * The camera's coordinate system sets the center of the visible world to be (0,0), 
 * with east and north being the positive directions. This will always be relative to the visible world
 * The larger space's coordinate system sets the top left of the starting visible world to be (0,0)
 * with east and south being the positive directions. As the camera moves, the coordinates of the larger space
 * being shown will also change
 * 
 * Note: Parts of the the code were made with the reference to Sven van Nigtevecht's code
 * 
 * @author David Jiang 
 * @version 2022/06/16
 */
public abstract class ScrollWorld extends World
{
    private final int width, height, cellSize;
    private final boolean inverted;
    private final ArrayList<ScrollActor> objects; // To store non-camera following objects
    private final ArrayList<ScrollActor> camFollowers; // To store camera following objects

    private int camX, camY; // Position of the camera relative to the larger space
    private int startingX, startingY;

    private final GreenfootImage bigBackground, back, topExtended;
    private int scrollPosX, scrollPosY;

    /**
     * Intializes a ScrollWorld
     * 
     * @param width The width of the world in pixels
     * @param height The height of the world in pixels
     * @param cellSize The cell size of the world in pixels
     * @param inverted Whether or not the scrolled background is inverted
     */
    public ScrollWorld(int width, int height, int cellSize, boolean inverted)
    {
        super(width, height, cellSize, false);
        this.back = getBackground();
        this.width = back.getWidth();
        this.height = back.getHeight();
        this.cellSize = cellSize;
        this.inverted = inverted;
        topExtended = null;

        objects = new ArrayList<ScrollActor>();
        camFollowers = new ArrayList<ScrollActor>();

        camX = getWidth() /2;
        camY = getHeight() /2;
        startingX = camX;
        startingY = camY;

        scrollPosX = 0;
        scrollPosY = 0;
        // bigBackground is the image that the camera scrolls over
        if(inverted){
            bigBackground = new GreenfootImage(width*3,height*2);
        }else{
            bigBackground = new GreenfootImage(width*2, height*2);
        }
        setNewBackground(back);
    }

    /** 
     * Moves the camera to a particular location in larger space (moves the visible the world). 
     * 
     * @param x The amount to move in the x direction (positive is right)
     * @param y The amount to move in the y direction(positive is down)
     */
    public void moveCam(int x, int y)
    {
        x += camX;
        y += camY;
        moveBackground((x-camX) * cellSize, (y-camY) * cellSize);
        for(ScrollActor a : objects){
            a.setLocation(a.getX() - (x-camX), a.getY() -(y-camY));
        }
        camX = x;
        camY = y;
    }

    /**
     * Moves the background to give the appearance of the actors moving (up/down)
     * 
     * @param x The x distance to redraw the background at
     * @param y The y distance to redraw the background at
     */
    public void moveBackground(int x, int y)
    {
        scrollPosY -= y;
        scrollPosX -= x;
        if(inverted){
            scrollPosY %= getHeight()*2;
            scrollPosX %= getWidth()*2;
            getBackground().drawImage(bigBackground, scrollPosX,scrollPosY);
        }else{
            while (scrollPosY < 0){
                scrollPosY += getHeight();
            }
            while (scrollPosX < 0){
                scrollPosX += getWidth();
            }
            scrollPosY %= getHeight();
            scrollPosX %= getWidth();
            getBackground().drawImage(bigBackground, scrollPosX -getWidth(),scrollPosY -getHeight());
        }
    }

    /** 
     * Sets the background of the world
     * 
     * @param background The new background
     */
    private void setNewBackground(GreenfootImage background)
    {
        if(inverted){
            bigBackground.drawImage(background, 0,background.getHeight());
            bigBackground.drawImage(background, background.getWidth()*2,background.getHeight());
            bigBackground.drawImage(background, background.getWidth()*2,0);
            bigBackground.drawImage(background, 0,0);
            background.mirrorHorizontally();
            bigBackground.drawImage(background, background.getWidth(),background.getHeight());
            bigBackground.drawImage(background, background.getWidth(),0);
            back.clear();
            back.drawImage(bigBackground, scrollPosX,scrollPosY);
        }else{
            bigBackground.drawImage(background, 0,background.getHeight());
            bigBackground.drawImage(background, background.getWidth(),background.getHeight());
            bigBackground.drawImage(background, 0,0);
            bigBackground.drawImage(background, background.getWidth(),0);
            bigBackground.drawImage(background,background.getWidth()*2,0);
            bigBackground.drawImage(background,background.getWidth()*2,background.getHeight());
            back.clear();
            back.drawImage(bigBackground, scrollPosX,scrollPosY);
        }
    }

    /**
     * Adds an object that follows the camera. The location is relative to the camera (the visible world), not from the larger space.
     * 
     * @param object The object to be added
     * @param x The x coordinate of the object to be added, using the camera's coordinate system
     * @param y The y coordinate of the object to be added, using the camera's coordinate system
     */
    public void addCameraFollower(ScrollActor object, int x, int y)
    {
        super.addObject(object, getWidth() /2 +x, getHeight() /2 +y);
        camFollowers.add(object);
        object.setIsCameraFollower(true);
    }

    /**
     * Adds an object to the the world using the larger space's coordinate system
     * 
     * @parma object The object to add to the world
     * @param x The x coordinate to add the object to
     * @param y The y coordinate to add the object to
     */
    public void addObject(Actor object, int x, int y)
    {
        if(object instanceof ScrollActor) {
            ScrollActor temp = (ScrollActor) object;
            super.addObject(temp,x,y);
            objects.add(temp);
            temp.setIsCameraFollower(false);
        }else{
            super.addObject(object,x,y);
        }
    }

    /**
     * Removes an object from the world. 
     * 
     * @param object The object to be removed
     */
    public void removeObject(Actor object)
    {
        super.removeObject(object);
        if (object instanceof ScrollActor) {
            ScrollActor temp = (ScrollActor) object;
            objects.remove(temp);
            camFollowers.remove(temp);
            temp.setIsCameraFollower(false);
        }
    }

    /** 
     * Returns the camera's x coördinate in larger space. 
     */
    public int getCameraX()
    {
        return camX;
    }

    /** 
     * Returns the camera's y coördinate in larger space. 
     */
    public int getCameraY()
    {
        return camY;
    }

    /**
     * Changes the current world by fading to black
     * 
     * @param world The world to change to
     */
    public void changeWorld(World world){
        TransitionAssist temp = new TransitionAssist(world);
        addObject(temp,width/2,height/2);
    }
}