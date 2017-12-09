package LeetCode.array;

import java.util.ArrayList;
import java.util.List;

public class LeetCode212 {
/**太慢
	public List<String> findWords(char[][] board, String[] words) {
        List<String> answers = new ArrayList<>();
        for(String word: words) {
            String answer = wordSearch(board, word);
            if(answer != null) {
                answers.add(answer);
            }
        }
        return answers;
    }
    
    public String wordSearch(char[][] board, String word) {
        char[] wordChar = word.toCharArray();
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (search(board, wordChar, i, j, 0)) {
                    return word;
                }
            }
        }
        return null;
    }
    
    private boolean search(char[][] board, char[] wordChar, int i, int j, int index) {
        if(index == wordChar.length) return true;
        if(i < 0 || j < 0 || i == board.length || j == board[i].length) return false;
        if(board[i][j] != wordChar[index]) return false;
        board[i][j] ^= 256;
        boolean result = search(board, wordChar, i+1, j, index+1)
            || search(board, wordChar, i-1, j, index+1)
            || search(board, wordChar, i, j+1, index+1)
            || search(board, wordChar, i, j-1, index+1);
        board[i][j] ^= 256;
        return result;
    }
    */
	//字典树 + 回溯
    private class TrieNode{
        String word;
        TrieNode[] trie = new TrieNode[26];
    }
    
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for(String word: words) {
            TrieNode temp = root;
            for(char wordChar: word.toCharArray()) {
                int index = wordChar - 'a';
                if(temp.trie[index] == null) temp.trie[index] = new TrieNode();
                temp = temp.trie[index];
            }
            temp.word = word;
        }
        return root;
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = buildTrie(words);
        List<String> list = new ArrayList<>();
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                dfs(board, root, list, i, j);
            }
        }
        return list;
    }
    
    private void dfs(char[][] board, TrieNode node, List<String> list, int i, int j) {
        char c = board[i][j];
        if(c == '#' || node.trie[c-'a'] == null) return;
        TrieNode tn = node.trie[c-'a'];
        if(tn.word != null) {
            list.add(tn.word);
            tn.word = null; //de-duplicate
        }
        board[i][j] = '#';
        if(i > 0) dfs(board, tn, list, i-1, j);
        if(i < board.length-1) dfs(board, tn, list, i+1, j);
        if(j > 0) dfs(board, tn, list, i, j-1);
        if(j < board[i].length-1) dfs(board, tn, list, i, j+1);
        board[i][j] = c;
    }
}
