import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Allows the game to be paused, resumed, and exited.
 * Allows the player to change their keybinds and volume at anytime via the settings. 
 * 
 * @author David Jiang 
 * @version 2022/06/16
 */
public class PauseButton extends ScrollActor
{
    public Label resume;
    public Label settings;
    public Decor blur;
    public Decor panel;
    public Decor exit;
    
    /**
     * Initializes a pause button.
     */
    public PauseButton(){
        setup();
        createPausePanel();
    }
    
    public void act(){
        clickedPauseButton();
    }
    
    /**
     * Sets up the pause button
     */
    public void setup(){
        GreenfootImage pause = new GreenfootImage("orangeButton.png");
        pause.scale(30,30);
        pause.drawImage(DetailsRenderer.pause,0,0);
        setImage(pause);
    }
    
    /**
     * Sets up the pause panel
     */
    public void createPausePanel(){
        GreenfootImage backButton = new GreenfootImage("orangeButton.png");
        backButton.setFont(DetailsRenderer.constantiaB30);
        backButton.drawString("EXIT",63,30);
        exit = new Decor(backButton);

        GreenfootImage resumeButton = new GreenfootImage("orangeButton.png");
        resumeButton.setFont(DetailsRenderer.constantiaB30);
        resumeButton.drawString("RESUME",40,28);
        resume = new Label(resumeButton);

        GreenfootImage settingsButton = new GreenfootImage("orangeButton.png");
        settingsButton.setFont(DetailsRenderer.constantiaB30);
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
    }

    
    /**
     * Performs actions depending on what button is clicked.
     */
    public void clickedPauseButton(){
        // Adds the pause panel
        if(Greenfoot.mouseClicked(this)){
            Player.paused = true;
            getWorld().addObject(blur,getWorld().getWidth()/2,getWorld().getHeight()/2);
            getWorld().addObject(panel,getWorld().getWidth()/2,getWorld().getHeight()/2);
            getWorld().addObject(resume,getWorld().getWidth()/2,150);
            getWorld().addObject(settings,getWorld().getWidth()/2,200);
            getWorld().addObject(exit,getWorld().getWidth()/2,250);
        }
        // Resumes game
        if(Greenfoot.mouseClicked(resume)){
            Player.paused = false;
            removePausePanel();
        }
        // Changes to setting screen
        if(Greenfoot.mouseClicked(settings)){
            removePausePanel();
            getWorld().changeWorld(new Settings(Player.levels[Player.level]));
        }
        // Changes to starting screen
        if(Greenfoot.mouseClicked(exit)){
            removePausePanel();
            getWorld().changeWorld(new StartingScreen());
        }
    }

    /**
     * Removes the pause panel
     */
    public void removePausePanel(){
        getWorld().removeObject(blur);
        getWorld().removeObject(panel);
        getWorld().removeObject(resume);
        getWorld().removeObject(settings);
        getWorld().removeObject(exit);
    }
}
