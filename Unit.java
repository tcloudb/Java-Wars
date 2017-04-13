import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
//should be abstract
public class Unit{
	private int maxHP, hp, atk, effectiveATK, def, mov, unitType, team;
	private boolean ready, isAir, isArmored, isDead, canCounterAttack, selected, canAttack;
	public static final Image TESTUNIT=(new ImageIcon("testunitready.png").getImage());				//testunit = 0
	public static final Image TESTUNITSELECTED=(new ImageIcon("testunitselected.png").getImage());
	public static final Image GUNNER = (new ImageIcon("Gunner.png").getImage()),					//gunner = 1
							  GUNNERR = (new ImageIcon("GunnerR.png").getImage()),
							  GUNNER2 = (new ImageIcon("Gunner2.png").getImage()),
							  GUNNER2R = (new ImageIcon("Gunner2R.png").getImage()),
							  GUNNERG = (new ImageIcon("GunnerG.png").getImage()),
							  GRENADIER = (new ImageIcon("Grenadier.png").getImage()),				//grenadier = 2
							  GRENADIERR = (new ImageIcon("GrenadierR.png").getImage()),
							  GRENADIER2 = (new ImageIcon("Grenadier2.png").getImage()),
							  GRENADIER2R = (new ImageIcon("Grenadier2R.png").getImage()),
							  GRENADIERG = (new ImageIcon("GrenadierG.png").getImage()),
							  TANK = (new ImageIcon("Tank.png").getImage()),						//tank = 3
							  TANKR = (new ImageIcon("TankR.png").getImage()),
							  TANK2 = (new ImageIcon("Tank2.png").getImage()),
							  TANK2R = (new ImageIcon("Tank2R.png").getImage()),
							  TANKG = (new ImageIcon("TankG.png").getImage()),
							  AATANK = (new ImageIcon("AATank.png").getImage()),					//aatank = 4
							  AATANKR = (new ImageIcon("AATankR.png").getImage()),
							  AATANK2 = (new ImageIcon("AATank2.png").getImage()),
							  AATANK2R = (new ImageIcon("AATank2R.png").getImage()),
							  AATANKG = (new ImageIcon("AATankG.png").getImage()),
							  BATTLEHELI = (new ImageIcon("Heli.png").getImage()),						//bheli = 5
							  BATTLEHELIR = (new ImageIcon("HeliR.png").getImage()),
							  BATTLEHELI2 = (new ImageIcon("Heli2.png").getImage()),
							  BATTLEHELI2R = (new ImageIcon("Heli2R.png").getImage()),
							  BATTLEHELIG = (new ImageIcon("HeliG.png").getImage()),
							  BOMBER = (new ImageIcon("Bomber.png").getImage()),					//bomber = 6
							  BOMBERR = (new ImageIcon("BomberR.png").getImage()),
							  BOMBER2 = (new ImageIcon("Bomber2.png").getImage()),
							  BOMBER2R = (new ImageIcon("Bomber2R.png").getImage()),
							  BOMBERG = (new ImageIcon("BomberG.png").getImage()),
							  FIGHTER = (new ImageIcon("Fighter.png").getImage()),					//fighter = 7
							  FIGHTERR = (new ImageIcon("FighterR.png").getImage()),
							  FIGHTER2 = (new ImageIcon("Fighter2.png").getImage()),
							  FIGHTER2R = (new ImageIcon("Fighter2R.png").getImage()),
							  FIGHTERG = (new ImageIcon("FighterG.png").getImage());
							  
