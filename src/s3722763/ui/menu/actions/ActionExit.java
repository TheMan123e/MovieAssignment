package s3722763.ui.menu.actions;

import s3722763.hireitems.Item;

public class ActionExit extends Action {

	public ActionExit() {
		super("Exit");
	}

	@Override
	public ActionResult act(Item[] items) {
		return ActionResult.END_PROGRAM;
	}

}
