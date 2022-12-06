import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;
    private int flag = 0;
    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */

        // COMPLETE HERE FOR PROBLEM 1
    public boolean findMazePath(int x, int y){
        if(x < 0 || y < 0 || x >= maze.getNRows() || y >= maze.getNCols()) return false;
        else if(maze.getColor(x,y) != NON_BACKGROUND) return false;
        else if (x == maze.getNRows() - 1 && y == maze.getNCols() - 1){
            maze.recolor(x,y,PATH);
            return true;
        }
        else{
            maze.recolor(x,y,TEMPORARY);
            if(findMazePath(x+1, y) || findMazePath(x-1, y) || findMazePath(x,y+1) || findMazePath(x,y-1)){
                maze.recolor(x,y,PATH);
                return true;
            }
        }
        return findMazePath(x+1, y) || findMazePath(x-1, y) || findMazePath(x,y+1) || findMazePath(x,y-1);
    }
     //ADD METHOD FOR PROBLEM 2 HERE
    public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace){
        if(x < 0 || y < 0 || x >= maze.getNRows() || y >= maze.getNCols()) return;
        else if(maze.getColor(x,y) != NON_BACKGROUND) return;
        else if (x == maze.getNRows() - 1 && y == maze.getNCols() - 1){
            trace.push(new PairInt(x,y));
            ArrayList<PairInt> list = new ArrayList<>(trace);
            result.add(list);
            trace.pop();
            maze.recolor(x,y,NON_BACKGROUND);
        }
        else{
            trace.push(new PairInt(x,y));
            maze.recolor(x,y,PATH);
            findMazePathStackBased(x+1,y,result,trace);
            findMazePathStackBased(x-1,y,result,trace);
            findMazePathStackBased(x,y+1,result,trace);
            findMazePathStackBased(x,y-1,result,trace);
            trace.pop();
            maze.recolor(x,y,NON_BACKGROUND);
            return;
        }
    }
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y){
        ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
        Stack<PairInt> trace = new Stack<>();
        findMazePathStackBased(x,y,result,trace);
        if(result.size() == 0){
            ArrayList<PairInt> temp = new ArrayList<>();
            result.add(temp);
        }
        return result;
    }
    // ADD METHOD FOR PROBLEM 3 HERE
    public ArrayList<PairInt> findMazePathMin(int x, int y){
        ArrayList<ArrayList<PairInt>> list =  findAllMazePaths(x,y);
        int min = list.get(0).size();
        ArrayList<PairInt> ans = list.get(0);
        for(int i = 1; i < list.size(); i++){
            if(list.get(i).size() < min){
                min = list.get(i).size();
                ans = list.get(i);
            }
        }
        return ans;
    }
    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
