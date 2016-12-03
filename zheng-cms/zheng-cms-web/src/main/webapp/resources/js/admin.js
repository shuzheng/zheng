$(function() {
	// 侧边栏操作按钮
	$('#guide').click(function() {
		$(this).toggleClass('toggled');
		$('#sidebar').toggleClass('toggled');
	});
	// 侧边栏二级菜单
	$('.sub-menu a').click(function() {
		$(this).next().slideToggle(200);
		$(this).parent().toggleClass('toggled');
	});
	// 个人资料
	$('.s-profile a').click(function() {
		$(this).next().slideToggle(200);
		$(this).parent().toggleClass('toggled');
	});
	// Waves初始化
	Waves.displayEffect();
	// 滚动条初始化
	$('#sidebar').mCustomScrollbar({
		theme: 'minimal-dark',
		scrollInertia: 100,
		axis: 'yx',
		mouseWheel: {
			enable: true,
			axis: 'y',
			preventDefault: true
		}
	});
	// 选项卡
	$(document).on('click', '.content_tab li', function() {
		$('.content_tab li').removeClass('cur');
		$(this).addClass('cur');
	});
	// 控制选项卡滚动位置 
	$('.tab_left>a').click(function() {
		$('.content_tab>ul').animate({scrollLeft: $('.content_tab>ul').scrollLeft() - 300}, 200);
		if ($('.content_tab>ul').scrollLeft() == 0) {
			$('.tab_left>a').removeClass('active');
		} else {
			$('.tab_left>a').addClass('active');
		}
	});
	$('.tab_right>a').click(function() {
		$('.content_tab>ul').animate({scrollLeft: $('.content_tab>ul').scrollLeft() + 300}, 200);
		if ($('.content_tab>ul').scrollLeft() == 0) {
			$('.tab_left>a').removeClass('active');
		} else {
			$('.tab_left>a').addClass('active');
		}
	});
	// 初始化
});
var tab = {
	addTab: function(title, url) {
		$('.content_tab li').removeClass('cur');
		var tab = '<li class="cur"><a class="waves-effect waves-light" href="javascript:;">' + title + '</a></li>';
		$('.content_tab>ul').append(tab);
	}
}