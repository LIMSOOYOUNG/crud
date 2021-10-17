const changePwd = {
	
	init: function() {
		//바로 실행되는 스크립트들을 호출	
		changePwd.bind()
	},
	
	bind: function() {
		//이벤트 추가하는 부분
		document.getElementById('pwdCheck').addEventListener('keyup', changePwd.onKeyupUserPwdCheck)
		document.getElementById('pwdCheck1').addEventListener('keyup', changePwd.onKeyupPassword)
		document.getElementById('pwdCheck2').addEventListener('keyup', changePwd.equalsPassword)		
		document.getElementById('changePwdBtn').addEventListener('click', changePwd.changePassword)		
	},
	
	onKeyupUserPwdCheck: function(changePwd) {
		/*const test = document.getElementById('pwdCheck').value
		console.log(test);*/
		
		if(changePwd === '' || typeof changePwd === 'undefined')
			return document.getElementById('checkPwdSpan').innerHTML = ""	
		
		const userPwd = document.getElementById('pwdCheck').value
		
		console.log(userPwd);
		const request = {userPwd: userPwd}
		let queryString = '?' + $.param(request)

		$.ajax({
			type: "GET",
			url: "/employee/pwd/check" + queryString,
			success: function(res) {
				console.log(res)
				
				if(document.getElementById('pwdCheck').value === '') {
					document.getElementById('checkPwdSpan').innerHTML = ""	
				
				} else if(res == true){
					document.getElementById('checkPwdSpan').innerHTML = "(비밀번호 확인완료)"
					document.getElementById('checkPwdSpan').style.color = 'green'
				} else {
					document.getElementById('checkPwdSpan').innerHTML = "(비밀번호가 일치하지 않습니다.)"
					document.getElementById('checkPwdSpan').style.color = 'red'
				}
			}
		})
	},
	
	onKeyupPassword: function() {
		const password = document.getElementById('pwdCheck1').value
		
		//재확인 비밀번호 일치 여부 확인
		changePwd.equalsPassword(password)
		//유효성 검사
		if(! changePwd.validPassword(password))
			return
		
	},
	
	validPassword: function(password) {
		//비밀번호가 없는 경우
		if(password === '' || typeof password === 'undefined')
			return document.getElementById('checkPwdSpan1').innerHTML = ""	
		
		//소문자 대문자 숫자를 포함하여 8~16자 체크
		if(!/^[a-zA-Z0-9]{8,16}$/.test(password)){
			document.getElementById('checkPwdSpan1').style.color = 'red'
    		document.getElementById('checkPwdSpan1').innerHTML = "(8~16 자리(영문+숫자)로 입력해주세요.)"	
   			return false;
  		}

  		const checkNum = password.search(/[0-9]/g); // 숫자사용
  		const checkEng = password.search(/[a-z]/ig); // 영문사용
		
		//숫자와 영문 사용 여부 확인
  		if(checkNum <0 || checkEng <0){
			document.getElementById('checkPwdSpan1').style.color = 'red'
    		document.getElementById('checkPwdSpan1').innerHTML = "(숫자와 영문자를 조합하여야 합니다.)"	
    		return false;
 		}
 		
 		const inputChangePwd = document.getElementById('pwdCheck1').value
		
		console.log(inputChangePwd);
		const request = {userPwd: inputChangePwd}
		let queryString = '?' + $.param(request)

		$.ajax({
			type: "GET",
			url: "/employee/pwd/check" + queryString,
			success: function(res) {
				console.log(res)
				
				if(document.getElementById('pwdCheck1').value === '') {
					document.getElementById('checkPwdSpan1').innerHTML = ""	
				
				} else if(res == false){
					document.getElementById('checkPwdSpan1').innerHTML = "(변경 가능한 비밀번호 입니다.)"
					document.getElementById('checkPwdSpan1').style.color = 'green'
				} else {
					document.getElementById('checkPwdSpan1').innerHTML = "(기존 비밀번호와 같습니다.)"
					document.getElementById('checkPwdSpan1').style.color = 'red'
				}
			}
		})
 		
 		return document.getElementById('checkPwdSpan1').innerHTML = ""	
	},
	
	equalsPassword: function() {
		const password = document.getElementById('pwdCheck1').value
		const checkPassword = document.getElementById('pwdCheck2').value
		
		//재확인 비밀번호가 없는 경우
		if(checkPassword === '' || typeof checkPassword === 'undefined')
			return document.getElementById('checkPwdSpan2').innerHTML = ""
		
		//비밀번호가 일치하지 않는 경우
		if(password !== checkPassword) {
			document.getElementById('checkPwdSpan2').innerHTML = "(비밀번호가 일치하지 않습니다.)"	
			return document.getElementById('checkPwdSpan2').style.color = 'red'
		}
		
		//서로 같은 경우 통과
		if(password === checkPassword) {
			document.getElementById('checkPwdSpan2').innerHTML = "(비밀번호가 일치합니다.)"	
			return document.getElementById('checkPwdSpan2').style.color = 'green'
		}
	},
	
	changePassword: function() {
		
		if(document.getElementById('checkPwdSpan').style.color == "green" && 
		   document.getElementById('checkPwdSpan1').style.color == "green" && 
		   document.getElementById('checkPwdSpan2').style.color == "green"){
			
		   document.getElementById('changePwdForm').submit();
			
		} else {
			alert("비밀번호 변경 조건을 확인해주세요")
		}
		
	}
	
}

changePwd.init()