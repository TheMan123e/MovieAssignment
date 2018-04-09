package s3722763.ui;

import s3722763.ui.result.IResult;

public abstract class UIAction {
	private String name;
	private String key;
	
	public UIAction(String name, String key) {
		this.name = name;
		this.key = key.toUpperCase();
	}
	
	public abstract IResult action();
	
	public String getName() {
		return name;
	}
	
	public String getKey() {
		return key;
	}
}
