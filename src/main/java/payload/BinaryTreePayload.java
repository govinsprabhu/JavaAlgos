package payload;

/**
 * Created by govindp on 9/22/2015.
 */
public class BinaryTreePayload {
    int maxLevel;

    public BinaryTreePayload(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }
}
