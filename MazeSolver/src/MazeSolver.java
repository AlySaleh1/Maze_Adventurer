import javax.swing.event.CellEditorListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class MazeSolver {

    public static int[] getDimensions(String pathToInput) {
        File inputMap = new File(pathToInput);
        try{
            Scanner scanner = new Scanner(inputMap);

            int[] dimension = new int[2];           //dimension[0] = width, dimension[1] = height

            if (scanner.hasNextLine()) {             //if file contains atleaast one line
                String line = scanner.nextLine();

                dimension[1] = line.split(" ").length;      //this gets the width of the map

                //finding how many lines are there in the file (height)
                int lineCounter = 1;        //set to one because first line has already been read
                while (scanner.hasNextLine()) {
                    line = scanner.nextLine();
                    lineCounter++;
                }
                dimension[0] = lineCounter;

                return dimension;

            } else {
                //the input file is empty, 0,0 symbolizes and error state
                dimension[0] = 0;
                dimension[1] = 0;
                return dimension;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;        //code will not reach this part, added to suppress errors
    }

    public static Cell[][] mapReader(String pathToInput) {
        File inputMap = new File(pathToInput);
        int[] dimension = getDimensions(pathToInput);

        if (dimension[0] == 0 && dimension[1] == 0) {
            System.out.println("input file is empty!");
            return null;
        } else {
            try {

                Cell[][] mazeMap = new Cell[dimension[0]][dimension[1]];

                Scanner scanner = new Scanner(inputMap);
                int yCord = 0;                              //the y coordinate


                while (scanner.hasNextLine()) {             //this part loads the content of the file into mazeMap from the top left to bottom right
                    String line = scanner.nextLine();
                    String[] lineCells = line.split(" ");

                    int xCord = 0;                          //the x coordinate
                    for (String cellContent: lineCells) {

                        Cell inputCell = new Cell(xCord,yCord, false, cellContent);
                        mazeMap[yCord][xCord] = inputCell;

                        xCord++;
                    }
                    yCord++;                                //go to the next "line" in mazeMap
                }

                return mazeMap;                             //map has been stored in the 2-d array

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        //remove
        Cell[][] map = new Cell[1][1];
        return map;
    }

    public static boolean isAtFinalLocation(String[][] map, int x, int y) {
        if (map.length == x && map[0].length == y) {
            return true;
        } else {
            return false;
        }
    }


    public static Adventurer BFS(Maze maze) {

        Queue<Cell> cells = new LinkedList<>();

        maze.getMap()[maze.getHeight() - 1][0].setExplored(true);
        cells.add(maze.getMap()[maze.getHeight() -  1][0]);      //add bottom left cell to queue


        Adventurer hero = new Adventurer(0,"1");

        int cellCounter = 0;            //this keeps track of how many cells we visited
        while(!cells.isEmpty()) {
            Cell currentCell = cells.remove();
            cellCounter++;

            if (maze.isDestination(currentCell)) {
                hero.setSecretKey(currentCell.getContent());
                hero.setStepsTaken(cellCounter);
                break;
            }

            int x = currentCell.getxCord();
            int y = currentCell.getyCord();

            if (maze.shouldGoUp(currentCell)) {
                maze.getMap()[y + 1][x].setExplored(true);
                cells.add(maze.getMap()[y + 1][x]);
            }

            if (maze.shouldGoRight(currentCell)) {
                maze.getMap()[y][x+1].setExplored(true);
                cells.add(maze.getMap()[y][x + 1]);
            }

            if (maze.shouldGoDown(currentCell)) {
                maze.getMap()[y-1][x].setExplored(true);
                cells.add(maze.getMap()[y - 1][x]);
            }

            if (maze.shouldGoLeft(currentCell)) {
                maze.getMap()[y][x-1].setExplored(true);
                cells.add(maze.getMap()[y][x - 1]);
            }

        }
        return hero;
    }

    public static Adventurer DFS(Maze maze) {

        Stack<Cell> cells = new Stack<>();

        maze.getMap()[maze.getHeight() - 1][0].setExplored(true);
        cells.add(maze.getMap()[maze.getHeight() -  1][0]);      //add bottom left cell to queue


        Adventurer hero = new Adventurer(0,"1");

        int cellCounter = 0;            //this keeps track of how many cells we visited
        while(!cells.isEmpty()) {
            Cell currentCell = cells.pop();
            cellCounter++;

            if (maze.isDestination(currentCell)) {
                hero.setSecretKey(currentCell.getContent());
                hero.setStepsTaken(cellCounter);
                break;
            }

            int x = currentCell.getxCord();
            int y = currentCell.getyCord();

            if (maze.shouldGoUp(currentCell)) {
                maze.getMap()[y + 1][x].setExplored(true);
                cells.add(maze.getMap()[y + 1][x]);
            }

            if (maze.shouldGoRight(currentCell)) {
                maze.getMap()[y][x+1].setExplored(true);
                cells.add(maze.getMap()[y][x + 1]);
            }

            if (maze.shouldGoDown(currentCell)) {
                maze.getMap()[y-1][x].setExplored(true);
                cells.add(maze.getMap()[y - 1][x]);
            }

            if (maze.shouldGoLeft(currentCell)) {
                maze.getMap()[y][x-1].setExplored(true);
                cells.add(maze.getMap()[y][x - 1]);
            }

        }
        return hero;
    }

    public static void main(String[] args){
        System.out.println();

        Cell[][] newMap = mapReader(*); //<= TODO: replace * with the absolute path of mapInput on your local Machine!
        Maze maze = new Maze(newMap);

        System.out.println("Solving the Maze using Breadth First Search:");

        Adventurer hero = BFS(maze);

        if (hero.getSecretKey().equals("1")) {
            System.out.println("There are no solutions to this maze!");
        } else {
            System.out.println("The secret value is "+ hero.getSecretKey());
            System.out.println("the number of cells checked is " + hero.getStepsTaken() + "\n");
        }

        System.out.println("Solving the Maze using Depth First Search:");

        newMap = mapReader(*); //<= TODO: replace * with the absolute path of mapInput on your local Machine!
        maze = new Maze(newMap);

        hero = DFS(maze);
        if (hero.getSecretKey().equals("1")) {
            System.out.println("There are no solutions to this maze!");
        } else {
            System.out.println("The secret value is "+ hero.getSecretKey());
            System.out.println("the number of cells checked is " + hero.getStepsTaken() + "\n");
        }

    }
}
