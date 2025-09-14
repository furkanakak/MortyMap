# MortyMap


<img src="https://github.com/user-attachments/assets/42840ae5-5f83-4d26-82ea-dab11a13b8e4" width="250" />


## Overview

**MortyMap** is a Rick & Morty sample app showcasing a clean, modern Android stack: Jetpack **Compose** UI, **MVVM** architecture, **Hilt** for DI, **Retrofit/OkHttp** for networking, **Paging 3** for infinite lists, and **Coil** for images. It includes a reusable paging list with a full‑screen initial loader, an append loading footer, and a BottomAppBar to switch between **Characters** and **Locations**.

---

## Technologies Used

* **UI**: Jetpack Compose (Material 3), Navigation Compose
* **Architecture**: MVVM + Repository
* **DI**: Hilt (`@HiltAndroidApp`, `@AndroidEntryPoint`, `@HiltViewModel`)
* **Networking**: Retrofit + Gson Converter, OkHttp + Logging Interceptor
* **Paging**: Paging 3 (runtime + compose)
* **Images**: Coil
* **Kotlin**: 2.2.x
* **Android**: Min SDK 24, Target/Compile SDK 36

---

## Features

* **BottomAppBar navigation** between **Characters** and **Locations**
* **Page‑keyed pagination** (`1 → 2 → 3 …`)

  * `BasePagePagingSource` (only requires `page -> List<T>`)
  * `BasePagerFactory` (single place to configure Paging 3)
* **Reusable `PagingList`** component

  * Full‑screen **initial loader** during the first page fetch
  * **Append loader** footer while loading the next page
  * Optional append **Retry** footer on errors
  * Stable item keys (based on model id or hash)
* **Network latency simulation (debug‑only)** via `NetworkLatency.delayForPage(page)` to easily visualize loaders
* **Material 3** theming and **Coil** images

---

## Getting Started
To run MortyMap, clone the repository and open it in Android Studio. Make sure Android Studio and SDKs are up to date.

```bash
git clone https://github.com/furkanakak/MortyMap.git
cd MortyMap
