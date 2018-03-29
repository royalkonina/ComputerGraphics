package GenericPrimitives;

public class Point<T extends Number>{
    private T x, y, z;

    public Point(T x, T y, T z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point(T x, T y)
    {
        this.x = x;
        this.y = y;
        this.z = (T) Integer.valueOf(0);
    }

    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }

    public T getY() {
        return y;
    }

    public void setY(T y) {
        this.y = y;
    }

    public T getZ() {
        return z;
    }

    public void setZ(T z) {
        this.z = z;
    }
}
