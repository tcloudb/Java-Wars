import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Grid extends JPanel implements MouseListener,KeyListener{
	private Sprite[][] grid;
	private boolean selected;
	private int selectedX, selectedY;
	private int victory=-1;
	private int turn=0;
	private Image grass = (new ImageIcon("grass.png")).getImage();
	private Image range = (new ImageIcon("range.png")).getImage();
	public Grid(){
		grid = new Sprite[30][20];
		for(int x=0; x<grid.length; x++){
			for(int y=0; y<grid[x].length; y++){
				grid[x][y]=new Sprite();
			}
		}
		for(Sprite[] array:grid){
			for(Sprite sprite:array){
				sprite.drink();
				sprite.setBackgroundImage((new ImageIcon("grass.png")).getImage());
			}
		}
		setBoard();
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(int x=0;x<grid.length;x++){
			for(int y=0;y<grid[x].length;y++){
				grid[x][y].draw(x,y,g,this);
			}
		}
		g.drawString("Turn "+(turn+1),0,grid[0].length*Sprite.GRIDWIDTH-2);
		if(victory!=-1){
			g.setFont(new Font("SANS_SERIF",Font.BOLD, 32));
			g.drawString("Player "+victory+" wins!",Sprite.GRIDWIDTH*grid.length/2-100,Sprite.GRIDWIDTH*grid[0].length/2-14);
		}
	}
	public int getMapWidth(){
		return grid.length;
	}
	public int getMapHeight(){
		return grid[0].length;
	}
	public void mouseClicked(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
		int x = e.getX()/Sprite.GRIDWIDTH;
		int y = e.getY()/Sprite.GRIDWIDTH;
		if(e.getButton()==MouseEvent.BUTTON2){
//			if(!grid[x][y].getOccupied())
//				grid[x][y].setUnit(1, turn%2);
		}
		else if(e.getButton()==MouseEvent.BUTTON1){
			if(grid[x][y].getOccupied()){
				if(!selected&&grid[x][y].getTeam()==turn%2){
					selected=true;
					selectedX=x;
					selectedY=y;
					if(grid[selectedX][selectedY].getUnit().getReady())
						displayRange(selectedX, selectedY, grid[selectedX][selectedY].getUnit());
				}
				else if(selected&&grid[x][y].getTeam()==grid[selectedX][selectedY].getTeam()){
					deselect();
					grid[x][y].select();
					selected=true;
					selectedX=x;
					selectedY=y;
					if(grid[selectedX][selectedY].getUnit().getReady())
						displayRange(selectedX, selectedY, grid[selectedX][selectedY].getUnit());
				}
				else{
					if(Math.abs(selectedX-x)<=1&&Math.abs(selectedY-y)<=1&&Math.abs(selectedX-x)!=Math.abs(selectedY-y)){
						if(grid[selectedX][selectedY].getUnit().getCanAttack()){
							battle(grid[selectedX][selectedY].getUnit(), grid[x][y].getUnit());
							grid[selectedX][selectedY].move();
							grid[selectedX][selectedY].setCanAttack(false);
							backgroundReset();
							if(grid[selectedX][selectedY].getUnit().getIsDead())
								grid[selectedX][selectedY].reset();
							if(grid[x][y].getUnit().getIsDead())
								grid[x][y].reset();
							deselect();
							int v=calcVictory();
							if(v!=-1)
								victory=v;
						}
					}
				}
			}
			else{
				if(selected){
					if(Math.abs(selectedX-x)+Math.abs(selectedY-y)<=grid[selectedX][selectedY].getMov()){
					move(selectedX,selectedY,x,y);
					backgroundReset();
					}
				}
			}
		}else if(e.getButton()==MouseEvent.BUTTON3){
			deselect();
		}
		repaint();
	}
	private void backgroundReset(){
		for(Sprite[] array:grid){
			for(Sprite sprite:array){
				sprite.setBackgroundImage(grass);
			}
		}
	}
	private void deselect(){
		for(Sprite[] array:grid){
			for(Sprite sprite:array){
				sprite.setBackgroundImage(grass);
				if(sprite.getUnit()!=null)
					sprite.deselect();
			}
			
		}
		selected=false;
		selectedX = -1;
		selectedY = -1;
	}
	private void displayRange(int xIndex, int yIndex, Unit u){
		int r = u.getMov();
		for(int x = xIndex-r; x<=xIndex+r; x++){
			for(int y = yIndex-r; y<=yIndex+r; y++){
				if(x<0||x>grid.length-1||y<0||y>grid[0].length-1){
				}else
					if(Math.abs(xIndex-x)+Math.abs(yIndex-y)<=r){
						grid[x][y].setBackgroundImage(range);
					}
			}
		}
	}
	public void move(int initX, int initY, int finX, int finY){
		if(grid[initX][initY].getReady()){
			grid[finX][finY].moveUnit(grid[initX][initY].getUnit());
			grid[finX][finY].move();
			selectedX=finX;
			selectedY=finY;
			grid[finX][finY].select();
			grid[initX][initY].reset();
		}
	}
	public void mouseReleased(MouseEvent e) {
	}
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
			deselect();
		}else if(e.getKeyCode()==KeyEvent.VK_Q){
			endTurn();
		}
		repaint();
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
	}
	public void battle(Unit a, Unit b) {
		a.damage(b);
		calcDeath(b);
		if (!b.getIsDead()) {
		b.damage(a);
		calcDeath(a);
		}
	}
	private void calcDeath(Unit a) {
		if (a.getHP() <= 0) {
			a.setIsDead(true);
		}
	}
	public int calcVictory() {
		boolean team1=false;
		boolean team2=false;
		for(Sprite[] array:grid){
			for(Sprite sprite:array){
				if(sprite.getUnit()!=null)
					team1=team1||sprite.getUnit().getTeam()==0;
			}
		}
		for(Sprite[] array:grid){
			for(Sprite sprite:array){
				if(sprite.getUnit()!=null)
					team2=team2||sprite.getUnit().getTeam()==1;
			}
		}
		if(!team1)
			return 1;
		else if(!team2)
			return 2;
		else
			return -1;
	}
	private void endTurn(){
		deselect();
		for(Sprite[] array:grid){
			for(Sprite sprite:array){
				if(sprite.getUnit()!=null){
					sprite.setReady();
					sprite.setCanAttack(true);
				}
			}
		}
		turn++;
	}
	public void drawVictory(int v){	
	}
	private void setBoard(){
		int gunners = 3;
		int gunnersR = 3;
		int grenadiers = 3;
		int grenadiersR = 3;
		int tanks = 3;
		int tanksR = 3;
		int aatanks = 3;
		int aatanksR = 3;
		int bcopters = 3;
		int bcoptersR = 3;
		int bombers = 3;
		int bombersR = 3;
		int fighters = 3;
		int fightersR = 3;
		for(int g = 0; g < gunners; g++){
			int x=(int)(Math.random()*grid.length);
			int y=(int)(Math.random()*grid[0].length);
			if(!grid[x][y].getOccupied())
				grid[x][y].setUnit(1, 0);
			else
				g--;
		}
		for(int g = 0; g < gunnersR; g++){
			int x=(int)(Math.random()*grid.length);
			int y=(int)(Math.random()*grid[0].length);
			if(!grid[x][y].getOccupied())
				grid[x][y].setUnit(1, 1);
			else
				g--;
		}
		for(int g = 0; g < grenadiers; g++){
			int x=(int)(Math.random()*grid.length);
			int y=(int)(Math.random()*grid[0].length);
			if(!grid[x][y].getOccupied())
				grid[x][y].setUnit(2, 0);
			else
				g--;
		}
		for(int g = 0; g < grenadiersR; g++){
			int x=(int)(Math.random()*grid.length);
			int y=(int)(Math.random()*grid[0].length);
			if(!grid[x][y].getOccupied())
				grid[x][y].setUnit(2, 1);
			else
				g--;
		}
		for(int g = 0; g < tanks; g++){
			int x=(int)(Math.random()*grid.length);
			int y=(int)(Math.random()*grid[0].length);
			if(!grid[x][y].getOccupied())
				grid[x][y].setUnit(3, 0);
			else
				g--;
		}
		for(int g = 0; g < tanksR; g++){
			int x=(int)(Math.random()*grid.length);
			int y=(int)(Math.random()*grid[0].length);
			if(!grid[x][y].getOccupied())
				grid[x][y].setUnit(3, 1);
			else
				g--;
		}
		for(int g = 0; g < aatanks; g++){
			int x=(int)(Math.random()*grid.length);
			int y=(int)(Math.random()*grid[0].length);
			if(!grid[x][y].getOccupied())
				grid[x][y].setUnit(4, 0);
			else
				g--;
		}
		for(int g = 0; g < aatanksR; g++){
			int x=(int)(Math.random()*grid.length);
			int y=(int)(Math.random()*grid[0].length);
			if(!grid[x][y].getOccupied())
				grid[x][y].setUnit(4, 1);
			else
				g--;
		}
		for(int g = 0; g < bcopters; g++){
			int x=(int)(Math.random()*grid.length);
			int y=(int)(Math.random()*grid[0].length);
			if(!grid[x][y].getOccupied())
				grid[x][y].setUnit(5, 0);
			else
				g--;
		}
		for(int g = 0; g < bcoptersR; g++){
			int x=(int)(Math.random()*grid.length);
			int y=(int)(Math.random()*grid[0].length);
			if(!grid[x][y].getOccupied())
				grid[x][y].setUnit(5, 1);
			else
				g--;
		}
		for(int g = 0; g < bombers; g++){
			int x=(int)(Math.random()*grid.length);
			int y=(int)(Math.random()*grid[0].length);
			if(!grid[x][y].getOccupied())
				grid[x][y].setUnit(6, 0);
			else
				g--;
		}
		for(int g = 0; g < bombersR; g++){
			int x=(int)(Math.random()*grid.length);
			int y=(int)(Math.random()*grid[0].length);
			if(!grid[x][y].getOccupied())
				grid[x][y].setUnit(6, 1);
			else
				g--;
		}
		for(int g = 0; g < fighters; g++){
			int x=(int)(Math.random()*grid.length);
			int y=(int)(Math.random()*grid[0].length);
			if(!grid[x][y].getOccupied())
				grid[x][y].setUnit(7, 0);
			else
				g--;
		}
		for(int g = 0; g < fightersR; g++){
			int x=(int)(Math.random()*grid.length);
			int y=(int)(Math.random()*grid[0].length);
			if(!grid[x][y].getOccupied())
				grid[x][y].setUnit(7, 1);
			else
				g--;
		}
	}
}