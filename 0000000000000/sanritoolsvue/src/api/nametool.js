import request from '@/utils/request'
import qs from 'qs'

export default {
  englishs(){return request.get('/name/englishs')},
  tokenizers(){return request.get('/name/tokenizers')},
  bizs(){return request.get('/name/bizs')},
  content(biz){return request.get(`/name/detail/${biz}`)},
  writeBizContent(biz,content){return request.post(`/name/mirror/write/${biz}`,content)},
  translate(orginChars,tokenizer,tranlatesArray,bizArray){
    let tranlates = tranlatesArray.join(',');
    let bizs = bizArray.join(',');
    return request.get('/name/translate',{params:{orginChars,tokenizer,tranlates,bizs}})
  },
  contentMerge(bizs){return request.get('/name/content/bizs',{params:{bizs:bizs.join(',')}})}
}
