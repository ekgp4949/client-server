package com.study.handler;

import com.study.util.Stack;

public class PrintHistoryCommand implements Command {

	Stack<String> history;

	public PrintHistoryCommand(Stack<String> history) {
		this.history = history;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}
}
