import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level2 extends Levels
{
    private GreenfootImage stone1 = new GreenfootImage(48,48*43);
    private GreenfootImage stone = new GreenfootImage("stone.png");
    private GreenfootImage darkness = new GreenfootImage(716,48*43);
    private Decor[] darknessScroll = new Decor[2];
    public Player player;
    /**
     * Constructor for objects of class Level2.
     * 
     */

    public Level2()
    {
        super(711,400,1,false);
        stone.scale(48,48);
        darkness.setColor(Color.BLACK);
        darkness.fill();
        construct();
        player = new Player();
        addCameraFollower(player, 0, 75);
    }

    public void construct(){
        for(int i = 0; i < 2; i++){
            Decor black = new Decor(darkness);
            darknessScroll[i] = black;
            addObject(black,-716/2+716*i,getHeight()/2+48*25-10);
        }
        createSpawnPlatform(stone,stone1);
        for(int i = 0; i < 16; i++){
            addStone(-48*4+48*i,287);
        }
        addObject(new Decor(new GreenfootImage("gate.png")),getWidth()/2-5,233);
        addCameraFollower(pause,-320,-170);
    }
    
    public void act(){
        for(Decor black: darknessScroll){
            if(black.getX() < -716/2){
                black.setLocation(black.getX()+716*2,black.getY());
            }
            if(black.getX() > 716*3/2){
                black.setLocation(black.getX()-716*2,black.getY());
            }
        }
        clickedPauseButton();
    }
    
    public void addStone(int x, int y){
        Block stoneBlock = new Block(false,stone1);
        addObject(stoneBlock,x,y+48*22);
    }
}
