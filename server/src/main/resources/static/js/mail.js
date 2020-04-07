
function view(id) {
    window.location.href = "/cloudmonitor/appInfo/view?id="+id;
}

function del(id) {
    window.location.href = "/cloudmonitor/appInfo/del?id="+id;
}

function cancel(){
    history.back();
}