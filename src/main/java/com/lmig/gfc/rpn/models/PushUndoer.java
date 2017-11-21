package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class PushUndoer implements Undoer {

	//@Override
	public void undo(Stack<Double> stack) {
		stack.pop();
		
	}
	
	

}
