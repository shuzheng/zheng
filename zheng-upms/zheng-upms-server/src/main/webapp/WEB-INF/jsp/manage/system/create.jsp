<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<div id="createDialog" class="crudDialog">
	<form id="createForm" method="post">
		<div class="form-group">
			<label for="theme"></label>
			<input id="theme" type="color" class="form-control" name="theme" maxlength="50">
		</div>
		<div class="form-group">
			<label for="icon">图标</label>
			<input id="icon" type="text" class="form-control" name="icon" maxlength="20">
		</div>
		<div class="form-group">
			<label for="title">标题</label>
			<input id="title" type="text" class="form-control" name="title" maxlength="20" required="true">
		</div>
		<div class="form-group">
			<label for="name">名称</label>
			<input id="name" type="text" class="form-control" name="name" maxlength="20" required="true">
		</div>
		<div class="row">
			<div class="col-lg-8 form-group">
				<label for="name">背景图</label>
				<input id="banner" type="text" class="form-control" name="banner" maxlength="150">
			</div>
			<div class="col-lg-4">
				<div id="picker">上传背景图</div>
			</div>
		</div>
		<div class="form-group">
			<label for="description">描述</label>
			<input id="description" type="text" class="form-control" name="description" maxlength="300">
		</div>
		<div class="form-group">
			<label for="basepath">根目录</label>
			<input id="basepath" type="text" class="form-control" name="basepath" maxlength="100">
		</div>
		<div class="radio">
			<div class="radio radio-inline radio-success">
				<input id="status_1" type="radio" name="status" value="1" checked>
				<label for="status_1">正常 </label>
			</div>
			<div class="radio radio-inline">
				<input id="status_0" type="radio" name="status" value="-1">
				<label for="status_0">锁定 </label>
			</div>
		</div>
		<div class="form-group text-right dialog-buttons">
			<a class="waves-effect waves-button" href="javascript:;" onclick="createSubmit();">保存</a>
			<a class="waves-effect waves-button" href="javascript:;" onclick="createDialog.close();">取消</a>
		</div>
	</form>
</div>
<script>
function initUploader() {
    //百度上传按钮
    var uploader = WebUploader.create({
        // swf文件路径
        swf: '${basePath}/resources/zheng-admin/plugins/webuploader-0.1.5/Uploader.swf',
        // 文件接收服务端
        method: 'POST',
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: {
            "id": '#picker',
            "multiple": false
        },
        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        resize: false,
        // 选完文件后，是否自动上传。
        auto: false,
        // 只允许选择图片文件
        accept: {
            title: '图片文件',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        }
    });
    uploader.on( 'fileQueued', function(file) {
        $.ajax({
            url: '${ZHENG_OSS_ALIYUN_OSS_POLICY}',
            type: 'GET',
            dataType: 'jsonp',
            jsonp: 'callback',
            success: function(result) {
                var suffix = get_suffix(file.name);
                var random_name = random_string();
                uploader.options.formData.key = result.dir + '/' + random_name + suffix;
                uploader.options.formData.policy = result.policy;
                uploader.options.formData.OSSAccessKeyId = result.OSSAccessKeyId;
                uploader.options.formData.success_action_status = 200;
                uploader.options.formData.callback = result.callback;
                uploader.options.formData.signature = result.signature;
                uploader.options.server = result.action;
                uploader.upload();
            },
            error: function(msg) {
                console.log(msg);
            }
        });
    });
    uploader.on( 'uploadSuccess', function(file, response) {
        $('#banner').val(response.data.filename).focus();
    });
    uploader.on( 'uploadError', function(file) {
        console.log('uploadError', file);
    });
}
// 根据路径获取后缀
function get_suffix(filename) {
    var pos = filename.lastIndexOf('.');
    var suffix = '';
    if (pos != -1) {
        suffix = filename.substring(pos);
    }
    return suffix;
}
// 随机字符串
function random_string(len) {
    len = len || 32;
    var chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    var maxPos = chars.length;
    var pwd = '';
    for (i = 0; i < len; i++) {
        pwd += chars.charAt(Math.floor(Math.random() * maxPos));
    }
    return pwd;
}
function createSubmit() {
    $.ajax({
        type: 'post',
        url: '${basePath}/manage/system/create',
        data: $('#createForm').serialize(),
        beforeSend: function() {
            if ($('#title').val() == '') {
                $('#title').focus();
                return false;
            }
            if ($('#name').val() == '') {
                $('#name').focus();
                return false;
            }
        },
        success: function(result) {
			if (result.code != 1) {
				if (result.data instanceof Array) {
					$.each(result.data, function(index, value) {
						$.confirm({
							theme: 'dark',
							animation: 'rotateX',
							closeAnimation: 'rotateX',
							title: false,
							content: value.errorMsg,
							buttons: {
								confirm: {
									text: '确认',
									btnClass: 'waves-effect waves-button waves-light'
								}
							}
						});
					});
				} else {
						$.confirm({
							theme: 'dark',
							animation: 'rotateX',
							closeAnimation: 'rotateX',
							title: false,
							content: result.data.errorMsg,
							buttons: {
								confirm: {
									text: '确认',
									btnClass: 'waves-effect waves-button waves-light'
								}
							}
						});
				}
			} else {
				createDialog.close();
				$table.bootstrapTable('refresh');
			}
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
			$.confirm({
				theme: 'dark',
				animation: 'rotateX',
				closeAnimation: 'rotateX',
				title: false,
				content: textStatus,
				buttons: {
					confirm: {
						text: '确认',
						btnClass: 'waves-effect waves-button waves-light'
					}
				}
			});
        }
    });
}
</script>