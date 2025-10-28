<#
.SYNOPSIS
高级HTML文件编码修复工具

.DESCRIPTION
此脚本使用更复杂的编码检测和转换方法，以解决HTML文件中的中文乱码问题。
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

# 函数：修复单个文件的编码
function Fix-FileEncoding($filePath) {
    try {
        # 确保文件存在
        if (-not (Test-Path -Path $filePath)) {
            Write-Host "Warning: File not found: $filePath" -ForegroundColor Yellow
            return
        }
        
        # 创建备份文件（如果还没有）
        $backupPath = "$filePath.bak"
        if (-not (Test-Path -Path $backupPath)) {
            Copy-Item -Path $filePath -Destination $backupPath -Force
            Write-Host "Created backup: $backupPath"
        }
        
        # 方法1：尝试使用UTF-8 with BOM读取
        try {
            $content = Get-Content -Path $backupPath -Raw -Encoding UTF8
            [System.IO.File]::WriteAllText($filePath, $content, [System.Text.Encoding]::UTF8)
            Write-Host "Fixed file with UTF-8: $filePath"
            return
        } catch {}
        
        # 方法2：尝试使用UTF-8 without BOM读取
        try {
            $content = Get-Content -Path $backupPath -Raw -Encoding Default
            [System.IO.File]::WriteAllText($filePath, $content, [System.Text.Encoding]::UTF8)
            Write-Host "Fixed file with Default encoding: $filePath"
            return
        } catch {}
        
        # 方法3：尝试使用字节分析
        try {
            $bytes = [System.IO.File]::ReadAllBytes($backupPath)
            
            # 检查BOM
            if ($bytes.Length -ge 3 -and $bytes[0] -eq 239 -and $bytes[1] -eq 187 -and $bytes[2] -eq 191) {
                # UTF-8 BOM
                $content = [System.Text.Encoding]::UTF8.GetString($bytes, 3, $bytes.Length - 3)
            } elseif ($bytes.Length -ge 2 -and $bytes[0] -eq 255 -and $bytes[1] -eq 254) {
                # UTF-16 LE
                $content = [System.Text.Encoding]::Unicode.GetString($bytes)
            } elseif ($bytes.Length -ge 2 -and $bytes[0] -eq 254 -and $bytes[1] -eq 255) {
                # UTF-16 BE
                $content = [System.Text.Encoding]::BigEndianUnicode.GetString($bytes)
            } else {
                # 默认假设为GB2312/GBK编码（常见的中文Windows编码）
                $content = [System.Text.Encoding]::GetEncoding(936).GetString($bytes)
            }
            
            # 保存为UTF-8 with BOM
            [System.IO.File]::WriteAllText($filePath, $content, [System.Text.Encoding]::UTF8)
            Write-Host "Fixed file with byte analysis: $filePath"
            return
        } catch {
            Write-Host "Error: Failed to fix file $filePath with byte analysis: $_" -ForegroundColor Red
        }
        
    } catch {
        Write-Host "Error: Failed to process file $filePath: $_" -ForegroundColor Red
    }
}

# 处理所有文件
foreach ($filePath in $filesToFix) {
    Fix-FileEncoding -filePath $filePath
}

Write-Host "\nAdvanced encoding fix process completed!" -ForegroundColor Green