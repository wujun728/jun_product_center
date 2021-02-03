<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/3/23
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form method="post" action="/admin/category/update" enctype="multipart/form-data">
    <div class="modal fade" id="edit_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <input type="hidden" id="edit_id" name="id"/>
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">修改分类</h4>
                </div>
                <div class="modal-body">
                    <div class="row form-group">
                        <div class="input-group">
                            <span class="input-group-addon">分类名称</span>
                            <input class="form-control" type="text" id="edit_name" name="name">
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="input-group">
                            <span class="input-group-addon">分类图片</span>
                            <span  class="pie">
                                <img class="img-rounded img-md" src="" id="edit_image"/>
                            </span>
                            <input type="file" accept="image/*" id="edit_upload" name="image" style="display: none;"/>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary" id="edit_button">保存</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</form>