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
});