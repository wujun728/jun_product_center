<?php
declare (strict_types = 1);

namespace app;

use think\App;
use think\exception\ValidateException;
use think\Validate;
use think\facade\View;
/**
 * 控制器基础类
 */
abstract class BaseController
{
    use \app\common\traits\Base;
    /**
     * Request实例
     * @var \think\Request
     */
    protected $request;

    /**
     * 应用实例
     * @var \think\App
     */
    protected $app;

    /**
     * 是否批量验证
     * @var bool
     */
    protected $batchValidate = false;

    /**
     * 控制器中间件
     * @var array
     */
    protected $middleware = [];

    /**
     * 构造方法
     * @access public
     * @param  App  $app  应用对象
     */
    public function __construct(App $app)
    {
        $this->app     = $app;
        $this->request = $this->app->request;

        // 控制器初始化
        $this->initialize();
    }

    // 初始化
    protected function initialize(){}

    // 获取系统参数
    protected function getSystem(){
        return [ 
            'os' => PHP_OS,
            'space' => round((disk_free_space('.')/(1024*1024)),2).'M',
            'addr' =>$_SERVER['HTTP_HOST'],
            'run' => $this->request->server('SERVER_SOFTWARE'),
            'php' => PHP_VERSION,
            'php_run' => php_sapi_name(),
            'mysql' => function_exists('mysql_get_server_info')?mysql_get_server_info():\think\facade\Db::query('SELECT VERSION() as mysql_version')[0]['mysql_version'],
            'think' => $this->app->version(),
            'upload' => ini_get('upload_max_filesize'),
            'max' => ini_get('max_execution_time').'秒',
            'ver' => 'V5.0.1',
        ];
    }

    protected function getJson($json = [])
    {
        if('json' == strtolower($this->getResponseType())){
            return $this->json($json['msg']??'操作成功',$json['code']??200,$json['data']??[],$json['extend']??[]);
        }
    }

    //页面分配变量
    protected function assign($key,$value)
    {
        return View::assign($key,$value);
    }

    //页面渲染 
    protected function fetch($template = '',$data = [])
    {
        return View::fetch($template,$data);
    }

    /**
     * 验证数据
     * @access protected
     * @param  array        $data     数据
     * @param  string|array $validate 验证器名或者验证规则数组
     * @param  array        $message  提示信息
     * @param  bool         $batch    是否批量验证
     * @return array|string|true
     * @throws ValidateException
     */
    protected function validate(array $data, $validate, array $message = [], bool $batch = false)
    {
        if (is_array($validate)) {
            $v = new Validate();
            $v->rule($validate);
        } else {
            if (strpos($validate, '.')) {
                // 支持场景
                [$validate, $scene] = explode('.', $validate);
            }
            $class = false !== strpos($validate, '\\') ? $validate : $this->app->parseClass('validate', $validate);
            $v     = new $class();
            if (!empty($scene)) {
                $v->scene($scene);
            }
        }

        $v->message($message);

        // 是否批量验证
        if ($batch || $this->batchValidate) {
            $v->batch(true);
        }

        return $v->failException(true)->check($data);
    }

}