import java.util.*;

public class D24 {
    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node() {
        }

        public Node(int _val, Node _prev, Node _next, Node _child) {
            val = _val;
            prev = _prev;
            next = _next;
            child = _child;
        }


        class Solution {
            public Node flatten(Node head) {
                if (head == null) return head;
                // pseudo head to ensure the `prev` pointer is never none
                Node pseudoHead = new Node(0, null, head, null);

                flattenDFS(pseudoHead, head);

                // detach the pseudo head from the real head
                pseudoHead.next.prev = null;
                return pseudoHead.next;
            }

            /* return the tail of the flatten list */
            public Node flattenDFS(Node prev, Node curr) {
                if (curr == null) return prev;
                curr.prev = prev;
                prev.next = curr;

                // the curr.next would be tempered in the recursive function
                Node tempNext = curr.next;

                Node tail = flattenDFS(curr, curr.child);
                curr.child = null;

                return flattenDFS(tail, tempNext);
            }
        }

        class Solution2 {
            public Node flatten(Node head) {
                Node dmy = new Node(-1, null, head, null);
                Node tail = DFS(dmy, head);
                dmy.next.prev = null;
                return dmy.next;
            }

            private Node DFS(Node pre, Node cur) {
                if (cur == null) return pre;
                pre.next = cur;
                cur.prev = pre;
                Node tmp = cur.next;
                Node tail = DFS(cur, cur.child);
                cur.child = null;
                return DFS(tail, tmp);
            }
        }

        class Solution3 {
//            public Node flatten(Node head) {
//                Node dmy = new Node(-1,null,head,null);
//                Node cur = dmy;
//                DFS(cur,head);
//                dmy.next.prev=null;
//                return dmy.next;
//            }

            //            private Node DFS(Node pre, Node head) {
//                Queue<Node> queue = new LinkedList <>();
//                queue.add(head);
//                while(!queue.isEmpty()){
//                    Node cur = queue.poll();
//                    pre.next=cur;
//                    cur.prev =pre;
//                    // 好像要用stack
//                    pre = pre.next;
//                    queue.add(cur.next);
//                    if(cur.child!=null){
//                        queue.add(cur.child);
//                    }
//
//                }
//            }
            private Node DFS2(Node head) {
                Node du = new Node(-1, null, null, null);
                Stack <Node> stack = new Stack <>();
                stack.push(head);
                Node op = du;
                while (!stack.isEmpty()) {
                    Node cur = stack.pop();
                    op.next = cur;
                    cur.prev = op;
                    if (cur.next != null) stack.push(cur.next);
                    if (cur.child != null) stack.push(cur.child);
                }
                return du;
            }
        }

