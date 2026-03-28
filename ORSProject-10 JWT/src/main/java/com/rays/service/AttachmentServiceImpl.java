package com.rays.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.AttachmentDAOInt;
import com.rays.dto.AttachmentDTO;

/**
 * Attachment Service implementation class.
 * 
 * This class provides business logic for AttachmentDTO.
 * It extends BaseServiceImpl to reuse common CRUD operations.
 * 
 * It acts as a service layer between controller and DAO
 * for attachment-related operations.
 * 
 * @author Rishabh Shrivastava
 */
@Service
@Transactional
public class AttachmentServiceImpl extends BaseServiceImpl<AttachmentDTO, AttachmentDAOInt>
		implements AttachmentServiceInt {

}