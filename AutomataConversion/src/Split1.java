
public class Split1 {
	public static void main(String args[])
	{
		String s=",q0,q1,q2,";
		String temp[];
		temp=s.split(",");
		for(int k=0;k<temp.length;k++)
			System.out.println(k+" "+temp[k]);
		
		
		
	}

}
