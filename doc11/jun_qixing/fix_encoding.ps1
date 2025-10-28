<#
.SYNOPSIS
修复HTML文件的中文乱码问题

.DESCRIPTION
此脚本将识别指定的HTML文件，检查其编码格式，并将其转换为正确的UTF-8编码，以解决中文乱码问题。

.EXAMPLE
./fix_encoding.ps1
#>

# 设置工作目录
Set-Location -Path "d:\workspace\github\jun_product_center_private_qixing\jun_qixing"

# 要修复的文件列表
$filesToFix = @(
    "jun_qixing/qixing/src/main/resources/static3/syscontent/list.html",
    "jun_qixing/qixing/src/main/resources/static3/syscontent/list0.html",
    "jun_qixing/qixing/src/main/resources/static3/syscontent/list_backup.html",
    "jun_qixing/qixing/src/main/resources/static3/users/user_edit.html",
    "jun_qixing/qixing/src/main/resources/static3/users/user_list.html",
    "jun_qixing/qixing/src/main/resources/templates/bizcustomertest/list.html",
    "jun_qixing/qixing/src/main/resources/templates/bizmail/list.html",
    "jun_qixing/qixing/src/main/resources/templates/biztest/list.html",
    "jun_qixing/qixing/src/main/resources/templates/hruserbecomemember/list.html",
    "jun_qixing/qixing/src/main/resources/templates/hruserhire/list.html",
    "jun_qixing/qixing/src/main/resources/templates/hruserinterview/list.html",
    "jun_qixing/qixing/src/main/resources/templates/hruseroffer/list.html",
    "jun_qixing/qixing/src/main/resources/templates/hruserresume/list.html",
    "jun_qixing/qixing/src/main/resources/templates/oalawinfo/list.html",
    "jun_qixing/qixing/src/main/resources/templates/oalawinfo/list2.html"
)

# 为每个文件创建备份并修复编码
foreach ($filePath in $filesToFix) {
    try {
        # 确保文件存在
        if (Test-Path -Path $filePath) {
            # 创建备份文件
            $backupPath = "$filePath.bak"
            Copy-Item -Path $filePath -Destination $backupPath -Force
            Write-Host "已创建备份: $backupPath"
            
            # 读取文件内容（自动检测编码）
            $content = Get-Content -Path $filePath -Raw -Encoding Byte
            
            # 尝试转换为UTF-8
            $utf8Content = [System.Text.Encoding]::UTF8.GetString($content)
            
            # 保存为UTF-8（带BOM）以确保兼容性
            [System.IO.File]::WriteAllText($filePath, $utf8Content, [System.Text.Encoding]::UTF8)
            
            Write-Host "已修复文件: $filePath"
        } else {
            Write-Host "警告: 文件不存在: $filePath" -ForegroundColor Yellow
        }
    } catch {
        Write-Host "错误: 处理文件 $filePath 时出错: $_" -ForegroundColor Red
    }
}

Write-Host "\nEncoding fix completed!" -ForegroundColor Green