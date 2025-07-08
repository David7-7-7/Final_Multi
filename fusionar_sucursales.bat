@echo off
echo Cambiando a la rama principal...
git checkout principal

echo Haciendo pull de la rama principal...
git pull origin principal

echo Haciendo merge de la rama 'Sebastián'...
git merge Sebastián

echo Subiendo cambios a GitHub...
git push origin principal

echo Fusión completada. Presiona una tecla para salir.
pause
