package Lvl_3;
import java.lang.Math;
import java.util.ArrayList;

public class Solution {
    public static int[] solution(int[][] input) {

// This was a great challenge. I thoroughly enjoyed it. Please thank whoever made it


    /** //== Solution ==//
     *  
     *  // Split into terminal and non_terminal states
     *  // Add denominatorTotals
     * 
     *  
     *  // Get Matrix
     *      // I_Matrix
     *      // Q_Matrix
     *      // R_Matrix
     *      // IMinusQ
     *      // F_Matrix
     *      // FR_Matrix
     *  
     *  // Get first row of FR
     *  // Get Lowest common denominator
     *  
//=======================================================
    /**
     *  // get sub determinant
     * 
     *  // get determinant
     * 
     *  // get Temp
     *      // initialize arraylist of fractions and set all to 0/1
     * 
     *  // get matrix of cofactors
     * 
     *  // get inverse
     *  
     *  // inverse
     * 
     */
//=======================================================
    /** //== Fraction ==//
     *  
     *  // Variables                                X                                                            
     *      // numerator                            X                                                    
     *      // denominator                          X                                
     *      // sign (bool)                          X                                
     *                                                              
     *  // constructor( numerator, denominator)     X                                                    
     *      // simplify                             X                            
     *                                                              
     *  // greatest common denominato               X                                            
     *                                                          
     *  // Fraction math                            X                                
     *      // Fraction Plus                        X                                            
     *      // Fraction Minus                       X                                    
     *      // Fraction Multiply                    X                                        
     *      // Fraction Divided by                  X                                        
     *      // Boolean equals                       X                                    
     *                                                          
     *  // Get Classes                              X                            
     *      // Get Numerator                        X                                    
     *      // Get Denominator                      X                                    
     *      // Boolean get Sign                     X                                    
     *                                                              
     *  // to string                                X                            
     *                                                          
     */

        int height = input.length;
        int width = input[0].length;
        ArrayList<Integer> terminal = new ArrayList<>();
        ArrayList<Integer> non_terminal = new ArrayList<>();
        ArrayList<Integer> denominatorTotals = new ArrayList<>();

        ArrayList<ArrayList<Fraction>> I_Matrix;
        ArrayList<ArrayList<Fraction>> Q_Matrix;
        ArrayList<ArrayList<Fraction>> R_Matrix;

        ArrayList<ArrayList<Fraction>> IMinusQ;
        ArrayList<ArrayList<Fraction>> F_Matrix;
        ArrayList<ArrayList<Fraction>> FR_Matrix;

        // checks if the matrix is 1x1
        if (input[0][0] == 0 && input.length == 1) {
            int[] OneXOne = { 1, 1 };
            return OneXOne;
        }
        // sorts terminal and non terminal states
        for (int i = 0; i < height; i++) {
            int total = 0;
            for (int j = 0; j < width; j++) {
                total += input[i][j];
            }
            if (total > 0) {
                non_terminal.add(i);
                denominatorTotals.add(total);
            } else {
                terminal.add(i);
            }
        }
        I_Matrix = getI_Matrix(non_terminal);
        Q_Matrix = getQ_Matrix(non_terminal, denominatorTotals, input);
        R_Matrix = getR_Matrix(non_terminal, terminal, denominatorTotals, input);

        IMinusQ = IMinusQ(I_Matrix, Q_Matrix);
        F_Matrix = getInverse(IMinusQ);
        FR_Matrix = getFR(F_Matrix, R_Matrix);
        
        // first row of fr
        ArrayList<Fraction> FRRow = getRow(FR_Matrix);
        // numerator list
        ArrayList<Fraction> numeratorList = new ArrayList<Fraction>();
        // splitting fr into numerators and denominators
        int[] denominators = new int[FRRow.size()];
        for (int i = 0; i < FRRow.size(); i++) {
            denominators[i] = FRRow.get(i).denominator;
            numeratorList.add(FRRow.get(i));
        }

        // get least common denominator
        int lcm = getLcm(denominators);
        int[] result = new int[FRRow.size() + 1];
        if(FRRow.get(0) == new Fraction(0,1) && FRRow.size() ==1) {
            return new int[] {1,1};
        }
        else {
            for (int j = 0; j < result.length-1; j++) {
                numeratorList.set(j, numeratorList.get(j).multiply(new Fraction(lcm, 1)));
                result[j] = numeratorList.get(j).numerator;
            }
            result[FRRow.size()] = lcm;
            return result;
        }
    }
//=============================================================
//=============================================================

