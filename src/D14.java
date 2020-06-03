import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class D14 {
    class Node {
        public int val;
        public List <Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
//    LinkedList<Integer> result= new LinkedList <>();
//    public List<Integer> preorder(Node root) {
//        if(root ==null) return null;
//        else{
//            result.add(root.val);
//            for(int i=0;i<root.children.size();i++){
//                result.add(root.children.get(i).val);
//                preorder(root.children.get(i));
//            }
//        }
//        return result;
//    }

    public List<Integer> preorder(Node root) {
        inOrder(root);
        return res;
    }
    public void inOrder(Node root) {
        if(root == null) {
            return;
        }
        res.add(root.val);
        int s = root.children.size();
        for(int i = 0; i < s; i++) {
            inOrder(root.children.get(i));
        }
    }

    public List<Integer> postorder(Node root) {
        posOrder(root);
        return res;
    }

    public void posOrder(Node root) {
        if(root ==null) return;
        for(int i=0;i<root.children.size();i++){
            posOrder(root.children.get(i));
            res.add(root.val);
        }

    }
    List<Integer> res = new ArrayList <Integer>();
    List<List<Integer>> result = new ArrayList <>();
    public List<List<Integer>> levelOrder(Node root) {
        level(root);
        return result;
    }
    Queue<Node> queue = new LinkedList();
    private void level(Node root) {
        queue.offer(root);

        while(!queue.isEmpty()){
            List<Integer> inner = new LinkedList <>();
            for(int i=0;i<queue.size();i++){
                Node curr = queue.poll();
                inner.add(curr.val);
                for(int j =0;j<curr.children.size();j++){
                    queue.offer(curr.children.get(i));
                }
            }
            result.add(inner);
//
        }

    }

    public int maxDepth(Node root) {
        if(root==null) return -1;
        int max =-1;
        for(int i =0;i<root.children.size();i++){
            int childrenMax =maxDepth(root.children.get(i));
            max = childrenMax>max? childrenMax:max;
        }
        return 1+max;
    }
    class MyCircularQueue {
        public int[] list;
        public int start;
        public int end;
        public int size;
        /** Initialize your data structure here. Set the size of the queue to be k. */
        public MyCircularQueue(int k) {
             list= new int[k+1];
             start=0;
             end=0;
             size = k;
        }

        /** Insert an element into the circular queue. Return true if the operation is successful. */
        public boolean enQueue(int value) {
            if(!isFull()){
                list[end] =value;
                end = (end+1)%size;
                return true;
            }else return false;
        }

        /** Delete an element from the circular queue. Return true if the operation is successful. */
        public boolean deQueue() {
            if(!isEmpty()){
                start=(start+1)%start;
                return true;
            }else  return false;
        }

        /** Get the front item from the queue. */
        public int Front() {
            if(!isEmpty()) return list[start];
            else return -1;
        }

        /** Get the last item from the queue. */
        public int Rear() {
            if(!isEmpty()) return list[(end-1+size)%size];
            else return -1;
        }

        /** Checks whether the circular queue is empty or not. */
        public boolean isEmpty() {
            if(start==end) return true;
            else return false;
        }

        /** Checks whether the circular queue is full or not. */
        public boolean isFull() {
            if((end+1)%size ==start) return true;
            else return false;
        }
    }

}
