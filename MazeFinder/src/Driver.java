import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Driver {

    public static void depthFirst(MazeCell[][] cells, MazeCell start, MazeCell end) {
        MyStack<MazeCell> stack = new MyStack<MazeCell>();
        int i = start.getRow();
        int j = start.getCol();
        stack.push(cells[i][j]);
        while (cells[i][j] != cells[end.getRow()][end.getCol()]) {
                if(cells[i][j].getRow() < 11 && cells[i + 1][j].unVisited() && cells[i + 1][j].getRow() != -1 && cells[i + 1][j].getCol() != -1) {
                    stack.push(cells[i][j]);
                    cells[i][j].visit();
                    i++;
                    System.out.println(stack.top());
                }
                else if(cells[i][j].getCol() < 11 && cells[i][j + 1].unVisited() && cells[i][j + 1].getRow() != -1 && cells[i][j + 1].getCol() != -1) {
                    stack.push(cells[i][j]);
                    cells[i][j].visit();
                    j++;
                    System.out.println(stack.top());
                }
                else if(cells[i][j].getCol() >= 0 && cells[i][j - 1].unVisited() && cells[i][j - 1].getRow() != -1 && cells[i][j - 1].getCol() != -1) {
                    stack.push(cells[i][j]);
                    cells[i][j].visit();
                    j--;
                    System.out.println(stack.top());
                }
                else if(cells[i][j].getRow() < 11 && cells[i - 1][j].unVisited() && cells[i - 1][j].getRow() != -1 && cells[i - 1][j].getCol() != -1) {
                    stack.push(cells[i][j]);
                    cells[i][j].visit();
                    i--;
                    System.out.println(stack.top());
                    } else {
                    cells[i][j].visit();
                    i = stack.top().getRow();
                    j = stack.top().getCol();
                    stack.pop();
                }
                if(cells[i][j] == cells[end.getRow()][end.getCol()]) {
                    System.out.println("END");
                }
        }
    }

    public static void print(int grid[][], int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            System.out.println();
            for (int j = 0; j < cols; j++) {
                System.out.print(grid[i][j] + " ");
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {


        Scanner fin = new Scanner(new File("Maze.in"));
        int rows = fin.nextInt();
        int cols = fin.nextInt();

        int [][] grid = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = fin.nextInt();
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 3)
                    System.out.print("S ");
                else if (grid[i][j] == 4)
                    System.out.print("E ");
                else
                    System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

        MazeCell[][] cells = new MazeCell[rows][cols];


        MazeCell start = new MazeCell(), end = new MazeCell();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j] = new MazeCell();
                if (grid[i][j] != 0) {
                    cells[i][j].setCoordinates(i, j);
                    if (grid[i][j] == 3)
                        start = cells[i][j];
                    if (grid[i][j] == 4)
                        end = cells[i][j];

                }

            }
        }

        System.out.println("start:"+start+" end:"+end);
        depthFirst(cells ,start, end);

    }
}

class MazeCell {
    private int row, col;
    private int direction;
    private boolean visited;

    public MazeCell(int r, int c) {
        row = r;
        col = c;
        direction = 0;
        visited = false;
    }

    public MazeCell() {
        row = col = -1;
        direction = 0;
        visited = false;
    }

    MazeCell(MazeCell rhs) {
        this.row = rhs.row;
        this.col = rhs.col;
        this.direction = rhs.direction;
        this.visited = rhs.visited;
    }


    public int getDirection() {
        return direction;
    }

    public void advanceDirection() {
        direction++;
        if (direction == 4)
            visited = true;
    }

    public void setCoordinates(int r, int c) {
        row = r;
        col = c;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MazeCell other = (MazeCell) obj;
        if (col != other.col)
            return false;
        if (row != other.row)
            return false;
        return true;
    }

    public void visit() {
        visited = true;
    }

    public void reset() {
        visited = false;
    }

    public boolean unVisited() {
        return !visited;
    }

    public String toString() {
        return "(" + row + "," + col + ")";
    }
}