    public static ArrayList<Fraction> getRow(ArrayList<ArrayList<Fraction>> input){
        return input.get(0);
    }
    public static int getLcm(int arr[]) 
    {   // input is denominator list
        int max = 0; 
        int n = arr.length;
        for (int i = 0; i < n; i++) { 
            if (arr[i] > max) { 
                max = arr[i]; 
            } 
        }  
        int rem = 1;   
        int factor = 2; 
        while (factor <= max) {  
            ArrayList<Integer> arrIndex = new ArrayList<Integer>(); 
            for (int j = 0; j < n; j++) { 
                if (arr[j] % factor == 0) {
                    arrIndex.add(arrIndex.size(), j); 
                } 
            }
            if (arrIndex.size() >= 2) { 
                for (int j = 0; j < arrIndex.size(); j++) { 
                    arr[arrIndex.get(j)] /= factor; 
                } 
                rem *= factor; 
            } 
            else { 
                factor++; 
            } 
        } 
        // Multiply all in arr
        for (int i = 0; i < n; i++) { 
            rem *= arr[i]; 
        } 
        return rem; 
    }   
    public static ArrayList<ArrayList<Fraction>> getFR(ArrayList<ArrayList<Fraction>> F, ArrayList<ArrayList<Fraction>> R) {
        int height = R.size();
        int width = R.get(0).size();
        
        if (F.get(0).size() != R.size()) {
            return F;
        }
        else {
            // make arraylist initializer function
            ArrayList<ArrayList<Fraction>> product = getTemp(height, width);

            for (int i = 0; i < F.size(); i++) {
                for (int j = 0; j < width; j++) {
                    for (int k = 0; k < F.get(0).size(); k++) {
                        // product[i][j] += matrix[i][k] * mat.getElement(k, j);
                        Fraction temp = product.get(i).get(j);
                        Fraction a = F.get(i).get(k);
                        Fraction b = R.get(k).get(j);
                        product.get(i).set(j, temp.plus(a.multiply(b)));
                    }
                }
            }
            return product;
        }
    }

