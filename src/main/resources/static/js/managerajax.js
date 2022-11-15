let index = {
	init: function(){
		$("#btn-update").on("click", ()=>{
		this.update_detail();
		});
		$("#btn-del").on("click", ()=>{
		this.del();
		});
	},
	
	update_detail:function(){
		var data = {
		publicid : $("#publicid").val(),
			department : $("#department").val(),
			position : $("#position").val(),
			resign : $("#resign").val()
		}
		
		var header = $("meta[name='_csrf_header']").attr("content");
		var token = $("meta[name='_csrf']").attr("content");
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
				location.href="/deputy/manager/employeelist";
			},
				error: function errorData(data){
				alert(data);
			}
		});
	},
	
	del:function(){
		const header = $("meta[name='_csrf_header']").attr("content");
		const token = $("meta[name='_csrf']").attr("content");
		
		let id = $("#deleteid").val();
		console.log(id);
		$.ajax({
			url: "/deputy/manager/deleteProc/"+id,
			type: "delete",
			contentType: "application/json; charset=utf-8;",
			dataType: "json",
			beforeSend: function (jqXHR, settings) {
            jqXHR.setRequestHeader(header, token);
			},
			success: function delData(){
				alert("삭제");
				location.href="/deputy/manager/employeelist";
			},
			error: function errorData(data){
			alert(data);
			}
		});
	}
		
}
index.init();