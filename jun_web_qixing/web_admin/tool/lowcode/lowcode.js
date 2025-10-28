layui.use(['layer', 'form', 'jquery'], function() {
    var $ = layui.jquery;
    var layer = layui.layer;
    var form = layui.form;
    
    // 全局变量
    var currentPage = {
        title: '新页面',
        components: [],
        id: new Date().getTime()
    };
    var selectedComponentId = null;
    var componentCounter = 0;
    
    // 初始化页面
    function init() {
        initDragAndDrop();
        initEventListeners();
    }
    
    // 初始化拖拽功能
    function initDragAndDrop() {
        // 拖拽源
        $('.component-item').on('dragstart', function(e) {
            e.originalEvent.dataTransfer.setData('text/plain', $(this).attr('data-type'));
            $(this).css('opacity', '0.5');
        });
        
        $('.component-item').on('dragend', function() {
            $(this).css('opacity', '1');
        });
        
        // 拖拽目标
        var canvas = $('#designCanvas');
        canvas.on('dragover', function(e) {
            e.preventDefault();
            canvas.addClass('drag-over');
        });
        
        canvas.on('dragleave', function() {
            canvas.removeClass('drag-over');
        });
        
        canvas.on('drop', function(e) {
            e.preventDefault();
            canvas.removeClass('drag-over');
            
            var componentType = e.originalEvent.dataTransfer.getData('text/plain');
            addComponent(componentType);
            
            // 移除空状态提示
            if (currentPage.components.length > 0) {
                $('.canvas-empty').hide();
            }
        });
    }
    
    // 初始化事件监听
    function initEventListeners() {
        // 页面标题更改
        $('#pageTitle').on('input', function() {
            currentPage.title = $(this).val();
        });
        
        // 保存页面
        $('#savePage').on('click', savePage);
        
        // 预览页面
        $('#previewPage').on('click', previewPage);
        
        // 生成代码
        $('#generateCode').on('click', generateCode);
        
        // 主题切换
        $('#themeSwitch').on('click', toggleTheme);
        
        // 设计区域点击事件
        $('#designCanvas').on('click', '.component-preview', function(e) {
            e.stopPropagation();
            selectComponent($(this).attr('data-id'));
        });
        
        // 点击设计区域空白处取消选择
        $('#designCanvas').on('click', function(e) {
            if (!$(e.target).closest('.component-preview').length) {
                deselectComponent();
            }
        });
    }
    
    // 添加组件
    function addComponent(type) {
        var component = {
            id: 'component_' + (componentCounter++),
            type: type,
            properties: getDefaultProperties(type),
            style: getDefaultStyle(type)
        };
        
        currentPage.components.push(component);
        renderComponent(component);
        selectComponent(component.id);
        
        return component;
    }
    
    // 获取组件默认属性
    function getDefaultProperties(type) {
        var properties = {
            id: '',
            name: '',
            value: '',
            placeholder: '',
            label: type.charAt(0).toUpperCase() + type.slice(1)
        };
        
        switch(type) {
            case 'button':
                properties.text = '按钮';
                properties.type = 'primary';
                properties.size = 'default';
                break;
            case 'input':
                properties.placeholder = '请输入...';
                properties.maxlength = '';
                properties.required = false;
                break;
            case 'select':
                properties.options = [{value: '1', label: '选项1'}, {value: '2', label: '选项2'}];
                properties.multiple = false;
                break;
            case 'table':
                properties.columns = [{field: 'id', title: 'ID'}, {field: 'name', title: '名称'}];
                properties.data = [{id: 1, name: '测试数据'}];
                properties.page = true;
                break;
        }
        
        return properties;
    }
    
    // 获取组件默认样式
    function getDefaultStyle(type) {
        var style = {
            width: '100%',
            height: 'auto',
            marginBottom: '10px'
        };
        
        switch(type) {
            case 'button':
                style.width = 'auto';
                break;
            case 'table':
                style.height = '400px';
                break;
        }
        
        return style;
    }
    
    // 渲染组件
    function renderComponent(component) {
        var canvas = $('#designCanvas');
        var html = generateComponentHTML(component);
        
        // 检查是否已存在该组件
        var existingComponent = canvas.find('.component-preview[data-id="' + component.id + '"]');
        if (existingComponent.length > 0) {
            existingComponent.replaceWith(html);
        } else {
            canvas.append(html);
        }
        
        // 如果是选中状态，添加选中样式
        if (selectedComponentId === component.id) {
            canvas.find('.component-preview[data-id="' + component.id + '"]').addClass('component-selected');
        }
    }
    
    // 生成组件HTML
    function generateComponentHTML(component) {
        var styleStr = Object.keys(component.style).map(key => 
            key.replace(/([A-Z])/g, '-$1').toLowerCase() + ': ' + component.style[key] + ';'
        ).join('');
        
        var html = '';
        
        switch(component.type) {
            case 'button':
                html = `<div class="component-preview" data-id="${component.id}" style="${styleStr}">
                    <button class="layui-btn layui-btn-${component.properties.type || 'primary'}">
                        ${component.properties.text || '按钮'}
                    </button>
                </div>`;
                break;
            case 'input':
                html = `<div class="component-preview" data-id="${component.id}" style="${styleStr}">
                    <div class="layui-form-item">
                        <label class="layui-form-label">${component.properties.label || '输入框'}</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input" placeholder="${component.properties.placeholder || '请输入...'}">
                        </div>
                    </div>
                </div>`;
                break;
            case 'text':
                html = `<div class="component-preview" data-id="${component.id}" style="${styleStr}">
                    <p>${component.properties.label || '文本内容'}</p>
                </div>`;
                break;
            case 'select':
                var optionsHtml = component.properties.options.map(opt => 
                    `<option value="${opt.value}">${opt.label}</option>`
                ).join('');
                html = `<div class="component-preview" data-id="${component.id}" style="${styleStr}">
                    <div class="layui-form-item">
                        <label class="layui-form-label">${component.properties.label || '下拉选择'}</label>
                        <div class="layui-input-block">
                            <select class="layui-select" ${component.properties.multiple ? 'multiple' : ''}>
                                ${optionsHtml}
                            </select>
                        </div>
                    </div>
                </div>`;
                break;
            case 'checkbox':
                html = `<div class="component-preview" data-id="${component.id}" style="${styleStr}">
                    <div class="layui-form-item">
                        <label class="layui-form-label">${component.properties.label || '复选框'}</label>
                        <div class="layui-input-block">
                            <input type="checkbox" name="checkbox" title="选项1">
                            <input type="checkbox" name="checkbox" title="选项2">
                        </div>
                    </div>
                </div>`;
                break;
            case 'radio':
                html = `<div class="component-preview" data-id="${component.id}" style="${styleStr}">
                    <div class="layui-form-item">
                        <label class="layui-form-label">${component.properties.label || '单选框'}</label>
                        <div class="layui-input-block">
                            <input type="radio" name="radio" value="1" title="选项1" checked>
                            <input type="radio" name="radio" value="2" title="选项2">
                        </div>
                    </div>
                </div>`;
                break;
            case 'grid':
                html = `<div class="component-preview" data-id="${component.id}" style="${styleStr}">
                    <div class="layui-row">
                        <div class="layui-col-md6">
                            <div class="layui-bg-gray" style="padding: 10px;">左侧列</div>
                        </div>
                        <div class="layui-col-md6">
                            <div class="layui-bg-gray" style="padding: 10px;">右侧列</div>
                        </div>
                    </div>
                </div>`;
                break;
            case 'card':
                html = `<div class="component-preview" data-id="${component.id}" style="${styleStr}">
                    <div class="layui-card">
                        <div class="layui-card-header">${component.properties.label || '卡片标题'}</div>
                        <div class="layui-card-body">卡片内容</div>
                    </div>
                </div>`;
                break;
            case 'tabs':
                html = `<div class="component-preview" data-id="${component.id}" style="${styleStr}">
                    <div class="layui-tab">
                        <ul class="layui-tab-title">
                            <li class="layui-this">标签一</li>
                            <li>标签二</li>
                        </ul>
                        <div class="layui-tab-content">
                            <div class="layui-tab-item layui-show">内容一</div>
                            <div class="layui-tab-item">内容二</div>
                        </div>
                    </div>
                </div>`;
                break;
            case 'table':
                var columnsHtml = component.properties.columns.map(col => 
                    `<th>${col.title}</th>`
                ).join('');
                var dataHtml = component.properties.data.map(row => {
                    var cellsHtml = component.properties.columns.map(col => 
                        `<td>${row[col.field] || ''}</td>`
                    ).join('');
                    return `<tr>${cellsHtml}</tr>`;
                }).join('');
                html = `<div class="component-preview" data-id="${component.id}" style="${styleStr}">
                    <table class="layui-table">
                        <thead>
                            <tr>${columnsHtml}</tr>
                        </thead>
                        <tbody>
                            ${dataHtml}
                        </tbody>
                    </table>
                    ${component.properties.page ? '<div class="layui-laypage layui-laypage-default"></div>' : ''}
                </div>`;
                break;
            case 'form':
                html = `<div class="component-preview" data-id="${component.id}" style="${styleStr}">
                    <form class="layui-form">
                        <div class="layui-form-item">
                            <label class="layui-form-label">用户名</label>
                            <div class="layui-input-block">
                                <input type="text" name="username" required lay-verify="required" placeholder="请输入用户名" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">密码</label>
                            <div class="layui-input-block">
                                <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit lay-filter="formDemo">提交</button>
                                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                            </div>
                        </div>
                    </form>
                </div>`;
                break;
            case 'tree':
                html = `<div class="component-preview" data-id="${component.id}" style="${styleStr}">
                    <div class="layui-tree">
                        <ul class="layui-tree-box">
                            <li>
                                <div class="layui-tree-entry">
                                    <span class="layui-tree-iconClick"></span>
                                    <span class="layui-tree-icon"></span>
                                    <a href="javascript:;" class="layui-tree-title">一级菜单1</a>
                                </div>
                                <ul class="layui-tree-child layui-show">
                                    <li>
                                        <div class="layui-tree-entry">
                                            <span class="layui-tree-icon"></span>
                                            <a href="javascript:;" class="layui-tree-title">二级菜单1-1</a>
                                        </div>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <div class="layui-tree-entry">
                                    <span class="layui-tree-iconClick"></span>
                                    <span class="layui-tree-icon"></span>
                                    <a href="javascript:;" class="layui-tree-title">一级菜单2</a>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>`;
                break;
            case 'chart':
                html = `<div class="component-preview" data-id="${component.id}" style="${styleStr}">
                    <div style="width: 100%; height: 300px;">
                        <div class="layui-bg-gray" style="width: 100%; height: 100%; display: flex; align-items: center; justify-content: center;">
                            图表组件
                        </div>
                    </div>
                </div>`;
                break;
        }
        
        return html;
    }
    
    // 选择组件
    function selectComponent(id) {
        // 取消之前的选择
        deselectComponent();
        
        selectedComponentId = id;
        
        // 添加选中样式
        $('#designCanvas .component-preview[data-id="' + id + '"]').addClass('component-selected');
        
        // 更新属性面板
        updatePropertiesPanel(id);
    }
    
    // 取消选择组件
    function deselectComponent() {
        if (selectedComponentId) {
            $('#designCanvas .component-preview[data-id="' + selectedComponentId + '"]').removeClass('component-selected');
            selectedComponentId = null;
        }
        
        // 清空属性面板
        $('#propertiesPanel').html('<div class="no-selection"><p>请选择一个组件进行编辑</p></div>');
    }
    
    // 更新属性面板
    function updatePropertiesPanel(id) {
        var component = currentPage.components.find(c => c.id === id);
        if (!component) return;
        
        var panel = $('#propertiesPanel');
        var html = '';
        
        // 基本属性
        html += '<div class="property-group">';
        html += '<h4>基本属性</h4>';
        html += '<div class="property-item">';
        html += '<label>组件ID</label>';
        html += '<input type="text" class="property-input" data-property="id" value="' + (component.properties.id || '') + '">';
        html += '</div>';
        html += '<div class="property-item">';
        html += '<label>组件名称</label>';
        html += '<input type="text" class="property-input" data-property="name" value="' + (component.properties.name || '') + '">';
        html += '</div>';
        
        // 根据组件类型添加特定属性
        switch(component.type) {
            case 'button':
                html += '<div class="property-item">';
                html += '<label>按钮文本</label>';
                html += '<input type="text" class="property-input" data-property="text" value="' + (component.properties.text || '按钮') + '">';
                html += '</div>';
                html += '<div class="property-item">';
                html += '<label>按钮类型</label>';
                html += '<select class="property-input" data-property="type">';
                html += '<option value="primary" ' + (component.properties.type === 'primary' ? 'selected' : '') + '>主要按钮</option>';
                html += '<option value="normal" ' + (component.properties.type === 'normal' ? 'selected' : '') + '>普通按钮</option>';
                html += '<option value="warn" ' + (component.properties.type === 'warn' ? 'selected' : '') + '>警告按钮</option>';
                html += '<option value="danger" ' + (component.properties.type === 'danger' ? 'selected' : '') + '>危险按钮</option>';
                html += '</select>';
                html += '</div>';
                break;
            case 'input':
                html += '<div class="property-item">';
                html += '<label>标签文本</label>';
                html += '<input type="text" class="property-input" data-property="label" value="' + (component.properties.label || '输入框') + '">';
                html += '</div>';
                html += '<div class="property-item">';
                html += '<label>占位文本</label>';
                html += '<input type="text" class="property-input" data-property="placeholder" value="' + (component.properties.placeholder || '请输入...') + '">';
                html += '</div>';
                html += '<div class="property-item">';
                html += '<label>最大长度</label>';
                html += '<input type="number" class="property-input" data-property="maxlength" value="' + (component.properties.maxlength || '') + '">';
                html += '</div>';
                break;
        }
        
        html += '</div>';
        
        // 样式属性
        html += '<div class="property-group">';
        html += '<h4>样式属性</h4>';
        html += '<div class="property-item">';
        html += '<label>宽度</label>';
        html += '<input type="text" class="property-input" data-style="width" value="' + (component.style.width || '100%') + '">';
        html += '</div>';
        html += '<div class="property-item">';
        html += '<label>高度</label>';
        html += '<input type="text" class="property-input" data-style="height" value="' + (component.style.height || 'auto') + '">';
        html += '</div>';
        html += '<div class="property-item">';
        html += '<label>下边距</label>';
        html += '<input type="text" class="property-input" data-style="marginBottom" value="' + (component.style.marginBottom || '10px') + '">';
        html += '</div>';
        html += '</div>';
        
        // 操作按钮
        html += '<div class="property-group">';
        html += '<button class="layui-btn layui-btn-danger" id="deleteComponent">删除组件</button>';
        html += '</div>';
        
        panel.html(html);
        
        // 绑定属性输入事件
        panel.find('.property-input[data-property]').on('input change', function() {
            var prop = $(this).attr('data-property');
            var value = $(this).val();
            updateComponentProperty(id, prop, value);
        });
        
        // 绑定样式输入事件
        panel.find('.property-input[data-style]').on('input change', function() {
            var style = $(this).attr('data-style');
            var value = $(this).val();
            updateComponentStyle(id, style, value);
        });
        
        // 绑定删除按钮事件
        panel.find('#deleteComponent').on('click', function() {
            deleteComponent(id);
        });
    }
    
    // 更新组件属性
    function updateComponentProperty(id, property, value) {
        var component = currentPage.components.find(c => c.id === id);
        if (!component) return;
        
        component.properties[property] = value;
        renderComponent(component);
    }
    
    // 更新组件样式
    function updateComponentStyle(id, style, value) {
        var component = currentPage.components.find(c => c.id === id);
        if (!component) return;
        
        component.style[style] = value;
        renderComponent(component);
    }
    
    // 删除组件
    function deleteComponent(id) {
        layer.confirm('确定要删除这个组件吗？', {
            btn: ['确定', '取消']
        }, function() {
            currentPage.components = currentPage.components.filter(c => c.id !== id);
            $('#designCanvas .component-preview[data-id="' + id + '"]').remove();
            deselectComponent();
            
            // 如果没有组件了，显示空状态
            if (currentPage.components.length === 0) {
                $('.canvas-empty').show();
            }
            
            layer.closeAll();
        });
    }
    
    // 保存页面
    function savePage() {
        // 这里可以实现保存到服务器的逻辑
        var pageData = JSON.stringify(currentPage, null, 2);
        
        layer.open({
            title: '页面数据',
            content: `<pre style="max-height: 400px; overflow-y: auto; padding: 10px; background-color: #f8f8f8;">${pageData}</pre>`,
            area: ['600px', '500px'],
            btn: ['复制', '关闭'],
            yes: function() {
                var textArea = document.createElement('textarea');
                textArea.value = pageData;
                document.body.appendChild(textArea);
                textArea.select();
                document.execCommand('copy');
                document.body.removeChild(textArea);
                layer.msg('复制成功');
            }
        });
    }
    
    // 预览页面
    function previewPage() {
        var previewHtml = generatePreviewHTML();
        
        var previewWindow = window.open('', '_blank');
        previewWindow.document.write(previewHtml);
        previewWindow.document.close();
    }
    
    // 生成预览HTML
    function generatePreviewHTML() {
        var html = `<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${currentPage.title}</title>
    <link rel="stylesheet" href="/assets/libs/layui/css/layui.css">
</head>
<body style="padding: 20px;">
`;
        
        // 添加所有组件的HTML
        currentPage.components.forEach(component => {
            html += generateComponentHTML(component);
        });
        
        html += `
<script src="/assets/libs/layui/layui.js"></script>
<script>
layui.use(['form', 'table'], function() {
    var form = layui.form;
    var table = layui.table;
    form.render();
});
</script>
</body>
</html>`;
        
        return html;
    }
    
    // 生成代码
    function generateCode() {
        var htmlCode = generatePreviewHTML();
        var jsCode = generateJSCode();
        var cssCode = generateCSSCode();
        
        // 创建代码面板
        var codePanel = $(`
        <div class="code-panel" id="codePanel">
            <div class="code-panel-header">
                <h3>生成的代码</h3>
                <span class="code-panel-close" id="closeCodePanel">&times;</span>
            </div>
            <div class="code-panel-content">
                <div class="code-tabs">
                    <div class="code-tab active" data-tab="html">HTML</div>
                    <div class="code-tab" data-tab="js">JavaScript</div>
                    <div class="code-tab" data-tab="css">CSS</div>
                </div>
                <div class="code-content active" data-content="html">
                    <pre style="max-height: 500px; overflow-y: auto; padding: 10px; background-color: #f8f8f8;">${htmlCode}</pre>
                </div>
                <div class="code-content" data-content="js">
                    <pre style="max-height: 500px; overflow-y: auto; padding: 10px; background-color: #f8f8f8;">${jsCode}</pre>
                </div>
                <div class="code-content" data-content="css">
                    <pre style="max-height: 500px; overflow-y: auto; padding: 10px; background-color: #f8f8f8;">${cssCode}</pre>
                </div>
            </div>
        </div>`);
        
        $('body').append(codePanel);
        codePanel.show();
        
        // 切换代码标签
        codePanel.find('.code-tab').on('click', function() {
            var tab = $(this).attr('data-tab');
            codePanel.find('.code-tab').removeClass('active');
            codePanel.find('.code-content').removeClass('active');
            $(this).addClass('active');
            codePanel.find('.code-content[data-content="' + tab + '"]').addClass('active');
        });
        
        // 关闭代码面板
        codePanel.find('#closeCodePanel').on('click', function() {
            codePanel.remove();
        });
    }
    
    // 生成JavaScript代码
    function generateJSCode() {
        var jsCode = `layui.use(['form', 'table'], function() {
    var $ = layui.jquery;
    var form = layui.form;
    var table = layui.table;
    
    // 表单渲染
    form.render();
    
    // 表单提交事件
    form.on('submit(formDemo)', function(data) {
        // 处理表单提交
        console.log(data.field);
        return false;
    });
    
    // 表格渲染
`;
        
        // 为每个表格组件添加渲染代码
        currentPage.components.forEach(component => {
            if (component.type === 'table' && component.properties.id) {
                jsCode += `    // 渲染表格 ${component.properties.id}
    table.render({
        elem: '#${component.properties.id}',
        cols: [[
`;
                
                component.properties.columns.forEach(col => {
                    jsCode += `            {field: '${col.field}', title: '${col.title}'},
`;
                });
                
                jsCode += `        ]],
        data: ${JSON.stringify(component.properties.data, null, 4)},
        page: ${component.properties.page}
    });

`;
            }
        });
        
        jsCode += `});`;
        
        return jsCode;
    }
    
    // 生成CSS代码
    function generateCSSCode() {
        var cssCode = '';
        
        // 为每个组件添加样式代码
        currentPage.components.forEach(component => {
            if (component.properties.id) {
                cssCode += `/* 组件 ${component.properties.id} 的样式 */
#${component.properties.id} {
`;
                
                Object.keys(component.style).forEach(key => {
                    cssCode += `    ${key.replace(/([A-Z])/g, '-$1').toLowerCase()}: ${component.style[key]};
`;
                });
                
                cssCode += `}

`;
            }
        });
        
        return cssCode;
    }
    
    // 切换主题
    function toggleTheme() {
        $('body').toggleClass('dark-theme');
        var isDark = $('body').hasClass('dark-theme');
        $('#themeSwitch i').attr('class', isDark ? 'layui-icon layui-icon-sunny' : 'layui-icon layui-icon-moon');
    }
    
    // 初始化应用
    $(function() {
        init();
    });
});