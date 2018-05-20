package s3722763.ui.menu.actions;

import s3722763.hireitems.Item;
/*
 * Class: ActionDisplay
 * Description: This class represents the action which displays the
 * 				info of each item in the rentable items array and their
 * 				respective hiring records
 * Author: Daniel Miskimmin	- 3722763
 */
public class ActionDisplay extends Action{
	public ActionDisplay() {
		super("Display");
	}

	/*
	 * ALGORITHM
	 * BEGIN
	 * 		FOREACH item in the rental item array
	 * 			GET details of item including hiring record
	 * 			DISPLAY details of item including hiring record
	 * END
	 */
	@Override
	public ActionResult act(Item[] items) {
		for(Item i : items) {
			if (i != null) {
				System.out.println(i.getDetails());
			}
		}
		
		return ActionResult.SUCCESS;
	}

}
