/**
 * @(#)Fighter.java
 *
 *
 * @author
 * @version 1.00 2013/5/21
 */


public class Fighter extends Unit {

    public Fighter(int t) {
    	setMaxHP(75);
		setHP(75);
		setAtk(0);
		setEffectiveATK(0);
		setDef(0);
		setCanAttack(true);
		setMov(8);
		setReady(true);
		setIsAir(true);
		setIsArmored(false);
		setIsDead(false);
		setCounterAttack(true);
		setTeam(t);
		setUnitType(7);
    }
   	public void damage(Unit u) {
		if (u.getIsArmored()) {
			setEffectiveATK(getAtk());
		}
		if (u.getIsAir()) {
			setEffectiveATK(50);
		}
		 super.damage(u);
	}

}

