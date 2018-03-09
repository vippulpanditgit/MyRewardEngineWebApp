package com.myreward.engine.model;

import com.myreward.engine.event.processor.MetaOpCodeProcessor;

public class RuleRequestDO {
	private String ruleId;
	private String rule;
	private MetaOpCodeProcessor metaOpCodeProcessor;
	public String getRuleId() {
		return ruleId;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	public MetaOpCodeProcessor getMetaOpCodeProcessor() {
		return metaOpCodeProcessor;
	}
	public void setMetaOpCodeProcessor(MetaOpCodeProcessor metaOpCodeProcessor) {
		this.metaOpCodeProcessor = metaOpCodeProcessor;
	}

}
