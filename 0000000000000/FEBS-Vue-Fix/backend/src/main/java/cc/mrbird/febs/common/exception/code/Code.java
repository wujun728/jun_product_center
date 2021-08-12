package cc.mrbird.febs.common.exception.code;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/5/9 14:08
 * @description：状态码枚举类
 * @version: 1.0
 */
public enum Code {
    /*
    OK[GET]：服务器成功返回用户请求的数据，该操作是幂等的（Idempotent）
     */
    C200(200, "Success"),
    /*
    CREATED[POST/PUT/PATCH]：用户新建或修改数据成功
     */
    C201(201, "CREATED"),
    /*
    Accepted[*]：表示一个请求已经进入后台排队（异步任务）
     */
    C202(202, "Accepted"),
    /*
   NO CONTENT - [DELETE]：用户删除数据成功
    */
    C204(204, "no content"),
    /*
    Not Modified：客户端使用缓存数据
     */
    C301(304, "Not Modified"),
    /*
    INVALID REQUEST - [POST/PUT/PATCH]：用户发出的请求有错误，服务器没有进行新建或修改数据的操作，该操作是幂等的。
     */
    C400(400, "INVALID REQUEST"),
    /*
    Unauthorized - [*]：表示用户没有权限（令牌、用户名、密码错误）
     */
    C401(401, "Unauthorized"),
    /*
    Forbidden - [*]：表示用户得到授权（与401错误相对），但是访问是被禁止的
     */
    C403(403, "Forbidden"),
    /*
    NOT FOUND - [*]：用户发出的请求针对的是不存在的记录，服务器没有进行操作，该操作是幂等的
     */
    C404(404, "Not Found"),
    /*
    method not allowed：该http方法不被允许
     */
    C405(405, "method not allowed"),
    /*
    Not Acceptable - [GET]：用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）
     */
    C406(406, "Not Acceptable"),
    /*

     */
    C409(409, "请求冲突"),
    /*
    Gone -[GET]：用户请求的资源被永久删除，且不会再得到的。例如文件下载时，文件不存在。
     */
    C410(410, "Gone"),
    /*
    请求体过大。比如说，服务器要求上传文件不能超过 5M，但是我们 POST 了 10M，这时候就返回 413。
     */
    C412(413, "Too Many"),
    /*
    请求的 URI 太长了。比如说，我们提供了太多的 Query 参数，以至于超过了服务器的限制，这时候可以返回 414。
     */
    C414(414, "Error Format"),
    /*
    不支持的媒体类型。例如上传文件只允许png图片，上传gif文件时，此时返回415
     */
    C415(415, "Error Type"),
    /*
    Unprocesable entity - [POST/PUT/PATCH]：当创建一个对象时，发生一个验证错误。请求格式正确，但语义错误。此时错误描述信息中最好有错误详情。
     */
    C422(422, "Unprocesable entity "),
    /*
    too many request - 请求过多
     */
    C429(429, "too many request"),
    /*
    INTERNAL SERVER ERROR - [*]：服务器发生错误，用户将无法判断发出的请求是否成功
     */
    C500(500, "Internal Server Error"),
    /*
    Token过期
     */
    C514(514, "TOKEN_EXPIRE"),
    /*
    错误码，根据响应信息中的msg参数展示具体错误信息。
     */
    C999(999, "Error"),

    /*******************************角色,登录,菜单,字典,后台参数配置:4001200*******************************/
    /**
     * 角色名称不能为空
     */
    RoleNameIsNotEmpty(4001201, "Role Name can not Empty"),

    /**
     * 角色状态不能为空
     */
    RoleStatusIsNotEmpty(4001202, "Role status can not Empty"),

    /**
     * 角色添加失败
     */
    RoleAddFailed(4001204, "Role Add Failed"),

    /**
     * 角色更新失败
     */
    RoleUpdateFailed(4001205, "Role Update Failed"),

