package com.mwh.demo.thymeleaf;

import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;
import org.thymeleaf.dom.Node;
import org.thymeleaf.dom.Text;
import org.thymeleaf.processor.AbstractProcessor;
import org.thymeleaf.processor.AttributeNameProcessorMatcher;
import org.thymeleaf.processor.IProcessorMatcher;
import org.thymeleaf.processor.ProcessorMatchingContext;
import org.thymeleaf.processor.ProcessorResult;


public class ImportCssProcessor extends AbstractProcessor {
	
	public ImportCssProcessor() {
	}

	public int getPrecedence() {
		return 10000;
	}

	@Override
	public IProcessorMatcher<? extends Node> getMatcher() {
		return new AttributeNameProcessorMatcher("merger","link");
	}

	@Override
	protected ProcessorResult doProcess(Arguments arguments,
			ProcessorMatchingContext processorMatchingContext, Node node) {
		String mergerFileName = ((Element)node).getAttributeValue("utils:merger");
		Text comment = new Text(String.format("<!-- css merger by %s (dev mode)-->", mergerFileName),false);
		((Element)node).addChild(comment);
		((Element)node).removeAttribute("utils:merger");
		return ProcessorResult.OK;
	}


}
