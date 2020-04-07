function searchByPara(){
	var account = $("#account").val();
	window.location.href = "/cloudmonitor/log/list?account="+escape(escape(account));
}

function view(id) {
	window.location.href = "/cloudmonitor/log/view?id="+id;
}

function cancel(){
	history.back();
}
