package com.douzon.mysite.action.main;

import com.douzon.mvc.action.AbstractActionFactory;
import com.douzon.mvc.action.Action;

public class MainActionFactory extends AbstractActionFactory {

	@Override
	public Action getAction(String actionName) {
	
		return new IndexAction();
	}

}
