public class D4Exam {
    public int[] printPrime(int n){
        if(n<2) return new int[1];
        int count =0,start=3;
        int[] result = new int[n];
        result[count++]=2;
        while(count<n){
            int isCounter=0;
            for(int i=start;i>=2;i--) {
                if (start%i==0)break;
                else isCounter++;
            }
            if(isCounter==0) result[count++]=start;
            start++;

        }
        return result;
    }
}
