package geometric_primitives;

public class Face {
    private int[] vIdx;
    private int[] vtIdx;
    private int[] vnIdx;

    public Face(int[] vIdx) {
        this.vIdx = vIdx;
    }

    public Face(int[] vIdx, int[] vtIdx) {
        this.vIdx = vIdx;
        this.vtIdx = vtIdx;
    }

    public Face(int[] vIdx, int[] vtIdx, int[] vvIdx) {
        this.vIdx = vIdx;
        this.vtIdx = vtIdx;
        this.vnIdx = vvIdx;
    }

    public int getVIdx(int index) {
        return vIdx[index] - 1;
    }

    public int getVtIdx(int index) {
        return vtIdx[index] - 1;
    }
}
