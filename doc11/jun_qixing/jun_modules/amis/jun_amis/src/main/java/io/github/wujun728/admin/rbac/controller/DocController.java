package io.github.wujun728.admin.rbac.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import io.github.wujun728.admin.db.service.JdbcService;
import io.github.wujun728.admin.page.constants.*;
import io.github.wujun728.admin.page.data.*;
import io.github.wujun728.admin.page.constants.*;
import io.github.wujun728.admin.page.data.*;
import io.github.wujun728.admin.page.service.DicService;
import io.github.wujun728.admin.page.service.FormService;
import io.github.wujun728.admin.page.service.PageService;
import io.github.wujun728.admin.rbac.service.InputParam;
import io.github.wujun728.admin.util.StringUtil;
import io.github.wujun728.admin.util.UrlUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController
@Slf4j
public class DocController {
    @Resource
    private JdbcService jdbcService;

    @Resource
    private DicService dicService;

    @Resource
    private PageService pageService;

    @Resource
    private FormService formService;

//    @GetMapping("/swagger-resources/configuration/ui")
//    public String ui(){
//        return "{\"deepLinking\":true,\"displayOperationId\":false,\"defaultModelsExpandDepth\":1,\"defaultModelExpandDepth\":1,\"defaultModelRendering\":\"example\",\"displayRequestDuration\":false,\"docExpansion\":\"none\",\"filter\":false,\"operationsSorter\":\"alpha\",\"showExtensions\":false,\"showCommonExtensions\":false,\"tagsSorter\":\"alpha\",\"validatorUrl\":\"\",\"supportedSubmitMethods\":[\"get\",\"put\",\"post\",\"delete\",\"options\",\"head\",\"patch\",\"trace\"],\"swaggerBaseUiUrl\":\"\"}";
//    }
//    @GetMapping("/swagger-resources")
//    public List<Object> swaggerResources(){
//
//        List<Map<String, Object>> menuTypes = dicService.options("menuType");
//        List<Object> list = new ArrayList<>();
//        for(Map<String, Object> item:menuTypes){
//            list.add(new JSONObject()
//                    .set("location","/openApi/"+item.get("value"))
//                    .set("name",item.get("label"))
//                    .set("swaggerVersion","3.0.3")
//                    .set("url","/openApi/"+item.get("value")));
//        }
//        list.add(new JSONObject()
//                .set("location","/v2/api-docs/magic-api/swagger2.json")
//                .set("name","MagicAPI接口")
//                .set("swaggerVersion","2.0")
//                .set("url","/v2/api-docs/magic-api/swagger2.json"));
//        list.add(new JSONObject()
//                .set("location","/v3/api-docs?group=默认接口")
//                .set("name","默认接口")
//                .set("swaggerVersion","3.0.3")
//                .set("url","/v3/api-docs?group=默认接口"));
//
//        return list;
//    }
    @GetMapping("/openApi/{menuType}")
    public JSONObject openApi(@PathVariable String menuType){
        // https://editor.swagger.io/?_ga=2.200610734.1652439241.1674876624-961416058.1667964762

        JSONObject schemas = new JSONObject();
        JSONArray tags = new JSONArray();
        JSONObject paths = new JSONObject();

        List<SysMenu> menus = jdbcService.find(SysMenu.class, new String[]{
                SysMenu.Fields.menuType,
                SysMenu.Fields.whetherButton
        }, new Object[]{
                menuType,
                Whether.NO
        });
        Set<String> apiKeys = new HashSet<>();
        for(SysMenu menu:menus){
            String url = menu.getUrl();
            if(StringUtils.isBlank(url) || !url.startsWith("/crud/")){
                continue;
            }
            String pageCode = url.substring("/crud/".length());
            Page page = pageService.get(pageCode);
            if(page != null){
                this.buildPageApi(page,schemas,tags,paths,apiKeys);
            }
        }

        schemas.set("响应",new JSONObject()
            .set("type","object")
            .set("title","响应")
            .set("properties",buildRespProps(null).set("data",null))
        );

        JSONObject api = new JSONObject()
            .set("openapi","3.0.3")
            .set("info",new JSONObject()
                .set("title","低代码开发平台")
                .set("description","描述信息")
                .set("termsOfService","http://swagger.io/terms/")
                .set("contact",new JSONObject()
                        .set("email","apiteam@swagger.io")
                )
                .set("license",new JSONObject()
                        .set("name","Apache 2.0")
                        .set("url","http://www.apache.org/licenses/LICENSE-2.0.html")
                )
                .set("version","1.0.11")
                .set("termsOfService","http://swagger.io/terms/")
                .set("termsOfService","http://swagger.io/terms/"))
            .set("externalDocs",new JSONObject()
                .set("description","Find out more about Swagger")
                .set("url","http://swagger.io")
            )
            .set("servers",new Object[]{
                    new JSONObject()
                    .set("url","https://petstore3.swagger.io/api/v3")
            })
            .set("tags",tags)
            .set("paths",paths)
            .set("components",new JSONObject()
                .set("schemas",schemas)
            )
        ;


        return api;
    }

