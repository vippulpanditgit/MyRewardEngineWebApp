package com.myreward.engine.rule;

import java.util.HashMap;
import java.util.Map;

import com.myreward.engine.model.RuleRequestDO;

public class MyRewardMetaDataHashTable {
	private static MyRewardMetaDataHashTable instance = null;
	private static Map<String, RuleRequestDO> metaDataHashTable = new HashMap<String, RuleRequestDO>();
	public static MyRewardMetaDataHashTable getInstance() {
		if(instance == null) {
			instance = new MyRewardMetaDataHashTable();
		}
		return instance;
	}
	public Map<String, RuleRequestDO> getMetaDataHashTable() {
		return metaDataHashTable;
	}
	public void setMetaDataHashTable(Map<String, RuleRequestDO> metaDataHashTable) {
		MyRewardMetaDataHashTable.metaDataHashTable = metaDataHashTable;
	}	
}
