package s3722763.ui.menu.items;

import s3722763.ui.menu.actions.Action;
/*
 * Class: MenuItem
 * Description: This base class connects an Action with a key
 * 				so when the key is pressed, the action is called
 * 				to act
 * Author: Daniel Miskimmin	- 3722763
 */
public class MenuItem {
	private String name;
	private String key;
	private Action action;
	private int spacing;
	
	public MenuItem(String name, String key, int spacing, Action action) {
		this.name = name;
		this.key = key;
		this.spacing = spacing;
		this.action = action;
	}
	
	/*
	 * ALGORITHM
	 * BEGIN
	 * 		RETURN name
	 * END
	 */
	public String getName() {
		return name;
	}
	
	public String getKey() {
		return key;
	}
	
	public Action getAction() {
		return action;
	}
	
	public int getSpacing() {
		return spacing;
	}
}
