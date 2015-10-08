import java.util.Scanner;


public class ENfatoNfa {
	
	int noofstates=0;
	String terminals="";
	String Arrayterminal[];
	String Table[]=new String[50];
	String ENfaTable[][]=new String[50][50];
	String NfaTable[][]=new String[50][50];
	int ptr=0;
	int statept=0;
	int stateposition=1;
	String NfaFinal="";
	String finalclosure="";
	ENfatoNfa()
	{
		
		accept();
		convertNfa();
//		//DrawAutomata d=new DrawAutomata(NfaTable,stateposition,Arrayterminal);
		NfatoDfa nf=new NfatoDfa(NfaTable,noofstates,terminals.substring(0,terminals.length()-2),NfaFinal);
	}
	

	
	public void accept()
	{
		
		
		noofstates=getnoofStates();
		getTerminals();
		acceptTable();
		inputtoENfa();
		
	}
	public void inputtoENfa()
	{
		
		String temp[]=new String[50];
		for(int i=0;i<noofstates;i++)
		{
			
			temp=Table[i].split("\t");
			for(int j=1;j<temp.length;j++)
			{
				ENfaTable[i][j-1]=temp[j];
			}
			
			
		}
		for(int i=0;i<noofstates;i++)
		{
			for(int j=0;j<Arrayterminal.length;j++)
			{
				System.out.print(ENfaTable[i][j]+"\t");
				
				
				
			}
			System.out.println();
		}
		
		
	}
	
	public void acceptTable()
	{
		
		
		String acceptstring="";
		Scanner sc=new Scanner(System.in);
		acceptstring=sc.nextLine();
		
		System.out.println("Enter table in following format");
		System.out.print("States\t");
		
		//for(int i=0;i<Arrayterminal.length;i++)
			
			//Arrayterminal[i]="^";
		for(int i=0;i<Arrayterminal.length;i++)
		System.out.print(Arrayterminal[i]+"\t");
		
		System.out.println();
		System.out.print("q0\tq0,q1\t");
		
		
		
		for(int i=0;i<Arrayterminal.length-1;i++)
			System.out.print("q"+i+"\t");
		System.out.println();

		System.out.println("Enter the table");
		
		System.out.print("States\t");
		for(int i=0;i<Arrayterminal.length;i++)
		System.out.print(Arrayterminal[i]+"\t");
		System.out.println();
		
		Table=acceptstring.split("#");
		
		for(int i=0;i<noofstates;i++)
		{
			
			System.out.println(Table[i]);
			if(Table[i].contains("*"))
			{
				
				Table[i]=Table[i].substring(1);
				NfaFinal=NfaFinal+Table[i].substring(0,2)+",";
				System.out.println("In final state where that i =1");
			}
			
			
		}
		System.out.print("States\t");
		for(int i=0;i<Arrayterminal.length;i++)
		System.out.print(Arrayterminal[i]+"\t");
		System.out.println();
		for(int i=0;i<noofstates;i++)
		{
			
			System.out.println(Table[i]);
		}
		
	}
	
	public void getTerminals()
	{
		
		System.out.println("Enter the Input separated by comma(,)");
		Scanner sc=new Scanner(System.in);
		terminals=sc.nextLine();
		terminals+=",^";
		Arrayterminal=terminals.split(",");
		

		
		
	}
	public int getnoofStates()
	{
		
		System.out.println("Enter no of states");
		
		Scanner sc=new Scanner(System.in);
		int get=sc.nextInt();

		return get;
		
	}
	public void convertNfa()
	{	String TempNfaString="";
		for(int i=0;i<noofstates;i++)	
		{
			NfaTable[i][0]="q"+(char)(i+48);
			finalclosure="";
			Eclosure(NfaTable[i][0]);
			String finalclosurearray[]=finalclosure.split(",");
				for(int j=0;j<Arrayterminal.length-1;j++)
				{	NfaTable[i][j+1]="";
					TempNfaString="";
					for(String s:finalclosurearray)
					{
					
						System.out.println("string "+s);
						int k=Integer.parseInt(s.substring(1,2));
						String s1[]=ENfaTable[k][j].split(",");
						for(String temp1:s1)
						{
							if(TempNfaString.contains(temp1)==false)
							{
								TempNfaString=TempNfaString+temp1+",";
								
								
								
							}
						}
					
					}
					
					String laststageNfaarray[]=TempNfaString.split(",");
					for(String s:laststageNfaarray)
					{
						finalclosure="";
						Eclosure(s);
						String temp3[]=finalclosure.split(",");
						for(String s1:temp3)
						if(NfaTable[i][j+1].contains(s1)==false)
						{
							if(NfaTable[i][j+1]!="")
							NfaTable[i][j+1]=NfaTable[i][j+1]+","+s1;
							else
								NfaTable[i][j+1]=s1;
								
							
							System.out.println("Nfa["+i+"]"+"["+(j+1)+"]"+NfaTable[i][j+1]);
							
						}
						}
							
						
					}
					
				}
		displayNfa();
		
		}
			
			
		
		
	private void displayNfa() {
		for(int i=0;i<noofstates;i++)
		{
			for(int j=0;j<Arrayterminal.length;j++)
			{
				System.out.print(NfaTable[i][j]+"\t");
				
			}
		// TODO Auto-generated method stub
		System.out.println();
		}
	}



	public void Eclosure(String string) {
		if(string.equals("-"))
			return;
	//	if(string==null)
		//	return;
		if(finalclosure.contains(string)==false)
		{
			finalclosure=finalclosure+string+",";
			int j=Integer.parseInt(string.substring(1,2));
			System.out.println(ENfaTable[j][Arrayterminal.length-1]);
			String temp[]=ENfaTable[j][Arrayterminal.length-1].split(",");
			
			for(String s:temp)
			{
				System.out.println("s="+s);
				Eclosure(s);
				
				
			}
			
		
		}
		
		
	}

	
	public void dfaDisplay()
	{
		String NfaFinalsplit[]=NfaFinal.split(",");
		System.out.println("DFA TABLE");
		for(int i=0;i<stateposition;i++){
			for(int j=0;j<Arrayterminal.length+1;j++)
			{
				if(j==0)
				{
					for(String temp:NfaFinalsplit)
					{
						if(NfaTable[i][j].contains(temp))
							NfaTable[i][j]="*"+NfaTable[i][j];
						
						
						
					}
						
					
					
				}
				if(NfaTable[i][j].equals(""))
					System.out.print("-\t\t");
				else
					System.out.print(NfaTable[i][j]+"\t\t");
				
			}
		System.out.println();
		}
		
	}
	
	private boolean present(String string) {
		
		
		if(string.equals(""))
			return false;
		
		for(int k=0;k<stateposition;k++)
		{	int flag1=0;
		int flag2=0;
		
			String	temp1[]=NfaTable[k][0].split(",");
			String	temp2[]=string.split(",");
			for(String temp:temp2)
			{
			if(NfaTable[k][0].contains(temp)==false)
				flag1=1;
			}
			for(String temp:temp1)
			{
			if(string.contains(temp)==false)
				flag2=1;
			}
			if(flag1==0 &&flag2==0)
				return(false);
		}
		
		
		// TODO Auto-generated method stub
		return true;
	}



	private int find(String string) {
		System.out.println(" In find");
		String temp=string;
		String temp1=temp.substring(1,2);
		return(Integer.parseInt(temp1));
		// TODO Auto-generated method stub
		
	}



	
	public static void main(String args[])
	{
		
		
		ENfatoNfa test=new ENfatoNfa();
		
		
	}
	
	
}
