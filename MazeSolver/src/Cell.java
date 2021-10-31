public class Cell {
    private int xCord;
    private int yCord;
    private boolean isExplored;
    private String content;

    public Cell(int xCord, int yCord, boolean isExplored, String content) {
        this.xCord = xCord;
        this.yCord = yCord;
        this.isExplored = isExplored;
        this.content = content;
    }

    public int getxCord() {
        return xCord;
    }

    public void setxCord(int xCord) {
        this.xCord = xCord;
    }

    public int getyCord() {
        return yCord;
    }

    public void setyCord(int yCord) {
        this.yCord = yCord;
    }

    public boolean isExplored() {
        return isExplored;
    }

    public void setExplored(boolean explored) {
        isExplored = explored;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