    private static void getSubDeterminant(ArrayList<ArrayList<Fraction>> input, ArrayList<ArrayList<Fraction>> output, int excludei, int excludej, int size) {
        int i = 0;
        int j = 0;
        for (int currentRow = 0; currentRow < size; currentRow++) {
            for (int currentCol = 0; currentCol < size; currentCol++) {
                if (currentRow != excludei && currentCol != excludej) {
                    output.get(i).set(j++, input.get(currentRow).get(currentCol));
                    if (j == size - 1) {    // does it need to be inside other if?
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }
    private static Fraction determinant(ArrayList<ArrayList<Fraction>> input, int size) {
        Fraction determinantAsFraction = new Fraction(0, 1);
        if (input.size() != input.get(0).size()) {
            return determinantAsFraction;
        }
        if (size == 1) {
            return input.get(0).get(0);
        }
        ArrayList<ArrayList<Fraction>> temp = getTemp(size, size);        
        int sign = 1;
        Fraction checkerboardFraction = new Fraction(sign, 1);
        for (int i = 0; i < size; i++) {
            getSubDeterminant(input, temp, 0, i, size);
            determinantAsFraction = determinantAsFraction.plus(checkerboardFraction.multiply(input.get(0).get(i).multiply(determinant(temp, size - 1))));
            sign = -sign;
            checkerboardFraction = new Fraction(sign, 1);
        }
        return determinantAsFraction;
    }
    private static ArrayList<ArrayList<Fraction>> getTemp(int sizei, int sizej){
        ArrayList<ArrayList<Fraction>> temp = new ArrayList<ArrayList<Fraction>>();
        for (int i = 0; i < sizei; i++) {
            ArrayList<Fraction> currentRow = new ArrayList<Fraction>();
            for (int j = 0; j < sizej; j++) {
                
                currentRow.add(new Fraction(0, 1));
            }
            temp.add(currentRow);
        }   
        return temp;
    }
    private static void matrixOfCofactors(ArrayList<ArrayList<Fraction>> input, ArrayList<ArrayList<Fraction>> output) {
        int height = input.size();
        int width = input.get(0).size();
        if (width == 1) {
            output.get(0).set(0, new Fraction(1, 1));
            return;
        }
        int sign = 1;
        ArrayList<ArrayList<Fraction>> temp = getTemp(height, width);
        for (int i= 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                getSubDeterminant(input, temp, i, j, width);
                sign = ((i + j) % 2 == 0) ? 1 : -1;
                Fraction signFraction = new Fraction(sign, 1);
                output.get(j).set(i, signFraction.multiply((determinant(temp, width - 1))));
            }
        }
    }
    public static ArrayList<ArrayList<Fraction>> getInverse(ArrayList<ArrayList<Fraction>> input) {
        int height = input.size();
        int width = input.get(0).size();

        Fraction determinantFraction = determinant(input, height);
        ArrayList<ArrayList<Fraction>> ImQInverse = getTemp(height, width);
        if (determinantFraction.equals(new Fraction(0,1))) {
            return ImQInverse;
        }
        
        ArrayList<ArrayList<Fraction>> temp = getTemp(height, width);

        matrixOfCofactors(input, temp);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Fraction tempFraction = temp.get(i).get(j).dividedBy(determinantFraction);
                
                ImQInverse.get(i).set(j, tempFraction);
            }
        }
        return ImQInverse;
    }
    private static ArrayList<ArrayList<Fraction>> IMinusQ(ArrayList<ArrayList<Fraction>> I_Matrix, ArrayList<ArrayList<Fraction>> Q_Matrix) {
        //if(Q_Matrix.size() != Q_Matrix.get(0).size()){
        //}
        ArrayList<ArrayList<Fraction>> ImQ_Matrix = new ArrayList<ArrayList<Fraction>>();
        for (int i = 0; i < Q_Matrix.size(); i++) {
            ArrayList<Fraction> currentRow = new ArrayList<Fraction>();
            for (int j = 0; j < Q_Matrix.size(); j++) {
                Fraction I = I_Matrix.get(i).get(j);
                Fraction Q = Q_Matrix.get(i).get(j);
                currentRow.add(I.minus(Q));
            }
            ImQ_Matrix.add(currentRow);
        }
        return ImQ_Matrix;       
    }
    private static ArrayList<ArrayList<Fraction>> getR_Matrix(ArrayList<Integer> non_terminal,
                                                              ArrayList<Integer> terminal, 
                                                              ArrayList<Integer> denominators, 
                                                              int[][] input) {
        int width = terminal.size();
        int height = non_terminal.size();
        ArrayList<ArrayList<Fraction>> R_Matrix = new ArrayList<ArrayList<Fraction>>();
        for (int i = 0; i < height; i++) {
            ArrayList<Fraction> currentRow = new ArrayList<Fraction>();
            for (int j = 0; j < width; j++) {
                int numerator = input[non_terminal.get(i)][terminal.get(j)];
                int denominator = denominators.get(i);
                currentRow.add(new Fraction(numerator, denominator));
            }
            R_Matrix.add(currentRow);
        }
        return R_Matrix;
    }
    private static ArrayList<ArrayList<Fraction>> getQ_Matrix(ArrayList<Integer> non_terminal,
            ArrayList<Integer> denominators, int[][] input) {
        int len = non_terminal.size();
        ArrayList<ArrayList<Fraction>> Q_Matrix = new ArrayList<ArrayList<Fraction>>();
        for (int i = 0; i < len; i++) {
            ArrayList<Fraction> currentRow = new ArrayList<Fraction>();
            for (int j = 0; j < len; j++) {
                int numerator = input[non_terminal.get(i)][non_terminal.get(j)];
                int denominator = denominators.get(i);
                currentRow.add(new Fraction(numerator, denominator));
            }
            Q_Matrix.add(currentRow);
        }
        return Q_Matrix;
    }
    private static ArrayList<ArrayList<Fraction>> getI_Matrix(ArrayList<Integer> non_terminal) {
        int len = non_terminal.size();
        ArrayList<ArrayList<Fraction>> I_Matrix = new ArrayList<ArrayList<Fraction>>();
        for (int i = 0; i < len; i++) {
            ArrayList<Fraction> currentRow = new ArrayList<Fraction>();
            for (int j = 0; j < len; j++) {
                if (i == j) {
                    currentRow.add(new Fraction(1, 1));
                } else {
                    currentRow.add(new Fraction(0, 1));
                }
            }
            I_Matrix.add(currentRow);
        }
        return I_Matrix;
    }

    //===========================================================================//
    //===========================================================================//
    //===========================================================================//
    //===========================================================================//
    //===========================================================================//
    //===========================================================================//
    private static class Fraction{ 
        private int numerator;
        private int denominator = 1;   
        private boolean sign = false; // true = negative, false = positive
        public Fraction(int numerator, int denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
            this.reduce();    
        }
        private int getGcm(int num1, int num2) {
            return num2 == 0 ? num1 : this.getGcm(num2, num1 % num2);    
        }
        public void reduce() {                
            if(!(this.numerator <= 0 && this.denominator <= 0) && !(this.numerator >= 0 && this.denominator >= 0)){
                this.sign = true;
            }
            this.numerator = Math.abs(this.numerator);        
            this.denominator = Math.abs(this.denominator);
            int gcm = this.getGcm(this.numerator, this.denominator);
            this.numerator = this.numerator / gcm;
            this.denominator = this.denominator / gcm;
            // When fraction is zero, make sure denominator is one and no negative sign
            if (this.numerator == 0 && this.denominator != 0) {
                this.denominator = 1;
                this.sign = false;
            }   
        }
        public Fraction plus(Fraction fractionIn) {
            int num = 0;
            int sign = 1;
            int a = this.numerator * fractionIn.denominator;
            int b = this.denominator * fractionIn.numerator;
            if (this.sign) { // this fraction is negative
                sign = -1;
            }
            if(fractionIn.sign) {
                num = sign * a + (-1)*b;
            }
            else {
                num = sign * a + b;
            }

            int denom = this.denominator * fractionIn.denominator;
            return new Fraction(num, denom);   
        }
        public Fraction minus(Fraction fractionIn) {
            int num = 0;
            int sign = 1;
            int a = this.numerator * fractionIn.denominator;
            int b = this.denominator * fractionIn.numerator;
            if (this.sign) { // this fraction is negative
                sign = -1;
            }
            if (fractionIn.sign) { // f1 is negative
                num = sign * a + b;
            } 
            else { // f1 is positive
                num = sign * a - b;                
            }
            int denom = this.denominator * fractionIn.denominator;
            return new Fraction(num, denom);    
        }
        public Fraction multiply(Fraction fractionTwo) {
            int sign = 1;
            
            if (this.sign && !fractionTwo.sign || !this.sign && fractionTwo.sign) {
                sign = -1;
            }
            return new Fraction(sign* this.numerator * fractionTwo.numerator, this.denominator * fractionTwo.denominator);   
        }
        public Fraction dividedBy(Fraction fractionTwo) {
            int sign = 1;
            // Either one fraction is negative will make the product fraction negative, but not for both fractions are negative.
            if (this.sign && !fractionTwo.sign || !this.sign && fractionTwo.sign) {
                sign = -1;
            }
            return new Fraction(sign *this.numerator * fractionTwo.denominator, this.denominator * fractionTwo.numerator);    }

        public boolean equals(Fraction fractionTwo) {
        return this.numerator == fractionTwo.numerator && this.denominator == fractionTwo.denominator && this.sign == fractionTwo.sign;
        }
       
        @Override
        public String toString() {
            return this.numerator + "/" + this.denominator;
        }
    }
    // Made by CatInAHat
}
class Main {
    public static void main(String[] args) {
        int[][] input = {{0, 2, 1, 0, 0}, {0, 0, 0, 3, 4}, {0, 0, 0, 0, 0}, {0, 0, 0, 0,0}, {0, 0, 0, 0, 0}};
        Solution.solution(input);
    }
    
}