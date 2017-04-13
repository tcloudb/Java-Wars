
public class BattleHeli extends Unit {

	public BattleHeli(int t) {
		setMaxHP(75);
		setHP(75);
		setAtk(25);
		setEffectiveATK(25);
		setDef(0);
		setMov(6);
		setReady(true);
		setIsAir(true);
		setIsArmored(false);
		setIsDead(false);
		setCounterAttack(true);
		setTeam(t);
		setCanAttack(true);
		setUnitType(5);
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

}