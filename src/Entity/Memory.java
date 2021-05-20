package Entity;

/**
 *
 * @author NamNH
 */
public class Memory {
    private int point;
    private int xFrog;
    private int xPipeI;
    private int heightPipeI;
    private int xPipeII;
    private int heightPipeII;

    public Memory() {
    }

    public Memory(int point, int xFrog, int xPipeI, int heightPipeI, int xPipeII, int heightPipeII) {
        this.point = point;
        this.xFrog = xFrog;
        this.xPipeI = xPipeI;
        this.heightPipeI = heightPipeI;
        this.xPipeII = xPipeII;
        this.heightPipeII = heightPipeII;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getxFrog() {
        return xFrog;
    }

    public void setxFrog(int xFrog) {
        this.xFrog = xFrog;
    }

    public int getxPipeI() {
        return xPipeI;
    }

    public void setxPipeI(int xPipeI) {
        this.xPipeI = xPipeI;
    }

    public int getHeightPipeI() {
        return heightPipeI;
    }

    public void setHeightPipeI(int heightPipeI) {
        this.heightPipeI = heightPipeI;
    }

    public int getxPipeII() {
        return xPipeII;
    }

    public void setxPipeII(int xPipeII) {
        this.xPipeII = xPipeII;
    }

    public int getHeightPipeII() {
        return heightPipeII;
    }

    public void setHeightPipeII(int heightPipeII) {
        this.heightPipeII = heightPipeII;
    }
    
}
