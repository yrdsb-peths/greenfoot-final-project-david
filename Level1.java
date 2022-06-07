import greenfoot.*;

public class Level1 extends ScrollWorld
{   
    private GreenfootImage grass = new GreenfootImage("grass block.png");
    private GreenfootImage dirt = new GreenfootImage("dirt.png");
    private GreenfootImage dirt1 = new GreenfootImage(48,48*43);
    private GreenfootImage darkness = new GreenfootImage(716,48*43);
    private GreenfootImage sky = new GreenfootImage("sky.png");
    private Decor[] skyScroll = new Decor[2];
    private Decor[] darknessScroll = new Decor[2];

    /**
     * Constructor for objects of class DemoWorld.
     */
    public Level1()
    {
        super(711, 400, 1,false);
        sky.scale(716,400);
        prepare();
        addCameraFollower(new Player(), 0, 75);
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        darkness.setColor(Color.BLACK);
        darkness.fill();
        for(int i = 0; i < 2; i++){
            Decor black = new Decor(darkness);
            darknessScroll[i] = black;
            addObject(black,-716/2+716*i,getHeight()/2+48*25);
        }
        grass.scale(48,48);
        dirt.scale(48,48);
        for(int n = 0; n < 48; n++){
            for(int m = 0; m < 48; m++){
                dirt.setColorAt(m,n,new Color((int)(dirt.getColorAt(m,n).getRed()*Math.pow(199,n)/Math.pow(200,n)),
                    (int)(dirt.getColorAt(m,n).getGreen()*Math.pow(199,n)/Math.pow(200,n)),
                    (int)(dirt.getColorAt(m,n).getBlue()*Math.pow(199,n)/Math.pow(200,n))));
            }
        }
        for(int i = 0; i < 42; i++){
            for(int n = 0; n < 48; n++){
                for(int m = 0; m < 48; m++){
                    if(i != 0){
                        dirt.setColorAt(n,m,new Color((int)(dirt.getColorAt(n,m).getRed()*Math.pow(199,48)/Math.pow(200,48)),
                            (int)(dirt.getColorAt(n,m).getGreen()*Math.pow(199,48)/Math.pow(200,48)),
                            (int)(dirt.getColorAt(n,m).getBlue()*Math.pow(199,48)/Math.pow(200,48))));
                    }
                }
            }
            dirt1.drawImage(dirt,0,i*48);
        }

        for(int i = 0; i < 2; i++){
            Decor sky = new Decor(this.sky);
            skyScroll[i] = sky;
            addObject(sky,-716/2+716*i,-getHeight()/2);
        }

        GreenfootImage empty = new GreenfootImage(50,1000);
        Block barrier = new Block(false,empty);
        addObject(barrier,125,225);
        for(int i = 0; i < 16; i++){
            addGrass(-48*4+48*i,345);
        }
        BlockBuilder bob = new BlockBuilder();
        addObject(bob, -100, -100);

        GreenfootImage houseImage = new GreenfootImage("house.png");
        houseImage.scale(87*7,28*7);
        Decor house = new Decor(houseImage);
        addObject(house,100,223);
    }

    public void act(){
        for(Decor sky: skyScroll){
            if(sky.getX() < -716/2){
                sky.setLocation(sky.getX()+716*2,sky.getY());
            }
            if(sky.getX() > 716*3/2){
                sky.setLocation(sky.getX()-716*2,sky.getY());
            }
        }
        for(Decor black: darknessScroll){
            if(black.getX() < -716/2){
                black.setLocation(black.getX()+716*2,black.getY());
            }
            if(black.getX() > 716*3/2){
                black.setLocation(black.getX()-716*2,black.getY());
            }
        }
    }

    private void addGrass(int x, int y){
        Block grassBlock = new Block(false,grass);
        addObject(grassBlock,x,y);
        Block dirtBlock = new Block(false,dirt1);
        addObject(dirtBlock,x,y+48*22);
    }
}