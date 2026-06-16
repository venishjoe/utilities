<#
 Copyright (c) 2023 Venish Joe Clarence (http://venishjoe.net)

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
#>

param (
    [Parameter(Mandatory=$true)]
    [string]$Directory
)

Get-ChildItem -Path $Directory -Filter "*.mov" | ForEach-Object {
    Write-Host "`n=== Processing $($_.FullName) ===" -ForegroundColor Yellow
    
    $ffmpegOutput = & ffmpeg -i $_.FullName 2>&1
    
    $timestamp = $ffmpegOutput | Where-Object { $_ -match 'creation_time\s*:\s*(\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2})\.000000Z' } | 
        Select-Object -First 1
    
    if ($timestamp) {
        try {
            $utcDateTime = [datetime]::ParseExact($matches[1], "yyyy-MM-ddTHH:mm:ss", [System.Globalization.CultureInfo]::InvariantCulture, [System.Globalization.DateTimeStyles]::AssumeUniversal)
            $localDateTime = $utcDateTime.ToLocalTime()
            
            $item = Get-Item $_.FullName
            $item.CreationTime = $localDateTime
            $item.LastWriteTime = $localDateTime
            $item.LastAccessTime = $localDateTime
            
            Write-Host "Updated timestamps to: $($item.CreationTime)"
        }
        catch {
            Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
        }
    }
}