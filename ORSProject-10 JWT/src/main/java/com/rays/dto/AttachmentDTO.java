package com.rays.dto;

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

import com.rays.common.BaseDTO;

/**
 * Data Transfer Object (DTO) for Attachment.
 * This class represents file attachments uploaded by users.
 * 
 * It is mapped to the database table ST_ATTACHMENT using JPA annotations.
 * 
 * The class stores file details such as name, type, description,
 * associated userId, and the actual file content in byte array format.
 * 
 * It also provides a constructor to convert MultipartFile into byte array
 * for storing file data in the database.
 * 
 * Extends BaseDTO to inherit common properties and methods.
 * 
 * @author Rishabh Shrivastava
 */
@Entity
@Table(name = "ST_ATTACHMENT")
public class AttachmentDTO extends BaseDTO {

	/**
	 * Name of the file.
	 */
	@Column(name = "NAME", length = 100)
	protected String name = null;

	/**
	 * MIME type of the file.
	 */
	@Column(name = "TYPE", length = 100)
	protected String type = null;

	/**
	 * Description of the file.
	 */
	@Column(name = "DESCRIPTION", length = 500)
	protected String description = null;

	/**
	 * ID of the user who uploaded the file.
	 */
	@Column(name = "USER_ID")
	protected Long userId = null;

	/**
	 * Binary data of the file stored as byte array.
	 */
	@Lob
	@Column(name = "DOC")
	private byte[] doc;

	/**
	 * Default constructor.
	 */
	public AttachmentDTO() {
	}

	/**
	 * Constructor to create AttachmentDTO from MultipartFile.
	 * It extracts file name, type, and converts file content into byte array.
	 * 
	 * @param file uploaded multipart file
	 */
	public AttachmentDTO(MultipartFile file) {
		name = file.getOriginalFilename();
		type = file.getContentType();

		try {
			doc = file.getBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets file name.
	 * 
	 * @return file name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets file name.
	 * 
	 * @param name file name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets file type.
	 * 
	 * @return file type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets file type.
	 * 
	 * @param type file type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets file description.
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets file description.
	 * 
	 * @param description file description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets user ID.
	 * 
	 * @return user ID
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * Sets user ID.
	 * 
	 * @param userId user ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * Returns value (not implemented).
	 * 
	 * @return null
	 */
	public String getValue() {
		return null;
	}

	/**
	 * Gets file binary data.
	 * 
	 * @return byte array of file
	 */
	public byte[] getDoc() {
		return doc;
	}

	/**
	 * Sets file binary data.
	 * 
	 * @param doc byte array
	 */
	public void setDoc(byte[] doc) {
		this.doc = doc;
	}

	/**
	 * Returns unique key (not implemented).
	 * 
	 * @return null
	 */
	@Override
	public String getUniqueKey() {
		return null;
	}

	/**
	 * Returns unique value (not implemented).
	 * 
	 * @return null
	 */
	@Override
	public String getUniqueValue() {
		return null;
	}

	/**
	 * Returns label (not implemented).
	 * 
	 * @return null
	 */
	@Override
	public String getLabel() {
		return null;
	}

	/**
	 * Returns table name (not implemented).
	 * 
	 * @return null
	 */
	@Override
	public String getTableName() {
		return null;
	}
}