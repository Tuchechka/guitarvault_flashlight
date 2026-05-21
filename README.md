# Plugin files

Цей zip = **2 файли** які заміняють згенеровані Flutter-ом.

## Послідовність

### 1. Створи скелет плагіна

```powershell
cd P:\project\mobile-lab
flutter create --template=plugin --platforms=android --org com.YOURNICK guitarvault_flashlight
cd guitarvault_flashlight
```

Заміни `YOURNICK` на свій нік (наприклад `nazar` або `nazariy`).

### 2. Заміни два файли

**Файл 1:** `lib/guitarvault_flashlight.dart`
→ повністю заміни вміст на мій `guitarvault_flashlight.dart`

**Файл 2:** Kotlin файл по шляху:
```
android/src/main/kotlin/com/YOURNICK/guitarvault_flashlight/GuitarvaultFlashlightPlugin.kt
```
→ повністю заміни вміст на мій `GuitarvaultFlashlightPlugin.kt`

⚠ У моєму Kotlin перший рядок: `package com.example.guitarvault_flashlight`
**Заміни на свій org**, наприклад: `package com.nazar.guitarvault_flashlight`

### 3. Видали зайве (опціонально)

Якщо в `lib/` є ще файли крім `guitarvault_flashlight.dart`:
- `guitarvault_flashlight_platform_interface.dart`
- `guitarvault_flashlight_method_channel.dart`

— видали їх. Ми їх не використовуємо. У `test/` теж можна видалити автотести (вони ламаються після видалення вище).

### 4. Перевірка білда

```powershell
flutter pub get
flutter analyze
```

Помилок не має бути.

### 5. Push на GitHub

На github.com → New repository → name: `guitarvault_flashlight` → **Public** → Create.

```powershell
git init
git add .
git commit -m "Initial commit: flashlight plugin"
git branch -M main
git remote add origin https://github.com/YOURNICK/guitarvault_flashlight.git
git push -u origin main
```

Готово — плагін у твоєму профілі.
