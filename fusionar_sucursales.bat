@echo off
echo Cambiando a la rama main
git checkout main

echo Haciendo pull de la rama main...
git pull origin main

echo Haciendo merge de la rama 'Sebastian'...
git merge Sebastian

echo Subiendo cambios a GitHub...
git push origin main

echo Fusión completada. Presiona una tecla para salir.
pause
