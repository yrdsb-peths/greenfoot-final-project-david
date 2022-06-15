import greenfoot.*;
import java.util.ArrayList;

/**
 * A scrollable world
 * The entire area (inculding non-visible parts) is called the larger space.
 * The visible area is called the camera.
 * The camera's location starts at the center of the visible world (screen).
 * 
 * Note: Parts of the the code were made with the help of Sven van Nigtevecht's code
 */
public abstract class ScrollWorld extends World
{
    private final int width, height, cellSize; // World size properties
    private final boolean inverted;
    private final ArrayList<ScrollActor> objects; // To store non-camera following objects
    private final ArrayList<ScrollActor> camFollowers; // To store camera following objects

    private int camX, camY; // Position of the camera relative to the larger space
    private int startingX, startingY;

    private final GreenfootImage bigBackground, back, topExtended; // 2 images to make modifying background easier
    private int scrollPosX, scrollPosY; // For scrolling the background

    public static int volume = 50;
    public static GreenfootSound[] music = {new GreenfootSound("Start.mp3"),new GreenfootSound("Story.mp3"),
                                            new GreenfootSound("Settings.mp3"),new GreenfootSound("Help.mp3"),
                                            new GreenfootSound("Level1.mp3"),new GreenfootSound("Level2.mp3"),
                                            new GreenfootSound("Level3.mp3"),new GreenfootSound("Level4.mp3"),
                                            new GreenfootSound("Level5.mp3"),new GreenfootSound("Level6.mp3"),
                                            new GreenfootSound("Ending.mp3")};

    /** Sets up a ScrollWorld. */
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
        if(inverted){
            bigBackground = new GreenfootImage(width*3,height*2);
        }else{
            bigBackground = new GreenfootImage(width*2, height*2);
        }
        setNewBackground(back);
    }

    /** Moves the camera to a particular location in larger space (moves the visible the world). */
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
     * This is done by redrawing the background onto the current one (in the larger space)
     * at certain coordinates, depending on the movements made in the x and y directions
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

    /** Sets the background of the world to be extra large (larger space) */
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
     * Adds an object that follows the camera.
     * The location is seen from the camera (the visible world), not from the larger space.
     */
    public void addCameraFollower(ScrollActor object, int x, int y)
    {
        super.addObject(object, getWidth() /2 +x, getHeight() /2 +y);
        camFollowers.add(object);
        object.setIsCameraFollower(true);
    }

    /**
     * Adds an object to the the world. If the given object is a ScrollActor 
     * or a subclass of it, the x and y coordinates are in the larger space.
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

    /** Removes an object from the world. */
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

    /** Returns the camera's x coördinate in larger space. */
    public int getCameraX()
    {
        return camX;
    }

    /** Returns the camera's y coördinate in larger space. */
    public int getCameraY()
    {
        return camY;
    }

    public void changeWorld(World world){
        TransitionAssist temp = new TransitionAssist(world);
        addObject(temp,width/2,height/2);
    }
}