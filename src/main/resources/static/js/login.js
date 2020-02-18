function Overlay() {
    // 调用父窗口显示遮罩层和Loading提示
    window.top.window.showOverlay();
}

function Modal() {
    // 调用父窗口方法模拟弹出模态窗口
    window.top.showModal("loginWin");
}

function loginWin() {
    Overlay();
    Modal();
}

function rightIFrameLoad(iframe) {
    var pHeight =   getWindowInnerHeight() - iframe.height() - 5;
    alert(pHeight);
    $('loginWin').height(pHeight);
}

// 浏览器兼容 取得浏览器可视区高度
function getWindowInnerHeight() {
    var winHeight = window.innerHeight
        || (document.documentElement && document.documentElement.clientHeight)
        || (document.body && document.body.clientHeight);
    return winHeight;

}

// 浏览器兼容 取得浏览器可视区宽度
function getWindowInnerWidth() {
    var winWidth = window.innerWidth
        || (document.documentElement && document.documentElement.clientWidth)
        || (document.body && document.body.clientWidth);
    return winWidth;

}

/**
 * 显示遮罩层
 */
function showOverlay() {
    // 遮罩层宽高分别为页面内容的宽高
    $('.overlay').css({'height':$(document).height(),'width':$(document).width()});
    $('.overlay').show();
}

/**
 * 模拟弹出模态窗口DIV
 * @param innerHtml 模态窗口HTML内容
 */
function showModal(innerHtml) {
    // 取得显示模拟模态窗口用DIV
    var dialog = $('#modalDiv');

    // 设置内容
    dialog.attr('src', innerHtml);

    // 模态窗口DIV窗口居中
    dialog.css({
        'top' : (getWindowInnerHeight() - dialog.height() - 100) / 2 + 'px',
        'left' : (getWindowInnerWidth() - dialog.width() - 200) / 2 + 'px'
    });    //TODO 获取更新后的子页面的宽高进行定位

    // 窗口DIV圆角
    dialog.find('.modal-container').css('border-radius','6px');

    //模态窗口关闭按钮事件
    // dialog.find('.btn-close').click(function(){
    //     closeModal();
    // });
    // iFrameResize();

    // 显示遮罩层
    showOverlay();

    // 显示遮罩层
    dialog.show();
}

/**
 * 模拟关闭模态窗口DIV
 */
function closeModal() {
    $('.overlay').hide();
    $('#modalDiv').hide();
}


function iFrameResize(){
    var obj = parent.document.getElementById("modalDiv"); //取得父页面IFrame对象
    $(obj).height(this.document.body.scrollHeight + 30);
    $(obj).width(this.document.body.scrollWidth + 30);
}


function changeToRegisterWin() {
    var obj = parent.document.getElementById("modalDiv");
    $(obj).attr('src', 'registerWin');
    $(obj).height(this.document.body.scrollHeight);
    $(obj).width(this.document.body.scrollWidth);
}

function changeToLogin() {
    var obj = parent.document.getElementById("modalDiv");
    $(obj).attr('src', 'loginWin');
    $(obj).height(this.document.body.scrollHeight);
    $(obj).width(this.document.body.scrollWidth);
}

function logout() {
    var form = new FormData();
    form.append("userId", getCookie("userId"));
    form.append("token", getCookie("token"));

    var settings = {
        "url": "user/logout",
        "method": "POST",
        "processData": false,
        "mimeType": "multipart/form-data",
        "contentType": false,
        "data": form
    };

    $.ajax(settings).done(function (response) {
        console.log(response);
        top.location.href = "first_page";
    });

}


//读取cookies
function getCookie(name){
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg)) {
        return unescape(arr[2]);
    }else
        return null;
}