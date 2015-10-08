import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class DrawAutomata extends Frame  implements WindowListener{
	
	Ellipse2D circle[]=new Ellipse2D[20];
	int noofstates;
	int temp=0;
	String DfaTable[][];
	String Arrayterminal[];
	int horizontal=40;
	int vertical=40;
	int sx,sy,dx,dy,cx,cy,tempcx,tempcy;
	int globlek;
	Stroke drawingStroke = new BasicStroke(1);
	  QuadCurve2D curve = new QuadCurve2D.Double();

	public DrawAutomata(String D[][],int nos,String At[])
	{
		noofstates=nos;
		DfaTable=D;
		Arrayterminal=At;
		int x=100;
		int y=100;
		this.setSize(1000,1000);
		this.setVisible(true);
		
		for(int i=0;i<noofstates;i++)
		{
			if(i%3==0 && i!=0)
			{
				y=y+200;
				x=100;
				
				
			}
			circle[i]=new Ellipse2D.Double(x,y,40,40);
			x=x+200;
			
			
				
			
		}  
		System.out.println("out from loop in constructor");
		this.addWindowListener(this);
		System.out.println("out from loop in constructor with temp:"+temp);
		
		
	}
	public void windowClosing(WindowEvent e)
	{


		System.exit(0);
	}
	public void windowOpened(WindowEvent e)
	{


		
	}
	public void windowIconified(WindowEvent e)
	{


		
	}
	public void windowDeiconified(WindowEvent e)
	{


	
	}
	public void windowClosed(WindowEvent e)
	{


		System.exit(0);
	}
	public void windowActivated(WindowEvent e)
	{


	
	}
	public void windowDeactivated(WindowEvent e)
	{


	
	}

	public void paint(Graphics g) {
		Graphics2D ga=(Graphics2D)g;
		for(int i=0;i<noofstates;i++)
				{
					ga.draw(circle[i]);
					if(DfaTable[i][0].contains("*"))
						ga.drawOval((int)circle[i].getX()-5,(int)circle[i].getY()-5,50,50 );
					ga.drawString(DfaTable[i][0],(int)circle[i].getX()+40,(int)circle[i].getY()+40);
					
				}
		temp++;
		System.out.println("out from loop in paint:temp="+temp);
		ga.setStroke(drawingStroke);
		  ga.setPaint(Color.blue);
		
		for(int i=0;i<noofstates;i++)
		{
			for(int j=1;j<=Arrayterminal.length;j++)
			{
				
				putLine(i,j);
				  if(sy==dy&&sx==dx)
				  {
					
					  curve.setCurve(sx, sy, cx, cy, dx+20, dy);
					  ga.draw(curve);
					  //System.out.println("arrayterminal="+Arrayterminal[j-1]);
					  ga.drawString(Arrayterminal[j-1],tempcx , tempcy);
				  }	
				  else
				  {
				curve.setCurve(sx, sy, cx, cy, dx, dy);
				  ga.draw(curve);
				  ga.drawString(Arrayterminal[j-1],tempcx ,tempcy );
				  }			
				
			}
			
		}
		horizontal=40;
		vertical=40;
		
		  }
	private void putLine(int i, int j) {
		
		int searchno=0;
		//for(int k=0;k<noofstates;k++)
	//	{
			
			if(findpost(i,j))
			{
				System.out.println("i j Search no "+i+" "+j+" "+searchno);
				searchno=globlek;
			//	break;
			}
			else
				System.out.println("In else loop in putLine");
			
		//}
		System.out.println("i Search no "+i+" "+" "+searchno);
			drawAutomataLines(i,searchno);
		
	}
	private boolean findpost(int i,int j) {

		
		for(int k=0;k<noofstates;k++)
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
	private void drawAutomataLines(int i, int searchno) {
			
			sx=(int)circle[i].getX()+20;;
			sy=(int)circle[i].getY()+20;
			dx=(int)circle[searchno].getX()+20;
			dy=(int)circle[searchno].getY()+20;
			
			if(i/3==searchno/3)
			{
				cx=(sx+dx)/2;
				cy=sy-horizontal;
				horizontal+=30;
				
			}
			else
			{
				cx=sx-vertical;
				cy=(sy+dy)/2;
				vertical+=30;
				
			}
		}
}
		
				

	
		


