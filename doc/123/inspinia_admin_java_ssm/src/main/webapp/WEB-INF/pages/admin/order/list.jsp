<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/7/17
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../shared/base-layout.jsp" %>
<%@include file="../shared/page-ibok.jsp" %>
<%--本页所需资源--%>
<link href="${pageContext.request.contextPath}/vendor/css/plugins/dataTables/datatables.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/vendor/js/plugins/dataTables/datatables.min.js"></script>
<%--正文--%>
<div id="_wrapper">
    <base-layout>
        <page-ibox title="订单管理" content="table content">
            <div class="form-horizontal">
                <div class="row">
                    <div class="form-group col-md-2 col-sm-10" style="margin-left: 0px;">
                        <div class="input-prepend input-group">
                            <span class="add-on input-group-addon"><i class="fa fa-id-card-o"></i>ID</span>
                            <input id="ID" name="ID"  type="text"  class="form-control" value="" />
                        </div>
                    </div>
                    <div class="btn btn-primary" style="margin-left: 10px;" v-on:click="">查询</div>
                </div>
            </div>
            <table class="dataTables table table-striped table-bordered table-hover dataTables-example">
                <thead>
                    <th>Option</th>
                    <th>serialNum</th>
                    <th>ID</th>
                    <th>Status</th>
                    <th>Price</th>
                    <th>Num</th>
                    <th>UserName</th>
                    <th>CreateTime</th>
                    <th>PayTime</th>
                    <th>DeliverTime</th>
                </thead>
            </table>
        </page-ibox>
    </base-layout>
</div>
<script>
    var app = new Vue({
        el: "#_wrapper",
        data: {
            dt_searchId:''
        },
        methods: {
            tableSearch: function (event) {
                var id =0;
            }
        },
        mounted:function () {
            this.$nextTick(function () {
                //load main table
                init_datatables();
            });
        }
    })

    //初始化表格相关
    function init_datatables() {
        $.fn.dtconfig.ajax = {
            url: "/admin/order/ajaxList",
            type: "post",
        }
        //ajax 请求返回为复杂对象，故需要设置列详情
        $.fn.dtconfig.columns = [
            {data:null,name:"option",class:"details-control"},
            {data:null,name:"serialNum"},
            {data:"id",name:"id"},
            {data:"statusDesc",name:"status",},
            {data:"totalNumber",name:"price"},
            {data:"total",name:"num"},
            {data:"user.name",name:"userName"},
            {data:"createDate",name:"createTime"},
            {data:"payDate",name:"payTime"},
            {data:"deliveryDate",name:"deliverTime"}//,defaultContent:"/" defaultContent 可以处理undefined 及null
        ];
        $.fn.dtconfig.columnDefs = [{
            targets:[7,8,9],
            render:function (data,type,row,meta) {
                if(data != undefined) {
                    return (new Date(data).Format("yyyy-MM-dd hh:mm:ss"));
                } else {
                  return '/';
                }
            }
        },{
            targets:[0],
            render:function (data,type,row,meta) {
                return "<a title='详情' class='details-control'><span style='margin-right: 5px;'><i class=\"fa fa-plus-circle\" aria-hidden=\"true\"></i></span></a><a title='发货'><span><i class=\"fa fa-paper-plane\" aria-hidden=\"true\"></i></span></a>"
            }
        }];
        //init
        var page_dt = $('.dataTables').DataTable($.fn.dtconfig);
        //row numbers
        page_dt.on('draw.dt',function () {
            page_dt.column(1, { search: 'applied', order: 'applied' }).nodes().each(function (cell, i) {
                cell.innerHTML = i + 1;
            });
        });

        //column detail //设置行详情相关代码
        //设置子表格输出格式
        function dealOrderItemsData(data) {
            var orderItems = data.orderItems;
            var html = '<table class=\'table table-hover\'>' +
                            '<thead>' +
                                '<tr>' +
                                    '<th>Name</td>' +
                                    '<th>SubTitle</td>' +
                                    '<th>PromotePrice</td>' +
                                    '<th>Stock</td>' +
                                '</tr>' +
                            '</thead>' + '{tbody}'
                '</table>';
            var tbody = '';
            if(Array.isArray(orderItems)) {
                for(var i in orderItems) {
                    tbody += '<tr>' +
                                '<td>' + orderItems[i].product.name + '</td>' +
                                '<td>' + orderItems[i].product.subTitle + '</td>' +
                                '<td>' + orderItems[i].product.promotePrice +'</td>' +
                                '<td>' + orderItems[i].product.stock + '</td>' +
                            '</tr>';
                }
            }
            return html.replace('{tbody}',tbody);
        }

        //设置子表格展开，收起事件
        var detailRows = [];
        $('.dataTables tbody').on('click','tr td .details-control',function () {
           var tr = $(this).closest("tr");
           var row = page_dt.row(tr);
           var index =  $.inArray(tr.attr('id'),detailRows);

           if(row.child.isShown()) {
               tr.find('i').eq(0).attr('class','fa fa-plus-circle');
               row.child.hide();
               //Remove from the 'open' array
               detailRows.splice(index,1);
           } else {
               tr.find('i').eq(0).attr('class','fa fa-minus-circle');
               var innerHtml =  dealOrderItemsData(row.data());
               row.child(innerHtml).show();

               if(index === -1) {
                   detailRows.push(tr.attr('id'));
               }
           }
        });

        //设置page_dt 为全局变量
        $.page_dt = page_dt;
    }
</script>