Write-Host "Stopping containers..." -ForegroundColor Green
Invoke-Expression -Command "docker-compose.exe stop"
Invoke-Expression -Command "docker-compose.exe down"


Write-Host "Removing container for  crypto_api service..." -ForegroundColor Green
Invoke-Expression -Command "docker-compose.exe rm --force crypto_api"

Write-Host "Build new image for crypto_api service..." -ForegroundColor Green
Invoke-Expression -Command "docker-compose.exe build --no-cache crypto_api"

Write-Host "Up crypto_api service..." -ForegroundColor Green
Invoke-Expression -Command "docker-compose.exe up"