    /**
     * RoleName重复
     */
    RoleNameIsSame(4001206, "Role name duplication"),

    /**
     * 更新时RoleId不能为空
     */
    UpdateRoleIdIsNotEmpty(4001207, " Update Role roleId must not Empty"),

    /**
     * 根据userId获取roleTree 失败
     */
    GetRoleTreeByUserIDFiled(4001208, "Get Role Info By User Failed"),

    /**
     * 权限分配时roleId不能为空
     */
    PermissionRoleIdIsNotEmpty(4001209, "Set Permission roleId must not Empty"),

    /**
     * 权限分配时Permissions不能为空
     */
    PermissionsIsNotEmpty(4001210, "Permissions must not Empty"),

    /**
     * 权限分配错误
     */
    PermissionsRoleIsFailed(4001211, "Allocation permission error"),

    /**
     * 删除角色IDS不能为空
     */
    RoleDeleteIdsNotEmpty(4001212, "Delete Role roleId can not Empty"),

    /**
     * 不能删除超级管理员
     */
    RoleCanNotDeleteAdmin(4001213, "You can't delete SuperAdmin"),

    /**
     * 删除角色失败
     */
    RoleDeleteFailed(4001214, "Delete Role Failed"),

    /**
     * 查询角色失败
     */
    RoleQueryFailed(4001215, "Query Role Failed"),
    /**
     * 设置角色
     */
    UserOrRoleEmpty(4001216, "user or role is empty."),
    /**
     * 超级管理员不可修改
     */
    NoChangeAdmin(4001217, "No change admin."),
    /**
     * 用户ids不能为空
     */
    UserIdEmpty(4001218, "ids is empty."),
    /**
     * user已存在
     */
    UserExist(4001219, "User is exist."),
    /**
     * 字典的NUM只能是数字,并且大于0
     */
    DictNumberWrong(4001220, "Dict Num must be numeric and greater than 0"),
    /**
     * 字典的子字典不能为空,并且值和Code不能为空
     */
    DictItemWrong(4001221, "Dict Child Node can not  Empty and Child Node must have code and value"),

    /**
     * 新增字典失败
     */
    DictAddFailed(4001222, "Dict Add Failed"),

    /**
     * 字典表名字已经被使用
     */
    DictNameHasBeenUser(4001223, "The dictionary name has been used"),

    /**
     * Dict ID 不能为空,或超过11位
     */
    DictIdWrong(4001224, "Dict Id is empty or Dict Id length over 11 "),

    /**
     * 更新字典失败
     */
    DictUpdateFailed(4001225, "Dict Update Failed"),

    /**
     * 删除字典ID不能为空
     */
    DictIdsIsNull(4001226, "Dict ids is Empty"),

    /**
     * 字典status 只能是0或1
     */
    DictStatusWrong(4001227, "Dict Status must 0 or 1"),

    /**
     * 删除字典失败
     */
    DictDeleteFailed(4001228, "Dict Delete Failed"),

    /**
     * 字典status 不能为空
     */
    DictStatusNotEmpty(4001229, "Dict Status can not empty"),
    /**
     * Dict对象不存在,修改失败
     */
    DictObjectIsEmpty(4001230, "Dict Object is empty ,Update Failed"),

    /**
     * 菜单查询失败
     */
    MenuQueryWrong(4001231, "Menu get Failed"),

    /**
     * 菜单状态只能是0,1
     */
    MenuStatusWrong(4001232, "Menu Status must 0 or 1"),

    /**
     * 账号密码错误
     */
    UserLoginFailedUserNameOrPassword(4001240, "Incorrect account or password, please enter again"),
    /**
     * 登录失败
     */
    UserLoginFailed(4001241, "User Login Filed"),
    /**
     * 当前用户没有许可
     */
    UserDoNotHavePermission(4001242, " The user has not configured permissions "),
    /**
     * 得到用户基本信息失败
     */
    UserInformationGetFailed(4001243, " Failed to get user information "),

