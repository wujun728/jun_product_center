<?php


namespace app\index\job;


use think\facade\Db;
use think\queue\Job;

class Order
{

    /**
     * 在宝塔里的  Supervisord管理器==添加手护进程
     * 名称就是 队列名称，启动命令是：php think queue:listen --queue +队列名
     * 启动用户：root
     * @队列执行
     * @param Job $job
     * @param [type] $param
     *
     * @return void
     */
    public function fire(Job $job, $param)
    {
        try {
            //参数
            $data = $param;
            /*操作开始 */
            $res = $this->handleOrder($data['id']);
            if (!$res){
               if ($job->attempts() > 2) {
                   record_log("任务已经重试2次，删除任务","job");
                   $job->delete();
                   return ;
               }
            }
            record_log("执行成功：".$res,"job_ok");
            //删除任务
            $job->delete();
        }catch (\Exception $exception){
            $job->delete();
            record_log($exception->getMessage(),"exception");
        }
    }

    /**
     * @处理数据
     * @param [type] $id
     *
     * @return void
     */
    public function handleOrder($id)
    {
        return true;
        //对订单进行数据库操作或其他等等
        //Db::name("order")->where("id",$data['id'])->save();
    }

    /**
     * @执行失败
     * @param [type] $data
     *
     * @return void
     */
    public function failed($data){
        // 记录日志
        record_log($data,'job_error');
    }

}