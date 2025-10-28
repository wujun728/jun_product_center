package io.github.wujun728.admin.page.constants;

import cn.hutool.core.util.StrUtil;
import io.github.wujun728.admin.util.StringUtil;

import java.util.*;
import java.util.stream.Collectors;

public class Opt {
    public static final String eq = "eq";
    public static final String notEq = "notEq";
    public static final String in = "in";
    public static final String notIn = "notIn";
    public static final String like = "like";
    public static final String likeIn = "likeIn";
    public static final String notLike = "notLike";
    public static final String notLikeIn = "notLikeIn";
    public static final String betweenAnd = "betweenAnd";
    public static final String less = "less";
    public static final String large = "large";
    public static final String lessEq = "lessEq";
    public static final String largeEq = "largeEq";

    public static final Map<String,String> sqlOptMap = new HashMap<>();
    static {
        sqlOptMap.put(eq,"=");
        sqlOptMap.put(notEq,"!=");
        sqlOptMap.put(like,"like");
        sqlOptMap.put(notLike,"not like");
        sqlOptMap.put(less,"<");
        sqlOptMap.put(lessEq,"<=");
        sqlOptMap.put(large,">");
        sqlOptMap.put(largeEq,">=");
    }

    public static boolean isSingleValue(String opt){
        return Arrays.asList(eq,notEq,like,notLike,less,large,lessEq,largeEq).contains(opt);
    }

    public static void getSql(String field,String opt,String type, String value,String format,StringBuffer sql, List<Object> values){
        if(StrUtil.isBlank(value)){
            return;
        }
        if(isSingleValue(opt)){
            Object realValue = DataType.getValue(type, value, format);
            if(realValue == null){
                return;
            }
            String sqlOpt = getSqlOpt(opt);
            if(StrUtil.isBlank(sqlOpt)){
                return;
            }
            if(DataType.isDate(type) && "yyyy-MM-dd".equals(format) && Arrays.asList(large,largeEq).contains(opt)){
                Calendar calendar = Calendar.getInstance();
                calendar.setTime((Date) realValue);
                calendar.set(Calendar.HOUR,0);
                calendar.set(Calendar.MINUTE,0);
                calendar.set(Calendar.SECOND,0);
                calendar.set(Calendar.MILLISECOND,0);
                realValue = calendar.getTime();
            }
            if(DataType.isDate(type) && "yyyy-MM-dd".equals(format) && Arrays.asList(less,lessEq).contains(opt)){
                Calendar calendar = Calendar.getInstance();
                calendar.setTime((Date) realValue);
                calendar.set(Calendar.HOUR,23);
                calendar.set(Calendar.MINUTE,59);
                calendar.set(Calendar.SECOND,59);
                calendar.set(Calendar.MILLISECOND,999);
                realValue = calendar.getTime();
            }

            if(isLike(opt)){
                realValue = StrUtil.format("%{}%",realValue);
            }
            String nfield = field;
            if(opt.startsWith("large") && field.endsWith("_from")){
                nfield = field.replace("_from","");
            }else if(opt.startsWith("less") && field.endsWith("_to")){
                nfield = field.replace("_to","");
            }
            sql.append(StrUtil.format(" and {} {} ? ",nfield,sqlOpt));
            values.add(realValue);
        }else{
            List<Object> realValues = DataType.getValues(type, value, format);
            if(realValues.isEmpty()){
                return;
            }
            if(in.equals(opt)){
                String args = StringUtil.concatStr(realValues.stream().map(e->"?").collect(Collectors.toList()),",");
                sql.append(StrUtil.format(" and {} in ({}) ",field,args));
                values.addAll(realValues);
            }else if(notIn.equals(opt)){
                String args = StringUtil.concatStr(realValues.stream().map(e->"?").collect(Collectors.toList()),",");
                sql.append(StrUtil.format(" and ({} is null or {} not in ({})) ",field,field,args));
                values.addAll(realValues);
            }else if(likeIn.equals(opt)){
                List<String> orSqls = realValues.stream().map(e -> StrUtil.format(" {} like ? ",field)).collect(Collectors.toList());
                sql.append(StrUtil.format(" and ({}) ",StringUtil.concatStr(orSqls," or ")));
                List<String> strValues = realValues.stream().map(e -> StrUtil.format("%{}%", e)).collect(Collectors.toList());
                values.addAll(strValues);
            }else if(notLikeIn.equals(opt)){
                List<String> orSqls = realValues.stream().map(e -> StrUtil.format(" {} not like ? ",field)).collect(Collectors.toList());
                sql.append(StrUtil.format(" and ( {} is null or ({})) ",field,StringUtil.concatStr(orSqls," and ")));
                List<String> strValues = realValues.stream().map(e -> StrUtil.format("%{}%", e)).collect(Collectors.toList());
                values.addAll(strValues);
            }else if(betweenAnd.equals(opt) && realValues.size() == 2){
                sql.append(StrUtil.format(" and {} between ? and ? ",field));

                if(DataType.isDate(type) && "yyyy-MM-dd".equals(format)){
                    Date date = (Date) realValues.get(0);

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime( date);
                    calendar.set(Calendar.HOUR,0);
                    calendar.set(Calendar.MINUTE,0);
                    calendar.set(Calendar.SECOND,0);
                    calendar.set(Calendar.MILLISECOND,0);
                    date = calendar.getTime();
                    values.add(date);

                    date = (Date) realValues.get(1);

                    calendar.setTime(date);
                    calendar.set(Calendar.HOUR,23);
                    calendar.set(Calendar.MINUTE,59);
                    calendar.set(Calendar.SECOND,59);
                    calendar.set(Calendar.MILLISECOND,999);

                    values.add(date);
                }else{
                    values.addAll(realValues);
                }
            }
        }
    }

    public static String getSqlOpt(String opt){
        return sqlOptMap.get(opt);
    }
    public static boolean isLike(String opt){
        return opt.toLowerCase().contains("like");
    }
}