    /**
     * 字典的Code 不能为空 或Code值超过了255
     */
    DictCodeWrong(4001244, "Dict Code is empty  or Dict Code length over 255 "),

    /**
     * 字典的Value不能为空 value值超过了255
     */
    DictValueWrong(4001245, "Dict Value is empty or Dict Value length over 255"),

    /**
     * 字典的tips不能为空
     */
    DictTipsWrong(4001246, "Dict Tips is empty or Dict Tips length over 255"),

    /**
     * 查询字典失败
     */
    DictQueryFailed(4001247, "Query Dict Failed"),

    /**
     * 当前菜单Code值已经存在
     */
    MenuCodeIsExists(4001248, "This Menu Code is Already Exists "),

    /**
     * 添加新菜单失败
     */
    MenuAddFailed(4001249, "Add Menu Failed"),

    /**
     * 菜单名称不能为空并且不能超过255个字符
     */
    MenuNameIsWrong(4001250, "Menu name can not empty and menu name must be less than 255"),

    /**
     * 菜单编码不能为空并且不能超过255个字符
     */
    MenuCodeIsWrong(4001251, "Menu code can not empty and menu code must be less than 255"),
    /**
     * 是否菜单字段有误
     */
    MenuIsMenuIsWrong(4001252, "Menu isMenu can not empty and isMenu value must (0 or 1)"),

    /**
     * 菜单级别不能为空
     */
    MenuLevelIsWrong(4001253, "Menu levels can not empty and levels must  more than 0"),

    /**
     * 菜单排序不能为空
     */
    MenuNumIsWrong(4001254, "Menu num can not empty and num must more than 0"),
    /**
     * 菜单父菜单ID不能为空
     */
    MenuParentIdIsWrong(4001255, "Menu parentId can not empty and parentId must more than 0"),
    /**
     * 菜单URL不能为空
     */
    MenuUrlIsWrong(4001256, "Menu url can not empty and url must be less than 255"),

    /**
     * 菜单Id不能为空
     */
    MenuIdIsWrong(4001257, "Menu id can not empty and id must more than 0"),

    /**
     * 子菜单的Code不能跟父菜单Code一样
     */
    MenuCodeIsSame(4001258, "Submenu code can not be the same as parent menu code"),

    /**
     * 菜单删除出错
     */
    MenuDeleteFailed(4001259, "Menu delete Failed"),
    /**
     * 编辑菜单失败
     */
    MenuEditFailed(4001260, "Menu edit Failed"),

    /**
     * 删除菜单Ids是空
     */
    MenuIdsIsWrong(4001261, "Menu ids can not empty"),

    /**
     * 菜单ID不存在,请输入正确的菜单ID
     */
    MenuIdIsNull(4001262, "Menu Object is empty , please check menu id"),

    /**
     * 菜单的Id不能和Pid一致
     */
    MenuIdHaveSameAsPid(4001263, "Menu id can not be the same as pid"),

    /**
     * 登录模式错误
     */
    LoginAuthModeIsWrong(4001264, "Login authMode is wrong "),

    /**
     * 授权码过期
     */
    TotPCodeError(4001265, "Dynamic password error"),

    /**
     * AuthCode不为空
     */
    TotAuthCodeEmpty(4001266, "Auth Code can not Empty"),

