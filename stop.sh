#!/bin/bash

echo "🛑 CafeSaaS projesi durduruluyor..."

cd ~/Masaüstü/cafesaas || exit
docker compose down

echo "✅ Sistem başarıyla temizlendi ve kapatıldı!"