 // int health, int a, int d, int m, boolean action, boolean air, boolean armor, boolean dead, boolean counter
	public Unit() {
		maxHP = 1;
		hp = 1;
		atk = 0;
		def = 0;
		mov = 1;
		ready = true;
		canAttack=true;
		isAir = false;
		isArmored = false;
		isDead = false;
		canCounterAttack = true;
		team=0;
	}
	public Unit(int type, int team){
		this();
		this.team=team;
		unitType=type;
	}
	public void setMaxHP(int i) {
		maxHP = i;
	}
	public void setHP(int i) {
		hp = i;
	}
	public void setAtk(int i) {
		atk = i;
	}
	public void setEffectiveATK(int i) {
		effectiveATK = i;
	}
	public void setDef(int i) {
		def = i;
	}
	public void setMov(int i) {
		mov = i;
	}
	public void setCanAttack(boolean i){
		canAttack=i;
	}
	public void setReady(boolean b) {
		ready = b;
	}
	public void setIsAir(boolean b) {
		isAir = b;
	}
	public void setIsArmored(boolean b) {
		isArmored = b;
	}
	public void setIsDead(boolean b) {
		isDead = b;
	}
	public void setCounterAttack(boolean b) {
		canCounterAttack = b;
	}
	public int getMaxHP() {
		return maxHP;
	}
	public int getHP() {
		return hp;
	}
	public int getAtk() {
		return atk;
	}
	public int getEffectiveATK() {
		return effectiveATK;
	}
	public int getDef() {
		return def;
	}
	public void setTeam(int team){
		this.team=team;
	}
	public int getMov() {
		return mov;
	}
	public boolean getReady() {
		return ready;
	}
	public boolean getIsAir() {
		return isAir;
	}
	public boolean getIsArmored() {
		return isArmored;
	}
	public boolean getIsDead() {
		return isDead;
	}
	public boolean getCounterAttack() {
		return canCounterAttack;
	}
	public void damage(Unit u) {
		int d = this.effectiveATK - u.def;
		u.hp -= d;
	}
	public void select(){
		selected=true;
	}
	public void deselect(){
		selected=false;
	}
	public void move() {
		ready=false;
	}
	public void attack() {
	}
	public void hold() {
		ready = false;
	}
	public boolean getCanAttack(){
		return canAttack;
	}
	public void setUnitType(int type){
		unitType=type;
	}
	public int getUnitType(){
		return unitType;
	}
	public void setReady(){
		ready=true;
	}
	public int getTeam(){
		return team;
	}
	public void draw(Graphics g, int xIndex, int yIndex, ImageObserver o){
		switch(team){
		case 0:
			switch(unitType){
			case 1:
				if(!ready&&!canAttack)
					g.drawImage(GUNNERG, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				else if(selected)
					g.drawImage(GUNNERR, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				else
					g.drawImage(GUNNER, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				break;
			case 2:
				if(!ready&&!canAttack)
					g.drawImage(GRENADIERG, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				else if(selected)
					g.drawImage(GRENADIERR, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				else
					g.drawImage(GRENADIER, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				break;
			case 3:
				if(!ready&&!canAttack)
					g.drawImage(TANKG, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				else if(selected)
					g.drawImage(TANKR, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				else
					g.drawImage(TANK, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				break;
			case 4:
				if(!ready&&!canAttack)
					g.drawImage(AATANKG, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				else if(selected)
					g.drawImage(AATANKR, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				else
					g.drawImage(AATANK, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				break;
			case 5:
				if(!ready&&!canAttack)
					g.drawImage(BATTLEHELIG, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				else if(selected)
					g.drawImage(BATTLEHELIR, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				else
					g.drawImage(BATTLEHELI, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				break;
			case 6:
				if(!ready&&!canAttack)
					g.drawImage(BOMBERG, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				else if(selected)
					g.drawImage(BOMBERR, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				else
					g.drawImage(BOMBER, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				break;
			case 7:
				if(!ready&&!canAttack)
					g.drawImage(FIGHTERG, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				else if(selected)
					g.drawImage(FIGHTERR, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				else
					g.drawImage(FIGHTER, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				break;
			}
			break;
		default:
			switch(unitType){
			case 1:
				if(!ready&&!canAttack)
					g.drawImage(GUNNERG, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				else if(selected)
					g.drawImage(GUNNER2R, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				else
					g.drawImage(GUNNER2, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				break;
			case 2:
				if(!ready&&!canAttack)
					g.drawImage(GRENADIERG, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				else if(selected)
					g.drawImage(GRENADIER2R, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				else
					g.drawImage(GRENADIER2, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				break;
			case 3:
				if(!ready&&!canAttack)
					g.drawImage(TANKG, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				else if(selected)
					g.drawImage(TANK2R, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				else
					g.drawImage(TANK2, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				break;
			case 4:
				if(!ready&&!canAttack)
					g.drawImage(AATANKG, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				else if(selected)
					g.drawImage(AATANK2R, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				else
					g.drawImage(AATANK2, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				break;
			case 5:
				if(!ready&&!canAttack)
					g.drawImage(BATTLEHELIG, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				else if(selected)
					g.drawImage(BATTLEHELI2R, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				else
					g.drawImage(BATTLEHELI2, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				break;
			case 6:
				if(!ready&&!canAttack)
					g.drawImage(BOMBERG, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				else if(selected)
					g.drawImage(BOMBER2R, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				else
					g.drawImage(BOMBER2, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				break;
			case 7:
				if(!ready&&!canAttack)
					g.drawImage(FIGHTERG, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				else if(selected)
					g.drawImage(FIGHTER2R, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				else
					g.drawImage(FIGHTER2, xIndex*Sprite.GRIDWIDTH, yIndex*Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, Sprite.GRIDWIDTH, o);
				break;
			}
			break;
		}
		g.setFont(new Font("SANS_SERIF",Font.BOLD,9));
		g.drawString(""+hp, xIndex*Sprite.GRIDWIDTH,yIndex*Sprite.GRIDWIDTH+Sprite.GRIDWIDTH);
	}
	public void setDead(){
		isDead=true;
	}
}