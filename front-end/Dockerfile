# Etapa 1: Construir la aplicación
FROM node:18-alpine AS build

WORKDIR /app

# Copiar los archivos package.json y package-lock.json
COPY package*.json ./

# Instalar las dependencias
RUN npm install

# Copiar el resto del código fuente
COPY . .

# Argumento para la URL del backend
ARG VITE_BACKEND_URL=/api

# Establecer la variable de entorno para Vite
ENV VITE_BACKEND_URL=${VITE_BACKEND_URL}

# Ejecutar el build
RUN npm run build

# Etapa 2: Servir la aplicación con Nginx
FROM nginx:stable-alpine

# Copiar el build generado en la etapa anterior
COPY --from=build /app/dist /usr/share/nginx/html

# Copiar el archivo de configuración de Nginx
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Exponer el puerto 80 (Nginx sirve en el puerto 80 por defecto)
EXPOSE 80

# Ejecutar Nginx
CMD ["nginx", "-g", "daemon off;"]
