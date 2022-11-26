//定义serializeObject方法，序列化表单
$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};


/**
 *
 * @param {String} url 需要打开的新的窗口地址
 * @param {String} win 当前父级窗口
 * @param {Object} params 新窗口的参数
 */
function newWindowPage(url, win, params = {}) {
    win = this.window;
    const name = params.name || '';
    const height = params.height || window.screen.height / 2;
    const width = params.width || window.screen.width / 2;
    const position = params.position || 'center';
    const toolbar = params.toolbar || 'no';
    const scrollbars = params.scrollbars || 'no';
    const resizable = params.resizable || 'no';
    const location = params.location || 'no';
    const slatus = params.slatus || 'no';
    const menubar = params.menubar || 'no';
    let top = params.top || null;
    let left = params.left || null;

    /**
     * params对象各个属性注释
     * @param {String} name 弹出窗口的名字（不是文件名），并不是必须的，默认为空，可选。
     * @param {Number} height 弹出窗口的高度，默认为屏幕尺寸高度的一半，可选。
     * @param {Number} width 弹出窗口的宽度，默认为屏幕尺寸宽度的一半，可选。
     * @param {String} position 弹出窗口的位置，默认为屏幕的居中显示，可选。
     * @param {String} toolbar 是否显示工具栏，yes位显示，默认为no，可选。
     * @param {String} scrollbars 是否显示滚动条，yes位显示，默认为no，可选。
     * @param {String} resizable 是否允许改变窗口大小，yes位允许，默认为no，可选。
     * @param {String} location 是否显示地址栏，yes位显示，默认为no，可选。
     * @param {String} slatus 是否显示状态栏的信息（通常是文件已经打开），yes位显示，默认为no，可选。
     * @param {String} menubar 是否显示菜单栏，yes位显示，默认为no，可选。
     * @param {Number} top 窗口距离顶部的位置，默认为null，可选。
     * @param {Number} left 窗口距离左侧的位置，默认为null，可选。
     */


        // 屏幕分辨率的高： window.screen.height
        // 屏幕分辨率的宽： window.screen.width
    const screenHeight = window.screen.height;
    const screenWidth = window.screen.width;

    const halfHeight = height / 2;
    const halfWidth = width / 2;

    const positionObj = {
        "center": {
            top: screenHeight / 2 - halfHeight,
            left: screenWidth / 2 - halfWidth
        },
        "left-top": {
            top: 0,
            left: 0
        },
        "left-center": {
            top: screenHeight / 2 - halfHeight,
            left: 0
        },
        "left-bottom": {
            top: screenHeight - height,
            left: 0
        },
        "right-top": {
            top: 0,
            left: screenWidth - width
        },
        "right-center": {
            top: screenHeight / 2 - halfHeight,
            left: screenWidth - width
        },
        "right-bottom": {
            top: screenHeight - height,
            left: screenWidth - width
        },
        "top-center": {
            top: 0,
            left: screenWidth / 2 - halfWidth
        },
        "bottom-center": {
            top: screenHeight - height,
            left: screenWidth / 2 - halfWidth
        },
        "center-top-half": {
            top: screenHeight / 8,
            left: screenWidth / 2 - halfWidth
        },
        "center-bottom-half": {
            top: screenHeight - screenHeight / 8 - height,
            left: screenWidth / 2 - halfWidth
        }
    };
    if (!top) {
        top = positionObj[position] ? positionObj[position].top : positionObj["center"].top;
    }
    ;

    if (!left) {
        left = positionObj[position] ? positionObj[position].left : positionObj["center"].left;
    }
    ;

    console.log(
        screenHeight,
        screenWidth
    );

    console.log(
        screenHeight,
        screenWidth,
        name,
        height,
        width,
        position,
        positionObj[position],
        toolbar,
        scrollbars,
        resizable,
        location,
        slatus,
        menubar,
        top,
        left,
        url
    );
    return win.open(url, name, `height=${height},width=${width},top=${top},left=${left + win.top.screenLeft},toolbar=${toolbar},scrollbars=${scrollbars},resizable=${resizable},location=${location},slatus=${slatus},menubar=${menubar}`);
}

//获取父页面值
function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (name == 'account') {
        console.log(r)
    }
    if (r != null) return unescape(revertUTF8(r[2]));
    return null;
}

// UTF8编码转成汉字字符串
function revertUTF8(szInput) {
    var x, wch, wch1, wch2, uch = "", szRet = "";
    for (x = 0; x < szInput.length; x++) {
        if (szInput.charAt(x) == "%") {
            wch = parseInt(szInput.charAt(++x) + szInput.charAt(++x), 16);
            if (!wch) {
                break;
            }
            if (!(wch & 0x80)) {
                wch = wch;
            } else if (!(wch & 0x20)) {
                x++;
                wch1 = parseInt(szInput.charAt(++x) + szInput.charAt(++x), 16);
                wch = (wch & 0x1F) << 6;
                wch1 = wch1 & 0x3F;
                wch = wch + wch1;
            } else {
                x++;
                wch1 = parseInt(szInput.charAt(++x) + szInput.charAt(++x), 16);
                x++;
                wch2 = parseInt(szInput.charAt(++x) + szInput.charAt(++x), 16);
                wch = (wch & 0x0F) << 12;
                wch1 = (wch1 & 0x3F) << 6;
                wch2 = (wch2 & 0x3F);
                wch = wch + wch1 + wch2;
            }
            szRet += String.fromCharCode(wch);
        } else {
            szRet += szInput.charAt(x);
        }
    }
    return (szRet);
}