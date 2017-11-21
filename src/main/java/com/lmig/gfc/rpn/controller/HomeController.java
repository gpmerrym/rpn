package com.lmig.gfc.rpn.controller;

import java.util.Stack;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.rpn.models.AddTwoNumbersTogether;
import com.lmig.gfc.rpn.models.DivideTwoNumbersTogether;
import com.lmig.gfc.rpn.models.ExponentTwoNumbersTogether;
import com.lmig.gfc.rpn.models.MultiplyTwoNumbersTogether;
import com.lmig.gfc.rpn.models.OneArgumentUndoer;
import com.lmig.gfc.rpn.models.PushUndoer;
import com.lmig.gfc.rpn.models.SubtractTwoNumberTogether;
import com.lmig.gfc.rpn.models.TwoArgumentUndoer;
import com.lmig.gfc.rpn.models.TwoNumberCalculation;
import com.lmig.gfc.rpn.models.Undoer;

@Controller
public class HomeController {

	private Stack<Double> stack;
	private Stack<Undoer> undoers;

	public HomeController() {
		stack = new Stack<Double>();
		undoers = new Stack<Undoer>();
	}

	@GetMapping("/")
	public ModelAndView showApp() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("calculator");
		mv.addObject("stack", stack);
		mv.addObject("hasTwoOrMoreNumbers", stack.size() >= 2);
		mv.addObject("hasUndoer", undoers.size() > 0);
		return mv;
	}

	@PostMapping("/enter")
	public ModelAndView pushNumberOntoStack(double value) {
		// uppercase Double - can be nullable
		// lower case D - expects the value to be present at all times

		stack.push(value);
		undoers.push(new PushUndoer());
		// undoers = new PushUndoer();
		// undoer = null;

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}

	@PostMapping("/add")
	public ModelAndView addTwoNumbers() {

		AddTwoNumbersTogether adder = new AddTwoNumbersTogether(stack);
		return doOperation(adder);
	}

	@PostMapping("/minus")
	public ModelAndView subtractTwoNumbers() {

		SubtractTwoNumberTogether minuser = new SubtractTwoNumberTogether(stack);
		return doOperation(minuser);
	}

	@PostMapping("/divide")
	public ModelAndView divideTwoNumbers() {

		DivideTwoNumbersTogether divider = new DivideTwoNumbersTogether(stack);
		return doOperation(divider);
	}

	@PostMapping("/multiply")
	public ModelAndView multiplyTwoNumbers() {

		MultiplyTwoNumbersTogether multiplier = new MultiplyTwoNumbersTogether(stack);
		return doOperation(multiplier);
	}
	
	@PostMapping("/exponent")
	public ModelAndView exponentTwoNumbers() {

		ExponentTwoNumbersTogether expo = new ExponentTwoNumbersTogether(stack);
		return doOperation(expo);

	}

	@PostMapping("/abs")
	public ModelAndView absOneNumber() {

		double value = stack.pop();
		undoers.push(new OneArgumentUndoer(value));

		double result = Math.abs(value);

		stack.push(result);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}

	@PostMapping("/undo")
	public ModelAndView undo() {
		// uppercase Double - can be nullable
		// lower case D - expects the value to be present at all times
		Undoer undoer = undoers.pop();
		undoer.undo(stack);
		// undoer = null;

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}

	private ModelAndView doOperation(TwoNumberCalculation calcy) {
		calcy.goDoIt();
		undoers.push(calcy);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;

	}

}
