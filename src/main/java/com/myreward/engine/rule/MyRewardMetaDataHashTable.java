package com.myreward.engine.rule;

import java.util.HashMap;
import java.util.Map;

public class MyRewardMetaDataHashTable {
	private static MyRewardMetaDataHashTable instance = null;
	private static Map<String, String> metaDataHashTable = new HashMap<String, String>();
	public static MyRewardMetaDataHashTable getInstance() {
		if(instance == null) {
			instance = new MyRewardMetaDataHashTable();
		}
		return instance;
	}
	public Map<String, String> getMetaDataHashTable() {
		return metaDataHashTable;
	}
	public void setMetaDataHashTable(Map<String, String> metaDataHashTable) {
		MyRewardMetaDataHashTable.metaDataHashTable = metaDataHashTable;
	}	
}
