import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.*;
import java.math.*;
import java.util.Random;
import java.util.Scanner;
public class GetPixelColor
{
	int m,n,m1,n1;
	Random randomGeneratorm = new Random(10);
	Random randomGeneratorn = new Random(4500);
	int []a=new int[400];
	int []b=new int[400];
 // public static void main(String args[]) throws IOException{
	 GetPixelColor(String img,String message,File file) 
	{
		 try
		{
			 System.out.println(file);
 //File file= new File("c:\\images\\m.jpg");
 
 BufferedImage image = ImageIO.read(file);
  // Getting pixel color by position x=100 and y=40 
  int clr=  image.getRGB(100,40);
  int  red   = (clr & 0x00ff0000) >> 16;
  int  green = (clr & 0x0000ff00) >> 8;
  int  blue  =  clr & 0x000000ff;
  /*System.out.println("Red Color value = "+ red);
  System.out.println("Green Color value = "+ green);
  System.out.println("Blue Color value = "+ blue);*/
	
  /*int i = 255;
        String hexString = Integer.toHexString( i );
        String octString = Integer.toOctalString( i );
        System.out.println("Hexa decimal conversion "+hexString);
        System.out.println("Octal coversion "+octString);
		
//BigInteger bi = new BigInteger(hexString, 16);
//System.out.println(bi);
int f=255;
int k=255;
int b=128;
String fi = Integer.toHexString( red );
String ki = Integer.toHexString( green );
String bi = Integer.toHexString( blue );

String sum=fi+ki+bi;
BigInteger bin5 = new BigInteger(sum,16);
System.out.println(bin);
System.out.println(k);
System.out.println(f);

clr=(0x00 + bin.intValue());

               image.setRGB(100,40,clr);*/
			   for(int lm=0;lm<150;lm++)
			{
				
					a[lm] = randomGeneratorm.nextInt(152);
				   b[lm] = randomGeneratorn.nextInt(152);
				   



			}
			m1=n1=1;
		// image = ImageIO.read(file);
			   System.out.println(" lenth="+message.length());
			   int lm=0; int ll=0;//lml=message.length()/250;
			   for(int f=0;f<message.length();f++)
			{

					m=a[lm];
					n=b[ll];
				
				
					
				
				if(lm>149)
				{
					lm=0;
					ll++;
				}
				lm++;
//				   System.out.print("m= "+m+"  n="+n);
  // System.out.println("ori");
	clr=  image.getRGB(m1,n1); 			  
	red   = (clr & 0x00ff0000) >> 16;
    green = (clr & 0x0000ff00) >> 8;
    blue  =  clr & 0x000000ff;
  //System.out.println(" original Red Color value = "+ red);
  //System.out.println(" Original Green Color value = "+ green);
  //System.out.println("original Blue Color value = "+ blue);
int t,s;
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
	   //System.out.println(" bit Red Color value = "+ redc);
 //System.out.println("bit Green Color value = "+ greenc);
  //System.out.println("bit Blue Color value = "+ bluec);

	
	char ch=message.charAt(f);
	int y=ch;
//	 System.out.print(" character : "+ch);
	 String y1 = Integer.toBinaryString(y);
	 if(y1.length()!=8)
	  {
			 t=y1.length();
			for(s=0;s<8-t;s++)
				y1="0"+y1;

	  }
	//  System.out.println("Blue Color value = "+ y1);
	 String resultr=redc.substring(0,5)+y1.substring(0,3);
	  // System.out.println("final  bit red Color value = "+ resultr);
		int bin1 = Integer.parseInt(resultr,2);
//		System.out.println(bin1);

	    String resultg=greenc.substring(0,5)+y1.substring(3,6);
	   //System.out.println("final bit green Color value = "+ resultg);
int bin2 = Integer.parseInt(resultg,2);
//		System.out.println(bin2);
	    String resultb=bluec.substring(0,6)+y1.substring(6,8);
	  // System.out.println("final bit blue Color value = "+ resultb);
int bin3 = Integer.parseInt(resultb,2);
//System.out.println(bin3);
int red_shift   = (bin1)<<16;
int    green_shift = (bin2) << 8;
 int   blue_shift  =  (bin3);

 int ans=red_shift+green_shift+blue_shift;
// System.out.print("Final value :"+ans+"      ");

		/*String fi = Integer.toString( bin1.intValue() );
String ki = Integer.toString( bin2.intValue() );
String bi = Integer.toString( bin3.intValue() );
System.out.println(fi);
System.out.println(ki);
System.out.println(bi);

String sum=fi+ki+bi;
*/
//BigInteger bin5 = new BigInteger(sum);

//clr=(0x00 + Integer.parseInt(sum));

               image.setRGB(m1,n1,ans);

			   //("here");
			   
	/*		   File file1=new File("ACP.bmp");
			BufferedImage image1 = ImageIO.read(file1);

			   clr=  image1.getRGB(m,n);
			   
   red   = (clr & 0x00ff0000) >> 16;
    green = (clr & 0x0000ff00) >> 8;
    blue  =  clr & 0x000000ff;
	
  System.out.println("Red Color value1 = "+ red);
  System.out.println("Green Color value1 = "+ green);
  System.out.println("Blue Color value1 = "+ blue);
		System.out.println("extract value :"+clr+"      ");
*/
	   //String mainresult=resultr.substring(5,8)+resultg.substring(5,8)+resultb.substring(6,8);
	   //System.out.println(mainresult);
	   //int jh=Integer.parseInt(mainresult,2);
		//			System.out.print("i="+(char)jh);

	//Scanner sc=new Scanner(System.in);
	//int ssn=sc.nextInt();

		m1++;
		if(m1==300)
		{
			m1=1;
			n1++;
		}


		}
		clr=(0x00000000);
			
               image.setRGB(m1,n1,clr);
			   System.out.println("last"+clr);

			   clr=  image.getRGB(m1,n1);
			    System.out.println("last"+clr);
  // red   = (clr & 0x00ff0000) >> 16;
   // green = (clr & 0x0000ff00) >> 8;
    //blue  =  clr & 0x000000ff;

 // System.out.println("Red Color value = "+ red);
  //System.out.println("Green Color value = "+ green);
  //System.out.println("Blue Color value = "+ blue);

ImageIO.write(image,"bmp",new File("c:\\learn\\ACP.bmp"));


		}
		catch(Exception e){System.out.println(e);}






  //}
		
  }
   /*public static void main(String args[])
	{

		new GetPixelColor("nik","jnj");
	}*/
  


}