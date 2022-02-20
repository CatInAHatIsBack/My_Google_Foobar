package Testing_Suite;

import java.util.ArrayList;
import java.util.Arrays;

class Test1{
    // true is 1 false is 0
    static ArrayList<ArrayList<int[]>> list = new ArrayList<ArrayList<int[]>>();
    static ArrayList<ArrayList<int[]>> masterList = new ArrayList<ArrayList<int[]>>();
    static int[][] grid;
    static int[] row;

    static void testing(boolean[][] boolGrid){

        grid = new int[boolGrid.length][boolGrid[0].length];
        
        transformBolToInt(boolGrid);
        /**
            check each row
            list (get list of current row) 
            master list to keep track to compare to after gotten all valid preimages for a row         
        */

        // index for each 2x2 
        for (int k = 0; k < grid.length; k++) {
            int[] currRow = getRow(k);
            // vert for 2x2
            for (int i = 0; i < currRow.length; i++) {
                // preimages of current 0 / 1
                
                getPre(currRow[i]);
                printList(list);
                // horisontal for 2x2
            }
            mergeList();
            System.out.println(" pr");
            printList2(masterList);
            list.clear();
        }
    }
    public static void mergeList(){
        if(masterList.size() == 0){
            masterList.addAll(list);
        }
        else{
            ArrayList<ArrayList<int[]>> tempList = new ArrayList<ArrayList<int[]>>();
            for (int k = 0; k < masterList.size(); k++) {
                int[] rightCol = getRight(masterList.get(k)); 
                for (int i = 0; i < list.size(); i++) {
                    // get left col of list
                    int[] leftCol = getLeft(list.get(i));
                    boolean isEqual = compareTopToBot(leftCol, rightCol);
                    if(isEqual){
                            ArrayList<int[]> temp = new ArrayList<int[]>();
                            temp = mergeLR(k,i, leftCol);
                            tempList.add(temp);
                        } 
                } 
            }
        
            masterList.clear();
            masterList.addAll(tempList);
            // printList(list);
            // printList2(masterList);
        }
    } 
    public static ArrayList<int[]> mergeLR(int k, int i, int j[]){
        ArrayList<int[]> temp = new ArrayList<int[]>();
        int[] newRow = new int[masterList.get(k).get(0).length + 1];
        // merge right col of master with left col of curr left
        for (int l = 0; l < masterList.get(k).size(); l++) {
            for (int v = 0; v < masterList.get(k).get(0).length; v++) {
                newRow[v] = masterList.get(k).get(l)[v];
            }
            newRow[masterList.get(k).get(0).length - 1] = j[l]; 
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
    public static void printList2(ArrayList<ArrayList<int[]>> thisList){
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
            System.out.println(" ML ");
            System.out.println(" ========" + k + "======= ");
            System.out.println();
        }
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
    public static void getPre(int curr){
        // adds all to list
        if(list.size() == 0){
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
                    list.add(temp);
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
                    list.add(temp);
                }
            }
        }
        // itterate each in list and compare bottom of image to top of this
        // get bot of current in list and compare to top of all possible preimages for zero||one
        else{
            // compare to current list 
            // list contains current possible preimages for current number
            ArrayList<ArrayList<int[]>> tempList = new ArrayList<ArrayList<int[]>>();
            // get each possible preimage from list
            for (int k = 0; k < list.size(); k++) {
                int[] currBotRow = getBot(list.get(k));
                // vert for 2x2
                if(curr == 0){
                    for (int i = 0; i < zero.length; i++) {
                        int[] currTopRow = getTop(zero[i]);
                        boolean isEqual = compareTopToBot(currBotRow, currTopRow);
                        if(isEqual){
                            ArrayList<int[]> temp = new ArrayList<int[]>();
                            temp.addAll(list.get(k));
                            temp.add(currTopRow);
                            tempList.add(temp);
                        } 
                    }
                }
                if(curr == 1){
                    for (int i = 0; i < one.length; i++) {
                        int[] currTopRow = getTop(one[i]);
                        boolean isEqual = compareTopToBot(currBotRow, currTopRow);
                        if(isEqual){
                            ArrayList<int[]> temp = new ArrayList<int[]>();
                            temp.addAll(list.get(k));
                            temp.add(currTopRow);
                            tempList.add(temp);
                        } 
                    }
                }
            }
            list.clear();
            list = tempList;
        }
    }
    public static boolean compareTopToBot(int[] bot, int[] top){
        if(Arrays.equals(bot,top)){
            return true;
        }
        return false;
    }
    public static int[] getTop(ArrayList<int[]> current){
        int[] temp = new int[2];
        for (int i = 0; i < current.get(0).length; i++) {
            temp[i] = current.get(0)[i];
        }
        return temp;
    }
    public static int[] getBot(ArrayList<int[]> current){
        int[] temp = new int[2];
        for (int i = 0; i < current.get(0).length; i++) {
            temp[i] = current.get(current.size() - 1)[i];
        }
        return temp; 
    }
    public static int[] getTop(int[][] current){
        int[] temp = new int[2];
        for (int i = 0; i < current[0].length; i++) {
            temp[i] = current[current.length - 1][i];
        }
        return temp;
    }
    public static int[] getRow(int curr){
        int[] getCurRow = new int[grid.length];
        for (int i = 0; i < grid.length; i++) {
            getCurRow[i] = grid[i][curr];
        }
        return getCurRow; 
    }
    public static void print2x2( int x){
       for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(zero[x][i][j]);
            }   
            System.out.println();
       } 
    }
    public static void print2x2Array(int x){
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
    //=============================================//
    //=============================================//
    //=============================================//
    //=============================================//
    //=============================================//
    //=============================================//
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
        testing(grid);
    }
}