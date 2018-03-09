package com.myreward.engine.webapp;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myreward.engine.event.processor.EventProcessor;
import com.myreward.engine.event.processor.MetaOpCodeProcessor;
import com.myreward.engine.rule.MyRewardMetaDataHashTable;
import com.myreward.parser.generator.MyRewardDataSegment;

@RestController
@RequestMapping("/rule")
public class MyRewardMetaDataHandler {

	private MetaOpCodeProcessor metaOpCodeProcessor;
	private EventProcessor eventProcessor;
	
	public MyRewardMetaDataHandler() {
		metaOpCodeProcessor = new MetaOpCodeProcessor();
        eventProcessor = new EventProcessor(metaOpCodeProcessor);
		metaOpCodeProcessor.initialize();
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
	public String add(@RequestBody String rule) {
		try {
			String uuid = UUID.randomUUID().toString();
			metaOpCodeProcessor.parse(rule);
			MyRewardMetaDataHashTable.getInstance().getMetaDataHashTable().put(uuid, rule);
			return uuid;
		} catch(Exception exp) {
			exp.printStackTrace();
		}
		return null;
	}
}
