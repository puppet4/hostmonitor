
function add() {
	window.location.href = "/cloudmonitor/heathMonitor/edit";
}

function view(id) {
	window.location.href = "/cloudmonitor/heathMonitor/view?id="+id;
}

function del(id) {
	if(confirm('你确定要删除吗？')) {
		window.location.href = "/cloudmonitor/heathMonitor/del?id=" + id;
	}
}
function edit(id){
	window.location.href = "/cloudmonitor/heathMonitor/edit?id="+id;
}

function cancel(){
	history.back();
}
