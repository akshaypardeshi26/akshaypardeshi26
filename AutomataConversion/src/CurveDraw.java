import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class CurveDraw extends Frame {
  Stroke drawingStroke = new BasicStroke(1);
  QuadCurve2D curve = new QuadCurve2D.Double(30,50,20,200,100,100);

  public void paint(Graphics g) {
  Graphics2D ga = (Graphics2D)g;
  ga.setStroke(drawingStroke);
  ga.setPaint(Color.green);
  ga.draw(curve);

  }

  public static void main(String args[]) {
  Frame frame = new CurveDraw();
  frame.addWindowListener(new WindowAdapter(){
  public void windowClosing(WindowEvent we){
  System.exit(0);
  }
  });
  frame.setSize(200, 200);
  frame.setVisible(true);
  }
}