    /**
     * 账号密码不能为空
     */
    UserNameOrPasswordIsEmpty(4001269, "account  or password can not be empty"),
    /**
     * 参数key 不能为空
     */
    CfgNameNotBlank(4001270, "System configuration parameter name cannot be empty"),
    /**
     * 参数key 最大100
     */
    CfgNameMaxLength(4001271, "System configuration parameter name length cannot be greater than 200"),
    /**
     * 参数值 不能为空
     */
    CfgValueNotBlank(4001272, "System configuration parameter value cannot be empty"),
    /**
     * 参数值 最大3000
     */
    CfgValueMaxLength(4001273, "System configuration parameter value length cannot be greater than 200"),
    /**
     * 参数描述 不能为空
     */
    CfgDescNotBlank(4001274, "System configuration parameter description cannot be empty"),
    /**
     * 参数描述 最大200
     */
    CfgDescMaxLength(4001275, "System configuration parameter description length cannot be greater than 200"),
    /**
     * 参数id 不能为空
     */
    CfgIdNotNull(4001276, "System configuration parameter id cannot be empty"),
    /**
     * 用户修改,account不能和已有账户重复
     */
    DuplicateAccount(4001277,"The Account is already exists."),
    /**
     * 用户登录名长度超过45
     */
    AccountTooLong(4001278,"account cannot be empty or more than 45."),
    /**
     * 密码长度超过45或者空
     */
    PasswordTooLong(4001279,"password cannot be empty or more than 45."),
    /**
     * 用户名称长度超过45
     */
    NameTooLong(4001280,"name more than 45."),
    /**
     * birthday不是日期类型
     */
    BirthdayFormatError(4001281,"The Birthday format is incorrect."),
    /**
     * 性别只能是1,2
     */
    SexDefault(4001282,"The Sex is only Male or Female."),
    /**
     * 手机号格式有误
     */
    PhoneFormatError(4001283,"The Phone format error."),
    /**
     * 邮箱格式有误
     */
    EmailFormatError(4001284,"The Email format error."),
    /**
     * 用户修改id必须传参且id存在
     */
    ExistUserId(4001285,"Id is not exist."),
    /**
     * 记录退出日志失败
     */
    ExitLogFailed(4001286,"Exit log insert failed"),
    /**
     * 账号被冻结
     */
    UserStatusHasWrong(4001287,"current account is Frozen"),
    /**
     * 系统用户登录账号不允许修改的限制
     */
    NoModifyAccout(4001288,"the account is not allowed."),


    /*******************************角色,登录,菜单,字典,后台参数配置:4001200*******************************/

    /****************************** 日志模块:4001800 ***** 开始 ***************************************/
    /**
     * 日志导出数据条数过大
     */
    ExportLogNumberToBig(4001802, "There are too many exports. Please adjust the export conditions"),

    /**
     * 查询远程数据库超时
     */
    ReadingRemoteTimeOut(4001803, "Remote Service Address Error or Connection Timeout"),

    /**
     * 导出日志失败
     */
    ExportLogFailed(4001805, "Export Data Failed"),


    /**
     * 结束时间太小,不能小于3600s
     */
    EndTimeToShort(4001814, "The end time is too short"),

    /**
     * 开始时间不能大于结束时间
     */
    StartTimeNoGtEndTime(4001815, "The start time should not be greater than the end time"),

    /**
     * 查询时间不能超过一个月
     */
    QueryTimeNotMoreOneMonth(4001816, "Queries should not exceed one month "),

    /**
     * Addr_type 必须是4或6
     */
    AddrTypeValueNotMatch(4001817, "addr_type Value only 4 or 6"),

    /**
     * 导出Excel失败
     */
    ExportExcelFailed(4001823, "Export Excel Failed"),

    /**
     * 传递Number类型必须大于等于O
     */
    NumberMustGtThan0(4001824, "Number error, not negative "),

    /**
     * Log Query StartTime 不能比当前时间大
     */
    QueryStartTimeMustLtNowTime(4001825, "Start time must be less than the current time "),

    /**
     * 查询时间不能超过当前时间
     */
    QueryTimeExceedCurrentTime(4001830,"Query time cannot exceed current time"),

    /**
     * param 不能为空
     */
    ExecutionParamWrong(4001834,"execution param can not empty"),
    /******************************** 日志模块 **** 结束 *************************************/

    /******************************** 定时任务模块:4001900  **** 开始 ***********************************/
    /**
     * 定时任务单次执行时,interval必须等于0
     */
    OneTimeIntervalMustBeZero(4001901, "when scheduleType is One-Time,interval must be 0"),

