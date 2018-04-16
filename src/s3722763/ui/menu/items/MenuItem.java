package s3722763.ui.menu.items;

public class MenuItem {
	private String name;
	private String key;
	//private Action action;
	private int spacing;
	
	public MenuItem(String name, String key, int spacing) {
		this.name = name;
		this.key = key;
		this.spacing = spacing;
	}
	
	public String getName() {
		return name;
	}
	
	public String getKey() {
		return key;
	}
	//public action getAction();
	
	public int getSpacing() {
		return spacing;
	}
}
