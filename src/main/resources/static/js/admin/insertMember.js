const insertMember = {
	
	init: function() {
		// 바로 실행되는 스크립트들을 호출	
		insertMember.bind()
	},
	
	bind: function() {
		// 이벤트를 추가해줌(키를 입력할 경우 or selectBox값이 변경 될 경우)
		document.getElementById('empId').addEventListener('keyup', insertMember.onKeyupUserIdCheck)
		document.getElementById('pwd1').addEventListener('keyup', insertMember.onKeyupPassword)
		document.getElementById('pwd2').addEventListener('keyup', insertMember.equalsPassword)		
		document.getElementById('deptNmae').addEventListener('change', insertMember.selectManagerName)		
	},
	
	// 사용할 ID입력 시 중복여부 체크 함수 
	onKeyupUserIdCheck: function(empId) {
		if(empId === '' || typeof empId === 'undefined')
			return document.getElementById('checkId').innerHTML = ""	
		
		const userId = document.getElementById('empId').value
		const request = {empId: userId}
		let queryString = '?' + $.param(request)

		$.ajax({
			type: "GET",
			url: "/admin/emp/checkUserId" + queryString,
			success: function(res) {
				console.log(res)
				
				if(document.getElementById('empId').value === '') {
					document.getElementById('checkId').innerHTML = ""	
				
				} else if(res == true){
					document.getElementById('checkId').innerHTML = "(사용가능한 ID 입니다.)"
					document.getElementById('checkId').style.color = 'green'
				} else {
					document.getElementById('checkId').innerHTML = "(이미사용중인 ID 입니다.)"
					document.getElementById('checkId').style.color = 'red'
				}
			}
		})
	},
	
	// 사용할 비밀번호 입력 시 유효성검사 & 입력한 비밀번호와 재확비밀번호 값 일치 여부확인
	onKeyupPassword: function() {
		const password = document.getElementById('pwd1').value
		
		// 재확인 비밀번호 일치 여부 확인
		insertMember.equalsPassword(password)
		// 비밀번호 유효성 검사
		if(! insertMember.validPassword(password))
			return
		
	},
	
	// 비밀번호 유효성 검사
	validPassword: function(password) {
		
		// 비밀번호가 없는 경우
		if(password === '' || typeof password === 'undefined')
			return document.getElementById('checkPwd1').innerHTML = ""	
		
		// 소문자 대문자 숫자를 포함하여 8~16자 체크
		if(!/^[a-zA-Z0-9]{8,16}$/.test(password)){
			document.getElementById('checkPwd1').style.color = 'red'
    		document.getElementById('checkPwd1').innerHTML = "(8~16 자리(영문+숫자)로 입력해주세요.)"	
   			return false;
  		}

  		const checkNum = password.search(/[0-9]/g); // 숫자사용
  		const checkEng = password.search(/[a-z]/ig); // 영문사용
		
		// 숫자와 영문 사용 여부 확인
  		if(checkNum <0 || checkEng <0){
			document.getElementById('checkPwd1').style.color = 'red'
    		document.getElementById('checkPwd1').innerHTML = "(숫자와 영문자를 조합하여야 합니다.)"	
    		return false;
    		
 		} else {
	
 			document.getElementById('checkPwd1').style.color = 'green' 
    		document.getElementById('checkPwd1').innerHTML = "(사용가능한 비밀번호 입니다.)"		
		}
 		
	},
	
	// 재확인 비밀번호 일치 여부 확인
	equalsPassword: function() {
		const password = document.getElementById('pwd1').value
		const checkPassword = document.getElementById('pwd2').value
		
		// 재확인 비밀번호가 없는 경우
		if(checkPassword === '' || typeof checkPassword === 'undefined')
			return document.getElementById('checkPwd2').innerHTML = ""
		
		// 비밀번호가 일치하지 않는 경우
		if(password !== checkPassword) {
			document.getElementById('checkPwd2').innerHTML = "(비밀번호가 일치하지 않습니다.)"	
			return document.getElementById('checkPwd2').style.color = 'red'
		}
		
		// 서로 같은 경우 통과
		if(password === checkPassword) {
			document.getElementById('checkPwd2').innerHTML = "(비밀번호가 일치합니다.)"	
			return document.getElementById('checkPwd2').style.color = 'green'
		}
	},

	// 소속부서의 담당자 목록 불러와 selectBox 만들기	
	selectManagerName: function() {
		
		const deptCode = document.getElementById('deptNmae').value
		const request = {deptCode: deptCode}
		
		let queryString = '?' + $.param(request)
		
		$.ajax({
			type: "GET",
			url: "/admin/manager/find" + queryString,
			success: function(managerList) {
				
				const $select = $("#managerName");
				$select.html("");
				
				for(let index in managerList) {
					
					$select.append("<option value='" + managerList[index].empNo + "'>" + managerList[index].empName + "</option>");
				}
			}
		});
	}
}

insertMember.init()