package com.douzon.mysite.guesbook;


import com.douzon.mvc.action.AbstractActionFactory;
import com.douzon.mvc.action.Action;

public class GuestbookActionFactory extends AbstractActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if("add".equals(actionName)) {
			action = new AddFormAction();
		}else if("deleteform".equals(actionName)) {
			action = new DeleteFormAction();
		}else if("delete".equals(actionName)) {
			action = new DeleteAction();
		}
		else {
			action = new GuestbookAction();
		}
		return action;
	}

}
