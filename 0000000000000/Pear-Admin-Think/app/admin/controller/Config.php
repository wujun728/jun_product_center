<?php
declare (strict_types = 1);

namespace app\admin\controller;

use think\facade\Request;

class Config extends Base
{
    protected $middleware = ['AdminCheck','AdminPermission'];
    
    // 系统配置
    public function index(){
        if(Request::isPost()){
           set_web(Request::post('','','strip_tags'));
           return  $this->success('保存成功',Request::root().'/config/index');
        }
        return $this->fetch('',[
            'data' => config('web')
        ]);
    }
}
