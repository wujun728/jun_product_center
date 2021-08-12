<?php
// 应用公共文件
if (!function_exists('opt_photo')){
    //图库选择
    function opt_photo($val)
    {
       return '<button class="pear-btn pear-btn-primary pear-btn-sm" style="margin:4px 5px;vertical-align:top;" id="'.$val.'" type="button">图库选择</button>
       <script>
       layui.use(["jquery"],function() {
        let $ = layui.jquery;
        //弹出窗设置 自己设置弹出百分比
        function screen() {
            if (typeof width !== "number" || width === 0) {
            width = $(window).width() * 0.8;
            }
            if (typeof height !== "number" || height === 0) {
            height = $(window).height() - 20;
            }
            return [width + "px", height + "px"];
        }
        $("#'.$val.'").on("click", function () {
            layer.open({
                type: 2,
                maxmin: true,
                title: "图库选择",
                shade: 0.1,
                area: screen(),
                content:"../index/optPhoto",
                success:function (layero,index) {
                    var iframe = window["layui-layer-iframe" + index];
                    iframe.child("'.$val.'")
                }
            });
        });
        })
        </script>';
    }
}
if (!function_exists('rm')) {
    //清除缓存
    function rm()
    {
        delete_dir(root_path().'runtime');
    }
}

if (!function_exists('is_url')){
    //是否
    function is_url($url)
    {
        if(preg_match("/^http(s)?:\\/\\/.+/",$url)) return $url;
    }
}

if (!function_exists('rand_string')) {
    /**
     *  随机数
     *
     * @param string $length 长度
     * @param string $type   类型
     * @return void
     */
    function rand_string($length = '32',$type=4): string
    {
        $rand='';
        switch ($type) {
            case '1':
                $randstr= '0123456789';
                break;
            case '2':
                $randstr= 'abcdefghijklmnopqrstuvwxyz';
                break;
            case '3':
                $randstr= 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
                break;
            default:
                $randstr= '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
                break;
        }
        $max = strlen($randstr)-1;
        mt_srand((double)microtime()*1000000);
        for($i=0;$i<$length;$i++) {
            $rand.=$randstr[mt_rand(0,$max)];
        }
        return $rand;
    }
}

if (!function_exists('set_password')) {
    //密码截取
    function set_password($password): string
    {
      return substr(md5($password), 3, -3);
    }
}

/**
 * 数据签名认证
 */
function data_sign($data = [])
{
    if (!is_array($data)) {
        $data = (array)$data;
    }
    ksort($data);
    $code = http_build_query($data);
    $sign = sha1($code);
    return $sign;
}

/**
 * 修改网站配置文件
 */
if (!function_exists('set_web')) {
    function set_web($data = [])
    {
        $str = "<?php\r\n/**\r\n * 系统配置文件\r\n */\r\nreturn [\r\n";
        foreach ($data as $key => $value) {
            if(is_array($value)){
            $str .= getArrTree($key,$value);
            }else{
                $str .= "\t'$key' => '$value',";
                $str .= "\r\n";
            }
        }
        $str .= '];';
        @file_put_contents(config_path().'web.php', $str);
    }
}

if (!function_exists('get_arr_tree')) {
    /**
     * 递归配置数组
     */
    function get_arr_tree($key,$data,$level="\t")
    {
        $i = "$level'$key' => [\r\n";
        foreach ($data as $k => $v) {
            if(is_array($v)){
                $i .= get_arr_tree($k,$v,$level."\t");
            }else{
                $i .= "$level\t'$k' => '$v',";
                $i .= "\r\n";      
            }
        }
        return  $i."$level".'],'."\r\n";
    }
}

if (!function_exists('aes_encrypt')) {
    /**
     *
     * @param string $string 需要加密的字符串
     * @param string $key 密钥
     * @return string
     */
    function aes_encrypt($string, $key="ONSPEED"): string
    {
        $data = openssl_encrypt($string, 'AES-128-ECB', $key, OPENSSL_RAW_DATA);
        return strtolower(bin2hex($data));
    }
}

