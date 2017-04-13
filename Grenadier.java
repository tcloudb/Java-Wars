/**
 * @(#)Grenadier.java
 *
 *
 * @author
 * @version 1.00 2013/5/22
 */


public class Grenadier extends Unit {

    public Grenadier(int t) {
    	setMaxHP(50);
		setHP(50);
		setCanAttack(true);
		setAtk(15);
		setEffectiveATK(15);
		setDef(0);
		setMov(2);
		setReady(true);
		setIsAir(false);
		setIsArmored(false);
		setIsDead(false);
		setCounterAttack(true);
		setTeam(t);
		setUnitType(2);
    }
    public void damage(Unit u) {
		if (u.getIsArmored()) {
			setEffectiveATK(getAtk() + 35);
		}
		if (u.getIsAir()) {
			setEffectiveATK(getAtk());
		}
		 super.damage(u);
	}

}