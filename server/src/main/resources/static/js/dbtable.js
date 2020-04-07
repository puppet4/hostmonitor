
function view(id) {
	window.location.href = "/cloudmonitor/dbTable/edit?id="+id;
}


function add() {
	window.location.href = "/cloudmonitor/dbTable/edit";
}

function del(id) {
	if(confirm('你确定要删除吗？')) {
		window.location.href = "/cloudmonitor/dbTable/del?id=" + id;
	}
}
function viewChart(id){
	window.location.href = "/cloudmonitor/dbTable/viewChart?id="+id;
}

function cancel(){
	history.back();
}
