package s3722763.ui.menu.actions;

public abstract class Action {
	protected String reasonForFailure;
	
	public abstract ActionResult act();

	public String getReasonForFailure() {
		return reasonForFailure;
	}
}
