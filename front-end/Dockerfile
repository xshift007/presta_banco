# Etapa 1: Construir la aplicación
FROM node:18-alpine AS build

WORKDIR /app

# Copiar los archivos package.json y package-lock.json
COPY package*.json ./

# Instalar las dependencias
RUN npm install

# Copiar el resto del código fuente
COPY . .

# Ejecutar el build
RUN npm run build

# Etapa 2: Servir la aplicación con Nginx
FROM nginx:stable-alpine

# Copiar el build generado en la etapa anterior
COPY --from=build /app/dist /usr/share/nginx/html

# Copiar el archivo de configuración de Nginx
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Copiar el script para reemplazar variables de entorno
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

# Exponer el puerto 80
EXPOSE 80

# Ejecutar el script de reemplazo y luego iniciar Nginx
CMD ["/entrypoint.sh"]
