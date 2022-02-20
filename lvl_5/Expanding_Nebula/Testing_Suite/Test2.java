package Testing_Suite;

import java.util.ArrayList;
import java.util.Arrays;

public class Test2 {
    static ArrayList<ArrayList<int[]>> preImageList = new ArrayList<ArrayList<int[]>>();
    static ArrayList<ArrayList<int[]>> mergedRows= new ArrayList<ArrayList<int[]>>();

    static int[] gridCol;
    static int[][] grid;

    public static void test(boolean[][] boolGrid){
        init(boolGrid);

        for (int k = 0; k < grid[0].length; k++) {
            // preimages of current row
            gridCol = getGridCol(k);
            // get col of preimages
            for (int i = 0; i < gridCol.length; i++) {
                getColPre(gridCol[i]);
            }
            mergeRows();
            // printList(preImageList);
            System.out.println("mergedRows");
            printList(mergedRows); 
            System.out.println("=======================");
            preImageList.clear();
        }
            // vert for 2x2
            
    }
    public static void deepCopy(ArrayList<ArrayList<int[]>> merger, ArrayList<ArrayList<int[]>> mergee){
        // deep copy colo pre images to merged Rows   
        // index for each 2x2 
        for (int k = 0; k < zero.length; k++) {
            // vert for 2x2
            for (int i = 0; i < zero[0].length; i++) {
                // horisontal for 2x2
                for (int j = 0; j < zero[0][0].length; j++) {
                        merger.get(k).get(i)[j] = mergee.get(k).get(i)[j];
                } 
            }
        }
        
    }
    public static void mergeRows(){
        if(mergedRows.size() == 0){
            mergedRows.addAll(preImageList);
        }
        else{
            ArrayList<ArrayList<int[]>> tempList = new ArrayList<ArrayList<int[]>>();
            for (int k = 0; k < mergedRows.size(); k++) {
                int[] rightCol = getRight(mergedRows.get(k)); 
                for (int i = 0; i < preImageList.size(); i++) {
                    // get left col of list
                    int[] leftCol = getLeft(preImageList.get(i));
                    boolean isEqual = compareTopToBot(leftCol, rightCol);
                    if(isEqual){
                            ArrayList<int[]> temp = new ArrayList<int[]>();
                            // rxc grid
                            temp = mergeLR(k,i, getRight(preImageList.get(i)));
                            tempList.add(temp);
                        } 
                } 
            }
            mergedRows.clear();
            mergedRows.addAll(tempList);
            
            // printList(preImageList);
            // printList(mergedRows);
        }
    } 
    public static void printList(ArrayList<ArrayList<int[]>> thisList){
        System.out.println();
        // index for each 2x2 
        for (int k = 0; k < thisList.size(); k++) {
            // vert for 2x2
            for (int i = 0; i < thisList.get(0).size(); i++) {
                // horisontal for 2x2
                for (int j = 0; j < thisList.get(0).get(0).length; j++) {
                      System.out.print(thisList.get(k).get(i)[j]);  
                } 
                System.out.println();
            }
            System.out.println();
            System.out.println(" ========" + k + "======= ");
            System.out.println();
        }
    }
    public static ArrayList<int[]> mergeLR(int k, int i, int j[]){
        ArrayList<int[]> temp = new ArrayList<int[]>();
        
        // merge right col of master with left col of curr left
        for (int l = 0; l < mergedRows.get(k).size(); l++) {
            int[] newRow = new int[mergedRows.get(k).get(0).length + 1];
            for (int v = 0; v < mergedRows.get(k).get(0).length; v++) {
                newRow[v] = mergedRows.get(k).get(l)[v];
            }
            newRow[mergedRows.get(k).get(0).length] = j[l]; 
            temp.add(newRow);
        }
        return temp;
    }
    public static int[] getRight(ArrayList<int[]> current){
        int[] temp = new int[current.size()];
        for (int i = 0; i < current.size(); i++) {
            temp[i] = current.get(i)[current.get(0).length - 1];
        }
        return temp;
    }
    public static int[] getLeft(ArrayList<int[]> current){
        int[] temp = new int[current.size()];
        for (int i = 0; i < current.size(); i++) {
            temp[i] = current.get(i)[0];
        }
        return temp;
    }
    public static void getColPre(int curr){
        // adds preimages of first in Col to preimagelist
        if(preImageList.size() == 0){
            if(curr == 0){
                // index for each 2x2 
                for (int k = 0; k < zero.length; k++) {
                    ArrayList<int[]> temp = new ArrayList<int[]>();
                    // vert for 2x2
                    for (int i = 0; i < zero[0].length; i++) {
                        int[] curRow = new int[zero[0].length];
                        // horisontal for 2x2
                        for (int j = 0; j < zero[0][0].length; j++) {
                            curRow[j] = zero[k][i][j];
                        } 
                        temp.add(curRow);
                    }
                    preImageList.add(temp);
                }
            }
            else if(curr == 1){
                // index for each 2x2 
                for (int k = 0; k < one.length; k++) {
                    ArrayList<int[]> temp = new ArrayList<int[]>();
                    // vert for 2x2
                    for (int i = 0; i < one[0].length; i++) {
                        int[] curRow = new int[one[0].length];
                        // horisontal for 2x2
                        for (int j = 0; j < one[0][0].length; j++) {
                            curRow[j] = one[k][i][j];
                        } 
                        temp.add(curRow);
                    }
                    preImageList.add(temp);
                }
            }
        }
        // if preimagelist not empty 
        // for each preimage in preimagelist
        // compare bot of preimage to top of each 2x2 grid in corresponding possible preimages
        // this will give the valid preimages for row each col saved to 
        else{
            // compare to current list 
            // list contains current possible preimages for current col

            ArrayList<ArrayList<int[]>> tempList = new ArrayList<ArrayList<int[]>>();
            // get each possible preimage from list
            for (int k = 0; k < preImageList.size(); k++) {
                int[] currBotRow = getBot(preImageList.get(k));
                // vert for 2x2
                if(curr == 0){
                    for (int i = 0; i < zero.length; i++) {
                        int[] currTopRow = getTop(zero[i]);
                        boolean isEqual = compareTopToBot(currBotRow, currTopRow);
                        if(isEqual){
                            ArrayList<int[]> temp = new ArrayList<int[]>();
                            temp.addAll(preImageList.get(k));
                            temp.add(getBot(zero[i]));
                            tempList.add(temp);
                        } 
                    }
                }
                if(curr == 1){
                    for (int i = 0; i < one.length; i++) {
                        int[] currTopRow = getTop(one[i]);
                        boolean isEqual = compareTopToBot(currBotRow, currTopRow);
                        if(isEqual){
                            // filter out all preimages that are not possible 
                            ArrayList<int[]> temp = new ArrayList<int[]>();
                            temp.addAll(preImageList.get(k));
                            temp.add(getBot(one[i]));
                            tempList.add(temp);
                        } 
                    }
                }
            }
            preImageList.clear();
            preImageList = tempList;
        }
    }
    public static boolean compareTopToBot(int[] bot, int[] top){
        if(Arrays.equals(bot,top)){
            return true;
        }
        return false;
    }
    public static int[] getBot(ArrayList<int[]> current){
        int[] temp = new int[current.get(0).length];
        for (int i = 0; i < current.get(0).length; i++) {
            temp[i] = current.get(current.size() - 1)[i];
        }
        return temp; 
    }
    public static int[] getBot(int[][] current){
        int[] temp = new int[current[0].length];
        for (int i = 0; i < current[0].length; i++) {
            temp[i] = current[current.length - 1][i];
        }
        return temp;
    }
    public static int[] getTop(int[][] current){
        int[] temp = new int[current[0].length];
        for (int i = 0; i < current[0].length; i++) {
            temp[i] = current[0][i];
        }
        return temp;
    }
    public static void init(boolean[][] boolGrid){
        grid = new int[boolGrid.length][boolGrid[0].length];
        transformBolToInt(boolGrid);
    }
    public static int[] getGridCol(int curr){
        int[] getCurCol = new int[grid.length];
        for (int i = 0; i < grid.length; i++) {
            getCurCol[i] = grid[i][curr];
        }
        return getCurCol; 
    }
    void forLoop(){
                // index for each 2x2 
                for (int k = 0; k < zero.length; k++) {
                    // vert for 2x2
                    for (int i = 0; i < zero[0].length; i++) {
                        // horisontal for 2x2
                        for (int j = 0; j < zero[0][0].length; j++) {
                                
                        } 
                    }
                }
    } 
    public static void print2x2(int[][] preList, int x){
       for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(zero[x][i][j]);
            }   
            System.out.println();
       } 
    }
    public static void transformBolToInt(boolean[][] boolGrid){
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(boolGrid[i][j] == true){
                    grid[i][j] = 1;
                }
                else{
                    grid[i][j] = 0;
                }
            }          
        }
    }
    // =========================================== //
    static int[][][] one = {
                  {{1,0},
                   {0,0}},

                  {{0,1},
                   {0,0}},

                  {{0,0},
                   {1,0}},

                  {{0,0},
                   {0,1}}
    };

    static int[][][] zero = {
                
                  {{1,0},
                   {1,0}},

                  {{0,1},
                   {0,1}},

                  {{1,1},
                   {0,0}},

                  {{0,0},
                   {1,1}},

                  {{0,1},     
                   {1,1}},

                  {{1,0},
                   {1,1}},

                  {{1,1},
                   {0,1}},

                  {{1,1},
                   {1,0}},

                  {{1,1},
                   {1,1}},

                  {{0,0},
                   {0,0}},

                  {{1,0},
                   {0,1}},

                  {{0,1},
                   {1,0}}
    };
    public static void main(String[] args) {
        boolean[][] grid = {
            {true, false, true}, 
            {false, true, false}, 
            {true, false, true}
    };
        test(grid);
    }
}
