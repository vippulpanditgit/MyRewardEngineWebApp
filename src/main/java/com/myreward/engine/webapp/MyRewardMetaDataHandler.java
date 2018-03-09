package com.myreward.engine.webapp;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myreward.engine.event.processor.EventProcessor;
import com.myreward.engine.event.processor.MetaOpCodeProcessor;
import com.myreward.engine.model.RuleRequestDO;
import com.myreward.engine.rule.MyRewardMetaDataHashTable;
import com.myreward.parser.generator.MyRewardDataSegment;

@RestController
@RequestMapping("/rule")
public class MyRewardMetaDataHandler {

	private RuleRequestDO ruleRequestDO;
	
	public MyRewardMetaDataHandler() {
		ruleRequestDO = new RuleRequestDO();
		ruleRequestDO.setMetaOpCodeProcessor(new MetaOpCodeProcessor());
		ruleRequestDO.getMetaOpCodeProcessor().initialize();
/*		metaOpCodeProcessor.parse(reward_metadata);
		metaOpCodeProcessor.print_code_segment();
        MyRewardDataSegment myRewardDataSegment = eventProcessor.createDataSegment();
        eventProcessor.setMyRewardDataSegment(myRewardDataSegment);
*/
	}
	@RequestMapping(method = RequestMethod.GET)
	public String get() {
		return "TEST";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String set(@RequestBody String rule) {
		try {
			ruleRequestDO.setRule(rule);
			ruleRequestDO.setRuleId(UUID.randomUUID().toString());
			ruleRequestDO.getMetaOpCodeProcessor().parse(rule);
			ruleRequestDO.getMetaOpCodeProcessor().initialize();
			MyRewardMetaDataHashTable.getInstance().getMetaDataHashTable().put(ruleRequestDO.getRuleId(), ruleRequestDO);
			return ruleRequestDO.getRuleId();
		} catch(Exception exp) {
			exp.printStackTrace();
		}
		return null;
	}
	
}