    /**
     * 定时任务设置为按照天间隔执行时,间隔天必须大于0
     */
    DayIntervalMustBeGTZero(4001902, "Timed tasks are set to execute at day intervals,Interval days must be greater than 0"),

    /**
     * 定时任务设置为间隔周执行时,无法获取周几执行
     */
    WeekIntervalFailOfWeekNo(4001903, "When a timed task is set to Interval week execution, it is not possible to get a few weeks of execution"),

    /**
     * 定时任务设置为按照周间隔执行时,间隔周必须大于0
     */
    WeekIntervalMustBeGTZero(4001904, "When a timed task is set to execute at a weekly interval, the interval week must be greater than 0"),

    /**
     * 定时任务设置为按照间隔月循环执行时,开始月份取自startDate的开始月份,month不应该有值
     */
    MonthIntervalMonthMustBeNull(4001905, "When a timed task is set to execute at a monthly interval,the month should not have a value"),

    /**
     * 定时任务设置为按照间隔月循环执行时,无法确定是按照某个月的第几周的周几执行还是按照某个月的某一天执行
     */
    MonthInterValFailOfDayOrWeek(4001906, "When a timed task is set to execute at a monthly interval,It is not possible to determine whether to perform on the week of the week of a month or on a given day of the month"),

    /**
     * 定时任务设置为按照某年某月循环执行时,无法确定是按照某个月的第几周的周几执行还是按照某个月的某一天执行
     */
    YearInterValFailOfDayOrWeek(4001907, "When a timed task is set to be performed at a monthly cycle in a year,It is not possible to determine whether to perform on the week of the week of a month or on a given day of the month"),

    /**
     * 定时任务设置为按照每年某某月执行时,无法获取哪些月执行
     */
    YearInterValFailOfMonth(4001908, "Timed tasks are set to be executed on a monthly basis, and it is not possible to get which months to perform"),

    /**
     * 内置时间计划不允许修改
     */
    IsInitializeUpdate(4001909, "Built-in time schedule is not allowed to be modified"),

    /**
     * 时间计划被策略对象引用
     */
    PolicyTschedule(4001910, "Schedules is referenced by a policy with ，Please remove the reference relationship before modifying  it."),
    /**
     * 时间计划被离线任务引用
     */
    TaskTschedule(4001911, "Schedules is referenced by an offline report task with ,Please remove the reference relationship before modifying  it."),

