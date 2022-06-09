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

        addPauseButton();
    }

    public void addPauseButton(){
        Font constantia = new Font("Constantia",true,false,30);

        GreenfootImage backButton = new GreenfootImage("orangeButton.png");
        backButton.setFont(new Font("Constantia",true,false,30));
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
}
