import java.util.Stack;

public class D19 {
    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

//    class Solution {
//        public ListNode insertionSortList(ListNode head) {
//            for(;head.next!=null;head = head.next){
//                ListNode j;
//                while()
//            }
//        }
//    }
    public ListNode insertionSortList2(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null) {
            ListNode tmp = cur.next;
            while (pre.next != null && pre.next.val < cur.val) pre = pre.next;
            cur.next = pre.next;
            pre.next = cur;
            pre = dummy;
            cur = tmp;
        }
        return dummy.next;
    }
    public ListNode insertionSortList3(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        ListNode cur = head;
        while(cur!=null){
            ListNode tmp = cur.next;
            while(pre.next!=null&&pre.next.val<cur.val) pre = pre.next;
            cur.next = pre.next;//means 要把cur加到pre后面（case1：pre后没数，case2：pre.next>cur
            pre.next=cur;
            cur = tmp;
            pre = dummy;
        }
        return dummy.next;
    }

    public ListNode insertionSortList(ListNode head) {
        ListNode h = new ListNode(-1);
        ListNode pre =h;
        ListNode tmp = pre.next;
        ListNode cur = head;
        ListNode lat = cur.next;
        while (lat!=null){
            pre =h;
            tmp = pre.next;
            while(tmp!=lat&&lat.val>tmp.val){
                pre = pre.next;
                tmp = tmp.next;

            }
            if(tmp == lat) cur =lat;
            else {
                cur.next = lat.next;
                lat.next = tmp;
                pre.next = lat;
            }
            lat = cur.next;

        }
        return h.next;
    }
//    public int[] mergeSort(int[] arr,int start, int end){
//        if(start<end){
//            int mid=(start+end)/2;
//            int[]left = mergeSort(arr,start,mid-1);
//            int[]right =mergeSort(arr,mid,end);
//            merge(arr,start,mid,end);
//        }
//        return arr;
//    }
//
//    private void merge(int[] arr, int start, int mid, int end) {
////        int LS = start;
////        int LE = mid-1;
////        int RS = mid;
////        int RE = end;
////        while(LS<LE&&RS<RE){
////            arr =
////        }
//        int p1=start,p2=mid,k = start;
//        int[] tmp = new int[end-start+1];
//        while(p1<=mid&&p2<=end){
//            if(arr[p1]>arr[p2]) tmp[k++] = arr[p2++];
//            else tmp[k++] = arr[p1++];
//
//        }
//        while(p1<mid)tmp[k++] = arr[p1++];
//        while(p2<end)tmp[k++] = arr[p2++];
//        for(int i=0;i<tmp.length;i++){
//            arr[start+i] = tmp[i];
//        }
//    }
    public void merge(int []a,int left,int mid,int right){
        int []tmp=new int[a.length];//辅助数组
        int p1=left,p2=mid+1,k=left;//p1、p2是检测指针，k是存放指针

        while(p1<=mid && p2<=right){
            if(a[p1]<=a[p2])
                tmp[k++]=a[p1++];
            else
                tmp[k++]=a[p2++];
        }

        while(p1<=mid) tmp[k++]=a[p1++];//如果第一个序列未检测完，直接将后面所有元素加到合并的序列中
        while(p2<=right) tmp[k++]=a[p2++];//同上

        //复制回原素组
        for (int i = left; i <=right; i++)
            a[i]=tmp[i];
    }

    public int[] mergeSort(int [] a,int start,int end){
        if(start<end){//当子序列中只有一个元素时结束递归
            int mid=(start+end)/2;//划分子序列
            mergeSort(a, start, mid);//对左侧子序列进行递归排序
            mergeSort(a, mid+1, end);//对右侧子序列进行递归排序
            merge(a, start, mid, end);//合并
        }
        return a;
    }
    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        Stack<Integer> multiStack = new Stack <>();
        Stack<StringBuilder> resStack = new Stack();
        for(Character c:s.toCharArray()){
            if(c=='['){
                multiStack.push(multi);
                multi=0;
                resStack.push(res);
                res = new StringBuilder();
            }else if(c==']'){
                StringBuilder tmp = new StringBuilder();
                int num = multiStack.pop();
                for(int i=0;i<num;i++)
                    tmp.append(res);
                res = resStack.pop().append(tmp);
            }else if(c<='9'&&c>='0') multi = multi*10+c-'0';
            else res.append(c);
        }

        return res.toString();
    }
    public static void main(String[] args) {
        D19 d19 = new D19();
        int [] list = {1,2,3,2,5,6};
        int[] sortedlist =d19.mergeSort(list,0,list.length-1);
        String one ="3[a]2[bc]";
        String res = d19.decodeString(one);
//        System.out.println(sortedlist);
//        Character one = '1';
//        ;
//        System.out.println(one);
//        System.out.println(Integer.valueOf(one));
//        Character two = 'c';
//        System.out.println(two);
//        System.out.println(Integer.valueOf(two));
//        char three = 'a';//char 强转int 结果是 转为char的ascii
//        int threeP = three+0;
//        int threeP2 = (int) three;
//        int threeP3 = three;
//        System.out.println("three "+three);
//        System.out.println(threeP);
//        System.out.println(threeP2);
//        System.out.println("threeP3 "+threeP3);
//        String four = "a";
////        int fourP = (int) four; // 不能将String 强转为int
////        int fourP2 = Integer.valueOf(four);//使用valueof也不能强转
////        System.out.println(fourP2);
//        String five = "2";
//        System.out.println(five);
//        int fiveP = Integer.valueOf(five);
//        System.out.println(fiveP);//如果String 是数字，可以通过valueof强转
//        char six = '9';
//        String sixP = six+"0";
//        int sixP2 = Integer.valueOf(six);//char 不能通过valueof强转，必须先转成String
//        int sixP3 = Integer.valueOf(sixP);//
//        System.out.println(six);
//        System.out.println(sixP);//90
//        System.out.println(sixP2);//57
//        System.out.println(sixP3);//90

        char num = '8';//如果直接+1，就把char强转为int，然后操作
        System.out.println(num+1);
        System.out.println(num-'0');
        System.out.println(num-'0'+1);
        System.out.println(1+num-'0');
        System.out.println(num+""+1);//绝世大坑，把char转化为String，在+1，还是String，操作只能用int鸭，兄弟
        System.out.println(Integer.valueOf(num+"")+1);


    }

}
