import java.util.Scanner;

public class bit_bin_opr {
    
	
	public static void main (String[] args) throws java.lang.Exception
	{
	   Scanner sc = new Scanner(System.in);
	   int a = sc.nextInt();
	   
	   while(a-- >0){
	       int   n = sc.nextInt();
	       long k = sc.nextLong();
	       long[] arr = new long[n];
	       
	       long min = Long.MAX_VALUE;
	       for(int  i=0;i<n;i++){
	           arr[i] = sc.nextLong();
	           min = Math.min(min, arr[i]);
	       }
	       while(k-- >0){
	           if(isPrime(min)){
	               min--;
	           }
	           else{
	               min++;
	           }
	       }
	       
	       for(int i=0;i<n;i++){
	           System.out.print(arr[i]+" ");
	       }
	       System.out.println();
	   }
	}
	public static boolean isPrime(long n){
	    if(n<2) return false;
	    
	    if(n==2 || n==3) return false;
	    
	    for(int i=5;i*i<n;i+=6){
	        if(n%i==0 || n%(i+2)==0){
	            return false;
	        }
	    }
	    return true;
	}   
}
