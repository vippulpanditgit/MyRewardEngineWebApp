package com.myreward.engine.webapp;

import java.util.UUID;

import org.antlr.v4.runtime.RecognitionException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myreward.engine.event.error.MetadataParsingException;
import com.myreward.engine.event.processor.MetaOpCodeProcessor;
import com.myreward.engine.model.RuleRequestDO;
import com.myreward.engine.rule.MyRewardMetaDataHashTable;

@RestController
public class MyRewardMetaDataHandler {
	public MyRewardMetaDataHandler() {

	}
	@RequestMapping(value="/rule", method = RequestMethod.GET)
	public ResponseEntity<String> get(@RequestParam("id") String id) {
		if(StringUtils.isBlank(id))
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		RuleRequestDO ruleRequestDO = MyRewardMetaDataHashTable.getInstance().getMetaDataHashTable().get(id);
		if(ruleRequestDO==null)
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<String>(ruleRequestDO.getRule(), HttpStatus.OK);
	}
	@RequestMapping(value="/rule", method = RequestMethod.POST)
	public ResponseEntity<String> set(@RequestBody String rule) {
		try {
			RuleRequestDO ruleRequestDO = create_rule_data_object(rule);
			MyRewardMetaDataHashTable.getInstance().getMetaDataHashTable().put(ruleRequestDO.getRuleId(), ruleRequestDO);
			return new ResponseEntity<String>(ruleRequestDO.getRuleId(), HttpStatus.OK);
		} catch(Exception exp) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	private RuleRequestDO create_rule_data_object(String rule) throws RecognitionException, MetadataParsingException {
		RuleRequestDO ruleRequestDO = new RuleRequestDO();
		ruleRequestDO.setMetaOpCodeProcessor(new MetaOpCodeProcessor());
		ruleRequestDO.getMetaOpCodeProcessor().initialize();
		ruleRequestDO.setRule(rule);
		ruleRequestDO.setRuleId(UUID.randomUUID().toString());
		ruleRequestDO.getMetaOpCodeProcessor().parse(rule, false);
		ruleRequestDO.getMetaOpCodeProcessor().initialize();
		return ruleRequestDO;
	}
	
}
