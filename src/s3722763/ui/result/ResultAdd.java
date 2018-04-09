package s3722763.ui.result;

import s3722763.hireitems.Item;

public class ResultAdd implements IResult{
	public Item item;
	
	public ResultAdd(Item item) {
		this.item = item;
	}
	
	@Override
	public String typeOfResult() {
		return "add";
	}
	
	public Item getItem() {
		return item;
	}

}
