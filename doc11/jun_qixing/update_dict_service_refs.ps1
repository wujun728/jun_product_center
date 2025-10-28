# PowerShell script to replace all occurrences of @sysDictService with @qixingSysDictService
# in template and static files within the qixing module

# Define the root directory to search
$rootDir = "d:\workspace\github\jun_product_center_private_qixing\jun_qixing\jun_qixing\qixing\src\main\resources"

# Define the file patterns to search
$filePatterns = @("*.html", "*.htm", "*.js")

# Define the search and replace strings
$searchString = "@sysDictService"
$replaceString = "@qixingSysDictService"

# Function to replace text in files
function Replace-TextInFiles($rootDir, $filePatterns, $searchString, $replaceString) {
    # Get all files matching the patterns
    $files = Get-ChildItem -Path $rootDir -Recurse -Include $filePatterns -File
    
    # Initialize counter
    $fileCount = 0
    $changeCount = 0
    
    # Process each file
    foreach ($file in $files) {
        # Read the file content
        $content = Get-Content -Path $file.FullName -Raw
        
        # Check if the file contains the search string
        if ($content -match $searchString) {
            # Count the number of occurrences
            $occurrences = ($content | Select-String -Pattern $searchString -AllMatches).Matches.Count
            $changeCount += $occurrences
            
            # Replace the text
            $newContent = $content -replace $searchString, $replaceString
            
            # Write the new content back to the file
            Set-Content -Path $file.FullName -Value $newContent -NoNewline
            
            # Increment the file counter
            $fileCount++
            
            Write-Host "Updated: $($file.FullName) - Found $occurrences occurrences"
        }
    }
    
    # Display summary
    Write-Host "Summary: Updated $fileCount files with a total of $changeCount occurrences replaced."
}

# Execute the replacement
Replace-TextInFiles -rootDir $rootDir -filePatterns $filePatterns -searchString $searchString -replaceString $replaceString