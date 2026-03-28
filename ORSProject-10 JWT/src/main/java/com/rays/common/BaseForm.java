package com.rays.common;

/**
 * Base Form class used for handling request data.
 * 
 * This class is extended by all form classes to provide
 * common properties such as id, pagination, and operation handling.
 * 
 * It also provides utility methods to convert form data into DTO.
 * 
 * @author Rishabh Shrivastava
 */
public class BaseForm {

	protected Long id;

	protected String createdBy;

	protected String modifiedBy;

	protected long createdDatetime;

	protected long modifiedDatetime;

	private Long[] ids;

	private int pageNo = 0;

	private int pageSize = 5;

	private String operation;

	/**
	 * Gets ID.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets ID.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Converts form into DTO.
	 * This method is overridden in child classes.
	 * 
	 * @return BaseDTO object
	 */
	public BaseDTO getDto() {
		return null;
	}

	/**
	 * Initializes DTO with form data.
	 * 
	 * @param dto DTO object
	 * @param <T> type of DTO
	 * @return initialized DTO
	 */
	public <T extends BaseDTO> T initDTO(T dto) {
		System.out.println("id => base dto => " + id);
		if (id != null && id > 0) {
			dto.setId(id);
		} else {
			dto.setId(null);
		}
		return dto;
	}
}