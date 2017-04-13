import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class Sprite {
	public static final int GRIDWIDTH=24;
	private Image sprite;
	private Unit unit;
	public Sprite(){
	}
	public void draw(int xIndex, int yIndex, Graphics g, ImageObserver o){
		g.drawImage(sprite, xIndex*GRIDWIDTH, yIndex*GRIDWIDTH, GRIDWIDTH, GRIDWIDTH, o);
		if(unit!=null){
			unit.draw(g,xIndex,yIndex,o);
		}
	}
	public void drink(){
		//drink some sprite
	}
	public void moveUnit(){
		unit=null;
	}
	public void setBackgroundImage(Image i){
		sprite=i;
	}
	//change with different units!
	public void setUnit(int unit, int turn){
		switch(unit){
		case 1:
			this.unit=new Gunner(turn);
			break;
		case 2:
			this.unit=new Grenadier(turn);
			break;
		case 3:
			this.unit=new Tank(turn);
			break;
		case 4:
			this.unit=new AATank(turn);
			break;
		case 5:
			this.unit=new BattleHeli(turn);
			break;
		case 6:
			this.unit=new Bomber(turn);
			break;
		case 7:
			this.unit=new Fighter(turn);
			break;
		default:
			this.unit=new Unit(0, turn);
			break;
		}
	}
	public void moveUnit(Unit u){
		unit=u;
	}
	public void select(){
		unit.select();
	}
	public void deselect(){
		unit.deselect();
	}
	public boolean getOccupied(){
		if(unit!=null)
			return true;
		else
			return false;
	}
	public boolean getReady(){
		return unit.getReady();
	}
	public void move(){
		unit.move();
	}
	public void reset(){
		unit=null;
	}
	public int getUnitType(){
		return unit.getUnitType();
	}
	public Unit getUnit(){
		return unit;
	}
	public int getMov(){
		return unit.getMov();
	}
	public int getTeam(){
		return unit.getTeam();
	}
	public void setDead(){
		unit.setDead();
	}
	public void setCanAttack(boolean b){
		unit.setCanAttack(b);
	}
	public void setReady(){
		unit.setReady();
	}
}