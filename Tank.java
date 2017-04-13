/**
 * @(#)Tank.java
 *
 *
 * @author
 * @version 1.00 2013/5/21
 */


public class Tank extends Unit{

    public Tank(int t) {
    	setMaxHP(100);
		setHP(100);
		setAtk(25);
		setEffectiveATK(25);
		setDef(0);
		setMov(5);
		setReady(true);
		setIsAir(false);
		setIsArmored(true);
		setIsDead(false);
		setCounterAttack(true);
		setTeam(t);
		setUnitType(3);
		setCanAttack(true);
    }
    public void damage(Unit u) {
		if (u.getIsArmored()) {
			setEffectiveATK(getAtk() + 15);
		}
		if (u.getIsAir()) {
			setEffectiveATK(0);
		}
		 super.damage(u);
	}

}

