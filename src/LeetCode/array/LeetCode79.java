package LeetCode.array;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cell, 
 * where "adjacent" cells are those horizontally or vertically neighboring. 
 * The same letter cell may not be used more than once.
 * For example,
 * Given board =[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.
 * @author Administrator
 *
 */
public class LeetCode79 {

	public boolean exist(char[][] board, String word) {
        char[] wordChar = word.toCharArray();
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] == word.charAt(0)) {
                    boolean result = search(board, wordChar, i, j, 0);
                    if(result) return true;
                }
            }
        }
        return false;
    }
    
    private boolean search(char[][] board, char[] word, int i, int j, int index) {
        if(index == word.length) return true;
        if(i < 0 || j < 0 || i == board.length || j == board[i].length) return false;
        if(board[i][j] != word[index]) return false;
        board[i][j] ^= 256; //相当于标记已遍历的字符，因为题目要求不能重复
        boolean result = search(board, word, i+1, j, index+1)
            || search(board, word, i-1, j, index+1)
            || search(board, word, i, j+1, index+1)
            || search(board, word, i, j-1, index+1);
        board[i][j] ^= 256;
        return result;
    }

}
