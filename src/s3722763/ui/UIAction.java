package s3722763.ui;

public abstract class UIAction {
	private String name;
	private String key;
	
	public UIAction(String name, String key) {
		this.name = name;
		this.key = key.toUpperCase();
	}
	
	public abstract void action();
	
	public String getName() {
		return name;
	}
	
	public String getKey() {
		return key;
	}
}
