function isDate(str)
{
    var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
    if(r==null)return false;
    var d= new Date(r[1], r[3]-1, r[4]);
    return (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]);
}
function dateCompare(startdate, enddate) {
    var arr = startdate.split("-");
    var starttime = new Date(arr[0], arr[1], arr[2]);
    var starttimes = starttime.getTime();
    var arrs = enddate.split("-");
    var lktime = new Date(arrs[0], arrs[1], arrs[2]);
    var lktimes = lktime.getTime();

    if (starttimes >= lktimes) {
        return false;
    }
    else {
        return true;
    }

}

function isNumber(value) {
    if ($.common.isEmpty(value)){
        return false;
    }
    var r = /^\+?[1-9][0-9]*$/;
    return r.test(value);
}
function isEmail(str){
    var re=/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
    return re.test(str);
}