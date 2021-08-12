const ALLCHAR = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
const NUMBERCHAR = '0123456789'

export function words(length,src) {
  length = length || 0;
  src = src || ALLCHAR;

  var res = '';
  while(length-- != 0){
    res += src.charAt(random(src.length - 1));
  }
  return res;
}
export function nums(length) {
  return words(length, NUMBERCHAR);
}
export function random(limit) {
  return Math.round(Math.random() * limit);
}
export function idcard(area, yyyyMMdd, sex) {
  area = area || '430124';
  yyyyMMdd = yyyyMMdd || this.date('yyyyMMdd');
  if (sex === undefined) {
    sex = (random(100) % 2 == 0);
  }
  //根据男/女 随机生成3 位数字
  var sno = nums(3);
  if(sex){
    while(sno % 2 == 0){
      sno = nums(3);
    }
  }else{
    while(sno % 2 != 0){
      sno = nums(3);
    }
  }
  //获得 17 位身份证号码
  var id17 = area + yyyyMMdd + sno;
  return id17 + getVerify(id17);
}

export function getVerify(id17){
  const power = [7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2];
  let sum = 0 ;
  for (let i = 0; i < id17.length; i++) {
    sum += id17[i] * power[i];
  }
  var t = sum % 11;
  var r = (12 - t) % 11;
  return r == 10?'x':r;
}