import request from '@/utils/request'
import {headers} from "@/utils/request";
import core from '@/api/core'

export default {
  doc(url){return request.get('/swagger/doc',{params:{url}},headers.html)},
  download(url){core.download('/swagger/doc/download',{url})}
}
