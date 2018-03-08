package com.myreward.engine.parser;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myreward.engine.event.processor.EventProcessor;
import com.myreward.engine.event.processor.MetaOpCodeProcessor;
import com.myreward.parser.generator.MyRewardDataSegment;

@RestController
@RequestMapping("/rule")
public class MyRewardMetaDataHandler {
	
	private MetaOpCodeProcessor metaOpCodeProcessor;
	private EventProcessor eventProcessor;
	
	public MyRewardMetaDataHandler() {
		MetaOpCodeProcessor metaOpCodeProcessor = new MetaOpCodeProcessor();
        EventProcessor eventProcessor = new EventProcessor(metaOpCodeProcessor);
		metaOpCodeProcessor.initialize();
/*		metaOpCodeProcessor.parse(reward_metadata);
		metaOpCodeProcessor.print_code_segment();
        MyRewardDataSegment myRewardDataSegment = eventProcessor.createDataSegment();
        eventProcessor.setMyRewardDataSegment(myRewardDataSegment);
*/
	}

	@RequestMapping(method = RequestMethod.POST)
	public String[] add(String rule) {
		try {
			metaOpCodeProcessor.parse(rule);
			MyRewardDataSegment myRewardDataSegment = eventProcessor.createDataSegment();
			return null;
		} catch(Exception exp) {
			
		}
		return null;
	}
}
