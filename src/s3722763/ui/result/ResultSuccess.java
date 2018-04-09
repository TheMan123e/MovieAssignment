package s3722763.ui.result;

public class ResultSuccess implements IResult{
	private boolean isSuccess;
	
	public ResultSuccess(boolean success) {
		this.isSuccess = success;
	}
	
	public boolean isSuccess() {
		return isSuccess;
	}

	@Override
	public String typeOfResult() {
		return "success";
	}
}
