package s3722763.ui.menu.actions;

import s3722763.hireitems.Item;

public class ActionDisplay extends Action{
	public ActionDisplay() {
		super("Display");
	}

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
