<?php
declare (strict_types = 1);

namespace app\admin\controller;

use think\facade\Session;
use think\facade\Request;
use app\common\util\Upload as Up;
use app\common\model\AdminPhoto as P;
use app\common\service\AdminAdmin as S;

class Index extends Base
{
    protected $middleware = ['AdminCheck'];
    
    // 首页
    public function index(){
        return $this->fetch('',[
            'nickname'  => get_field('admin_admin',Session::get('admin.id'),'nickname')
        ]);
    }

    // 清除缓存
    public function cache(){
        Session::clear();
         return $this->getJson(rm());
        }

    // 菜单
    public function menu(){
        return json(get_tree(Session::get('admin.menu')));
    }

    // 欢迎页
    public function home(){
        return $this->fetch('',$this->getSystem());
    }

    // 修改密码
    public function pass(){
        if (Request::isAjax()){
            $this->getJson(S::goPass());
        }
        return $this->fetch();
    }

    // 通用上传
    public function upload(){
        return $this->getJson(Up::putFile(Request::file(),Request::post('path')));
    }

    // 图库选择
    public function optPhoto(){
        if (Request::isAjax()) {
            return $this->getJson(P::getAll());
        }
        return $this->fetch('',P::getPath());
    }

}
