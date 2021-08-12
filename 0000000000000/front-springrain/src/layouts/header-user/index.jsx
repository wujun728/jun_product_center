import React, { useState } from 'react';
import config from 'src/commons/config-hoc';
import { EditOutlined, LogoutOutlined, UserOutlined } from '@ant-design/icons';
import { Link } from 'react-router-dom';
import { HeaderUser } from 'src/commons/ra-lib';
import ModifyPassword from './ModifyPassword';
import { toLogin } from 'src/commons';
import { preRouter } from 'src/commons/PRE_ROUTER';
import UserCenterModel from '../user-center/index'
export default config({
  connect: state => ({
    theme: state.layout.theme,
    loginUser: state.layout.loginUser,
  }),
})(function LayoutHeaderUser(props) {
  const { theme, loginUser } = props;
  const [visible, setVisible] = useState(false);
  const [center, setCenter] = useState(false);

  function handleLogout() {
    props.ajax.post('api/system/logout').finally(() => toLogin()); 
  }
  return (
    <div style={{ display: 'flex', alignItems: 'center', marginLeft: 8 }}>
      <HeaderUser
        theme={theme}
        menus={[
          {
            text: <><EditOutlined />修改密码</>,
            onClick: () => setVisible(true),
          },
          {
            text: <><UserOutlined />个人中心</>,
            onClick: () => setCenter(true),
          },
          {
            text: 'Divider',
          },
          {
            text: <><LogoutOutlined />退出登录</>,
            onClick: () => handleLogout(),
          },
        ]}
        loginUser={loginUser}
      />
      <ModifyPassword
        visible={visible}
        onOk={() => setVisible(false)}
        onCancel={() => setVisible(false)}
      />
      <UserCenterModel
        visible={center}
        onOk={() => setCenter(false)}
        onCancel={() => setCenter(false)}
      />
    </div>
  );
});
