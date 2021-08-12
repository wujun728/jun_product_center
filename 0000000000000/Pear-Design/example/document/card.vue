<template>
    <div>
        <h3>基础</h3>
        <p-card>内容</p-card>
        <h3>标题</h3>
        <p-card title="标题">内容</p-card>
        <h3>阴影</h3>
        <p-card title="标题" shadow>内容</p-card>
        <h3>主题</h3>
        <p-row space="10">
            <p-col span="8">
                <p-card type="primary" title="标题" shadow>内容</p-card>
            </p-col>
             <p-col span="8">
                <p-card type="warming" title="标题" shadow>内容</p-card>
            </p-col>
             <p-col span="8">
                <p-card type="failure" title="标题" shadow>内容</p-card>
            </p-col>
        </p-row>
        <h3>组合</h3>
        <p-row space="10">
            <p-col span="6">
                <p-card type="primary" title="标题" shadow>内容</p-card>
            </p-col>
             <p-col span="6">
                <p-card type="primary" title="标题" shadow>内容</p-card>
            </p-col>
             <p-col span="6">
                <p-card type="primary" title="标题" shadow>内容</p-card>
            </p-col>
             <p-col span="6">
                <p-card type="primary" title="标题" shadow>内容</p-card>
            </p-col>
             <p-col span="12">
                <p-card title="标题" shadow>内容</p-card>
            </p-col>
             <p-col span="12">
                <p-card title="标题" shadow>内容</p-card>
            </p-col>
        </p-row>
    </div>
</template>
<style>
h3{
  color: rgba(0, 0, 0, 0.65);
}
</style>