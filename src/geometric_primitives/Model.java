package geometric_primitives;

public class Model {
    private PointDouble[] v;
    private PointDouble[] vt;
    private PointDouble[] vn;
    private Face[] faces;

    public Model(PointDouble[] v, PointDouble[] vt, PointDouble[] vn, Face[] faces) {
        this.v = v;
        this.vt = vt;
        this.vn = vn;
        this.faces = faces;
    }
}
