package com.lmig.gfc.rpn.models;

import java.util.Stack;

public interface Undoer {
	
	public void undo(Stack<Double> stack);

}
