package s3722763.ui.menu.actions;

import s3722763.hireitems.Item;

/*
 * Class: ActionExit
 * Description: This class represents the user wanting to exit the program
 * 				This has no other purpose than to tell the loop which
 * 				calls this method that it should exit
 * Author: Daniel Miskimmin	- 3722763
 */
public class ActionExit extends Action {

	public ActionExit() {
		super("Exit");
	}

	/*
	 * ALGORITHM
	 * BEGIN
	 * 		RETURN End program
	 * END
	 */
	@Override
	public ActionResult act(Item[] items) {
		return ActionResult.END_PROGRAM;
	}

}
