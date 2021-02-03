<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/7/26
  Time: 19:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<template id="page-ibox" >
    <div class="ibox float-e-margins" v-if="iboxSeen">
        <div class="ibox-title">
            <h5>{{title}}</h5>
            <div class="ibox-tools">
                <a class="collapse-link">
                    <i class="fa fa-chevron-up"></i>
                </a>
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-wrench"></i>
                </a>
                <ul class="dropdown-menu dropdown-user">
                    <li><a href="#">Config option 1</a>
                    </li>
                    </li>
                    <li><a href="#">Config option 2</a>
                    </li>
                </ul>
                <a class="close-link">
                    <i class="fa fa-times"></i>
                </a>
            </div>
        </div>
        <%--<div class="ibox-content" v-html="content">--%>
        <div class="ibox-content">
            <slot></slot>
        </div>
    </div>
</template>


<script type="text/javascript">
    var pageIbox = {
        template:"#page-ibox",
        props:{
            title:String,
            content:String
        },
        computed:{
            iboxSeen :function () {
                return  typeof (this.title) != "undefined" && typeof (this.content) != "undefined";
            }
        }
    };

    //define component
    Vue.component("page-ibox",pageIbox);

</script>
