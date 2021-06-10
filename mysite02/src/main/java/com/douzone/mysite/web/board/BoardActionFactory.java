package com.douzone.mysite.web.board;

import com.douzone.mvc.Action;
import com.douzone.mvc.ActionFactory;


public class BoardActionFactory extends ActionFactory {
	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("view".equals(actionName)) {
			action = new ViewAction();
		} else if("modify".equals(actionName)) {
			action = new ModifyAction();
		}else if("modifyform".equals(actionName)) {
			action = new ModifyFormAction();
		}else if("write".equals(actionName)) {
			action = new WriteAction();
		}else if("writeform".equals(actionName)){
			action = new WriteFormAction();
		}else if("delete".equals(actionName)){
			action = new DeleteAction();
		}else if("comment".equals(actionName)){
			action = new CommentAction();
		} else { // default Action
			action = new ListAction();
		}
		
		return action;
	}
}
