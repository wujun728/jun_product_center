<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/7/18
  Time: 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<template id="page-header">
    <div class="row wrapper border-bottom white-bg page-heading" v-if="titleSeen">
        <div class="col-lg-10">
            <h2>{{pageTitle}}</h2>
            <ol class="breadcrumb" v-for="item in breadcrumbs">
                <li :class="item.class">
                    <a v-if="item.href != ''" :href="item.href">{{item.name}}</a>
                    <strong v-else>{{item.name}}</strong>
                </li>
            </ol>
        </div>
        <div class="col-lg-2"></div>
    </div>
</template>

<script type="text/javascript">
    Vue.component("page-header",{
        template:"#page-header",
        computed:{
            titleSeen:function () {
                return  typeof (this.breadcrumbs) != "undefined";
            }
        },
        props:{
            breadcrumbs:Array,
            pageTitle: String
        },
    })
</script>


