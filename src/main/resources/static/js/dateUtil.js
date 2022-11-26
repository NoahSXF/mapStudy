function getMyDate(str) {
    var oDate = new Date(str),
        oYear = oDate.getFullYear(),
        oMonth = oDate.getMonth() + 1,
        oDay = oDate.getDate(),
        oHour = oDate.getHours(),
        oMin = oDate.getMinutes(),
        oSen = oDate.getSeconds(),
        oTime = oYear + '-' + addZero(oMonth) + '-' + addZero(oDay) + ' ' + addZero(oHour) + ':' +
            addZero(oMin) + ':' + addZero(oSen);
    return oTime;
}

//补零操作
function addZero(num) {
    if (parseInt(num) < 10) {
        num = '0' + num;
    }
    return num;
}
