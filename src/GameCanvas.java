import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameCanvas extends JPanel {

    BufferedImage backBuffered;
    Graphics graphics;

    int countStar = 0;
    int countBullet =0;
    int countPlayerBullet = 0;

    List<Star> stars;

    List<BulletEnemy> bulletEnemies;

    List<BulletEnemy> playerBullets;

    Background background;

    public Player player = new Player();
    public Enemy enemy = new Enemy();

    private Random random = new Random();


    public GameCanvas() {
        this.setSize(1024, 600);

        this.setupBackBuffered();

        this.setupCharacter();

        this.setVisible(true);
    }

    private void setupBackBuffered() {
        this.backBuffered = new BufferedImage(1024, 600, BufferedImage.TYPE_4BYTE_ABGR);
        this.graphics = this.backBuffered.getGraphics();
    }

    private void setupCharacter() {
        this.background = new Background();
        this.stars = new ArrayList<>();
        this.bulletEnemies = new ArrayList<>();
        this.playerBullets = new ArrayList<>();
        this.setupPlayer();
        this.setupEnemy();
    }

    private void setupPlayer() {
        this.player.position.set(100, 200);
    }

    private void setupEnemy() {
        this.enemy.position.set(800, 400);
        this.enemy.image = this.loadImage("resources/images/circle.png");
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(this.backBuffered, 0, 0, null);
    }

    public void renderAll() {
        this.background.render(this.graphics);
        this.stars.forEach(star -> star.render(graphics));
        this.player.render(this.graphics);
        this.enemy.render(this.graphics);
        this.bulletEnemies.forEach(bulletEnemy -> bulletEnemy.render(graphics));
        this.playerBullets.forEach(playerBullet -> playerBullet.render(graphics));
        this.repaint();
    }

    public void runAll() {
        this.createStar();
        this.stars.forEach(star -> star.run());
        this.runEnemy();
        this.enemyShoot(this.enemy);
        this.bulletEnemies.forEach(bulletEnemy -> bulletEnemy.run());
        this.playerShoot(this.enemy);
        this.playerBullets.forEach(playerBullet -> playerBullet.run());
        this.player.run();
    }


    private void enemyShoot(Enemy enemy){
        if (this.countBullet ==15) {
            BulletEnemy bulletEnemy = new BulletEnemy();
            bulletEnemy.position.set(this.enemy.position);
            bulletEnemy.velocity.set(this.player.velocity);
            bulletEnemy.image = this.loadImage("resources/images/circle.png");
            this.bulletEnemies.add(bulletEnemy);

            this.countBullet = 0;
        } else {
            this.countBullet +=1;
        }
    }


    private void playerShoot(Enemy enemy){
        if (this.countPlayerBullet ==10){
            BulletEnemy playerBullet = new BulletEnemy();
            playerBullet.position.set(this.player.position);
            playerBullet.velocity.set(5,0);
            playerBullet.image = this.loadImage("resources/images/circle.png");
            this.playerBullets.add(playerBullet);

            this.countPlayerBullet = 0;
        } else {
            this.countPlayerBullet +=1;
        }

    }

    private void createStar() {
        if (this.countStar == 3) {

            Star star = new Star();
            star.position.set(1024, this.random.nextInt(600));
            star.velocity.set(-(random.nextInt(5) + 1), 0);
            star.image = this.loadImage("resources/images/star.png");
            this.stars.add(star);

            this.countStar = 0;
        } else {
            this.countStar += 1;
        }
    }

    private void runEnemy() {
        Vector2D velocity = this.player.position
                .subtract(this.enemy.position)
                .normalize()
                .multiply(2.5f);
        this.enemy.velocity.set(velocity);
        this.enemy.run();
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            return null;
        }
    }
}