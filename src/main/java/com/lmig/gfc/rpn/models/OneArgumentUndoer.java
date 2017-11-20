package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class OneArgumentUndoer {

	private double value;

	public OneArgumentUndoer(double value) {
		this.value = value;
	}
	
	public void undo(Stack<Double> stack) {
		stack.pop();
		parentUndo(stack);
		
	}
	protected void parentUndo(Stack<Double> stack) {
		stack.push(value);
	}

}
