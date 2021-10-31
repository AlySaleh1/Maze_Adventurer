public class Maze {

    private Cell[][] map;
    boolean solved;

    public Maze(Cell[][] map) {
        this.map = map;
        this.solved=false;
    }


    public Cell[][] getMap() {
        return map;
    }

    public void setMap(Cell[][] map) {
        this.map = map;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public int getHeight() {
        return this.map.length;
    }

    public int getWidth() {
        return this.map[0].length;
    }

    public boolean hasArrived(int x,int y) {
        if (x == this.map.length && y == this.map[0].length) {  //if you are at the upper right corner
            return true;
        } else {
            return false;
        }
    }

    public boolean isPath(int x, int y) {       //checks if cell at [y][x] is a wall

        String cellContent = this.map[y][x].getContent();
        if (!cellContent.equals("1")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean withinMap(int x, int y){     //checks if inputs within map, to avoid index outof bound errors
        if (y >= 0 && y < map.length && x >=0 && x < map[0].length){
            return true;
        } else {
            return false;
        }
    }


    //checks if you can go to the cell above
    public boolean shouldGoUp(Cell cell){
        //check if y+1 is inbound
        int x = cell.getxCord();
        int y = cell.getyCord();

        if (this.withinMap(x,y + 1)) {                      //not using && here to avoid index out of bound errors
            if (!this.getMap()[y + 1][x].isExplored() && this.isPath(x,y + 1)) {    //if the current cell is not explored
                return true;
            }
            return false;
        }
        return false;
    }

    //checks to see if you can go right
    public boolean shouldGoRight(Cell cell){
        //check if y+1 is inbound
        int x = cell.getxCord();
        int y = cell.getyCord();

        if (this.withinMap(x + 1,y)) {                      //not using && here to avoid index out of bound errors
            if (!this.getMap()[y][x + 1].isExplored() && this.isPath(x + 1,y)) {    //if the current cell is not explored
                return true;
            }
            return false;
        }
        return false;
    }

    //checks to see if you should go down
    public boolean shouldGoDown(Cell cell){
        //check if y+1 is inbound
        int x = cell.getxCord();
        int y = cell.getyCord();

        if (this.withinMap(x,y - 1)) {                      //not using && here to avoid index out of bound errors
            if (!this.getMap()[y - 1][x].isExplored() && this.isPath(x,y - 1)) {       //if the current cell is not explored
                return true;
            }
            return false;
        }
        return false;
    }

    //checks to see if you should go left
    public boolean shouldGoLeft(Cell cell){
        //check if y+1 is inbound
        int x = cell.getxCord();
        int y = cell.getyCord();

        if (this.withinMap(x - 1,y)) {                      //not using && here to avoid index out of bound errors
            if (!this.getMap()[y][x - 1].isExplored() && this.isPath(x - 1,y)) {        //if the current cell is not explored
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean isDestination(Cell cell) {
        if (cell.getxCord() == this.getWidth() - 1  && cell.getyCord() == 0) {      //top right corner
            return true;
        } else {
            return false;
        }

    }
}
