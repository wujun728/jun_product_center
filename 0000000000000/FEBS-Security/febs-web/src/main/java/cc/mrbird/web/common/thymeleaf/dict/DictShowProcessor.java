package cc.mrbird.web.common.thymeleaf.dict;

import cc.mrbird.system.domain.Dict;
import cc.mrbird.system.service.DictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring5.context.SpringContextUtils;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;

public class DictShowProcessor extends AbstractElementTagProcessor {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String TAG_NAME = "show";// 标签名
    private static final int PRECEDENCE = 300;
    private static final String FIELD_NAME = "fieldName";// 字典编码
    private static final String KEYY = "keyy";// 字典项值

    public DictShowProcessor(String dialectPrefix) {
        super(TemplateMode.HTML, dialectPrefix, TAG_NAME, true, null, false, PRECEDENCE);

    }

    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
                             IElementTagStructureHandler structureHandler) {
        String fieldName = tag.getAttribute(FIELD_NAME).getValue();
        String keyy = tag.getAttribute(KEYY).getValue();

        final IEngineConfiguration configuration = context.getConfiguration();
        final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
        final IStandardExpression expression = parser.parseExpression(context, keyy);
        final String realvalue = String.valueOf(expression.execute(context));

        ApplicationContext appCtx = SpringContextUtils.getApplicationContext(context);
        DictService dictService = appCtx.getBean(DictService.class);
        Dict dict = dictService.findDictByFieldNameAndKeyy(fieldName, realvalue);

        if (log.isDebugEnabled()) {
            log.debug("dict== fieldName:{}, keyy:{}, realvalue:{}", fieldName, keyy, realvalue);
        }
        String label = "";
        if (dict != null) {
            label = dict.getValuee();
        }
        structureHandler.replaceWith(label, false);
    }


}
