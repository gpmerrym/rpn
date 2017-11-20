package com.lmig.gfc.rpn.controller;

import java.util.Stack;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.rpn.models.OneArgumentUndoer;
import com.lmig.gfc.rpn.models.TwoArgumentUndoer;

@Controller
public class HomeController {

	private Stack<Double> stack;
	private OneArgumentUndoer undoer;
	

	public HomeController() {
		stack = new Stack<Double>();
	}
	
	

	@GetMapping("/")
	public ModelAndView showApp() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("calculator");
		mv.addObject("stack", stack);
		mv.addObject("hasTwoOrMoreNumbers", stack.size() >= 2);
		//mv.addObject("hasOneOrMoreNumbers", stack.size() >= 1);
		mv.addObject("hasUndoer", undoer != null);
		
		return mv;
	}

	@PostMapping("/enter")
	public ModelAndView pushNumberOntoStack(double value) {
		// uppercase Double - can be nullable
		// lower case D - expects the value to be present at all times

		stack.push(value);
		undoer = null;

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}

	@PostMapping("/add")
	public ModelAndView addTwoNumbers() {

		double first = stack.pop();
		double second = stack.pop();
		double result = first + second;

		stack.push(result);
		undoer = new TwoArgumentUndoer(first, second);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}
	
	@PostMapping("/minus")
	public ModelAndView subtractTwoNumbers() {

		double first = stack.pop();
		double second = stack.pop();
		double result = second-first;

		stack.push(result);
		undoer = new TwoArgumentUndoer(first, second);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}
	
	@PostMapping("/abs")
	public ModelAndView absOneNumber() {

		double value = stack.pop();
		undoer = new OneArgumentUndoer(value);
		
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
		
		undoer.undo(stack);
		
		undoer = null;

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}

}
