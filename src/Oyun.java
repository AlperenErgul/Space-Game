import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

class Ate�{
	
	private int x;
	private int y;
	
	public Ate�(int x, int y) {
		
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
	
}

public class Oyun extends JPanel implements KeyListener,ActionListener{

	Timer timer = new Timer(2,this);
	
	private int ge�en_s�re=0;
	private int harcanan_ate�=0;
	private BufferedImage image;
	private ArrayList<Ate�> ate�ler= new ArrayList<Ate�>();
	
	private int atesdirY=5;
	private int topX=0;
	private int topdirX=10;
	
	private int uzayGemisiX=0;
	
	private int dirUzayX=20;
	
	public boolean kontrolEt(){
		
		for(Ate� ate� : ate�ler) {
			
			if(new Rectangle(ate�.getX(),ate�.getY(),10,20).intersects(new Rectangle(topX,0,20,20))) {
				return true;
			}
			
		}
		return false;
		
	}
	
	public Oyun() {
		try {
			image = ImageIO.read(new FileInputStream(new File("resim.png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setBackground(Color.black);
		
		timer.start();
	}

	@Override
	public void repaint() {
		// TODO Auto-generated method stub
		super.repaint();
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		ge�en_s�re+=5;
		g.setColor(Color.red);
		
		g.fillOval(topX, 0,20, 20);
		
		g.drawImage(image, uzayGemisiX, 490,image.getWidth()/5,image.getHeight()/5, this);
		
		for(Ate� ate� : ate�ler) {
			if(ate�.getY()<0) {
				ate�ler.remove(ate�);
			}
		}
		
		g.setColor(Color.BLUE);
		
		for(Ate� ate� : ate�ler) {
			g.fillRect(ate�.getX(),ate�.getY(),10,20);
		}
		
		if(kontrolEt()) {
			timer.stop();
			
			String message = "Kazand�n�z\n"
					+"Harcanan Ate�:" + harcanan_ate�+"\n"
					+"Ge�en S�re :" + ge�en_s�re;
			
			JOptionPane.showMessageDialog(this, message); 	
			System.exit(0);
		}
			
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		for(Ate� ate� : ate�ler) {
			ate�.setY(ate�.getY()-atesdirY);
		}
		
		topX = topX +topdirX;
		
		if(topX>=750) {
			topdirX = -topdirX;
		}
		
		if(topX<=0) {
			topdirX = -topdirX;
		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
		
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
int c = e.getKeyCode();
		
		if(c== KeyEvent.VK_LEFT) {
		
			if(uzayGemisiX<=0) {
				uzayGemisiX=0;
			}
			else {
			uzayGemisiX-=dirUzayX;	
			}
			
		}
		else if(c==KeyEvent.VK_RIGHT) {
				
			if(uzayGemisiX>=720) {
				uzayGemisiX=720;
			}else {
				uzayGemisiX+=dirUzayX;
			}
			
			
		}	
		else if(c== KeyEvent.VK_CONTROL) {
			ate�ler.add(new Ate�(uzayGemisiX+15,490));
			
			harcanan_ate�++;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
}
