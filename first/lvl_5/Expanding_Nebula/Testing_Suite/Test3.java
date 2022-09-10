package Testing_Suite;

import java.util.ArrayList;

public class Test3 {
    BasePreImage[] preImageOne;
    BasePreImage[] preImageZero;
    int[][] grid;
    // pre images for curr col
    ArrayList<PreImageCol> colPreImages = new ArrayList<PreImageCol>();

    Test3(boolean[][] boolGrid){
        grid = new int[boolGrid.length][boolGrid.length];
        transformBoolToInt(boolGrid);
        init(boolGrid);
        getAllPreImages(); 
    }
    /**
        get possible preimages of base      | done
        get get column                      | done

     */
    /**
        top row : 2
        bot row : 1

        need to check right col of preimages in current list with left of x 
        if the == then add right of x to answer
     */
    public void getAllPreImages(){
        ArrayList<PreImageCol> currentColPossible = new ArrayList<PreImageCol>();
        MergedCols mergedCols = new MergedCols(); 
        for (int i = 0; i < grid[0].length; i++) {
            currentColPossible = getColPreImage(i); 
            mergedCols.merge(currentColPossible);
            currentColPossible.clear();
        }
    }
    public ArrayList<PreImageCol> getColPreImage(int currentCol){
        int[] col = getCurrentCol(currentCol);
        printCol(col, currentCol);
        // ArrayList<Integer> 
        
        ArrayList<PreImageCol> currentPossible = new ArrayList<PreImageCol>();
        for (int i = 0; i < col.length; i++) {
            ArrayList<PreImageCol> preimages = new ArrayList<PreImageCol>();
            if(i == 0){
                if(col[i] == 0){
                    for (int j = 0; j < preImageZero.length; j++) {
                        preimages.add(new PreImageCol(preImageZero[j]));
                    } 
                }
                if(col[i] == 1){
                    for (int j = 0; j < preImageOne.length; j++) {
                        preimages.add(new PreImageCol(preImageOne[j]));
                    } 
                }
            }
            else{
                if(col[i] == 0){
                    for (int k = 0; k < currentPossible.size(); k++) {
                        for (int j = 0; j < preImageZero.length; j++) {
                            if(currentPossible.get(k).lastRowBinRep == preImageZero[j].topBinaryRepresentation)
                            preimages.add(new PreImageCol(preImageZero[j], currentPossible.get(k)));
                        }
                    }
                }
            
                if(col[i] == 1){
                    for (int k = 0; k < currentPossible.size(); k++) {
                        for (int j = 0; j < preImageOne.length; j++) {
                            if(currentPossible.get(k).lastRowBinRep == preImageOne[j].topBinaryRepresentation)
                            preimages.add(new PreImageCol(preImageOne[j], currentPossible.get(k)));
                        }
                    }
                }
            }
            for (int j = 0; j < preimages.size(); j++) {
                preimages.get(j).printColPre();  
            } 
            currentPossible.clear();
            currentPossible = preimages;
        }
        return currentPossible;
        

    }
    public void transformBoolToInt(boolean[][] boolGrid){
        for (int i = 0; i < boolGrid.length; i++) {
            for (int j = 0; j < boolGrid[0].length; j++) {
                if(boolGrid[i][j] == true){
                    grid[i][j] = 1;
                }
                else{
                    grid[i][j] = 0;
                }
            }          
        }
    }
    public void printGrid(int[][] grid){
        System.out.println(" ==== Input Grid ====== ");
        for (int i = 0; i < grid.length; i++) {
            // horisontal for 2x2
            for (int j = 0; j < grid[0].length; j++) {
                  System.out.print(grid[i][j]);  
            } 
            System.out.println();
        }
    }
    public void printCol(int[] col, int colNum){
    
        System.out.println();
        System.out.println(" ==== Column : " + colNum + "  ====== ");
        for (int i = 0; i < col.length; i++) {
            System.out.println(col[i]); 
        }

    }
    public int[] getCurrentCol(int currentCol){
        int[] currCol = new int[grid.length];
        for (int i = 0; i < grid.length; i++) {
            currCol[i] = grid[i][currentCol]; 
        }
        return currCol;
    }
    public void init(boolean[][] boolGrid){
        System.out.println();
        preImageOne = new BasePreImage[one.length];     
        preImageZero = new BasePreImage[zero.length];     
        for (int i = 0; i < one.length; i++) {
            System.out.println();
            System.out.println(" preimage from one : " + i);
            System.out.println();
            preImageOne[i] = new BasePreImage(one[i]);
        }         
        for (int i = 0; i < zero.length; i++) {
            System.out.println();
            System.out.println(" preimage from zero : " + i);
            System.out.println();
            preImageZero[i] = new BasePreImage(zero[i]);
        }
    }

