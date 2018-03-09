package com.myreward.engine.webapp;

import org.antlr.v4.runtime.RecognitionException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myreward.engine.event.error.EventProcessingException;
import com.myreward.engine.event.error.MetadataParsingException;
import com.myreward.engine.event.processor.EventProcessor;
import com.myreward.engine.event.processor.MetaOpCodeProcessor;
import com.myreward.engine.model.EventRequestDO;
import com.myreward.engine.model.RuleRequestDO;
import com.myreward.engine.model.event.EventDO;
import com.myreward.engine.rule.MyRewardMetaDataHashTable;
import com.myreward.parser.generator.MyRewardDataSegment;

@RestController
public class MyRewardEventHandler {
	private EventProcessor eventProcessor;
	private MetaOpCodeProcessor metaOpCodeProcessor = new MetaOpCodeProcessor();
	
	public MyRewardEventHandler() {
	}
	
	@RequestMapping("/event")
	public void process(@RequestBody EventRequestDO eventRequestDO) throws Exception {
		try {
			if(eventRequestDO==null)
				throw new Exception();

			RuleRequestDO ruleRequestDO = MyRewardMetaDataHashTable.getInstance().getMetaDataHashTable().get(eventRequestDO.getRuleId());
	        eventProcessor = new EventProcessor(ruleRequestDO.getMetaOpCodeProcessor());
	        eventProcessor.setMyRewardDataSegment(eventProcessor.createDataSegment());
	        eventProcessor.create_meta_tree();
			if(eventProcessor.process_event(eventRequestDO.getEventDO())) {
				eventProcessor.getMyRewardDataSegment().printString();
			}
		} catch (RecognitionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MetadataParsingException|EventProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
