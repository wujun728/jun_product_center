const config = {

 }
/**
 *
 * @param {封装图片的方法} arg
 */
const getImgPath = function(arg) {
  return process.env.BASE_API+arg
}


export {config,getImgPath}