    /**
     * 时间计划被策略对象引用
     */
    PolicyTscheduleDel(4001912, "Schedules is referenced by a policy with ，Please remove the reference relationship before deleting  it."),
    /**
     * 时间计划被离线任务引用
     */
    TaskTscheduleDel(4001913, "Schedules is referenced by an offline report task with ,Please remove the reference relationship before deleting  it."),
    /**
     * IsInitialize必须为空
     */
    IsInitializeEmpty(4001914, "IsInitialize must be empty"),
    /**
     * 名称不能为空
     */
    TscheduleNameEmpty(4001915, "Name can not be empty"),
    /**
     * 计划类型不能为空
     */
    TscheduleTypeEmpty(4001916, "Schedules type can not be empty"),
    /**
     * 开始时间类型不能为空
     */
    TscheduleStartTimeEmpty(4001917, "Start Time type can not be empty"),
    /**
     * 结束时间类型不能为空
     */
    TscheduleEndTimeEmpty(4001918, "End Time type can not be empty"),
    /**
     * 开始日期类型不能为空
     */
    TscheduleStartDateEmpty(4001919, "Start Date type can not be empty"),
    /**
     * 结束日期类型不能为空
     */
    TscheduleEndDateEmpty(4001920, "End Date type can not be empty"),
    /**
     * 自定义间隔必须为0
     */
    Tscheduleinterval(4001921, "Custom Every must be 0"),
    /**
     * OnDay必须为空
     */
    TscheduleOnDayEmpty(4001922, "onDay must be empty"),
    /**
     * onWeek必须为空
     */
    TscheduleOnWeekEmpty(4001923, "onWeek must be empty"),
    /**
     * onMonth必须为空
     */
    TschedulOnMonthEmpty(4001924, "onMonth must be empty"),
    /**
     * weekNo必须为空
     */
    TscheduleWeekNoEmpty(4001925, "weekNo must be empty"),
    /**
     * 间隔不能为空
     */
    TscheduleIntervalEmpty(4001926, "interval Can not be empty"),
    /**
     * 间隔必须大于0
     */
    TscheduleIntervalRange(4001927, "interval must be greater than 0"),
    /**
     * OnWeek不能为空
     */
    TscheduleOnWeekNotEmpty(4001928, "onWeek Can not be empty"),
    /**
     * OnWeek格式错误
     */
    TscheduleOnWeekFormat(4001929, "onWeek format error"),
    /**
     * OnWeek不大于7
     */
    TscheduleOnWeekRange(4001930, "onWeek not greater than 7"),
    /**
     * OnMonth和Interval不能同时为空
     */
    OnMonthAndInterval(4001931, "onMonth and Interval Can't be empty at the same time"),
    /**
     * Interval和OnMonth不能同时有值
     */
    OnMonthAndIntervalNotEmpty(4001932, "interval and onMonth Can't have value at the same time"),
    /**
     * 间隔必须大于或等于0
     */
    IntervalNotRange(4001933, "interval must be greater than or equal to 0"),
    /**
     * OnMonth不能为空
     */
    TscheduleOnMonthNotEmpty(4001934, "onMonth Can not be empty"),
    /**
     * OnMonth格式错误
     */
    TscheduleOnMonthFormat(4001935, "onMonth format error"),
    /**
     * OnMonth不大于12
     */
    TscheduleOnMonthRange(4001936, "onMonth not greater than 12"),
    /**
     * Onday和Weekno必须有一个不能为空的
     */
    OnDayAndOnWeekNot(4001937, "onDay and weekNo There must be one that cannot be empty"),
    /**
     * OnDay和WeekNo不能同时有值
     */
    OnDayAndOnWeekEmpty(4001938, "onDay and weekNo Can't have value at the same time"),
    /**
     * OnDay格式错误
     */
    TscheduleOnDayFormat(4001939, "onDay format error"),
    /**
     * OnDay不大于31
     */
    TscheduleOnDayRange(4001940, "onDay not greater than 31"),
    /**
     * OnWeek格式错误
     */
    TscheduleWeekNoFormat(4001941, "onWeek format error"),
    /**
     * OnWeek不大于4
     */
    TscheduleWeekNokRange(4001942, "onWeek not greater than 4"),
    /**
     * startDate的时间格式错误
     */
    StartDateFormat(4001943, "startDate Error in time format"),
    /**
     * EndDated的时间格式错误
     */
    EndDateFormat(4001944, "EndDate Error in time format"),
    /**
     * StartTime时间格式错误
     */
    StartTimeFormat(4001945, "StartTime Error in time format"),
    /**
     * EndTime时间格式错误
     */
    EndTimeFormat(4001946, "EndTime Error in time format"),
    /**
     * 开始日期和开始时间不应小于当前时间加上2分钟
     */
    StartDateRange(4001947, "startDate and startTime should not be less than the current time Add 2 minutes"),
    /**
     * statTime必须小于endTime
     */
    StartTimeAndEndTime(4001948, "statTime must be less than endTime"),
    /**
     * statDate必须小于endDate
     */
    StartAndEndDate(4001949, "statDate must be less than endDate"),
    /**
     * scheduleType格式错误
     */
    ScheduleTypeFormat(4001950, "scheduleType format error"),
    /**
     * 无法删除内置时间计划
     */
    IsInitializeDekError(4001952, "Built-in time schedule cannot be deleted"),
    /**
     * scheduleList不能为空
     */
    ScheduleListEmpty(4001953, "ScheduleList can not be empty"),

