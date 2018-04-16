package s3722763.ui.menu.items;

import s3722763.ui.menu.actions.Action;

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
