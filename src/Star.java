import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

public class Star {

    public Vector2D position;
    public BufferedImage image;
    public Vector2D velocity;
    private Random random = new Random();
    public int width = random.nextInt(10) + 1;
    public int height = this.width;

    public Star() {
        this.position = new Vector2D();
        this.velocity = new Vector2D();
    }

    public void run() {
        this.position.addUp(this.velocity);
    }

    public void render(Graphics graphics) {

        graphics.drawImage(this.image, (int)this.position.x, (int)this.position.y, this.width, this.height, null);
    }
}