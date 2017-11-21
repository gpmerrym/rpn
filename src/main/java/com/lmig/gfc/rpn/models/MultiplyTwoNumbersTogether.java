package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class MultiplyTwoNumbersTogether extends TwoNumberCalculation {

	public MultiplyTwoNumbersTogether(Stack<Double> stack) {
		super(stack);

	}

	@Override
	protected double doMath(double first, double second) {
		return first * second;
	}

}
