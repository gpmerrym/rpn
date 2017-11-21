package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class DivideTwoNumbersTogether extends TwoNumberCalculation {

	public DivideTwoNumbersTogether(Stack<Double> stack) {
		super(stack);
	}

	@Override
	protected double doMath(double first, double second) {
		return second / first;
	}

}