        Deque <Node> stack = new ArrayDeque <>();
    }

    public Node flatten(Node head) {
        if (head == null) return head;
        Node pseudoHead = new Node(0, null, head, null);
        Node curr, prev = pseudoHead;
        Deque <Node> stack = new ArrayDeque <>();
        stack.push(head);
        while (!stack.isEmpty()) {
            curr = stack.pop();
            prev.next = curr;
            curr.prev = prev;

            if (curr.next != null) stack.push(curr.next);
            if (curr.child != null) {
                stack.push(curr.child);
                // don't forget to remove all child pointers.
                curr.child = null;
            }
            prev = curr;
        }
        // detach the pseudo node from the result
        pseudoHead.next.prev = null;
        return pseudoHead.next;
    }

    class Solution {
        class Node {
            int val;
            Node next;
            Node random;

            public Node(int _val, Node _next, Node _random) {
                val = _val;
                next = _next;
                random = _random;
            }
        }

        // HashMap which holds old nodes as keys and new nodes as its values.
        HashMap <Node, Node> visitedHash = new HashMap <Node, Node>();

        public Node copyRandomList(Node head) {

            if (head == null) {
                return null;
            }

            // If we have already processed the current node, then we simply return the cloned version of
            // it.
            if (this.visitedHash.containsKey(head)) {
                return this.visitedHash.get(head);
            }

            // Create a new node with the value same as old node. (i.e. copy the node)
            Node node = new Node(head.val, null, null);

            // Save this value in the hash map. This is needed since there might be
            // loops during traversal due to randomness of random pointers and this would help us avoid
            // them.
            this.visitedHash.put(head, node);

            // Recursively copy the remaining linked list starting once from the next pointer and then from
            // the random pointer.
            // Thus we have two independent recursive calls.
            // Finally we update the next and random pointers for the new node created.
            node.next = this.copyRandomList(head.next);
            node.random = this.copyRandomList(head.random);

            return node;
        }
    }

    class Solution2 {
        class Node {
            int val;
            Node next;
            Node random;

            public Node() {
            }

            public Node(int _val, Node _next, Node _random) {
                val = _val;
                next = _next;
                random = _random;
            }
        }

        // HashMap which holds old nodes as keys and new nodes as its values.
        HashMap <Node, Node> visitedHash = new HashMap <Node, Node>();

        public Node copyRandomList(Node head) {
            if (visitedHash.containsKey(head)) {
                return visitedHash.get(head);
            }
            Node node = new Node(head.val, null, null);
            visitedHash.put(head, node);
            node.next = copyRandomList(head.next);
            node.random = copyRandomList(head.random);
            return node;
        }

// Definition for a Node.





    }
    public class Solution3 {

            class Node {
                public int val;
                public Node next;
                public Node random;

                public Node(int _val) {
                    val = _val;
                    next = null;
                    random = null;
                }

                public Node(int _val, Node _next, Node _random) {
                    val = _val;
                    next = _next;
                    random = _random;
                }
            }

            ;
            // Visited dictionary to hold old node reference as "key" and new node reference as the "value"
            HashMap <Node, Node> visited = new HashMap <Node, Node>();

            public Node getClonedNode(Node node) {
                // If the node exists then
                if (node != null) {
                    // Check if the node is in the visited dictionary
                    if (this.visited.containsKey(node)) {
                        // If its in the visited dictionary then return the new node reference from the dictionary
                        return this.visited.get(node);
                    } else {
                        // Otherwise create a new node, add to the dictionary and return it
                        this.visited.put(node, new Node(node.val, null, null));
                        return this.visited.get(node);
                    }
                }
                return null;
            }

            public Node copyRandomList(Node head) {
                if (head == null) {
                    return null;
                }
                Node oldNode = head;
                // Creating the new head node.
                Node newNode = new Node(oldNode.val);
                this.visited.put(oldNode, newNode);

                // Iterate on the linked list until all nodes are cloned.
                while (oldNode != null) {
                    // Get the clones of the nodes referenced by random and next pointers.
                    newNode.random = this.getClonedNode(oldNode.random);
                    newNode.next = this.getClonedNode(oldNode.next);
                    // Move one step ahead in the linked list.
                    oldNode = oldNode.next;
                    newNode = newNode.next;
                }
                return this.visited.get(head);
            }
        }
    /*
// Definition for a Node.
class Node {
public int val;
public Node next;
public Node random;

public Node() {}

public Node(int _val,Node _next,Node _random) {
    val = _val;
    next = _next;
    random = _random;
}
};
*/
    public class Solution4 {
        class Node {
            public int val;
            public Node next;
            public Node random;

            public Node() {}
            public Node(int _val) {
                val = _val;
                next = null;
                random = null;
            }
            public Node(int _val,Node _next,Node _random) {
                val = _val;
                next = _next;
                random = _random;
            }
        };
        public Node copyRandomList(Node head) {
            Node start = head;

            while (start!=null){
                Node newNode = new Node(start.val);
                Node next = start.next;
                start.next=newNode;
                newNode.next=next;
                start=next;
            }
            start = head;
            //while wrong --start.next!=null
            while(start!=null){
                start.next.random = start.random==null?null:start.random.next;
                start=start.next.next;
            }
            Node oldNode = head,newNode = head.next,newHead = newNode;

            //while wrong --newNode!=null
            while(oldNode!=null){
                oldNode.next=oldNode.next.next;
                newNode.next = newNode.next.next;
                oldNode=oldNode.next;
                newNode=newNode.next;
            }
            return newHead;
        }
        public Node copyRandomList2(Node head) {

            if (head == null) {
                return null;
            }

            // Creating a new weaved list of original and copied nodes.
            Node ptr = head;
            while (ptr != null) {

                // Cloned node
                Node newNode = new Node(ptr.val);

                // Inserting the cloned node just next to the original node.
                // If A->B->C is the original linked list,
                // Linked list after weaving cloned nodes would be A->A'->B->B'->C->C'
                newNode.next = ptr.next;
                ptr.next = newNode;
                ptr = newNode.next;
            }

            ptr = head;

            // Now link the random pointers of the new nodes created.
            // Iterate the newly created list and use the original nodes' random pointers,
            // to assign references to random pointers for cloned nodes.
            while (ptr != null) {
                ptr.next.random = (ptr.random != null) ? ptr.random.next : null;
                ptr = ptr.next.next;
            }

            // Unweave the linked list to get back the original linked list and the cloned list.
            // i.e. A->A'->B->B'->C->C' would be broken to A->B->C and A'->B'->C'
            Node ptr_old_list = head; // A->B->C
            Node ptr_new_list = head.next; // A'->B'->C'
            Node head_old = head.next;
            while (ptr_old_list != null) {
                ptr_old_list.next = ptr_old_list.next.next;
                ptr_new_list.next = (ptr_new_list.next != null) ? ptr_new_list.next.next : null;
                ptr_old_list = ptr_old_list.next;
                ptr_new_list = ptr_new_list.next;
            }
            return head_old;
        }
    }
}
