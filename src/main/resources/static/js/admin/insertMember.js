const insertMember = {
	
	init: () => {
		//바로 실행되는 스크립트들을 호출	
		insertMember.bind()
	},
	
	bind: () => {
		//이벤트 추가하는 부분
		document.getElementById('empId').addEventListener('keyup', insertMember.onKeyupUserIdCheck)
		document.getElementById('pwd1').addEventListener('keyup', insertMember.onKeyupPassword)
		document.getElementById('pwd2').addEventListener('keyup', insertMember.equalsPassword)		
	},
	
	onKeyupUserIdCheck: () => {
		const userId = document.getElementById('empId').value
		const request = {empId: userId}
		let queryString = '?' + $.param(request)

		$.ajax({
			type: "GET",
			url: "/admin/emp/checkUserId" + queryString,
			success: (res) => {
				console.log(res)
			}
		})
		
	},
	
	onKeyupPassword: () => {
		const password = document.getElementById('pwd1').value
		
		//재확인 비밀번호 일치 여부 확인
		insertMember.equalsPassword(password)
		//유효성 검사
		if(! insertMember.validPassword(password))
			return
		
	},
	
	validPassword: (password) => {
		//비밀번호가 없는 경우
		if(password === '' || typeof password === 'undefined')
			return document.getElementById('checkPwd1').innerHTML = ""	
		
		//소문자 대문자 숫자를 포함하여 8~16자 체크
		if(!/^[a-zA-Z0-9]{8,16}$/.test(password)){
			document.getElementById('checkPwd1').style.color = 'red'
    		document.getElementById('checkPwd1').innerHTML = "8~16 자리로 입력해주세요."	
   			return false;
  		}

  		const checkNum = password.search(/[0-9]/g); // 숫자사용
  		const checkEng = password.search(/[a-z]/ig); // 영문사용
		
		//숫자와 영문 사용 여부 확인
  		if(checkNum <0 || checkEng <0){
			document.getElementById('checkPwd1').style.color = 'red'
    		document.getElementById('checkPwd1').innerHTML = "숫자와 영문자를 조합하여야 합니다."	
    		return false;
 		}
 		
 		return document.getElementById('checkPwd1').innerHTML = ""	
	},
	
	equalsPassword: () => {
		const password = document.getElementById('pwd1').value
		const checkPassword = document.getElementById('pwd2').value
		
		//재확인 비밀번호가 없는 경우
		if(checkPassword === '' || typeof checkPassword === 'undefined')
			return document.getElementById('checkPwd2').innerHTML = ""
		
		//비밀번호가 일치하지 않는 경우
		if(password !== checkPassword) {
			document.getElementById('checkPwd2').innerHTML = "비밀번호가 일치하지 않습니다."	
			return document.getElementById('checkPwd2').style.color = 'red'
		}
		
		//서로 같은 경우 통과
		if(password === checkPassword) {
			document.getElementById('checkPwd2').innerHTML = "비밀번호가 일치합니다."	
			return document.getElementById('checkPwd2').style.color = 'green'
		}
	}
	
}

insertMember.init()