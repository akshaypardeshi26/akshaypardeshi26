import java.awt.*;
import java.awt.event.*;
import java.io.*;
class Hidedlg extends Dialog implements ActionListener
{
	
		TextField tf;
		Button ok;
		String img;
		File g;
	Hidedlg(Frame s,String name,boolean f,String image,File file)
	{
		super(s,name,f);
		g=file;
		tf=new TextField(20);
		ok=new Button("OK");
		setLayout(new FlowLayout());
		setVisible(true);
		setSize(200,200);
		add(tf);
		add(ok);
		ok.addActionListener(this);



	}
	public void actionPerformed(ActionEvent e)
	{

		if((Button)e.getSource()==ok)
		{

			new GetPixelColor(img,tf.getText(),g);

		}


	}
	/*public static void main(String[] args) 
	{
		System.out.println("Hello World!");
	}*/

}
