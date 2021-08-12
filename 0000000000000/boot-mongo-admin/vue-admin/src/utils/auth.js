import Vue from 'vue'
import store from '@/store'

/**权限指令**/
const Auth = Vue.directive('has', {
    bind: function(el, binding) {
        if (!Vue.prototype.$_has(binding.value)) {
            // el.parentNode.removeChild(el);
            el.style.display = 'none';
        }
    }
});
//权限检查方法
Vue.prototype.$_has = function(value) {
    let isExist = true;
    // let isExist = false;
    //super
    if (store.state.app && store.state.app.user && store.state.app.user.admin) {
        return true;
    }
    let buttonperms = store.state.app.perms;
    if (buttonperms != null && buttonperms) {
        // 权限列表
        for (let i = 0; i < buttonperms.length; i++) {
            if (buttonperms[i].indexOf(value) > -1) {
                isExist = true;
                break;
            }
        }
    }
    return isExist;
    // return hasPermission(value);
};

/**
 * 是否拥有权限
 * @param {*} perms
 */
export function hasPermission(perms) {
    let hasPermission = false
    let permissions = store.state.perms
    for (let i = 0, len = permissions.length; i < len; i++) {
        if (permissions[i] === perms) {
            hasPermission = true;
            break
        }
    }
    return hasPermission
}

export default Auth