if (!function_exists('aes_decrypt')) {
    /**
     * @param string $string 需要解密的字符串
     * @param string $key 密钥
     * @return string
     */
    function aes_decrypt($string, $key="ONSPEED"): string
    {
        try {
            return openssl_decrypt(hex2bin($string), 'AES-128-ECB', $key, OPENSSL_RAW_DATA);
        }catch (\Exception $e){
            return false;
        }
    }
}

if (!function_exists('get_field')) {
    /**
     * 获取指定表指定行指定字段
     * @param  string       $tn      完整表名
     * @param  string|array $where   参数数组或者id值
     * @param  string       $field   字段名,默认'name'
     * @param  string       $default 获取失败的默认值,默认''
     * @param  array        $order   排序数组
     * @return string                获取到的内容
     */
    function get_field($tn, $where, $field = 'name', $default = '', $order = ['id' => 'desc'])
    {
        if (!is_array($where)) {
            $where = ['id' => $where];
        }
        $row = \think\facade\Db::name($tn)->field([$field])->where($where)->order($order)->find();
        return $row === null ? $default : $row[$field];
    }
  }

  if (!function_exists('delete_dir')) {
    /**
     * 遍历删除文件夹所有内容
     * @param  string $dir 要删除的文件夹
     */
    function delete_dir($dir)
    {
        $dh = opendir($dir);
        while ($file = readdir($dh)) {
            if ($file != '.' && $file != '..') {
                $filepath = $dir . '/' . $file;
                if (is_dir($filepath)) {
                    delete_dir($filepath);
                } else {
                    @unlink($filepath);
                }
            }
        }
        closedir($dh);
        @rmdir($dir);
    }
  }

  if (!function_exists('get_tree')) {
    /**
     * 递归无限级分类权限
     * @param array $data
     * @param int $pid
     * @param string $field1 父级字段
     * @param string $field2 子级关联的父级字段
     * @param string $field3 子级键值
     * @return mixed
     */
    function get_tree($data, $pid = 0, $field1 = 'id', $field2 = 'pid', $field3 = 'children')
    {
        $arr = [];
        foreach ($data as $k => $v) {
            if ($v[$field2] == $pid) {
                $v[$field3] = get_tree($data, $v[$field1]);
                $arr[] = $v;
            }
        }
        return $arr;
    }
  }

  if (!function_exists('hump_underline')) {
    /**
     * 驼峰转下划线
     * @param  string $str 需要转换的字符串
     * @return string      转换完毕的字符串
     */
    function hump_underline($str)
    {
        return strtolower(trim(preg_replace('/[A-Z]/', '_\\0', $str), '_'));
    }
 }

  if (!function_exists('underline_hump')) {
    /**
     * 下划线转驼峰
     * @param  string $str 需要转换的字符串
     * @return string      转换完毕的字符串
     */
    function underline_hump($str)
    {
        return ucfirst(
            preg_replace_callback('/_([a-zA-Z])/', function ($match) {
                return strtoupper($match[1]);
            }, $str)
        );
    }
  }

  if (!function_exists('record_log')){
    /**
     * @记录日志
     * @param [type] $param
     * @param string $file
     *
     * @return void
     */
     function record_log($param,$file=''){
        $path = root_path().'log/'.$file."/";
        if (!is_dir($path)) @mkdir($path,0777,true);
        if (is_array($param)){
            $param = json_encode($param,JSON_FORCE_OBJECT|JSON_UNESCAPED_UNICODE);
        }
        @file_put_contents(
            $path.date("Y_m_d",time()).".txt",
            "执行日期："."\r\n".date('Y-m-d H:i:s', time()) . ' ' . "\n" . $param . "\r\n",
            FILE_APPEND
        );
    }

}
