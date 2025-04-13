# ---- Build stage ----
FROM node:18 AS build

WORKDIR /app
COPY . .
RUN npm install
RUN npm run build

# ---- Runtime stage ----
FROM nginx:alpine

COPY --from=build /app/dist/frontend /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
