Array.prototype.shuffle = function () {
    let arr = this,
        temp,
        length = arr.length;
    for (let i = 0; i < length - 1; i++) {
        let index = Math.floor(Math.random() * (length--));
        temp = arr[index];
        arr[index] = arr[length];
        arr[length] = temp;
    }
    return arr;
};

// 字符数组，去掉了I,i,L,l,1,O,o,0,#,%等视觉识别歧义的字符
var signArray = [
    ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'k', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y'],
    ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y'],
    ['2', '3', '4', '5', '6', '7', '8', '9'],
    ['!', '$', '*', '.']
];

/**
 * 长度8-12位
 * 包含大小写字母，数字，字符至少一个
 */
function generate() {
    var pwd = [];
    //随机生成密码长度8-16
    var len = randomNum(12, 16);
    //循环密码长度
    for (var i = 0; i < len; i++) {
        var arrayIndex = i % signArray.length;
        var tmpArray = signArray[arrayIndex];
        var signIndex = Math.floor(Math.random() * tmpArray.length);
        pwd.push(tmpArray[signIndex]);
    }
    pwd.shuffle();
    $("#password").val(pwd.join(""))
}

function randomNum(min, max) {
    return Math.floor(Math.random() * (max - min + 1) + min);
}