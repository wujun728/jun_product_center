<?php
namespace app\common\util;

use think\exception\ValidateException;
use app\common\service\AdminPhoto;
class Upload
{

    //通用上传
    public static function putFile($file,$path){
        if(!$path) $path = 'default';
        try {
            validate(['file'=>[
                'fileSize' => 410241024,
                'fileExt' => 'jpg,jpeg,png,bmp,gif',
                'fileMime' => 'image/jpeg,image/png,image/gif', 
            ]])->check(['file' => $file]);
        } catch (\think\exception\ValidateException $e) {
            return ['msg'=>'上传失败','code'=>201,'data'=>$e->getMessage()];
        }
        foreach($file as $k) {
            if(config('web.file-type')==2){
                //阿里云上传
                $res = Oss::alYunOSS($k, $k->extension(),$path);
                if ($res["code"] == 201) return ['msg'=>'上传失败','code'=>201,'data'=>$res["msg"]];
                $name = $res['src'];
                AdminPhoto::add($k,$name,$path,2);
            }else{
                $savename = '/'.'upload'. '/'. \think\facade\Filesystem::disk('public')->putFile($path, $k);
                $name = str_replace("\\","/",$savename);
                AdminPhoto::add($k,$name,$path,1);
            }
        }
        return ['msg'=>'上传成功','code'=>0,'data'=>['src'=>$name,'thumb'=>$name]];
   }
}