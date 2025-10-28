# 查找项目中的所有jar文件
Write-Host "查找项目中的jar文件..."
$projectJars = Get-ChildItem -Path . -Filter *.jar -Recurse | Where-Object { $_.FullName -like "*\target\*" } | Select-Object -ExpandProperty FullName

# 查找Maven仓库中的Spring依赖
Write-Host "查找Spring依赖..."
$mavenRepo = "D:\Java\.m2\repository"
$springJars = Get-ChildItem -Path "$mavenRepo\org\springframework" -Filter *.jar -Recurse | Select-Object -ExpandProperty FullName
$springBootJars = Get-ChildItem -Path "$mavenRepo\org\springframework\boot" -Filter *.jar -Recurse | Select-Object -ExpandProperty FullName

# 查找其他必要的第三方依赖
Write-Host "查找其他必要依赖..."
$alibabaJars = Get-ChildItem -Path "$mavenRepo\com\alibaba" -Filter *.jar -Recurse | Select-Object -ExpandProperty FullName
$mybatisJars = Get-ChildItem -Path "$mavenRepo\org\mybatis" -Filter *.jar -Recurse | Select-Object -ExpandProperty FullName
$baomidouJars = Get-ChildItem -Path "$mavenRepo\com\baomidou" -Filter *.jar -Recurse | Select-Object -ExpandProperty FullName
$mysqlJars = Get-ChildItem -Path "$mavenRepo\mysql" -Filter *.jar -Recurse | Select-Object -ExpandProperty FullName
$redisJars = Get-ChildItem -Path "$mavenRepo\redis" -Filter *.jar -Recurse | Select-Object -ExpandProperty FullName

# 构建classpath
Write-Host "构建classpath..."
$classpath = ($projectJars + $springJars + $springBootJars + $alibabaJars + $mybatisJars + $baomidouJars + $mysqlJars + $redisJars) -join ';'

# 显示找到的jar文件数量
Write-Host "找到 $($projectJars.Count) 个项目jar文件"
Write-Host "找到 $($springJars.Count + $springBootJars.Count) 个Spring依赖jar文件"
Write-Host "找到 $($alibabaJars.Count + $mybatisJars.Count + $baomidouJars.Count + $mysqlJars.Count + $redisJars.Count) 个其他依赖jar文件"

# 启动应用程序
Write-Host "启动应用程序..."
java -cp "$classpath" com.ruoyi.QixingApplication