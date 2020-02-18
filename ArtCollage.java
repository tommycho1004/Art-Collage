/*************************************************************************
 *  Compilation:  javac ArtCollage.java
 *  Execution:    java ArtCollage
 *
 *  @author:Dong Gun Cho, dc1293@scarletmail.rutgers.edu, dc1293
 *
 *************************************************************************/

import java.awt.Color;

public class ArtCollage {

    // The orginal picture
    private Picture original;

    // The collage picture
    private Picture collage;

    // The collage Picture consists of collageDimension X collageDimension tiles
    private int collageDimension;

    // A tile consists of tileDimension X tileDimension pixels
    private int tileDimension;
    
    /*
     * One-argument Constructor
     * 1. set default values of collageDimension to 4 and tileDimension to 100
     * 2. initializes original with the filename image
     * 3. initializes collage as a Picture of tileDimension*collageDimension x tileDimension*collageDimension, 
     *    where each pixel is black (see all constructors for the Picture class).
     * 4. update collage to be a scaled version of original (see scaling filter on Week 9 slides)
     *
     * @param filename the image filename
     */
    public ArtCollage (String filename) {
	this(filename, 100, 4);
    }

    /*
     * Three-arguments Constructor
     * 1. set default values of collageDimension to cd and tileDimension to td
     * 2. initializes original with the filename image
     * 3. initializes collage as a Picture of tileDimension*collageDimension x tileDimension*collageDimension, 
     *    where each pixel is black (see all constructors for the Picture class).
     * 4. update collage to be a scaled version of original (see scaling filter on Week 9 slides)
     *
     * @param filename the image filename
     */
    public ArtCollage (String filename, int td, int cd) {

	collageDimension = cd;
    tileDimension = td;
    original = new Picture(filename);
    collage = new Picture(collageDimension*tileDimension,collageDimension*tileDimension);
    for (int col = 0; col<collage.width(); col++)
    {
        for (int row = 0; row<collage.height(); row++)
        {
            int cola = (col*original.width())/collage.width();
            int rowa = (row*original.height())/collage.height();
            collage.set(col, row, original.get(cola, rowa));
        }
    }
    }

    /*
     * Returns the collageDimension instance variable
     *
     * @return collageDimension
     */
    public int getCollageDimension() {

	return collageDimension;
    }

    /*
     * Returns the tileDimension instance variable
     *
     * @return tileDimension
     */
    public int getTileDimension() {

	return tileDimension;
    }

    /*
     * Returns original instance variable
     *
     * @return original
     */
    public Picture getOriginalPicture() {

	return original;
    }

    /*
     * Returns collage instance variable
     *
     * @return collage
     */
    public Picture getCollagePicture() {

	return collage;
    }
    
    /*
     * Display the original image
     * Assumes that original has been initialized
     */
    public void showOriginalPicture() {

	original.show();
    }

    /*
     * Display the collage image
     * Assumes that collage has been initialized
     */
    public void showCollagePicture() {

	collage.show();
    }

    /*
     * Replaces the tile at collageCol,collageRow with the image from filename
     * Tile (0,0) is the upper leftmost tile
     *
     * @param filename image to replace tile
     * @param collageCol tile column
     * @param collageRow tile row
     */
    public void replaceTile (String filename,  int collageCol, int collageRow) 
    {
	    Picture newP = new Picture(filename);
        for(int i = 0; i<collage.width(); i++)
        {
           for(int j = 0; j<collage.height(); j++)
           {
               if(i/tileDimension == collageCol && j/tileDimension == collageRow)
               {
                    int c = (i%tileDimension) * collageDimension * newP.width() / collage.width();
                    int r = (j%tileDimension) * collageDimension * newP.height() / collage.height();
                    Color color = newP.get(c,r);
                    collage.set(i, j, color);   
                }
            }
        }
    }
    
    /*
     * Makes a collage of tiles from original Picture
     * original will have collageDimension x collageDimension tiles, each tile
     * has tileDimension X tileDimension pixels
     */
    public void makeCollage () {
       for(int i = 0; i<collage.width(); i++){
        for(int j = 0; j<collage.height(); j++){
            int c = (i%tileDimension) * collageDimension * original.width() / collage.width();
            int r = (j%tileDimension) * collageDimension * original.height() / collage.height();
            Color color = original.get(c,r);
            collage.set(i, j, color);
            }
        }
    }
    /*
     * Colorizes the tile at (collageCol, collageRow) with component 
     * (see Week 9 slides, the code for color separation is at the 
     *  book's website)
     *
     * @param component is either red, blue or green
     * @param collageCol tile column
     * @param collageRow tile row
     */
    public void colorizeTile (String component,  int collageCol, int collageRow) 
    {
        for(int i = 0; i<collage.width(); i++)
        {
            for(int j = 0; j<collage.height(); j++)
            {
                Color color = collage.get(i, j);
                if(i/tileDimension == collageCol && j/tileDimension == collageRow)
                {
                    if(component.equals("red"))
                    {
                        int red = color.getRed();
                        collage.set(i, j, new Color(red, 0, 0));
                    }
                    if(component.equals("green"))
                    {
                        int green = color.getGreen();
                        collage.set(i, j, new Color(0, green, 0));
                    }
                    if(component.equals("blue"))
                    {
                        int blue = color.getBlue();
                        collage.set(i, j, new Color(0, 0, blue));
                    }
                }
            }
        }
    }

    /*
     * Greyscale tile at (collageCol, collageRow)
     * (see Week 9 slides, the code for luminance is at the book's website)
     *
     * @param collageCol tile column
     * @param collageRow tile row
     */

    public void greyscaleTile (int collageCol, int collageRow) 
    {
        for(int i = 0; i<collage.width(); i++)
        {
            for(int j = 0; j<collage.height(); j++)
            {
                Color color = collage.get(i, j);
                if(i/tileDimension == collageCol && j/tileDimension == collageRow)
                {
                    Color grey = Luminance.toGray(color);
                    collage.set(i, j, grey);
                }
            }
        }
    }


    // Test client 
    public static void main (String[] args) {

    }
}
