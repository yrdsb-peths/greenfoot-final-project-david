import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Levels here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Levels extends ScrollWorld
{
    public Label resume;
    public Label settings;
    public Decor blur;
    public Decor panel;
    public PauseButton pause;
    public Decor exit;
    /**
     * Constructor for objects of class Levels.
     * 
     */
    public Levels(int width, int height, int cellSize, boolean inverted)
    {
        super(width,height,cellSize,inverted);
        createPauseButton();
    }

    public void createPauseButton(){
        Font constantia = new Font("Constantia",true,false,30);

        GreenfootImage backButton = new GreenfootImage("orangeButton.png");
        backButton.setFont(constantia);
        backButton.drawString("EXIT",63,30);
        exit = new Decor(backButton);

        GreenfootImage resumeButton = new GreenfootImage("orangeButton.png");
        resumeButton.setFont(constantia);
        resumeButton.drawString("RESUME",40,28);
        resume = new Label(resumeButton);

        GreenfootImage settingsButton = new GreenfootImage("orangeButton.png");
        settingsButton.setFont(constantia);
        settingsButton.drawString("SETTINGS",30,28);
        settings = new Label(settingsButton);

        GreenfootImage tint = new GreenfootImage(711,400);
        tint.setColor(new Color(128,128,128,75));
        tint.fill();
        blur = new Decor(tint);

        GreenfootImage frame = new GreenfootImage(350,200);
        frame.setColor(Color.GRAY);
        frame.fill();
        panel = new Decor(frame);

        pause = new PauseButton();
    }

    public void clickedPauseButton(){
        if(Greenfoot.mouseClicked(pause)){
            Player.paused = true;
            addObject(blur,getWidth()/2,getHeight()/2);
            addObject(panel,getWidth()/2,getHeight()/2);
            addObject(resume,getWidth()/2,150);
            addObject(settings,getWidth()/2,200);
            addObject(exit,getWidth()/2,250);
        }
        if(Greenfoot.mouseClicked(resume)){
            Player.paused = false;
            removePausePanel();
        }
        if(Greenfoot.mouseClicked(settings)){
            removePausePanel();
            changeWorld(new Settings(Player.levels[Player.level]));
        }
        if(Greenfoot.mouseClicked(exit)){
            removePausePanel();
            changeWorld(new StartingScreen());
        }
    }

    public void removePausePanel(){
        removeObject(blur);
        removeObject(panel);
        removeObject(resume);
        removeObject(settings);
        removeObject(exit);
    }

    public void createSpawnPlatform(GreenfootImage image, GreenfootImage toDraw){
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
        addObject(new Block(new GreenfootImage(50,1000)),125,225);
    }
    
    public void addBreakingBlock(BreakingBlock block,LocationTracker tracker,int x,int y){
        addObject(block,x,y);
        addObject(tracker,x,y);
        block.x = x;
        block.y = y;
    }
}
