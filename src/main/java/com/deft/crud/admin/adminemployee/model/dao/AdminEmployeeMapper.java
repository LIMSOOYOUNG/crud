package com.deft.crud.admin.adminemployee.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.deft.crud.admin.adminemployee.model.dto.AdminEmployeeDTO;
import com.deft.crud.admin.adminemployee.model.dto.DepartmentDTO;
import com.deft.crud.admin.adminemployee.model.dto.EmployeeImageDTO;
import com.deft.crud.admin.adminemployee.model.dto.JobDTO;
import com.deft.crud.member.model.dto.MemberDTO;

@Mapper
public interface AdminEmployeeMapper {

	/* 사원 상세보기 */
	AdminEmployeeDTO empDetail(int employeeNo);

	/* 수정 페이지 */
	AdminEmployeeDTO empInfoModify(int employeeNo);

	/* 수정 페이지 수정 부분 */
	int employeeModify(AdminEmployeeDTO parameters);

	/* JobName List 불러오기 */
	List<JobDTO> jobNameList();

	/* DeptName List 불러오기*/
	List<DepartmentDTO> deptNameList();

	/* manager List 불러오기*/
	List<AdminEmployeeDTO> managerList();

	/* 아이디 중복여부 확인 */
	int checkUserId(String empId);

	/* 사원 등록 */
	int insertMember(MemberDTO member);

	List<MemberDTO> selectJobList();

	List<MemberDTO> selectDeptList();

	List<MemberDTO> selectAuthorityList();

	List<MemberDTO> selectManagerList(String deptCode);

	int insetProfileImg(MemberDTO member);

	/* 사원정보 텍스트 수정 */
	int modifyEmployeeText(AdminEmployeeDTO parameters);
	/* 사원 이미지 정보 수정 */
	int modifyEmployeeImage(EmployeeImageDTO employeeImageDTO);
	
}
