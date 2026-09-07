# Spring Boot & Kubernetes Study Case Assessment Repository

Welcome to the **Spring Boot & Kubernetes Assessment & Prep Repository**. This repository contains 6 hands-on study cases and assessment apps designed for practicing Spring Boot backend development (clean/hexagonal architecture, JPA, concurrency, security, performance), Angular 17+ standalone frontends, Dockerization, and Kubernetes (k8s) manifests/deployments.

---

## 📁 Subprojects & Architecture Summary

| Subproject | Domain / Focus | Key Architectural Patterns & Tech Stack |
| :--- | :--- | :--- |
| [`booking-app`](booking-app/) | Equipment Reservations | Event-Driven (Spring Events), JPA Specifications, Concurrency Control, Angular Frontend, Kubernetes Manifests |
| [`expenses-app`](expenses-app/) | Expense Management | Spring Boot REST API, PostgreSQL, Flyway, Angular Frontend, Kubernetes Manifests |
| [`incidents-app`](incidents-app/) | Incident Tracking | Spring Boot REST API, PostgreSQL, Flyway, Angular Frontend, Kubernetes Manifests |
| [`kb-app`](kb-app/) | Knowledge Base Articles | JPA Criteria API / Specification, Full-text Search, Paged Queries, Angular Frontend, Kubernetes Manifests |
| [`leave-portal-app`](leave-portal-app/) | Employee Leave Portal | Spring Boot REST API, PostgreSQL, Angular Frontend |
| [`siemens-prep-app`](siemens-prep-app/) | Self-Service Ticket Portal | Hexagonal Architecture (Domain / Application / Adapters), Java 21, Spring Boot 3, Angular 17 Standalone, Flyway, Docker, K8s, GitHub Actions |

---

## 🛠️ General Prerequisites

Before building or running any of the applications in this repository, ensure you have installed:

- **Java Development Kit (JDK 21+)**
- **Node.js (v18+)** & `npm`
- **Docker** & **Docker Compose**
- **Kubernetes CLI (`kubectl`)** & local k8s environment (e.g. Minikube, Kind, or Docker Desktop Kubernetes) *(optional, for running K8s manifests)*
- **Maven** (or use bundled Maven Wrappers where available)

---

## 🚀 Getting Started

Each directory contains its own self-contained backend, frontend, questions/tasks (`question.md`), and answer solutions (`answer.md`).

### Running a Subproject (e.g., `siemens-prep-app`)

1. **Navigate to the target subproject directory**:
   ```bash
   cd siemens-prep-app
   ```

2. **Run Backend (Spring Boot)**:
   ```bash
   cd backend
   ./mvnw spring-boot:run
   ```
   *Note: Ensure PostgreSQL is running or start it via Docker Compose if `docker-compose.yml` is present.*

3. **Run Frontend (Angular)**:
   ```bash
   cd ../frontend
   npm install
   npm start
   ```

4. **Run via Docker / Kubernetes**:
   Navigate to the `k8s/` or `infra/` folder inside the subproject and apply manifests:
   ```bash
   kubectl apply -f k8s/
   ```

---

## 🧪 Assessment Workflow

1. Open `question.md` in any subproject folder to inspect checklist tasks, bug fixes, or architectural requirements.
2. Complete your code implementations across backend, frontend, or deployment configurations.
3. Compare your solution against `answer.md` in the respective subproject folder.
