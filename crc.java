import java.util.*;
import java.io.*;
public class Main
{
	public static void main(String[] args) {
		//System.out.println("Hello World");
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the size of the data : ");
		int n=sc.nextInt();
		int data[]=new int[n];
		System.out.println("Enter the data in binary :");
		for(int i=0;i<n;i++)
		{
		    int val=sc.nextInt();
		    if(val==0 || val==1)
		        data[i]=val;
		    else{
		        System.out.println("Error");
		        break;
		    }
		}
		//----------------------//
		int d=data.length;
		
	    int[] ndata=new int[d+4-1];
		for(int i=0;i<d;i++)
	        ndata[i]=data[i];
	    for(int i=d;i<ndata.length;i++)
	        ndata[i]=0;
	    //----------------------//
	    int[] ecoded=xor(ndata,d);
	    System.out.println("Encode data sender side to recicer side:");
	    for(int i=0;i<ecoded.length;i++)
	        System.out.print(ecoded[i]);
	   //----CHECKING FOR ERROR IN RECIVER SIDE----//
	    if(ecoded[0]==0)
	        ecoded[0]=1;
	    else
	        ecoded[0]=0;
	        
	    int[] decoded=xor(ecoded,d);
	    System.out.println();
	    for(int i=d;i<decoded.length;i++)
	       if(decoded[i]!=0){
	            System.out.println("Error in data :-("); 
	            System.exit(-1);
	       }
	    System.out.println("No erroe in data...");
	}
	public static int[] xor(int[] data,int len)
	{
	    int[] crc={1,0,0,1};
	    int[] rem=new int[crc.length];
	    int[] ndata=data;
	    int n=crc.length;
	    int d=len;
	    
	    for(int i=0;i<n;i++)
	        rem[i]=data[i];
	        
	    for(int j=0;j<d-1;j++)
	    {
	        int tmp=rem[0];
	        for(int i=1;i<n;i++)
	        {
	            if(tmp==0)
	            {
	                rem[i-1]=rem[i];
	            }
	            else{
	                rem[i-1]=rem[i]^crc[i];
	            }
	        }
	        rem[n-1]=ndata[n+j];
	    }
	    int k=1;
	    for(int i=d;i<ndata.length;i++)
	        ndata[i]=rem[k++];
	    
	    return ndata;
	}
}
