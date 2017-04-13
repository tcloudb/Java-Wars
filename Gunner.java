
public class Gunner extends Unit {

	public Gunner(int t) {
		setMaxHP(50);
		setHP(50);
		setAtk(25);
		setEffectiveATK(25);
		setDef(0);
		setMov(3);
		setReady(true);
		setIsAir(false);
		setIsArmored(false);
		setIsDead(false);
		setCounterAttack(true);
		setTeam(t);
		setUnitType(1);
		setCanAttack(true);
	}

	public void damage(Unit u) {
		if (u.getIsArmored()) {
			setEffectiveATK(getAtk() - 10);
		}
		if (u.getIsAir()) {
			setEffectiveATK(getAtk() - 10);
		}
		 super.damage(u);
	}

	public int getDamage(Unit u) {
		if (u.getIsArmored()) {
			setEffectiveATK(getAtk() - 10);
		}
		if (u.getIsAir()) {
			setEffectiveATK(getAtk() - 10);
		}
		return getEffectiveATK();
	}



}