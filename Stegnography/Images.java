import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.math.*;
import java.util.Random;
import java.util.Scanner;
class MyCanvas extends Canvas
{
	Image img;
	public MyCanvas(Image img)
	{
		this.img=img;

	}
	public void paint(Graphics g)
	{
		if(img!=null)
			g.drawImage(img,0,0,500,300,this);



	}
	public void setImage(Image img)
	{
		this.img=img;



	}
}  
public class Images implements ActionListener
{
	Frame fr=new Frame("Frame");
	Label Label1=new Label("Label1");
	Button Button1=new Button("Upload");
	Image Image1;
	MyCanvas Canvas1;
	Button hide=new Button("hide");
	FileDialog fd=new FileDialog(fr,"Open",FileDialog.LOAD);
	FileDialog fs=new FileDialog(fr,"Save",FileDialog.SAVE);
	String d;
	Button extract=new Button("extract");
	File file,file1;
	Button open=new Button("Open");
	TextArea tx=new TextArea(30,100);
	Button save=new Button("save");
	void initialize()
	{
		fr.setSize(500,300);
		fr.setLayout(new FlowLayout());
		fr.add(Label1);
		fr.add(Button1);
		fr.add(hide);
		fr.add(extract);
		fr.add(open);
		hide.addActionListener(this);
		Button1.addActionListener(this);
		save.addActionListener(this);
		extract.addActionListener(this);
		open.addActionListener(this);
		Canvas1=new MyCanvas(null);
		Canvas1.setSize(0,0);
		fr.add(Canvas1);
		//fr.add(tx);
		fr.setVisible(true);


	}
	void imageload()
	{
		fd.show();
		if(fd.getFile()==null)
					Label1.setText("no file");
		else
		{
			d=(fd.getDirectory()+fd.getFile());
			System.out.println("image  "+d);
			Toolkit toolkit=Toolkit.getDefaultToolkit();
			Image1=toolkit.getImage(d);
			//Canvas1.setImage(Image1);
			//Canvas1.repaint();
			file=new File(fd.getFile());
			System.out.println(file);


		}
		
	}
	void fileload()
	{
		fd.show();
		if(fd.getFile()==null)
					Label1.setText("no file");
		else
		{
			d=(fd.getDirectory()+fd.getFile());
			System.out.println("file   "+d);
			file1=new File(fd.getFile());

			new Openfile(d,file1,file);
			

		}
		
	}
	void extractdata()
	{
		String st="";

		int m=1; int n=1;
		int m1=1,n1=1;
		int s,t;


Random randomGeneratorm = new Random(10);
	Random randomGeneratorn = new Random(4500);
	int []a=new int[400];
	int []b=new int[400];

	for(int lm=0;lm<150;lm++)
			{	
					a[lm] = randomGeneratorm.nextInt(152);
				   b[lm] = randomGeneratorn.nextInt(152);
				   


			}
	

		try
		{
			
		int i=0;
		File file1=new File("c:\\learn\\ACP.bmp");
		
			 BufferedImage image1 = ImageIO.read(file1);
		int lm=0; int ll=0;
		while(true)
		{

//			System.out.print("a= "+a[lm]+"  b= "+b[ll]);
			m=a[lm];
					n=b[ll];
				
				
					
				
				if(lm==149)
			{
					lm=0;
					ll++;
			}
			lm++;
			int clr=  image1.getRGB(m1,n1);
//			System.out.println("ans1 :"+clr);
			int  red   = (clr & 0x00ff0000) >> 16;
			int  green = (clr & 0x0000ff00) >> 8;
			int  blue  =  clr & 0x000000ff;
			

			if(red==0 && green==0 && blue==0)
			{
				System.out.println("Satisfied");
				break;

			}
			else
			{
				
				String redc = Integer.toBinaryString(red);
				if(redc.length()!=8)
				{
					t=redc.length();
					for( s=0;s<8-t;s++)
					redc="0"+redc;

				}
				String greenc = Integer.toBinaryString(green);
				if(greenc.length()!=8)
				{
					 t=greenc.length();
					for( s=0;s<8-t;s++)
					greenc="0"+greenc;

				}
				String bluec = Integer.toBinaryString(blue);
				if(bluec.length()!=8)
				{
					t=bluec.length();
					for( s=0;s<8-t;s++)
					bluec="0"+bluec;

				}
//				System.out.println("Red Color value = "+ redc);
  //System.out.println("Green Color value = "+ greenc);
  //System.out.println("Blue Color value = "+ bluec);

				String mainresult=redc.substring(5,8)+greenc.substring(5,8)+bluec.substring(6,8);
	//			System.out.println(mainresult);
			//	BigInteger bin5 = new BigInteger(mainresult,2);
		//		int y=(int)bin5;			
					//i=bin5.intValue();
					i=Integer.parseInt(mainresult,2);
					System.out.print((char)i);
				st=st+(char)i;
			//	System.out.print((char)bin5);
				//System.out.print("m="+m);

				
	//			 System.out.println("Red Color value = "+ red);
  //System.out.println("Green Color value = "+ green);
  //System.out.println("Blue Color value = "+ blue);
		m1++;
		if(m1==300)
		{
			m1=1;
			n1++;
		}

//  Scanner sc=new Scanner(System.in);
//		int ssn=sc.nextInt();
				

			}

		}
		fr.add(tx);
		tx.setText(st);
		fr.add(save);
		fr.setSize(1000,1000);
		
		
		//fr.repaint();
		}
		catch(Exception e){}

	}
	public void save()
	{
		
		try
		{


			String sv;
			fs.show();
			if(fs.getFile()==null)
				Label1.setText("no file");
			else
			{
				sv=(fs.getDirectory()+fs.getFile());
			
				FileOutputStream fos = new FileOutputStream(sv);
				String s=tx.getText();
				for (int i=0; i<s.length();++i){
				fos.write((byte)s.charAt(i)); }


			}
			
		}
		catch (Exception e)
		{
		}
		


	}
	public void actionPerformed(ActionEvent e)
	{

		if((Button)e.getSource()==Button1)
		{

			imageload();
			 Picture pic = new Picture(d);
			 pic.show();
	
		}
		if((Button)e.getSource()==open)
		{


			fileload();


		}
		if((Button)e.getSource()==extract)
		{

//			imageload();
			extractdata();

	
		}
		if((Button)e.getSource()==hide)
		{

			new Hidedlg(fr,"Hide",false,d,file);

		}
		if((Button)e.getSource()==save)
		{

			save();

		}


	}
	public static void main(String args[])
	{
		Images i=new Images();
		i.initialize();

	}

	




}
