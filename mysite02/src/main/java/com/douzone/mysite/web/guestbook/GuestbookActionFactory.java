package com.douzone.mysite.web.guestbook;

import com.douzone.mvc.Action;
import com.douzone.mvc.ActionFactory;

public class GuestbookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if("deleteform".equals(actionName)) {
			action = new DeleteFormAction();
		}else if("add".equals(actionName)) {
			action = new AddAction();
		}else if("delete".equals(actionName)) {
			action = new DeleteAction();
		}else {
			action = new IndexAction();
		}
		
		
		return action;
	}

}