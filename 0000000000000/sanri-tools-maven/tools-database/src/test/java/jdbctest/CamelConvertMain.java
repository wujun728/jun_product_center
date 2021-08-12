package jdbctest;

import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;
import com.sanri.tools.modules.database.dtos.meta.ActualTableName;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.util.SelectUtils;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.junit.Test;

import java.io.StringReader;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CamelConvertMain {
    // jsqlparser 解析
    CCJSqlParserManager parserManager = new CCJSqlParserManager();

    @Test
    public void tes22t() throws JSQLParserException {
        String sql = "select * from mct.mct_event_handler";
        Select select = (Select) parserManager.parse(new StringReader(sql));
        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
        List<String> tableList = tablesNamesFinder.getTableList(select);
        System.out.println(tableList);
    }

    @Test
    public void testBuildSelect(){
        String catalog = "xxdb";
        ActualTableName main = new ActualTableName(catalog,"mct","mct_event_common");
        ActualTableName sub = new ActualTableName(catalog,"mct","mct_event_handler");

        Table mainTable = new Table(main.getSchema(),main.getTableName());
        mainTable.setAlias(new Alias("a"));
        Table subTable = new Table(sub.getSchema(),sub.getTableName());
        subTable.setAlias(new Alias("b"));

        Select select = new Select();
        PlainSelect plainSelect = new PlainSelect();
        SelectItem selectItem = new AllTableColumns(mainTable);
        plainSelect.setSelectItems(Collections.singletonList(selectItem));
        plainSelect.setFromItem(mainTable);
        System.out.println(plainSelect.toString());
    }

    @Test
    public void testBuilsssdSelect(){
        String catalog = "xxdb";
        ActualTableName main = new ActualTableName(catalog,"mct","mct_event_common");
        ActualTableName sub = new ActualTableName(catalog,"mct","mct_event_handler");

        Table mainTable = new Table(main.getSchema(),main.getTableName());
        mainTable.setAlias(new Alias("a"));
        Table subTable = new Table(sub.getSchema(),sub.getTableName());
        subTable.setAlias(new Alias("b"));

        Select select = SelectUtils.buildSelectFromTableAndExpressions(mainTable, new Column("b.*"));
        System.out.println(select);

        EqualsTo equalsTo = new EqualsTo();
        equalsTo.setLeftExpression(new Column("a.uuid"));
        equalsTo.setRightExpression(new Column("b.event_record_id"));
        SelectUtils.addJoin(select,subTable,equalsTo);

        System.out.println(select);
    }

    @Test
    public void test(){
        String orderColumn = "orderColumn";
        //输入是LOWER_CAMEL，输出是LOWER_UNDERSCORE
        orderColumn = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, orderColumn);
        System.out.println(orderColumn);//order_column

        orderColumn = "orderColumn";
        //输入是LOWER_CAMEL，输出是UPPER_CAMEL
        orderColumn = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL,orderColumn);
        System.out.println(orderColumn);//OrderColumn

        orderColumn = "order_column";
        //输入是LOWER_UNDERSCORE，输出是LOWER_CAMEL
        orderColumn = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,orderColumn);
        System.out.println(orderColumn);//orderColumn

        Converter<String, String> stringStringConverter = CaseFormat.LOWER_UNDERSCORE.converterTo(CaseFormat.LOWER_CAMEL);
        String order_column = stringStringConverter.convert("order_column");
        System.out.println(order_column);

        String order_column1 = CaseFormat.LOWER_UNDERSCORE.converterTo(CaseFormat.UPPER_CAMEL).convert("order_column");
        System.out.println(order_column1);
    }
}
