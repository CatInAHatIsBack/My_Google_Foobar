package Testing_Suite;


import java.util.ArrayList;
public class Test4 {

    int[][] grid;
    ArrayList<Integer> masterPreimages = new ArrayList<Integer>();
    BasePreImage[] preImageOne;
    BasePreImage[] preImageZero;

    Test4(boolean[][] boolGrid){
        grid = new int[boolGrid.length][boolGrid[0].length];
        transformBoolToInt(boolGrid);
        getBasePreImages(); 
        solve();
    }
    void solve(){
        ArrayList<int[]> colPreImages = new ArrayList<int[]>();
        for (int i = 0; i < grid[0].length; i++) {
            colPreImages = getColPreImages(i);
            masterPreimages = mergeCols(colPreImages);
            colPreImages.clear();
        }
        for (int i = 0; i < masterPreimages.size(); i++) {
            System.out.println(masterPreimages.get(i));
        }
        System.out.println();
        System.out.println();
        System.out.println(masterPreimages.size());
    }
    public ArrayList<Integer> mergeCols(ArrayList<int[]> colPreImages){
        ArrayList<Integer> mergedPreimages = new ArrayList<Integer>();
        if(masterPreimages.size() == 0){
            for (int i = 0; i < colPreImages.size(); i++) {
                mergedPreimages.add(colPreImages.get(i)[2]);
            }
        }
        else{
            for (int i = 0; i < masterPreimages.size(); i++) {
                int curr = masterPreimages.get(i);
                for (int j = 0; j < colPreImages.size(); j++) {
                    if(curr == colPreImages.get(j)[1]){
                        mergedPreimages.add(colPreImages.get(j)[2]);
                    }
                }
            }
        }   
        masterPreimages.clear();
        return mergedPreimages;
    }
    public ArrayList<int[]> getColPreImages(int currentCol){
        // arr int[] stores bot row and right col value
        int[] col = getCurrentCol(currentCol); 
        ArrayList<int[]> currentPossible = new ArrayList<int[]>();

        for (int i = 0; i < col.length; i++) {
            ArrayList<int[]> preimages = new ArrayList<int[]>();
            if(i == 0){
                if(col[i] == 0){
                    for (int j = 0; j < preImageZero.length; j++) {
                        int[] botLeftAndRight = {preImageZero[j].bot, preImageZero[j].leftCol, preImageZero[j].rightCol};
                        preimages.add(botLeftAndRight);
                    } 
                }
                if(col[i] == 1){
                    for (int j = 0; j < preImageOne.length; j++) {
                        int[] botLeftAndRight = {preImageOne[j].bot, preImageOne[j].leftCol, preImageOne[j].rightCol};
                        preimages.add(botLeftAndRight);
                    } 
                }
            }
            else{
                if(col[i] == 0){
                    for (int k = 0; k < currentPossible.size(); k++) {
                        for (int j = 0; j < preImageZero.length; j++) {
                            if(currentPossible.get(k)[0] == preImageZero[j].top){
                                int updatedLeftCol = updateLeftCol(currentPossible.get(k)[1],preImageZero[j].bot);
                                int updatedRightCol = updateRightCol(currentPossible.get(k)[2],preImageZero[j].bot);
                                int[] botLeftAndRight = {preImageZero[j].bot,updatedLeftCol, updatedRightCol};
                                preimages.add(botLeftAndRight);
                            }
                        }
                    }
                }
            
                if(col[i] == 1){
                    for (int k = 0; k < currentPossible.size(); k++) {
                        for (int j = 0; j < preImageOne.length; j++) {
                            if(currentPossible.get(k)[0] == preImageOne[j].top){
                                int updatedLeftCol = updateLeftCol(currentPossible.get(k)[1],preImageOne[j].bot);
                                int updatedRightCol = updateRightCol(currentPossible.get(k)[2],preImageOne[j].bot);
                                int[] botLeftAndRight = {preImageOne[j].bot,updatedLeftCol, updatedRightCol};
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
    public int updateLeftCol(int current, int add){
            if(add == 0 || add == 1){
                return current * 2;
            }
            if(add == 2 || add == 3){
                return current * 2 + 1;
            }
            return -1;
    }
    public int updateRightCol(int current, int add){
            if(add == 0 || add == 2){
                return current * 2;
            }
            if(add == 1 || add == 3){
                return current * 2 + 1;
            }
            return -1;
    }
    public int[] getCurrentCol(int currentCol){
        int[] currCol = new int[grid.length];
        for (int i = 0; i < grid.length; i++) {
            currCol[i] = grid[i][currentCol]; 
        }
        return currCol;
    }
    public void getBasePreImages(){
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
    class BasePreImage{

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
            

            printBase();
        }
        int convretToDeci(int[] current2x1){
            if(current2x1[0] == 0 && current2x1[1] == 0) {return 0;}
            if(current2x1[0] == 0 && current2x1[1] == 1) {return 1;}
            if(current2x1[0] == 1 && current2x1[1] == 0) {return 2;}
            if(current2x1[0] == 1 && current2x1[1] == 1) {return 3;}
            return -1;
        }
        void printBase(){
            System.out.println("top : " + top );
            System.out.println("bot : " + bot);
            System.out.println("right: " + rightCol);
        }

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
        new Test4(grid);
    }
}
