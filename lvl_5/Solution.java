
import java.util.ArrayList;
import java.util.HashMap;
public class Solution {

    static int val;
    static int[][] grid;
    static BasePreImage[] preImageOne;
    static BasePreImage[] preImageZero;
    static HashMap<Integer, Integer> masterMap = new HashMap<Integer,Integer>();
    static ArrayList<int[]> currentPossible = new ArrayList<int[]>();

    public static int solution(boolean[][] boolGrid) {
        grid = new int[boolGrid.length][boolGrid[0].length];
        transformBoolToInt(boolGrid);
        getBasePreImages(); 
        return solve();
        
    }
    static int solve(){
        ArrayList<int[]> colPreImages = new ArrayList<int[]>();
        for (int i = 0; i < grid[0].length; i++) {
            colPreImages = getColPreImages(i);
            mergeCols(colPreImages);
            colPreImages.clear();
        }
        // System.out.println("final val : " + val);
        return val; 
    }
    public static void mergeCols(ArrayList<int[]> colPreImages){
        if(masterMap.size() == 0){
            for (int[] i : colPreImages) {
                if(masterMap.containsKey(i[2])){
                    masterMap.put(i[2], masterMap.get(i[2]) + 1);
                }
                else{
                    masterMap.put(i[2], 1);
                }
            }
            // System.out.println(masterMap);
        }
        else{
            HashMap<Integer, Integer> tempMap = new HashMap<Integer,Integer>();
            val = 0;
            for(int[] i : colPreImages){
                if(masterMap.containsKey(i[1])){
                    if(tempMap.containsKey(i[2])){
                        tempMap.put(i[2], tempMap.get(i[2]) + masterMap.get(i[1]));
                        val += masterMap.get(i[1]);
                    }
                    else{
                        tempMap.put(i[2], masterMap.get(i[1]));
                        val += masterMap.get(i[1]); 
                    }
                }
            }
            masterMap.clear();
            masterMap = tempMap;
        }
    }
    public static ArrayList<int[]> getColPreImages(int currentCol){
        // arr int[] stores bot row and right col value
        int[] col = getCurrentCol(currentCol); 
        for (int i = 0; i < col.length; i++) {
            ArrayList<int[]> preimages = new ArrayList<int[]>();
            if(i == 0){
                if(col[i] == 0){
                    for (int j = 0; j < preImageZero.length; j++) {
                        int[] botLeftAndRight = addAll(preImageZero, j);
                        preimages.add(botLeftAndRight);
                    } 
                }
                if(col[i] == 1){
                    for (int j = 0; j < preImageOne.length; j++) {
                        int[] botLeftAndRight = addAll(preImageOne, j);;
                        preimages.add(botLeftAndRight);
                    } 
                }
            }
            else{
                if(col[i] == 0){
                    for (int k = 0; k < currentPossible.size(); k++) {
                        for (int j = 0; j < preImageZero.length; j++) {
                            if(currentPossible.get(k)[0] == preImageZero[j].top){
                                int[] botLeftAndRight = getBotLeftRight(preImageZero, k, j);
                                preimages.add(botLeftAndRight);
                            }
                        }
                    }
                }
                if(col[i] == 1){
                    for (int k = 0; k < currentPossible.size(); k++) {
                        for (int j = 0; j < preImageOne.length; j++) {
                            if(currentPossible.get(k)[0] == preImageOne[j].top){
                                int[] botLeftAndRight = getBotLeftRight(preImageOne, k, j);
                                preimages.add(botLeftAndRight);
                            } 
                        }
                    }
                }
            }
            currentPossible.clear();
            currentPossible = preimages;
        }
        return currentPossible;
    }
    public static int[] addAll(BasePreImage[] preImage, int j){
        int[] curr = {preImage[j].bot, preImage[j].leftCol, preImage[j].rightCol};
        return curr;
    }

    public static int[] getBotLeftRight(BasePreImage[] preImage, int k, int j){
        int updatedLeftCol = updateLeftCol(currentPossible.get(k)[1],preImage[j].bot);
        int updatedRightCol = updateRightCol(currentPossible.get(k)[2],preImage[j].bot);
        int[] botLeftAndRight = {preImage[j].bot,updatedLeftCol, updatedRightCol}; 
        return botLeftAndRight;
    }
    public static int updateLeftCol(int current, int add){
            if(add == 0 || add == 1){
                return current * 2;
            }
            if(add == 2 || add == 3){
                return current * 2 + 1;
            }
            return -1;
    }
    public static int updateRightCol(int current, int add){
            if(add == 0 || add == 2){
                return current * 2;
            }
            if(add == 1 || add == 3){
                return current * 2 + 1;
            }
            return -1;
    }
    public static int[] getCurrentCol(int currentCol){
        int[] currCol = new int[grid.length];
        for (int i = 0; i < grid.length; i++) {
            currCol[i] = grid[i][currentCol]; 
        }
        return currCol;
    }
    public static void getBasePreImages(){
        preImageOne = new BasePreImage[one.length];     
        preImageZero = new BasePreImage[zero.length];     
        for (int i = 0; i < one.length; i++) {
            preImageOne[i] = new BasePreImage(one[i]);
        }         
        for (int i = 0; i < zero.length; i++) {
            preImageZero[i] = new BasePreImage(zero[i]);
        }
    }
    static class BasePreImage{

        int top;
        int bot;
        int rightCol;
        int leftCol;
        BasePreImage(int[][] preImage){
            top = convretToDeci(preImage[0]);
            bot = convretToDeci(preImage[1]);

            int[] tempLeft = {preImage[0][0], preImage[1][0]};
            int[] tempRight = {preImage[0][1], preImage[1][1]};

            leftCol = convretToDeci(tempLeft);
            rightCol = convretToDeci(tempRight);
        }
        int convretToDeci(int[] current2x1){
            if(current2x1[0] == 0 && current2x1[1] == 0) {return 0;}
            if(current2x1[0] == 0 && current2x1[1] == 1) {return 1;}
            if(current2x1[0] == 1 && current2x1[1] == 0) {return 2;}
            if(current2x1[0] == 1 && current2x1[1] == 1) {return 3;}
            return -1;
        }
    }
    public static void transformBoolToInt(boolean[][] boolGrid){
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
        solution(grid);
    }
}
