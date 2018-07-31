import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Player {
    public Vector2D position;
    public Vector2D velocity;
    private List<Vector2D> vertices;
    private Polygon polygon;
    public double angle = 0.0;

    private Random random = new Random();

    public Player() {

        this.position = new Vector2D();
        this.vertices = Arrays.asList(
                new Vector2D(),
                new Vector2D(0, 16),
                new Vector2D(20, 8)
        );
        this.polygon = new Polygon();
        this.velocity = new Vector2D(3.5f,0);
    }

    public void run(){
        this.position.addUp(this.velocity);
        this.backToScreen();

    }

    private void backToScreen(){
        if (this.position.x > 1024){
            this.position.set(0, this.random.nextInt(600));
        }
        if (this.position.x <0){
            this.position.set(1024, this.random.nextInt(600));
        }
        if (this.position.y > 600){
            this.position.set(this.random.nextInt(1024), 0);
        }
        if (this.position.y <0){
            this.position.set(this.random.nextInt(1024), 600);
        }
    }

    public void render(Graphics graphics) {
        this.polygon.reset();

        Vector2D center = this.vertices
                .stream()
                .reduce(new Vector2D(), (v1, v2) -> v1.add(v2))
                .multiply(1/ this.vertices.size())
                .rotate(this.angle);

        Vector2D translation = this.position.subtract(center);

//        List<Vector2D> list = new ArrayList<>();
//        this.vertices.forEach(vertex -> list.add(vertex.add(translation)));

        this.vertices.stream()
                .map(vertex ->vertex.rotate(angle))
                .map(vertex -> vertex.add(translation))
                .forEach(vertex -> polygon.addPoint((int) vertex.x, (int) vertex.y));
        graphics.setColor(Color.RED);
        graphics.fillPolygon(this.polygon);

    }

}