package geometric_primitives;

public class PointDouble {

    public final double x;
    public final double y;
    public final double z;

    public PointDouble(double x, double y) {
        this.x = x;
        this.y = y;
        z = 0.0;
    }

    @Override
    public String toString() {
        return "PointDouble{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    public PointDouble(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
