<?php


namespace app\index\controller;


use think\facade\Queue;

class Job extends \app\BaseController
{

    /**
     * @定时任务
     *
     * @return void
     */
    public function test(){
        //参数
        $params = ['id'=>10086];
        $isPushed = Queue::later(3, \app\index\job\Order::class, $params, "order");
        if($isPushed !== false){
            echo date('Y-m-d H:i:s') . " 队列添加成功";
        }else{
            echo '队列添加失败';
        }
    }

}