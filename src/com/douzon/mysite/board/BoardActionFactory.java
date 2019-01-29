package com.douzon.mysite.board;

import com.douzon.mvc.action.AbstractActionFactory;
import com.douzon.mvc.action.Action;

public class BoardActionFactory extends AbstractActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if("writeaction".equals(actionName)) {
			action = new WriteFormAction();
		}else if("write".equals(actionName)) {
			action = new writeAction();
		}else if("view".equals(actionName)) {
			action = new ViewAction();
		}else if("delete".equals(actionName)) {
			action = new DeleteAction();
		}else if("rewrite".equals(actionName)) {
			action = new RewriteFormAction();
		}else if("rewriteaction".equals(actionName)) {
			action = new RewriteAction();
		}else if("reply".equals(actionName)) {
			action = new ReplyFormAction();
		}else if("replyaction".equals(actionName)) {
			action = new ReplyAction();
		}else if("search".equals(actionName)) {
			action = new SearchFormAction();
		}
		else {
			action = new BoardAction();
		}
		return action;
	}

}
