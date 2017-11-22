package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class AbsoluterOfOneNumber implements Godoer, Undoer {
	
	private Stack <Double> stack;
	private OneArgumentUndoer undoer;
	
	public AbsoluterOfOneNumber(Stack<Double> stack) {
		this.stack = stack;
	}
	
	public void goDoIt() {
		double value = stack.pop();
		double result = Math.abs(value);
		stack.push(result);
		undoer = new OneArgumentUndoer(value);
	}

	@Override
	public void undo(Stack<Double> stack) {
		undoer.undo(stack);
		
		
	}
	
}
