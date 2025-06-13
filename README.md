# Greedy Bond Allocation Algorithm

## 🧠 Overview

This project implements a **greedy algorithm** for allocating a pool of bonds to a list of financial **deals**, each of which has specific minimum asset class and credit rating requirements. The goal is to allocate bonds efficiently such that each deal’s constraints are satisfied using the least amount of capital or asset coverage.

This tool is intended for use in structured finance, portfolio management, or loan syndication applications.

---

## 📌 Problem Statement

Given:
- A pool of available **bonds**, each defined by:
  - `AssetClass` (e.g., Corporate, Sovereign, Municipal)
  - `Rating` (e.g., AAA, AA, BBB)
  - `Value` (nominal or market)
  - `Number available`

- A list of **deals**, each with:
  - Minimum required amounts per `AssetClass`
  - Minimum required amounts per `Rating`
  - Total value

### Objective:
Greedily assign bonds to deals such that:
- Each deal's constraints are met.
- Bonds are not double-counted.
- Allocation is efficient and minimal in borrowing cost for broker.
---

## ⚙️ How It Works

1. **Sort Deals** — by tightest constraint first (e.g., highest required asset total).
2. **Sort Bonds** — by decreasing value or most constrained asset class.
3. For each deal:
   - Loop through available bonds.
   - Assign bonds that contribute the most to the deals unmet requirements.
   - Track which bonds are used and update remaining needs of deal.
4. Stop when the deal is satisfied or bonds are exhausted.
5. Compute total amount of unallocated deals. This is the loan.

---

## 🛠 Technologies

- Java 17+
- Maven/Gradle (build)
- JUnit (testing)
- Optional future work: Spring Boot for REST API exposure

---

## 📂 Project Structure
