public class sudoku {

    public static boolean isSafe(int puzzle[][], int row, int col, int digit){
        // check in column
        for(int i = 0; i<=8; i++){
            if(puzzle[i][col] == digit){
                return false;
            }
        }
        // check in row
        for(int j = 0; j<=8; j++){
            if(puzzle[row][j] == digit){
                return false;
            }
        }
        // check in grids
        int sr = (row/3)*3;
        int sc = (col/3)*3;
        // 3x3 grid
        for(int i = sr; i<sr+3; i++){
            for(int j = sc; j<sc+3; j++){
                if(puzzle[i][j] == digit){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean function(int puzzle[][], int row, int col){
        // base case
        if(row == 9){
            return true;
        }

        // recursion
        
        // next row and col
        int nextRow = row; 
        int nextCol = col+1;
        if(col+1 == 9){
            nextRow = row+1;
            nextCol = 0;
        }

        // for given values
        if(puzzle[row][col] != 0){
            return function(puzzle, nextRow, nextCol);
        }

        // main working
        for(int digit = 1; digit<=9; digit++){
            if(isSafe(puzzle,row, col, digit)){
                puzzle[row][col] = digit;
                if(function(puzzle, nextRow, nextCol)){ // solition exists
                    return true;
                } 
                puzzle[row][col] = 0;
                }
            }
        return false;
    }

    public static void printfun(int puzzle[][]){
        for(int i = 0; i<9; i++){
            for(int j = 0; j<9; j++){
                System.out.print(puzzle[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        // int puzzle[][] = {{0,0,8,0,0,0,0,0,0},
        // {4,9,0,1,5,7,0,0,2},
        // {0,0,3,0,0,4,1,9,0},
        // {1,8,5,0,6,0,0,2,0},
        // {0,0,0,0,2,0,0,6,0},
        // {9,6,0,4,0,5,3,0,0},
        // {0,3,0,0,7,2,0,0,4},
        // {0,4,9,0,3,0,0,5,7},
        // {8,2,7,0,0,9,0,1,3}};

        int puzzle[][] = {{7,0,0,3,0,6,0,0,2},
        {0,0,0,7,0,4,0,9,5},
        {0,5,0,0,2,0,3,0,0},
        {8,0,0,6,0,0,0,5,0},
        {5,4,0,0,8,1,9,0,3},
        {0,0,1,9,0,0,0,6,0},
        {1,0,5,0,9,0,7,0,6},
        {0,6,0,0,0,2,0,0,4},
        {4,0,0,1,6,0,5,0,0}};

        if(function(puzzle, 0, 0)){
            printfun(puzzle);
        } else {
            System.out.println("Soution not exists.");
        }
    }
}
