# Greedy Bond Allocation Algorithm

## 🧠 Overview

This project implements a **greedy algorithm** for allocating a pool of bonds to a list of financial **deals**, each of which has specific minimum asset class and credit rating requirements. The goal is to allocate bonds efficiently such that each deal’s constraints are satisfied using the least amount of capital or asset coverage.

This tool is intended for use in structured finance, portfolio management, or loan syndication applications.

---

## 📌 Problem Statement

Given:
- A pool of available **bonds**, each defined by:
  - `AssetClass` (e.g., Equity, Fixed Income)
  - `Rating` (e.g., AAA, BBB)
  - `Value` (nominal or market)

- A list of **deals**, each with:
  - Minimum required amounts per `AssetClass`
  - Minimum required amounts per `Rating`

### Objective:
Greedily assign bonds to deals such that:
- Each deal's constraints are met.
- Bonds are not double-counted.
- Allocation is efficient and minimal in cost or bond usage.

---

## ⚙️ How It Works

1. **Sort Deals** — by tightest constraint first (e.g., highest required asset total).
2. **Sort Bonds** — by decreasing value or most constrained asset class.
3. For each deal:
   - Loop through available bonds.
   - Assign bonds that contribute to unmet requirements.
   - Track which bonds are used and update remaining needs.
4. Stop when the deal is satisfied or bonds are exhausted.

---

## 🛠 Technologies

- Java 17+
- Maven/Gradle (build)
- JUnit (testing)
- Optional: Spring Boot for REST API exposure

---

## 📂 Project Structure
