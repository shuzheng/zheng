<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<div id="updateDialog" class="crudDialog">
	<form id="updateForm" method="post">
		<input type="hidden" name="userId" value="1">
		<div class="row">
			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="title">标题</label>
						<input id="title" type="text" class="form-control" name="title" maxlength="200" value="${article.title}">
					</div>
				</div>
			</div>
			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="description">描述</label>
						<input id="description" type="text" class="form-control" name="description" maxlength="500" value="${article.description}">
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<div class="fg-line">
						<label for="author">作者</label>
						<input id="author" type="text" class="form-control" name="author" maxlength="20" value="${article.author}">
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<div class="fg-line">
						<label for="fromurl">来源</label>
						<input id="fromurl" type="text" class="form-control" name="fromurl" maxlength="300" value="${article.fromurl}">
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<div class="fg-line">
						<label for="keywords">关键字</label>
						<input id="keywords" type="text" class="form-control" name="keywords" maxlength="100" value="${article.keywords}">
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<div class="fg-line">
						<select id="topicId" name="topicId" style="width: 100%">
							<option value="0">所属专题</option>
							<c:forEach var="cmsTopic" items="${cmsTopics}">
								<option value="${cmsTopic.topicId}" <c:if test="${article.topicId==cmsTopic.topicId}">selected</c:if>>${cmsTopic.title}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<div class="fg-line">
						<label for="image">封面图</label>
						<input id="image" type="text" class="form-control" name="image" maxlength="300" value="${article.image}">
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<div class="fg-line">
						<label for="systemId">所属系统</label>
						<input id="systemId" type="text" class="form-control" name="systemId" value="${article.systemId}">
					</div>
				</div>
			</div>
			<div class="col-sm-12">
				<div class="form-group">
					<div class="fg-line">
						<label for="content">内容</label>
						<input id="content" type="text" class="form-control" name="content" value="${article.content}">
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<label>类型</label>
				<div class="radio">
					<div class="radio radio-inline radio-info">
						<input id="type_1" type="radio" name="type" value="1" <c:if test="${article.type==1}">checked</c:if>>
						<label for="type_1">普通 </label>
					</div>
					<div class="radio radio-inline radio-danger">
						<input id="type_2" type="radio" name="type" value="2" <c:if test="${article.type==2}">checked</c:if>>
						<label for="type_2">热门 </label>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<label>评论</label>
				<div class="radio">
					<div class="radio radio-inline radio-info">
						<input id="allowcomments_1" type="radio" name="allowcomments" value="1" <c:if test="${article.allowcomments==1}">checked</c:if>>
						<label for="allowcomments_1">允许 </label>
					</div>
					<div class="radio radio-inline radio-danger">
						<input id="allowcomments_0" type="radio" name="allowcomments" value="0" <c:if test="${article.allowcomments==0}">checked</c:if>>
						<label for="allowcomments_0">不允许 </label>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<label>状态</label>
				<div class="radio">
					<div class="radio radio-inline radio-danger">
						<input id="status_-1" type="radio" name="status" value="-1" <c:if test="${article.status==-1}">checked</c:if>>
						<label for="status_-1">不通过 </label>
					</div>
					<div class="radio radio-inline radio-info">
						<input id="status_0" type="radio" name="status" value="0" <c:if test="${article.status==0}">checked</c:if>>
						<label for="status_0">待审核 </label>
					</div>
					<div class="radio radio-inline radio-success">
						<input id="status_1" type="radio" name="status" value="1" <c:if test="${article.status==1}">checked</c:if>>
						<label for="status_1">已通过 </label>
					</div>
				</div>
			</div>
		</div>

		<div class="form-group text-right dialog-buttons">
			<a class="waves-effect waves-button" href="javascript:;" onclick="updateSubmit();">保存</a>
			<a class="waves-effect waves-button" href="javascript:;" onclick="updateDialog.close();">取消</a>
		</div>
	</form>
</div>
<script>
function updateSubmit() {
    $.ajax({
        type: 'post',
        url: '${basePath}/manage/article/update/${article.articleId}',
        data: $('#updateForm').serialize(),
        beforeSend: function() {
			if ($('#title').val() == '') {
				$('#title').focus();
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
				updateDialog.close();
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