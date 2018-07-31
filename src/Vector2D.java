public class Vector2D {
    public float x;
    public float y;

    //    Khởi tạo bằng constructor
    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    //    Khai báo vector 0
    public Vector2D(){
        this.x =0;
        this.y =0;
    }

    //    Tra gan toa do vector A bang toa do vector B
    public Vector2D set(float x, float y){
        this.x = x;
        this.y = y;
        return this;
    }

    //
    public Vector2D set(Vector2D vector2D){
        return this.set(vector2D.x, vector2D.y);
    }

    //    Cong don vector
    public Vector2D addUp(float x, float y){
        this.x += x;
        this.y += y;
        return this;
    }

    //    Cong don vector truyen ca vector vao
    public Vector2D addUp(Vector2D vector2D){
        return this.addUp(vector2D.x, vector2D.y);
    }

    //    Cong hai vector voi dau vao la gia tri xy
    public Vector2D add(float x, float y){
        return new Vector2D(this.x + x, this.y + y);
    }

    //    Cong hai vector voi dau vao la vector
    public Vector2D add(Vector2D vector2D){
        return this.add(vector2D.x, vector2D.y);
    }

    //  Tru Vector
    public Vector2D subtractBy(float x, float y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    //    Tru Vector
    public Vector2D subtractBy(Vector2D vector2D) {
        return this.subtractBy(vector2D.x, vector2D.y);
    }

    //    Tru Vector
    public Vector2D subtract(float x, float y) {
        return new Vector2D(this.x - x, this.y - y);
    }

    //    Tru vector dau vao la vector
    public Vector2D subtract(Vector2D vector2D) {
        return this.subtract(vector2D.x, vector2D.y);
    }

    //    Do dai vector
    public float length() {
        return (float) Math.sqrt((double)(this.x * this.x + this.y * this.y));
    }

    //    Nhan vector voi 1 so
    public Vector2D multiply(float number) {
        this.x *= number;
        this.y *= number;
        return this;
    }

    //    Copy vector
    public Vector2D copy() {
        return new Vector2D(this.x, this.y);
    }

    //    Xoay vector
    public Vector2D rotate(double angle) {
        double radians = Math.toRadians(angle);
        float cos = (float) Math.cos(radians);
        float sin = (float) Math.sin(radians);
        return new Vector2D(this.x * cos - this.y * sin, this.x * sin + this.y * cos);
    }

    //    Chuan Hoa Vector
    public Vector2D normalize() {
        float length = this.length();
        return new Vector2D(this.x / length, this.y / length);
    }

}