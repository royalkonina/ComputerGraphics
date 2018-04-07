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

    public Face[] getFaces() {
        return faces;
    }

    public PointDouble getV(int vIdx) {
        return v[vIdx];
    }

    public PointDouble getVt(int vtIdx) {
        return new PointDouble(vt[vtIdx].x * 2 - 1,
                vt[vtIdx].y * 2 - 1,
                vt[vtIdx].z * 2 - 1);
    }
}
