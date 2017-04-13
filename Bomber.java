/**
 * @(#)Bomber.java
 *
 *
 * @author
 * @version 1.00 2013/5/21
 */


public class Bomber extends Unit {

    public Bomber(int t) {

    	setMaxHP(75);
		setHP(75);
		setAtk(40);
		setEffectiveATK(40);
		setCanAttack(true);
		setDef(0);
		setMov(7);
		setReady(true);
		setIsAir(true);
		setIsArmored(false);
		setIsDead(false);
		setCounterAttack(true);
		setTeam(t);
		setUnitType(6);
    }
   	public void damage(Unit u) {
		if (u.getIsArmored()) {
			setEffectiveATK(getAtk() + 20);
		}
		if (u.getIsAir()) {
			setEffectiveATK(0);
		}
		 super.damage(u);
	}

}

