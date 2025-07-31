# 🚀 Spring Boot CI/CD with AWS Elastic Beanstalk

[![Java](https://img.shields.io/badge/Java-21-blue.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen)](https://spring.io/projects/spring-boot)
[![Elastic Beanstalk](https://img.shields.io/badge/AWS%20Elastic%20Beanstalk-PaaS-FF9900?logo=amazon-aws&logoColor=white)](https://aws.amazon.com/elasticbeanstalk/)
[![Parameter Store](https://img.shields.io/badge/AWS%20Parameter%20Store-Configuration-purple?logo=amazon-aws&logoColor=white)](https://docs.aws.amazon.com/systems-manager/latest/userguide/systems-manager-parameter-store.html)
[![Amazon RDS](https://img.shields.io/badge/Amazon%20RDS-Database-527FFF?logo=amazon-rds&logoColor=blue)](https://aws.amazon.com/rds/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17-red)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker-enabled-blue)](https://www.docker.com/)
[![Swagger](https://img.shields.io/badge/Swagger-API%20Docs-85EA2D?logo=swagger&logoColor=black)](https://swagger.io/)
[![GitHub Actions](https://img.shields.io/badge/GitHub%20Actions-CI%2FCD-white?logo=github-actions&logoColor=white)](https://github.com/features/actions)
[![Slack](https://img.shields.io/badge/Slack-Notifications-4A154B?logo=slack)](https://slack.com)

> This project is a showcase of real-world CI/CD pipeline automation using GitHub Actions, Docker, and various AWS services — including Elastic Beanstalk, RDS, ECR, Parameter Store, IAM, and Slack integration.  
> Originally started to support partner onboarding, the focus here is not on the application itself but rather on the infrastructure and deployment automation.

---

## 📚 Table of Contents

- [Tech Stack](#-tech-stack)
- [CI/CD Workflow](#️-cicd-workflow)
- [Secrets Management](#-secrets-management)
- [Swagger API Docs](#-swagger-api-docs)
- [Testing](#-testing)
- [Live API Demo (cURL)](#-live-api-demo-curl)
- [Deployment Highlights](#-deployment-highlights)
- [Possible Improvements](#-possible-improvements-future-scope)
- [Contributions](#-contributions)
- [Contact](#-contact)


---

## 🧱 Tech Stack

- **Language:** Java 21
- **Framework:** Spring Boot 3.2.0
- **Containerization:** Docker
- **Database:** PostgreSQL (AWS RDS)
- **CI/CD:** GitHub Actions
- **Cloud Services:**
  - AWS Elastic Beanstalk
  - AWS ECR
  - AWS RDS
  - AWS Systems Manager Parameter Store
  - AWS S3
  - AWS IAM
- **Notifications:** Slack integration via webhook
- **API Docs:** Swagger UI

---

## ⚙️ CI/CD Workflow

### 🔁 Build Workflow (`build.yml`)
Runs on every pull request to `main`:
- Spins up PostgreSQL service
- Builds the project
- Runs JUnit tests

### 🚀 Deployment Workflow (`deploy.yml`)
Triggered on `push` to `main`:
1. Sends Slack notification for deployment start
2. Builds the project and runs tests
3. Builds and pushes Docker image to AWS ECR
4. Creates `Dockerrun.aws.json` and zips it
5. Deploys to AWS Elastic Beanstalk
6. Sends Slack notification on success or failure

---


## 🔐 Secrets Management

- All sensitive values (DB credentials, AWS keys, etc.) are stored in:
  - **AWS Systems Manager Parameter Store**
  - **GitHub Secrets**

![Parameter Store Config](assets/aws-ssm-vars.PNG)

---

## 📖 Swagger API Docs

Swagger is integrated for REST API documentation.

Below is a quick walkthrough showing grouped endpoints and example operations.

![Swagger Demo](assets/swagger-demo.gif)

> This includes:
> - Registering and managing IP addresses
> - Viewing integration requests
> - Manager operations like approving integrations
> - and etc.

---

## 🧪 Testing

- **Frameworks Used:** JUnit
- **DB Setup:** PostgreSQL test DB via GitHub Actions service container
- **Note:** Only a few sample tests are added for demonstration purposes

---

## 🧪 Live API Demo (cURL)

Tested the deployment using terminal `curl` commands to verify end-to-end integration:

![Live API Demo](assets/live-demo.gif)

---


## 📌 Deployment Highlights

The following screenshots describes end-to-end deployment and configuration.

### ✅ Elastic Beanstalk - Environment Dashboard
![EB Environment](assets/aws-eb.PNG)

### 📦 ECR - Docker Image from CI/CD
![ECR Screenshot](assets/aws-ecr.PNG)

### 🗄️ RDS - PostgreSQL Instance
![RDS Screenshot](assets/aws-rds.PNG)

### 🔔 Slack - CI/CD Notifications
![Slack Screenshot](assets/slack-notification.PNG)


---

## 🧭 Possible Improvements (Future Scope)

- Improve testing coverage
- Add user authentication and role-based access
- Integrate monitoring (e.g., CloudWatch, Grafana)

---


## 🤝 Contributions

This is a personal showcase project and not actively maintained for feature completeness. However, feel free to fork it or use parts of it for your own CI/CD workflows.

---

## 📬 Contact

Built by **Kamran Zeynalov**  

[![LinkedIn](https://img.shields.io/badge/LinkedIn-blue?logo=linkedin&style=flat-square)](https://www.linkedin.com/in/zeynalov-kamran/)


---



