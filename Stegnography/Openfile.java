import java.io.*;
import java.lang.String;
class Openfile 
{
	FileInputStream fin;
	int ch;
	String message;
	Openfile(String d,File file1,File file)
	{
		try
		{
			message="";
			fin=new FileInputStream(d);
			while( (ch = fin.read()) != -1)
			message=message+(char)ch;
			System.out.println(message);
			new GetPixelColor("dummy",message,file);

		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		



	}
	public static void main(String[] args) 
	{
		//new Openfile("nik");
		System.out.println("Hello World!");
	}
}
