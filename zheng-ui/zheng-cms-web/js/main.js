/* 兼容 */
if (navigator.userAgent.match(/IEMobile\/10\.0/)) {
  var msViewportStyle = document.createElement('style');
  msViewportStyle.appendChild(
    document.createTextNode(
      '@-ms-viewport{width:auto!important}'
    )
  );
  document.querySelector('head').appendChild(msViewportStyle);
}

$(function () {
  var nua = navigator.userAgent;
  var isAndroid = (nua.indexOf('Mozilla/5.0') > -1 && nua.indexOf('Android ') > -1 && nua.indexOf('AppleWebKit') > -1 && nua.indexOf('Chrome') === -1);
  if (isAndroid) {
    $('select.form-control').removeClass('form-control').css('width', '100%');
  }
  
});
// 下拉菜单
/*
$('.dropdown').mouseover(function(){
   $('.dropdown').addClass("open");
});
$('.dropdown').mouseleave(function(){
   $('.dropdown').removeClass("open");
});
$('.dropdown-menu').mouseleave(function(){
   $('.dropdown-menu').slideUp();
});
*/