    private void buildPageApi(Page page,JSONObject schemas, JSONArray tags, JSONObject paths,Set<String> apiKeys){
        String apiKey = StrUtil.format("page:{}",page.getCode());
        if(apiKeys.contains(apiKey)){
            return;
        }
        apiKeys.add(apiKey);
        addTag(tags,getName(page.getName()));
        paths.set("/admin/page/crudQuery/"+page.getCode(),new JSONObject()
            .set("post",new JSONObject()
                .set("tags",new String[]{getName(page.getName())})
                .set("summary","分页查询")
                .set("description","分页查询")
                .set("operationId",page.getCode()+"Query")
                .set("requestBody",new JSONObject()
                    .set("description",getName(page.getName())+"查询条件")
                    .set("content",new JSONObject()
                        .set("application/json",new JSONObject()
                            .set("schema",new JSONObject()
                                .set("$ref","#/components/schemas/"+StrUtil.format("分页查询«{}查询条件»",getName(page.getName())))
                            )
                        )
                    )
                )
                .set("responses",new JSONObject()
                    .set("200",new JSONObject()
                        .set("description","成功")
                        .set("content",new JSONObject()
                            .set("application/json",new JSONObject()
                                .set("schema",new JSONObject()
                                    .set("$ref",StrUtil.format("#/components/schemas/响应«响应参数«{}查询结果»»",getName(page.getName())))
                                )
                            )
                        )
                    )
                )
            )
        );

        JSONObject conditionProperties = new JSONObject();
        List<PageQueryField> queryFields = page.getQueryFields();
        List<String> queryFieldsRequired = new ArrayList<>();
        for (PageQueryField field : queryFields) {
            buildInputApi(field,conditionProperties,queryFieldsRequired,schemas,tags,paths,apiKeys);
        }

        List<String> resultFieldsRequired = new ArrayList<>();
        JSONObject resultProperties = new JSONObject();
        List<PageResultField> resultFields = page.getResultFields();
        for (PageResultField field : resultFields) {
            buildInputApi(field,resultProperties,resultFieldsRequired,schemas,tags,paths,apiKeys);
        }

        schemas.set(StrUtil.format("分页查询«{}查询条件»",getName(page.getName())),new JSONObject()
            .set("type","object")
            .set("properties",new JSONObject()
                .set("orderBy",new JSONObject()
                    .set("type","string")
                    .set("description","排序字段")
                )
                .set("orderDir",new JSONObject()
                    .set("type","string")
                    .set("description","正序asc/倒序desc")
                )
                .set("page",new JSONObject()
                    .set("type","integer")
                    .set("description","当前第几页")
                    .set("format","int32")
                )
                .set("perPage",new JSONObject()
                    .set("type","integer")
                    .set("description","每页多少条")
                    .set("format","int32")
                )
                .set("conditions",new JSONObject()
                    .set("description","查询条件")
                    .set("$ref","#/components/schemas/"+getName(page.getName())+"查询条件")
                )
            )
        );
        schemas.set(StrUtil.format("{}查询条件",getName(page.getName())),new JSONObject()
            .set("title",StrUtil.format("{}查询条件",getName(page.getName())))
            .set("type","object")
            .set("properties",conditionProperties)
            .set("required",queryFieldsRequired)
        );
        schemas.set(StrUtil.format("响应«响应参数«{}查询结果»»",getName(page.getName())),new JSONObject()
                .set("title",page.getName()+"查询结果")

                .set("type","object")
                .set("properties",
                    buildRespProps(
                        StrUtil.format("#/components/schemas/响应参数«{}查询结果»",getName(page.getName()))
                    )
                )
        )
        ;
        schemas.set(StrUtil.format("响应参数«{}查询结果»",getName(page.getName())),new JSONObject()
            .set("type","object")
            .set("title",StrUtil.format("响应参数«{}查询结果»",getName(page.getName())))
            .set("properties",new JSONObject()
                    .set("total",new JSONObject()
                            .set("type","integer")
                            .set("description","总数")
                            .set("format","int32")
                    )
                    .set("items",new JSONObject()
                            .set("type","array")
                            .set("description","明细行")
                            .set("items",new JSONObject()
                                    .set("$ref",StrUtil.format("#/components/schemas/{}查询结果",getName(page.getName())))
                            )
                    )
            )
        );
        schemas.set(StrUtil.format("{}查询结果",getName(page.getName())),new JSONObject()
            .set("type","object")
            .set("title",StrUtil.format("{}查询结果",getName(page.getName())))
            .set("properties",resultProperties)
            .set("required",resultFieldsRequired)
        );

        List<PageButton> pageButtons = page.getPageButtons();
        for (PageButton pageButton : pageButtons) {

            String optionType = pageButton.getOptionType();
            if (ActionType.PopForm.equals(optionType)) {
                //弹出表单
                buildFormApi(formService.get(pageButton.getOptionValue()),schemas,tags,paths,apiKeys);
            } else if (ActionType.PopPage.equals(optionType)) {
                //弹出页面
            } else if (ActionType.OpenNew.equals(optionType)) {
                //浏览器打开新页面
            } else if (ActionType.Ajax.equals(optionType)) {
                //ajax请求
                String url = getUrl(pageButton.getOptionValue());
                List<String> pathArgNames = UrlUtil.getPathArgNames(url);
                List<Object> args = new ArrayList<>();
                for(String argName:pathArgNames){
                    args.add(new JSONObject()
                            .set("name",argName)
                            .set("in","path")
                            .set("description",argName)
                            .set("required",true)
                            .set("style","simple")
                            .set("schema",new JSONObject()
                                    .set("type","string")
                            ));
                }
                paths.set(url,new JSONObject()
                        .set("get",new JSONObject()
                                .set("tags",new String[]{getName(page.getName())})
                                .set("summary",pageButton.getLabel())
                                .set("operationId",page.getCode()+pageButton.getLabel())
                                .set("parameters",args)
                                .set("responses",new JSONObject()
                                        .set("200",new JSONObject()
                                                .set("description","成功")
                                                .set("content",new JSONObject()
                                                        .set("application/json",new JSONObject()
                                                                .set("schema",new JSONObject()
                                                                        .set("$ref","#/components/schemas/响应")
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                );
            } else if (ActionType.PopIframe.equals(optionType)) {
                //弹出框加载iframe
            }
        }
        List<PageRef> pageRefs = page.getPageRefs();
        for (PageRef pageRef : pageRefs) {
            String refType = pageRef.getRefType();
            if(RefType.Page.equals(refType)){
                Page refPage = pageService.get(pageRef.getRefPageCode());
                buildPageApi(refPage,schemas,tags,paths,apiKeys);
            }else if(RefType.Form.equals(refType)){
                Form form = formService.get(pageRef.getRefPageCode());
                buildFormApi(form,schemas,tags,paths,apiKeys);
            }
        }
    }

    void buildInputApi(InputParam field,JSONObject properties,List<String> required,JSONObject schemas, JSONArray tags, JSONObject paths,Set<String> apiKeys){
        String remark = field.getLabel()+";";
        String example = null;
        String propType = null;
        String format = null;
        String type = field.getType();
        if (DataType.isDate(type)) {
            propType = "string";
            format = "date-time";
            remark += "日期类型,格式:" + field.getFormat();
        } else if (DataType.isImg(type)) {
            remark += "图片路径,多个图片用英文逗号隔开,最后斜杠为上传图片名称";
            example = "/download/241/abc.jpg,/download/241/123.png";
        } else if (DataType.isFile(type)) {
            propType = "string";
            remark += "文件路径,多个文件用英文逗号隔开,最后斜杠为上传文件名称";
            example = "/download/241/abc.zip,/download/241/123.jpg";
        } else if (DataType.isDic(type)) {
            propType = "string";
            remark += "数据字典(" + field.getFormat() + ");";
            List<Map<String, Object>> options = dicService.options(field.getFormat());
            StringBuilder sb = new StringBuilder();
            options.forEach(option -> {
                sb.append(option.get("value")).append(":").append(option.get("label")).append(";");
            });
            remark += sb.toString();
            if (!options.isEmpty()) {
                example = (String) options.get(0).get("value");
            }
        } else if (DataType.isStr(type)) {
            remark += "字符串";
            propType = "string";
        } else if (DataType.isNumber(type)) {
            propType = "integer";
            format = "int64";
            remark += "整数";
        } else if (DataType.isDouble(type)) {
            propType = "number";
            remark += "小数";
            format = "double";
        } else if(DataType.isSelector(type)){
            propType = "integer";
            format = "int64";

            Page page = pageService.get(field.getFormat());
            buildPageApi(page,schemas,tags,paths,apiKeys);
            remark += "选择器,请参考("+getName(page.getName())+"->分页查询);";
        }
        String fieldName = null;
        if(field instanceof FormField){
            fieldName = field.getField();
        }else{
            fieldName = StringUtil.toFieldColumn(field.getField());
        }

        properties.set(fieldName,new JSONObject()
            .set("type",propType)
            .set("description",remark)
            .set("format",format)
            .set("example",example)
        );

        if(ComponentType.InputTable.equals(field.getComponentType())){

            String formCode = field.getFormat().split(",")[0];
            Form form = formService.get(formCode);
            properties.set(fieldName,new JSONObject()
                .set("type","array")
                .set("items",new JSONObject()
                    .set("$ref",StrUtil.format("#/components/schemas/{}",getName(form.getName())))
                )
            );
            buildFormApi(form,schemas,tags,paths,apiKeys);
        }

        if (Whether.YES.equals(field.getMust())){
            required.add(fieldName);
        }
    }



    private void buildFormApi(Form form,JSONObject schemas, JSONArray tags, JSONObject paths,Set<String> apiKeys){
        String apiKey = StrUtil.format("form:{}",form.getCode());
        if(apiKeys.contains(apiKey)){
            return;
        }
        apiKeys.add(apiKey);

        String initApi = null;
        String saveApi = null;

        if(StrUtil.isNotBlank(form.getTableName())){
            initApi = StrUtil.format("/admin/common/{}/get",form.getCode());
            saveApi = StrUtil.format("/admin/common/{}/saveOrUpdate",form.getCode());
        }
        if(StrUtil.isNotBlank(form.getInitApi())){
            initApi = form.getInitApi();
        }
        if(StrUtil.isNotBlank(form.getApi())){
            saveApi = form.getApi();
        }
        if(StringUtils.isNotBlank(initApi) && initApi.contains(":")){
            initApi = initApi.split(":")[1];
        }
        if(StringUtils.isNotBlank(saveApi) && saveApi.contains(":")){
            saveApi = saveApi.split(":")[1];
        }
        if(StringUtils.isNotBlank(initApi)){
            if(UrlUtil.match(initApi,"/admin/common/{code}/get")){
                addTag(tags,getName(form.getName()));
                String formCode = initApi.substring("/admin/common/".length(), initApi.lastIndexOf("/"));
                Form initForm = formService.get(formCode);
                JSONObject props = new JSONObject();
                List<String> required = new ArrayList<>();
                for (FormField formField : initForm.getFormFields()) {
                    buildInputApi(formField,props,required,schemas,tags,paths,apiKeys);
                }

                paths.set(initApi,new JSONObject()
                    .set("get",new JSONObject()
                        .set("tags",new String[]{getName(form.getName())})
                        .set("summary",getName(initForm.getName())+"详情")
                        .set("operationId",initForm.getCode()+"Detail")
                        .set("parameters",new Object[]{
                                new JSONObject()
                                        .set("name","id")
                                        .set("in","query")
                                        .set("description","主键,id为空时是初始化参数")
                                        .set("required",false)
                                        .set("schema",new JSONObject()
                                        .set("type","integer")
                                        .set("format","int64")
                                )
                        })
                        .set("responses",new JSONObject()
                                .set("200",new JSONObject()
                                        .set("description","成功")
                                        .set("content",new JSONObject()
                                                .set("application/json",new JSONObject()
                                                        .set("schema",new JSONObject()
                                                                .set("$ref",StrUtil.format("#/components/schemas/响应«{}详情»",getName(initForm.getName())))
                                                        )
                                                )
                                        )
                                )
                        )
                    )

                );
                schemas.set(StrUtil.format("响应«{}详情»",getName(initForm.getName())),new JSONObject()
                    .set("title",StrUtil.format("响应«{}详情»",getName(initForm.getName())))
                    .set("type","object")
                    .set("properties",buildRespProps(
                            StrUtil.format("#/components/schemas/{}",getName(initForm.getName()))
                        )
                    )
                );
                schemas.set(StrUtil.format("{}",getName(initForm.getName())),new JSONObject()
                        .set("type","object")
                        .set("title",StrUtil.format("{}",getName(initForm.getName())))
                        .set("properties",props)
                        .set("required",required)
                );

            }
        }

        if(StringUtils.isNotBlank(saveApi)){
            if(UrlUtil.match(saveApi,"/admin/common/{code}/saveOrUpdate")){
                addTag(tags,getName(form.getName()));
                String formCode = saveApi.substring("/admin/common/".length(), saveApi.lastIndexOf("/"));
                Form saveForm = formService.get(formCode);

                JSONObject props = new JSONObject();
                List<String> required = new ArrayList<>();
                for (FormField formField : saveForm.getFormFields()) {
                    buildInputApi(formField,props,required,schemas,tags,paths,apiKeys);
                }

                paths.set(saveApi,new JSONObject()
                        .set("post",new JSONObject()
                                .set("tags",new String[]{getName(form.getName())})
                                .set("summary",getName(saveForm.getName())+"保存或更新")
                                .set("operationId",saveForm.getCode()+"SaveOrUpdate")
                                .set("requestBody",new JSONObject()
                                        .set("content",new JSONObject()
                                                .set("application/json",new JSONObject()
                                                        .set("schema",new JSONObject()
                                                                .set("$ref",StrUtil.format("#/components/schemas/{}",getName(saveForm.getName())))
                                                        )
                                                )
                                        )
                                )
                                .set("responses",new JSONObject()
                                        .set("200",new JSONObject()
                                                .set("description","成功")
                                                .set("content",new JSONObject()
                                                        .set("application/json",new JSONObject()
                                                                .set("schema",new JSONObject()
                                                                        .set("$ref",StrUtil.format("#/components/schemas/响应«{}详情»",getName(saveForm.getName())))
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )

                );
                schemas.set(StrUtil.format("响应«{}详情»",getName(saveForm.getName())),new JSONObject()
                        .set("title",StrUtil.format("响应«{}详情»",getName(saveForm.getName())))
                        .set("type","object")
                        .set("properties",buildRespProps(
                                StrUtil.format("#/components/schemas/{}",getName(saveForm.getName())))
                        )
                );
                schemas.set(StrUtil.format("{}",getName(saveForm.getName())),new JSONObject()
                        .set("type","object")
                        .set("title",StrUtil.format("{}",getName(saveForm.getName())))
                        .set("properties",props)
                        .set("required",required)
                );
            }
        }

    }

    private JSONObject buildRespProps(String ref){
        return new JSONObject()
            .set("msg",new JSONObject()
                    .set("description","提示信息")
                    .set("type","string")
            )
            .set("status",new JSONObject()
                    .set("type","integer")
                    .set("description","状态码;0:成功;1:未登录;2:无权限;9:错误;其他:错误;")
                    .set("format","int32")
            )
            .set("data",new JSONObject()
                    .set("description","数据")
                    .set("$ref",ref)
            );
    }

    private void addTag(JSONArray tags,String name){
        for(int i=0;i<tags.size();i++){
            String tagName = tags.getJSONObject(i).getStr("name");
            if(tagName.equals(name)){
                return;
            }
        }
        tags.add(new JSONObject().set("name",name));
    }

    private String getUrl(String url){
        if(url == null){
            return null;
        }
        return url.replace("get:","").replace("post:","").replace("$","");
    }

    private String getName(String name){
        if(name == null){
            return null;
        }
        return name.replace("列表","").replace("表单","").replace("管理","");
    }
}
