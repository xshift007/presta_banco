#!/bin/sh

# Reemplazar VITE_BACKEND_URL_PLACEHOLDER por el valor de VITE_BACKEND_URL
if [ -n "$VITE_BACKEND_URL" ]; then
  echo "Reemplazando VITE_BACKEND_URL_PLACEHOLDER por $VITE_BACKEND_URL"
  find /usr/share/nginx/html -type f -exec sed -i "s|VITE_BACKEND_URL_PLACEHOLDER|$VITE_BACKEND_URL|g" {} \;
else
  echo "No se proporcionó VITE_BACKEND_URL, usando valor por defecto"
fi

# Iniciar Nginx
nginx -g 'daemon off;'
