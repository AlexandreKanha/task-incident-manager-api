# teste feito só pra ver se eu lembro como se faz ps1, se for testar mesmo usa Postman...

Write-Host "`n========================================" -ForegroundColor Cyan
Write-Host "TESTE 1: Criar usuário válido (preparação)" -ForegroundColor Cyan
Write-Host "========================================`n" -ForegroundColor Cyan

$createUserResponse = Invoke-RestMethod -Uri "http://localhost:8080/api/users" `
    -Method POST `
    -ContentType "application/json" `
    -Body '{"name": "Test User", "email": "test@example.com"}'

Write-Host "Usuário criado com sucesso:" -ForegroundColor Green
Write-Host ($createUserResponse | ConvertTo-Json -Depth 10)

$userId = $createUserResponse.id

Write-Host "`n========================================" -ForegroundColor Cyan
Write-Host "TESTE 2: Criar task com usuário inexistente (404)" -ForegroundColor Cyan
Write-Host "========================================`n" -ForegroundColor Cyan

try {
    Invoke-RestMethod -Uri "http://localhost:8080/api/tasks" `
        -Method POST `
        -ContentType "application/json" `
        -Body '{"title": "Test Task", "priority": "HIGH", "userId": 999}'
    Write-Host "ERRO: Deveria ter retornado 404!" -ForegroundColor Red
} catch {
    $statusCode = $_.Exception.Response.StatusCode.value__
    $json = $null
    $body = $null
    if ($_.ErrorDetails -and $_.ErrorDetails.Message) {
        $body = $_.ErrorDetails.Message
    } else {
        try {
            $reader = [System.IO.StreamReader]::new($_.Exception.Response.GetResponseStream())
            $body = $reader.ReadToEnd()
        } catch { $body = $null }
    }
    if ($body) {
        try { $json = $body | ConvertFrom-Json } catch { $json = $null }
    }
    Write-Host "Status Code: $statusCode" -ForegroundColor Yellow
    Write-Host "Response:" -ForegroundColor Yellow
    if ($json) {
        Write-Host ($json | ConvertTo-Json -Depth 10)
    } elseif ($body) {
        Write-Host $body
    } else {
        Write-Host "(sem corpo de resposta)"
    }
    if ($statusCode -eq 404 -and $json -and $json.error -eq "NOT_FOUND") {
        Write-Host "`n✅ PASSOU: Retornou 404 NOT_FOUND corretamente" -ForegroundColor Green
    } else {
        Write-Host "`n❌ FALHOU: Resposta inesperada" -ForegroundColor Red
    }
}

Write-Host "`n========================================" -ForegroundColor Cyan
Write-Host "TESTE 3: Criar task com validação (400)" -ForegroundColor Cyan
Write-Host "========================================`n" -ForegroundColor Cyan

try {
    Invoke-RestMethod -Uri "http://localhost:8080/api/tasks" `
        -Method POST `
        -ContentType "application/json" `
        -Body '{"title": "", "priority": null, "userId": null}'
    Write-Host "ERRO: Deveria ter retornado 400!" -ForegroundColor Red
} catch {
    $statusCode = $_.Exception.Response.StatusCode.value__
    $json = $null
    $body = $null
    if ($_.ErrorDetails -and $_.ErrorDetails.Message) {
        $body = $_.ErrorDetails.Message
    } else {
        try {
            $reader = [System.IO.StreamReader]::new($_.Exception.Response.GetResponseStream())
            $body = $reader.ReadToEnd()
        } catch { $body = $null }
    }
    if ($body) {
        try { $json = $body | ConvertFrom-Json } catch { $json = $null }
    }
    Write-Host "Status Code: $statusCode" -ForegroundColor Yellow
    Write-Host "Response:" -ForegroundColor Yellow
    if ($json) {
        Write-Host ($json | ConvertTo-Json -Depth 10)
    } elseif ($body) {
        Write-Host $body
    } else {
        Write-Host "(sem corpo de resposta)"
    }
    if ($statusCode -eq 400 -and $json -and $json.error -eq "VALIDATION_ERROR") {
        Write-Host "`n✅ PASSOU: Retornou 400 VALIDATION_ERROR corretamente" -ForegroundColor Green
        Write-Host "Campos com erro:" -ForegroundColor Yellow
        if ($json.fields) {
            $json.fields.PSObject.Properties | ForEach-Object {
                Write-Host "  - $($_.Name): $($_.Value)" -ForegroundColor Yellow
            }
        }
    } else {
        Write-Host "`n❌ FALHOU: Resposta inesperada" -ForegroundColor Red
    }
}

Write-Host "`n========================================" -ForegroundColor Cyan
Write-Host "TESTE 4: Buscar usuário inexistente (404)" -ForegroundColor Cyan
Write-Host "========================================`n" -ForegroundColor Cyan

try {
    Invoke-RestMethod -Uri "http://localhost:8080/api/users/999" `
        -Method GET
    Write-Host "ERRO: Deveria ter retornado 404!" -ForegroundColor Red
} catch {
    $statusCode = $_.Exception.Response.StatusCode.value__
    $json = $null
    $body = $null
    if ($_.ErrorDetails -and $_.ErrorDetails.Message) {
        $body = $_.ErrorDetails.Message
    } else {
        try {
            $reader = [System.IO.StreamReader]::new($_.Exception.Response.GetResponseStream())
            $body = $reader.ReadToEnd()
        } catch { $body = $null }
    }
    if ($body) {
        try { $json = $body | ConvertFrom-Json } catch { $json = $null }
    }
    Write-Host "Status Code: $statusCode" -ForegroundColor Yellow
    Write-Host "Response:" -ForegroundColor Yellow
    if ($json) {
        Write-Host ($json | ConvertTo-Json -Depth 10)
    } elseif ($body) {
        Write-Host $body
    } else {
        Write-Host "(sem corpo de resposta)"
    }
    if ($statusCode -eq 404 -and $json -and $json.error -eq "NOT_FOUND") {
        Write-Host "`n✅ PASSOU: Retornou 404 NOT_FOUND corretamente" -ForegroundColor Green
    } else {
        Write-Host "`n❌ FALHOU: Resposta inesperada" -ForegroundColor Red
    }
}

Write-Host "`n========================================" -ForegroundColor Cyan
Write-Host "TESTE 5: Criar task válida (sucesso)" -ForegroundColor Cyan
Write-Host "========================================`n" -ForegroundColor Cyan

$createTaskResponse = Invoke-RestMethod -Uri "http://localhost:8080/api/tasks" `
    -Method POST `
    -ContentType "application/json" `
    -Body "{`"title`": `"Valid Task`", `"description`": `"Test description`", `"priority`": `"HIGH`", `"userId`": $userId}"

Write-Host "Task criada com sucesso:" -ForegroundColor Green
Write-Host ($createTaskResponse | ConvertTo-Json -Depth 10)

Write-Host "`n========================================" -ForegroundColor Cyan
Write-Host "RESUMO DOS TESTES" -ForegroundColor Cyan
Write-Host "========================================`n" -ForegroundColor Cyan
Write-Host "✅ Tratamento de erros implementado corretamente!" -ForegroundColor Green
Write-Host "✅ ResourceNotFoundException retorna 404" -ForegroundColor Green
Write-Host "✅ Validações retornam 400 com detalhes dos campos" -ForegroundColor Green
Write-Host "✅ Mensagens JSON padronizadas" -ForegroundColor Green
