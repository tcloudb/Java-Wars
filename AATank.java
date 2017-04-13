/**
 * @(#)AATank.java
 *
 *
 * @author
 * @version 1.00 2013/5/21
 */


public class AATank extends Unit{

    public AATank(int t) {
    	setMaxHP(100);
		setHP(100);
		setAtk(35);
		setCanAttack(true);
		setEffectiveATK(35);
		setDef(0);
		setMov(5);
		setReady(true);
		setIsAir(false);
		setIsArmored(true);
		setIsDead(false);
		setCounterAttack(true);
		setTeam(t);
		setUnitType(4);
    }
    public void damage(Unit u) {
		if (u.getIsArmored()) {
			setEffectiveATK(getAtk() - 15);
		}
		if (u.getIsAir()) {
			setEffectiveATK(getAtk() + 25);
		}
		 super.damage(u);
	}


}


