import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class main {
	public static void main(String[] args) {
		okno window = new okno();
	}
}
class okno extends JFrame{
	public okno() {
		setBounds(0,0,800,600);
		setTitle("Mini Paint");
		add(new panel());
		setVisible(true);
	}
}
class panel extends JPanel{
	private Color[] masColor;
	private Image razer;
	private int tCol=0;
	private int mX,mY;
	private boolean flag=false;
	panel(){
		addMouseListener(new myMouse());
		addMouseMotionListener(new myMotionMouse());
		setFocusable(true);
		try {
			razer = ImageIO.read(new File("C:\\Users\\Пользователь\\Desktop\\paint\\imgg\\razer.png"));
		}
		catch(IOException ex) {System.out.println("Can't load \"razer\" image");}
	}
	public void paintComponent(Graphics gr) {
		gr.drawImage(razer, 650, 25, this);	
		masColor=new Color[7];
		Color col1=new Color(255,255,255);
		masColor[0]=new Color(55,100,255);
		masColor[1]=Color.GREEN;
		masColor[2]=Color.MAGENTA;
		masColor[3]=Color.RED;
		masColor[4]=Color.YELLOW;
		masColor[5]=Color.ORANGE;
		masColor[6]=Color.WHITE; 
		for(int i = 0; i < 7; i++) {
			gr.setColor(masColor[i]);
			gr.fillRect(i*100,0,100,50);
		 }
	 if(flag==true) {
		gr.setColor(masColor[tCol]);
		gr.fillRect(mX, mY, 3, 3);
	    if(tCol==6) {
		    gr.setColor(masColor[6]);
		    gr.fillRect(mX, mY, 100, 100);
	     }
	  }
  }

	private class myMouse implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent arg0) {}
		@Override
		public void mouseEntered(MouseEvent arg0) {}
		@Override
		public void mouseExited(MouseEvent arg0) {}
		@Override
		public void mousePressed(MouseEvent e) {
			int tX=e.getX();
			int tY=e.getY();
			int col=e.getClickCount();
			int btn=e.getButton();
			if(tX>0 && tX<700 && tY>0 && tY<50) {
				if(col==1) {
					if(btn==1) {tCol=tX/100;}
			 
				}
		 
			}
	 
		}
		public void mouseReleased(MouseEvent e) {
			flag=false;
		}
	}
	private class myMotionMouse implements MouseMotionListener{
	public void mouseDragged(MouseEvent e) {
		int tX=e.getX();
		int tY=e.getY();
		if(tY>50) {
			mX=tX;
			mY=tY;
			flag=true;
			repaint();
	 }
  }
	public void mouseMoved(MouseEvent e) {
		System.out.println(e.getX()+"   "+e.getY());
	int tX=e.getX();
	int tY=e.getY();
	if(tX>0 && tX<700 && tY>0 && tY<50) {
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	 }
	else {
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	  }
    }
  }
}