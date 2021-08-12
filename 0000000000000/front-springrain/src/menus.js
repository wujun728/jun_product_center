import { ajax } from "src/commons/ajax";
import { preRouter } from 'src/commons/PRE_ROUTER'
/*
 * 菜单数据 返回Promise各式，支持前端硬编码、异步获取菜单数据
 * */
export default function getMenus() {
    return ajax.post("api/system/user/menu").then((res) => {
        (res.result || []).forEach(element => {
            element.path = `${preRouter}` + element.path
        });
        return (res.result || []).map((item) => ({
            key: item.key,
            parentKey: item.parentKey,
            ...item,
        }));
    });

    // TODO 根据userId获取菜单数据 或在此文件中前端硬编码菜单  实例，实际项目请用上面方式进行请求   /menus
    // return Promise.resolve([

    //     { key: 'system', text: '系统管理', icon: 'user', order: 900 },
    //     { key: 'user', parentKey: 'system', text: '用户管理', icon: 'user', path: '/users', order: 900 },
    //     { key: 'role', parentKey: 'system', text: '角色管理', icon: 'lock', path: '/roles', order: 900 },
    //     { key: 'form', text: '复杂表单', icon: 'align-left', path: '/form', order: 900 },
    //     { key: 'upload', text: '文件上传', icon: 'align-left', path: '/upload', order: 900 },
    //     // { key: 'menu', parentKey: 'system', text: '菜单管理', icon: 'align-left', path: '/menus', order: 900 },

    //     // { key: 'other-site', text: '第三方网站', icon: 'ant-design', order: 800 },
    //     // { key: 'antDesign', parentKey: 'other-site', text: 'Ant Design 官网', icon: 'ant-design', url: 'https://ant-design.gitee.io', target: '', order: 2000 },
    //     // { key: 'baidu', parentKey: 'other-site', text: '百度', icon: 'ant-design', url: 'https://baidu.com', target: '', order: 2000 },

    //     // { key: 'example', text: '示例', icon: 'align-left', order: 600 },
    //     // { key: 'page404', parentKey: 'example', text: '404页面不存在', icon: 'file-search', path: '/404', order: 700 },
    //     // { key: 'table-editable', parentKey: 'example', text: '可编辑表格', icon: 'align-left', path: '/example/table-editable', order: 600 },

    //     // { key: 'level', basePath: '/demo', parentKey: 'example', text: '多级', icon: 'align-left', order: 500 },
    //     // { key: 'level1', parentKey: 'level', path: '/1', text: '多级1', icon: 'align-left', order: 500 },
    //     // { key: 'level11', parentKey: 'level', path: '/2', text: '多级11', icon: 'align-left', order: 500 },
    //     // { key: 'level2', parentKey: 'level11', path: '/3', text: '多级11', icon: 'align-left', order: 500 },

    //     // { key: 'baidu-family', basePath: 'http://baidu.com', text: '百度全家桶', icon: 'DribbbleOutlined', order: 400 },
    //     // { key: 'baidu-zhidao', parentKey: 'baidu-family', text: '知道', icon: 'align-left', url: '/zhidao' },
    //     // { key: 'baidu-buzhidao', parentKey: 'baidu-family', text: '不知道', icon: 'align-left', url: '/buzhidao' },
    //     // { key: 'code-key', parentKey: 'baidu-family', text: '添加用户', type: '2', code: 'ADD_USER', icon: 'align-left', url: '/buzhidao' },
    // ]);
}

/*
菜单数据主键也可以是：id parentId
角色字段： id name description
* */
