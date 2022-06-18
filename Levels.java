import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A class to facilitate the creation of levels. 
 * Superclass of all of the levels
 * 
 * @author David Jiang 
 * @version 2022/06/16
 */
public abstract class Levels extends ScrollWorld
{
    public Player player;
    public PauseButton pause;
    public BreakingBlock[] breakingBlock;
    public Decor[] trackers;
    public Decor[] skyScroll = new Decor[2];
    public Decor[] voidScroll = new Decor[2];
    public Decor cheatButton;
    public Block topBorder;
    /**
    * Constructor for instances of Levels.
    * 
    * @param width Width of the world, in pixels
    * @param height Height of the world, in pixels
    * @param cellSize Size of the cell of the world, in pixels
    * @param inverted Whether or not the world scrolls with inverted backgrounds
    * @param numBreakingBlocks Number of BreakingBlocks to be created in the world
    * 
    */
    public Levels(int width, int height, int cellSize, boolean inverted, int numBreakingBlocks)
    {
        super(width,height,cellSize,inverted);

        GreenfootImage button = new GreenfootImage("orangeButton.png");
        button.scale(5,5);
        cheatButton = new Decor(button);

        breakingBlock = new BreakingBlock[numBreakingBlocks];
        trackers = new Decor[numBreakingBlocks];
        
        pause = new PauseButton();
        player = new Player();
    }

    /**
     * Toggles the cheats. If on, the player experiences no gravity
     */
    public void toggleCheats(){
        if(Greenfoot.mouseClicked(cheatButton)){
            Player.cheatsOn = !Player.cheatsOn;
        }
    }

    /**
     * Generates the images of the spawn platform, with fading to black
     * 
     * @param image The image of each individual block
     * @param toDraw The GreenfootImage that acts as the strip
     * @param isLastLevel whether the level this is drawn for is the last level
     */
    public void createSpawnPlatform(GreenfootImage image, GreenfootImage toDraw,boolean isLastLevel){
        GreenfootImage tempImage = new GreenfootImage(image);
        for(int n = 0; n < 48; n++){
            for(int m = 0; m < 48; m++){
                tempImage.setColorAt(m,n,new Color((int)(tempImage.getColorAt(m,n).getRed()*Math.pow(199,n)/Math.pow(200,n)),
                        (int)(tempImage.getColorAt(m,n).getGreen()*Math.pow(199,n)/Math.pow(200,n)),
                        (int)(tempImage.getColorAt(m,n).getBlue()*Math.pow(199,n)/Math.pow(200,n))));
            }
        }
        for(int i = 0; i < 42; i++){
            for(int n = 0; n < 48; n++){
                for(int m = 0; m < 48; m++){
                    if(i != 0){
                        tempImage.setColorAt(n,m,new Color((int)(tempImage.getColorAt(n,m).getRed()*Math.pow(199,48)/Math.pow(200,48)),
                                (int)(tempImage.getColorAt(n,m).getGreen()*Math.pow(199,48)/Math.pow(200,48)),
                                (int)(tempImage.getColorAt(n,m).getBlue()*Math.pow(199,48)/Math.pow(200,48))));
                    }
                }
            }
            toDraw.drawImage(tempImage,0,i*48);
        }
        if(!isLastLevel){
            addObject(new Block(DetailsRenderer.barrier),125,225);
        }else{
            addObject(new Block(DetailsRenderer.barrier),586,225);
        }
    }

    /**
     * Implements a sky
     * 
     * @param color The color for the sky
     */
    public void addSky(Color color){
        GreenfootImage sky = new GreenfootImage(716,400);
        sky.setColor(color);
        sky.fill();
        for(int i = 0; i < 2; i++){
            Decor sky1 = new Decor(sky);
            skyScroll[i] = sky1;
            addObject(sky1,-358+716*i,-getHeight()/2);
        }
    }

    /**
     * Adds the void (the place that the player falls into)
     * 
     * @param color The color for the void
     */
    public void addVoid(Color color){
        GreenfootImage bottom = new GreenfootImage(716,2064);
        bottom.setColor(color);
        bottom.fill();
        for(int i = 0; i < 2; i++){
            Decor black = new Decor(bottom);
            voidScroll[i] = black;
            addObject(black,-358+716*i,getHeight()/2+1200);
        }
    }

    /**
     * Scrolls the sky and the void
     */
    public void scrollSkyAndVoid(){
        for(Decor sky: skyScroll){
            if(sky.getX() < -716/2){
                sky.setLocation(sky.getX()+1432,sky.getY());
            }
            if(sky.getX() > 716*3/2){
                sky.setLocation(sky.getX()-1432,sky.getY());
            }
        }
        for(Decor black: voidScroll){
            if(black.getX() < -716/2){
                black.setLocation(black.getX()+1432,black.getY());
            }
            if(black.getX() > 716*3/2){
                black.setLocation(black.getX()-1432,black.getY());
            }
        }
    }

    /**
     * Adds a breaking block to the world
     * 
     * @param block The BreakingBlock object to add
     * @param tracker The tracker to track the block
     * @param x The x coordinate of the block
     * @param y The y coordinate of the block
     */
    public void addBreakingBlock(BreakingBlock block,Decor tracker,int x,int y){
        addObject(block,x,y);
        addObject(tracker,x,y);
        block.x = x;
        block.y = y;
    }

    /**
     * Resets the status of all breaking blocks.
     */
    public void resetBreakingBlocks(){
        for(int i = 0; i < breakingBlock.length; i++){
            BreakingBlock block = breakingBlock[i];
            if(block.removed && Player.warpedToCheckpoint){
                addObject(block,trackers[i].getX(),trackers[i].getY());
                block.removed = false;
            }
            if(Player.warpedToCheckpoint){
                block.reset();
            }
        }
    }

    /**
     * Creates all of the breaking blocks to be added in the world
     * 
     * @param image The sprite of the BreakingBlock
     * @param duration The number of frames between each time the BreakingBlock cracks
     */
    public void setupBreakingBlocks(GreenfootImage image,int duration){
        if(breakingBlock.length != 0){
            for(int i = 0; i < breakingBlock.length; i++){
                BreakingBlock cobblestone = new BreakingBlock(image,duration);
                breakingBlock[i] = cobblestone;
                Decor tracker = new Decor(new GreenfootImage(1,1));
                trackers[i] = tracker;
            }
        }
    }
}
