package com.mwh.springmvc.thymeleaf;

import org.thymeleaf.dom.Node;
import org.thymeleaf.processor.IProcessorMatcher;
import org.thymeleaf.processor.ProcessorMatchingContext;

public class ScriptNodeProcessorMatcher implements IProcessorMatcher<Node>  {

	@Override
	public boolean matches(Node node, ProcessorMatchingContext context) {
		String nodename = node.getDocumentName();
		System.out.println(nodename);
		return true;
	}

	@Override
	public Class<Node> appliesTo() {
		return Node.class;
	}

}
