package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class TwoArgumentUndoer extends OneArgumentUndoer {
	
		//private double first;
		private double second;
	
		public TwoArgumentUndoer(double first, double second){
			super(first);
			//this.first = first;
			this.second = second;			
		}
		
		public void undo(Stack<Double> stack){
			stack.pop();
			parentUndo(stack);
			
		}
		
		protected void parentUndo(Stack<Double> stack) {
			stack.push(second);
			super.parentUndo(stack);
		}

}