    /**
     * 错误的时间计划类型
     */
    WrongScheduleType(4001954, "ScheduleType must be single|daily|weekly|monthly"),

    /******************************** 公共:4009000 **** 开始 ***********************************/

    /**
     * 请求参数orderBy中的排序字段名称不存在!
     */
    OrderByFieldNotExist(4009003, "The sorting field name in the request parameter orderBy does not exist."),

    /**
     * 不存在的配置
     */
    NotExistConfiguration(4009004, "Not exist configuration."),

    /**
     * 请求参数中的isValid参数的值，只能为0(停用)和1(启用)
     */
    IsValidIsWrongRange(4009007, "The value of the isValid parameter in the request parameters can only be 0 (disable) and 1 (enable)."),

    /**
     * 新增不能携带ID
     */
    NoExistId(4009008, "Add Can not contain id."),

    /**
     * update disable or enable must contain id
     */
    ExistId(4009009, "update,disable or enable must contain id or id is not exist."),

    /**
     * 被引用不允许更改有效标识
     */
    IsReference(4009010, "the record is referenced and are not allowed to be updated Valid-identification."),

    /**
     * disable,有效标识只能是0
     */
    DisableValid(4009011, "if disable,Valid-identification must be 0."),

    /**
     * enable,有效标识只能是1
     */
    EnableValid(4009012, "if enable,Valid-identification must be 1."),

    /**
     * 有效标识只能是0,1
     */
    FinalValid(4009013, "Valid-identification can only be 0 or 1."),

    /**
     * id必须为数字
     */
    IdIsNumber(4009014, "id is a number!"),

    /**
     * 删除异常，失败
     */
    DeleteFailure(4009015, "delete data failure."),

    /**
     * 查询失败
     */
    SelectFailure(4009016, "select data failure."),

    /**
     * 新增失败
     */
    AddFailure(4009017, "add data failure."),

    /**
     * 修改失败
     */
    UpdateFailure(4009018, "update data failure."),

    /**
     * 修改操作,id不能为空
     */
    IdNotEmpty(4009019, "the id can not be empty when modified"),

    /**
     * 修改失败，id不存在
     */
    IdNotExist(4009020, "fail to modify, this id does not exist"),

    /**
     * 用户不存在
     */
    UserNotExist(4009021, "the user does not exist."),

    /**
     * 批量插入失败
     */
    InsertBatchError(4009023, "InsertBatch failed."),

    /**
     * 插入数据均未通过校验，请调整文件内的数据
     */
    ValidListEmpty(4009025, "Import Failed."),

    /**
     * PageSize 必须是整数,大于0
     */
    PageSizeValueIsWrong(4009026, "PageSize must be number and  More than 0"),
    /**
     * 只支持csv，txt的文件导入
     */
    ImportFileType(4009027, "Only support import CSV or TXT file "),
    /**
     * 导入文件内容为空
     */
    ImportFileEmpty(4009028, "File is empty"),

    /**
     * 脚本调用异常
     */
    ScriptExceeption(4009030, "Script call exception."),

    /**
     * 错误的时间格式
     */
    DateFormatWrong(4009032,"Incorrect Date Format"),

    /**
     * 开始时间不能为空
     */
    StartTimeNotBlank(4009033, "Start time cannot be empty"),

    /**
     * 结束时间不能为空
     */
    EndTimeNotBlank(4009034, "End time cannot be empty"),
    /********************************公共 结束*************************************/

    /********************************用户登录 4004000开始*************************************/
    UserAccountExit(4004001, "No account exists"),
    UserAccount(4004002, "Account is not enabled, please login again after enabling"),


    /********************************用户登录 结束*************************************/

    ;

    /**
     * Code 状态码
     */
    private Integer code;
    /**
     * desc 描述
     */
    private String desc;

    Code(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
