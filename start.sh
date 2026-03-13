#!/bin/bash

echo "🚀 CafeSaaS projesi başlatılıyor..."

# 1. Proje ana dizinine git ve Docker'ı arka planda başlat
cd ~/Masaüstü/cafesaas || exit
echo "🐳 Docker konteynerleri ayağa kaldırılıyor..."
docker compose up -d

# 2. Frontend dizinine geç ve geliştirici sunucusunu başlat
echo "💻 Frontend başlatılıyor..."
cd frontend || exit

# Kullandığın komuta göre burayı güncelleyebilirsin (npm start, npm run dev, yarn dev vb.)
npm run dev
