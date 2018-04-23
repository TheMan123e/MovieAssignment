package s3722763.ui.menu.actions;

import s3722763.hireitems.Item;

public abstract class Action {
	private String name;
	protected Item[] tempRentalItems;
	
	public Action(String name) {
		this.name = name;
	}
	
	protected String reasonForFailure;
	
	public abstract ActionResult act(Item[] items);
	
	public Item[] getUpdatedList() {
		return tempRentalItems;
	}

	public String getReasonForFailure() {
		return reasonForFailure;
	}
	
	public String getName() {
		return name;
	}
}
