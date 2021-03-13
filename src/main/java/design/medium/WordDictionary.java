package design.medium;

/**
 * 211. 添加与搜索单词 - 数据结构设计
 *
 * 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
 * 实现词典类 WordDictionary ：
 *
 * WordDictionary() 初始化词典对象
 * void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
 * bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回  false 。word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。
 *
 * 示例：
 *
 * 输入：
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * 输出：
 * [null,null,null,null,false,true,true,true]
 *
 * 解释：
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 *
 * 提示：
 *
 * 1 <= word.length <= 500
 * addWord 中的 word 由小写英文字母组成
 * search 中的 word 由 '.' 或小写英文字母组成
 * 最多调用 50000 次 addWord 和 search
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/design-add-and-search-words-data-structure
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class WordDictionary {

    private Trie data;
    /** Initialize your data structure here. */
    public WordDictionary() {
        data = new Trie();
    }

    public void addWord(String word) {
        data.insert(word);
    }

    public boolean search(String word) {
        return data.search(word);
    }



    class Trie {

        private boolean isString = false;
        private Trie[] next = new Trie[26];

        public Trie() {
        }

        public void insert(String word) {
            Trie root = this;
            char[] arr = word.toCharArray();
            for (int i = 0;i < arr.length;i++) {
                if (root.next[arr[i] - 'a'] == null) {
                    root.next[arr[i] - 'a'] = new Trie();
                }
                root = root.next[arr[i] - 'a'];
            }
            root.isString = true;
        }

        public boolean search(String word) {
            Trie root = this;
            char[] arr = word.toCharArray();
            for (int i = 0;i < arr.length;i++) {
                if ('.' == arr[i]) {
                    for (Trie cur : root.next) {
                        if (cur == null) {
                            continue;
                        }
                        if (i == arr.length - 1 && cur.isString) {
                            return true;
                        }
                        if (i < arr.length - 1 && cur.search(word.substring(i + 1))) {
                            return true;
                        }
                    }
                    return false;
                } else {
                    if (root.next[arr[i] - 'a'] == null) {
                        return false;
                    }
                    root = root.next[arr[i] - 'a'];
                }
            }
            return root.isString;
        }
    }

}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
