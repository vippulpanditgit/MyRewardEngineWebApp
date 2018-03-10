package com.myreward.engine.webapp;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myreward.engine.event.processor.MetaOpCodeProcessor;
import com.myreward.engine.model.RuleRequestDO;
import com.myreward.engine.rule.MyRewardMetaDataHashTable;

@RestController
public class MyRewardMetaDataHandler {
	public MyRewardMetaDataHandler() {

	}
	@RequestMapping(value="/rule", method = RequestMethod.GET)
	public String get() {
		return "TEST";
	}
	@RequestMapping(value="/rule", method = RequestMethod.POST)
	public ResponseEntity<String> set(@RequestBody String rule) {
		try {
			RuleRequestDO ruleRequestDO = new RuleRequestDO();
			ruleRequestDO.setMetaOpCodeProcessor(new MetaOpCodeProcessor());
			ruleRequestDO.getMetaOpCodeProcessor().initialize();
			ruleRequestDO.setRule(rule);
			ruleRequestDO.setRuleId(UUID.randomUUID().toString());
			ruleRequestDO.getMetaOpCodeProcessor().parse(rule);
			ruleRequestDO.getMetaOpCodeProcessor().initialize();
			MyRewardMetaDataHashTable.getInstance().getMetaDataHashTable().put(ruleRequestDO.getRuleId(), ruleRequestDO);
			return new ResponseEntity<String>(ruleRequestDO.getRuleId(), HttpStatus.OK);
		} catch(Exception exp) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	
}
