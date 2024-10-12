package dtcc.itn262.items.weapons;

public interface IWeapon {
	String getWeapon();
	int getDamage();
	String getType();
	String getDescription();
	void setWeapon(String weapon);
	void setDamage(int damage);
	void setType(String type);
	void setDescription(String description);
	String toString();
}
