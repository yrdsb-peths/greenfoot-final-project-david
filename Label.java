import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A Label class that allows you to display a textual value on screen.
 * 
 * The Label is an actor, so you will need to create it, and then add it to the world
 * in Greenfoot.  If you keep a reference to the Label then you can change the text it
 * displays.  
 *
 * @author Amjad Altadmri (modified by David Jiang)
 * @version 1.2
 */
public class Label extends Actor
{
    private static final Color transparent = new Color(0,0,0,0);
    
    // Information for the appearance of the text
    private String value;
    private int fontSize  = 30;
    private Color lineColor = Color.BLACK;
    private Color fillColor = Color.WHITE;
    private GreenfootImage image;

    /**
     * Create a new label to display an integer
     * 
     * @param value The integer to be displayed
     * @param fontSize The font size of the integer to be displayed
     */
    public Label(int value, int fontSize)
    {
        this(Integer.toString(value), fontSize);
    }

    /**
     * Create a new label to display a string
     * 
     * @param value The string to be displayed
     * @param fontSize The font size of the string to be displayed
     */
    public Label(String value, int fontSize)
    {
        this.value = value;
        this.fontSize = fontSize;
        updateImage();
    }

    /**
     * Creates a new label with the given image
     * 
     * @param image The image to be displayed
     */
    public Label(GreenfootImage image){
        this.image = image;
        updateImage(image);
    }

    /**
     * Sets the value as text
     * 
     * @param value The text to be show
     */
    public void setValue(String value)
    {
        this.value = value;
        updateImage();
    }

    /**
     * Sets the value as integer
     * 
     * @param value The value to be show
     */
    public void setValue(int value)
    {
        this.value = Integer.toString(value);
        updateImage();
    }

    /**
     * Sets the font size of the text
     * 
     * @param fontsize The new font size of the text
     */
    public void setFontSize(int fontSize){
        this.fontSize = fontSize;
        updateImage();
    }

    /**
     * Sets the line color of the text
     * 
     * @param lineColor The line color of the text
     */
    public void setLineColor(Color lineColor)
    {
        this.lineColor = lineColor;
        updateImage();
    }

    /**
     * Sets the fill color of the text
     * 
     * @param fillColor The fill color of the text
     */
    public void setFillColor(Color fillColor)
    {
        this.fillColor = fillColor;
        updateImage();
    }

    /**
     * Gets the current fill color
     * @return The fill color
     */
    public Color getFillColor(){
        return fillColor;
    }
    
    /**
     * Update the image on screen based on current appearance information
     */
    public void updateImage()
    {
        if(image == null){
            setImage(new GreenfootImage(value, fontSize, fillColor, transparent, lineColor));
        }else{
            image.setColor(Color.WHITE);
            image.fill();
            image.setColor(Color.BLACK);
            image.setFont(new Font(fontSize));
            image.drawString(value,image.getWidth()/2-value.length()*fontSize/4+1,image.getHeight()*fontSize/20-fontSize/4);
        }
    }

    /**
     * Updates the text image based on an image
     * 
     * @param image The new diaplay image  
     */
    public void updateImage(GreenfootImage image){
        setImage(image);
    }
}