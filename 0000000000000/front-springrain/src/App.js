import React from "react";
import { ConfigProvider, Spin } from "antd";
import zhCN from "antd/lib/locale-provider/zh_CN";
import moment from "moment";
import "moment/locale/zh-cn";
import { util } from "src/commons/ra-lib";
import { getLoginUser, setLoginUser, isLogin, toLogin } from "src/commons";
import { connect } from "src/models"; // 解决 antd 日期组件国际化问题
import cfg from "src/config";
import theme from "./theme.less";
import getMenus from "./menus";
import AppRouter from "./router/AppRouter";

const { getMenuTreeDataAndPermissions } = util;
const { appName } = cfg;

// 设置语言
moment.locale("zh-cn");

@connect()
export default class App extends React.Component {
    state = {
        loading: true, // 初始化时true，显示loading，等待资源加载
    };

    componentDidMount() {
        const {
            action: { layout },
        } = this.props;
        layout.setAppName(appName);
        layout.setPrimaryColor(theme.primaryColor);

        // 未登录，直接跳转到登录页面
        // if (!isLogin()) {
        //     this.setState({ loading: false });
        //     return toLogin();
        // }

        // 从Storage中获取出需要同步到redux的数据
        this.props.action.getStateFromStorage();

        const loginUser = getLoginUser();

        const userId = loginUser?.id;

        // 获取系统菜单 和 随菜单携带过来的权限
        this.setState({ loading: true });
        try{
            getMenus(userId)
            .then((res) => {
                const plainMenus = res || [];
                //const permissions = [];
                const userPaths = [];

                const { menuTreeData } = getMenuTreeDataAndPermissions(
                    plainMenus
                );

                plainMenus.forEach(({ type, path, code }) => {
                    //if (type === '2' && code) permissions.push(code);
                    if (path) userPaths.push(path);
                });

                // if (loginUser) {
                //     loginUser.permissions = permissions;
                //     setLoginUser(loginUser);
                // }

                layout.setMenus(menuTreeData);
                layout.setPlainMenus(plainMenus);
                layout.setUserPaths(userPaths);
                // layout.setPermissions(permissions);
                layout.setLoginUser(loginUser);
            }).finally(() => {
                 this.setState({ loading: false });
            });
        }catch{

        }
       
    }

    render() {
        const { subLoading, subAppError } = this.props;
        const { loading } = this.state;
        return (
            <ConfigProvider locale={zhCN}>
                {loading ? (
                    <div
                        style={{
                            display: "flex",
                            width: "100%",
                            height: "100vh",
                            alignItems: "center",
                            justifyContent: "center",
                        }}
                    >
                        <Spin spinning tip="加载中..." />
                    </div>
                ) : (
                    <div id='container'>
                        <AppRouter 
                        subLoading={subLoading}
                        subAppError={subAppError}
                    />
                    </div>
                    
                )}
            </ConfigProvider>
        );
    }
}
