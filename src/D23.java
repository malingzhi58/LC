import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class D23 {
    class MapSum {
        boolean isEnd = false;
        HashMap<Character,MapSum> next = new HashMap <>();
        int value = 0;
        public MapSum() {
        }
        public void insert(String key, int val) {
            MapSum root =this;
            char[] list = key.toCharArray();
            for(int i=0;i<list.length;i++){
                if(root.next.get(list[i])==null){
                    root.next.put(list[i],new MapSum());
                    root = root.next.get(list[i]);
                }
            }
            root.value=val;
        }
        public int sum(String prefix) {
            MapSum root =this;
            char[] list = prefix.toCharArray();
            for(int i=0;i<list.length;i++){
                if(root.next.get(list[i])!=null){
                    root = root.next.get(list[i]);
                }else return 0;
            }
            return sumChildren(root);
        }

        private int sumChildren(MapSum root) {
            int res = root.value;
            for(Character each:root.next.keySet()){
                res+=sumChildren(root.next.get(each));
            }
            return res;
        }
    }
    public String replaceWords(List <String> roots, String sentence) {
        HashSet<String> set  = new HashSet <>();
        for(String root:roots){
            set.add(root);
        }
        List<String> sent = Arrays.asList(sentence.split("//s+"));
        StringBuilder ans = new StringBuilder();
        for(String word:sent){
            char[] one = word.toCharArray();
            String prefix= new String();
            for(int i=0;i<one.length;i++){
                if(set.contains(word.substring(0,i))) {
                    prefix = word.substring(0,i);
                    break;}
//                if(ans.length()>0) ans.append(" ");
            }
            ans.append(prefix);
//            if(ans.length()>0)
            ans.append(" ");
        }

        return ans.toString();
    }
    public String replaceWords2(List<String> roots, String sentence) {
        TrieNode root = new TrieNode();
        for(String num: roots){
            char[] array = num.toCharArray();
            for(int i=0;i<array.length;i++){
                if(root.children[array[i]-'a']==null)root.children[array[i]-'a']= new TrieNode();
                root = root.children[array[i]-'a'];
            }
            root.word = num;
        }
        List<String> sent = Arrays.asList(sentence.split("//s+"));
        StringBuilder ans = new StringBuilder();
        for(String word:sent){
//            String result;
            TrieNode cur = root;
            char[] array=word.toCharArray();
            for(int i=0;i<array.length;i++){
                if(cur.children[array[i]-'a']==null||cur.word!=null){
//                    result=word;
                    break;
                }
                cur = cur.children[array[i]-'a'];
//                if()
            }

            ans.append(cur.word!=null?cur.word:word);
            ans.append(" ");
        }
        return ans.toString();
    }
    class TrieNode{
        public String word;
        public TrieNode[] children;
        public TrieNode() {
            this.children = new TrieNode[26];
        }
    }



    class WordDictionary {
        private WordDictionary[] next;
        private boolean isEnd;
        public WordDictionary() {
            next = new WordDictionary[26];
            isEnd =false;
        }
        public void addWord(String word) {
            char[] num =word.toCharArray();
            WordDictionary cur = this;
            for(int i=0;i<num.length;i++){
                if(cur.next[num[i]-'a']==null)cur.next[num[i]-'a']=new WordDictionary();
                cur = cur.next[num[i]-'a'];
            }
            cur.isEnd=true;
        }
        /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
//        public boolean search(String word) {
//            return match(word,this,0);
//        }

//        private boolean match(String word, WordDictionary root, int start) {
//            if(start==word.length()) return root.isEnd;
//            char cur = word.charAt(start);
//            if(root.next[cur-'a']==null) return false;
//            else if(cur=='.'){
//                for(int i=0;i<26;i++){
//                    match(word,root.next[i],start+1);
//                }
//            }
//            return match(word,root.next[cur-'a'],start+1);
//        }
    }
    class MyThread extends Thread{
        @Override
        public void run() {
            super.run();
        }
    }
}
