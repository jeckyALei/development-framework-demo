package com.mwh.demo.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.processor.IProcessor;


public class UtilDialect extends AbstractDialect { 

    public UtilDialect() { 
        super(); 
    } 
     
    /* 
     * All of this dialect's attributes and/or tags 
     * will start with 'hello:*' 
     */ 
    public String getPrefix() { 
        return "utils"; 
    } 

     
    /* 
     * Non-lenient: if a tag starting with 'hello:' is 
     * found but no processor exists in this dialect for it, 
     * throw an exception.  
     */ 
    public boolean isLenient() { 
        return false; 
    } 

     
    /* 
     * The processors. 
     */ 
    @Override 
    public Set<IProcessor> getProcessors() { 
        final Set<IProcessor> processors = new HashSet<IProcessor>(); 
        processors.add(new ImportScriptProcessor()); 
        processors.add(new ImportCssProcessor()); 
        return processors; 
    } 


} 