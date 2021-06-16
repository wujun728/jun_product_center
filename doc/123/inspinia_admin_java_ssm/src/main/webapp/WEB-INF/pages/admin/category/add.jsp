<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/3/14
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<button data-target="#addModel" data-toggle="modal" class="btn btn-primary">增加</button>
<form method="post" action="/admin/category/add" enctype="multipart/form-data">
    <div class="modal fade" id="addModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">增加分类</h4>
                </div>
                <div class="modal-body">
                    <div class="row form-group">
                        <div class="input-group">
                            <span class="input-group-addon" >分类名称</span>
                            <input class="form-control" type="text" id="name" name="name">
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="input-group">
                            <span class="input-group-addon">分类图片</span>
                            <input type="file" id="categoryImage" name="image" accept="image/*" >
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary">增加</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</form>

