package com.lmig.gfc.rpn.models;

import java.util.Stack;

public abstract class TwoNumberCalculation implements Undoer {
	// removes the ability for someone to call "new"

	private Stack<Double> stack;
	private Undoer undoer;

	public TwoNumberCalculation(Stack<Double> stack) {
		this.stack = stack;

	}

	public void goDoIt() {
		double first = stack.pop();
		double second = stack.pop();
		double result = doMath(first, second);
		stack.push(result);
		undoer = new TwoArgumentUndoer(first, second);

	}

	// dummy method
	// parent can see the child methods..
	protected abstract double doMath(double first, double second);
	// can call abstract on a method
	// child class has to implement this

	@Override
	public void undo(Stack<Double> stack) {
		undoer.undo(stack);

	}

}