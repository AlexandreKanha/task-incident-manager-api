:: teste feito só pra testar kk
@echo off
echo.
echo ========================================
echo TESTE 1: Criar usuario valido
echo ========================================
echo.
curl -X POST http://localhost:8080/api/users -H "Content-Type: application/json" -d "{\"name\": \"Test User\", \"email\": \"test@example.com\"}"
echo.
echo.

echo ========================================
echo TESTE 2: Criar task com usuario inexistente (404)
echo ========================================
echo.
curl -X POST http://localhost:8080/api/tasks -H "Content-Type: application/json" -d "{\"title\": \"Test Task\", \"priority\": \"HIGH\", \"userId\": 999}"
echo.
echo.

echo ========================================
echo TESTE 3: Criar task com validacao (400)
echo ========================================
echo.
curl -X POST http://localhost:8080/api/tasks -H "Content-Type: application/json" -d "{\"title\": \"\", \"priority\": null, \"userId\": null}"
echo.
echo.

echo ========================================
echo TESTE 4: Buscar usuario inexistente (404)
echo ========================================
echo.
curl -X GET http://localhost:8080/api/users/999
echo.
echo.

echo ========================================
echo TESTE 5: Criar task valida
echo ========================================
echo.
curl -X POST http://localhost:8080/api/tasks -H "Content-Type: application/json" -d "{\"title\": \"Valid Task\", \"description\": \"Test description\", \"priority\": \"HIGH\", \"userId\": 1}"
echo.
echo.

pause
