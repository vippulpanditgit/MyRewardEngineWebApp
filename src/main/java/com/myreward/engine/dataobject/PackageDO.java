package com.myreward.engine.dataobject;

import java.util.Date;
import java.util.List;

public class PackageDO extends BaseDO {
	String description;
	Date effectiveDate;
	Date expirationDate;
	
	List<PackageDO> subPackages;

}
