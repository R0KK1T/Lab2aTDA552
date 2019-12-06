import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    // Just a single image, TODO: Generalize
    BufferedImage volvoImage;
    BufferedImage saabImage;
    BufferedImage scaniaImage;
    // To keep track of a single cars position
    Point scaniaPoint = new Point(0, 200);
    Point saabPoint = new Point(0, 100);
    Point volvoPoint = new Point();
    HashMap<Car, Point> pics = new HashMap<>();

    // TODO: Make this general for all cars
    void moveIt(Car car, int x, int y){
        pics.get(car).x = x;
        pics.get(car).y = y;
    }
    void initHashMap(ArrayList<Car> cars){
        for (Car car : cars) {
            if(car.getClass() == Volvo240.class){
                pics.put(car, volvoPoint);
            }
            else if(car.getClass() == Saab95.class){
                pics.put(car, saabPoint);
            }
            else if(car.getClass() == Scania.class){
                pics.put(car, scaniaPoint);
            }
        }
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Remember to right click src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            saabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
            scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(volvoImage, volvoPoint.x, volvoPoint.y, null); // see javadoc for more info on the parameters
        g.drawImage(saabImage, saabPoint.x, saabPoint.y + 100, null);
        g.drawImage(scaniaImage, scaniaPoint.x, scaniaPoint.y + 200, null);

    }
}
