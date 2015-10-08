import java.util.Scanner;


public class NfatoDfa {
	
	int noofstates=0;
	String terminals="";
	String Arrayterminal[];
	String Table[]=new String[50];
	String NfaTable[][]=new String[50][50];
	String DfaTable[][]=new String[50][50];
	int ptr=0;
	int statept=0;
	int stateposition=1;
	String NfaFinal="";
	int globlek=0;
	NfatoDfa()
	{
		
		accept();
		convertDfa();
		
	//	DrawAutomata d=new DrawAutomata(DfaTable,stateposition,Arrayterminal);
		renewTable();
		
	}
	private void renewTable() {
		for(int i=0;i<stateposition;i++)
		{
			for(int j=1;j<=Arrayterminal.length;j++)
			{
				findpost(i,j);
				
					DfaTable[i][j]="q"+globlek;
				
				
			}
			
			
		}
		for(int i=0;i<stateposition;i++)
		{
			if(DfaTable[i][0].contains("*"))
				DfaTable[i][0]="*q"+i;
			else
			DfaTable[i][0]="q"+i;
			
			
		}
		System.out.println("Dfa new Renew Table");
		for(int i=0;i<stateposition;i++)
		{
			for(int j=0;j<=Arrayterminal.length;j++)
			{
				
				
					System.out.print(DfaTable[i][j]+"\t");
				
				
			}
			System.out.println();
			
			
		}
		// TODO Auto-generated method stub
		
	}
	private boolean findpost(int i,int j) {

		
		for(int k=0;k<stateposition;k++)
		{	int flag1=0;
		int flag2=0;
		
			String	temp1[]=DfaTable[k][0].split(",");
			String	temp2[]=DfaTable[i][j].split(",");
			System.out.println("k="+k+" "+"dfa[k]="+DfaTable[k][0]+" dfa[i][j] "+DfaTable[i][j]);
			for(String temp:temp2)
			{
				if(temp.contains("*"))
					temp=temp.substring(1);
			if(DfaTable[k][0].contains(temp)==false)
				flag1=1;
			}
			for(String temp:temp1)
			{
				if(temp.contains("*"))
					temp=temp.substring(1);
			if(DfaTable[i][j].contains(temp)==false)
				flag2=1;
			}
			if(flag1==0 &&flag2==0)
			{globlek=k;
				return(true);
			}
		}
				return false;
	
	}
	NfatoDfa(String NfaT[][],int nos,String term,String fin)
	{
Arrayterminal=term.split(",");
NfaFinal=fin;
		for(int i=0;i<nos;i++)
		{
			for(int j=1;j<=Arrayterminal.length;j++)
			{
			NfaTable[i][j-1]=NfaT[i][j];
			}
		}
		System.out.print("dfa constr");
		noofstates=nos;
				
		for(int i=0;i<noofstates;i++)
		{
			for(int j=0;j<Arrayterminal.length;j++)
			{
				System.out.print(NfaTable[i][j]+"\t");
				
				
				
			}
			System.out.println();
		}
		convertDfa();
		DrawAutomata d=new DrawAutomata(DfaTable,stateposition,Arrayterminal);
	}
	

	
	public void accept()
	{
		
		
		noofstates=getnoofStates();
		getTerminals();
		acceptTable();
		inputtoNfa();
		
	}
	public void inputtoNfa()
	{
		
		String temp[]=new String[50];
		for(int i=0;i<noofstates;i++)
		{
			
			temp=Table[i].split("\t");
			for(int j=1;j<temp.length;j++)
			{
				NfaTable[i][j-1]=temp[j];
			}
			
			
		}
		for(int i=0;i<noofstates;i++)
		{
			for(int j=0;j<Arrayterminal.length;j++)
			{
				System.out.print(NfaTable[i][j]+"\t");
				
				
				
			}
			System.out.println();
		}
		
		
	}
	
	public void acceptTable()
	{
		System.out.println("Enter table in following format");
		System.out.print("States\t");
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
		
		
		for(int i=0;i<noofstates;i++)
		{
			Scanner sc=new Scanner(System.in);
			Table[i]=sc.nextLine();
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
		Arrayterminal=terminals.split(",");
		

		
		
	}
	public int getnoofStates()
	{
		
		System.out.println("Enter no of states");
		
		Scanner sc=new Scanner(System.in);
		int get=sc.nextInt();

		return get;
		
	}
	public void convertDfa()
	{
		for(int i=0;i<50;i++)
			for(int j=0;j<50;j++)
				DfaTable[i][j]="";
		String ns="";
		DfaTable[0][0]="q0";
		String nsarray[];
		while(true)
		{
			 ns=DfaTable[statept][0];
			 
			if(ns.equals(""))
				break;
			nsarray=ns.split(",");
			for(int k=0;k<nsarray.length;k++)
			{
				
				System.out.print(" k= "+k+" nsarray "+nsarray[k]);
			}
			System.out.println();
			for(int k=0;k<nsarray.length;k++)
			{
				
				int i=find(nsarray[k]);
				System.out.println("exit from in find");
				for(int j=0;j<Arrayterminal.length;j++)
				{
					if(NfaTable[i][j].equals("-")==false)
					{
						if(DfaTable[statept][j+1].contains(NfaTable[i][j])==false)
						{
							if(DfaTable[statept][j+1]=="")
								DfaTable[statept][j+1]=NfaTable[i][j];
							else
							{	String temp[]=NfaTable[i][j].split(",");
								for(String s:temp)
								{
									if(DfaTable[statept][j+1].contains(s)==false)
										
									{
										DfaTable[statept][j+1]=DfaTable[statept][j+1]+","+s;
										
										
									}
									
									
								}
								
							}
						}
						System.out.println("j="+j+" k= "+k+" dfa= "+DfaTable[statept][j+1]);
						
						
					}
					
				}
				
				
				
				
			}
			for(int j=1;j<=Arrayterminal.length;j++)
			{
				
				if(present(DfaTable[statept][j]))
				{
					System.out.println("In present j="+j+" Dfacontent="+DfaTable[statept][j]);
					DfaTable[stateposition++][0]=DfaTable[statept][j];
					System.out.println(" storing Dfa table stateposition="+stateposition+ " dfa content="+DfaTable[stateposition-1][0]);
				}
				
			}
			statept++;
			System.out.println("statept="+statept);
		}
		dfaDisplay();
		
		
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
						if(DfaTable[i][j].contains(temp))
							DfaTable[i][j]="*"+DfaTable[i][j];
						
						
						
					}
						
					
					
				}
				if(DfaTable[i][j].equals(""))
					System.out.print("-\t\t");
				else
					System.out.print(DfaTable[i][j]+"\t\t");
				
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
		
			String	temp1[]=DfaTable[k][0].split(",");
			String	temp2[]=string.split(",");
			for(String temp:temp2)
			{
			if(DfaTable[k][0].contains(temp)==false)
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
		
		
		NfatoDfa test=new NfatoDfa();
		
		
	}
	
	
}
