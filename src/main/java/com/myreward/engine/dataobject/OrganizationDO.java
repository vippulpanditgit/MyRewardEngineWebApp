package com.myreward.engine.dataobject;

import java.util.Date;
import java.util.List;

public class OrganizationDO extends BaseDO {
	private String name;
	private Date terminatedOn;
	private String organizationType;
	private String marketType;
	private boolean isPrivate;
	private String marketSegmentType;
	private boolean isGovernment;
	private boolean isWomenOwned;
	private boolean isSmallBusiness;
	private String minorityType;
	private String religiousExemptionType;
	private String stockSymbol;
	private Date marketSegmentEffectiveDate;
	private boolean isERISACompliant;//Employee Retirement Income Security Act
	private List<OrganizationDO> subOrganizations;
	private List<OrganizationContactDO> organizationContacts;
	private List<OrganizationLocationDO> organizationLocations;

}
