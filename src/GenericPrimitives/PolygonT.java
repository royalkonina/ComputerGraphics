package GenericPrimitives;

public class PolygonT<T extends Number> {

    public static final Long DEFAULT_Z = 0L;
    private Point<T>[] points;


    public PolygonT(Point<T>[] points) {
        this.points = points;
    }

    public Point<T> getPoint(int index) {
        return points[index];
    }

    public T getX(int index) {
        return points[index].getX();
    }

    public T getY(int index) {
        return points[index].getY();
    }

    public T getZ(int index) {
        return points[index].getZ() == null ? (T) DEFAULT_Z : points[index].getZ();
    }
}
