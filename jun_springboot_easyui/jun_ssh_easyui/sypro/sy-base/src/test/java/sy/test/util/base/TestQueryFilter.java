package sy.test.util.base;

import org.nutz.json.Json;

import sy.util.base.QueryFilter;

/**
 * 演示QueryFilter参数传递的使用方式
 * 
 * http://git.oschina.net/sphsyv/sypro
 * 
 * @author 孙宇
 *
 */
public class TestQueryFilter {

	public static void main(String[] args) {

		QueryFilter filter = new QueryFilter();
		filter.addFilter("Q_name_like", "孙");// name like '%孙%'
		filter.addFilter("Q_name_leftLike", "孙");// name like '%孙'
		filter.addFilter("Q_name_rightLike", "孙");// name like '孙%'

		filter.addFilter("Q_id_=_Long", "1");// id = 1
		filter.addFilter("Q_id_EQ_Long", "2");// id = 2
		filter.addFilter("Q_id_=_Short", "3");// id = 3
		filter.addFilter("Q_id_=_String", "4");// id = 4
		filter.addFilter("Q_id_=_int", "5");// id = 5
		filter.addFilter("Q_id_=_Integer", "6");// id = 6

		filter.addFilter("Q_id_!=_Long", "1");// id != 1

		filter.addFilter("Q_age_>_Short", "10");// age > 10
		filter.addFilter("Q_age_GT_Short", "15");// age > 15

		filter.addFilter("Q_age_>=_int", "11");// age >= 11
		filter.addFilter("Q_age_GE_Long", "16");// age >= 16

		filter.addFilter("Q_age_<_Short", "10");// age < 10
		filter.addFilter("Q_age_LT_Short", "15");// age < 15

		filter.addFilter("Q_age_<=_int", "11");// age <= 11
		filter.addFilter("Q_age_LE_Long", "16");// age <= 16

		filter.addFilter("Q_id_In_Long", "1,2,3,4");// id in (1,2,3,4)
		filter.addFilter("Q_id_NotIn_Long", "1,2,3,4");// id not in (1,2,3,4)

		filter.addFilter("Q_company_isNull", "null");// company is null
		filter.addFilter("Q_company_isNotNull", "null");// company is not null

		filter.addFilter("Q_birthday_=_Date", "2014-01-01");// birthday = '2014-01-01'
		filter.addFilter("Q_birthday_EQ_Date", "2014-01-01");// birthday = '2014-01-01'

		filter.addFilter("Q_birthday_>_Date", "2014-01-01");// birthday > '2014-01-01'
		filter.addFilter("Q_birthday_GT_Date", "2014-01-01");// birthday > '2014-01-01'

		filter.addFilter("Q_birthday_>=_Date", "2014-01-01");// birthday >= '2014-01-01'
		filter.addFilter("Q_birthday_GE_Date", "2014-01-01");// birthday >= '2014-01-01'

		filter.addFilter("Q_birthday_<_Date", "2014-01-01");// birthday < '2014-01-01'
		filter.addFilter("Q_birthday_LT_Date", "2014-01-01");// birthday < '2014-01-01'

		filter.addFilter("Q_birthday_<=_Date", "2014-01-01");// birthday <= '2014-01-01'
		filter.addFilter("Q_birthday_LE_Date", "2014-01-01");// birthday <= '2014-01-01'

		filter.addFilter("Q_birthday_=_DateTime", "2014-01-01 00:00:00");// birthday = '2014-01-01'
		filter.addFilter("Q_birthday_EQ_DateTime", "2014-01-01 00:00:00");// birthday = '2014-01-01'

		// 其他就不一一列举了，大部分都支持

		System.out.println(Json.toJson(filter.getParams()));

	}

}
