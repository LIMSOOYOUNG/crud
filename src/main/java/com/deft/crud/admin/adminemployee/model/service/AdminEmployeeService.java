package com.deft.crud.admin.adminemployee.model.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.deft.crud.admin.adminemployee.model.dao.AdminEmployeeMapper;
import com.deft.crud.admin.adminemployee.model.dto.AdminEmployeeDTO;
import com.deft.crud.admin.adminemployee.model.dto.DepartmentDTO;
import com.deft.crud.admin.adminemployee.model.dto.EmployeeImageDTO;
import com.deft.crud.admin.adminemployee.model.dto.JobDTO;
import com.deft.crud.member.model.dto.MemberDTO;

import net.coobird.thumbnailator.Thumbnails;

@Service
public class AdminEmployeeService {

	private AdminEmployeeMapper adminEmployeeMapper;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public AdminEmployeeService(AdminEmployeeMapper adminEmployeeMapper, PasswordEncoder passwordEncoder) {
		this.adminEmployeeMapper = adminEmployeeMapper;
		this.passwordEncoder = passwordEncoder;
	}
	
	/* ID중복 체크 여부 */
	public int checkUserId(String empId) {
		
		return adminEmployeeMapper.checkUserId(empId);
	}
	
	/* 사원 등록 */
	public int insertMember(HttpServletRequest request, MemberDTO member, MultipartFile profileThumbNail) {
		
		/* 입력한 비밀번호 암호화 */
		String encodedPassword = passwordEncoder.encode(member.getEmpPwd());	
	    
		/* 암호화한 비밀번호 등록할 사원정보DTO에 넣어줌 */
	    member.setEmpPwd(encodedPassword);
	    
	    /* 절대경로를 변수에 초기화한다. */
		String root = request.getSession().getServletContext().getRealPath("\\");
		
		/* 이미지 원본파일과 썸네일을 저장할 경로를 설정해준다.*/
		String fileUploadDirectory = root + "\\upload\\profileImage\\original";
		String thumbnailDirectory = root + "\\upload\\profileImage\\thumbnail";
		
		/* 파일 객체 생성 */
		File directory = new File(fileUploadDirectory);
		File directory2 = new File(thumbnailDirectory);
		
		/* 이미지를 저장할 폴더가 없을시에 폴더를 생성해준다. */
		if(!directory.exists() || !directory2.exists()) {
			System.out.println("폴더생성 : " + directory.mkdirs());
			System.out.println("폴더생성 : " + directory2.mkdirs());
		}
		
		/* 이미지 원본파일 */
		String originFileName = profileThumbNail.getOriginalFilename();
		
		/* 이미지 확장자 */
		String ext = originFileName.substring(originFileName.lastIndexOf("."));
		
		/* 이미지파일명이 중복되지 않도록 설정해준다. */
		String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
		
		member.setOriginalName(originFileName);
		member.setSavedName(savedName);
		member.setSavedPath(fileUploadDirectory);
		
		/* 썸네일 너비 높이 설정 */
		int width = 200;
		int height = 250;
	    
		/* 사원정보와 프로필사진저장 경로를 가지고 매퍼로 이동 */
	    int result = adminEmployeeMapper.insertMember(member);		
	    
	    int profileImgResult = 0;
	    
	    if(result > 0) {
	    	
	    	try {
				/* 원본 이미지 저장 */
	    		profileThumbNail.transferTo(new File(fileUploadDirectory + "\\" + savedName));
				
				/* 썸네일 저장 */
				Thumbnails.of(fileUploadDirectory + "\\" + savedName)
				.size(width, height)
				.toFile(thumbnailDirectory + "\\thumbnail_" + savedName);
				
				/* 썸네일 경로 멥에 담아준다. */
				String thumbnailPath = "/thumbnail_" + savedName;
				member.setThumbnailPath(thumbnailPath);
				
				/* 프로필이미지 등록 결과 값이 1이 되면 등록 성공 아니면 실패 */
				profileImgResult = adminEmployeeMapper.insetProfileImg(member);
				
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				
				/* 어떠한 예외처리가 발생한다면 파일 삭제 */
				new File(fileUploadDirectory + "\\" + savedName).delete();
			}
	    }
		
		return result;
	}
	
	/* 직급 목록 조회 */
	public List<MemberDTO> selectJobList() {

		List<MemberDTO> jobList = adminEmployeeMapper.selectJobList();
		
		return jobList;
	}

	/* 부서목록 조회 */
	public List<MemberDTO> selectDeptList() {

		List<MemberDTO> deptList = adminEmployeeMapper.selectDeptList();
		
		return deptList;
	}

	
	/* 사원 상세보기 */
	public AdminEmployeeDTO empDetail(int employeeNo) {
		
		AdminEmployeeDTO adminEmployeeDTO = adminEmployeeMapper.empDetail(employeeNo);
		
		return adminEmployeeDTO;
	}


	/* 사원정보 수정페이지 불러오기 */
	public AdminEmployeeDTO empInfoModify(int employeeNo) {
		
		AdminEmployeeDTO adminEmployeeDTO = adminEmployeeMapper.empInfoModify(employeeNo);
		
		return adminEmployeeDTO;
	}


	/* 사원정보 수정 */
	public int employeeModify(AdminEmployeeDTO parameters) {
		
		int result = adminEmployeeMapper.employeeModify(parameters);
		
		return result;
	}


	/* 직급명 조회 */
	public List<JobDTO> jobNameList() {
		
		List<JobDTO> JobList = adminEmployeeMapper.jobNameList();
		
		return JobList;
	}


	/* 부서명 조회 */
	public List<DepartmentDTO> deptNameList() {
		
		List<DepartmentDTO> deptList = adminEmployeeMapper.deptNameList();
		
		return deptList;
	}

	/* 매니저 리스트 */
	public List<AdminEmployeeDTO> managerList() {
		
		List<AdminEmployeeDTO> managerList = adminEmployeeMapper.managerList();
		
		return managerList;
	}

	public List<MemberDTO> selectManagerList(String deptCode) {
		
		List<MemberDTO> managerList = adminEmployeeMapper.selectManagerList(deptCode);
		
		return managerList;
	}
	
	/* 사원 정보 수정 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE,
			rollbackFor = {Exception.class})
	public int modifyEmployeeImg(AdminEmployeeDTO parameters, EmployeeImageDTO employeeImageDTO) {
		
		int result = 0;
		
		int modifyEmployeeText = adminEmployeeMapper.modifyEmployeeText(parameters);
		
		int modifyEmployeeImage = adminEmployeeMapper.modifyEmployeeImage(employeeImageDTO);
		
		
		if(modifyEmployeeText > 0 && modifyEmployeeImage > 0) {
			
			result = 1;
		}
		
		return result;
	}

	

	

	




	



}
