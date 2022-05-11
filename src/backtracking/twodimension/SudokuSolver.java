package backtracking.twodimension;

import java.util.HashSet;

public class SudokuSolver {
    public static void main(String[] args) {
        char[][] board = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        solveSudoku(board);

        for (int p = 0; p < 9; p++) {
            for (int q = 0; q < 9; q++) {
                System.out.print(board[p][q] + " ");
            }
            System.out.println();
        }

    }

    public static void solveSudoku(char[][] board) {
        HashSet<String> visited = new HashSet<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') {
                    markVisited(visited, i, j, board[i][j], true);
                }
            }
        }

        int r = 0;
        int c = 0;
        outerloop:
        for (; r < board.length; r++) {
            for (; c < board[0].length; c++) {
                if (board[r][c] == '.') {
                    break outerloop;
                }
            }
        }
        System.out.println(" r " + r + " c " + c);

        solve(board, r, c, visited);

        /*for (String s : visited) {
            System.out.println(s);
        }*/
    }

    private static boolean solve(char[][] board, int r, int c, HashSet<String> visited) {
        if (visited.size() == 243) {
            System.out.println("here!");
            return true;
        }

        if (c == board[0].length) {
            c = 0;
            r++;
        }

        if (r >= board.length) {
            return false;
        }

        boolean flag = false;

        if (board[r][c] == '.') {
            for (char k = '1'; k <= '9'; k++) {
                if (markVisited(visited, r, c, k, true)) {
                    board[r][c] = k;
                    flag = solve(board, r, c + 1, visited);
                    if (flag) {
                        break;
                    }
                    board[r][c] = '.';
                    markVisited(visited, r, c, k, false);
                }
            }
        } else {
            flag = solve(board, r, c + 1, visited);
        }

        return flag;
    }

    private static boolean markVisited(HashSet<String> visited, int row, int col, char digit,
        boolean mark) {
        String rowKey = digit + "row" + row;
        String colKey = digit + "col" + col;
        String boxKey = digit + "box" + (row / 3) + "" + (col / 3);

        if (mark) {
            if (visited.contains(rowKey) || visited.contains(colKey) || visited.contains(boxKey)) {
                return false;
            }
            visited.add(rowKey);
            visited.add(colKey);
            visited.add(boxKey);
        } else {
            visited.remove(rowKey);
            visited.remove(colKey);
            visited.remove(boxKey);
        }

        return mark;
    }


    private static String generateKey(int row, int col, char digit) {
        StringBuilder sb = new StringBuilder();
        sb.append(digit);
        sb.append(row);
        sb.append(col);
        sb.append(Math.abs(row - col) / 3);
        return sb.toString();
    }
}
