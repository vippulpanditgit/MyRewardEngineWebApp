package com.myreward.engine.model;

import com.myreward.engine.model.event.EventDO;

public class EventRequestDO {
	private String ruleId;
	private EventDO eventDO;
	public String getRuleId() {
		return ruleId;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	public EventDO getEventDO() {
		return eventDO;
	}
	public void setEventDO(EventDO eventDO) {
		this.eventDO = eventDO;
	}

}