    class MergedCols{
        ArrayList<ArrayList<Integer>> mergedCols = new ArrayList<ArrayList<Integer>>();

        /**
            each col preimage has left and right col value
            
            if list is empty add both for all 
            else 
                for all in list check current right col value with all
                left col values preCol 
                    if they match add right value to list
            // this will give an arrlist of arrlist<int> that stores 
            // possible preimages cols binary representations
            // for all cols merged 


         */

        // ArrayList<Integer>
        
        MergedCols(){

        }
        public void merge(ArrayList<PreImageCol> preCol){
            // mergedCols.add(preCol);
            ArrayList<ArrayList<Integer>> mergedList = new ArrayList<ArrayList<Integer>>();
            if(mergedCols.size() == 0){
                for (int i = 0; i < preCol.size(); i++) {
                    ArrayList<Integer> temp = new ArrayList<Integer>();
                    temp.add(preCol.get(i).columnLeftBinRep);
                    temp.add(preCol.get(i).columnRightBinRep);
                    mergedList.add(temp);
                }
                mergedCols.addAll(mergedList);
                printGrid();
            }
            else{
                ArrayList<ArrayList<Integer>> tempMergedList = new ArrayList<ArrayList<Integer>>();
                for (int i = 0; i < mergedCols.size(); i++) {
                    ArrayList<Integer> currentPossible = mergedCols.get(i);
                    for (int j = 0; j < preCol.size(); j++) {
                        ArrayList<Integer> temp = new ArrayList<Integer>();
                        int len = currentPossible.size();
                        if(currentPossible.get(len-1) == preCol.get(j).columnLeftBinRep){
                            for (int k = 0; k < len; k++) {
                                temp.add(currentPossible.get(k));
                            }
                            temp.add(preCol.get(j).columnRightBinRep);
                            tempMergedList.add(temp);
                        } 

                    } 
                }   
                mergedCols.clear();
                mergedCols.addAll(tempMergedList);
                
                printGrid();
            }    
        }
        void printGrid(){
            for (int i = 0; i < mergedCols.size(); i++) {
                System.out.println();
                System.out.println("Array :" + i);
                for (int j = 0; j < mergedCols.get(i).size(); j++) {
                    System.out.print(mergedCols.get(i).get(j));  
                } 
            }
        }
        
    }
    class PreImageMerged{
        
        PreImageMerged(){

        }
    }
    class PreImageCol{
        // pre image for each column
        ArrayList<Integer> rowBinRep = new ArrayList<Integer>();
        int columnLeftBinRep;
        int columnRightBinRep;
        int lastRowBinRep;

        PreImageCol(BasePreImage current){
            rowBinRep.add(current.topBinaryRepresentation);
            rowBinRep.add(current.botBinaryRepresentation);
            columnLeftBinRep = current.leftBinaryRepresentation; 
            columnRightBinRep = current.rightBinaryRepresentation;
            lastRowBinRep = current.botBinaryRepresentation;
        }
        PreImageCol(BasePreImage current, PreImageCol preImage){

            makeCopy(preImage);
            rowBinRep.add(current.botBinaryRepresentation);
            updateCol(preImage.columnLeftBinRep, preImage.columnRightBinRep, current.botBinaryRepresentation); 
            lastRowBinRep = current.botBinaryRepresentation;
        }
        public void makeCopy(PreImageCol preImage){
            int len = preImage.rowBinRep.size();
            for (int i = 0; i < len; i++) {
                rowBinRep.add(preImage.rowBinRep.get(i));
            }
        }
        public void updateCol(int left, int right, int rowAdded){
            if(rowAdded == 0){
                columnLeftBinRep = left * 2;
                columnRightBinRep = right * 2;
            }
            if(rowAdded == 1){
                columnLeftBinRep = left * 2;
                columnRightBinRep = right * 2 + 1;
            }
            if(rowAdded == 2){
                columnLeftBinRep = left * 2 + 1;
                columnRightBinRep = right * 2;
            }
            if(rowAdded == 3){
                columnLeftBinRep = left * 2 + 1;
                columnRightBinRep = right * 2 + 1;
            }
        }
        public void printColPre(){

            System.out.println(" ====== Col Preimage ====== ");
            for (int i = 0; i < rowBinRep.size(); i++) {
                    System.out.println("Row " + i + " : " + rowBinRep.get(i)); 
            }
            System.out.println("left | " + "right");
            System.out.println("   " + columnLeftBinRep + " | " + columnRightBinRep);
            
        }
        // int[] topRow;
        // int[] botRow;
        
        // int[] leftCol;
        // int[] rightCol;
        
        // int[][] twoByTwoBaseGrid;


        // int topBinaryRepresentation;
        // int botBinaryRepresentation;
        // int leftBinaryRepresentation;
        // int rightBinaryRepresentation;
        

    }
    class BasePreImage{
        
