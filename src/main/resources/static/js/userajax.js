let index = {

	init:function(){
		$("#btn-join").on("click", ()=>{
		this.join();
		});
		$("#btn-update").on("click", ()=>{
		this.update();
		});
		$("#btn-del").on("click", ()=>{
		this.del();
		});
	},
	
	join:function(){
		const header = $("meta[name='_csrf_header']").attr("content");
		const token = $("meta[name='_csrf']").attr("content");
		
		var data = {
		username: $("#username").val(),
		password: $("#password").val(),
		name : $("#name").val(),
		email : $("#email").val(),
		mobile : $("#mobile").val(),
		postCode : $("#postcode").val(),
		address : $("#address").val(),
		detailAddress : $("#detailAddress").val(),
		extraAddress : $("#extraAddress").val()
		}

		$.ajax({
			type: "POST",
			url: "/deputy/join/joinProc",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8;",
			dataType: "json",
			beforeSend: function (jqXHR, settings) {
            jqXHR.setRequestHeader(header, token);
			},
			success: function onData(result){
			alert("가입완료");
			location.href="/deputy/main";
			},
			error: function errorData(){
				/* 통신 실패시 data 유효성 검사 */
				joinCheck();
			}
		});
	},
	
	update:function(){
		const header = $("meta[name='_csrf_header']").attr("content");
		const token = $("meta[name='_csrf']").attr("content");
	
		var data = {
			id : $("#id").val(),
			password: $("#Password").val(),
			mobile : $("#mobile").val(),
			email : $("#email").val(),
			postCode : $("#postcode").val(),
			address : $("#address").val(),
			detailAddress : $("#detailAddress").val(),
			extraAddress : $("#extraAddress").val()
		}
		console.log(data.id);
		
		console.log(data);
		$.ajax({
			type: "put",
			url: "/deputy/manager/updateProc",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8;",
			dataType: "json",
			beforeSend: function (jqXHR, settings) {
            jqXHR.setRequestHeader(header, token);
			},
			success: function updateData(){
				alert("수정 완료");
				location.href="/deputy/employee/detail";
			},
			error: function errorData(data){
				/* 통신 실패시 data 유효성 검사 */
				updateCheck();
			}
		});
		}
		
}

/* join 실행시 입력된 data값 유효성검사 */
function joinCheck(){

	const username_reg = /^(?=.*[a-zA-z])(?=.*[0-9])+$/;
	if(!username_reg.test($("#username").val())){
		const element = document.getElementById("usernameCheck");
		element.className = "alert-danger";
		element.innerText = "아이디를 입력해주세요(영문, 숫자)";
	} else {
		const element = document.getElementById("usernameCheck");
		element.className = "alert-success"
		element.innerText = "ok";
	}
	
	const password_reg = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[@$!%*#?&]).{8,16}$/;
	if(!password_reg.test($("#password").val())){
	const element = document.getElementById("passwordCheck");
		element.className = "alert-danger";
		element.innerText = "영문자, 숫자, 특수문자를 포함해주세요 (8~16자)";
	} else {
		const element = document.getElementById("passwordCheck");
		element.className = "alert-success"
		element.innerText = "ok";
	}

	const name_reg = /^[ㄱ-ㅎ가-힣]+$/;
	if(!name_reg.test($("#name").val())){
	const element = document.getElementById("nameCheck");
		element.className = "alert-danger";
		element.innerText = "한글을 입력해주세요";
	} else {
		const element = document.getElementById("nameCheck");
		element.className = "alert-success"
		element.innerText = "ok";
	}

	const email_reg = /[a-z0-9]+@[a-z]+\.[a-z]{2,3}$/;
	if(!email_reg.test($("#email").val())){
	const element = document.getElementById("emailCheck");
		element.className = "alert-danger";
		element.innerText = "메일 양식에 맞지 않습니다";
	} else {
		const element = document.getElementById("emailCheck");
		element.className = "alert-success"
		element.innerText = "ok";
	}
	
	const mobile_reg = /^01(?:0|1[6-9])([0-9]{3,4})([0-9]{4})/;
	if(!mobile_reg.test($("#mobile").val())){
	const element = document.getElementById("mobileCheck");
		element.className = "alert-danger";
		element.innerText = "휴대번호를 입력해주세요";
	} else {
		const element = document.getElementById("mobileCheck");
		element.className = "alert-success"
		element.innerText = "ok";
	}
	
	if(($("#postcode").val() == "") || ($("#address").val() = "")){
	const element = document.getElementById("addressCheck");
		element.className = "alert-danger";
		element.innerText = "주소를 입력해주세요";
	} else {
		const element = document.getElementById("addressCheck");
		element.className = "alert-success"
		element.innerText = "ok";
	}
}


/* 실행시 입력된 data값 유효성검사 */
function updateCheck(){

	const password_reg = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[@$!%*#?&]).{8,16}$/;
	if(!password_reg.test($("#password").val())){
	const element = document.getElementById("passwordCheck");
		element.className = "alert-danger";
		element.innerText = "영문자, 숫자, 특수문자를 포함해주세요 (8~16자)";
	} else {
		const element = document.getElementById("passwordCheck");
		element.className = "alert-success"
		element.innerText = "ok";
	}
	
	const email_reg = /[a-z0-9]+@[a-z]+\.[a-z]{2,3}$/;
	if(!email_reg.test($("#email").val())){
	const element = document.getElementById("emailCheck");
		element.className = "alert-danger";
		element.innerText = "메일 양식에 맞지 않습니다";
	} else {
		const element = document.getElementById("emailCheck");
		element.className = "alert-success"
		element.innerText = "ok";
	}
	
	const mobile_reg = /^01(?:0|1[6-9])([0-9]{3,4})([0-9]{4})/;
	if(!mobile_reg.test($("#mobile").val())){
	const element = document.getElementById("mobileCheck");
		element.className = "alert-danger";
		element.innerText = "휴대번호를 입력해주세요";
	} else {
		const element = document.getElementById("mobileCheck");
		element.className = "alert-success"
		element.innerText = "ok";
	}
	
	if(($("#postcode").val() == "") || ($("#address").val() == "")){
	const element = document.getElementById("addressCheck");
		element.className = "alert-danger";
		element.innerText = "주소를 입력해주세요";
	} else {
		const element = document.getElementById("addressCheck");
		element.className = "alert-success"
		element.innerText = "ok";
	}
}

index.init();