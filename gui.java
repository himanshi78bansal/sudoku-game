import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class chat extends JFrame {

    private JTextField[][] cells;
    private JButton solveButton;

    public chat() {
        super("Sudoku Solver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cells = new JTextField[9][9];
        JPanel sudokuPanel = new JPanel(new GridLayout(9, 9));

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j] = new JTextField(1);
                sudokuPanel.add(cells[i][j]);
            }
        }

        solveButton = new JButton("Solve");
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solveSudoku();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(solveButton);

        add(sudokuPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void solveSudoku() {
        int[][] puzzle = new int[9][9];
        // Parse the values from the text fields and update the puzzle array
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String value = cells[i][j].getText();
                puzzle[i][j] = value.isEmpty() ? 0 : Integer.parseInt(value);
            }
        }

        if (solveSudoku(puzzle)) {
            // Update the GUI with the solved values
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    cells[i][j].setText(String.valueOf(puzzle[i][j]));
                }
            }
            JOptionPane.showMessageDialog(this, "Sudoku solved successfully!", "Sudoku Solver", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No solution exists for the given Sudoku puzzle.", "Sudoku Solver", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean solveSudoku(int[][] puzzle) {
        // Implement the Sudoku solver logic here using a backtracking algorithm
        int row = -1;
        int col = -1;
        boolean isEmpty = true;

        // Find the first empty cell (with value 0)
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (puzzle[i][j] == 0) {
                    row = i;
                    col = j;
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }

        // If no empty cell is found, the Sudoku puzzle is solved
        if (isEmpty) {
            return true;
        }

        // Try placing numbers 1 to 9 in the empty cell
        for (int num = 1; num <= 9; num++) {
            if (isValidMove(puzzle, row, col, num)) {
                // If the number is valid, place it in the cell
                puzzle[row][col] = num;

                // Recursively solve the rest of the puzzle
                if (solveSudoku(puzzle)) {
                    return true;
                }

                // If the recursive call doesn't lead to a solution, backtrack and try the next number
                puzzle[row][col] = 0;
            }
        }

        // No number from 1 to 9 leads to a solution for the current empty cell
        return false;
    }

    private boolean isValidMove(int[][] puzzle, int row, int col, int num) {
        // Check if the number is present in the current row or column
        for (int i = 0; i < 9; i++) {
            if (puzzle[row][i] == num || puzzle[i][col] == num) {
                return false;
            }
        }

        // Check if the number is present in the 3x3 subgrid
        int subgridStartRow = row - row % 3;
        int subgridStartCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (puzzle[subgridStartRow + i][subgridStartCol + j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new chat();
            }
        });
    }
}
