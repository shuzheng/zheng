$(function() {
	// 侧边栏操作按钮
	$('#guide').click(function() {
		$(this).toggleClass('toggled');
		$('#aside').toggleClass('toggled');
	});
	// Waves初始化
	Waves.displayEffect();
});