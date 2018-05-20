package s3722763.ui.menu.actions;

import s3722763.hireitems.Item;
import s3722763.util.exceptions.IdException;

/*
 * Class: Action
 * Description: Base class for menu actions, which act when a commanjd is entered
 * Author: Daniel Miskimmin	- 3722763
 */
public abstract class Action {
	private String name;
	protected Item[] tempRentalItems;
	
	public Action(String name) {
		this.name = name;
	}
	
	protected String reasonForFailure;
	
	public abstract ActionResult act(Item[] items) throws Exception;
	
	/*
	 * ALGORITHM
	 * BEGIN
	 * 		RETURN updated rental items list
	 * END
	 */
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