        /**
         
            binary representation of grid 

            for the int[][] 
                   {1,0}
                   {1,1}           

                ------------    
               | 0,0 | 0,1 |
                ------------    
               | 1,0 | 0,1 |
                ------------    
                
                top row is 
                 ------------    
               | 0,0 | 0,1 |
                 ------------    

                   {1,0}

                Deci representation top - 2
                 

                and the bottom row is 
                 ------------    
               | 1,0 | 0,1 |
                ------------    

                   {1,1}

                Deci representation bot - 3 

                
                left column is  && right col is 
                   ------              ------    
                  | 0,0 |             | 0,1 |
                  ------              ------    
                 | 1,0 |             | 0,1 |
                 ------              ------ 

                 {1,                    0}
                 {1,                    1} 
                
                Deci representation left - 3 
                Deci representation right - 1 
             
         */
        int[] topRow;
        int[] botRow;
        
        int[] leftCol;
        int[] rightCol;
        
        int[][] twoByTwoBaseGrid;


        int topBinaryRepresentation;
        int botBinaryRepresentation;
        int leftBinaryRepresentation;
        int rightBinaryRepresentation;
        
        BasePreImage(int[][] twoByTwoBaseGrid){
            this.twoByTwoBaseGrid = twoByTwoBaseGrid;         
            evaluate(twoByTwoBaseGrid); 
            printInformation();
        }
        void evaluate(int[][] twoByTwoBaseGrid){
            getTop();
            getBot();
            getLeft();
            getRight();                        
        }
        void getTop(){
            topRow = new int[twoByTwoBaseGrid.length];
            for (int i = 0; i < twoByTwoBaseGrid.length; i++) {
                topRow[i] = twoByTwoBaseGrid[0][i];
            }
            topBinaryRepresentation = convretToDeci(topRow);
        }
        void getBot(){
            int botRowIndex = twoByTwoBaseGrid.length - 1;
            int lengthOfRow = twoByTwoBaseGrid[botRowIndex].length;
            
            botRow = new int[lengthOfRow];
            for (int i = 0; i < lengthOfRow; i++) {
                botRow[i] = twoByTwoBaseGrid[botRowIndex][i];
            }
            botBinaryRepresentation = convretToDeci(botRow);
        }
        void getLeft(){

            int leftColIndex = twoByTwoBaseGrid.length - 1;
            int lengthOfCol = twoByTwoBaseGrid[leftColIndex].length;
            
            leftCol = new int[lengthOfCol];
            for (int i = 0; i < lengthOfCol; i++) {
                leftCol[i] = twoByTwoBaseGrid[i][0];
            }
            leftBinaryRepresentation = convretToDeci(leftCol);
        }
        void getRight(){
            int rightColIndex = twoByTwoBaseGrid.length - 1;
            int lengthOfCol = twoByTwoBaseGrid[rightColIndex].length;
            
            rightCol = new int[lengthOfCol];
            
            for (int i = 0; i < lengthOfCol; i++) {
                rightCol[i] = twoByTwoBaseGrid[i][rightColIndex];
            }
            rightBinaryRepresentation = convretToDeci(rightCol);
        }
        int convretToDeci(int[] current2x1){
            if(current2x1[0] == 0 && current2x1[1] == 0) {return 0;}
            if(current2x1[0] == 0 && current2x1[1] == 1) {return 1;}
            if(current2x1[0] == 1 && current2x1[1] == 0) {return 2;}
            if(current2x1[0] == 1 && current2x1[1] == 1) {return 3;}
            return -1;
        } 
        void switchTemplate(){

            int x = 0;
            switch (x) {
                    case 1:   
                        break;
                    case 2: 
                        break;
                    case 3:  
                    default:
                        break;
                }
        }
        void printInformation(){
            String line1 = String.format(" Top Row : |-------|  Binary Representation : |---|   Left Col : |---|       Right Col : |---|");
            String line2 = String.format("           | %d | %d |                          | %d |              | %d |                   | %d |", topRow[0],topRow[1], topBinaryRepresentation,leftCol[0], rightCol[0]);
            String line3 = String.format(" Bot Row : |-------|  Binary Representation : |---|              |---|                   |---|  ");
            String line4 = String.format("           | %d | %d |                          | %d |              | %d |                   | %d |", botRow[0], botRow[1], botBinaryRepresentation,leftCol[1],rightCol[1]);
            String line5 = String.format("           |-------|                                            " );
            String line6 = String.format("                                                       Bin Rep : | %d |        Bin Rep :  | %d | ", leftBinaryRepresentation, rightBinaryRepresentation);
            
            System.out.println();
            System.out.println(line1);
            System.out.println(line2);
            System.out.println(line3);
            System.out.println(line4);
            System.out.println(line5);
            System.out.println(line6);
            System.out.println();
        }
    }

    //===========================================================//
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
        new Test3(grid);
    }

}
